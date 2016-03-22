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


    public static final String food_table = "foodTABLE";
    public static final String column_Food = "Food";
    public static final String column_Price = "Price";
    public static final String column_Source = "Source";


    public MyManage(Context context) {

        //Create & Connected SQLite
        myOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = myOpenHelper.getWritableDatabase();
        readSqLiteDatabase = myOpenHelper.getReadableDatabase();

    }   // Constructor

    public long addValueToSQLite(int intTABLE,
                                 String strFirst,
                                 String strSecond,
                                 String strThird) {

        long myLong = 0;

        switch (intTABLE) {

            case 1:
                //userTABLE
                ContentValues contentValues = new ContentValues();
                contentValues.put(column_User, strFirst);
                contentValues.put(column_Password, strSecond);
                contentValues.put(column_Name, strThird);

                myLong = writeSqLiteDatabase.insert(user_table, null, contentValues);

                break;
            case 2:
                //foodTABLE
                ContentValues contentValues1 = new ContentValues();
                contentValues1.put(column_Food, strFirst);
                contentValues1.put(column_Price, strSecond);
                contentValues1.put(column_Source, strThird);

                myLong = writeSqLiteDatabase.insert(food_table, null, contentValues1);

                break;

        }   // switch

        return myLong;
    }


}   // Main Class
