package hashcode.thefob.login;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import hashcode.thefob.utility.PasswordEvaluator;



import hashcode.thefob.R;

public class SignUpActivity extends AppCompatActivity
{
    private final int PASSWORD_LENGTH = 8;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_form_layout);

        final EditText passwordInput = findViewById(R.id.password_input);
        final EditText passwordConfirm = findViewById(R.id.password_confirm);
        final Button button = findViewById(R.id.button);
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
                TextView uppercaseIndicator = findViewById(R.id.uppercase_check);
                TextView lowercaseIndicator = findViewById(R.id.lowercase_check);
                TextView numberIndicator = findViewById(R.id.number_check);
                TextView specialCharIndicator = findViewById(R.id.special_char_indicator);
                TextView charLength = findViewById(R.id.char_length_indicator);




                checkAndIndicate(uppercaseIndicator,PasswordEvaluator.hasCapital(s.toString()));
                checkAndIndicate(lowercaseIndicator,PasswordEvaluator.hasSimple(s.toString()));
                checkAndIndicate(numberIndicator,PasswordEvaluator.hasDigits(s.toString()));
                checkAndIndicate(specialCharIndicator,PasswordEvaluator.hasSpecialCharacters(s.toString()));
                checkAndIndicate(charLength,PasswordEvaluator.checkLength(s.toString(),PASSWORD_LENGTH));

                if(PasswordEvaluator.checkAll(s.toString(),passwordConfirm.getText().toString(),PASSWORD_LENGTH))
                    button.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corner));
                else
                    button.setBackground(getResources().getDrawable(R.drawable.inactive_button_bg_rounded_corners));


            }

            void checkAndIndicate(TextView indicator, boolean condition)
            {
                if(condition)
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
                if(PasswordEvaluator.checkAll(s.toString(),passwordConfirm.getText().toString(),PASSWORD_LENGTH))
                    button.setBackground(getResources().getDrawable(R.drawable.button_bg_rounded_corner));
                else
                    button.setBackground(getResources().getDrawable(R.drawable.inactive_button_bg_rounded_corners));

            }
        });
    }


}
