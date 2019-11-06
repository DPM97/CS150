import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import javax.swing.plaf.IconUIResource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

/**
 * main game class
 */

public class Game {
    /**
     * player bank
     */
    int bank;
    /**
     * map obj
     */
    Map map;
    /**
     * queue of dwarfs in game
     */
    PriorityQueue<Dwarf> dwarfs;
    /**
     * gold that has been discovered but not harvested
     */
    Stack<Dwarf> goldDiscovered;
    /**
     * pits that have been discovered but not filled
     */
    Stack<Dwarf> pitsDiscovered;
    /**
     * amount of gol collected
     */
    int collected;
    /**
     * amount of moves made / scoring interval
     */
    int tries;
    /**
     * logger obj
     */
    Logger logger;

    /**
     * constructor
     *
     * @param map map obj
     * @throws IOException exception for logger
     */

    public Game(Map map) throws IOException {
        this.bank = 300000;
        this.collected = 0;
        this.map = map;
        this.dwarfs = new PriorityQueue<Dwarf>();
        this.tries = 0;
        this.pitsDiscovered = new Stack<Dwarf>();
        this.goldDiscovered = new Stack<Dwarf>();
        this.logger = new Logger(this);
    }

    /**
     * start game - import dwarfs - run program until all gold is discovered
     *
     * @throws IOException          file reader exception
     * @throws InterruptedException exception for sleep method
     */

    public void start() throws IOException, InterruptedException {
        int gold = fetchTotalGold();
        BufferedReader reader = new BufferedReader(new FileReader("./src/dwarfs.txt"));
        String line = reader.readLine();
        while (this.bank >= 300 && line != null) {
            if (line.equals("digger")) {
                this.bank -= 300;
                createDigger();
                this.logger.log("DIGGER HAS BEEN ADDED");
            } else if (line.equals("harvester")) {
                this.bank -= 500;
                createHarvester();
                this.logger.log("HARVESTER HAS BEEN ADDED");
            } else if (line.equals("builder")) {
                this.bank -= 750;
                createBuilder();
                this.logger.log("BUILDER HAS BEEN ADDED");
            } else {
                //System.out.println("invalid dwarf type");
            }
            line = reader.readLine();
        }

        while (this.collected < gold) {
            //fetchTotalGold();
            PriorityQueue<Dwarf> copy = new PriorityQueue<Dwarf>(this.dwarfs);
            while (!copy.isEmpty()) {
                Dwarf curDwarf = copy.poll();
                if (curDwarf.getClass().getName().equals("Digger")) {
                    if (!this.map.checkDirt()) {
                        curDwarf.move();
                    }
                } else if (curDwarf.getClass().getName().equals("Harvester")) {
                    if (curDwarf.dwarf != null && curDwarf.status == "CHOOSING") {
                        if (curDwarf.dig(curDwarf.dwarf)) {
                            curDwarf.status = "MOVING";
                            //System.out.println("HARVESTER GOING TO GOLD");
                            this.logger.log(curDwarf + " GOING TO GOLD");
                        } else {
                            curDwarf.status = "IDLE";
                        }
                    } else if (curDwarf.dwarf == null && curDwarf.status == "IDLE" && !this.goldDiscovered.isEmpty()) { //idle
                        //System.out.println("HARVESTER GOT INSTRUCTIONS");
                        curDwarf.dwarf = this.goldDiscovered.pop();
                        curDwarf.goldLoc = curDwarf.dwarf.location;
                        curDwarf.status = "CHOOSING";
                    } else if (curDwarf.status == "MOVING" && curDwarf.dwarf != null) {
                        curDwarf.move();
                        if (curDwarf.location == 0 && curDwarf.status.equals("IDLE")) {
                            curDwarf.dwarf = null;
                        }
                    } else if (curDwarf.dwarf == null && curDwarf.location != 0 && !curDwarf.status.equals("CLEANING")) {
                        curDwarf.goBack();
                    } else {
                        this.logger.log(curDwarf + " IDLE");
                        curDwarf.status = "IDLE";
                    }

                } else if (curDwarf.getClass().getName().equals("Builder")) {
                    if (curDwarf.dwarf != null && curDwarf.status.equals("CHOOSING")) {
                        if (curDwarf.fill(curDwarf.dwarf)) {
                            curDwarf.status = "MOVING";
                            this.logger.log(curDwarf + " GOING TO PIT");
                        } else {
                            curDwarf.status = "IDLE";
                        }
                    } else if (curDwarf.dwarf == null && curDwarf.status.equals("IDLE") && !this.pitsDiscovered.isEmpty()) { //idle
                        curDwarf.dwarf = this.pitsDiscovered.pop();
                        curDwarf.pitLoc = curDwarf.dwarf.location;
                        curDwarf.status = "CHOOSING";
                    } else if (curDwarf.status.equals("MOVING") && curDwarf.dwarf != null) {
                        curDwarf.move();
                        if (curDwarf.location == 0 && curDwarf.status.equals("IDLE")) {
                            curDwarf.dwarf = null;
                        }
                    } else if (curDwarf.dwarf == null && curDwarf.location != 0) {
                        //System.out.println("MOVING BACK");
                        curDwarf.goBack();
                    } else {
                        //System.out.println("BUILDER IS IDLE");
                        this.logger.log(curDwarf + " IDLE");
                        if (this.map.checkDirt()) {
                            cleanup();
                        } else {
                            curDwarf.status = "IDLE";
                        }
                    }
                } else {
                    //System.out.println("Invalid dwarf type");
                }

            }
            this.tries++;
            //this.map.print();
            TimeUnit.SECONDS.sleep(1 / 2);

        }
        System.out.println("TOTAL MOVES: " + this.tries);
        this.logger.log("TOTAL MOVES: " + this.tries);
        this.logger.close();
    }

    /**
     * clean up the rest of the map
     */
    public void cleanup() throws IOException {
        PriorityQueue<Dwarf> copy = new PriorityQueue<Dwarf>(this.dwarfs);
        while (!copy.isEmpty()) {
            Dwarf curDwarf = copy.poll();
            if (curDwarf.getClass().getName().equals("Harvester")) {
                curDwarf.status = "CLEANING";
                curDwarf.fill(null);
            }
        }
    }

    /**
     * fetch all gold on map
     *
     * @return number of gold tiles
     */

    public int fetchTotalGold() {
        int g = 0;
        for (int i = 0; i < this.map.totalElements; i++) {
            if (this.map.map.get(i).type.equals("G")) {
                //System.out.println(i);
                g++;
            }
        }
        return g;
    }

    /**
     * create builder obj
     * and add to queue
     */

    public void createBuilder() {
        this.dwarfs.add(new Builder(this.map, this));
    }

    /**
     * create harvester obj
     * and add to queue
     */

    public void createHarvester() {
        this.dwarfs.add(new Harvester(this.map, this));
    }

    /**
     * create digger obj
     * and add to queue
     */

    public void createDigger() {
        this.dwarfs.add(new Digger(this.map, this));
    }


}
