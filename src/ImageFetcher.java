import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.URL;

public class ImageFetcher extends JFrame {

    JButton back;
    JButton enter;
    JButton fle;
    JTextField set;
    JTextField abrev;
    JTextField loc;
    JLabel s;
    JLabel a;
    JLabel l;
    JLabel con;
    JFileChooser chooser;
    private boolean running = false;

    public ImageFetcher(JFrame prevGUI) {
        setVisible(true);
        setMinimumSize(new Dimension(300, 500));
        setMaximumSize(new Dimension(300, 500));
        setSize(new Dimension(300, 500));
        setPreferredSize(new Dimension(300, 500));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(prevGUI.getX(), prevGUI.getY());
        setTitle("MTGUpdater");

        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);

        GridBagConstraints gc = new GridBagConstraints();

        final JFileChooser fc = new JFileChooser();

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

        fle = new JButton("...");
        gc.gridx = 10;
        gc.gridy = 2;
        add(fle, gc);

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

        loc = new JTextField(/*insert file path from fle*/);
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

        con = new JLabel("Current File: ");
        gc.gridx = 0;
        gc.gridy = 5;
        add(con, gc);

        chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                if (running) {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Downloading aborted!");
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Downloading aborted!");
                }
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

        chooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooserHappened();
            }
        });

        fle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (running) {
                    running = false;
                    JOptionPane.showMessageDialog(null, "Downloading aborted!");
                }
                chooser.showOpenDialog(null);
            }
        });

        pack();
    }

    private void chooserHappened() {
        if (running) {
            running = false;
            JOptionPane.showMessageDialog(null, "Downloading aborted!");
        }

        File file = chooser.getSelectedFile();
        loc.setText(file.getPath());
    }

    private void thingHappened() {
        String setString = "http://api.mtgapi.com/v1/card/set/" + set.getText();
        String abrevString = abrev.getText();
        String saveTo = loc.getText();

        if (running) {
            running = false;
            JOptionPane.showMessageDialog(null, "Downloading aborted!");
        }

        running = true;
        getImage(setString.replaceAll(" ", "%20"), abrevString, saveTo);
    }

    public void getImage(final String l, final String p, final String lo) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 1;
                try {
                    JSONParser parser = new JSONParser();
                    while (running) {
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

                        changeText(p + "-" + object.get("name").toString().replaceAll(":", " ").replaceAll("Æ", "AE").replaceAll("//", " ") + ".jpg");
                        Thread.sleep(10L);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    if (e.getCause() == new IndexOutOfBoundsException().getCause()) {
                        JOptionPane.showMessageDialog(null, "Finished Downloading! :D");
                        running = false;
                    } else {
                        JOptionPane.showMessageDialog(null, "An Error Occured. Did you spell something wrong?");
                        running = false;
                    }
                }
            }
        }, "Download").start();
    }

    private void changeText(String s) {
        con.setText("Current File: " + s);
        System.out.println("here");
    }

    public void saveImage(String imageUrl, String destinationFile) throws IOException {
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
