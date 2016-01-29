package it.mattiamerlini.mvc_scoprimondo.APIConnector.JSON;

import com.json.generators.JSONGenerator;
import com.json.generators.JsonGeneratorFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import it.mattiamerlini.mvc_scoprimondo.APIConnector.APIConfig;

/**
 * Created by mattia on 03/01/16.
 */
public class JSONEncodeAdapter
{
    private JsonGeneratorFactory factory;
    private JSONGenerator generator;
    private Map jsonMap;


    public JSONEncodeAdapter()
    {
        this.factory = JsonGeneratorFactory.getInstance();
        this.generator = this.factory.newJsonGenerator();
        this.jsonMap = new HashMap<>();
    }

    public void setResponseCode(int code)
    {
        this.jsonMap.put("response code", code);
    }

    private Map setPerson(String nome, String cognome)
    {
        Map m = new HashMap<>();
        m.put("nome", nome);
        m.put("cognome", cognome);
        return m;
    }

    public void setPersons(ArrayList<String> persone)
    {
        ArrayList<Map> array = new ArrayList<>();
        for(String s : persone)
        {
            String[] s1 = s.split(" - ");
            Map map = this.setPerson(s1[0], s1[1]);
            array.add(map);
        }
        this.jsonMap.put("data", array);
    }

    public void setAPICredential()
    {
        this.jsonMap.put("user", APIConfig.USER);
        this.jsonMap.put("password", APIConfig.PASSWORD);
    }

    public void setAction(String action)
    {
        this.jsonMap.put("action", action);
    }

    public void setArgs(Map args)
    {
        this.jsonMap.put("args", args);
    }

    public String getString()
    {
        return this.generator.generateJson(this.jsonMap);
    }
}
