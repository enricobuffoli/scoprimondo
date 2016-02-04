package it.mattiamerlini.mvc_scoprimondo.Activities.Registration;


import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.enrico.mvc_scoprimondo.R;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import it.mattiamerlini.mvc_scoprimondo.Activities.Login.LoginActivity;
import it.mattiamerlini.mvc_scoprimondo.Activities.Menu.MenuActivity;
import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Base.User.User;
import it.mattiamerlini.mvc_scoprimondo.Fragments.DatePickerFragment;
import it.mattiamerlini.mvc_scoprimondo.Utilities.ActivityUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.DataUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.NetworkUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.RequestUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.SessionUtility;
import it.mattiamerlini.mvc_scoprimondo.Utilities.UXUtility;

public class RegistrationActivity extends AppCompatActivity
{
    private ScrollView scrollView;
    private RelativeLayout layout;

    private EditText txtName;
    private EditText txtSurname;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtConfirmPassword;
    private EditText txtBirthday;
    private Spinner txtRole;

    private TextView labelName;
    private TextView labelSurname;
    private TextView labelEmail;
    private TextView labelPassword;
    private TextView labelConfirmPassword;
    private TextView labelBirthday;
    private TextView labelRole;

    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        this.scrollView = (ScrollView) findViewById(R.id.scrollView);
        this.layout = (RelativeLayout) findViewById(R.id.layout);

        this.txtName = (EditText) findViewById(R.id.txtName);
        this.txtSurname = (EditText) findViewById(R.id.txtSurname);
        this.txtEmail = (EditText) findViewById(R.id.txtEmail);
        this.txtPassword = (EditText) findViewById(R.id.txtPassword );
        this.txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        this.txtBirthday = (EditText) findViewById(R.id.txtBirthday);
        this.txtRole = (Spinner) findViewById(R.id.txtRole);

        this.labelName = (TextView) findViewById(R.id.labelName);
        this.labelSurname = (TextView) findViewById(R.id.labelSurname);
        this.labelEmail = (TextView) findViewById(R.id.labelEmail);
        this.labelPassword = (TextView) findViewById(R.id.labelPassword);
        this.labelConfirmPassword = (TextView) findViewById(R.id.labelConfirmPassword);
        this.labelBirthday = (TextView) findViewById(R.id.labelBirthday);
        this.labelRole = (TextView) findViewById(R.id.labelRole);

        this.buttonRegister = (Button) findViewById(R.id.buttonRegister);


        this.txtBirthday.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                showDatePicker();
            }
        });
        this.txtBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        this.initComponents();

        this.buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                this.errors = "";

                String name = txtName.getText().toString();
                String surname = txtSurname.getText().toString();
                String email = txtEmail.getText().toString();
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();
                Date birtday = DataUtility.stringToDate(txtBirthday.getText().toString());
                String role = (String) txtRole.getSelectedItem();

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
                        if(this.checkData(name, surname, email, password, confirmPassword, birtday, role))
                        {
                            User newUser = new User(name, surname, email, password, birtday, role);
                            Console.log(newUser);

                            if(RequestUtility.requestUserRegistration(newUser))
                            {
                                Map<String, String> messages = new HashMap<>();
                                messages.put("toastMessage", "Registrazione effettuata, effettua il login!");
                                ActivityUtility.changeActivity(getApplicationContext(), LoginActivity.class, messages);
                            }

                        }
                        else
                        {
                            ActivityUtility.showLongToast(getApplicationContext(), this.errors);
                        }
                    }
                }


            }

            String errors = "";


            private boolean checkData(String name, String surname, String email, String password, String confirmPassword, Date birtday, String role)
            {
                boolean noErrors = true;
                if(!DataUtility.validString(name, 3))
                {
                    this.errors += "Nome ";
                    noErrors = false;
                }

                if(!DataUtility.validString(surname, 3))
                {
                    this.errors += "Cognome ";
                    noErrors = false;
                }

                if(!DataUtility.validateEmail(email))
                {
                    this.errors += "Email ";
                    noErrors = false;
                }

                if(!password.equals(confirmPassword) || !DataUtility.validString(password, 4) || !DataUtility.validString(confirmPassword, 4))
                {
                    this.errors += "Password ";
                    noErrors = false;
                }

                if(birtday == null)
                {
                    this.errors += "Data di nascita ";
                    noErrors = false;
                }

                if(!DataUtility.validString(role, 3))
                {
                    this.errors += "Ruolo ";
                    noErrors = false;
                }

                if (!noErrors)
                    this.errors = "Errori nei seuenti campi:\n" + this.errors;

                return noErrors;
            }
        });
    }

    private void initComponents()
    {
        UXUtility ux = UXUtility.getInstance(this.layout.getContext());

        ux.setMainBackground(this.scrollView);

        ux.setLeftTextView(this.labelName, "Nome", 0);
        ux.setCenteredEditText(this.txtName, "", this.labelName.getId(), InputType.TYPE_CLASS_TEXT);

        ux.setLeftTextView(this.labelSurname, "Cognome", this.txtName.getId());
        ux.setCenteredEditText(this.txtSurname, "", this.labelSurname.getId(), InputType.TYPE_CLASS_TEXT);

        ux.setLeftTextView(this.labelEmail, "Email", this.txtSurname.getId());
        ux.setCenteredEditText(this.txtEmail, "", this.labelEmail.getId(), InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        ux.setLeftTextView(this.labelPassword, "Password", this.txtEmail.getId());
        ux.setCenteredEditText(this.txtPassword, "", this.labelPassword.getId(), InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        ux.setLeftTextView(this.labelConfirmPassword, "Conferma password", this.txtPassword.getId());
        ux.setCenteredEditText(this.txtConfirmPassword, "", this.labelConfirmPassword.getId(), InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        ux.setLeftTextView(this.labelBirthday, "Data di nascita", this.txtConfirmPassword.getId());
        ux.setCenteredEditText(this.txtBirthday, "", this.labelBirthday.getId(), InputType.TYPE_NULL);

        ux.setLeftTextView(this.labelRole, "Ruolo", this.txtBirthday.getId());
        ux.setCenteredSpinner(this.txtRole, new String[]{"Bambino", "Psicologo"}, "Bambino", this.labelRole.getId());

        ux.setCenteredButton(this.buttonRegister, "REGISTRATI", this.txtRole.getId());


    }

    private void showDatePicker()
    {
        DialogFragment newFragment = new DatePickerFragment() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                txtBirthday.setText(String.format("%d-%d-%d", year, month+1, day));
            }
        };
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}
