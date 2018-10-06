package hashcode.thefob.utility;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import java.util.Locale;

public class LocaleUtility
{

    public static void setLocale(Context context, String language)
    {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());

        //save data to shared preference
        SharedPreferences.Editor editor = context.getSharedPreferences("Settings", Context.MODE_PRIVATE).edit();
        editor.putString("AppLang", language);

        editor.apply();

    }

    public static void loadLocale(Context context)
    {
        SharedPreferences prefs = context.getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("AppLang", "");
        setLocale(context, language);

    }

}
