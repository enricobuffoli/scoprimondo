package it.mattiamerlini.mvc_scoprimondo.Utilities;

import java.util.HashMap;
import java.util.Map;

import it.enricobuffoli.mvc_scoprimondo.ButtonGesture.ButtonGestureView;
import it.mattiamerlini.mvc_scoprimondo.APIConnector.APIConfig;
import it.mattiamerlini.mvc_scoprimondo.APIConnector.Connection.Request;
import it.mattiamerlini.mvc_scoprimondo.APIConnector.JSON.JSONDecodeAdapter;
import it.mattiamerlini.mvc_scoprimondo.APIConnector.JSON.JSONEncodeAdapter;
import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Base.User.User;

/**
 * Created by mattia on 28/01/16.
 */
public class RequestUtility
{
    /*
    * Array di azioni basato sugli indici delle tab.
    * Lo 0 e il 6 non sono gestiti perchè tab fasulle (Home e Fine).
    */
    public final static String[] SAVE_ACTIONS = new String[] {"", "INSERT MADRE NATURA", "INSERT MADRE TERRA", "INSERT TERRA PADRI", "INSERT MADRE PATRIA", "INSERT TERRA FRONTIERA", ""};

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

    public static boolean requestButtonGestureViewSave(ButtonGestureView buttonGestureView, int index)
    {
        //Estrai dati dal button gesture
        String immagine = "testimmagine";
        String email = SessionUtility.getInstance(null).getUserLogged().getEmail();

        Map args = new HashMap();
        args.put("immagine", immagine);
        args.put("email", email);

        JSONEncodeAdapter encoder = new JSONEncodeAdapter();
        encoder.setAPICredential();
        encoder.setAction(RequestUtility.SAVE_ACTIONS[index]);
        encoder.setArgs(args);
        String query = encoder.getString();

        Request post = new Request(APIConfig.API_HOST, Request.CONTENT_TYPE_JSON);
        post.execute(query);
        String response = post.getResult();

        Console.log(String.format("Richiedo di salvare %d. Richiesta -> [%s] Risposta -> [%s]", index, query, response));

        JSONDecodeAdapter decoder = new JSONDecodeAdapter(response);

        if(decoder.getResponseCode() == 200)
            return true;
        return false;
    }
}
