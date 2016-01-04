package com.example.enrico.gestioneimmagini.ImageClasses;


import android.graphics.PointF;
import android.view.MotionEvent;
import android.widget.ImageView;

import android.util.*;
import android.content.Context;


public class Image extends ImageView {
	// these matrices will be used to move and zoom image
	private Rectangle imageRect;
	PointF center;
	PrivateTouchListener myTouch;
	public Image(Context context) {

        super(context);
        imageRect= new Rectangle(this.getDrawable().getIntrinsicWidth(),this.getDrawable().getIntrinsicHeight());
	     center=new PointF(this.getDrawable().getIntrinsicWidth()/2,this.getDrawable().getIntrinsicHeight()/2);
	     myTouch=new PrivateTouchListener(imageRect, center);
	     this.setOnTouchListener(myTouch);

    }
public Image(Context context, AttributeSet attrs)
{
    super(context, attrs);
    imageRect= new Rectangle(this.getDrawable().getIntrinsicWidth(),this.getDrawable().getIntrinsicHeight());
    center=new PointF(this.getDrawable().getIntrinsicWidth()/2,this.getDrawable().getIntrinsicHeight()/2);
    myTouch=new PrivateTouchListener(imageRect, center);
    this.setOnTouchListener(myTouch);
}

/**
 * @param context
 * @param attrs
 * @param defStyle
 */
public Image(Context context, AttributeSet attrs, int defStyle)
{
    super(context, attrs, defStyle);
    imageRect= new Rectangle(this.getDrawable().getIntrinsicWidth(),this.getDrawable().getIntrinsicHeight());
    center=new PointF(this.getDrawable().getIntrinsicWidth()/2,this.getDrawable().getIntrinsicHeight()/2);
    myTouch=new PrivateTouchListener(imageRect, center);
    this.setOnTouchListener(myTouch);
}
	/*public Image(Context context,int drawable) {

	        super(context);
	        init(drawable);

	    }
	public Image(Context context, AttributeSet attrs,int drawable)
    {
        super(context, attrs);
        init(drawable);
    }

 
    public Image(Context context, AttributeSet attrs, int defStyle,int drawable)
    {
        super(context, attrs, defStyle);
        init(drawable);
    }
    private void init(int drawable)
    {
    	 this.setImageResource(drawable);
    	 imageRect= new Rectangle(this.getDrawable().getIntrinsicWidth(),this.getDrawable().getIntrinsicHeight());
	     center=new PointF(this.getDrawable().getIntrinsicWidth()/2,this.getDrawable().getIntrinsicHeight()/2);
	     this.setOnTouchListener(new PrivateTouchListener(imageRect, center));
    }*/
    public void setOnClickListener(OnClickListener listener)
    {
    	super.setOnClickListener(listener);
    }
    public void setDrawable(int drawable)
    {
    	this.setImageResource(drawable);
    }
    public boolean pointBelongToImage(MotionEvent ev)
    {
    	return myTouch.pointBelongToImage(ev);
    }
    public void setEnabled(boolean flag)
    {
    	super.setEnabled(flag);
    }
}
