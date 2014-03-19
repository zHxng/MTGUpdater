import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetGUI extends JFrame {

    JButton image;
    JButton price;
    JButton close;

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
        setSize(300, 500);
        setMinimumSize(new Dimension(300, 500));
        setMaximumSize(new Dimension(300, 500));
        setSize(new Dimension(300, 500));
        setPreferredSize(new Dimension(300, 500));
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

        price = new JButton(" Fetch Prices ");
        gc.gridx = 0;
        gc.gridy = 1;
        add(price, gc);

        close = new JButton("      Close       ");
        gc.gridx = 0;
        gc.gridy = 2;
        add(close, gc);

        image.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                toImage();
            }
        });

        price.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                toPrice();
            }
        });

        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pack();
    }

    private void toImage() {
        new ImageFetcher(this).requestFocus();
    }

    private void toPrice(){
        new PriceFetcher(this).requestFocus();
    }

}
