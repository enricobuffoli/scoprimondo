package com.example.enrico.gestioneimmagini.LayoutGestures;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import com.example.enrico.gestioneimmagini.R;
import java.util.ArrayList;

/**
 * Created by enrico on 07/01/16.
 */
public class ButtonGestureView extends FrameLayout {

    private ArrayList<ImageButton> buttons = new ArrayList<>();
    private static final int up = 0, down = 1, bottom = 2, top = 3, delete = 4, mirroring = 5;
    private ImageMotionView motionView;

    public ButtonGestureView(Context context) {
        super(context, null, 0);
    }

    public ButtonGestureView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public ButtonGestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        this.setBackgroundColor(Color.rgb(135,206,235));
        motionView = (ImageMotionView) findViewById(R.id.motion_view);
        initButton();
    }

    private void initButton() {
        for (int i = 0; i < 6; i++) {
            buttons.add(new ImageButton(this.getContext()));
            buttons.get(i).setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            buttons.get(i).setY(100);
            buttons.get(i).setX(100 * i+100);
            buttons.get(i).setBackgroundResource(R.mipmap.inactive);
            buttons.get(i).setOnTouchListener(new onButtonTouch());
            this.addView(buttons.get(i));
        }
        initButtonImages();
    }

    private void initButtonImages() {
        buttons.get(up).setImageResource(R.mipmap.function_icons_up);
        buttons.get(down).setImageResource(R.mipmap.function_icons_down);
        buttons.get(bottom).setImageResource(R.mipmap.function_icons_bottom);
        buttons.get(top).setImageResource(R.mipmap.function_icons_top);
        buttons.get(delete).setImageResource(R.mipmap.function_icons_delete);
        buttons.get(mirroring).setImageResource(R.mipmap.specchia);
        buttons.get(up).setOnClickListener(new onUpListener());
        buttons.get(down).setOnClickListener(new onDownListener());
        buttons.get(top).setOnClickListener(new onTopListener());
        buttons.get(bottom).setOnClickListener(new onBottomListener());
        buttons.get(delete).setOnClickListener(new onDeleteListener());
        buttons.get(mirroring).setOnClickListener(new onMirroringListener());
        deactiveAllButtons();
    }

    public void deactiveLevelButtons()
    {
        for(int i=0;i<=top;i++)
        {
            buttons.get(i).setBackgroundResource(R.mipmap.inactive);
            buttons.get(i).setEnabled(false);
            buttons.get(i).setClickable(false);
        }
    }
    public void deactiveAllButtons()
    {
        for(int i=0;i<buttons.size();i++)
        {
            buttons.get(i).setBackgroundResource(R.mipmap.inactive);
            buttons.get(i).setEnabled(false);
            buttons.get(i).setClickable(false);
        }
    }
    public void activeAllButtons()
    {
        for(int i=0;i<buttons.size();i++)
        {
            buttons.get(i).setBackgroundResource(R.mipmap.active);
            buttons.get(i).setEnabled(true);
            buttons.get(i).setClickable(true);
        }
    }
    public void addImage(int drawable)
    {
        motionView.addImage(drawable);
        if(motionView.getImagesNumber()==1)
        {
            activeAllButtons();
            deactiveLevelButtons();
        }
        else if(motionView.getImagesNumber()>1)
            activeAllButtons();
    }
    private class onUpListener implements OnClickListener {


        @Override
        public void onClick(View v) {
            motionView.toUpperLevel();
        }


    }

    private class onDownListener implements OnClickListener  {


        @Override
        public void onClick(View v)
        {
            motionView.toLowerLevel();
        }


    }
    private class onTopListener implements OnClickListener  {


        @Override
        public void onClick(View v)
        {
            motionView.toTopLevel();
        }


    }
    private class onBottomListener implements OnClickListener  {


        @Override
        public void onClick(View v)
        {
            motionView.toBottomLevel();
        }

    }
    private class onDeleteListener implements OnClickListener  {


        @Override
        public void onClick(View v)
        {
            motionView.deleteImage();
            if(motionView.getImagesNumber()==1)
            {
                deactiveLevelButtons();
            }
            else if(motionView.getImagesNumber()==0)
            {
                deactiveAllButtons();
            }
        }

    }
    private class onMirroringListener implements OnClickListener  {


        @Override
        public void onClick(View v)
        {
            motionView.mirroringImage();
        }

    }
    private class onButtonTouch implements OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageButton upView = (ImageButton) v;
            if (event.getActionMasked() == MotionEvent.ACTION_DOWN)
                upView.setBackgroundResource(R.mipmap.cliccked);
            else if (event.getActionMasked() == MotionEvent.ACTION_UP)
                upView.setBackgroundResource(R.mipmap.active);

            return false;
        }
    }
}


