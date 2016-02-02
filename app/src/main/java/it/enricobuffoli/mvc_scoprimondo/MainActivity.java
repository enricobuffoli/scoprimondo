package it.enricobuffoli.mvc_scoprimondo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import it.enricobuffoli.mvc_scoprimondo.ButtonGesture.ButtonGestureView;
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
        this.madreNatura = (ButtonGestureView) findViewById(R.id.button_gesture);
        this.tabHost = (TabHostImpl) findViewById(R.id.tabHost);

        /* Inizializzazioni di prova, vanno in realtà pescate dall'XML */
        this.madreTerra = new ButtonGestureView(this.getApplicationContext());
        this.terraPadri = new ButtonGestureView(this.getApplicationContext());
        this.madrePatria = new ButtonGestureView(this.getApplicationContext());
        this.terraFrontiera = new ButtonGestureView(this.getApplicationContext());

        this.madreNatura.init();
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
    }
}
