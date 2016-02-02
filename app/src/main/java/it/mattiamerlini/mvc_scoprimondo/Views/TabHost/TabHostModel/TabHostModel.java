package it.mattiamerlini.mvc_scoprimondo.Views.TabHost.TabHostModel;

import java.util.ArrayList;

import it.enricobuffoli.mvc_scoprimondo.ButtonGesture.ButtonGestureView;
import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Base.Model.Model;
import it.mattiamerlini.mvc_scoprimondo.Utilities.RequestUtility;

/**
 * Created by mattia on 02/02/16.
 */
public abstract class TabHostModel extends Model
{
    private ArrayList<ButtonGestureView> buttonGestureViews;

    public TabHostModel()
    {
        this.buttonGestureViews = new ArrayList<>();
    }

    public void addButtonGestureViewByTabIndex(ButtonGestureView buttonGestureView, int tabIndex)
    {
        try
        {
            this.buttonGestureViews.add(tabIndex, buttonGestureView);
            this.fireValuesChange();
        }
        catch (Exception e)
        {
            Console.log("ADD " + e.getMessage());
        }
    }

    public ButtonGestureView getButtonGestureViewByTabIndex(int tabIndex)
    {
        try
        {
            return this.buttonGestureViews.get(tabIndex);
        }
        catch (Exception e)
        {
            Console.log("GET " + e.getMessage());
            return null;
        }
    }

    public ArrayList<ButtonGestureView> getButtonGestureViews()
    {
        return this.buttonGestureViews;
    }

    public abstract boolean isButtonGestureViewWellFormed(int tabIndex);

    public boolean saveButtonGestureView(int tabIndex)
    {
        return RequestUtility.requestButtonGestureViewSave(this.getButtonGestureViewByTabIndex(tabIndex), tabIndex);
    }
}
