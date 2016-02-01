package it.mattiamerlini.mvc_scoprimondo.Utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.enricobuffoli.mvc_scoprimondo.ImageMotion.ImageMotionView;
import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;

/**
 * Created by mattia on 28/01/16.
 */
public class DataUtility
{
    public static final String[] INVALID_STRINGS = new String[] {" ", "prova", "test", "admin"};
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static Date stringToDate(String string)
    {
        try
        {
            return DataUtility.format.parse(string);
        }
        catch (Exception e)
        {
            Console.log(e.getMessage());
            return null;
        }
    }

    public static String dateToString(Date date)
    {
        try
        {
            return DataUtility.format.format(date);
        }
        catch (Exception e)
        {
            Console.log(e.getMessage());
            return null;
        }
    }

    public static boolean validateEmail(String email)
    {
        email = email.toUpperCase();
        Matcher matcher = DataUtility.VALID_EMAIL_ADDRESS_REGEX .matcher(email);
        return matcher.find();
    }

    public static boolean validString(String s, int minLength)
    {
        if(s.length() < minLength)
            return false;
        for (String not : DataUtility.INVALID_STRINGS)
        {
            if(s.equals(not))
                return false;
        }
        return true;
    }

    public static boolean isImageWellFormed(boolean b)
    {
        return b;
    }
}
