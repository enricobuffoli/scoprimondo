package it.mattiamerlini.mvc_scoprimondo.Utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.concurrent.ExecutionException;

import it.mattiamerlini.mvc_scoprimondo.APIConnector.APIConfig;
import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;

/**
 * Created by mattia on 28/01/16.
 */
public class NetworkUtility
{
    public static int TYPE_WIFI = 1;
    public static int TYPE_MOBILE = 2;
    public static int TYPE_NOT_CONNECTED = 0;

    //Errors
    public static String ERROR_NOT_CONNECTED = "Non sei connesso ad Internet.\nConnettiti e riprova.";
    public static String ERROR_API_NOT_CONNECTED = "Server Scoprimondo non in linea.\nAttendi e riprova.";


    public static int getConnectivityStatus(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (null != activeNetwork) {
            if(activeNetwork.getType() == ConnectivityManager.TYPE_WIFI)
                return TYPE_WIFI;

            if(activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE)
                return TYPE_MOBILE;
        }
        return TYPE_NOT_CONNECTED;
    }

    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    public static boolean isAPIServerReachable()
    {
        AsyncTask<String, Boolean, Boolean> a = new AsyncTask<String, Boolean, Boolean>() {
            @Override
            protected Boolean doInBackground(String... params) {
                try
                {
                    return InetAddress.getByName(APIConfig.API_IP).isReachable(2000);
                    /*SocketAddress sockaddr = new InetSocketAddress(APIConfig.API_HOST, APIConfig.API_PORT);
                    Socket sock = new Socket();
                    int timeoutMs = 2000;
                    sock.connect(sockaddr, timeoutMs);
                    return true;*/
                }
                catch(Exception e)
                {
                    Console.log(e.getMessage());
                    return false;
                }
            }
        };
        try
        {
            a.execute();
            return a.get();
        }
        catch (Exception e)
        {
            Console.log(e.getMessage());
            return false;
        }

    }
}
