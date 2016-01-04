package merlini.mvcpattern.DraggableImagePanel;

import android.animation.PointFEvaluator;
import android.content.Context;
import android.graphics.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import merlini.mvcpattern.Base.Console;
import merlini.mvcpattern.R;

/**
 * Created by mattia on 10/12/15.
 */
public class TransformableImage extends View implements OnTouchListener
{
    Bitmap bitmap;
    private float xTopLeft;
    private float yTopLeft;
    private Paint paint;
    private float rotation;
    private float dx;
    private float dy;
    private float scale;


    public TransformableImage(Context context, int drawable) {
        super(context);
        this.init(drawable);
    }

    public TransformableImage(Context context, AttributeSet attrs, int drawable) {
        super(context, attrs);
        this.init(drawable);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
        float newX = event.getX();
        float newY = event.getY();
        //this.move(newX, newY);
        //this.rotate(50);
        //this.invalidate();


        int motionEvent = event.getAction() & MotionEvent.ACTION_MASK;

        switch (motionEvent)
        {
            case MotionEvent.ACTION_DOWN:
                Console.log(String.format("ACTION_DOWN -> [%f, %f]", newX, newY));
                break;
            case MotionEvent.ACTION_MOVE:
                Console.log(String.format("ACTION_MOVE -> [%f, %f]", newX, newY));
                this.move(newX, newY);
                break;
            case MotionEvent.ACTION_UP:
                Console.log(String.format("ACTION_UP -> [%f, %f]", newX, newY));
                break;
            default:
                Console.log(String.format("ACTION_UNKNOW (%s) -> [%f, %f]", motionEvent, newX, newY));
        }

        return true;
    }

    public void setImage(int drawable)
    {
        this.bitmap = BitmapFactory.decodeResource(this.getResources(), drawable);
        //this.init();
    }

    private void init(int drawable)
    {
        this.bitmap = BitmapFactory.decodeResource(this.getResources(), drawable);
        this.setOnTouchListener(this);
        this.xTopLeft = this.bitmap.getWidth() / 2; //0;//this.bitmap.getWidth();
        this.yTopLeft = this.bitmap.getHeight() / 2; //0;//this.bitmap.getHeight();
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
        this.paint.setStrokeWidth(100f);
        this.rotation = 0;




    }

    public void move(float newX, float newY)
    {


        ImageView a = new ImageView(this.getContext());
        a.setImageBitmap(this.bitmap);
        Matrix inverse = new Matrix();
        this.getMatrix().invert(inverse);
        float[] touchPoint = new float[] {newX, newY};
        inverse.mapPoints(touchPoint);



        Matrix m = this.getMatrix();
        m.postTranslate(newX - this.xTopLeft, newY - this.yTopLeft);
        float[] punti = new float[] {this.xTopLeft, this.yTopLeft};
        m.mapPoints(punti);


        this.xTopLeft = punti[0];
        this.yTopLeft = punti[1];

        Console.log(String.format("TopLeft : [%f;%f]", this.xTopLeft, this.yTopLeft));

        /*float x = this.xTopLeft;
        float y = this.yTopLeft;

        Console.log(String.format("Touch: [%f;%f] TopLeft : [%f;%f]", touchPoint[0], touchPoint[1], x, y));

        float dx = this.xTopLeft;
        float dy = (this.bitmap.getHeight()/2) - touchPoint[1];
        float cx = touchPoint[0] + dx;
        float cy = touchPoint[1] + dy;
        float lx = cx + this.bitmap.getWidth()/2;
        float ly = cy + this.bitmap.getHeight()/2;

        this.xTopLeft = lx;
        this.yTopLeft = ly;*/

        //this.xTopLeft = this.getX() + touchPoint[0] - (this.bitmap.getWidth()/2);
        //this.yTopLeft = this.getY() + touchPoint[1] - (this.bitmap.getHeight()/2);

        this.invalidate(); // Repaint
    }

    public void rotate(float angle)
    {
        this.rotation += angle;

        this.invalidate(); // Repaint
    }

    @Override
    public void onDraw(Canvas canvas)
    {
        canvas.save(Canvas.MATRIX_SAVE_FLAG);
        {
            // Canvas

            canvas.drawBitmap(this.bitmap, this.xTopLeft, this.yTopLeft, this.paint);
            canvas.rotate(this.rotation, this.bitmap.getWidth() / 2, this.bitmap.getHeight()/ 2);


            Console.log(String.format("TOP_LEFT [%f, %f]", this.xTopLeft, this.yTopLeft));
        }
        canvas.restore();
        {
            // Canvas senza trasformazioni o rotazioni
        }
    }
}
