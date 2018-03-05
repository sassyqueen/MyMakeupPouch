package sg.edu.rp.g913.mymakeuppouch;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
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

        alMakeup.add(new Makeup("Lip Products", "001", "\nMatte/Glossy. Includes lip tint, lip gloss, lipsticks and chapsticks."));
        alMakeup.add(new Makeup("Face Makeup", "002", "\nIncludes base makeup, foundation, CC/BB creams, concealers"));
        alMakeup.add(new Makeup("Eye/Eyebrow Makeup" , "003", "\nIncludes eyeshadows, eye primers, eyebrow pencil, mascara, eyeliner"));
        alMakeup.add(new Makeup("Skincare/Beauty Products", "004", "\nIncludes toners, moisturisers"));
        alMakeup.add(new Makeup("Makeup Tools", "005", "\nIncludes brushes, sponges"));

        final String[] mycategory = {""};
        lvMakeup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                //create dialog builder
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(MainActivity.this);


                if (position == 0) {
                    //set dialog details
                    myBuilder.setTitle("Lip Products");
                    myBuilder.setMessage("Add new lip product?");
                    mycategory[0] ="001";

                } else if (position == 1) {
                    myBuilder.setTitle("Face Makeup");
                    myBuilder.setMessage("Add new face makeup product?");
                    mycategory[0] ="002";
                } else if (position == 2) {
                    myBuilder.setTitle("Eye/Eyebrow Makeup");
                    myBuilder.setMessage("Add new eye/eyebrow product?");
                    mycategory[0] ="003";
                } else if (position == 3) {
                    myBuilder.setTitle("Skincare/Beauty Products");
                    myBuilder.setMessage("Add new skincare product?");
                    mycategory[0] ="004";
                } else if (position == 4) {
                    myBuilder.setTitle("Makeup Tools");
                    myBuilder.setMessage("Add new makeup tool?");
                    mycategory[0] ="005";
                }
                myBuilder.setCancelable(false);
                //config the positive button
                myBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //intent


                        Intent intent = new Intent(getBaseContext(), secondActivity.class);
                        Makeup currentItem = alMakeup.get(position);
                        intent.putExtra("title", currentItem.makeupGroup);
                        intent.putExtra("SelectedCat" , mycategory[0]);
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
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent objEvent) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onBackPressed();
            return true;
        }
        return super.onKeyUp(keyCode, objEvent);
    }

    @Override
    public void onBackPressed() {

        goBack();
    }

    public void goBack() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                MainActivity.this);

        alertDialog.setTitle("Confirm");
        alertDialog.setMessage("Are you sure you want to exit?");
        alertDialog.setCancelable(true);
        alertDialog.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        moveTaskToBack(true);
                        System.exit(0);
                    }
                });

        alertDialog.setPositiveButton("No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

}


