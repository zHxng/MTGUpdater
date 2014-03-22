import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PriceFetcher extends JFrame {

    JButton back;
    JButton urlButton;
    JTextField card;
    JTextField set;
    JLabel label;
    JLabel setLabel;

    public PriceFetcher(JFrame prevGUI) {
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

        label = new JLabel("Card: ");
        gc.gridx = 0;
        gc.gridy = 0;
        add(label, gc);

        setLabel = new JLabel("Set: ");
        gc.gridx = 0;
        gc.gridy = 1;
        add(setLabel, gc);

        card = new JTextField();
        gc.gridx = 1;
        gc.gridy = 0;
        gc.gridwidth = 100;
        card.setColumns(20);
        add(card, gc);

        set = new JTextField();
        gc.gridx = 1;
        gc.gridy = 1;
        gc.gridwidth = 100;
        set.setColumns(20);
        add(set, gc);

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
                    fetchPrice();
//                    getCookie(card.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });

        pack();
    }

    private void fetchPrice() throws Exception {
        getCookie(this.card.getText(), this.set.getText());
    }

    private void getCookie(String card, String set) throws Exception {
        Connection.Response response = Jsoup.connect("http://www.starcitygames.com/")
                .method(Connection.Method.GET)
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Connection", "keep-alive")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.8,fr;q=0.6")
                .header("Cache-Control", "max-age=0")
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36")
                .execute();

        Document doc = response.parse();
        Element script = doc.getElementsByTag("script").get(0);
        Connection.Response scriptResponse = Jsoup.connect("http://www.starcitygames.com" + script.attributes().get("src"))
                .method(Connection.Method.GET)
                .execute();
        String dvh = scriptResponse.body().split("=")[1].split("\"")[1];
        String stuff = "{\"appName\":\"Netscape\",\"platform\":\"Win32\",\"cookies\":1,\"syslang\":\"en-US\",\"userlang\":\"en-US\",\"cpu\":\"\",\"productSub\":\"20030107\",\"plugins\":{\"0\":\"WidevineContentDecryptionModule\",\"1\":\"ShockwaveFlash\",\"2\":\"ChromeRemoteDesktopViewer\",\"3\":\"NativeClient\",\"4\":\"ChromePDFViewer\",\"5\":\"AdobeExManDetect\",\"6\":\"AdobeAcrobat\",\"7\":\"AdobeAAMDetect\",\"8\":\"WolframMathematica\",\"9\":\"GoogleUpdate\",\"10\":\"NPLastPass\",\"11\":\"SilverlightPlug-In\",\"12\":\"MicrosoftOffice2013\",\"13\":\"PandoWebPlugin\",\"14\":\"VLCWebPlugin\",\"15\":\"UnityPlayer\",\"16\":\"CitrixOnlineWebDeploymentPlugin1.0.0.104\",\"17\":\"NASAEyes\",\"18\":\"ShockwaveFlash\"},\"mimeTypes\":{\"0\":\"WidevineContentDecryptionModuleapplication/x-ppapi-widevine-cdm\",\"1\":\"ShockwaveFlashapplication/x-shockwave-flash\",\"2\":\"FutureSplashPlayerapplication/futuresplash\",\"3\":\"application/vnd.chromium.remoting-viewer\",\"4\":\"NativeClientExecutableapplication/x-nacl\",\"5\":\"PortableNativeClientExecutableapplication/x-pnacl\",\"6\":\"PortableDocumentFormatapplication/pdf\",\"7\":\"PortableDocumentFormatapplication/x-google-chrome-print-preview-pdf\",\"8\":\"AdobeExManDetectapplication/x-adobeexmandetect\",\"9\":\"AcrobatPortableDocumentFormatapplication/pdf\",\"10\":\"AdobePDFinXMLFormatapplication/vnd.adobe.pdfxml\",\"11\":\"AdobePDFinXMLFormatapplication/vnd.adobe.x-mars\",\"12\":\"AcrobatFormsDataFormatapplication/vnd.fdf\",\"13\":\"XMLVersionofAcrobatFormsDataFormatapplication/vnd.adobe.xfdf\",\"14\":\"AcrobatXMLDataPackageapplication/vnd.adobe.xdp+xml\",\"15\":\"AdobeFormFlow99DataFileapplication/vnd.adobe.xfd+xml\",\"16\":\"AdobeAAMDetectapplication/x-adobeaamdetect\",\"17\":\"ComputableDocumentFormatapplication/vnd.wolfram.cdf\",\"18\":\"ComputableDocumentFormatapplication/vnd.wolfram.cdf.text\",\"19\":\"WolframMathematicaNotebookapplication/vnd.wolfram.mathematica\",\"20\":\"WolframMathematicaPlayerNotebookapplication/vnd.wolfram.player\",\"21\":\"WolframMathematicaNotebookapplication/mathematica\",\"22\":\"application/x-vnd.google.update3webcontrol.3\",\"23\":\"application/x-vnd.google.oneclickctrl.9\",\"24\":\"nplastpassapplication/x-vnd-lastpass\",\"25\":\"npctrlapplication/x-silverlight\",\"26\":\"application/x-silverlight-2\",\"27\":\"LyncPlug-inforFirefoxapplication/vnd.microsoft.communicator.ocsmeeting\",\"28\":\"Thisplug-indetectsandlaunchesPandoMediaBoosterapplication/x-pandoplugin\",\"29\":\"MPEGaudioaudio/mpeg\",\"30\":\"MPEGaudioaudio/x-mpeg\",\"31\":\"MPEGvideovideo/mpeg\",\"32\":\"MPEGvideovideo/x-mpeg\",\"33\":\"MPEGvideovideo/mpeg-system\",\"34\":\"MPEGvideovideo/x-mpeg-system\",\"35\":\"MPEG-4audioaudio/mp4\",\"36\":\"MPEG-4audioaudio/x-m4a\",\"37\":\"MPEG-4videovideo/mp4\",\"38\":\"MPEG-4videoapplication/mpeg4-iod\",\"39\":\"MPEG-4videoapplication/mpeg4-muxcodetable\",\"40\":\"MPEG-4videovideo/x-m4v\",\"41\":\"AVIvideovideo/x-msvideo\",\"42\":\"Oggstreamapplication/ogg\",\"43\":\"Oggvideovideo/ogg\",\"44\":\"Oggstreamapplication/x-ogg\",\"45\":\"VLCplug-inapplication/x-vlc-plugin\",\"46\":\"WindowsMediaVideovideo/x-ms-asf-plugin\",\"47\":\"WindowsMediaVideovideo/x-ms-asf\",\"48\":\"WindowsMediaapplication/x-mplayer2\",\"49\":\"WindowsMediavideo/x-ms-wmv\",\"50\":\"WindowsMediaVideovideo/x-ms-wvx\",\"51\":\"WindowsMediaAudioaudio/x-ms-wma\",\"52\":\"GoogleVLCplug-inapplication/x-google-vlc-plugin\",\"53\":\"WAVaudioaudio/wav\",\"54\":\"WAVaudioaudio/x-wav\",\"55\":\"3GPPaudioaudio/3gpp\",\"56\":\"3GPPvideovideo/3gpp\",\"57\":\"3GPP2audioaudio/3gpp2\",\"58\":\"3GPP2videovideo/3gpp2\",\"59\":\"DivXvideovideo/divx\",\"60\":\"FLVvideovideo/flv\",\"61\":\"FLVvideovideo/x-flv\",\"62\":\"Matroskavideoapplication/x-matroska\",\"63\":\"Matroskavideovideo/x-matroska\",\"64\":\"Matroskaaudioaudio/x-matroska\",\"65\":\"Playlistxspfapplication/xspf+xml\",\"66\":\"MPEGaudioaudio/x-mpegurl\",\"67\":\"WebMvideovideo/webm\",\"68\":\"WebMaudioaudio/webm\",\"69\":\"RealMediaFileapplication/vnd.rn-realmedia\",\"70\":\"RealMediaAudioaudio/x-realaudio\",\"71\":\"AMRaudioaudio/amr\",\"72\":\"FLACaudioaudio/x-flac\",\"73\":\"UnityPlayerdatafileapplication/vnd.unity\",\"74\":\"CitrixOnlineAppDetectorPluginapplication/x-col-application-detector\",\"75\":\"NASAEyesapplicatin/x-nasa-eyes\",\"76\":\"AdobeFlashmovieapplication/x-shockwave-flash\",\"77\":\"FutureSplashmovieapplication/futuresplash\"},\"screen\":{\"width\":1280,\"height\":800,\"colorDepth\":24},\"fonts\":{\"0\":\"Calibri\",\"1\":\"Cambria\",\"2\":\"Terminal\",\"3\":\"Constantia\",\"4\":\"LucidaBright\",\"5\":\"Georgia\",\"6\":\"SegoeUI\",\"7\":\"Candara\",\"8\":\"TrebuchetMS\",\"9\":\"Verdana\",\"10\":\"Consolas\",\"11\":\"LucidaConsole\",\"12\":\"LucidaSansTypewriter\",\"13\":\"CourierNew\",\"14\":\"Courier\"}}";
        Connection.Response finalResponse = Jsoup.connect("http://www.starcitygames.com" + script.attributes().get("src"))
                .method(Connection.Method.POST)
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36")
                .referrer("http://www.starcitygames.com")
                .header("Connection", "keep-alive")
                .header("Origin", "http://www.starcitygames.com")
                .header("X-Distil-Ajax", dvh)
                .data("p", stuff)
                .execute();
        Map<String, String> cookies = finalResponse.cookies();
        String SID = cookies.get("D_SID");
        String PID = cookies.get("D_PID");
        String UID = cookies.get("D_UID");
        String IID = cookies.get("D_IID");

        connectTo(SID, PID, UID, IID, card, set);
    }

    private void connectTo(String sid, String pid, String uid, String iid, String card, String set) throws Exception {
        Connection.Response response = Jsoup.connect("http://sales.starcitygames.com/search.php?substring=" + card)
                .method(Connection.Method.GET)
                .header("Accept-Encoding", "gzip,deflate,sdch")
                .header("Connection", "keep-alive")
                .header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
                .header("Accept-Language", "en-US,en;q=0.8,fr;q=0.6")
                .header("Cache-Control", "max-age=0")
                .userAgent("Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36")
                .cookie("D_SID", sid)
                .cookie("D_PID", pid)
                .cookie("D_UID", uid)
                .cookie("D_IID", iid)
                .execute();

        Document doc = response.parse();
        Elements trElements = doc.getElementsByTag("tr");

        // Price: deckdbbody search_results_9
        // Set: deckdbbody search_results_2

        // $: rmUuft
        // 0: pZOspA rmUuft kznGOn
        // 1: rmUuft jDhAhn2 kznGOn
        // 2:
        // 3:
        // 4: kznGOn wBJlhw rmUuft
        // 5: vDqctQ rmUuft kznGOn
        // 5: rmUuft ltJnjG2 kznGOn
        // .: ASEUvo kznGOn rmUuft

        BufferedImage image = null;
        char[] charArray;

        boolean gotten = false;
        for (Element trElement : trElements) {
            if (trElement.hasClass("deckdbbody_row") || trElement.hasClass("deckdbbody_row2")) {
                Elements tdElements = trElement.getElementsByTag("td");
                for (Element tdElement : tdElements) {
                    if (tdElement.hasClass("search_results_2")) {
                        System.out.println(tdElement.html());
                    }
                    if (tdElement.hasClass("search_results_9")) {
                        Elements divElements = tdElement.getElementsByTag("div");
                        for (Element divElement : divElements) {
                            if (divElement.classNames().size() == 3 && !gotten) {
                                gotten = true;
                                for (Object object : divElement.classNames().toArray()) {
                                    String string = String.valueOf(object);
                                    Element style = doc.select("style").first();
                                    Matcher cssMatcher = Pattern.compile("[.](\\w+)\\s*[{]([^}]+)[}]").matcher(style.html());
                                    while (cssMatcher.find()) {
                                        if (cssMatcher.group(1).equals(string)) {
                                            if (cssMatcher.group(2).contains("//")) {
                                                System.out.println("here");
                                                String s = "http://" + cssMatcher.group(2).split("//")[1].replace(");", "");
                                                System.out.println(s);
                                                URL url = new URL(s);
                                                URLConnection con = url.openConnection();
                                                con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                                                con.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.154 Safari/537.36");
                                                con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
                                                con.setRequestProperty("Cookie", "D_SID=" + sid + ";"
                                                                + "D_PID=" + pid + ";"
                                                                + "D_UID=" + uid + ";"
                                                                + "D_IID=" + iid + ";"
                                                );
                                                con.setRequestProperty("Referer", response.url().toString());
                                                InputStream input = con.getInputStream();
                                                image = ImageIO.read(input);
                                            }
                                        }
                                    }
                                }
//                                System.out.println(image);
//                                URL url = new URL(image);

                                assert image != null;
                                ImageIcon icon = new ImageIcon(image);
                                charArray = ((String) JOptionPane.showInputDialog(null,
                                        "Enter 1st row numbers as they appear.",
                                        "Not a catchpa! (StarCityGames is stupid)",
                                        JOptionPane.QUESTION_MESSAGE, icon, null, null)).toCharArray();
                            }
                        }
                    }

                }
            }
        }
    }

}
