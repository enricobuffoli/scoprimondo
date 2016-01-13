package com.example.enrico.gestioneimmagini.LayoutGestures;

/**
 * Created by enrico on 07/01/16.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.example.enrico.gestioneimmagini.ImageClasses.Image;
import java.util.ArrayList;
import java.util.Collections;


public class ImageMotionView extends RelativeLayout {

    private ArrayList <Image> images =new ArrayList<>();
    private boolean enabled=false;
    private Paint paint;
    public ImageMotionView(Context context) {
        super(context);
        init();
    }
    public ImageMotionView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init();
    }

    public ImageMotionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void init()
    {
        ShapeDrawable rectShapeDrawableBackColor = new ShapeDrawable(); // pre defined class

        ShapeDrawable rectShapeDrawable = new ShapeDrawable(); // pre defined class
        // get paint
        Paint paint = rectShapeDrawable.getPaint();
        rectShapeDrawableBackColor.getPaint().setColor(Color.WHITE);
        // set border color, stroke and stroke width
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20); // you can change the value of 5
        this.setBackground(rectShapeDrawable);

        this.setBackgroundColor(Color.WHITE);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        final int action = MotionEventCompat.getActionMasked(ev);
        // Always handle the case of the touch gesture being complete.
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            for(int i=images.size()-1;i>=0;i--)
            {
                if(images.get(i).pointBelongToImage(ev))
                {
                    if(!enabled && this.whichIsEnabled()<i)
                    {
                        images.get(i).setEnabled(true);
                        enabled=true;
                    }
                    else if(enabled && images.get(i).isEnabled())
                        images.get(i).setEnabled(false);
                }
                else if(!images.get(i).pointBelongToImage(ev) && images.get(i).isEnabled())
                    images.get(i).setEnabled(false);
            }
        }


        enabled=false;

        return false;
    }
    public void addImage(int drawable)
    {
        Image image = new Image(this.getContext(),drawable);
        addView(image);
        image.setEnabled(false);
        images.add(image);
    }
    private int whichIsEnabled()
    {
        int index=-1;
        for(int i=0;i<images.size();i++)
            if(images.get(i).isEnabled())
                index=i;
        return index;
    }
    public void toUpperLevel()
    {
        int enabledIndex=this.whichIsEnabled();
        if(enabledIndex!=images.size()-1&& enabledIndex!=-1)
        {
            Collections.swap(images, enabledIndex, enabledIndex + 1);
            for (int i = enabledIndex + 1; i < images.size(); i++)
                images.get(i).bringToFront();
        }
    }
    public void toLowerLevel()
    {
        int enabledIndex=this.whichIsEnabled();
        if(enabledIndex!=0&& enabledIndex!=-1)
        {
            Collections.swap(images, enabledIndex, enabledIndex - 1);
            for (int i = enabledIndex; i < images.size(); i++)
                images.get(i).bringToFront();
        }
    }
    public void toTopLevel()
    {
        int enabledIndex=this.whichIsEnabled();
        if(enabledIndex!=images.size()-1&& enabledIndex!=-1)
        {
            Image tempEnabled = images.get(enabledIndex);
            for(int i=enabledIndex;i<images.size()-1;i++)
            {
                Collections.swap(images,i,i+1);
            }
            tempEnabled.bringToFront();
        }
    }
    public void toBottomLevel()
    {
        int enabledIndex=this.whichIsEnabled();
        if(enabledIndex!=0&& enabledIndex!=-1)
        {
            for(int i=enabledIndex;i>0;i--)
            {
                images.get(enabledIndex-i).bringToFront();
                Collections.swap(images, i, i - 1);
            }

        }
    }
    public void deleteImage()
    {
        int enabledIndex = this.whichIsEnabled();
        if(enabledIndex!=-1)
        {
            this.removeView(images.get(enabledIndex));
            images.remove(enabledIndex);
        }
    }
    public void mirroringImage()
    {
        int enabledIndex=this.whichIsEnabled();

        if(enabledIndex!=-1)
            images.get(enabledIndex).mirroring();
    }
    public int getImagesNumber()
    {
        return images.size();
    }
}