package hashcode.thefob.dataRepository;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;

import net.sqlcipher.Cursor;
import net.sqlcipher.database.SQLiteDatabase;
import net.sqlcipher.database.SQLiteException;

import java.util.ArrayList;

import hashcode.thefob.login.LoginActivity;



public class AccountTypeOperations {



    //method use to insert a new Account type
    public static void insertAccountType(Context context,SQLiteDatabase db, String name, int iconId)
    {

        try
            {
        ContentValues accountTypeValues = new ContentValues();
        accountTypeValues.put("Name", name);
        accountTypeValues.put("IconId", iconId);
        db.insert("AccountType", null, accountTypeValues);
            }

    catch (SQLiteException e)
    {

        Toast toast = Toast.makeText(context, "DataBase Unavailable", Toast.LENGTH_SHORT);
        toast.show();
    }
    }




    //method use to delete an Account Type
    public static void deleteAccountType(Context context,SQLiteDatabase db,int id)
    {
        try
        {
        db.delete("AccountType",
                "Id=?",
                new String[]{Integer.toString(id)});
        }
        catch (SQLiteException e)
        {

        Toast toast = Toast.makeText(context, "DataBase Unavailable", Toast.LENGTH_SHORT);
        toast.show();
        }

    }



     //method to update an Account Type icon
    public static void updateIcon(Context context,SQLiteDatabase db,int id,int newIconId){
      try
      {
        ContentValues newIconIdContent=new ContentValues();
        newIconIdContent.put("ID","newIconId");

        db.update("AccountType",
                  newIconIdContent,
                 "ID = ?",
                  new String[] {Integer.toString(id)});

      }
    catch (SQLiteException e)
    {

        Toast toast = Toast.makeText(context, "DataBase Unavailable", Toast.LENGTH_SHORT);
        toast.show();

    }

    }


    //method to update an Account Type Name
    public static void updateAccountName(Context context,SQLiteDatabase db,int id, String newAccountName){
        try
        {
            ContentValues newAccountNameContent=new ContentValues();
            newAccountNameContent.put("Name","newAccountName");

            db.update("AccountType",
                    newAccountNameContent,
                    "Name = ?",
                    new String[] {newAccountName});

        }
        catch (SQLiteException e)
        {

            Toast toast = Toast.makeText(context, "DataBase Unavailable", Toast.LENGTH_SHORT);
            toast.show();

        }

    }



    public static Cursor createCursor(Context context, int cusorId) {

        //to convert the integer cursor id as a String value
        String strCursorId = Integer.toString(cusorId);

        Cursor cursor=null;
        try {
            SQLiteDatabase db = SQLiteDatabaseHelper.connectDataBase(context, LoginActivity.PASSWORD);

                    cursor = db.query("AccountType",
                    new String[]{ "Name", "IconId"},
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



    //method to get all ids

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



//method use to get AccountType Name by giving the id
    public static String getAccountTypeName(Context context ,int cursorId){
        String accountTypeName=null;
        Cursor cursor=createCursor(context,cursorId);
        if(cursor.moveToFirst()){
            accountTypeName= cursor.getString(0);
        }
        return accountTypeName;


    }




    //method use to get IconId  by giving the cursorId
    public static String getIconId(Context context ,int cursorId){
        String iconId=null;
        Cursor cursor=createCursor(context,cursorId);
        if(cursor.moveToFirst()){
            iconId= cursor.getString(1);
        }
        return iconId;


    }

}
