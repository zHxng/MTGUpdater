import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class PriceFetcher extends JFrame{

    JButton back;
    JButton urlButton;
    JTextField url;
    JTextField cookie;

    public PriceFetcher(JFrame prevGUI){
        setVisible(true);
        setSize(300, 500);
        setMinimumSize(new Dimension(300, 500));
        setMaximumSize(new Dimension(300, 500));
        setSize(new Dimension(300, 500));
        setPreferredSize(new Dimension(300, 500));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(prevGUI.getX(), prevGUI.getY());
        setTitle("MTGUpdater");

        GridBagLayout gl = new GridBagLayout();
        setLayout(gl);

        GridBagConstraints gc = new GridBagConstraints();

        back = new JButton("<-- Back");
        gc.gridx = 0;
        gc.gridy = 2;
        add(back, gc);

        urlButton = new JButton("Submit");
        gc.gridx = 1;
        gc.gridy = 2;
        add(urlButton, gc);

        url = new JTextField();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 100;
        url.setColumns(20);
        url.setText("Insert Url");
        add(url, gc);

        cookie = new JTextField();
        gc.gridx = 0;
        gc.gridy = 1;
        gc.gridwidth = 100;
        cookie.setColumns(20);
        cookie.setText("Insert Cookie");
        add(cookie, gc);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SetGUI();
            }
        });

        urlButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
//                    fetchPrice();
                    test();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        pack();
    }

    private void fetchPrice() throws Exception {
        URL url = new URL(this.url.getText());

        URLConnection connection = url.openConnection();
        connection.setRequestProperty("Cookie",  this.cookie.getText());

        InputStream inputStream;
    }

    private void test() throws Exception {
        URL obj = new URL(this.url.getText());
        URLConnection conn = obj.openConnection();

        //get all headers
        Map<String, List<String>> map = conn.getHeaderFields();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            System.out.println("Key : " + entry.getKey() + " ,Value : " + entry.getValue());
        }

        //get header by 'key'
        String server = conn.getHeaderField("Server");
    }

}
