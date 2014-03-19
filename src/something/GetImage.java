package something;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class GetImage {

    public static void getImage(String l, String p, String lo) {
        try {
            JSONParser parser = new JSONParser();
            boolean not = true;
            int i = 1;
            while (not) {
                URL url = new URL(l);
                BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
                JSONArray array = (JSONArray) parser.parse(reader);
                JSONObject object = (JSONObject) array.get(i);
                String id = object.get("id").toString();
                URL idURL = new URL("http://api.mtgapi.com/v1/card/id/" + id);
                BufferedReader reader1 = new BufferedReader(new InputStreamReader(idURL.openStream()));
                JSONArray array1 = (JSONArray) parser.parse(reader1);
                JSONObject object1 = (JSONObject) array1.get(0);
                String imageURL = object1.get("image").toString();
                saveImage(imageURL, lo + "\\" + p + "-" + object.get("name").toString().replaceAll(":", " ").replaceAll("Æ", "AE").replaceAll("//", " ") + ".jpg");

                i++;

                System.out.println(p + "-" + object.get("name").toString().replaceAll(":", " ").replaceAll("Æ", "AE").replaceAll("//", " ") + ".jpg");
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

}