import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Stack;

public class Game {
    int bank;
    Map map;
    PriorityQueue<Dwarf> dwarfs;
    Stack<Integer> goldDiscovered;
    Stack<Integer> pitsDiscovered;
    int collected;
    int tries;

    public Game(Map map) {
        this.bank = 3000;
        this.collected = 0;
        this.map = map;
        this.dwarfs = new PriorityQueue<Dwarf>();
        this.tries = 0;
    }

    public void start() throws IOException {
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
        }

        while (this.collected < gold) {
            PriorityQueue<Dwarf> copy = this.dwarfs;
            while(!copy.isEmpty()){
                Dwarf curDwarf = copy.poll();
                if (curDwarf.getClass().getName() == "Digger") {
                    curDwarf.right();
                } else if (curDwarf.getClass().getName() == "Harvester") {
                    if (this.goldDiscovered.size() != 0) {
                        //go to the gold
                    }
                } else if (curDwarf.getClass().getName() == "Builder") {
                    if (this.pitsDiscovered.size() != 0) {
                        //go to the pit
                    }
                } else {
                    System.out.println("Invalid dwarf type");
                }


                System.out.println(curDwarf.location);
            }
            this.tries++;
            //System.out.println("Tries: " + this.tries);
        }
    }

    public int fetchTotalGold() {
        int g = 0;
        for(int i = 0; i < this.map.totalElements; i++) {
            if (this.map.map.get(i).equals("G")) {
                g++;
            }
        }
        return g;
    }

    public void createBuilder() {
        this.dwarfs.add(new Builder(this.map));
    }

    public void createHarvester() {
        this.dwarfs.add(new Harvester(this.map));
    }

    public void createDigger() {
        this.dwarfs.add(new Digger(this.map));
    }

    public void kill(Dwarf dwarf) {
        this.dwarfs.remove(dwarf);
        this.bank -= 100; //pay family
    }

}
