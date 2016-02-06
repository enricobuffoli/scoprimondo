package it.enricobuffoli.mvc_scoprimondo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import it.enricobuffoli.mvc_scoprimondo.ButtonGesture.ButtonGestureView;
import it.mattiamerlini.mvc_scoprimondo.Activities.Login.LoginActivity;
import it.mattiamerlini.mvc_scoprimondo.Fragments.ViewDialogFragment;
import it.mattiamerlini.mvc_scoprimondo.Utilities.ActivityUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.SessionUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.UXUtility;
import it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner.ImageSpinnerItem;
import it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner.ImageSpinnerModel;
import it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner.ImageSpinnerView;
import it.mattiamerlini.mvc_scoprimondo.Views.TabHost.Impl.TabHostImpl;


import com.example.enrico.mvc_scoprimondo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

public class MainActivity extends AppCompatActivity
{
    private TabHostImpl tabHost;

    private ButtonGestureView buttonGestureMadreNatura;
    private ImageSpinnerView imageSpinnerMadreNatura;
    private Button showDialogSpinnerMadreNatura;

    private ButtonGestureView buttonGestureMadreTerra;
    private ImageSpinnerView imageSpinnerMadreTerra;
    private Button showDialogSpinnerMadreTerra;

    private ButtonGestureView buttonGestureTerraPadri;
    private ImageSpinnerView imageSpinnerTerraPadri;
    private Button showDialogSpinnerTerraPadri;

    private ButtonGestureView buttonGestureMadrePatria;
    private ImageSpinnerView imageSpinnerMadrePatria;
    private Button showDialogSpinnerMadrePatria;

    private ButtonGestureView buttonGestureTerraFrontiera;
    private ImageSpinnerView imageSpinnerTerraFrontiera;
    private Button showDialogSpinnerTerraFrontiera;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(SessionUtility.getInstance(getApplicationContext()).getUserLogged() == null)
        {
            Map<String, String> messages = new HashMap<>();
            messages.put("toastMessage", "Effettua il login prima di giocare!");
            ActivityUtility.changeActivity(this.getApplicationContext(), LoginActivity.class, messages);
        }

        //Retrieve elements
        this.tabHost = (TabHostImpl) findViewById(R.id.tabHost);

        this.buttonGestureMadreNatura = (ButtonGestureView) findViewById(R.id.button_gesture_tab_2);
        this.buttonGestureMadreTerra = (ButtonGestureView) findViewById(R.id.button_gesture_tab_3);
        this.buttonGestureTerraPadri = (ButtonGestureView) findViewById(R.id.button_gesture_tab_4);
        this.buttonGestureMadrePatria = (ButtonGestureView) findViewById(R.id.button_gesture_tab_5);
        this.buttonGestureTerraFrontiera = (ButtonGestureView) findViewById(R.id.button_gesture_tab_6);

        this.showDialogSpinnerMadreNatura = (Button) findViewById(R.id.showDialogSpinnerMadreNatura);
        this.showDialogSpinnerMadreTerra = (Button) findViewById(R.id.showDialogSpinnerMadreTerra);
        this.showDialogSpinnerTerraPadri = (Button) findViewById(R.id.showDialogSpinnerTerraPadri);
        this.showDialogSpinnerMadrePatria = (Button) findViewById(R.id.showDialogSpinnerMadrePatria);
        this.showDialogSpinnerTerraFrontiera = (Button) findViewById(R.id.showDialogSpinnerTerraFrontiera);


        //Init ButtonGestureViews
        this.buttonGestureMadreNatura.init(R.id.image_motion_view_tab_2);
        this.buttonGestureMadreTerra.init(R.id.image_motion_view_tab_3);
        this.buttonGestureTerraPadri.init(R.id.image_motion_view_tab_4);
        this.buttonGestureMadrePatria.init(R.id.image_motion_view_tab_5);
        this.buttonGestureTerraFrontiera.init(R.id.image_motion_view_tab_6);

        this.tabHost.setup(this);
        this.initComponents();
        this.tabHost.setCurrentTab(1);

