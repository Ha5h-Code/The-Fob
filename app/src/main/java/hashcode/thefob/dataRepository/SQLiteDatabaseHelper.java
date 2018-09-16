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
                    "Id" + " INTEGER PRIMARY KEY," +
                    "Name Text,"  +
                    "Icon STRING" +
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

<<<<<<< HEAD

    private static void insertAccountType(SQLiteDatabase db,String name,int iconId){
        ContentValues accountTypeValues = new ContentValues();
        accountTypeValues.put("name",name);
        accountTypeValues.put("IconId",iconId);
        db.insert("AccountType",null,accountTypeValues);

    }
=======
    public void changePasswords(Context context,String oldPassword,String newPassword)
    {
        final SQLiteDatabaseHelper helper = new SQLiteDatabaseHelper(context);
        final SQLiteDatabase db = helper.getWritableDatabase(oldPassword);


        String PRAGMA_KEY = "PRAGMA key = " + oldPassword + ";";
        String PRAGMA_REKEY = "PRAGMA rekey = " + newPassword + ";";
        db.rawExecSQL(PRAGMA_KEY);
        db.rawExecSQL(PRAGMA_REKEY);
    }

    public static SQLiteDatabase connectDataBase(Context context,String password)
    {
        return SQLiteDatabaseHelper.getInstance(context).getWritableDatabase(password);

    }

>>>>>>> origin/master
}
