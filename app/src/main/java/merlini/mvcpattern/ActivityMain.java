package merlini.mvcpattern;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import merlini.mvcpattern.Base.Console;
import merlini.mvcpattern.ButtonPanel.ButtonPanel;
import merlini.mvcpattern.Database.DatabaseHelper;
import merlini.mvcpattern.Database.QueryManager;
import merlini.mvcpattern.Database.TableManager;
import merlini.mvcpattern.DraggableImagePanel.DraggableImagePanel;
import merlini.mvcpattern.DraggableImagePanel.TransformableImage;
import merlini.mvcpattern.ImagePanel.ImagePanel;

public class ActivityMain extends AppCompatActivity
{
    private ButtonPanel buttonPanel;
    private ImagePanel imagePanel;
    private DraggableImagePanel draggableImagePanel;

    private DatabaseHelper db;
    private TableManager table;
    private QueryManager queryManager;
    private TextView t;

    public static int ID = 0;

    public ActivityMain()
    {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        //Ogni componente dell'activity deve essere assegnato al suo layout
        this.buttonPanel = (ButtonPanel) findViewById(R.id.buttonPanel);
        this.imagePanel = (ImagePanel) findViewById(R.id.imagePanel);

        this.draggableImagePanel = (DraggableImagePanel) findViewById(R.id.view);
        this.draggableImagePanel.addImage(R.drawable.ciao);
        //this.draggableImagePanel.addImage(R.drawable.msport);

        this.t = (TextView) findViewById(R.id.textView);
        Button b = (Button) findViewById(R.id.button);

        this.db = new DatabaseHelper(this.getApplicationContext(), "db1");
        this.table = new TableManager("Utenti", this.db);
        this.queryManager = new QueryManager(this.table) {
            private String out;
            @Override
            protected void onBeforeSelectIterations()
            {
                this.out = "";
            }

            @Override
            protected void onAfterSelectIterations()
            {
                t.setText(this.out);
            }

            @Override
            protected void onSelectIteration(Cursor cursor)
            {
                this.out = this.out.concat(cursor.getString(cursor.getColumnIndex("nome")));
            }

            @Override
            protected void onInsertResult(boolean insert)
            {
                Toast.makeText(getApplicationContext(), Boolean.toString(insert), Toast.LENGTH_SHORT);
            }

            @Override
            protected void onUpdateResult(boolean update)
            {
                Toast.makeText(getApplicationContext(), Boolean.toString(update), Toast.LENGTH_SHORT);
            }

            @Override
            protected void onDeleteResult(boolean delete)
            {
                Toast.makeText(getApplicationContext(), Boolean.toString(delete), Toast.LENGTH_SHORT);
            }
        };



        this.buttonPanel.addButton("Merlini", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePanel.addImage(R.drawable.merlini);
            }
        });

        this.buttonPanel.addButton("MSport", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePanel.addImage(R.drawable.msport);
            }
        });

        this.buttonPanel.addButton("Renzi", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePanel.addImage(R.drawable.renzi);
            }
        });

        this.buttonPanel.addButton("Sbirri", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePanel.addImage(R.drawable.sbirri);
            }
        });
    }

    public void addAndDisplay(View view)
    {
        ContentValues cv = new ContentValues();
        cv.put("nome", "mattia");
        cv.put("cognome", "merlini");
        cv.put("email", "info@mm.it");
        cv.put("psw", "123456");
        this.queryManager.insert(cv);


        this.queryManager.select(false, null, null, null, null, null, null, null);
    }
}
