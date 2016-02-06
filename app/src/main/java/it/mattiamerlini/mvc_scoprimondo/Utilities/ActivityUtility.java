package it.mattiamerlini.mvc_scoprimondo.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;

import it.mattiamerlini.mvc_scoprimondo.Activities.Menu.MenuActivity;
import it.mattiamerlini.mvc_scoprimondo.Fragments.AlertDialogFragment;
import it.mattiamerlini.mvc_scoprimondo.Fragments.AlertMessageFragment;
import it.mattiamerlini.mvc_scoprimondo.Fragments.ViewDialogFragment;
import it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner.ImageSpinnerItem;
import it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner.ImageSpinnerView;

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

    public static void showDialogMessage(final Activity activity, String title, String message, String positiveButton, String negativeButton, final Callable<Void> positive, final Callable<Void> negative)
    {
        AlertDialogFragment dialog = new AlertDialogFragment(title, message, positiveButton, negativeButton) {
            @Override
            protected void onNegativeClick(DialogInterface dialog, int which) {
                try
                {
                    if(negative != null)
                        negative.call();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onPositiveClick(DialogInterface dialog, int which)
            {
                try
                {
                    if(positive != null)
                        positive.call();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        dialog.show(activity.getFragmentManager(), "alert");
    }

    public static void showDialogAlert(final Activity activity, String title, String message, String neutralButton, final Callable<Void> neutral)
    {
        AlertMessageFragment msg = new AlertMessageFragment(title, message, neutralButton) {
            @Override
            protected void onNeutralClick(DialogInterface dialog, int which) {
                try
                {
                    if(neutral != null)
                        neutral.call();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        msg.show(activity.getFragmentManager(), "message");
    }

    public static void showViewDialog(Activity activity, ImageSpinnerView v, String positiveButton, String negativeButton, final Callable<Void> positive, final Callable<Void> negative)
    {
        ViewDialogFragment spinnerDialog = new ViewDialogFragment(v, positiveButton, negativeButton) {
            @Override
            protected void onNegativeClick(DialogInterface dialog, int which) {
                try
                {
                    if(negative != null)
                        negative.call();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            protected void onPositiveClick(DialogInterface dialog, int which) {
                try
                {
                    if(positive != null)
                        positive.call();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        };
        spinnerDialog.show(activity.getFragmentManager(), "ViewDialog");
    }
}
