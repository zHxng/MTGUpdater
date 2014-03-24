import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

public class CsvIO {
    public ArrayList<ArrayList<String>> cells = new ArrayList<ArrayList<String>>();
    private final File f;

    public CsvIO(File csv) throws Exception {
        f = csv;
        BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(csv)));
        String line;
        while ((line = input.readLine()) != null) {
            String[] list = line.split(",");
            ArrayList<String> l = new ArrayList<String>();
            for (int i = 0; i < list.length; i++) {
                l.add(list[i]);
            }
            cells.add(l);
        }
        input.close();
    }

    public synchronized String get(int row, int column) {
        try {
            return cells.get(row).get(column);
        } catch (Exception e) {
            return "";
        }
    }

    public synchronized void set(int row, int column, String s) {
        while (row >= cells.size()) {
            cells.add(new ArrayList<String>());
        }
        ArrayList<String> r = cells.get(row);
        while (column >= r.size()) {
            r.add("");
        }
        r.set(column, s);
    }

    public synchronized void save() throws Exception {
        save(f);
    }

    public synchronized void save(File csv) throws Exception {
        PrintWriter writer = new PrintWriter(csv);
        for (int i = 0; i < cells.size(); i++) {
            ArrayList<String> row = cells.get(i);
            for (int j = 0; j < row.size(); j++) {
                writer.print(row.get(j) + ",");
            }
            writer.println();
        }
        writer.close();
    }

}