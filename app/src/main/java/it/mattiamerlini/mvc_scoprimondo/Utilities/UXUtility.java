package it.mattiamerlini.mvc_scoprimondo.Utilities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.enrico.mvc_scoprimondo.R;

/**
 * Created by mattia on 29/01/16.
 */
public class UXUtility<U>
{
    private static UXUtility ourInstance = null;
    private Context context;

    public static UXUtility getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new UXUtility(context);
        ourInstance.setContext(context);
        return ourInstance;
    }

    private Typeface typeface;
    private int textColor;
    private int buttonColor;
    private int marginTop;
    private int marginLeft;
    private int footerBackgroundColor;
    private int footerTextColor;
    private Drawable background;

    private UXUtility(Context context)
    {
        this.context = context;
        this.typeface = Typeface.createFromAsset(this.context.getAssets(),"fonts/Montserrat-Bold.otf");
        this.textColor = Color.parseColor("#1b455a");
        this.buttonColor = Color.parseColor("#f38630");
        this.marginTop = 10;
        this.marginLeft = 10;
        this.footerBackgroundColor = Color.parseColor("#1b455a");
        this.footerTextColor = Color.parseColor("#ffffff");
        this.background = ContextCompat.getDrawable(this.context, R.drawable.sfondo);
    }

    public void setLogo(ImageView imageView, int below)
    {
        imageView.setImageDrawable(ContextCompat.getDrawable(this.context, R.drawable.logo));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_START);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        imageView.setLayoutParams(params);
    }

    public void setTitle(TextView textView, int below)
    {
        textView.setText("LO SCOPRIMONDO");
        textView.setTypeface(this.getTypeface());
        textView.setTextColor(this.getTextColor());
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, below);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        textView.setLayoutParams(params);
    }

    public void setCenteredButton(Button button, String text, int below)
    {
        button.setText(text);
        button.setTextColor(this.getTextColor());
        button.setBackgroundColor(this.getButtonColor());
        button.setTypeface(this.getTypeface());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, below);
        params.setMargins(0, this.getMarginTop(), 0, 0);
        button.setGravity(Gravity.CENTER_HORIZONTAL);
        button.setLayoutParams(params);
    }

    public void setCenteredEditText(EditText editText, String text, int below, int inputType)
    {
        editText.setText(text);
        editText.setTextColor(this.getTextColor());
        editText.setTypeface(this.getTypeface());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, below);
        params.setMargins(0, this.getMarginTop(), 0, 0);
        editText.setGravity(Gravity.CENTER_HORIZONTAL);
        editText.setInputType(inputType);
        editText.setLayoutParams(params);
    }

    public void setCenteredSpinner(Spinner spinner, U[] options, U selected, int below)
    {
        ArrayAdapter<U> adapter = new ArrayAdapter<U>(this.getContext(), android.R.layout.simple_spinner_item, options);
        spinner.setAdapter(adapter);
        if(selected != null)
            spinner.setSelection(adapter.getPosition(selected));
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, below);
        params.setMargins(0, this.getMarginTop(), 0, 0);
        spinner.setGravity(Gravity.CENTER_HORIZONTAL);
        spinner.setLayoutParams(params);
    }

    public void setLeftTextView(TextView textView, String text, int below)
    {
        textView.setText(text);
        textView.setTextColor(this.getTextColor());
        textView.setTypeface(this.getTypeface());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, below);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(0, this.getMarginTop(), 0, 0);
        textView.setLayoutParams(params);
    }

    public void setLogoutButtonInFooter(Button logoutButton)
    {
        logoutButton.setText("LOGOUT");
        logoutButton.setTextColor(this.getTextColor());
        logoutButton.setBackgroundColor(this.getButtonColor());
        logoutButton.setTypeface(this.getTypeface());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        logoutButton.setLayoutParams(params);
    }

    public void setUserLoggedInfoInFooter(TextView userLoggedInfo)
    {
        userLoggedInfo.setTextColor(this.getFooterTextColor());
        userLoggedInfo.setTypeface(this.getTypeface());
        userLoggedInfo.setGravity(Gravity.CENTER_VERTICAL);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        params.addRule(RelativeLayout.CENTER_VERTICAL);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        params.setMargins(this.getMarginLeft(), 0, 0, 0);
        userLoggedInfo.setLayoutParams(params);
    }

    public void setFooter(RelativeLayout footer, Button logout, TextView userInfo)
    {
        footer.setBackgroundColor(this.getFooterBackgroundColor());
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        params.setMargins(0, this.getMarginTop(), 0, 0);
        footer.setLayoutParams(params);

        this.setLogoutButtonInFooter(logout);
        this.setUserLoggedInfoInFooter(userInfo);
    }

    public void setMainBackground(RelativeLayout layout)
    {
        layout.setBackground(this.getBackground());
    }

    public void setMainBackground(ScrollView scrollView)
    {
        scrollView.setBackground(this.getBackground());
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getButtonColor() {
        return buttonColor;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public int getFooterBackgroundColor() {
        return footerBackgroundColor;
    }

    public int getFooterTextColor() {
        return footerTextColor;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public Drawable getBackground() {
        return background;
    }


}
