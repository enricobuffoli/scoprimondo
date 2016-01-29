package it.mattiamerlini.mvc_scoprimondo.Utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import it.mattiamerlini.mvc_scoprimondo.Base.User.User;

/**
 * Created by mattia on 28/01/16.
 */
public class SessionUtility
{
    private static SessionUtility ourInstance = null;
    public static SessionUtility getInstance(Context context) {
        if(ourInstance == null)
            ourInstance = new SessionUtility(context);
        return ourInstance;
    }

    private SharedPreferences preferences;

    private SessionUtility(Context context)
    {
        this.preferences = context.getSharedPreferences("ScoprimondoPref", 0);
    }

    public void loginUser(User user)
    {
        Editor editor = this.preferences.edit();
        editor.putInt("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("surname", user.getSurname());
        editor.putString("email", user.getEmail());
        editor.putString("password", user.getPassword());
        editor.putString("birthday", DataUtility.dateToString(user.getBirthday()));
        editor.putString("role", user.getRole());

        editor.putBoolean("logged", true);

        editor.apply();
    }

    public User getUserLogged()
    {
        if(this.isUserLogged())
        {
            return new User(this.preferences.getInt("id", 0), this.preferences.getString("name", ""), this.preferences.getString("surname", ""), this.preferences.getString("email", ""), this.preferences.getString("password", ""), DataUtility.stringToDate(this.preferences.getString("birthday", "")), this.preferences.getString("role", ""));
        }
        return null;
    }

    public boolean isUserLogged()
    {
        return this.preferences.getBoolean("logged", false);
    }

    public void logoutUser()
    {
        Editor editor = this.preferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean allDataIsSet()
    {
        User user = null;
        return (this.isUserLogged() && (user = this.getUserLogged()) != null && (!user.missingData()));
    }
}
