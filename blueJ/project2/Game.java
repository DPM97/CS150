import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
    Stack<Stack<Integer>> goldDiscovered;
    /**
     * pits that have been discovered but not filled
     */
    Stack<Stack<Integer>> pitsDiscovered;
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
        this.bank = 3000;
        this.collected = 0;
        this.map = map;
        this.dwarfs = new PriorityQueue<Dwarf>();
        this.tries = 0;
        this.pitsDiscovered = new Stack<Stack<Integer>>();
        this.goldDiscovered = new Stack<Stack<Integer>>();
    }

    /**
     * start game - import dwarfs - run program until all gold is discovered
     * @throws IOException
     * @throws InterruptedException
     */

    public void start() throws IOException, InterruptedException {
        int gold = fetchTotalGold();
        BufferedReader reader = new BufferedReader(new FileReader("./dwarfs.txt"));
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
                    curDwarf.move();


                } else if (curDwarf.getClass().getName() == "Harvester") {
                    if (this.goldDiscovered.size() != 0 && curDwarf.memory.isEmpty()) { //
                        curDwarf.goldLoc = this.goldDiscovered.peek();
                        this.goldDiscovered.pop();
                    } else if (curDwarf.goldLoc != null) { //on its way to the gold
                        if (curDwarf.goldLoc.size() == 1) {
                            curDwarf.dig(curDwarf.goldLoc.peek());
                            curDwarf.goldLoc = null;
                        } else {
                            curDwarf.memory.push(curDwarf.location);
                            curDwarf.location = curDwarf.goldLoc.pop();
                        }
                    } else {
                        if (curDwarf.memory.size() != 0) {
                            System.out.println("HARVESTER GOING TO GOLD");
                            curDwarf.location = curDwarf.memory.pop();
                        } else {
                            System.out.println("HARVESTER IDLE");
                        }
                    }




                } else if (curDwarf.getClass().getName() == "Builder") {
                    if (this.pitsDiscovered.size() != 0 && curDwarf.memory.isEmpty()) { //
                        curDwarf.pitLoc = this.pitsDiscovered.peek();
                        this.pitsDiscovered.pop();
                    } else if (curDwarf.pitLoc != null) { //on its way to the gold
                        if (curDwarf.pitLoc.size() == 1) {
                            curDwarf.fill(curDwarf.pitLoc.peek());
                            curDwarf.pitLoc = null;
                        } else {
                            curDwarf.memory.push(curDwarf.location);
                            curDwarf.location = curDwarf.pitLoc.pop();
                        }
                    } else {
                        if (curDwarf.memory.size() != 0) {
                            System.out.println("BUILDER GOING TO PIT");
                            curDwarf.location = curDwarf.memory.pop();
                        } else {
                            System.out.println("BUILDER IDLE");
                        }
                    }



                } else {
                    System.out.println("Invalid dwarf type");
                }


            }
            this.tries++;
            TimeUnit.SECONDS.sleep(1);
            //System.out.println("Tries: " + this.tries);
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
        this.dwarfs.add(new Builder(this.map));
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

    /**
     * kill dwarf (might not be useful if I decide to just avoid all traps)
     * @param dwarf dwarf that will be killed
     */

    public void kill(Dwarf dwarf) {
        this.dwarfs.remove(dwarf);
        this.bank -= 100; //pay family
    }

}
