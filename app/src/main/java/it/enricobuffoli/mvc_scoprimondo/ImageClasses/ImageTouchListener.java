package it.enricobuffoli.mvc_scoprimondo.ImageClasses;

import android.app.Activity;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Utilities.ActivityUtility;

public class ImageTouchListener implements OnTouchListener {

    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ROTATE = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    private Rectangle imageRect;
    private Image image;
    PointF center;
    private static final int bX = 2,bY = 5, matrixLenght=9, oneTouch=1,twoTouch=2,threeTouch=3;
    private static final int firstEvent=0,secondEvent=1,thirdEvent=2,twoPi=360;

    public ImageTouchListener(Image image,Rectangle imageRect,PointF center)
    {
        this.image=image;
        this.imageRect= imageRect;
        this.center= center;
    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Image view = (Image) v;
        try {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:
                    if (isOnImageOneTouch(event)) {
                        savedMatrix.set(matrix);
                        start.set(event.getX(), event.getY());
                        mode = DRAG;
                        lastEvent = null;
                        imageRect.stable();
                    } else
                        mode = NONE;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    if (isOnImageDoubleTouch(event)) {
                        if (event.getPointerCount() == threeTouch)
                            oldDist = spacing(event);
                        savedMatrix.set(matrix);
                        mode = ROTATE;
                        lastEvent = new float[4];
                        d = rotation(event);
                        imageRect.stable();
                    } else
                        mode = NONE;

                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    lastEvent = null;
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        matrix.set(savedMatrix);
                        float dx = event.getX() - start.x;
                        float dy = event.getY() - start.y;
                        float matrixValues[] = new float[matrixLenght];
                        matrix.postTranslate(dx, dy);
                        matrix.getValues(matrixValues);
                        float X = matrixValues[bX];
                        float Y = matrixValues[bY];
                        center = imageRect.getCenterByB(new PointF(X, Y));

                    } else if (mode == ROTATE) {
                        if (event.getPointerCount() == twoTouch) {
                            matrix.set(savedMatrix);
                            newRot = rotation(event);
                            float r;
                            if (d < 0)
                                d = twoPi + d;
                            if (newRot < 0)
                                newRot = twoPi + newRot;

                            r = (newRot - d + twoPi) % twoPi;
                            matrix.postRotate(r, center.x, center.y);
                            float angle = (float) Math.toRadians(r);
                            imageRect.setRotation(angle);

                        } else if (lastEvent != null && event.getPointerCount() == threeTouch) {
                            Console.log("3 dita");
                            matrix.set(savedMatrix);
                            float newDist = spacing(event);
                            float scale = (newDist / oldDist);
                            matrix.postScale(scale, scale, center.x, center.y);
                            imageRect.setScaleX(scale);
                            imageRect.setScaleY(scale);
                        }
                    }


                    break;
            }
            view.setImageMatrix(matrix);
        }
        catch (Exception exp)
        {
            ActivityUtility.showDialogAlert((Activity) this.image.getContext(), "Ops . . .", "Errore: " + exp.getMessage(), "Continua", null);
        }
        return true;

    }

    public void mirroringImage()
    {
        try {
            imageRect.stable();
            float matrixValues[] = new float[matrixLenght];
            matrix.getValues(matrixValues);
            float X = matrixValues[bX];
            float Y = matrixValues[bY];
            center = imageRect.getCenterByB(new PointF(X, Y));
            System.out.println(imageRect.toString());
            matrix.postScale(-1, 1, center.x, center.y);
            this.imageRect.setScaleX(-1);
            image.setImageMatrix(matrix);
        }
        catch (Exception exp)
        {
            ActivityUtility.showDialogAlert((Activity) this.image.getContext(), "Ops . . .", "Errore: " + exp.getMessage(), "Continua", null);
        }
    }

    public boolean pointBelongToImage(MotionEvent event)
    {
        try {
            PointF touch = new PointF(event.getX(), event.getY());
            touch = relativePosition(touch);
            return imageRect.contains(touch);
        }
        catch (Exception exp)
        {
            ActivityUtility.showDialogAlert((Activity) this.image.getContext(), "Ops . . .", "Errore: " + exp.getMessage(), "Continua", null);
            return false;
        }
    }

    private boolean isOnImageOneTouch(MotionEvent event)
    {
        PointF touch= new PointF(event.getX(),event.getY());
        touch=relativePosition(touch);
        return imageRect.contains(touch);
    }

    private boolean isOnImageDoubleTouch(MotionEvent event)
    {
        PointF touch1= new PointF(event.getX(firstEvent),event.getY(firstEvent));
        touch1=relativePosition(touch1);
        PointF touch2= new PointF(event.getX(secondEvent),event.getY(secondEvent));
        touch2=relativePosition(touch2);
        return imageRect.contains(touch1)||imageRect.contains(touch2);
    }

    private PointF relativePosition(PointF p)
    {
        p.x=p.x-center.x;
        p.y=center.y-p.y;
        return p;
    }
    /** Determine the space between the first two fingers
     */
    private float spacing(MotionEvent event) {
        float x1 = event.getX(firstEvent) - event.getX(secondEvent);
        float y1 = event.getY(firstEvent) - event.getY(secondEvent);
        float x2 = event.getX(firstEvent) - event.getX(thirdEvent);
        float y2 = event.getY(firstEvent) - event.getY(thirdEvent);
        float x3 = event.getX(secondEvent) - event.getX(thirdEvent);
        float y3 = event.getY(secondEvent) - event.getY(thirdEvent);
        if((float)Math.sqrt(x1 * x1 + y1 * y1)>(float)Math.sqrt(x2 * x2 + y2 * y2) && (float)Math.sqrt(x1 * x1 + y1 * y1)>(float)Math.sqrt(x3 * x3 + y3 * y3))
            return (float)Math.sqrt(x1 * x1 + y1 * y1);
        else if((float)Math.sqrt(x2 * x2 + y2 * y2)>(float)Math.sqrt(x1 * x1 + y1 * y1)&&(float)Math.sqrt(x2 * x2 + y2* y2)>(float)Math.sqrt(x3 * x3 + y3 * y3))
            return (float)Math.sqrt(x2 * x2 + y2 * y2);
        else
            return (float)Math.sqrt(x3 * x3 + y3 * y3);
    }


    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(firstEvent) - event.getX(secondEvent));
        double delta_y = (event.getY(firstEvent) - event.getY(secondEvent));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private float xDistanceBetweenTwoPoints(PointF a,PointF b)
    {
        return Math.abs(a.x-b.x);
    }

    private float yDistanceBetweenTwoPoints(PointF a,PointF b)
    {
        return Math.abs(a.y-b.y);
    }

    public void getMatrixValues(float values[])
    {
        float matrixValues[] = new float[matrixLenght];
        matrix.getValues(matrixValues);
        values[0] = matrixValues[bX];
        values[1] = matrixValues[bY];
        values[2] = imageRect.getRectAngle();
        values[3] = imageRect.getScaleX();
    }
    public void postTranslate(Image image,float dx, float dy)
    {
        try {
            matrix.postTranslate(dx, dy);
            image.setImageMatrix(matrix);
        }
        catch (Exception exp)
        {
            ActivityUtility.showDialogAlert((Activity) this.image.getContext(), "Ops . . .", "Errore: " + exp.getMessage(), "Continua", null);
        }
    }
}
