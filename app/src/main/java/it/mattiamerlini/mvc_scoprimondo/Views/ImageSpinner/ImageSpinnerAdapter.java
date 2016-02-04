package it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.enrico.mvc_scoprimondo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mattia on 04/02/16.
 */
public class ImageSpinnerAdapter extends ArrayAdapter<Integer>
{
    private ArrayList<ImageSpinnerItem> images;

    private Activity activity;

    /**
     * Costruttore dello spinner adapter.
     * @param activity Activity Activity di riferimento (per il contesto).
     * @param images ImageSpinnerModel Modello con tutte le immagini di scoprimondo.
     * @param restrictions ArrayList<Integer> Array con gli IDs delle immagini da inserire in questo preciso spinner.
     */
    public ImageSpinnerAdapter(Activity activity, ImageSpinnerModel images, ArrayList<ImageSpinnerItem> restrictions) {
        super(activity.getApplicationContext(), android.R.layout.simple_spinner_item, images.getImagesIds(restrictions));

        this.activity = activity;
        this.images = images.getItems(restrictions);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getImageForPosition(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getImageForPosition(position);
    }

    private View getImageForPosition(int position)
    {
        LayoutInflater inflater = this.activity.getLayoutInflater();

        View spinnerItem = inflater.inflate(R.layout.image_spinner_layout, null);
        TextView text = ((TextView) spinnerItem.findViewById(R.id.spinnerText));
        ImageView image = ((ImageView) spinnerItem.findViewById(R.id.spinnerImage));

        ImageSpinnerItem item = this.images.get(position);
        String spinnerText = item.getImageText();
        Drawable spinnerImage = item.getImageDrawable();

        text.setText(spinnerText);

        Bitmap bitmap = ((BitmapDrawable) spinnerImage).getBitmap();
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Resources r = this.activity.getResources();
        float bounding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics());
        float xScale = bounding / width;
        float yScale = bounding / height;
        float scale = (xScale <= yScale) ? xScale : yScale;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        BitmapDrawable result = new BitmapDrawable(scaledBitmap);

        image.setImageDrawable(result);
        image.setMaxWidth(15);
        image.setMaxHeight(15);

        return spinnerItem;
    }
}
