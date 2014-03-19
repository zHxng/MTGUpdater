import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetGUI extends JFrame {

    JButton image;
    JButton price;

    public SetGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setVisible(true);
        setSize(700, 700);
        setMinimumSize(new Dimension(700,700));
        setMaximumSize(new Dimension(700,700));
        setSize((new Dimension(700,700)));
        setPreferredSize((new Dimension(700,700)));
        setLocationRelativeTo(null);
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
        image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                toImage();
            }
        });

        pack();
    }

    public void toImage() {
        new ImageFetcher(this).requestFocus();
    }

}
