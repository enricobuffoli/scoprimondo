package it.mattiamerlini.mvc_scoprimondo.Views.Interfaces;

import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by mattia on 01/02/16.
 */
public abstract class TabHost extends android.widget.TabHost
{
    public TabHost(Context context)
    {
        super(context);
    }

    public TabHost(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    public void setCurrentTab(int index)
    {
        if(this.onBeforeTabChange(index))
            super.setCurrentTab(index);
    }

    protected abstract boolean onBeforeTabChange(int futureTabIndex);
}
