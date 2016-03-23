package appewtc.masterung.ungrestaurant;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

public class ShowScoreActivity extends AppCompatActivity {

    //Explicit
    private TextView studentTextView, dateTextView, scoreTextView;
    private String studentString, dateString, scoreString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        //Bind Widget
        bindWidget();

        //Show View
        showView();

    }   // Main Method

    public void clickSaveData(View view) {

        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        try {

            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            nameValuePairs.add(new BasicNameValuePair("isAdd", "true"));
            nameValuePairs.add(new BasicNameValuePair("Student", studentString));
            nameValuePairs.add(new BasicNameValuePair("Date", dateString));
            nameValuePairs.add(new BasicNameValuePair("Score", scoreString));

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost("http://swiftcodingthai.com/atc/php_add_score.php");
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
            httpClient.execute(httpPost);

            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(ShowScoreActivity.this, "สำเร็จ", "บันทึกข้อมูลเรียบร้อย");

        } catch (Exception e) {
            MyAlertDialog myAlertDialog = new MyAlertDialog();
            myAlertDialog.myDialog(ShowScoreActivity.this, "มีความผิดปกติ", "ไม่สามารถ บันทึกข้อมูลได้");
        }





    }   // clickSave

    private void showView() {
        studentString = getIntent().getStringExtra("Student");
        dateString = getIntent().getStringExtra("Date");
        scoreString = Integer.toString(getIntent().getIntExtra("Score", 0));

        studentTextView.setText(studentString);
        dateTextView.setText(dateString);
        scoreTextView.setText(scoreString);
    }

    private void bindWidget() {
        studentTextView = (TextView) findViewById(R.id.textView5);
        dateTextView = (TextView) findViewById(R.id.textView6);
        scoreTextView = (TextView) findViewById(R.id.textView7);
    }

}   // Main Class
