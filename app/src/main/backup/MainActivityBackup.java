package backup;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivityBackup extends AppCompatActivity {
    ListView lvMakeup;
    ArrayList<Makeup> alMakeup = new ArrayList<>();
    CustomAdapter caMakeup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvMakeup = (ListView) findViewById(R.id.listViewMakeup);
        caMakeup = new CustomAdapter(this, R.layout.row, alMakeup);
        lvMakeup.setAdapter(caMakeup);

        alMakeup.add(new Makeup("Lipsticks"));
        alMakeup.add(new Makeup("Face Makeup"));
        alMakeup.add(new Makeup("Eye/Eyebrow Makeup"));
        alMakeup.add(new Makeup("Skin Care/Beauty Products"));
        alMakeup.add(new Makeup("Makeup Tools"));


        lvMakeup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //create dialog builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivityBackup.this);


                if (position == 0) {
                    //set dialog details
                    myBuilder.setTitle("Lipsticks");
                    myBuilder.setMessage("Such as lipsticks, lip primers, lip balms, lip tint, etc...");

                } else if (position == 1) {
                    myBuilder.setTitle("Face Makeup");
                    myBuilder.setMessage("Such as foundation, concealer, primer, BB Cream, CC Cream, highlights, contour products, face powder etc...");
                } else if (position == 2) {
                    myBuilder.setTitle("Eye Makeup");
                    myBuilder.setMessage("Such as eyeshadow, eye primer, mascara, eyeliner pencils/gels, eyebrow pencil/gels etc...");
                } else if (position == 3) {
                    myBuilder.setTitle("Skin Care/Beauty Products");
                    myBuilder.setMessage("Such as moisturisers, toner, setting spray, night cream etc...");
                } else if (position == 4) {
                    myBuilder.setTitle("Makeup Tools");
                    myBuilder.setMessage("Such as brushes, sponges, beauty blenders, tweezers, eyelash curler, brow brush, etc...");
                }
                myBuilder.setCancelable(false);
                //config the positive button
                myBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //intent

                        Intent intent = new Intent(getBaseContext(), secondActivityBackup2.class);
                        Makeup currentItem = alMakeup.get(position);
                        intent.putExtra("title", currentItem.makeupGroup);

                        startActivity(intent);
                    }
                });
                // config the negative button
                myBuilder.setPositiveButton("Cancel", null);

                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            Intent intent = new Intent(getBaseContext(), about.class);
            startActivity(intent);
            return true;

        }
        else if (id==R.id.action_home){
            Intent intent = new Intent(this, MainActivityBackup.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }
}


