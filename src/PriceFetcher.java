import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class PriceFetcher extends JFrame{

    JButton back;
    JButton urlButton;
    JTextField card;

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
        gc.gridy = 1;
        add(back, gc);

        urlButton = new JButton("Submit");
        gc.gridx = 1;
        gc.gridy = 1;
        add(urlButton, gc);

        card = new JTextField();
        gc.gridx = 0;
        gc.gridy = 0;
        gc.gridwidth = 100;
        card.setColumns(20);
        card.setText("Insert Card");
        add(card, gc);

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
        getCookie(this.card.getText());
    }

    private void getCookie(String card) throws Exception {
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

        connectTo(SID, PID, UID, IID, card);
    }

    private void connectTo(String sid, String pid, String uid, String iid, String card) throws Exception {
        Connection.Response response = Jsoup.connect("http://www.starcitygames.com/")
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
        System.out.println(doc.body());
    }

}
