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
//        String stuff = "%7B%22appName%22%3A%22Netscape%22%2C%22platform%22%3A%22Win32%22%2C%22cookies%22%3A1%2C%22syslang%22%3A%22en-US%22%2C%22userlang%22%3A%22en-US%22%2C%22cpu%22%3A%22%22%2C%22productSub%22%3A%2220030107%22%2C%22plugins%22%3A%7B%220%22%3A%22WidevineContentDecryptionModule%22%2C%221%22%3A%22ShockwaveFlash%22%2C%222%22%3A%22ChromeRemoteDesktopViewer%22%2C%223%22%3A%22NativeClient%22%2C%224%22%3A%22ChromePDFViewer%22%2C%225%22%3A%22AdobeExManDetect%22%2C%226%22%3A%22AdobeAcrobat%22%2C%227%22%3A%22AdobeAAMDetect%22%2C%228%22%3A%22WolframMathematica%22%2C%229%22%3A%22GoogleUpdate%22%2C%2210%22%3A%22NPLastPass%22%2C%2211%22%3A%22SilverlightPlug-In%22%2C%2212%22%3A%22MicrosoftOffice2013%22%2C%2213%22%3A%22PandoWebPlugin%22%2C%2214%22%3A%22VLCWebPlugin%22%2C%2215%22%3A%22UnityPlayer%22%2C%2216%22%3A%22CitrixOnlineWebDeploymentPlugin1.0.0.104%22%2C%2217%22%3A%22NASAEyes%22%2C%2218%22%3A%22ShockwaveFlash%22%7D%2C%22mimeTypes%22%3A%7B%220%22%3A%22WidevineContentDecryptionModuleapplication%2Fx-ppapi-widevine-cdm%22%2C%221%22%3A%22ShockwaveFlashapplication%2Fx-shockwave-flash%22%2C%222%22%3A%22FutureSplashPlayerapplication%2Ffuturesplash%22%2C%223%22%3A%22application%2Fvnd.chromium.remoting-viewer%22%2C%224%22%3A%22NativeClientExecutableapplication%2Fx-nacl%22%2C%225%22%3A%22PortableNativeClientExecutableapplication%2Fx-pnacl%22%2C%226%22%3A%22PortableDocumentFormatapplication%2Fpdf%22%2C%227%22%3A%22PortableDocumentFormatapplication%2Fx-google-chrome-print-preview-pdf%22%2C%228%22%3A%22AdobeExManDetectapplication%2Fx-adobeexmandetect%22%2C%229%22%3A%22AcrobatPortableDocumentFormatapplication%2Fpdf%22%2C%2210%22%3A%22AdobePDFinXMLFormatapplication%2Fvnd.adobe.pdfxml%22%2C%2211%22%3A%22AdobePDFinXMLFormatapplication%2Fvnd.adobe.x-mars%22%2C%2212%22%3A%22AcrobatFormsDataFormatapplication%2Fvnd.fdf%22%2C%2213%22%3A%22XMLVersionofAcrobatFormsDataFormatapplication%2Fvnd.adobe.xfdf%22%2C%2214%22%3A%22AcrobatXMLDataPackageapplication%2Fvnd.adobe.xdp%2Bxml%22%2C%2215%22%3A%22AdobeFormFlow99DataFileapplication%2Fvnd.adobe.xfd%2Bxml%22%2C%2216%22%3A%22AdobeAAMDetectapplication%2Fx-adobeaamdetect%22%2C%2217%22%3A%22ComputableDocumentFormatapplication%2Fvnd.wolfram.cdf%22%2C%2218%22%3A%22ComputableDocumentFormatapplication%2Fvnd.wolfram.cdf.text%22%2C%2219%22%3A%22WolframMathematicaNotebookapplication%2Fvnd.wolfram.mathematica%22%2C%2220%22%3A%22WolframMathematicaPlayerNotebookapplication%2Fvnd.wolfram.player%22%2C%2221%22%3A%22WolframMathematicaNotebookapplication%2Fmathematica%22%2C%2222%22%3A%22application%2Fx-vnd.google.update3webcontrol.3%22%2C%2223%22%3A%22application%2Fx-vnd.google.oneclickctrl.9%22%2C%2224%22%3A%22nplastpassapplication%2Fx-vnd-lastpass%22%2C%2225%22%3A%22npctrlapplication%2Fx-silverlight%22%2C%2226%22%3A%22application%2Fx-silverlight-2%22%2C%2227%22%3A%22LyncPlug-inforFirefoxapplication%2Fvnd.microsoft.communicator.ocsmeeting%22%2C%2228%22%3A%22Thisplug-indetectsandlaunchesPandoMediaBoosterapplication%2Fx-pandoplugin%22%2C%2229%22%3A%22MPEGaudioaudio%2Fmpeg%22%2C%2230%22%3A%22MPEGaudioaudio%2Fx-mpeg%22%2C%2231%22%3A%22MPEGvideovideo%2Fmpeg%22%2C%2232%22%3A%22MPEGvideovideo%2Fx-mpeg%22%2C%2233%22%3A%22MPEGvideovideo%2Fmpeg-system%22%2C%2234%22%3A%22MPEGvideovideo%2Fx-mpeg-system%22%2C%2235%22%3A%22MPEG-4audioaudio%2Fmp4%22%2C%2236%22%3A%22MPEG-4audioaudio%2Fx-m4a%22%2C%2237%22%3A%22MPEG-4videovideo%2Fmp4%22%2C%2238%22%3A%22MPEG-4videoapplication%2Fmpeg4-iod%22%2C%2239%22%3A%22MPEG-4videoapplication%2Fmpeg4-muxcodetable%22%2C%2240%22%3A%22MPEG-4videovideo%2Fx-m4v%22%2C%2241%22%3A%22AVIvideovideo%2Fx-msvideo%22%2C%2242%22%3A%22Oggstreamapplication%2Fogg%22%2C%2243%22%3A%22Oggvideovideo%2Fogg%22%2C%2244%22%3A%22Oggstreamapplication%2Fx-ogg%22%2C%2245%22%3A%22VLCplug-inapplication%2Fx-vlc-plugin%22%2C%2246%22%3A%22WindowsMediaVideovideo%2Fx-ms-asf-plugin%22%2C%2247%22%3A%22WindowsMediaVideovideo%2Fx-ms-asf%22%2C%2248%22%3A%22WindowsMediaapplication%2Fx-mplayer2%22%2C%2249%22%3A%22WindowsMediavideo%2Fx-ms-wmv%22%2C%2250%22%3A%22WindowsMediaVideovideo%2Fx-ms-wvx%22%2C%2251%22%3A%22WindowsMediaAudioaudio%2Fx-ms-wma%22%2C%2252%22%3A%22GoogleVLCplug-inapplication%2Fx-google-vlc-plugin%22%2C%2253%22%3A%22WAVaudioaudio%2Fwav%22%2C%2254%22%3A%22WAVaudioaudio%2Fx-wav%22%2C%2255%22%3A%223GPPaudioaudio%2F3gpp%22%2C%2256%22%3A%223GPPvideovideo%2F3gpp%22%2C%2257%22%3A%223GPP2audioaudio%2F3gpp2%22%2C%2258%22%3A%223GPP2videovideo%2F3gpp2%22%2C%2259%22%3A%22DivXvideovideo%2Fdivx%22%2C%2260%22%3A%22FLVvideovideo%2Fflv%22%2C%2261%22%3A%22FLVvideovideo%2Fx-flv%22%2C%2262%22%3A%22Matroskavideoapplication%2Fx-matroska%22%2C%2263%22%3A%22Matroskavideovideo%2Fx-matroska%22%2C%2264%22%3A%22Matroskaaudioaudio%2Fx-matroska%22%2C%2265%22%3A%22Playlistxspfapplication%2Fxspf%2Bxml%22%2C%2266%22%3A%22MPEGaudioaudio%2Fx-mpegurl%22%2C%2267%22%3A%22WebMvideovideo%2Fwebm%22%2C%2268%22%3A%22WebMaudioaudio%2Fwebm%22%2C%2269%22%3A%22RealMediaFileapplication%2Fvnd.rn-realmedia%22%2C%2270%22%3A%22RealMediaAudioaudio%2Fx-realaudio%22%2C%2271%22%3A%22AMRaudioaudio%2Famr%22%2C%2272%22%3A%22FLACaudioaudio%2Fx-flac%22%2C%2273%22%3A%22UnityPlayerdatafileapplication%2Fvnd.unity%22%2C%2274%22%3A%22CitrixOnlineAppDetectorPluginapplication%2Fx-col-application-detector%22%2C%2275%22%3A%22NASAEyesapplicatin%2Fx-nasa-eyes%22%2C%2276%22%3A%22AdobeFlashmovieapplication%2Fx-shockwave-flash%22%2C%2277%22%3A%22FutureSplashmovieapplication%2Ffuturesplash%22%7D%2C%22screen%22%3A%7B%22width%22%3A1280%2C%22height%22%3A800%2C%22colorDepth%22%3A24%7D%2C%22fonts%22%3A%7B%220%22%3A%22Calibri%22%2C%221%22%3A%22Cambria%22%2C%222%22%3A%22Terminal%22%2C%223%22%3A%22Constantia%22%2C%224%22%3A%22LucidaBright%22%2C%225%22%3A%22Georgia%22%2C%226%22%3A%22SegoeUI%22%2C%227%22%3A%22Candara%22%2C%228%22%3A%22TrebuchetMS%22%2C%229%22%3A%22Verdana%22%2C%2210%22%3A%22Consolas%22%2C%2211%22%3A%22LucidaConsole%22%2C%2212%22%3A%22LucidaSansTypewriter%22%2C%2213%22%3A%22CourierNew%22%2C%2214%22%3A%22Courier%22%7D%7D";
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
