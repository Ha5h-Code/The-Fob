package hashcode.thefob.utility;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class NavigationItemHandler
{
    private Intent intent;

    public void handleMyfob(Context context)
    {

    }

    public void handleFavourites(Context context)
    {

    }

    public void handleCreate(Context context)
    {

    }

    public void handleSettings(Context context)
    {

    }

    public void handleHelp(Context context)
    {
        String url = "https://github.com/Ha5h-Code/The-Fob";
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        context.startActivity(intent);
    }

    public void handleAboutUs(Context context)
    {

    }
}
