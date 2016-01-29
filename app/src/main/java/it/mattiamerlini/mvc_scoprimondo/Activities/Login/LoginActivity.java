package it.mattiamerlini.mvc_scoprimondo.Activities.Login;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import it.mattiamerlini.mvc_scoprimondo.Activities.Menu.MenuActivity;
import com.example.enrico.mvc_scoprimondo.R;

import java.util.Map;

import it.mattiamerlini.mvc_scoprimondo.Activities.Registration.RegistrationActivity;
import it.mattiamerlini.mvc_scoprimondo.Base.User.User;
import it.mattiamerlini.mvc_scoprimondo.Utilities.ActivityUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.NetworkUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.RequestUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.SessionUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.UXUtility;

public class LoginActivity extends AppCompatActivity
{
    private RelativeLayout layout;
    private ImageView logo;
    private TextView titolo;

    private TextView labelTxtEmail;
    private EditText txtEmail;
    private TextView labelTxtPsw;
    private EditText txtPsw;
    private Button btnLogin;
    private Button btnRegistrati;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Retrieve elements
        this.layout = (RelativeLayout) findViewById(R.id.login_layout);
        this.logo = (ImageView) findViewById(R.id.logo);
        this.titolo = (TextView) findViewById(R.id.titolo);

        this.labelTxtEmail = (TextView) findViewById(R.id.labelTxtEmail);
        this.txtEmail = (EditText) findViewById(R.id.txtEmail);
        this.labelTxtPsw = (TextView) findViewById(R.id.labelTxtPsw);
        this.txtPsw = (EditText) findViewById(R.id.txtPsw);

        this.btnLogin = (Button) findViewById(R.id.btnLogin);
        this.btnRegistrati = (Button) findViewById(R.id.btnRegistrati);

        this.initComponents();

        Map<String, String> messages = ActivityUtility.retrieveIntentMessages(this, new String[]{"toastMessage"});
        if((messages.get("toastMessage")) != null)
            ActivityUtility.showLongToast(this.getApplicationContext(), messages.get("toastMessage"));



        /**
         * Autologin
         */
        //ActivityUtility.setProperOrientation(this);
        if(SessionUtility.getInstance(getApplicationContext()).isUserLogged() && SessionUtility.getInstance(getApplicationContext()).allDataIsSet())
            ActivityUtility.changeActivity(getApplicationContext(), MenuActivity.class, null);
        else
            SessionUtility.getInstance(getApplicationContext()).logoutUser();

        this.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txtEmail.getText().toString();
                String psw = txtPsw.getText().toString();

                if(!NetworkUtility.isOnline(getApplicationContext()))
                {
                    ActivityUtility.showLongToast(getApplicationContext(), NetworkUtility.ERROR_NOT_CONNECTED);
                }
                else
                {
                    if(!NetworkUtility.isAPIServerReachable())
                    {
                        ActivityUtility.showLongToast(getApplicationContext(), NetworkUtility.ERROR_API_NOT_CONNECTED);
                    }
                    else
                    {
                        User user = null;
                        if ((user = RequestUtility.checkLogin(email, psw)) != null) {
                            SessionUtility.getInstance(getApplicationContext()).loginUser(user);
                            ActivityUtility.changeActivity(getApplicationContext(), MenuActivity.class, null);
                        }
                        else
                        {
                            ActivityUtility.showLongToast(getApplicationContext(), "Login non valido. Se non sei iscritto puoi farlo premendo il pulsante REGISTRATI.");
                        }
                    }
                }
            }
        });

        this.btnRegistrati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtility.changeActivity(getApplicationContext(), RegistrationActivity.class, null);
            }
        });
    }

    private void initComponents()
    {
        UXUtility ux = UXUtility.getInstance(this.getApplicationContext());

        ux.setMainBackground(this.layout);
        ux.setLogo(this.logo, 0);
        ux.setTitle(this.titolo, this.logo.getId());
        ux.setLeftTextView(this.labelTxtEmail, "Email", this.titolo.getId());
        ux.setCenteredEditText(this.txtEmail, "", this.labelTxtEmail.getId(), InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        ux.setLeftTextView(this.labelTxtPsw, "Password", this.txtEmail.getId());
        ux.setCenteredEditText(this.txtPsw, "", this.labelTxtPsw.getId(), InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        ux.setCenteredButton(this.btnLogin, "Login", this.txtPsw.getId());
        ux.setCenteredButton(this.btnRegistrati, "Registrati", this.btnLogin.getId());
    }
}
