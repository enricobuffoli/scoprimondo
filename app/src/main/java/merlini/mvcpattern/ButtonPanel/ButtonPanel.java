package merlini.mvcpattern.ButtonPanel;

import android.content.Context;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import merlini.mvcpattern.ActivityMain;
import merlini.mvcpattern.Base.Console;

/**
 * Created by mattia on 26/11/15.
 */
public class ButtonPanel extends RelativeLayout implements Observer
{
    private HorizontalScrollView horizontalScrollView;
    private LinearLayout linearLayout;

    private TextView textView;

    private ButtonModel buttonModel;

    public ButtonPanel(Context context) {
        super(context);
        init();
    }

    public ButtonPanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init()
    {
        // Inizializzo i vari layout
        this.horizontalScrollView = new HorizontalScrollView(this.getContext());
        this.horizontalScrollView.setId(ActivityMain.ID ++);
        this.linearLayout = new LinearLayout(this.getContext());
        this.linearLayout.setId(ActivityMain.ID ++);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.addView(this.horizontalScrollView, params);
        this.horizontalScrollView.addView(this.linearLayout, params);
        this.horizontalScrollView.setHorizontalScrollBarEnabled(false);

        this.buttonModel = new ButtonModel();
        this.textView = new TextView(this.getContext());

        LayoutParams params1 = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.BELOW, this.horizontalScrollView.getId());
        this.addView(this.textView, params1);


        this.buttonModel.addObserver(this);

        /*this.buttonModel.addButton(this.getContext(), "Click me", new OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText(textView.getText() + " click");
                Console.log("Cliccato!");
                buttonModel.addButton(getContext(), "Click me2", new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Console.log("Clicked!");
                    }
                });
            }
        });*/

        //this.debug();

        /*Button b = new Button(this.getContext());
        b.setText("Test btn");
        b.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View o) {
                System.out.println(o);
            }
        });

        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE);

        this.linearLayout.addView(b);

        Button b2 = new Button(this.getContext());
        b2.setText("Test btn2");

        params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params1.addRule(RelativeLayout.CENTER_IN_PARENT, b.getId());

        Button b3 = new Button(this.getContext());
        b3.setText("Test btn3");
        this.linearLayout.addView(b3);

        Button b4 = new Button(this.getContext());
        b4.setText("Test btn3");
        this.linearLayout.addView(b4);

        Button b5 = new Button(this.getContext());
        b5.setText("Test btn3");
        this.linearLayout.addView(b5);*/




    }

    @Override
    public void update(Observable observable, Object data)
    {
        this.buttonModel = (ButtonModel) observable;
        Console.log(data);
        this.linearLayout.removeAllViewsInLayout();
        this.linearLayout.removeAllViews();
        this.disegna();
        this.linearLayout.invalidate();
    }

    private void disegna()
    {
        //this.linearLayout = new LinearLayout(this.getContext());
        this.addButtonsToPanel();
    }

    private void addButtonsToPanel()
    {
        for(Button b : this.buttonModel.getButtons())
        {
            this.linearLayout.removeView(b);
            this.linearLayout.addView(b);
        }
    }

    private void debug()
    {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Console.log("\nNumero di Button aggiunti: " + linearLayout.getChildCount() + "\n");
            }
        };
        Timer t = new Timer();
        t.scheduleAtFixedRate(task, 0, 3000);
    }

    public void addButton(String label, OnClickListener listener)
    {
        this.buttonModel.addButton(this.getContext(), label, listener);
    }
}
