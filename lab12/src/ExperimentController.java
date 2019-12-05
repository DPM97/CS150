import java.io.*;
import java.util.ArrayList;

/**
 * experiment controller class
 * @throws IOException exception for reader
 */
public class ExperimentController {
    public static void main(String[] args) throws IOException {
        ExperimentController controller = new ExperimentController();
        controller.test();
    }

    /**
     * test
     * @throws IOException exception for reader
     */

    public void test() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./src/L_numbers.csv"));
        ArrayList<StudentRecord> records = new ArrayList<>();
        String line = reader.readLine();
        while(line != null) {
            String[] split = line.split(",");
            StudentRecord record = new StudentRecord(split[1], split[2], split[0]);
            records.add(record);
            line = reader.readLine();
        }

        ArrayList<Long> dataOne = testChain(records, 500);
        ArrayList<Long> dataTwo = testLinear(records, 500);

        BufferedWriter writer = new BufferedWriter(new FileWriter(new File("out.csv")));
        for (int i = 0; i < dataTwo.size(); i++) {
            writer.append(Integer.toString(i));
            writer.append(",");
            writer.append(Long.toString(dataOne.get(i)));
            writer.append(",");
            writer.append(Double.toString(i * 0.01));
            writer.append(",");
            writer.append(Long.toString(dataTwo.get(i)));
            writer.append('\n');
        }
        writer.close();
    }

    /**
     * test chain implementation
     * @param data data arraylist
     * @param size maxsize
     * @return result
     */

    public ArrayList<Long> testChain(ArrayList<StudentRecord> data, int size) {
        ArrayList<Long> dataOut = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            ChainHashRoster table = new ChainHashRoster(size, i);
            for (int z = 0 ; z < 10000; z++) {
                    table.put(data.get(z));
                }
            long start = System.currentTimeMillis();
            table.get("F22596300");
            long end = System.currentTimeMillis();
            dataOut.add(end - start);
        }
        return dataOut;
    }

    /**
     * test linear implementation
     * @param data data arraylist
     * @param size maxsize
     * @return result
     */

    public ArrayList<Long> testLinear(ArrayList<StudentRecord> data, int size) {
        ArrayList<Long> dataOut = new ArrayList<>();
        for (double i = 0.01; i < 1; i+=0.01) {
            LinearHashRoster table = new LinearHashRoster(size, i);
            for (int z = 0 ; z < 10000; z++) {
                table.put(data.get(z));
            }
            long start = System.currentTimeMillis();
            table.get("F22596300");
            long end = System.currentTimeMillis();
            dataOut.add(end - start);
        }
        return dataOut;
    }
}
