import javax.swing.*;
import java.awt.*;

public class ImageFetcher extends JFrame{

    JButton back;
    JButton enter;
    JTextField set;
    JTextField abrev;
    JTextField loc;
    JLabel s;
    JLabel a;
    JLabel l;

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

        back = new JButton("<-- Back");
        gc.gridx = -10;
        gc.gridy = 10;
        add(back, gc);

        enter = new JButton("Submit");
        gc.gridx = -1;
        gc.gridy = 4;
        add(enter, gc);

        set = new JTextField();
        gc.gridx = -1;
        gc.gridy = 0;
        set.setSize(50, 10);
        add(set, gc);

        abrev = new JTextField();
        gc.gridx = -1;
        gc.gridy = 1;
        abrev.setSize(50, 10);
        add(abrev, gc);

        loc = new JTextField();
        gc.gridx = -1;
        gc.gridy = 2;
        loc.setSize(50, 10);
        add(loc, gc);

        s = new JLabel("Set: ");
        gc.gridx = 1;
        gc.gridy = 0;
        add(s, gc);

        a = new JLabel("Abrev: ");
        gc.gridx = 1;
        gc.gridy = 1;
        add(a, gc);

        l = new JLabel("Save To: ");
        gc.gridx = 1;
        gc.gridy = 2;
        add(l, gc);

        pack();
    }

}
