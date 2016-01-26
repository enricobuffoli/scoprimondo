package com.example.enrico.mvc_scoprimondo.ButtonGesture;

import android.widget.ImageButton;

import com.example.enrico.mvc_scoprimondo.Base.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by enrico on 15/01/16.
 */
public class ButtonGestureModel extends Model {
    private ArrayList<ImageButton> buttons;
    private static final int up=0, down=1, top=2, bottom=3, delete=4, mirror=5;
    public ButtonGestureModel()
    {
        buttons = new ArrayList<>();
    }
    public void addButton(ImageButton button)
    {
        buttons.add(button);
    }
    public ArrayList<ImageButton> getButtons()
    {
        return buttons;
    }
    public List<ImageButton> getLevelButtons()
    {
        return (List<ImageButton>) buttons.subList(up,delete);
    }
}
