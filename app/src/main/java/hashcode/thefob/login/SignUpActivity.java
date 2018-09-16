package hashcode.thefob.login;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

import hashcode.thefob.contentActivities.MenuActivity;
import hashcode.thefob.R;
import hashcode.thefob.utility.PasswordEvaluator;

import net.sqlcipher.database.SQLiteDatabase;

import hashcode.thefob.dataRepository.SQLiteDatabaseHelper;


import static hashcode.thefob.utility.EmailSender.sendEmail;
import static hashcode.thefob.utility.PixelAdjustor.dpToPx;


public class SignUpActivity extends AppCompatActivity
{
    private final int PASSWORD_LENGTH = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form_linear);

        final EditText passwordInput = findViewById(R.id.password_input);
        final EditText passwordConfirm = findViewById(R.id.password_confirm);
        final Button button = findViewById(R.id.button);
        final TextView uppercaseIndicator = findViewById(R.id.uppercase_check);
        final TextView lowercaseIndicator = findViewById(R.id.lowercase_check);
        final TextView numberIndicator = findViewById(R.id.number_check);
        final TextView specialCharIndicator = findViewById(R.id.special_char_indicator);
        final TextView charLength = findViewById(R.id.char_length_indicator);

        passwordInput.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {
                checkAndIndicate(uppercaseIndicator, PasswordEvaluator.hasCapital(s.toString()));
                checkAndIndicate(lowercaseIndicator, PasswordEvaluator.hasSimple(s.toString()));
                checkAndIndicate(numberIndicator, PasswordEvaluator.hasDigits(s.toString()));
                checkAndIndicate(specialCharIndicator, PasswordEvaluator.hasSpecialCharacters(s.toString()));
                checkAndIndicate(charLength, PasswordEvaluator.checkLength(s.toString(), PASSWORD_LENGTH));

                if (PasswordEvaluator.checkAll(s.toString(), passwordConfirm.getText().toString(), PASSWORD_LENGTH))
                    button.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corner));
                else
                    button.setBackground(getResources().getDrawable(R.drawable.inactive_button_bg_rounded_corners));

            }

            void checkAndIndicate(TextView indicator, boolean condition)
            {
                if (condition)
                    indicator.setTextColor(getResources().getColor(R.color.correct));
                else
                    indicator.setTextColor(getResources().getColor(R.color.incorrect));
            }


        });

        passwordConfirm.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {

            }

            @Override
            public void afterTextChanged(Editable s)
            {


                if (PasswordEvaluator.checkAll(s.toString(), passwordInput.getText().toString(), PASSWORD_LENGTH))
                    button.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corner));
                else
                    button.setBackground(getResources().getDrawable(R.drawable.inactive_button_bg_rounded_corners));

            }


        });


        final ViewGroup activityRootView = (ViewGroup) ((ViewGroup) this
                .findViewById(android.R.id.content)).getChildAt(0);
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            @Override
            public void onGlobalLayout()
            {
                int heightDiff = activityRootView.getRootView().getHeight() - activityRootView.getHeight();
                TextView tv1 = findViewById(R.id.textView4);
                TextView tv2 = findViewById(R.id.textView5);
                LinearLayout masterPassword = findViewById(R.id.masterPasswordLinear);
                LinearLayout confirmPassword = findViewById(R.id.confirmPasswordLinear);
                LinearLayout passwordConstraints = findViewById(R.id.passwordContraint);
                //Button button = findViewById(R.id.button);
                LinearLayout rootView = findViewById(R.id.rootLayout);


                if (heightDiff > dpToPx(getApplicationContext(), 200))
                { // if more than 200 dp, it's probably a keyboard...

                    tv1.setVisibility(View.GONE);
                    tv2.setVisibility(View.GONE);

                    LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            0,
                            0.8f
                    );

                    LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,
                            1.8f
                    );

                    LinearLayout.LayoutParams buttonParam = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,
                            0.5f
                    );
                    rootView.setWeightSum(4.0f);
                    param2.gravity = Gravity.CENTER;
                    buttonParam.gravity = Gravity.CENTER;
                    masterPassword.setLayoutParams(param);
                    confirmPassword.setLayoutParams(param);
                    passwordConstraints.setLayoutParams(param2);
                    //passwordConstraints.setGravity(Gravity.TOP);
                    button.setLayoutParams(buttonParam);


                } else
                {
                    tv1.setVisibility(View.VISIBLE);
                    tv2.setVisibility(View.VISIBLE);
                    LinearLayout.LayoutParams param2 = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,
                            3.0f
                    );

                    LinearLayout.LayoutParams buttonParam = new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            0,
                            0.6f
                    );
                    rootView.setWeightSum(8.0f);
                    param2.gravity = Gravity.CENTER;
                    passwordConstraints.setLayoutParams(param2);
                    buttonParam.gravity = Gravity.CENTER;
                    button.setLayoutParams(buttonParam);

                }
            }
        });
    }


    public void continueButton(View view)
    {
        SQLiteDatabase db = SQLiteDatabaseHelper.connectDataBase(this, LoginActivity.PASSWORD);
        EditText passwordInput = findViewById(R.id.password_input);
        LoginActivity.PASSWORD = passwordInput.getText().toString();
        db.changePassword(LoginActivity.PASSWORD);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(SignUpActivity.this);
        View mView = getLayoutInflater().inflate(R.layout.password_recovery, null);
        final Button configurePlanB = mView.findViewById(R.id.configure_planB);
        final Button skipPlanB = mView.findViewById(R.id.skip_planB);

        configurePlanB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String message = String.format("Your Passwor is %s",LoginActivity.PASSWORD);
                String subject = "Password Recovery - The Fob";
                String recipient = "";
                sendEmail(SignUpActivity.this,message,subject,recipient);

//
            }
        });

        skipPlanB.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                nextActivity();
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    public void nextActivity()
    {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


}
