package com.example.enrico.mvc_scoprimondo.ImageMotion;

/**
 * Created by enrico on 07/01/16.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;


public class ImageMotionView extends RelativeLayout {

    private ImageMotionController imageMotionController;
    public ImageMotionView(Context context)
    {
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

        try {
            final int action = MotionEventCompat.getActionMasked(ev);
            // Always handle the case of the touch gesture being complete.
            if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN)
                this.imageMotionController.eventDown(ev);
        }
        catch (Exception exp)
        {
            //(new PopupWindow(this.getContext()));
        }
        return false;
    }
    public void createController(ImageMotionModel imageMotionModel)
    {
        imageMotionController = new ImageMotionController(imageMotionModel,this);

    }

}