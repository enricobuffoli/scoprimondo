package it.enricobuffoli.mvc_scoprimondo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import it.enricobuffoli.mvc_scoprimondo.ButtonGesture.ButtonGestureView;
import it.enricobuffoli.mvc_scoprimondo.ImageMotion.ImageMotionModel;
import it.mattiamerlini.mvc_scoprimondo.Utilities.UXUtility;
import it.mattiamerlini.mvc_scoprimondo.Views.TabHost.Impl.TabHostImpl;


import com.example.enrico.mvc_scoprimondo.R;

public class MainActivity extends AppCompatActivity
{
    private TabHostImpl tabHost;

    private ButtonGestureView madreNatura;
    private ButtonGestureView madreTerra;
    private ButtonGestureView terraPadri;
    private ButtonGestureView madrePatria;
    private ButtonGestureView terraFrontiera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve elements
        this.tabHost = (TabHostImpl) findViewById(R.id.tabHost);

        this.madreNatura = (ButtonGestureView) findViewById(R.id.button_gesture_tab_2);
        this.madreTerra = (ButtonGestureView) findViewById(R.id.button_gesture_tab_3);
        this.terraPadri = (ButtonGestureView) findViewById(R.id.button_gesture_tab_4);
        this.madrePatria = (ButtonGestureView) findViewById(R.id.button_gesture_tab_5);
        this.terraFrontiera = (ButtonGestureView) findViewById(R.id.button_gesture_tab_6);

        //Init ButtonGestureViews
        this.madreNatura.init(R.id.image_motion_view_tab_2);
        this.madreTerra.init(R.id.image_motion_view_tab_3);
        this.terraPadri.init(R.id.image_motion_view_tab_4);
        this.madrePatria.init(R.id.image_motion_view_tab_5);
        this.terraFrontiera.init(R.id.image_motion_view_tab_6);

        this.tabHost.setup(this);
        this.initComponents();
        this.tabHost.setCurrentTab(1);
    }

    private void initComponents() {
        UXUtility ux = UXUtility.getInstance(this.getApplicationContext());

        ux.setMainBackground(this.tabHost);

        ux.addTabToTabHost(this.tabHost, "HOME", "HOME", R.id.tab1, null, 0);
        ux.addTabToTabHost(this.tabHost, "MADRE NATURA", "MADRE NATURA", R.id.tab2, this.madreNatura, 1);
        ux.addTabToTabHost(this.tabHost, "MADRE TERRA", "MADRE TERRA", R.id.tab3, this.madreTerra, 2);
        ux.addTabToTabHost(this.tabHost, "TERRA PADRI", "TERRA PADRI", R.id.tab4, this.terraPadri, 3);
        ux.addTabToTabHost(this.tabHost, "MADRE PATRIA", "MADRE PATRIA", R.id.tab5, this.madrePatria, 4);
        ux.addTabToTabHost(this.tabHost, "TERRA FRONTIERA", "TERRA FRONTIERA", R.id.tab6, this.terraFrontiera, 5);
        ux.addTabToTabHost(this.tabHost, "FINE", "FINE", R.id.tab7, null, 6);

        ux.styleTabHostIndicators(this.tabHost);
    }
}
