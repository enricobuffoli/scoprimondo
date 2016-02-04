package it.mattiamerlini.mvc_scoprimondo.Views.ImageSpinner;

import android.content.Context;

import com.example.enrico.mvc_scoprimondo.R;

import java.util.ArrayList;

import it.mattiamerlini.mvc_scoprimondo.Base.Console.Console;
import it.mattiamerlini.mvc_scoprimondo.Base.Model.Model;

/**
 * Created by mattia on 04/02/16.
 */
public class ImageSpinnerModel extends Model
{
    public static ImageSpinnerModel instance = null;
    private ImageSpinnerModel(Context context)
    {
        this.context = context;
        this.items = new ArrayList<>();


        IMAGE_SPINNER_ITEMS_MADRE_NATURA = new ArrayList<>();
        IMAGE_SPINNER_ITEMS_MADRE_TERRA = new ArrayList<>();
        IMAGE_SPINNER_ITEMS_TERRA_PADRI = new ArrayList<>();
        IMAGE_SPINNER_ITEMS_MADRE_PATRIA = new ArrayList<>();
        IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA = new ArrayList<>();

        IMAGE_SPINNER_ITEMS_MADRE_NATURA_REQUIRED = new ArrayList<>();
        IMAGE_SPINNER_ITEMS_MADRE_TERRA_REQUIRED = new ArrayList<>();
        IMAGE_SPINNER_ITEMS_TERRA_PADRI_REQUIRED = new ArrayList<>();
        IMAGE_SPINNER_ITEMS_MADRE_PATRIA_REQUIRED = new ArrayList<>();
        IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA_REQUIRED = new ArrayList<>();

        /*
        * Inserimento immagini MADRE NATURA
        */
        IMAGE_SPINNER_ITEMS_MADRE_NATURA.add(new ImageSpinnerItem(this.context, R.drawable.cieloazzurro, "Cielo Azzurro"));
        IMAGE_SPINNER_ITEMS_MADRE_NATURA.add(new ImageSpinnerItem(this.context, R.drawable.cielonotturno, "Cielo Notturno"));
        IMAGE_SPINNER_ITEMS_MADRE_NATURA.add(new ImageSpinnerItem(this.context, R.drawable.cielonotturno2, "Cielo Notturno"));
        IMAGE_SPINNER_ITEMS_MADRE_NATURA.add(new ImageSpinnerItem(this.context, R.drawable.albero, "Albero"));
        IMAGE_SPINNER_ITEMS_MADRE_NATURA.add(new ImageSpinnerItem(this.context, R.drawable.sole, "Sole"));

        /*
        * Inserimento immagini MADRE TERRA
        */
        IMAGE_SPINNER_ITEMS_MADRE_TERRA.add(new ImageSpinnerItem(this.context, R.drawable.terreno, "Terreno"));
        IMAGE_SPINNER_ITEMS_MADRE_TERRA.add(new ImageSpinnerItem(this.context, R.drawable.terreno2, "Terreno"));
        IMAGE_SPINNER_ITEMS_MADRE_TERRA.add(new ImageSpinnerItem(this.context, R.drawable.bimbaasiatica, "Bimba"));
        IMAGE_SPINNER_ITEMS_MADRE_TERRA.add(new ImageSpinnerItem(this.context, R.drawable.bimbacaucasica, "Bimba"));
        IMAGE_SPINNER_ITEMS_MADRE_TERRA.add(new ImageSpinnerItem(this.context, R.drawable.bimbaindiana, "Bimba"));

        /*
        * Inserimento immagini TERRA PADRI
        */
        IMAGE_SPINNER_ITEMS_TERRA_PADRI.add(new ImageSpinnerItem(this.context, R.drawable.uomoprimitivo, "Uomo primitivo"));
        IMAGE_SPINNER_ITEMS_TERRA_PADRI.add(new ImageSpinnerItem(this.context, R.drawable.trex, "Animale preistorico"));
        IMAGE_SPINNER_ITEMS_TERRA_PADRI.add(new ImageSpinnerItem(this.context, R.drawable.tempio_indu, "Tempio Indu"));
        IMAGE_SPINNER_ITEMS_TERRA_PADRI.add(new ImageSpinnerItem(this.context, R.drawable.cartucciera, "Cartucciera"));
        IMAGE_SPINNER_ITEMS_TERRA_PADRI.add(new ImageSpinnerItem(this.context, R.drawable.fasciasudore, "Fascia sudore"));

        /*
        * Inserimento immagini MADRE PATRIA
        */
        IMAGE_SPINNER_ITEMS_MADRE_PATRIA.add(new ImageSpinnerItem(this.context, R.drawable.giubbablu, "Giubba blu"));
        IMAGE_SPINNER_ITEMS_MADRE_PATRIA.add(new ImageSpinnerItem(this.context, R.drawable.vigile_urbano, "Vigile urbano"));
        IMAGE_SPINNER_ITEMS_MADRE_PATRIA.add(new ImageSpinnerItem(this.context, R.drawable.poliziotto, "Poliziotto"));
        IMAGE_SPINNER_ITEMS_MADRE_PATRIA.add(new ImageSpinnerItem(this.context, R.drawable.pompiere, "Pompiere"));
        IMAGE_SPINNER_ITEMS_MADRE_PATRIA.add(new ImageSpinnerItem(this.context, R.drawable.macchina_polizia, "Macchina polizia"));

        /*
        * Inserimento immagini TERRA FRONTIERA
        */
        IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA.add(new ImageSpinnerItem(this.context, R.drawable.bimbonero, "Bimbo"));
        IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA.add(new ImageSpinnerItem(this.context, R.drawable.uomonero, "Uomo"));
        IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA.add(new ImageSpinnerItem(this.context, R.drawable.nave_guerra1, "Nave guerra"));
        IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA.add(new ImageSpinnerItem(this.context, R.drawable.elmetto, "Elmetto"));
        IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA.add(new ImageSpinnerItem(this.context, R.drawable.bracciodestroteso, "Braccio destro"));

        for(ImageSpinnerItem item : IMAGE_SPINNER_ITEMS_MADRE_NATURA)
            this.addItem(item);
        for(ImageSpinnerItem item : IMAGE_SPINNER_ITEMS_MADRE_TERRA)
            this.addItem(item);
        for(ImageSpinnerItem item : IMAGE_SPINNER_ITEMS_TERRA_PADRI)
            this.addItem(item);
        for(ImageSpinnerItem item : IMAGE_SPINNER_ITEMS_MADRE_PATRIA)
            this.addItem(item);
        for(ImageSpinnerItem item : IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA)
            this.addItem(item);
    }
    public static ImageSpinnerModel getInstance(Context context)
    {
        if(instance == null)
            instance = new ImageSpinnerModel(context);

        return instance;
    }

