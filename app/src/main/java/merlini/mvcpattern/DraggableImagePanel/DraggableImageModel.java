package merlini.mvcpattern.DraggableImagePanel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;

import java.util.ArrayList;

import merlini.mvcpattern.Base.Console;
import merlini.mvcpattern.Base.Model;
import merlini.mvcpattern.R;

/**
 * Created by mattia on 02/12/15.
 */
public class DraggableImageModel extends Model
{
    public static int ID = 0;
    ArrayList<TransformableImage> images;

    public DraggableImageModel()
    {
        this.images = new ArrayList<>();
    }

    public void addImage(int drawable, Context context)
    {
        try
        {
            TransformableImage i = new TransformableImage(context, drawable);
            i.setId(DraggableImageModel.ID++);
            i.setClickable(true);
            i.setEnabled(true);
            i.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(TransformableImage a : getImages())
                    {
                        a.setEnabled(false);
                    }
                    v.setEnabled(true);
                }
            });
            this.images.add(i);
            this.fireValuesChange(String.format("Aggiunta immagine draggable [%s]", i.toString()));
        }
        catch (Exception e)
        {
            Console.log(e.getMessage());
        }
    }

    public ArrayList<TransformableImage> getImages()
    {
        return this.images;
    }
}
