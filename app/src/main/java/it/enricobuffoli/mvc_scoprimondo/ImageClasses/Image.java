package it.enricobuffoli.mvc_scoprimondo.ImageClasses;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;


public class Image extends ImageView {

    private int imageChanges[] = new int[3];
	private Rectangle imageRect;
    private PointF center;
	private ImageTouchListener imageTouch;
	private String drawable;
    private Bitmap imageBitmap,borderBitmap;
    private final int BORDER_WIDTH = 5;

    public Image(Context context, int drawable)
    {
        this(context,null,0,drawable);
    }
    public Image(Context context, AttributeSet attrs,int drawable)
    {
        this(context, attrs, 0, drawable);
    }

    public Image(Context context, AttributeSet attrs, int defStyle,int drawable)
    {
        super(context, attrs, defStyle);
        init(drawable);
    }
    private void init(int drawable)
    {
        this.drawable= String.valueOf(drawable);
        setScaleType(ScaleType.MATRIX);
        this.setDrawable(drawable);
        this.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        imageRect= new Rectangle(this.getDrawable().getIntrinsicWidth(),this.getDrawable().getIntrinsicHeight());
        center=new PointF(this.getDrawable().getIntrinsicWidth()/2,this.getDrawable().getIntrinsicHeight()/2);

        /*
         * Fix Memory
         */
        //BitmapFactory.Options options = new BitmapFactory.Options();
        //options.inSampleSize = 2; //1 normale, due è minorata e occupa meno spazio

        imageBitmap = BitmapFactory.decodeResource(this.getResources(),drawable);//, options);
        this.initBorderBitmap();
        this.setImageBitmap(imageBitmap);
        imageTouch=new ImageTouchListener(this, imageRect, center);
        this.setOnTouchListener(imageTouch);
    }
    public void setDrawable(int drawable)
    {
    	this.setImageResource(drawable);
    }
    public boolean pointBelongToImage(MotionEvent ev)
    {
    	return imageTouch.pointBelongToImage(ev);
    }

    public void setEnabled(boolean flag)
    {
        if(flag)
            this.activeBorder();
        else
            this.deactiveBorder();

        super.setEnabled(flag);
    }
    private void initBorderBitmap()
    {
        final int BORDER_COLOR = Color.RED;
        borderBitmap = Bitmap.createBitmap(imageBitmap.getWidth() + 2 * BORDER_WIDTH,
                imageBitmap.getHeight() + 2 * BORDER_WIDTH,
                imageBitmap.getConfig());
        Canvas borderCanvas = new Canvas(borderBitmap);
        Paint borderPaint = new Paint();
        borderPaint.setColor(BORDER_COLOR);
        borderCanvas.drawRect(0, 0, borderBitmap.getWidth(), borderBitmap.getHeight(), borderPaint);
        borderPaint = new Paint(Paint.FILTER_BITMAP_FLAG);
        borderCanvas.drawBitmap(imageBitmap, BORDER_WIDTH, BORDER_WIDTH, borderPaint);

    }
    private void activeBorder()
    {
        if(!this.isEnabled())
        {
            this.setImageBitmap(borderBitmap);
            this.imageTouch.postTranslate(this, -BORDER_WIDTH, -BORDER_WIDTH);
        }
    }
    private void deactiveBorder()
    {
        if(this.isEnabled())
        {
            this.setImageBitmap(imageBitmap);
            this.imageTouch.postTranslate(this, BORDER_WIDTH, BORDER_WIDTH);
        }
    }
    public float[] getImageChanges()
    {
        float values[]= new float[4];
        imageTouch.getMatrixValues(values);
        return values;
    }
    public void mirroring()
    {
        imageTouch.mirroringImage();
    }
    public String toString()
    {
        return this.drawable;
    }
}
