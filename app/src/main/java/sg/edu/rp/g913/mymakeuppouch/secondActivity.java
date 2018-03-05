package sg.edu.rp.g913.mymakeuppouch;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.regex.Pattern;

public class secondActivity extends AppCompatActivity {
    Button addNew;
    TextView title;
    ListView lvAddNew;
    ArrayList<Lipsticks> alLipstickItem = new ArrayList<Lipsticks>();
    ArrayAdapter<Lipsticks> aaLipsticks;
    ArrayList<FaceMakeup> alfaceItem = new ArrayList<FaceMakeup>();
    ArrayAdapter<FaceMakeup> aaface;
    ArrayList<Skincare> alskincareItem = new ArrayList<Skincare>();
    ArrayAdapter<Skincare> aaskincare;
    ArrayList<EyeEyebrowProducts> aleyeItem = new ArrayList<EyeEyebrowProducts>();
    ArrayAdapter<EyeEyebrowProducts> aaEye;
    ArrayList<Tools> altoolItem = new ArrayList<Tools>();
    ArrayAdapter<Tools> aaTool;
    EditText etAddNew, etPOD;
    Spinner spn;
    ArrayList<String> alOption = new ArrayList<String>();
    ArrayAdapter<String> aaOption;

    ArrayList<String> arr_name = new ArrayList<String>();
    ArrayList<String> arr_date = new ArrayList<String>();
    ArrayList<String> arr_choice = new ArrayList<String>();
    String[] buf_name = null;
    String[] buf_date = null;
    String[] buf_choice = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        addNew = (Button) findViewById(R.id.buttonAdd);
        title = (TextView) findViewById(R.id.textViewTitle);
        lvAddNew = (ListView) findViewById(R.id.listViewItem);
        etAddNew = (EditText) findViewById(R.id.editTextAdd);
        etPOD = (EditText) findViewById(R.id.editTextPOD);
        spn = (Spinner) findViewById(R.id.spinner);

        Intent intentReceived = getIntent();
        final String category = intentReceived.getStringExtra("title");
        final String Selectedcategory = intentReceived.getStringExtra("SelectedCat");
        System.out.println("Category Selected: " + Selectedcategory);
        title.setText(category);

        aaLipsticks = new ArrayAdapter<Lipsticks>(this, android.R.layout.simple_list_item_1, alLipstickItem);

        aaface = new ArrayAdapter<FaceMakeup>(this, android.R.layout.simple_list_item_1, alfaceItem);

        aaEye = new ArrayAdapter<EyeEyebrowProducts>(this, android.R.layout.simple_list_item_1, aleyeItem);

        aaskincare = new ArrayAdapter<Skincare>(this, android.R.layout.simple_list_item_1, alskincareItem);

