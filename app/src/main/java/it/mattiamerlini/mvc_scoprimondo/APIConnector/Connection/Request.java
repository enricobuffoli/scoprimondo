package it.mattiamerlini.mvc_scoprimondo.APIConnector.Connection;

import android.os.AsyncTask;

import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Utilities.NetworkUtility;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by mattia on 31/12/15.
 */
public class Request extends AsyncTask<String, Integer, String>
{
    public static final String CONTENT_TYPE_DATA = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_JSON = "application/json";

    private String link;
    private URL url = null;
    private HttpURLConnection conn = null;

    private BufferedWriter writer;
    private BufferedReader reader;

    private String result = null;

    public Request(String link, String contentType)
    {
        this.link = link;

        try
        {
            this.url = new URL(this.link);
            this.conn = (HttpURLConnection) this.url.openConnection();
            this.conn.setDoOutput(true);
            this.conn.setDoInput(true);
            this.conn.setChunkedStreamingMode(0);
            this.conn.setInstanceFollowRedirects(false);
            this.conn.setRequestMethod("POST");
            this.conn.setRequestProperty("Content-Type", contentType);
            this.conn.setRequestProperty("charset", "utf-8");
            this.conn.setUseCaches(false);
        }
        catch (Exception e)
        {
            Console.log(e.getMessage());
        }
    }

    public String query(String params)
    {
        try
        {
            this.conn.connect();

            //Writer to the REST Server
            this.writer = new BufferedWriter(new OutputStreamWriter(this.conn.getOutputStream(), "UTF-8"));
            this.writer.write(params);
            this.writer.flush();

            //Reader from the REST Server
            this.reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            /*String out="";
            String s = "";
            while ((s = this.reader.readLine()) != null) out += s;
            return out;*/

            return this.reader.readLine();
        }
        catch (Exception e)
        {
            Console.log(e);
            return null;
        }
    }

    public String getResult()
    {
        try
        {
            return this.get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected String doInBackground(String... params) {
        return this.query(params[0]);
    }

    @Override
    protected void onPostExecute(String result) {

    }
}
