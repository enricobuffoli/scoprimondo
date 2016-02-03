package it.mattiamerlini.mvc_scoprimondo.APIConnector.JSON;

import com.json.parsers.JSONParser;
import com.json.parsers.JsonParserFactory;

import java.util.ArrayList;
import java.util.Map;

import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Base.User.User;
import it.mattiamerlini.mvc_scoprimondo.Utilities.DataUtility;

/**
 * Created by mattia on 30/12/15.
 */
public class JSONDecodeAdapter
{
    JsonParserFactory factory;
    JSONParser parser;
    Map jsonMap;

    public JSONDecodeAdapter(String jsonString)
    {
        this.factory = JsonParserFactory.getInstance();
        this.parser = this.factory.newJsonParser();
        Console.log(jsonString);
        this.jsonMap = this.parser.parseJson(jsonString);
    }

    public int getResponseCode()
    {
        String parsedValue = (String) this.jsonMap.get("Response code");
        return Integer.parseInt(parsedValue);
    }

    public String getPerson(Map persona)
    {
        String out = "";
        String nome = (String) persona.get("nome");
        String cognome = (String) persona.get("cognome");
        out += String.format("%s - %s\n", nome, cognome);
        return out;
    }

    public String getPersons()
    {
        Object parsedValue = (Object) this.jsonMap.get("Data");

        if(parsedValue instanceof ArrayList)
        {
            ArrayList<Map> mapList = (ArrayList<Map>) parsedValue;
            String out = "";
            for(Map m : mapList)
            {
                out += this.getPerson(m);
            }
            return out;
        }
        else
        {
            return this.getPerson((Map) parsedValue);
        }
    }

    private ArrayList<Map> getData()
    {
        return  (ArrayList<Map>) this.jsonMap.get("Data");
    }

    public User getUser()
    {
        ArrayList<Map> users = this.getData();
        for(Map<String, String> userInfo : users)
        {
            return new User(Integer.parseInt((String) userInfo.get("ID")), (String) userInfo.get("Nome"), (String) userInfo.get("Cognome"), (String) userInfo.get("email"), (String) userInfo.get("Password"), DataUtility.stringToDate(userInfo.get("Data_nascita")), (String) userInfo.get("Ruolo"));
        }
            return null;
    }
}
