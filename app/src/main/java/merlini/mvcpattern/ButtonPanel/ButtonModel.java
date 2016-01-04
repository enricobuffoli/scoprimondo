package merlini.mvcpattern.ButtonPanel;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.util.ArrayList;

import merlini.mvcpattern.ActivityMain;
import merlini.mvcpattern.Base.Console;
import merlini.mvcpattern.Base.Model;

/**
 * Created by mattia on 26/11/15.
 */
public class ButtonModel extends Model
{
    ArrayList<Button> buttons;

    public ButtonModel()
    {
        this.buttons = new ArrayList<>();
    }

    public void addButton(Context context, String label, OnClickListener listener)
    {
        Button b = new Button(context);
        b.setText(label);
        b.setOnClickListener(listener);
        b.setId(ActivityMain.ID ++);

        try
        {
            this.buttons.add(b);
            this.fireValuesChange(String.format("Aggiunta bottone [%s]", b.toString()));
        }
        catch (Exception e)
        {
            Console.log(e);
        }
    }

    public ArrayList<Button> getButtons()
    {
        return this.buttons;
    }
}
