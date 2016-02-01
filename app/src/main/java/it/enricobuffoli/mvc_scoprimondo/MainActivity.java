package it.enricobuffoli.mvc_scoprimondo;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import android.widget.TabHost.*;

import it.enricobuffoli.mvc_scoprimondo.ButtonGesture.ButtonGestureView;
import it.mattiamerlini.mvc_scoprimondo.Views.Impl.TabHostImpl;


import com.example.enrico.mvc_scoprimondo.R;

public class MainActivity extends AppCompatActivity
{
    private TabHostImpl tabHost;
    private String tab1 = "tab1";
    private String tab2 = "tab2";
    private String tab3 = "tab3";
    private String tab4 = "tab4";
    private String tab5 = "tab5";
    private String tab6 = "tab6";

    private String prePreviusTab = this.tab2;
    private String previusTab = this.tab2;
    private String currentTab = this.tab2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonGestureView buttonGestureView=(ButtonGestureView) findViewById(R.id.button_gesture);
        buttonGestureView.init();

        //Retrieve elements
        this.tabHost = (TabHostImpl) findViewById(R.id.tabHost);
        this.tabHost.setup(this);

        this.tabHost.setBackground(ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.sfondo));

        TabSpec spec = null;

        spec = this.tabHost.newTabSpec(this.tab1);
        spec.setIndicator("HOME", null); //ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.logo)));
        spec.setContent(R.id.tab1);
        this.tabHost.addTab(spec);

        spec = this.tabHost.newTabSpec(this.tab2);
        spec.setIndicator("MADRE\nNATURA", null); //ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.logo)));
        spec.setContent(R.id.tab2);
        this.tabHost.addTab(spec);

        spec = this.tabHost.newTabSpec(this.tab3);
        spec.setIndicator("MADRE\nTERRA", null); //ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.logo)));
        spec.setContent(R.id.tab3);
        this.tabHost.addTab(spec);

        spec = this.tabHost.newTabSpec(this.tab4);
        spec.setIndicator("TERRA\nPADRI", null); //ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.logo)));
        spec.setContent(R.id.tab4);
        this.tabHost.addTab(spec);

        spec = this.tabHost.newTabSpec(this.tab5);
        spec.setIndicator("MADRE\nPATRIA", null); //ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.logo)));
        spec.setContent(R.id.tab5);
        this.tabHost.addTab(spec);

        spec = this.tabHost.newTabSpec(this.tab6);
        spec.setIndicator("TERRA\nFRONTIERA", null); //ContextCompat.getDrawable(this.getApplicationContext(), R.drawable.logo)));
        spec.setContent(R.id.tab6);
        this.tabHost.addTab(spec);

        this.tabHost.setCurrentTab(1);


                /*
                if(current.equals(tab2))
                {
                    if(clicked.equals(tab1))
                    {
                        //Exit the game
                        AlertDialogFragment dialog = new AlertDialogFragment("Attenzione", "Sei sicuro di voler abbandonare?\nI dati andranno persi.", "Sì, abbandona!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which) {

                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which) {
                                ActivityUtility.changeActivity(getApplicationContext(), MenuActivity.class, null);
                            }
                        };
                        dialog.show(getSupportFragmentManager(), "alert");
                    }
                    if(clicked.equals(tab3))
                    {
                        //Next step
                        AlertDialogFragment dialog = new AlertDialogFragment("Attenzione", "Hai fatto tutto?", "Sì, vai avanti!", "No, resta qui!") {
                            @Override
                            protected void onNegativeClick(DialogInterface dialog, int which)
                            {
                                tabHost.setCurrentTabByTag(previusTab);
                            }

                            @Override
                            protected void onPositiveClick(DialogInterface dialog, int which) {
                            }
                        };
                        dialog.show(getSupportFragmentManager(), "alert");
                    }
                    else
                    {
                        tabHost.setCurrentTabByTag(tab2);
                    }
                }
                */


    }

    public void setTabHostRules(TabHostImpl t, View.OnTouchListener onTouchListener)
    {


    }
}