        aaTool = new ArrayAdapter<Tools>(this, android.R.layout.simple_list_item_1, altoolItem);
        aaOption = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, alOption);

        registerForContextMenu(lvAddNew);
        spn.setAdapter(aaOption);


        alOption.clear();
        if (Selectedcategory.equalsIgnoreCase("005")) {
            String[] strOption = getResources().getStringArray(R.array.toolArray);
            alOption.addAll(Arrays.asList(strOption));
        } else if (Selectedcategory.equalsIgnoreCase("001") || Selectedcategory.equalsIgnoreCase("002") || Selectedcategory.equalsIgnoreCase("003") || Selectedcategory.equalsIgnoreCase("004")) {

            String[] strOption = getResources().getStringArray(R.array.spinnerItem);
            alOption.addAll(Arrays.asList(strOption));

        }
        aaOption.notifyDataSetChanged();
        etPOD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creating listener to set date
                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        etPOD.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                };
                //create datepicker dialog
                Calendar now = Calendar.getInstance(); //Create a Calendar object with current date/time
                DatePickerDialog myDateDialog = new DatePickerDialog(secondActivity.this, myDateListener, now.get(Calendar.YEAR), now.get((Calendar.MONTH)), now.get(Calendar.DAY_OF_MONTH));
                myDateDialog.show();
            }
        });


        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(etAddNew.getText().toString())) {
                    etAddNew.setError("Product Name cannot be empty");
                    return;
                } else if (TextUtils.isEmpty(etPOD.getText().toString())) {
                    etPOD.setError("Date cannot be empty");
                    return;
                }


                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(secondActivity.this);
                SharedPreferences.Editor prefEdit = prefs.edit();
                String choice_str = "c" + Integer.toString(spn.getSelectedItemPosition());
                Log.d("BUFFER SAVE choice STR ", choice_str);
                String save_name = prefs.getString("name_" + Selectedcategory, "");
                String save_date = prefs.getString("date_" + Selectedcategory, "");
                String save_choice = prefs.getString("choice_" + Selectedcategory, "");


                String choice_label = "";

                if (choice_str.equalsIgnoreCase("c0")) {
                    choice_label = App.context.getResources().getString(R.string.c0);

                } else if (choice_str.equalsIgnoreCase("c1")) {
                    choice_label = App.context.getResources().getString(R.string.c1);
                } else if (choice_str.equalsIgnoreCase("c2")) {
                    choice_label = App.context.getResources().getString(R.string.c2);
                }

                //actual concat (|) string

                save_name = save_name + etAddNew.getText().toString() + "|";
                save_date = save_date + etPOD.getText().toString() + "|";
                save_choice = save_choice + choice_label + "|";

                Log.d("BUFFER SAVE CATEGORY ", Selectedcategory);
                Log.d("BUFFER SAVE NAME ", save_name.toString());
                Log.d("BUFFER SAVE DATE ", save_date.toString());
                Log.d("BUFFER SAVE CHOICE ", save_choice.toString());


                prefEdit.putString("date_" + Selectedcategory, save_date);
                prefEdit.putString("name_" + Selectedcategory, save_name);

                prefEdit.putString("choice_" + Selectedcategory, save_choice);

                if (Selectedcategory.equalsIgnoreCase("005")) {
                    String[] strOption = getResources().getStringArray(R.array.toolArray);

                }

                if (Selectedcategory.equalsIgnoreCase("001")) {

                    alLipstickItem.add(new Lipsticks(etAddNew.getText().toString(), etPOD.getText().toString(), choice_label));
                    lvAddNew.setAdapter(aaLipsticks);
                    aaLipsticks.notifyDataSetChanged();
                } else if (Selectedcategory.equalsIgnoreCase("002")) {
                    alfaceItem.add(new FaceMakeup(etAddNew.getText().toString(), etPOD.getText().toString(), choice_label));
                    lvAddNew.setAdapter(aaface);
                    aaface.notifyDataSetChanged();
                } else if (Selectedcategory.equalsIgnoreCase("003")) {
                    aleyeItem.add(new EyeEyebrowProducts(etAddNew.getText().toString(), etPOD.getText().toString(), choice_label));
                    lvAddNew.setAdapter(aaEye);
                    aaEye.notifyDataSetChanged();
                } else if (Selectedcategory.equalsIgnoreCase("004")) {
                    alskincareItem.add(new Skincare(etAddNew.getText().toString(), etPOD.getText().toString(), choice_label));
                    lvAddNew.setAdapter(aaskincare);
                    aaskincare.notifyDataSetChanged();


                } else if (Selectedcategory.equalsIgnoreCase("005")) {
                    altoolItem.add(new Tools(etAddNew.getText().toString(), etPOD.getText().toString(), choice_label));
                    lvAddNew.setAdapter(aaTool);
                    aaTool.notifyDataSetChanged();
                }


                prefEdit.commit();


            }

        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent intentReceived = getIntent();
        final String category = intentReceived.getStringExtra("title");
        final String Selectedcategory = intentReceived.getStringExtra("SelectedCat");

        title.setText(category);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String name = prefs.getString("name_" + Selectedcategory, "");
        String date = prefs.getString("date_" + Selectedcategory, "");
        String choice = prefs.getString("choice_" + Selectedcategory, "");

        String choice_str = "c" + Integer.toString(spn.getSelectedItemPosition());


        //first get the initial list
        buf_name = (String[]) getSplittedValues(name);
        buf_date = (String[]) getSplittedValues(date);
        buf_choice = (String[]) getSplittedValues(choice);

        Log.d("BUFFER ONRESUME choice", Integer.toString(buf_choice.length));
        Log.d("BUFFER ONRESUME date", Integer.toString(buf_date.length));
        Log.d("BUFFER ONRESUME NAME", Integer.toString(buf_name.length));


        if (Selectedcategory.equalsIgnoreCase("001")) {


            for (int i = 0; i < buf_name.length; i++) {
                alLipstickItem.add(new Lipsticks(buf_name[i].toString(), buf_date[i].toString(), buf_choice[i].toString()));

                Log.d("BUFFER  index choice", buf_choice[i].toString());
                Log.d("BUFFER index date", buf_date[i].toString());
                Log.d("BUFFER index NAME", buf_name[i].toString());

            }
            lvAddNew.setAdapter(aaLipsticks);
            aaLipsticks.notifyDataSetChanged();

        } else if (Selectedcategory.equalsIgnoreCase("002")) {


            for (int i = 0; i < buf_name.length; i++) {
                alfaceItem.add(new FaceMakeup(buf_name[i].toString(), buf_date[i].toString(), buf_choice[i].toString()));

                Log.d("BUFFER index choice", buf_choice[i].toString());
                Log.d("BUFFER index date", buf_date[i].toString());
                Log.d("BUFFER index NAME", buf_name[i].toString());

            }
            lvAddNew.setAdapter(aaface);
            aaface.notifyDataSetChanged();

        } else if (Selectedcategory.equalsIgnoreCase("003")) {

            for (int i = 0; i < buf_name.length; i++) {
                aleyeItem.add(new EyeEyebrowProducts(buf_name[i].toString(), buf_date[i].toString(), buf_choice[i].toString()));

                Log.d("BUFFER  index choice", buf_choice[i].toString());
                Log.d("BUFFER index date", buf_date[i].toString());
                Log.d("BUFFER index NAME", buf_name[i].toString());

            }
            lvAddNew.setAdapter(aaEye);
            aaEye.notifyDataSetChanged();

        } else if (Selectedcategory.equalsIgnoreCase("004")) {

            for (int i = 0; i < buf_name.length; i++) {
                alskincareItem.add(new Skincare(buf_name[i].toString(), buf_date[i].toString(), buf_choice[i].toString()));

                Log.d("BUFFER  index choice", buf_choice[i].toString());
                Log.d("BUFFER index date", buf_date[i].toString());
                Log.d("BUFFER index NAME", buf_name[i].toString());

            }
            lvAddNew.setAdapter(aaskincare);
            aaskincare.notifyDataSetChanged();

        } else if (Selectedcategory.equalsIgnoreCase("005")) {

            for (int i = 0; i < buf_name.length; i++) {
                altoolItem.add(new Tools(buf_name[i].toString(), buf_date[i].toString(), buf_choice[i].toString()));

                Log.d("BUFFER  index choice", buf_choice[i].toString());
                Log.d("BUFFER index date", buf_date[i].toString());
                Log.d("BUFFER index NAME", buf_name[i].toString());

            }
            lvAddNew.setAdapter(aaTool);
            aaTool.notifyDataSetChanged();

        }

        etAddNew.setText("");
        etPOD.setText("");
        spn.setSelection(spn.getSelectedItemPosition());
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

        } else if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }



        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "More about my product");
        menu.add(1, 0, 1, "Delete item");


    }

    @Override
    public boolean onContextItemSelected(final MenuItem item) {

        if (item.getGroupId() == 0) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            int index = info.position;

            Intent intentReceived = getIntent();
            final String category = intentReceived.getStringExtra("title");
            final String Selectedcategory = intentReceived.getStringExtra("SelectedCat");

            title.setText(category);

            Intent intent = new Intent(getBaseContext(), thirdActivity.class);
            if (Selectedcategory.equalsIgnoreCase("001")) {
                Lipsticks currentItem = alLipstickItem.get(index);
                intent.putExtra("name", currentItem.lipproduct);
                intent.putExtra("date", currentItem.lipdate);
                intent.putExtra("choice", currentItem.dateChoice);

            } else if (Selectedcategory.equalsIgnoreCase("002")) {
                FaceMakeup currentItem = alfaceItem.get(index);
                intent.putExtra("name", currentItem.faceprodname);
                intent.putExtra("date", currentItem.facedate);
                intent.putExtra("choice", currentItem.dateChoice);

            } else if (Selectedcategory.equalsIgnoreCase("003")) {
                EyeEyebrowProducts currentItem = aleyeItem.get(index);
                intent.putExtra("name", currentItem.eyeandbrow);
                intent.putExtra("date", currentItem.date);
                intent.putExtra("choice", currentItem.choice);

            } else if (Selectedcategory.equalsIgnoreCase("004")) {
                Skincare currentItem = alskincareItem.get(index);
                intent.putExtra("name", currentItem.skincare);
                intent.putExtra("date", currentItem.date);
                intent.putExtra("choice", currentItem.choice);


            } else if (Selectedcategory.equalsIgnoreCase("005")) {
                Tools currentItem = altoolItem.get(index);
                intent.putExtra("name", currentItem.tool);
                intent.putExtra("date", currentItem.date);
                intent.putExtra("choice", currentItem.choice);

            }

            intent.putExtra("category", category);
            intent.putExtra("selectedCat", Selectedcategory);
            startActivity(intent);


        } else if (item.getGroupId() == 1) {


            //create dialog builder
            AlertDialog.Builder myBuilder = new AlertDialog.Builder(secondActivity.this);
            myBuilder.setTitle("Confirm");
            myBuilder.setMessage("Delete item?");
            myBuilder.setCancelable(false);

//            config the positive button
            myBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
//                    Log.d("secondActivityBackup.this", "it works here inside neg button");
                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                    int index = info.position;

                    Intent intentReceived = getIntent();
                    final String category = intentReceived.getStringExtra("title");
                    final String Selectedcategory = intentReceived.getStringExtra("SelectedCat");
                    title.setText(category);

                    if (Selectedcategory.equalsIgnoreCase("001")) {
                        Lipsticks currentItem = alLipstickItem.get(index);
                        alLipstickItem.remove(currentItem);
                        aaLipsticks.notifyDataSetChanged();
                        /*
                           loop through Lipsticks

                           for each item

                             buf_name[currentItem].delete
                             buf_date[currentItem].delete
                             buf_choice[currentItem].delete

                         */
                        String name_delete = "";
                        String date_delete = "";
                        String choice_delete = "";

                        for (int i = 0; i < alLipstickItem.size(); i++) {
                            Lipsticks refreshItem = alLipstickItem.get(i);
                            name_delete = name_delete + refreshItem.getLipproduct() + "|";
                            date_delete = date_delete + refreshItem.getLipdate() + "|";
                            choice_delete = choice_delete + refreshItem.getDateChoice() + "|";

                        }
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(secondActivity.this);
                        SharedPreferences.Editor prefEdit = prefs.edit();
                        prefEdit.putString("name_" + Selectedcategory, name_delete);
                        prefEdit.putString("date_" + Selectedcategory, date_delete);
                        prefEdit.putString("choice_" + Selectedcategory, choice_delete);
                        prefEdit.commit();


                    } else if (Selectedcategory.equalsIgnoreCase("002")) {
                        FaceMakeup currentItem = alfaceItem.get(index);
                        alfaceItem.remove(currentItem);
                        aaface.notifyDataSetChanged();


                        String name_delete = "";
                        String date_delete = "";
                        String choice_delete = "";

                        for (int i = 0; i < alfaceItem.size(); i++) {
                            FaceMakeup refreshItem = alfaceItem.get(i);
                            name_delete = name_delete + refreshItem.getFaceprodname() + "|";
                            date_delete = date_delete + refreshItem.getFacedate() + "|";
                            choice_delete = choice_delete + refreshItem.getDateChoice() + "|";

                        }
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(secondActivity.this);
                        SharedPreferences.Editor prefEdit = prefs.edit();
                        prefEdit.putString("name_" + Selectedcategory, name_delete);
                        prefEdit.putString("date_" + Selectedcategory, date_delete);
                        prefEdit.putString("choice_" + Selectedcategory, choice_delete);
                        prefEdit.commit();


                    } else if (Selectedcategory.equalsIgnoreCase("003")) {
                        EyeEyebrowProducts currentItem = aleyeItem.get(index);
                        aleyeItem.remove(currentItem);
                        aaEye.notifyDataSetChanged();


                        String name_delete = "";
                        String date_delete = "";
                        String choice_delete = "";

                        for (int i = 0; i < aleyeItem.size(); i++) {
                            EyeEyebrowProducts refreshItem = aleyeItem.get(i);
                            name_delete = name_delete + refreshItem.getEyeandbrow() + "|";
                            date_delete = date_delete + refreshItem.getDate() + "|";
                            choice_delete = choice_delete + refreshItem.getChoice() + "|";

                        }
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(secondActivity.this);
                        SharedPreferences.Editor prefEdit = prefs.edit();
                        prefEdit.putString("name_" + Selectedcategory, name_delete);
                        prefEdit.putString("date_" + Selectedcategory, date_delete);
                        prefEdit.putString("choice_" + Selectedcategory, choice_delete);
                        prefEdit.commit();


                    } else if (Selectedcategory.equalsIgnoreCase("004")) {
                        Skincare currentItem = alskincareItem.get(index);
                        alskincareItem.remove(currentItem);
                        aaskincare.notifyDataSetChanged();

                        String name_delete = "";
                        String date_delete = "";
                        String choice_delete = "";

                        for (int i = 0; i < alskincareItem.size(); i++) {
                            Skincare refreshItem = alskincareItem.get(i);
                            name_delete = name_delete + refreshItem.getSkincare() + "|";
                            date_delete = date_delete + refreshItem.getDate() + "|";
                            choice_delete = choice_delete + refreshItem.getChoice() + "|";

                        }
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(secondActivity.this);
                        SharedPreferences.Editor prefEdit = prefs.edit();
                        prefEdit.putString("name_" + Selectedcategory, name_delete);
                        prefEdit.putString("date_" + Selectedcategory, date_delete);
                        prefEdit.putString("choice_" + Selectedcategory, choice_delete);
                        prefEdit.commit();

                    } else if (Selectedcategory.equalsIgnoreCase("005")) {
                        Tools currentItem = altoolItem.get(index);
                        altoolItem.remove(currentItem);
                        aaTool.notifyDataSetChanged();


                        String name_delete = "";
                        String date_delete = "";
                        String choice_delete = "";

                        for (int i = 0; i < altoolItem.size(); i++) {
                            Tools refreshItem = altoolItem.get(i);
                            name_delete = name_delete + refreshItem.getTool() + "|";
                            date_delete = date_delete + refreshItem.getDate() + "|";
                            choice_delete = choice_delete + refreshItem.getChoice() + "|";

                        }
                        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(secondActivity.this);
                        SharedPreferences.Editor prefEdit = prefs.edit();
                        prefEdit.putString("name_" + Selectedcategory, name_delete);
                        prefEdit.putString("date_" + Selectedcategory, date_delete);
                        prefEdit.putString("choice_" + Selectedcategory, choice_delete);
                        prefEdit.commit();
                    }


                }
            });
            // config the negative button

            myBuilder.setPositiveButton("Cancel", null);
            AlertDialog myDialog = myBuilder.create();
            myDialog.show();


        }

        return super.onContextItemSelected(item); //pass menu item to the superclass implementation.
    }

    public ContentValues Category_Reader(String cat, String mode, String valuestosave) {

        ContentValues file_list = new ContentValues();
        BufferedReader reader = null;
        Writer writer = null;
        AssetManager asset_file = null;
        asset_file = (AssetManager) App.context.getAssets();
        String value = "";
        String modevalue = cat + ".txt";


        if (mode.equalsIgnoreCase("list")) {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(openFileInput(modevalue)));
                String line;
                while ((line = input.readLine()) != null) {
                    file_list.put(value, cat);
                }

                input.close();
            } catch (IOException e) {
                e.printStackTrace();

            }
        } else {
            //default is add/delete
            try {
                FileOutputStream openFileOutput = App.context.openFileOutput(modevalue, Context.MODE_PRIVATE);
                openFileOutput.write(valuestosave.getBytes());

                file_list.put(valuestosave, cat);

                openFileOutput.flush();
                openFileOutput.close();
            } catch (IOException b) {

                b.printStackTrace();

            }


        }


        return file_list;
    }


    public String[] getSplittedValues(String i) {

        String[] ar = null;
        String ic = i;
        if (!ic.contains("|")) {
            ic = i + "|";

        }

        return ic.split(Pattern.quote("|"));


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
                secondActivity.this);

        alertDialog.setTitle("Confirm");
        alertDialog.setMessage("Go back to previous page?");
        alertDialog.setCancelable(true);
        alertDialog.setNegativeButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
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



