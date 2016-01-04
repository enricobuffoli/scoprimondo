package merlini.mvcpattern.DraggableImagePanel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Observable;
import java.util.Observer;

import merlini.mvcpattern.Base.Console;

/**
 * Created by mattia on 02/12/15.
 */
public class DraggableImagePanel extends RelativeLayout implements Observer, View.OnClickListener
{
    private DraggableImageModel draggableImageModel;

    public DraggableImagePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public DraggableImagePanel(Context context) {
        super(context);
        this.init();
    }

    private void init()
    {
        this.setLayoutParams(new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        this.draggableImageModel = new DraggableImageModel();
        this.draggableImageModel.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data)
    {
        Console.log("Fired");
        this.draggableImageModel = (DraggableImageModel) observable;
        Console.log(data);
        this.removeAllViewsInLayout();
        this.removeAllViews();
        this.draw();
        this.invalidate();
    }

    private void draw()
    {
        this.addImageToPanel();
    }

    private void addImageToPanel()
    {
        for(TransformableImage b : this.draggableImageModel.getImages())
        {
            Console.log("Aggiungo " + b);
            //this.removeView(b);
            this.addView(b);
        }
    }

    public void addImage(int drawable)
    {
        this.draggableImageModel.addImage(drawable, this.getContext());
    }

    @Override
    public void onClick(View v) {
        for(TransformableImage a : this.draggableImageModel.getImages())
        {
            a.setActivated(false);
            a.setClickable(false);
        }
        v.setActivated(true);
        v.setClickable(true);

    }
}
