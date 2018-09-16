package hashcode.thefob.dataRepository;

import net.sqlcipher.database.SQLiteOpenHelper;
import android.content.Context;
import net.sqlcipher.database.SQLiteDatabase;

public class SQLiteDatabaseHelper extends SQLiteOpenHelper
{
    private static SQLiteDatabaseHelper instance;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "vault";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + "AccountType" + " (" +
                    "Id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Name Text,"  +
                    "iconId INTERGER" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + "AccountType";


    public SQLiteDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    static public synchronized SQLiteDatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new SQLiteDatabaseHelper(context);
        }
        return instance;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
    }


    private static void insertAccountType(SQLiteDatabase db,String name,int iconId){
        ContentValues accountTypeValues = new ContentValues();
        accountTypeValues.put("name",name);
        accountTypeValues.put("IconId",iconId);
        db.insert("AccountType",null,accountTypeValues);

    }
}
