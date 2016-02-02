package it.mattiamerlini.mvc_scoprimondo.Views.TabHost.Impl;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;

import java.util.concurrent.Callable;

import it.enricobuffoli.mvc_scoprimondo.ButtonGesture.ButtonGestureView;
import it.mattiamerlini.mvc_scoprimondo.Activities.Menu.MenuActivity;
import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Base.User.User;
import it.mattiamerlini.mvc_scoprimondo.Fragments.AlertDialogFragment;
import it.mattiamerlini.mvc_scoprimondo.Fragments.AlertMessageFragment;
import it.mattiamerlini.mvc_scoprimondo.Utilities.ActivityUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.DataUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.NetworkUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.RequestUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.SessionUtility;
import it.mattiamerlini.mvc_scoprimondo.Views.TabHost.Interfaces.TabHost;
import it.mattiamerlini.mvc_scoprimondo.Views.TabHost.TabHostModel.TabHostModel;

/**
 * Created by mattia on 01/02/16.
 */
public class TabHostImpl extends TabHost
{

    private Activity activity;
    public TabHostModel model;
    private int previous;

    public TabHostImpl(Context context) {
        super(context);
    }

    public TabHostImpl(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setup(Activity activity)
    {
        this.model = new TabHostModel() {
            @Override
            public boolean isButtonGestureViewWellFormed(int tabIndex) {
                return TabHostImpl.this.isButtonGestureViewWellFormed(tabIndex);
            }
        };
        this.activity = activity;
        this.setup();
    }

    @Override
    protected boolean onBeforeTabChange(final int futureTabIndex)
    {
        final int currentTabIndex = this.getCurrentTab();
        boolean returnValue = false;

        Console.log(String.format("Current: %d, Future: %d.", currentTabIndex, futureTabIndex));

        switch(currentTabIndex)
        {
            case 0 :
                returnValue = true;
                break;
            case 1 :
                switch (futureTabIndex)
                {
                    case 0 :
                        this.backToMenu();
                        returnValue = false;
                        break;
                    case 2 :
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        this.stepForward(currentTabIndex, futureTabIndex);
                        returnValue = false;
                        break;
                    case 1 :
                    case 3 :
                    case 4 :
                    case 5 :
                    case 6 :
                        returnValue = false;
                        break;
                    default:
                        returnValue = true;
                        break;
                }
                break;
            case 2 :
                switch (futureTabIndex)
                {
                    case 0 :
                        this.backToMenu();
                        returnValue = false;
                        break;
                    case 1:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        stepBack(futureTabIndex);
                        returnValue = false;
                        break;
                    case 3 :
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        this.stepForward(currentTabIndex, futureTabIndex);
                        returnValue = false;
                        break;
                    case 2 :
                    case 4 :
                    case 5 :
                    case 6 :
                        returnValue = false;
                        break;
                    default:
                        returnValue = true;
                        break;
                }
                break;
            case 3 :
                switch (futureTabIndex)
                {
                    case 0 :
                        this.backToMenu();
                        returnValue = false;
                        break;
                    case 2:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        this.stepBack(futureTabIndex);
                        returnValue = false;
                        break;
                    case 4 :
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        this.stepForward(currentTabIndex, futureTabIndex);
                        returnValue = false;
                        break;
                    case 1:
                    case 3 :
                    case 5 :
                    case 6 :
                        returnValue = false;
                        break;
                    default:
                        returnValue = true;
                        break;
                }
                break;
            case 4 :
                switch (futureTabIndex)
                {
                    case 0 :
                        this.backToMenu();
                        returnValue = false;
                        break;
                    case 3:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        this.stepBack(futureTabIndex);
                        returnValue = false;
                        break;
                    case 5:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        this.stepForward(currentTabIndex, futureTabIndex);
                        returnValue = false;
                        break;
                    case 1:
                    case 2:
                    case 4:
                    case 6 :
                        returnValue = false;
                        break;
                    default:
                        returnValue = true;
                        break;
                }
                break;
            case 5 :
                switch (futureTabIndex)
                {
                    case 0 :
                        this.backToMenu();
                        returnValue = false;
                        break;
                    case 4:
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        this.stepBack(futureTabIndex);
                        returnValue = false;
                        break;
                    case 6 :
                        if(this.previous == futureTabIndex)
                        {
                            this.previous = 0;
                            returnValue = true;
                            break;
                        }
                        this.lastStep(currentTabIndex);
                        returnValue = false;
                        break;
                    case 1 :
                    case 2 :
                    case 3 :
                    case 5 :
                        returnValue = false;
                        break;
                    default:
                        returnValue = true;
                        break;
                }
                break;
            case 6 :
                returnValue = true;
                break;
            default:
                returnValue = true;
                break;
        }
        return returnValue;
    }

    private boolean isButtonGestureViewWellFormed(int tabIndex)
    {
        ButtonGestureView buttonGesture = null;
        boolean returnValue = false;
        switch (tabIndex)
        {
            case 1: //MADRE NATURA
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
            case 2: //MADRE TERRA
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
            case 3: //TERRA PADRI
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
            case 4: //MADRE PATRIA
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
            case 5: //TERRA FRONTIERA
                buttonGesture = this.model.getButtonGestureViewByTabIndex(tabIndex);
                Console.log(String.format("Controllo %d -> [%s]", tabIndex, buttonGesture));
                //Do checks
                returnValue = true;
                break;
        }
        return returnValue;
    }

    private void backToMenu()
    {
        ActivityUtility.showDialogMessage(this.activity, "Attenzione", "Sei sicuro di voler abbandonare?\nI dati andranno persi.", "Sì, abbandona!", "No, resta qui!", new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                ActivityUtility.changeActivity(activity.getApplicationContext(), MenuActivity.class, null);
                return null;
            }
        }, new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                return null;
            }
        });
    }

    private void stepForward(final int currentTabIndex, final int futureTabIndex)
    {
        if(this.model.isButtonGestureViewWellFormed(currentTabIndex))
        {

            ActivityUtility.showDialogMessage(this.activity, "Attenzione", "Hai fatto tutto?", "Sì, vai avanti!", "No, resta qui!", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    if(!NetworkUtility.isOnline(activity.getApplicationContext()))
                    {
                        ActivityUtility.showLongToast(activity.getApplicationContext(), NetworkUtility.ERROR_NOT_CONNECTED);
                    }
                    else
                    {
                        if(!NetworkUtility.isAPIServerReachable())
                        {
                            ActivityUtility.showLongToast(activity.getApplicationContext(), NetworkUtility.ERROR_API_NOT_CONNECTED);
                        }
                        else
                        {
                            if (TabHostImpl.this.model.saveButtonGestureView(currentTabIndex))
                            {
                                TabHostImpl.this.previous = futureTabIndex;
                                TabHostImpl.this.setCurrentTab(futureTabIndex);
                                return null;
                            }
                            else
                            {
                                ActivityUtility.showLongToast(activity.getApplicationContext(), "Impossibile salvare");
                            }
                        }
                    }
                    return null;
                }
            }, null);
        }
        else
        {
            ActivityUtility.showDialogAlert(this.activity, "Attenzione", "Per procedere devi aver messo almeno . . .", "Ok, ricontrollo!", null);
        }
    }

    private void stepBack(final int futureTabIndex)
    {
        ActivityUtility.showDialogMessage(this.activity, "Attenzione", "Sei sicuro di voler tornare indietro?", "Sì, torna indietro!", "No, resta qui!", new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                TabHostImpl.this.previous = futureTabIndex;
                TabHostImpl.this.setCurrentTab(futureTabIndex);
                return null;
            }
        }, null);
    }

    private void lastStep(final int currentTabIndex)
    {
        if(this.model.isButtonGestureViewWellFormed(currentTabIndex))
        {

            ActivityUtility.showDialogMessage(this.activity, "Attenzione", "Hai fatto tutto?\nTornerai al menù!", "Ok, salva e torna al menu!", "No, resta qui!", new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    if(!NetworkUtility.isOnline(activity.getApplicationContext()))
                    {
                        ActivityUtility.showLongToast(activity.getApplicationContext(), NetworkUtility.ERROR_NOT_CONNECTED);
                    }
                    else
                    {
                        if(!NetworkUtility.isAPIServerReachable())
                        {
                            ActivityUtility.showLongToast(activity.getApplicationContext(), NetworkUtility.ERROR_API_NOT_CONNECTED);
                        }
                        else
                        {
                            if (TabHostImpl.this.model.saveButtonGestureView(currentTabIndex))
                            {
                                ActivityUtility.changeActivity(activity.getApplicationContext(), MenuActivity.class, null);
                                return null;
                            }
                            else
                            {
                                ActivityUtility.showLongToast(activity.getApplicationContext(), "Impossibile salvare");
                            }
                        }
                    }
                    return null;
                }
            }, null);
        }
        else
        {
            ActivityUtility.showDialogAlert(this.activity, "Attenzione", "Per terminare devi aver messo almeno . . .", "Ok, ricontrollo!", null);
        }
    }
}
