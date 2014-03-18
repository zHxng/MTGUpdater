import javax.swing.*;
import java.awt.*;

public class setGUI extends JFrame{

    JButton image;
    JButton price;

    public setGUI(){
        setVisible(true);
        setSize(700, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("MTGUpdater");

        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);

        GridBagConstraints gc = new GridBagConstraints();

        image = new JButton("Fetch Images");
        gc.gridx = 0;
        gc.gridy = 0;
        add(image, gc);

        price = new JButton("Fetch Prices");
        gc.gridx = 0;
        gc.gridy = 1;
        add(price, gc);

        pack();
    }
}
