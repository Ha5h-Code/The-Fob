package hashcode.thefob.dataRepository;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;

import java.util.ArrayList;

import hashcode.thefob.login.LoginActivity;

import static javax.xml.ws.soap.AddressingFeature.ID;

public class AccountTypeOperations {
    public static void insertAccountType(SQLiteDatabase db, String name, int iconId)
    {
        ContentValues accountTypeValues = new ContentValues();
        accountTypeValues.put("name", name);
        accountTypeValues.put("IconId", iconId);
        db.insert("AccountType", null, accountTypeValues);

    }

    public static void deleteAccountType(int id)
    {

    }
    public Cursor createCursor(Context context, int cusorId) {

        //to convert the integer cursor id as a String value
        String strCursorId = Integer.toString(cusorId);

        Cursor cursor=null;
        try {
            SQLiteDatabase db = SQLiteDatabaseHelper.connectDataBase(context, LoginActivity.PASSWORD);

                    cursor = db.query("AccountType",
                    new String[]{"ID", "Name", "Icon"},
                    "ID=?",
                    new String[]{strCursorId},
                    null,
                    null,
                    null);


        } catch (SQLiteException e) {

            Toast toast = Toast.makeText(context, "DataBase Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        return cursor;



    }


    public static ArrayList<Integer> getAllId(Context context){


       Cursor cursor;
       ArrayList<Integer> idArrayList=new ArrayList<Integer>();
        try {
            SQLiteDatabase db = SQLiteDatabaseHelper.connectDataBase(context, LoginActivity.PASSWORD);

            cursor = db.query("AccountType",
                    new String[]{"ID"},
                    null,
                  null,
                    null,
                    null,
                    null);


             idArrayList=new ArrayList<Integer>();

                while (cursor.moveToNext()){

                    idArrayList.add(cursor.getInt(0));
            }





        } catch (SQLiteException e) {

            Toast toast = Toast.makeText(context, "DataBase Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        return  idArrayList;


    }

}
