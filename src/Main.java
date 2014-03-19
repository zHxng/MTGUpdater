import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        SetGUI myGUI = new SetGUI();
        //if(){
        GetImage.getImage(getLink(), getAbrev(), getLocation());
        //}else{

        //}

        System.out.println("Working!");
    }

    private static String getLink() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Set Link?");
        String link = "http://api.mtgapi.com/v1/card/set/" + scan.nextLine().replaceAll(" ", "%20");

        return link;
    }

    private static String getAbrev() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Set Abbreviation?");
        String pre = scan.nextLine();

        return pre;
    }

    private static String getLocation() {
        Scanner scan = new Scanner(System.in);

        System.out.println("Where to save?");
        String location = "D:\\MTG\\ImagesRequired\\" + scan.nextLine();

        return location;
    }

}