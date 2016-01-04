package merlini.mvcpattern.ImagePanel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import merlini.mvcpattern.ActivityMain;
import merlini.mvcpattern.Base.Console;
import merlini.mvcpattern.ButtonPanel.ButtonModel;
import merlini.mvcpattern.R;

/**
 * Created by mattia on 27/11/15.
 */
public class ImagePanel extends View implements Observer
{
    private ImageModel imageModel;

    public ImagePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.init();
    }

    public ImagePanel(Context context) {
        super(context);
        this.init();
    }

    private void init()
    {
        this.imageModel = new ImageModel();
        this.imageModel.addObserver(this);

        // Img di prova
        //this.imageModel.addImage(R.drawable.due, this.getContext());
    }

    public void addImage(int drawable)
    {
        this.imageModel.addImage(drawable, this.getContext());
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        Paint paint = new Paint();
        paint.setStrokeWidth(1f);
        for(Bitmap b : this.imageModel.getImages())
        {
            canvas.drawBitmap(b, 0, 0, paint);
        }
    }

    @Override
    public void update(Observable observable, Object data)
    {
        this.imageModel = (ImageModel) observable;
        Console.log(data);
        this.invalidate();
    }
}
