package it.mattiamerlini.mvc_scoprimondo.Utilities;

import java.util.HashMap;
import java.util.Map;

import it.mattiamerlini.mvc_scoprimondo.APIConnector.APIConfig;
import it.mattiamerlini.mvc_scoprimondo.APIConnector.Connection.Request;
import it.mattiamerlini.mvc_scoprimondo.APIConnector.JSON.JSONDecodeAdapter;
import it.mattiamerlini.mvc_scoprimondo.APIConnector.JSON.JSONEncodeAdapter;
import it.mattiamerlini.mvc_scoprimondo.Base.User.User;

/**
 * Created by mattia on 28/01/16.
 */
public class RequestUtility
{
    public static User checkLogin(String email, String password)
    {
        Map args = new HashMap();
        args.put("email", email);
        args.put("password", password);

        JSONEncodeAdapter encoder = new JSONEncodeAdapter();
        encoder.setAPICredential();
        encoder.setAction("CHECK UTENTE");
        encoder.setArgs(args);
        String query = encoder.getString();

        Request post = new Request(APIConfig.API_HOST, Request.CONTENT_TYPE_JSON);
        post.execute(query);
        String response = post.getResult();

        JSONDecodeAdapter decoder = new JSONDecodeAdapter(response);

        if(decoder.getResponseCode() == 200)
            return decoder.getUser();
        return null;
    }

    public static boolean requestUserRegistration(User user)
    {
        Map args = new HashMap();
        args.put("nome", user.getName());
        args.put("cognome", user.getSurname());
        args.put("email", user.getEmail());
        args.put("password", user.getPassword());
        args.put("data_nascita", DataUtility.dateToString(user.getBirthday()));
        args.put("ruolo", user.getRole());

        JSONEncodeAdapter encoder = new JSONEncodeAdapter();
        encoder.setAPICredential();
        encoder.setAction("INSERT UTENTE");
        encoder.setArgs(args);
        String query = encoder.getString();

        Request post = new Request(APIConfig.API_HOST, Request.CONTENT_TYPE_JSON);
        post.execute(query);
        String response = post.getResult();

        JSONDecodeAdapter decoder = new JSONDecodeAdapter(response);

        if(decoder.getResponseCode() == 200)
            return true;
        return false;
    }
}
