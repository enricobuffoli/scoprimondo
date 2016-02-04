package it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

import java.util.ArrayList;

import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;

/**
 * Created by mattia on 04/02/16.
 */
public class ImageSpinnerView extends Spinner
{
    private Activity activity;
    private ImageSpinnerModel model = ImageSpinnerModel.getInstance(this.getContext());
    private ImageSpinnerAdapter adapter;

    public ImageSpinnerView(Context context) {
        super(context);
    }

    public ImageSpinnerView(Context context, int mode) {
        super(context, mode);
    }

    public ImageSpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void init(Activity activity, ArrayList<ImageSpinnerItem> restriction)
    {
        Console.log(restriction + " " + restriction.size());
        this.activity = activity;
        this.adapter = new ImageSpinnerAdapter(this.activity, this.model, restriction);

        this.setAdapter(this.adapter);
    }

    public Integer getDrawableSelected()
    {
        return (Integer) this.getSelectedItem();
    }
}
