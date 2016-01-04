package merlini.mvcpattern.ImagePanel;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

import merlini.mvcpattern.ActivityMain;
import merlini.mvcpattern.Base.Console;
import merlini.mvcpattern.Base.Model;
import merlini.mvcpattern.R;

/**
 * Created by mattia on 27/11/15.
 */
public class ImageModel extends Model
{
    ArrayList<Bitmap> images;

    public ImageModel()
    {
        this.images = new ArrayList<>();
    }

    public void addImage(int drawable, Context context)
    {
        try
        {
            Bitmap b = BitmapFactory.decodeResource(context.getResources(), drawable);
            this.images.add(b);
            this.fireValuesChange(String.format("Aggiunta immagine [%s]", b.toString()));
        }
        catch (Exception e)
        {
            Console.log(e.getMessage());
        }
    }

    public ArrayList<Bitmap> getImages()
    {
        return this.images;
    }
}
