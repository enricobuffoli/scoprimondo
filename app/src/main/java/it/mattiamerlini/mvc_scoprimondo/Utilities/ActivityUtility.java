package it.mattiamerlini.mvc_scoprimondo.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.widget.Toast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import it.mattiamerlini.mvc_scoprimondo.Activities.Menu.MenuActivity;

/**
 * Created by mattia on 27/01/16.
 */
public class ActivityUtility
{
    public static void changeActivity(Context context, Class newActivity, Map<String, String> messages)
    {
        Intent intent = new Intent(context, newActivity);

        if(messages != null)
        {
            for(Entry<String, String> entry : messages.entrySet())
                intent.putExtra(entry.getKey(), entry.getValue());
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static Map<String, String> retrieveIntentMessages(Activity activity, String [] keys)
    {
        Intent intent = activity.getIntent();

        Map<String, String> out = new HashMap<>();

        for(String key : keys)
        {
            if((intent.getStringExtra(key)) != null)
                out.put(key, intent.getStringExtra(key));
        }

        return out;
    }

    public static void showLongToast(Context context, String text)
    {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static boolean isTablet(Context context)
    {
        return (context.getApplicationContext().getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static void setProperOrientation(Activity activity)
    {
        if(ActivityUtility.isTablet(activity.getApplicationContext()))
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        else
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}
