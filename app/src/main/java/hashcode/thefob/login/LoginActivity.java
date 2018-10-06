package hashcode.thefob.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

import net.sqlcipher.database.SQLiteDatabase;

import hashcode.thefob.R;
import hashcode.thefob.contentActivities.MenuActivity;
import hashcode.thefob.dataRepository.SQLiteDatabaseHelper;
import hashcode.thefob.utility.LocaleUtility;

/**
 * A login screen that offers login via password.
 */
public class LoginActivity extends AppCompatActivity
{
    public static String PASSWORD = "";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        SQLiteDatabase.loadLibs(this);
        //LocaleUtility.loadLocale(this);

        try
        {
            String tempPassword = "fob123";
            SQLiteDatabase db = SQLiteDatabaseHelper.connectDataBase(this, tempPassword);
            setContentView(R.layout.register_layout);

            final ImageButton languageSelecter = findViewById(R.id.button2);


            languageSelecter.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    showLanguageChange();

                }
            });


        } catch (net.sqlcipher.database.SQLiteException e)
        {
            setContentView(R.layout.login_without_finger);
        }


    }

    private void showLanguageChange()
    {

        final ImageButton languageSelecter = findViewById(R.id.button2);
        final String[] language = {"English", "Sinhala"};
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(LoginActivity.this);
        mbuilder.setTitle("Change Language");
        mbuilder.setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Drawable icon;
                if (which == 0)
                {
                    LocaleUtility.setLocale(LoginActivity.this, "en");
                    icon = getDrawable(R.drawable.ic_flag_of_the_united_states);
                    languageSelecter.setBackground(icon);
                    recreate();

                } else if (which == 1)
                {

                    LocaleUtility.setLocale(LoginActivity.this, "si-rLK");
                    icon = getDrawable(R.drawable.ic_flag_of_sri_lanka);
                    languageSelecter.setBackground(icon);
                    recreate();


                }
                dialog.dismiss();

            }
        });

        AlertDialog dialog = mbuilder.create();
        dialog.show();
    }


    public void login(View v)
    {
        TextView passwordInput = findViewById(R.id.password_input_login);
        PASSWORD = passwordInput.getText().toString();
        try
        {
            SQLiteDatabase db = SQLiteDatabaseHelper.connectDataBase(this, PASSWORD);
            nextActivity();

        } catch (net.sqlcipher.database.SQLiteException e)
        {
            View contextView = findViewById(R.id.rootView);
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
            Drawable incorrectBackground = getDrawable(R.drawable.incorrect_edit_text);
            passwordInput.setBackground(incorrectBackground);
            passwordInput.startAnimation(shake);
            Snackbar snackbar = Snackbar.make(contextView, R.string.login_error, Snackbar.LENGTH_SHORT);
            snackbar.getView().setBackgroundColor(Color.parseColor("#ff0000"));
            TextView snackbarTextView = snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            snackbarTextView.setTextSize(16);
            snackbar.show();
        }
    }

    public void openSignupForm(View view)
    {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

    public void nextActivity()
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}

