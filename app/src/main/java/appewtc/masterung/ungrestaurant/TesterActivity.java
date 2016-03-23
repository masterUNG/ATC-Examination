package appewtc.masterung.ungrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TesterActivity extends AppCompatActivity {

    //Explicit
    private TextView studentTextView, dateTextView, questionTextView;
    private RadioGroup choiceRadioGroup;
    private RadioButton choice1RadioButton, choice2RadioButton,
            choice3RadioButton, choice4RadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tester);

        //Bind Widget
        bindWidget();

    }   // Main Method

    private void bindWidget() {
        studentTextView = (TextView) findViewById(R.id.textView2);
        dateTextView = (TextView) findViewById(R.id.textView3);
        questionTextView = (TextView) findViewById(R.id.textView4);
        choiceRadioGroup = (RadioGroup) findViewById(R.id.ragChoice);
        choice1RadioButton = (RadioButton) findViewById(R.id.radioButton);
        choice2RadioButton = (RadioButton) findViewById(R.id.radioButton2);
        choice3RadioButton = (RadioButton) findViewById(R.id.radioButton3);
        choice4RadioButton = (RadioButton) findViewById(R.id.radioButton4);
    }

}   // Main Class
