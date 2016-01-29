package it.mattiamerlini.mvc_scoprimondo.Activities.TestGioca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.enrico.mvc_scoprimondo.R;

import it.enricobuffoli.mvc_scoprimondo.ButtonGesture.ButtonGestureView;
import it.enricobuffoli.mvc_scoprimondo.ImageMotion.ImageMotionView;

public class TestGiocaActivity extends AppCompatActivity
{
    private ButtonGestureView buttonGestureView;
    private ImageMotionView imageMotionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_gioca);

        //Retrieve elements
        this.buttonGestureView = (ButtonGestureView) findViewById(R.id.view);
        this.imageMotionView = (ImageMotionView) findViewById(R.id.view2);

        this.buttonGestureView.init();
    }
}
