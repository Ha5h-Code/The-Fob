package hashcode.thefob.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import net.sqlcipher.database.SQLiteDatabase;

import hashcode.thefob.MenuActivity;
import hashcode.thefob.dataRepository.SQLiteDatabaseHelper;

import hashcode.thefob.R;

/**
 * A login screen that offers login via password.
 */
public class LoginActivity extends AppCompatActivity
{
    public static String PASSWORD = "fob123" ;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SQLiteDatabase.loadLibs(this);

        try
        {
            SQLiteDatabase db = SQLiteDatabaseHelper.connectDataBase(this,PASSWORD);
            setContentView(R.layout.register_layout);

        }
        catch(net.sqlcipher.database.SQLiteException e)
        {
            setContentView(R.layout.login_without_finger);
        }


    }


    public void openSignupForm(View view)
    {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public void nextActivity()
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}

