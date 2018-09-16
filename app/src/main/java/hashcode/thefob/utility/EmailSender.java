package hashcode.thefob.utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import hashcode.thefob.login.SignUpActivity;


public class EmailSender
{
    public static boolean sendEmail(Context context, String message)
    {
        String recepientEmail = "";
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + recepientEmail));
        //emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, "nipunsampath@gmail.com");
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Password Recovery - The Fob");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Your Password is " + message);

        try
        {
            context.startActivity(emailIntent);
            return true;
        }
        catch(android.content.ActivityNotFoundException ex) {
            Toast.makeText(context, "There is no email client installed.", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}
