import javax.swing.*;
import java.awt.*;

public class ImageFetcher extends JFrame{

    public ImageFetcher(JFrame prevGUI) {
        setVisible(true);
        setMinimumSize(new Dimension(700,700));
        setMaximumSize(new Dimension(700,700));
        setSize((new Dimension(700,700)));
        setPreferredSize((new Dimension(700,700)));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(prevGUI.getX(), prevGUI.getY());
        setTitle("MTGUpdater");

        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);

        GridBagConstraints gc = new GridBagConstraints();

        pack();
    }

}
