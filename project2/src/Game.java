import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
     * constructor
     * @param map map obj
     */

    public Game(Map map) {
        this.bank = 300000;
        this.collected = 0;
        this.map = map;
        this.dwarfs = new PriorityQueue<Dwarf>();
        this.tries = 0;
        this.pitsDiscovered = new Stack<Dwarf>();
        this.goldDiscovered = new Stack<Dwarf>();
    }

    /**
     * start game - import dwarfs - run program until all gold is discovered
     * @throws IOException
     * @throws InterruptedException
     */

    public void start() throws IOException, InterruptedException {
        int gold = fetchTotalGold();
        BufferedReader reader = new BufferedReader(new FileReader("./src/dwarfs.txt"));
        String line = reader.readLine();
        while(this.bank >= 300 && line != null) {
            if (line.equals("digger")) {
                this.bank-=300;
                createDigger();
            } else if (line.equals("harvester")) {
                this.bank-=500;
                createHarvester();
            } else if (line.equals("builder")) {
                this.bank-=750;
                createBuilder();
            } else {
                System.out.println("invalid dwarf type");
            }
            line = reader.readLine();
        }

        while (this.collected < gold) {
            PriorityQueue<Dwarf> copy = new PriorityQueue<Dwarf>(this.dwarfs);
            while(!copy.isEmpty()){
                Dwarf curDwarf = copy.poll();
                if (curDwarf.getClass().getName() == "Digger") {
                    if (!this.map.checkDirt()) {
                        curDwarf.move();
                    }
                } else if (curDwarf.getClass().getName() == "Harvester") {
                    if (curDwarf.dwarf != null && curDwarf.status == "CHOOSING") {
                        System.out.println("HARVESTER GOING TO GOLD");
                        curDwarf.dig(curDwarf.dwarf);
                        curDwarf.status = "MOVING";
                    } else if (curDwarf.dwarf == null && curDwarf.status == "IDLE" && !this.goldDiscovered.isEmpty()) { //idle
                        System.out.println("HARVESTER GOT INSTRUCTIONS");
                        curDwarf.dwarf = this.goldDiscovered.peek();
                        this.goldDiscovered.pop();
                        curDwarf.goldLoc = curDwarf.dwarf.location;
                        curDwarf.status = "CHOOSING";
                    } else if (curDwarf.status == "MOVING" && curDwarf.dwarf != null) {
                        curDwarf.move();
                        if (curDwarf.location == curDwarf.goldLoc) { //has been built over gold
                            curDwarf.status = "IDLE";
                            curDwarf.dwarf = null;
                        }
                    } else if (curDwarf.dwarf == null && curDwarf.location != 0) {
                        curDwarf.goBack();
                    } else {
                        System.out.println("HARVESTER IS IDLE");
                        curDwarf.status = "IDLE";
                    }

                } else if (curDwarf.getClass().getName() == "Builder") {
                    if (curDwarf.dwarf != null && curDwarf.status == "CHOOSING") {
                        System.out.println("BUILDER GOING TO PIT");
                        curDwarf.fill(curDwarf.dwarf);
                        curDwarf.status = "MOVING";
                    } else if (curDwarf.dwarf == null && curDwarf.status == "IDLE" && !this.pitsDiscovered.isEmpty()) { //idle
                        System.out.println("BUILDER GOT INSTRUCTIONS");
                        curDwarf.dwarf = this.pitsDiscovered.peek();
                        this.pitsDiscovered.pop();
                        curDwarf.pitLoc = curDwarf.dwarf.location;
                        curDwarf.status = "CHOOSING";
                    } else if (curDwarf.status == "MOVING" && curDwarf.dwarf != null) {
                        curDwarf.move();
                        if (curDwarf.location == curDwarf.pitLoc) { //has been built over pit
                            curDwarf.status = "IDLE";
                            curDwarf.dwarf = null;
                        }
                    } else if (curDwarf.dwarf == null && curDwarf.location != 0) {
                        curDwarf.goBack();
                    } else {
                        System.out.println("BUILDER IS IDLE");
                        curDwarf.status = "IDLE";
                    }
                } else {
                    System.out.println("Invalid dwarf type");
                }
            }
            this.tries++;
            map.print();
            TimeUnit.SECONDS.sleep(1/100);
        }
    }

    /**
     * fetch all gold on map
     * @return number of gold tiles
     */

    public int fetchTotalGold() {
        int g = 0;
        for(int i = 0; i < this.map.totalElements; i++) {
            if (this.map.map.get(i).type.equals("G")) {
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
