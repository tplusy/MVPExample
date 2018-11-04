package plusy.top.mvpexample;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import plusy.top.mvpexample.presenter.DownloadPresenter;
import plusy.top.mvpexample.view.IDisplayView;

public class MainActivity extends AppCompatActivity implements IDisplayView {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.hello);
        DownloadPresenter downloadPresenter = new DownloadPresenter(this);
        downloadPresenter.download(Uri.parse("https://top.plusy/test.mp3"));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void showProcess(int process) {
        textView.setText(String.format("%d%%", process));
    }

    @Override
    public void showDone(Object o) {
        Toast.makeText(this, "Download file OK.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Download file error!", Toast.LENGTH_SHORT).show();
    }
}