        //Set OnClickEvents
        this.setOnClicks();
    }


    private void initComponents() {
        UXUtility ux = UXUtility.getInstance(this.getApplicationContext());

        ux.setMainBackground(this.tabHost);

        ux.addTabToTabHost(this.tabHost, "HOME", "HOME", R.id.tab1, null, 0);
        ux.addTabToTabHost(this.tabHost, "MADRE NATURA", "MADRE NATURA", R.id.tab2, this.buttonGestureMadreNatura, 1);
        ux.addTabToTabHost(this.tabHost, "MADRE TERRA", "MADRE TERRA", R.id.tab3, this.buttonGestureMadreTerra, 2);
        ux.addTabToTabHost(this.tabHost, "TERRA PADRI", "TERRA PADRI", R.id.tab4, this.buttonGestureTerraPadri, 3);
        ux.addTabToTabHost(this.tabHost, "MADRE PATRIA", "MADRE PATRIA", R.id.tab5, this.buttonGestureMadrePatria, 4);
        ux.addTabToTabHost(this.tabHost, "TERRA FRONTIERA", "TERRA FRONTIERA", R.id.tab6, this.buttonGestureTerraFrontiera, 5);
        ux.addTabToTabHost(this.tabHost, "FINE", "FINE", R.id.tab7, null, 6);

        ux.styleButton(this.showDialogSpinnerMadreNatura, "+", 50);
        ux.styleButton(this.showDialogSpinnerMadreTerra, "+", 50);
        ux.styleButton(this.showDialogSpinnerTerraPadri, "+", 50);
        ux.styleButton(this.showDialogSpinnerMadrePatria, "+", 50);
        ux.styleButton(this.showDialogSpinnerTerraFrontiera, "+", 50);

        ux.styleTabHostIndicators(this.tabHost);
    }

    private void setOnClicks()
    {
        this.showDialogSpinnerMadreNatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.imageSpinnerMadreNatura = new ImageSpinnerView(MainActivity.this.getApplicationContext());
                MainActivity.this.imageSpinnerMadreNatura.init(MainActivity.this, ImageSpinnerModel.IMAGE_SPINNER_ITEMS_MADRE_NATURA);

                ActivityUtility.showViewDialog(MainActivity.this, MainActivity.this.imageSpinnerMadreNatura, "Ok, inserisci!", "Annulla", new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        int drawableSelected = MainActivity.this.imageSpinnerMadreNatura.getDrawableSelected();
                        MainActivity.this.buttonGestureMadreNatura.addImage(drawableSelected);
                        return null;
                    }
                }, null);
            }
        });

        this.showDialogSpinnerMadreTerra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.imageSpinnerMadreTerra = new ImageSpinnerView(MainActivity.this.getApplicationContext());
                MainActivity.this.imageSpinnerMadreTerra.init(MainActivity.this, ImageSpinnerModel.IMAGE_SPINNER_ITEMS_MADRE_TERRA);

                ActivityUtility.showViewDialog(MainActivity.this, MainActivity.this.imageSpinnerMadreTerra, "Ok, inserisci!", "Annulla", new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        int drawableSelected = MainActivity.this.imageSpinnerMadreTerra.getDrawableSelected();
                        MainActivity.this.buttonGestureMadreTerra.addImage(drawableSelected);
                        return null;
                    }
                }, null);
            }
        });

        this.showDialogSpinnerTerraPadri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.imageSpinnerTerraPadri = new ImageSpinnerView(MainActivity.this.getApplicationContext());
                MainActivity.this.imageSpinnerTerraPadri.init(MainActivity.this, ImageSpinnerModel.IMAGE_SPINNER_ITEMS_TERRA_PADRI);

                ActivityUtility.showViewDialog(MainActivity.this, MainActivity.this.imageSpinnerTerraPadri, "Ok, inserisci!", "Annulla", new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        int drawableSelected = MainActivity.this.imageSpinnerTerraPadri.getDrawableSelected();
                        MainActivity.this.buttonGestureTerraPadri.addImage(drawableSelected);
                        return null;
                    }
                }, null);
            }
        });

        this.showDialogSpinnerMadrePatria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.imageSpinnerMadrePatria = new ImageSpinnerView(MainActivity.this.getApplicationContext());
                MainActivity.this.imageSpinnerMadrePatria.init(MainActivity.this, ImageSpinnerModel.IMAGE_SPINNER_ITEMS_MADRE_PATRIA);

                ActivityUtility.showViewDialog(MainActivity.this, MainActivity.this.imageSpinnerMadrePatria, "Ok, inserisci!", "Annulla", new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        int drawableSelected = MainActivity.this.imageSpinnerMadrePatria.getDrawableSelected();
                        MainActivity.this.buttonGestureMadrePatria.addImage(drawableSelected);
                        return null;
                    }
                }, null);
            }
        });

        this.showDialogSpinnerTerraFrontiera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.imageSpinnerTerraFrontiera = new ImageSpinnerView(MainActivity.this.getApplicationContext());
                MainActivity.this.imageSpinnerTerraFrontiera.init(MainActivity.this, ImageSpinnerModel.IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA);

                ActivityUtility.showViewDialog(MainActivity.this, MainActivity.this.imageSpinnerTerraFrontiera, "Ok, inserisci!", "Annulla", new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        int drawableSelected = MainActivity.this.imageSpinnerTerraFrontiera.getDrawableSelected();
                        MainActivity.this.buttonGestureTerraFrontiera.addImage(drawableSelected);
                        return null;
                    }
                }, null);
            }
        });
    }
}
