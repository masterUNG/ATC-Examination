package appewtc.masterung.ungrestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private EditText userEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private String userString, passwordString;
    private MyManage myManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Request Database
        myManage = new MyManage(this);

        //Button Controller
        buttonController();

        //Tester Add Value
        //tester();

        //Delete All SQLite
        deleteAllSQLite();

        //Synchronize JSON to SQLite
        synJSONtoSQLite();


    }   // Main Method

    private void synJSONtoSQLite() {

        //Connected http
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);

        int intTABLE = 0;
        while (intTABLE <= 1) {

            //1 Create InputStream
            InputStream inputStream = null;
            String[] urlJSONStrings = {"http://swiftcodingthai.com/atc/php_get_user_master.php",
                    "http://swiftcodingthai.com/atc/php_get_question_master.php"};

            try {

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlJSONStrings[intTABLE]);
                HttpResponse httpResponse = httpClient.execute(httpPost);
                HttpEntity httpEntity = httpResponse.getEntity();
                inputStream = httpEntity.getContent();

            } catch (Exception e) {
                Log.d("test", "Input ==> " + e.toString());
            }


            //2 Create JSON String
            String strJSON = null;
            try {

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String strLine = null;
                StringBuilder stringBuilder = new StringBuilder();

                while ((strLine = bufferedReader.readLine()) != null) {
                    stringBuilder.append(strLine);
                }
                inputStream.close();
                strJSON = stringBuilder.toString();

            } catch (Exception e) {
                Log.d("test", "strJSON ==> " + e.toString());
            }


            //3 Update to SQLite

            try {

                JSONArray jsonArray = new JSONArray(strJSON);

                for (int i = 0; i < jsonArray.length(); i++) {

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    switch (intTABLE) {
                        case 0:
                            //userTABLE

                            String strUser = jsonObject.getString(MyManage.column_User);
                            String strPassword = jsonObject.getString(MyManage.column_Password);
                            String strName = jsonObject.getString(MyManage.column_Name);

                            myManage.addValueToSQLite(strUser, strPassword, strName);

                            break;
                        case 1:
                            //questionTABLE

                            String strQuestion = jsonObject.getString(MyManage.column_Question);
                            String strChoice1 = jsonObject.getString(MyManage.column_Choice1);
                            String strChoice2 = jsonObject.getString(MyManage.column_Choice2);
                            String strChoice3 = jsonObject.getString(MyManage.column_Choice3);
                            String strChoice4 = jsonObject.getString(MyManage.column_Choice4);
                            String strAnswer = jsonObject.getString(MyManage.column_Answer);

                            myManage.addQuestion(strQuestion,
                                    strChoice1, strChoice2, strChoice3, strChoice4, strAnswer);

                            break;
                    }


                }   // for


            } catch (Exception e) {
                Log.d("test", "Update ==> " + e.toString());
            }


            intTABLE += 1;
        }   // while


    }   // synJSONtoSQLite

    private void deleteAllSQLite() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        sqLiteDatabase.delete(MyManage.user_table, null, null);
        sqLiteDatabase.delete(MyManage.question_table, null, null);
    }

    private void tester() {
        myManage.addValueToSQLite("user", "pass", "name");
        myManage.addQuestion("คำถาม", "ก", "ข", "ค", "ง", "1");
    }

    private void buttonController() {

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Receive Value From Edit Text
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //Check Space
                if (userString.equals("") || passwordString.equals("")) {
                    //Have Space
                    MyAlertDialog myAlertDialog = new MyAlertDialog();
                    myAlertDialog.myDialog(MainActivity.this,
                            "มีช่องว่าง", "กรุณากรอกทุกช่อง คะ");

                } else {
                    //No Space

                }

            }   // onClick
        });

    }   // buttonController

    private void bindWidget() {

        userEditText = (EditText) findViewById(R.id.editText);
        passwordEditText = (EditText) findViewById(R.id.editText2);
        loginButton = (Button) findViewById(R.id.button);

    }

}   // Main Class นี่คือ คลาสหลัก
