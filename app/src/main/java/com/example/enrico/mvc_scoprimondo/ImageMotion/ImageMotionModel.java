package com.example.enrico.mvc_scoprimondo.ImageMotion;

import com.example.enrico.mvc_scoprimondo.Base.Model;
import com.example.enrico.mvc_scoprimondo.ImageClasses.Image;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by enrico on 15/01/16.
 */
public class ImageMotionModel extends Model {
    public static final int unselected=0,selected=1,added=2,up=3,low=4,top=5,bottom=6,removed=7,mirror=8,changed_pos=9;
    private static final int X=0,Y=1,angle=2,scale=3;
    private ArrayList<ImageState> images;
    private ArrayList<ImageMemory> imagesMemory;
    public ImageMotionModel()
    {
        images = new ArrayList<>();
        imagesMemory = new ArrayList<>();
    }
    public void addImage(Image image)
    {
        image.setEnabled(false);
        images.add(new ImageState(image,added));
        /*Vuoi posizionarla dove vuoi o sempre nello stesso posto dimmi te merlo*/
        this.addImageMemory(image,added);
        this.fireValuesChange();
    }
    public void removeImage()
    {
        for(ImageState imgState : this.images)
        {
            if(imgState.getState()==selected)
            {
                imgState.setState(removed);
                this.addImageMemory(imgState);
            }
            else
                imgState.setState(unselected);
        }
        this.fireValuesChange();
    }
    public void setImageSelected(Image image)
    {
        System.out.println("ciao");
        for(ImageState imgState : this.images)
        {
            if(imgState.getImage().equals(image))
            {
                imgState.setState(selected);
            }
            else
            {
                if(imgState.getState()==selected)
                    this.addImageMemory(imgState);

                imgState.setState(unselected);
            }
        }
        this.fireValuesChange();
    }
    public void setImageState(int state)
    {
        for(ImageState imgState : this.images)
        {
            if(imgState.getState()==selected)
            {
                imgState.setState(state);
                switch (state)
                {
                    case up:
                        this.toUpperLevel();
                        imgState.setState(changed_pos);
                        break;
                    case low:
                        this.toLowerLevel();
                        imgState.setState(changed_pos);
                        break;
                    case top:
                        this.toTopLevel();
                        imgState.setState(changed_pos);
                        break;
                    case bottom:
                        this.toBottomLevel();
                        imgState.setState(changed_pos);
                        break;
                }
                this.addImageMemory(imgState);

            }
        }
        this.fireValuesChange();
    }
    public ArrayList<ImageState> getImageArray()
    {
        return images;
    }
    private void toUpperLevel()
    {
            for (ImageState temp : images)
                if (temp.getState() == up && images.indexOf(temp) != images.size() - 1)
                {
                    Collections.swap(images, images.indexOf(temp), images.indexOf(temp) + 1);
                    return;
                }
    }
    private void toLowerLevel()
    {
        for (ImageState temp: images)
            if(temp.getState()==low && images.indexOf(temp)!=0)
                Collections.swap(images,images.indexOf(temp),images.indexOf(temp)-1);
    }
    private void toTopLevel()
    {
            for(ImageState temp: images)
                if(temp.getState()==top && images.indexOf(temp)!=images.size()-1)
                    Collections.swap(images,images.indexOf(temp),images.indexOf(temp)+1);

    }
    private void toBottomLevel()
    {
        Collections.reverse(images);
        for(ImageState temp: images)
            if(temp.getState()==bottom && images.indexOf(temp)!=images.size()-1)
                Collections.swap(images, images.indexOf(temp), images.indexOf(temp) + 1);
        Collections.reverse(images);
    }
    private void addImageMemory(ImageState image)
    {
        float[] values = image.getImage().getImageChanges();
        imagesMemory.add(new ImageMemory(values[X],values[Y],values[angle],values[scale],image.getState(),image.getImage().toString()));

    }
    private void addImageMemory(Image image, int state)
    {
        float[] values = image.getImageChanges();
        imagesMemory.add(new ImageMemory(values[X],values[Y],values[angle],values[scale],state,image.toString()));

    }
    public ArrayList<ImageMemory> getImagesMemory()
    {
        return imagesMemory;
    }
    public class ImageState {
        private int state;
        private Image image;
        public ImageState(Image image,int state)
        {
            this.state=state;
            this.image=image;
        }
        public void setState(int state)
        {
            this.state=state;
        }
        public int getState()
        {
            return this.state;
        }
        public Image getImage()
        {
            return this.image;
        }
    }
    private class ImageMemory{
        private float X,Y,angle,scale;
        private int state;
        private String drawable;

        public ImageMemory(float X,float Y,float angle,float scale,int state,String drawable)
        {
            this.X=X;
            this.Y=Y;
            this.angle=angle;
            this.scale=scale;
            this.state=state;
            this.drawable=drawable;
        }

        public float getAngle() {
            return angle;
        }

        public float getY() {
            return Y;
        }

        public float getX() {
            return X;
        }

        public float getScale() {
            return scale;
        }

        public int getState() {
            return state;
        }

        public String getDrawable() {
            return drawable;
        }
    }
}
