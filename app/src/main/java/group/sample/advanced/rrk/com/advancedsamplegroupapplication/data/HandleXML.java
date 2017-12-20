package group.sample.advanced.rrk.com.advancedsamplegroupapplication.data;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by RyoRyeong Kim on 2017-12-15.
 */

public class HandleXML {

    private String title = "title";
    private String link ="link";
    private String description = "description";
    private String urlString = null;
    private XmlPullParserFactory xmlPullParserFactory;
    public volatile boolean parsingComplete = true;


    handlerInterface anInterface;
    public HandleXML(String url, handlerInterface anInterface){
        urlString = url;
        this.anInterface = anInterface;
    }

    public HanldeXML(String urlBase,String postFixKorean, handlerInterface anInterface) throws UnsupportedEncodingException {

        StringBuilder builder = new StringBuilder();


        builder.append(urlBase);
        builder.append(URLEncoder.encode(postFixKorean,"UTF-8"));
        urlString = builder.toString();
        this.anInterface = anInterface;
    }
    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getDescription() {
        return description;
    }


    public void parseXMLAndStoreIt(XmlPullParser myParser){
        int event;
        String text=  null;

        try{
           event = myParser.getEventType();
           while (event != XmlPullParser.END_DOCUMENT ) {
               String name = myParser.getName();

               switch (event) {
                   case XmlPullParser.START_TAG:
                       Log.d("TAG","XmlPullParser.StartTag..");
                       break;
                   case XmlPullParser.TEXT:
                       Log.d("TAG","XmlPullParser.TEXT.. : myParserText" + myParser.getText());
                       text = myParser.getText();
                       break;
                   case XmlPullParser.END_TAG:
                       Log.d("TAG","XmlPullParser.EndTag.." +name);

                       if(name.equals("title")){
                           title = text;
                       }

                       else if(name.equals("link")){
                           link = text;
                       }

                       else if(name.equals("description")){
                           description = text;
                       }
                           break;
                   default:
                       Log.e("TAG","default value is occured...");
                       break;

               }

               event = myParser.next();

           }


        }catch (Exception e){
            e.printStackTrace();
        }

        parsingComplete = false;



    }
    public void fetchXML(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try{
                    URL url  = new URL (urlString);




                    HttpURLConnection  urlConnection = (HttpURLConnection )url.openConnection();
                    urlConnection.setReadTimeout(10000);
                    urlConnection.setReadTimeout(10000 /* milliseconds */);
                    urlConnection.setConnectTimeout(15000 /* milliseconds */);
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setDoInput(true);

                    // set Query
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();

                    xmlPullParserFactory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = xmlPullParserFactory.newPullParser();

                    parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
                    parser.setInput( inputStream,null);

                    parseXMLAndStoreIt( parser );

                    inputStream.close();
                }catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }

                anInterface.fetchEnded();
            };
        });

        thread.start();
    }


    public interface handlerInterface{
        public void fetchEnded();
        public void fetchError();
    }
}
