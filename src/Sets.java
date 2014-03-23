import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Sets {

    public static HashMap<String, String> main() {
        Scanner in = null;
        try {
            in = new Scanner(new File("sets.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        HashMap<String, String> sets = new HashMap<String, String>();
        assert in != null;
        while (in.hasNext()) {
            String line = in.nextLine();
            if (line.split(" ")[0].equals("OTHERS")) {
                sets.put(line.split(" ")[0], line.substring(7));
            } else
                sets.put(line.split(" ")[0], line.substring(4));
        }
        return sets;
    }

}
