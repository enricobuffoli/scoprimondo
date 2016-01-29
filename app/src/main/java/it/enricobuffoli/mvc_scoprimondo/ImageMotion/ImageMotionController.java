package it.enricobuffoli.mvc_scoprimondo.ImageMotion;

import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by enrico on 15/01/16.
 */

public class ImageMotionController implements Observer {
    private ImageMotionModel imageMotionModel;
    private ImageMotionView imageMotionView;
    public ImageMotionController(ImageMotionModel imageMotionModel,ImageMotionView imageMotionView)
    {
        this.imageMotionModel= imageMotionModel;
        this.imageMotionView=imageMotionView;
        this.imageMotionModel.addObserver(this);
    }
    @Override
    public void update(Observable observable, Object data) {

        for(ImageMotionModel.ImageState temp: imageMotionModel.getImageArray())
        {
            switch (temp.getState())
            {
                case (ImageMotionModel.added):
                    imageMotionView.addView(temp.getImage());
                    imageMotionModel.setImageSelected(temp.getImage());
                    break;
                case (ImageMotionModel.removed):
                    imageMotionView.removeView(temp.getImage());
                    imageMotionModel.getImageArray().remove(imageMotionModel.getImageArray().indexOf(temp));
                    break;
                case (ImageMotionModel.changed_pos):
                    this.bringImageToFront(imageMotionModel.getImageArray().indexOf(temp));
                    imageMotionModel.setImageSelected(temp.getImage());
                    break;
                case (ImageMotionModel.mirror):
                    temp.getImage().mirroring();
                    imageMotionModel.setImageSelected(temp.getImage());
                    break;
                case (ImageMotionModel.selected):
                    temp.getImage().setEnabled(true);
                    break;
                case (ImageMotionModel.unselected):
                    temp.getImage().setEnabled(false);
                    break;
            }
        }
    }
    public void bringImageToFront(int beginIndex)
    {
        for(int i=beginIndex;i<imageMotionModel.getImageArray().size();i++)
            imageMotionModel.getImageArray().get(i).getImage().bringToFront();
    }
    public void eventDown(MotionEvent ev)
    {
        try {
            ArrayList<ImageMotionModel.ImageState> temp = imageMotionModel.getImageArray();
            for (int i = temp.size() - 1; i >= 0; i--)
                if (temp.get(i).getImage().pointBelongToImage(ev)) {
                    temp.get(i).getImage().setEnabled(true);
                    imageMotionModel.setImageSelected(temp.get(i).getImage());
                    for (i--; i >= 0; i--) {
                        temp.get(i).getImage().setEnabled(false);
                    }
                    return;
                } else {
                    temp.get(i).getImage().setEnabled(false);
                }
        }
        catch (Exception exp)
        {

        }
    }

}
