package com.example.enrico.mvc_scoprimondo.ButtonGesture;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.enrico.mvc_scoprimondo.ImageClasses.Image;
import com.example.enrico.mvc_scoprimondo.ImageMotion.ImageMotionModel;
import com.example.enrico.mvc_scoprimondo.ImageMotion.ImageMotionView;
import com.example.enrico.mvc_scoprimondo.R;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by enrico on 07/01/16.
 */

public class ButtonGestureView extends FrameLayout implements Observer {

    private ArrayList<ImageButton> buttons = new ArrayList<>();
    private static final int []buttonDrawables= {R.mipmap.function_icons_up, R.mipmap.function_icons_down, R.mipmap.function_icons_top, R.mipmap.function_icons_bottom, R.mipmap.function_icons_delete, R.mipmap.specchia};
    private static final int []buttonBackgroundDrawables={R.mipmap.active, R.mipmap.inactive, R.mipmap.cliccked};
    private static final int active=0,inactive=1,cliccked=2;
    private static final int x_delay=100,y_delay=100;
    private final OnClickListener []buttonListeners={new onUpListener(), new onDownListener(), new onTopListener(), new onBottomListener(), new onDeleteListener(), new onMirroringListener()};
    private ButtonGestureModel buttonGestureModel;
    private ImageMotionModel imageMotionModel;
    private ImageMotionView imageMotionView;
    public ButtonGestureView(Context context) {
        this(context, null, 0);
    }

    public ButtonGestureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ButtonGestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init() {
        this.setBackgroundColor(Color.rgb(135,206,235));
        buttonGestureModel = new ButtonGestureModel();
        imageMotionModel = new ImageMotionModel();
        imageMotionView = (ImageMotionView) findViewById(R.id.motion_view);
        imageMotionView.createController(imageMotionModel);
        this.imageMotionModel.addObserver(this);
        initButton();
        System.out.println("ciao");
        this.addImage(R.mipmap.ciao);
        this.addImage(R.mipmap.ciao2);
    }

    private void initButton() {
        for (int i = 0; i < 6; i++) {
            ImageButton temp= new ImageButton(this.getContext());
            temp.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            temp.setY(y_delay);
            temp.setX(x_delay * i + x_delay);
            temp.setOnTouchListener(new onButtonTouch());
            temp.setImageResource(buttonDrawables[i]);
            temp.setOnClickListener(buttonListeners[i]);
            this.buttonGestureModel.addButton(temp);
            this.addView(temp);
        }
        this.deactiveAllButtons();
    }

    public void deactiveLevelButtons()
    {
        for(ImageButton b: buttonGestureModel.getLevelButtons())
        {
            b.setEnabled(false);
            b.setClickable(false);
            b.setBackgroundResource(buttonBackgroundDrawables[inactive]);
        }
    }
    public void deactiveAllButtons()
    {
        for(ImageButton b: buttonGestureModel.getButtons())
        {
            b.setEnabled(false);
            b.setClickable(false);
            b.setBackgroundResource(buttonBackgroundDrawables[inactive]);
        }
    }
    public void activeAllButtons()
    {
        for(ImageButton b: buttonGestureModel.getButtons())
        {
            b.setEnabled(true);
            b.setClickable(true);
            b.setBackgroundResource(buttonBackgroundDrawables[active]);
        }
    }
    public void addImage(int drawable)
    {
        Image temp= new Image(imageMotionView.getContext(),drawable);
        imageMotionModel.addImage(temp);
    }
    @Override
    public void update(Observable observable, Object data) {
        this.imageMotionModel = (ImageMotionModel) observable;
          if(imageMotionModel.getImageArray().size()==0)
              deactiveAllButtons();
          else if(imageMotionModel.getImageArray().size()==1)
          {
              activeAllButtons();
              deactiveLevelButtons();
          }
          else
              activeAllButtons();
    }

        private class onUpListener implements OnClickListener {
            @Override
            public void onClick(View v) {
                imageMotionModel.setImageState(imageMotionModel.up);
            }
        }

        private class onDownListener implements OnClickListener {
            @Override
            public void onClick(View v) {
                imageMotionModel.setImageState(imageMotionModel.low);
            }
        }
        private class onTopListener implements OnClickListener {
            @Override
            public void onClick(View v) {
                imageMotionModel.setImageState(imageMotionModel.top);
            }
        }
        private class onBottomListener implements OnClickListener {
            @Override
            public void onClick(View v) {
                imageMotionModel.setImageState(imageMotionModel.bottom);
            }
        }
        private class onDeleteListener implements OnClickListener {
            @Override
            public void onClick(View v) {
                imageMotionModel.removeImage();
            }
        }
        private class onMirroringListener implements OnClickListener {
            @Override
            public void onClick(View v) {
                imageMotionModel.setImageState(imageMotionModel.mirror);
            }

        }
        private class onButtonTouch implements OnTouchListener {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageButton button = (ImageButton) v;
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
                    button.setBackgroundResource(buttonBackgroundDrawables[cliccked]);
                else if (event.getActionMasked() == MotionEvent.ACTION_UP)
                    button.setBackgroundResource(buttonBackgroundDrawables[active]);

                return false;
            }
        }

}


