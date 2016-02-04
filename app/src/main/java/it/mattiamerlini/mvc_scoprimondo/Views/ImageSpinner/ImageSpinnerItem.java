package it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;

/**
 * Created by mattia on 04/02/16.
 */
public class ImageSpinnerItem
{
    private int imageId;
    private String imageText;
    private Drawable imageDrawable;
    private Context context;

    public ImageSpinnerItem(int imageId, String imageText) {
        this.imageId = imageId;
        this.imageText = imageText;
    }

    public ImageSpinnerItem(Context context, int imageId, String imageText) {
        this.context = context;
        this.imageText = imageText;
        this.imageId = imageId;
        this.imageDrawable = ContextCompat.getDrawable(this.context, this.imageId);
    }

    public int getImageId() {
        return imageId;
    }

    public String getImageText() {
        return imageText;
    }

    public Drawable getImageDrawable() {
        return imageDrawable;
    }
}
