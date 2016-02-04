package it.mattiamerlini.mvc_scoprimondo.Activities.Menu;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.enrico.mvc_scoprimondo.R;

import java.util.HashMap;
import java.util.Map;

import it.enricobuffoli.mvc_scoprimondo.MainActivity;
import it.mattiamerlini.mvc_scoprimondo.Activities.Login.LoginActivity;
import it.mattiamerlini.mvc_scoprimondo.Base.User.User;
import it.mattiamerlini.mvc_scoprimondo.Utilities.ActivityUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.SessionUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.UXUtility;

public class MenuActivity extends AppCompatActivity
{
    private RelativeLayout layout;
    private ImageView logo;
    private TextView titolo;
    private Button gioca;
    private Button visualizzaScene;
    private Button comeFunziona;
    
    private RelativeLayout footer;
    private Button logout;
    private TextView userLogged;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //Retrieve elements
        this.layout = (RelativeLayout) findViewById(R.id.menu_layout);
        this.logo = (ImageView) findViewById(R.id.logo);
        this.titolo = (TextView) findViewById(R.id.titolo);
        this.gioca = (Button) findViewById(R.id.gioca);
        this.visualizzaScene = (Button) findViewById(R.id.visualizza_scene);
        this.comeFunziona = (Button) findViewById(R.id.come_funziona);
        this.footer = (RelativeLayout) findViewById(R.id.footer);
        this.logout = (Button) findViewById(R.id.logout);
        this.userLogged = (TextView) findViewById(R.id.user_logged);

        this.initComponents();

        this.logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SessionUtility.getInstance(getApplicationContext()).logoutUser();
                ActivityUtility.changeActivity(getApplicationContext(), LoginActivity.class, null);
            }
        });

        this.comeFunziona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility.showLongToast(getApplicationContext(), "Funzione non ancora disponibile!");
            }
        });

        this.visualizzaScene.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility.showLongToast(getApplicationContext(), "Funzione non ancora disponibile!");
            }
        });

        this.gioca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility.changeActivity(getApplicationContext(), MainActivity.class, null);
            }
        });

        User logged = SessionUtility.getInstance(getApplicationContext()).getUserLogged();
        if(logged == null)
        {
            Map<String, String> messages = new HashMap<>();
            messages.put("toastMessage", "Effettua il login prima di giocare!");
            ActivityUtility.changeActivity(this.getApplicationContext(), LoginActivity.class, messages);
        }
        else
            this.userLogged.setText(String.format("Nome: %s %s, Ruolo: %s", logged.getName().toUpperCase(), logged.getSurname().toUpperCase(), logged.getRole().toUpperCase()));






    }

    private void initComponents()
    {
        UXUtility ux = UXUtility.getInstance(this.getApplicationContext());

        ux.setMainBackground(this.layout);
        ux.setLogo(this.logo, 0);
        ux.setTitle(this.titolo, this.logo.getId());
        ux.setCenteredButton(this.gioca, "GIOCA", this.titolo.getId());
        ux.setCenteredButton(this.visualizzaScene, "VISUALIZZA SCENE", this.gioca.getId());
        ux.setCenteredButton(this.comeFunziona, "COME FUNZIONA", this.visualizzaScene.getId());
        ux.setFooter(this.footer, this.logout, this.userLogged);
    }
}
