package com.example.enrico.gestioneimmagini;

import com.example.enrico.gestioneimmagini.ImageClasses.*;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;


import android.widget.*;
import android.util.*;

public class MainActivity extends AppCompatActivity {

        private TextView t;
        int drawable;
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.content_main);
            CustomView myView =(CustomView) findViewById(R.id.parent_view);
            Image view1 = (Image) findViewById(R.id.imageView1);
            //myView.addImage(view1);
            //Image view2 = (Image) findViewById(R.id.imageView2);



            t=(TextView) findViewById(R.id.textView1);


        }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