    private ArrayList<ImageSpinnerItem> items;
    private Context context;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_MADRE_NATURA;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_MADRE_TERRA;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_TERRA_PADRI;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_MADRE_PATRIA;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA;

    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_MADRE_NATURA_REQUIRED;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_MADRE_TERRA_REQUIRED;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_TERRA_PADRI_REQUIRED;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_MADRE_PATRIA_REQUIRED;
    public static ArrayList<ImageSpinnerItem> IMAGE_SPINNER_ITEMS_TERRA_FRONTIERA_REQUIRED;


    public void addItem(ImageSpinnerItem item)
    {
        try
        {
            if(!this.isAlredyIn(item))
            {
                this.items.add(item);
                this.fireValuesChange();
            }
        }
        catch (Exception e)
        {
            Console.log(e.getMessage());
        }
    }

    public boolean isAlredyIn(ImageSpinnerItem item)
    {
        for(ImageSpinnerItem i : this.items)
        {
            if(i.getImageId() == item.getImageId())
                return true;
        }
        return false;
    }

    public ImageSpinnerItem getItem(int position)
    {
        try
        {
            return this.items.get(position);
        }
        catch (Exception e)
        {
            Console.log(e.getMessage());
            return null;
        }
    }

    /* Get Items */
    public ArrayList<ImageSpinnerItem> getItems()
    {
        return this.items;
    }
    public ArrayList<ImageSpinnerItem> getItems(ArrayList<ImageSpinnerItem> restrictions)
    {
        if(restrictions == null) return this.getItems();

        ArrayList<ImageSpinnerItem> restrictedItems = new ArrayList<>();
        for(ImageSpinnerItem item : this.getItems())
        {
            for(ImageSpinnerItem restriction : restrictions)
            {
                if(item.getImageId() == restriction.getImageId())
                    restrictedItems.add(item);
            }
        }
        return restrictedItems;
    }

    /* Get Images Ids */
    public ArrayList<Integer> getImagesIds()
    {
        ArrayList<Integer> imagesIds = new ArrayList<>();
        for(ImageSpinnerItem item : this.getItems())
        {
            imagesIds.add(item.getImageId());
        }
        return imagesIds;
    }
    public ArrayList<Integer> getImagesIds(ArrayList<ImageSpinnerItem> restrictions)
    {
        if(restrictions == null) return this.getImagesIds();

        ArrayList<Integer> imagesIds = new ArrayList<>();
        for(ImageSpinnerItem item : this.getItems(restrictions))
        {
            imagesIds.add(item.getImageId());
        }
        return imagesIds;
    }

    /* Get Images Texts */
    public ArrayList<String> getImagesTexts()
    {
        ArrayList<String> imagesTexts = new ArrayList<>();
        for(ImageSpinnerItem item : this.getItems())
        {
            imagesTexts.add(item.getImageText());
        }
        return imagesTexts;
    }
    public ArrayList<String> getImagesTexts(ArrayList<ImageSpinnerItem> restrictions)
    {
        if(restrictions == null) return this.getImagesTexts();

        ArrayList<String> imagesTexts = new ArrayList<>();
        for(ImageSpinnerItem item : this.getItems(restrictions))
        {
            imagesTexts.add(item.getImageText());
        }
        return imagesTexts;
    }

    public void setItems(ArrayList<ImageSpinnerItem> items)
    {
        this.items = items;
    }


}
