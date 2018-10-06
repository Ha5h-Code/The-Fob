package hashcode.thefob.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import hashcode.thefob.R;
import hashcode.thefob.utility.NavigationItemHandler;

public class AboutUsInSettings extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        NavigationItemHandler handler = new NavigationItemHandler(this);
        handler.handleAboutUs();

    }
}
