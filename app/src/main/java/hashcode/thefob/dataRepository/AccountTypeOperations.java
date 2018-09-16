package hashcode.thefob.dataRepository;

import android.content.ContentValues;

import net.sqlcipher.database.SQLiteDatabase;

public class AccountTypeOperations
{
    private static void insertAccountType(SQLiteDatabase db, String name, int iconId)
    {
        ContentValues accountTypeValues = new ContentValues();
        accountTypeValues.put("name", name);
        accountTypeValues.put("IconId", iconId);
        db.insert("AccountType", null, accountTypeValues);

    }
}
