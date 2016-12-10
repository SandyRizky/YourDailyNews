package id.sch.smktelkom_mlg.learn.yourdailynews;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String link = extras.getString("link");

        WebView display = (WebView) findViewById(R.id.webDetail);

        display.loadUrl(link);
//        display.setBackgroundColor(0);
//        display.loadDataWithBaseURL("", "<img src='"+ link +"' style='display: inline;height: auto;max-width: 100%'/>",
//                "text/html", "UTF-8", "");
    }
}
