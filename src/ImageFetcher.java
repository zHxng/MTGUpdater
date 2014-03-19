import javax.swing.*;
import java.awt.*;

public class ImageFetcher extends JFrame{

    public ImageFetcher() {
        setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("MTGUpdater: Image Fetcher");

        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);

        GridBagConstraints gc = new GridBagConstraints();

        pack();
    }

}
