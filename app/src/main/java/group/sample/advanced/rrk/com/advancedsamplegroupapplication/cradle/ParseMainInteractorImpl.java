package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

/**
 * Created by RyoRyeong Kim on 2017-12-20.
 */

public class ParseMainInteractorImpl implements ParseMainInteractor {


    ParseMainInteractor.OnFinishedListener onFinishedListener;
    Runnable fullBoardTask;

    public ParseMainInteractorImpl(  OnFinishedListener onFinishedListener ){
        this.onFinishedListener = onFinishedListener;

        fullBoardTask = () -> {
                final StringBuilder builder = new StringBuilder();

                try{

                    Connection connection = Jsoup.connect(Constants.PUBLIC_OPINION_BASE_URL);
                    connection.timeout(5000);
                    Document doc = connection.get();
                    Elements classDivs = doc.select("div[class]");

                    int i = 0;
                    for( Element ele : classDivs ){

                        i++;

                        switch (ele.attr("class")){
                            case "cspb_info text":

                                builder.append("title " + ele.select("h5").text());
                                builder.append("title " + ele.select("p").text());
                                break;
                            case "":
                                break;
                            default:
                                break;
                        }
                    }

                    onFinishedListener.onFinished(false, builder.toString(),new BoardData());
                } catch (MalformedInputException e){
                    builder.append("MalformedInputException : " + e.getMessage());

                    e.printStackTrace();
                    onFinishedListener.onFinished(true,builder.toString(),new BoardData());
                } catch (IOException e ){
                    builder.append("IOException : " + e.getMessage());
                    e.printStackTrace();
                    onFinishedListener.onFinished(true,builder.toString(),new BoardData());
                }catch (Exception e){
                    builder.append("Exception : " + e.getMessage());
                    e.printStackTrace();
                    onFinishedListener.onFinished(true,builder.toString(),new BoardData());
                }
        };
    }


    @Override
    public void init(AppCompatActivity activity) {

        if( activity instanceof MainParseActivity ){
            Thread thread = new Thread(fullBoardTask);
            thread.start();
        }

    }

    @Override
    public void initFragment(Fragment fragment) {

    }
}
