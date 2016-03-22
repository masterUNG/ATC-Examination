package appewtc.masterung.ungrestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by masterUNG on 3/22/16 AD.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String user_table = "userTABLE";
    public static final String column_id = "_id";
    public static final String column_User = "User";
    public static final String column_Password = "Password";
    public static final String column_Name = "Name";


    public static final String question_table = "questionTABLE";
    public static final String column_Question = "Question";
    public static final String column_Choice1 = "Choice1";
    public static final String column_Choice2 = "Choice2";
    public static final String column_Choice3 = "Choice3";
    public static final String column_Choice4 = "Choice4";
    public static final String column_Answer = "Answer";


    public MyManage(Context context) {

        //Create & Connected SQLite
        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addQuestion(String strQuestion,
                            String strChoice1,
                            String strChoice2,
                            String strChoice3,
                            String strChoice4,
                            String strAnswer) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Question, strQuestion);
        contentValues.put(column_Choice1, strChoice1);
        contentValues.put(column_Choice2, strChoice2);
        contentValues.put(column_Choice3, strChoice3);
        contentValues.put(column_Choice4, strChoice4);
        contentValues.put(column_Answer, strAnswer);

        return writeSqLiteDatabase.insert(question_table, null, contentValues);
    }


    public long addValueToSQLite(String strFirst,
                                 String strSecond,
                                 String strThird) {

        long myLong = 0;

        //userTABLE
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_User, strFirst);
        contentValues.put(column_Password, strSecond);
        contentValues.put(column_Name, strThird);

        myLong = writeSqLiteDatabase.insert(user_table, null, contentValues);

        return myLong;
    }


}   // Main Class