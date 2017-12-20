package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Connection;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.nio.charset.MalformedInputException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

public class JSoupActivity extends AppCompatActivity {


    @BindView(R.id.btnPart)
    Button btnParse;

    @BindView(R.id.tvContent)
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);


        ButterKnife.bind(this);

    }

    @OnClick(R.id.btnPart)
    public void parseClicked(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                super.run();

                final StringBuilder builder = new StringBuilder();



                try {
                    Document doc = Jsoup.connect("http://www.ssaurel.com/blog/").get();
                    String title = doc.title();
                    Elements links = doc.select("a[href]");

                    builder.append(title).append("\n");

                    for (Element link : links) {
                        builder.append("\n").append("Link : ").append(link.attr("href")).append("\n")
                                .append("Text : ").append(link.text());
                    }
                }catch (MalformedInputException e){
                    builder.append("MalformedInputException : " + e.getMessage());
                }
                catch (IOException e ){
                    builder.append("IOException : " + e.getMessage());
                }catch (Exception e){
                    builder.append("Exception : " + e.getMessage());
                }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            tvContent.setText(builder.toString());
                        }
                    });

            }
        };

        thread.start();
    }


}
