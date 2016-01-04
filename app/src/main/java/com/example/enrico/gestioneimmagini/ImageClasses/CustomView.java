package com.example.enrico.gestioneimmagini.ImageClasses;

import java.util.ArrayList;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

public class CustomView extends ViewGroup {
	
	private int mTouchSlop;
	private ArrayList<Image> images =new ArrayList<Image>();
    public CustomView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
    public CustomView(Context context, AttributeSet attrs, int defStyle)
    {
    	super(context,attrs,defStyle);
    }
    public CustomView(Context context, AttributeSet attrs)
    {
    	super(context,attrs);
    }



    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        /*
         * This method JUST determines whether we want to intercept the motion.
         * If we return true, onTouchEvent will be called and we do the actual
         * scrolling there.
         */


        final int action = MotionEventCompat.getActionMasked(ev);

        // Always handle the case of the touch gesture being complete.
        if (action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_UP) {
                   for(int i=0;i<images.size();i++)
                   {
                	   if(images.get(i).pointBelongToImage(ev))
                	   {
                		   images.get(i).setEnabled(true);
                	   }
                	   else 
                		   images.get(i).setEnabled(false);
                   }
        }
        
        

       
        return true;
    }
    public void addImage(Image image)
    {
       addView(image);
    }

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}

   
}