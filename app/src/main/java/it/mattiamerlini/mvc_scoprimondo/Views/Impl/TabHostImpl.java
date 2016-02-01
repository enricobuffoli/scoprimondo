package it.mattiamerlini.mvc_scoprimondo.Views.Impl;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;

import it.mattiamerlini.mvc_scoprimondo.Activities.Menu.MenuActivity;
import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Fragments.AlertDialogFragment;
import it.mattiamerlini.mvc_scoprimondo.Fragments.AlertMessageFragment;
import it.mattiamerlini.mvc_scoprimondo.Utilities.ActivityUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.DataUtility;
import it.mattiamerlini.mvc_scoprimondo.Views.Interfaces.TabHost;

/**
 * Created by mattia on 01/02/16.
 */
public class TabHostImpl extends TabHost
{

    private Activity activity;
    private int previous;

    public TabHostImpl(Context context) {
        super(context);
    }

    public TabHostImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setup(Activity activity)
    {
        this.activity = activity;
        this.setup();
        this.previous = this.getCurrentTab();
    }

    @Override
    protected boolean onBeforeTabChange(final int futureTabIndex)
    {
        int currentTabIndex = this.getCurrentTab();
        final boolean[] returnValue = {false};

        Console.log(String.format("Current: %d, Future: %d.", currentTabIndex, futureTabIndex));

        switch(currentTabIndex)
        {
            case 0 :
                returnValue[0] = true;
                break;
            case 1 :
                switch (futureTabIndex)
                {
                    case 0 :

                            AlertDialogFragment dialog = new AlertDialogFragment("Attenzione", "Sei sicuro di voler abbandonare?\nI dati andranno persi.", "Sì, abbandona!", "No, resta qui!") {
                                @Override
                                protected void onNegativeClick(DialogInterface dialog, int which) {

                                }

                                @Override
                                protected void onPositiveClick(DialogInterface dialog, int which) {
                                    ActivityUtility.changeActivity(activity.getApplicationContext(), MenuActivity.class, null);
                                }
                            };
                            dialog.show(activity.getFragmentManager(), "alert");

                        returnValue[0] = false;
                        break;
                    case 2 :
                        if(DataUtility.isImageWellFormed(true)) {
                            if(this.previous == futureTabIndex)
                            {
                                this.previous = 0;
                                returnValue[0] = true;
                                break;
                            }
                            AlertDialogFragment dialog1 = new AlertDialogFragment("Attenzione", "Hai fatto tutto?", "Sì, vai avanti!", "No, resta qui!") {
                                @Override
                                protected void onNegativeClick(DialogInterface dialog, int which)
                                {

                                }

                                @Override
                                protected void onPositiveClick(DialogInterface dialog, int which)
                                {
                                    TabHostImpl.this.previous = futureTabIndex;
                                    TabHostImpl.this.setCurrentTab(futureTabIndex);
                                }
                            };
                            dialog1.show(activity.getFragmentManager(), "alert");
                        }
                        else
                        {
                            AlertMessageFragment msg = new AlertMessageFragment("Attenzione", "Per procedere devi aver messo almeno . . .", "Ok, ricontrollo!") {
                                @Override
                                protected void onNeutralClick(DialogInterface dialog, int which) {

                                }
                            };
                            msg.show(activity.getFragmentManager(), "message");
                        }
                        returnValue[0] = false;
                        break;
                    case 1 :
                    case 3 :
                    case 4 :
                    case 5 :
                        returnValue[0] = false;
                        break;
                    default:
                        returnValue[0] = true;
                        break;
                }
                break;
            case 2 :
                switch (futureTabIndex)
                {
                    case 0:
                        AlertDialogFragment dialog2 = new AlertDialogFragment("Attenzione", "Sei sicuro di voler abbandonare?\nI dati andranno persi.", "Sì, abbandona!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which) {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which) {
                                ActivityUtility.changeActivity(activity.getApplicationContext(), MenuActivity.class, null);
                            }
                        };
                        dialog2.show(activity.getFragmentManager(), "alert");
                        returnValue[0] = false;
                        break;
                    case 1:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue[0] = true;
                            break;
                        }
                        AlertDialogFragment dialog4 = new AlertDialogFragment("Attenzione", "Sei sicuro di voler tornare indietro?", "Sì, torna indietro!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which)
                            {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which)
                            {
                                TabHostImpl.this.previous = futureTabIndex;
                                TabHostImpl.this.setCurrentTab(futureTabIndex);
                            }
                        };
                        dialog4.show(activity.getFragmentManager(), "alert");

                        returnValue[0] = false;
                        break;
                    case 3 :
                        if(DataUtility.isImageWellFormed(false)) {
                            if(this.previous == futureTabIndex)
                            {
                                this.previous = 0;
                                returnValue[0] = true;
                                break;
                            }
                            AlertDialogFragment dialog3 = new AlertDialogFragment("Attenzione", "Hai fatto tutto?", "Sì, vai avanti!", "No, resta qui!") {
                                @Override
                                protected void onNegativeClick(DialogInterface dialog, int which)
                                {

                                }

                                @Override
                                protected void onPositiveClick(DialogInterface dialog, int which)
                                {
                                    TabHostImpl.this.previous = futureTabIndex;
                                    TabHostImpl.this.setCurrentTab(futureTabIndex);
                                }
                            };
                            dialog3.show(activity.getFragmentManager(), "alert");
                        }
                        else
                        {
                            AlertMessageFragment msg = new AlertMessageFragment("Attenzione", "Per procedere devi aver messo almeno . . .", "Ok, ricontrollo!") {
                                @Override
                                protected void onNeutralClick(DialogInterface dialog, int which) {

                                }
                            };
                            msg.show(activity.getFragmentManager(), "message");
                        }
                        returnValue[0] = false;
                        break;
                    case 2 :
                    case 4 :
                    case 5 :
                        returnValue[0] = false;
                        break;
                    default:
                        returnValue[0] = true;
                        break;
                }
                break;
            case 3 :
                switch (futureTabIndex)
                {
                    case 0:
                        AlertDialogFragment dialog5 = new AlertDialogFragment("Attenzione", "Sei sicuro di voler abbandonare?\nI dati andranno persi.", "Sì, abbandona!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which) {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which) {
                                ActivityUtility.changeActivity(activity.getApplicationContext(), MenuActivity.class, null);
                            }
                        };
                        dialog5.show(activity.getFragmentManager(), "alert");
                        returnValue[0] = false;
                        break;
                    case 2:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue[0] = true;
                            break;
                        }
                        AlertDialogFragment dialog6 = new AlertDialogFragment("Attenzione", "Sei sicuro di voler tornare indietro?", "Sì, torna indietro!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which)
                            {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which)
                            {
                                TabHostImpl.this.previous = futureTabIndex;
                                TabHostImpl.this.setCurrentTab(futureTabIndex);
                            }
                        };
                        dialog6.show(activity.getFragmentManager(), "alert");
                        returnValue[0] = false;
                        break;
                    case 4 :
                        if(DataUtility.isImageWellFormed(true)) {
                            if(this.previous == futureTabIndex)
                            {
                                this.previous = 0;
                                returnValue[0] = true;
                                break;
                            }
                            AlertDialogFragment dialog7 = new AlertDialogFragment("Attenzione", "Hai fatto tutto?", "Sì, vai avanti!", "No, resta qui!") {
                                @Override
                                protected void onNegativeClick(DialogInterface dialog, int which)
                                {

                                }

                                @Override
                                protected void onPositiveClick(DialogInterface dialog, int which)
                                {
                                    TabHostImpl.this.previous = futureTabIndex;
                                    TabHostImpl.this.setCurrentTab(futureTabIndex);
                                }
                            };
                            dialog7.show(activity.getFragmentManager(), "alert");
                        }
                        else
                        {
                            AlertMessageFragment msg = new AlertMessageFragment("Attenzione", "Per procedere devi aver messo almeno . . .", "Ok, ricontrollo!") {
                                @Override
                                protected void onNeutralClick(DialogInterface dialog, int which) {

                                }
                            };
                            msg.show(activity.getFragmentManager(), "message");
                        }
                        returnValue[0] = false;
                        break;
                    case 1:
                    case 3 :
                    case 5 :
                        returnValue[0] = false;
                        break;
                    default:
                        returnValue[0] = true;
                        break;
                }
                break;
            case 4 :
                switch (futureTabIndex)
                {
                    case 0:
                        AlertDialogFragment dialog8 = new AlertDialogFragment("Attenzione", "Sei sicuro di voler abbandonare?\nI dati andranno persi.", "Sì, abbandona!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which) {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which) {
                                ActivityUtility.changeActivity(activity.getApplicationContext(), MenuActivity.class, null);
                            }
                        };
                        dialog8.show(activity.getFragmentManager(), "alert");
                        returnValue[0] = false;
                        break;
                    case 3:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue[0] = true;
                            break;
                        }
                        AlertDialogFragment dialog9 = new AlertDialogFragment("Attenzione", "Sei sicuro di voler tornare indietro?", "Sì, torna indietro!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which)
                            {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which)
                            {
                                TabHostImpl.this.previous = futureTabIndex;
                                TabHostImpl.this.setCurrentTab(futureTabIndex);
                            }
                        };
                        dialog9.show(activity.getFragmentManager(), "alert");
                        returnValue[0] = false;
                        break;
                    case 5:
                        if(DataUtility.isImageWellFormed(true)) {
                            if(this.previous == futureTabIndex)
                            {
                                this.previous = 0;
                                returnValue[0] = true;
                                break;
                            }
                            AlertDialogFragment dialog10 = new AlertDialogFragment("Attenzione", "Hai fatto tutto?", "Sì, vai avanti!", "No, resta qui!") {
                                @Override
                                protected void onNegativeClick(DialogInterface dialog, int which)
                                {

                                }

                                @Override
                                protected void onPositiveClick(DialogInterface dialog, int which)
                                {
                                    TabHostImpl.this.previous = futureTabIndex;
                                    TabHostImpl.this.setCurrentTab(futureTabIndex);
                                }
                            };
                            dialog10.show(activity.getFragmentManager(), "alert");
                        }
                        else
                        {
                            AlertMessageFragment msg = new AlertMessageFragment("Attenzione", "Per procedere devi aver messo almeno . . .", "Ok, ricontrollo!") {
                                @Override
                                protected void onNeutralClick(DialogInterface dialog, int which) {

                                }
                            };
                            msg.show(activity.getFragmentManager(), "message");
                        }
                        returnValue[0] = false;
                        break;
                    case 1:
                    case 2:
                    case 4:
                        returnValue[0] = false;
                        break;
                    default:
                        returnValue[0] = true;
                        break;
                }
                break;
            case 5 :
                switch (futureTabIndex)
                {
                    case 0:
                        AlertDialogFragment dialog11 = new AlertDialogFragment("Attenzione", "Sei sicuro di voler abbandonare?\nI dati andranno persi.", "Sì, abbandona!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which) {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which) {
                                ActivityUtility.changeActivity(activity.getApplicationContext(), MenuActivity.class, null);
                            }
                        };
                        dialog11.show(activity.getFragmentManager(), "alert");
                        returnValue[0] = false;
                        break;
                    case 4:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue[0] = true;
                            break;
                        }
                        AlertDialogFragment dialog12 = new AlertDialogFragment("Attenzione", "Sei sicuro di voler tornare indietro?", "Sì, torna indietro!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which)
                            {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which)
                            {
                                TabHostImpl.this.previous = futureTabIndex;
                                TabHostImpl.this.setCurrentTab(futureTabIndex);
                            }
                        };
                        dialog12.show(activity.getFragmentManager(), "alert");
                        returnValue[0] = false;
                        break;
                    case 1:
                    case 2:
                    case 3:
                    case 5:
                        returnValue[0] = false;
                        break;
                    default:
                        returnValue[0] = true;
                        break;
                }
                break;
            default:
                returnValue[0] = true;
                break;
        }
        return returnValue[0];
    }
}
