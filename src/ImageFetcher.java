import something.GetImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImageFetcher extends JFrame{

    JButton back;
    JButton enter;
    JTextField set;
    JTextField abrev;
    JTextField loc;
    JLabel s;
    JLabel a;
    JLabel l;
    JTextArea con;

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

        s = new JLabel("Set: ");
        gc.gridx = 0;
        gc.gridy = 0;
        add(s, gc);

        a = new JLabel("Abrev: ");
        gc.gridx = 0;
        gc.gridy = 1;
        add(a, gc);

        l = new JLabel("Save To: ");
        gc.gridx = 0;
        gc.gridy = 2;
        add(l, gc);

        set = new JTextField();
        gc.gridx = 5;
        gc.gridy = 0;
        set.setColumns(15);
        add(set, gc);

        abrev = new JTextField();
        gc.gridx = 5;
        gc.gridy = 1;
        abrev.setColumns(15);
        add(abrev, gc);

        loc = new JTextField();
        gc.gridx = 5;
        gc.gridy = 2;
        loc.setColumns(15);
        add(loc, gc);

        back = new JButton("<-- Back");
        gc.gridx = 0;
        gc.gridy = 4;
        add(back, gc);

        enter = new JButton("Submit");
        gc.gridx = 5;
        gc.gridy = 4;
        add(enter, gc);

        con = new JTextArea();
        gc.gridx = 2;
        gc.gridy = 5;

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SetGUI();
            }
        });

        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                thingHappened();
            }
        });

        pack();
    }

    private void thingHappened() {
        GetImage myImage = new GetImage();

        String setString = "http://api.mtgapi.com/v1/card/set/" + set.getText();
        String abrevString = abrev.getText();
        String saveTo = loc.getText();

        try {
            myImage.getImage(setString.replaceAll(" ", "%20"), abrevString, saveTo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "An Error Occured. Did you spell something wrong?");
        }

        con.append(myImage.cardName());
    }

}
