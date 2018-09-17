package hashcode.thefob.utility;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import java.util.Locale;

public class LocaleUtility
{
    private static Locale locale;

    public static void setLocale(Locale localeIn)
    {
        locale = localeIn;
        if (locale != null)
        {
            Locale.setDefault(locale);
        }
    }

    public static void setConfigChange(Context context)
    {
        if (locale != null)
        {
            Locale.setDefault(locale);

            Configuration configuration = context.getResources().getConfiguration();
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            configuration.locale = locale;

            context.getResources().updateConfiguration(configuration, displayMetrics);
        }
    }

}
