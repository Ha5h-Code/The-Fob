package hashcode.thefob.utility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import hashcode.thefob.R;
import hashcode.thefob.contentActivities.MenuActivity;
import hashcode.thefob.settings.SettingsActivity;


public class NavigationItemHandler
{
    private Intent intent;
    private  Context context;
    private String gitUrl = "https://github.com/Ha5h-Code/The-Fob";
    private String emailAddress = "nipunsampath@gmail.com";

    public NavigationItemHandler(Context context)
    {
        this.context = context;
    }

    public void handleMyfob()
    {
        intent = new Intent(context, MenuActivity.class);
    }

    public void handleFavourites()
    {

    }

    public void handleCreate()
    {

    }

    public void handleSettings()
    {
        intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    public void handleHelp()
    {

        openUrl(gitUrl);

    }

    public void handleAboutUs()
    {
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(R.layout.about_us_alert_box, null);
        final ImageView github = mView.findViewById(R.id.github_about);
        final ImageView email = mView.findViewById(R.id.mail_about);

        github.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openUrl(gitUrl);


            }
        });

        email.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EmailSender.sendEmail(context,"","About The Fob",emailAddress);
            }
        });
        mBuilder.setView(mView);
        AlertDialog dialog = mBuilder.create();
        dialog.show();
    }

    private void openUrl(String url)
    {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }
}
