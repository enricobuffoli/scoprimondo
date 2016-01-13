package com.example.enrico.gestioneimmagini;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import com.example.enrico.gestioneimmagini.LayoutGestures.*;
import com.example.enrico.gestioneimmagini.LayoutGestures.ImageMotionView;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonGestureView prova =(ButtonGestureView) findViewById(R.id.button_gesture);
        prova.init();
        prova.addImage(R.mipmap.ciao);
        prova.addImage(R.mipmap.ciao2);
/*
        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Tab1");
        tab1.setContent(new Intent(this, ScoprimondoTabActivity.class));

        tab2.setIndicator("Tab2");

        tab3.setIndicator("Tab3");

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);
*/
    }
}
