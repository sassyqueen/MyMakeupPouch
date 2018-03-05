//import android.app.DatePickerDialog;
//import android.content.ContentValues;
//import android.content.Context;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.content.res.AssetManager;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.support.v7.app.AlertDialog;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.ContextMenu;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.Spinner;
//import android.widget.TextView;
//
//import java.io.BufferedReader;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Writer;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.regex.Pattern;
//
//import sg.edu.rp.g913.mymakeuppouch.App;
//import sg.edu.rp.g913.mymakeuppouch.EyeEyebrowProducts;
//import sg.edu.rp.g913.mymakeuppouch.FaceMakeup;
//import sg.edu.rp.g913.mymakeuppouch.Lipsticks;
//import sg.edu.rp.g913.mymakeuppouch.MainActivity;
//import sg.edu.rp.g913.mymakeuppouch.R;
//import sg.edu.rp.g913.mymakeuppouch.Skincare;
//import sg.edu.rp.g913.mymakeuppouch.Tools;
//import sg.edu.rp.g913.mymakeuppouch.about;
//import sg.edu.rp.g913.mymakeuppouch.thirdActivity;
//
//
//public class secondActivityBackup extends AppCompatActivity {
//    Button addNew;
//    TextView title;
//    ListView lvAddNew;
//    ArrayList<Lipsticks> alLipstickItem = new ArrayList<Lipsticks>();
//    ArrayAdapter<Lipsticks> aaLipsticks;
//    ArrayList<FaceMakeup> alfaceItem = new ArrayList<FaceMakeup>();
//    ArrayAdapter<FaceMakeup> aaface;
//    ArrayList<Skincare> alskincareItem = new ArrayList<Skincare>();
//    ArrayAdapter<Skincare> aaskincare;
//    ArrayList<EyeEyebrowProducts> aleyeItem = new ArrayList<EyeEyebrowProducts>();
//    ArrayAdapter<EyeEyebrowProducts> aaEye;
//    ArrayList<Tools> altoolItem = new ArrayList<Tools>();
//    ArrayAdapter<Tools> aaTool;
//    EditText etAddNew, etPOD;
//    Spinner spn;
//
//    ArrayList<String> arr_name = new ArrayList<String>();
//    ArrayList<String> arr_date = new ArrayList<String>();
//    ArrayList<String> arr_choice = new ArrayList<String>();
//
//    String[] buf_name = null;
//    String[] buf_date = null;
//    String[] buf_choice = null;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_second);
//
//
//        addNew = (Button) findViewById(R.id.buttonAdd);
//        title = (TextView) findViewById(R.id.textViewTitle);
//        lvAddNew = (ListView) findViewById(R.id.listViewItem);
//        etAddNew = (EditText) findViewById(R.id.editTextAdd);
//        etPOD = (EditText) findViewById(R.id.editTextPOD);
//        spn = (Spinner) findViewById(R.id.spinner);
//        Intent intentReceived = getIntent();
//        final String category = intentReceived.getStringExtra("title");
//        title.setText(category);
//
//
//        aaLipsticks = new ArrayAdapter<Lipsticks>(this, android.R.layout.simple_list_item_1, alLipstickItem);
//
//        aaface = new ArrayAdapter<FaceMakeup>(this, android.R.layout.simple_list_item_1, alfaceItem);
//
//        aaEye = new ArrayAdapter<EyeEyebrowProducts>(this, android.R.layout.simple_list_item_1, aleyeItem);
//
//        aaskincare = new ArrayAdapter<Skincare>(this, android.R.layout.simple_list_item_1, alskincareItem);
//
//        aaTool = new ArrayAdapter<Tools>(this, android.R.layout.simple_list_item_1, altoolItem);
//
//
//        registerForContextMenu(lvAddNew);
//
//        etPOD.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //creating listener to set date
//                DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                        etPOD.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                    }
//                };
//                //create datepicker dialog
//                Calendar now = Calendar.getInstance(); //Create a Calendar object with current date/time
//                DatePickerDialog myDateDialog = new DatePickerDialog(secondActivityBackup.this, myDateListener, now.get(Calendar.YEAR), now.get((Calendar.MONTH)), now.get(Calendar.DAY_OF_MONTH));
//                myDateDialog.show();
//            }
//        });
//
//
//        addNew.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(secondActivityBackup.this);
//                SharedPreferences.Editor prefEdit = prefs.edit();
//
//
//                int arrayLength = buf_name.length;
//                String save_name="";
//                String save_date="";
//                String save_choice="";
//                for (int i = 0; i < buf_name.length; i++) {
//
//                   save_name=save_name+buf_name[i].toString()+"|";
//
//
//                }
//                   save_name = save_name+etAddNew.getText().toString();
//
//                prefEdit.putString("date", etPOD.getText().toString());
//                prefEdit.putString("name", save_name);
//
//
//
//                if (spn.getSelectedItemPosition() == 0) {
//
//                    prefEdit.putString("choice", "Expiry Date");
//
//                    if (category.equalsIgnoreCase("lipsticks")) {
//
//
//                        alLipstickItem.add(new Lipsticks(etAddNew.getText().toString(), etPOD.getText().toString(), "Expiry Date"));
//                        lvAddNew.setAdapter(aaLipsticks);
//                        aaLipsticks.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("face makeup")) {
//                        alfaceItem.add(new FaceMakeup(etAddNew.getText().toString(), etPOD.getText().toString(), "Expiry Date"));
//                        lvAddNew.setAdapter(aaface);
//                        aaface.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("eye/eyebrow makeup")) {
//                        aleyeItem.add(new EyeEyebrowProducts(etAddNew.getText().toString(), etPOD.getText().toString(), "Expiry Date"));
//                        lvAddNew.setAdapter(aaEye);
//                        aaEye.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("skin care/beauty products")) {
//                        alskincareItem.add(new Skincare(etAddNew.getText().toString(), etPOD.getText().toString(), "Expiry Date"));
//                        lvAddNew.setAdapter(aaskincare);
//                        aaskincare.notifyDataSetChanged();
//
//
//                    } else {
//                        altoolItem.add(new Tools(etAddNew.getText().toString(), etPOD.getText().toString(), "Recently Cleaned"));
//                        lvAddNew.setAdapter(aaTool);
//                        aaTool.notifyDataSetChanged();
//                    }
//
//
//                } else if (spn.getSelectedItemPosition() == 1) {
//                    prefEdit.putString("choice", "Period After Opening (PAO)");
//
//
//                    if (category.equalsIgnoreCase("lipsticks")) {
//                        alLipstickItem.add(new Lipsticks(etAddNew.getText().toString(), etPOD.getText().toString(), "Period After Opening (PAO)"));
//                        lvAddNew.setAdapter(aaLipsticks);
//                        aaLipsticks.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("face makeup")) {
//                        alfaceItem.add(new FaceMakeup(etAddNew.getText().toString(), etPOD.getText().toString(), "Period After Opening (PAO)"));
//                        lvAddNew.setAdapter(aaface);
//                        aaface.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("eye/eyebrow makeup")) {
//                        aleyeItem.add(new EyeEyebrowProducts(etAddNew.getText().toString(), etPOD.getText().toString(), "Period After Opening (PAO)"));
//                        lvAddNew.setAdapter(aaEye);
//                        aaEye.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("skin care/beauty products")) {
//                        alskincareItem.add(new Skincare(etAddNew.getText().toString(), etPOD.getText().toString(), "Period After Opening (PAO)"));
//                        lvAddNew.setAdapter(aaskincare);
//                        aaskincare.notifyDataSetChanged();
//
//
//                    } else {
//                        altoolItem.add(new Tools(etAddNew.getText().toString(), etPOD.getText().toString(), "Recently Cleaned"));
//                        lvAddNew.setAdapter(aaTool);
//                        aaTool.notifyDataSetChanged();
//                    }
//                } else if (spn.getSelectedItemPosition() == 2) {
//                    prefEdit.putString("choice", "Recently Cleaned");
//
//                    if (category.equalsIgnoreCase("lipsticks")) {
//                        alLipstickItem.add(new Lipsticks(etAddNew.getText().toString(), etPOD.getText().toString(), "Expiry Date/ Period After Opening"));
//                        lvAddNew.setAdapter(aaLipsticks);
//                        aaLipsticks.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("face makeup")) {
//                        alfaceItem.add(new FaceMakeup(etAddNew.getText().toString(), etPOD.getText().toString(), "Expiry Date/ Period After Opening"));
//                        lvAddNew.setAdapter(aaface);
//                        aaface.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("eye/eyebrow makeup")) {
//                        aleyeItem.add(new EyeEyebrowProducts(etAddNew.getText().toString(), etPOD.getText().toString(), "Expiry Date/ Period After Opening"));
//                        lvAddNew.setAdapter(aaEye);
//                        aaEye.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("skin care/beauty products")) {
//                        alskincareItem.add(new Skincare(etAddNew.getText().toString(), etPOD.getText().toString(), "Expiry Date/ Period After Opening"));
//                        lvAddNew.setAdapter(aaskincare);
//                        aaskincare.notifyDataSetChanged();
//
//
//                    } else {
//                        altoolItem.add(new Tools(etAddNew.getText().toString(), etPOD.getText().toString(), "Recently Cleaned"));
//                        lvAddNew.setAdapter(aaTool);
//                        aaTool.notifyDataSetChanged();
//                    }
//
//                }
//
//
//                prefEdit.commit();
//
//
//            }
//
//        });
//
//
//    }
//
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        Intent intentReceived = getIntent();
//        final String category = intentReceived.getStringExtra("title");
//        title.setText(category);
//
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        String name = prefs.getString("name", "");
//        Log.d("BUFFER NAME",name);
//        String date = prefs.getString("date", "");
//
//        Log.d("BUFFER date",date);
//        System.out.println(date);
//        String choice = prefs.getString("choice", "");
//        System.out.println(choice);
//
//
//        //first get the initial list
//        buf_name = (String[]) getSplittedValues(name);
//        buf_date = (String[]) getSplittedValues(date);
//        buf_choice = (String[]) getSplittedValues(choice);
//
//        ContentValues addCategories = new ContentValues();
//
//        addCategories.put("1", "Expiry Date");
//        addCategories.put("2", "Period After Opening (PAO)");
//        addCategories.put("3", "Recently Cleaned");
//
//
//
//        if (category.equalsIgnoreCase("lipsticks")) {
//
//
//            for (int i = 0; i < buf_name.length; i++) {
//                alLipstickItem.add(new Lipsticks(buf_name[i].toString(), date, addCategories.getAsString("1")));
//
//            }
//
//
//            etAddNew.setText("");
//            etPOD.setText("");
//            if (choice == "Expiry Date") {
//                spn.setSelection(0);
//            } else if (choice == "Period After Opening") {
//                spn.setSelection(1);
//            } //else {
//               // spn.setSelection(2);
//           // }
//
//            lvAddNew.setAdapter(aaLipsticks);
//            aaLipsticks.notifyDataSetChanged();
//
//        }
//
//
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here.
//        int id = item.getItemId();
//
//        if (id == R.id.action_about) {
//            Intent intent = new Intent(getBaseContext(), about.class);
//            startActivity(intent);
//            return true;
//
//        } else if (id == R.id.action_home) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
//
//
//    public void onCreateContextMenu(ContextMenu menu, View v,
//                                    ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        menu.add(0, 0, 0, "More");
//        menu.add(1, 0, 1, "Delete");
//
//
//    }
//
//    @Override
//    public boolean onContextItemSelected(final MenuItem item) {
//
//        if (item.getGroupId() == 0) {
//            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//            int index = info.position;
//
//            Intent intentReceived = getIntent();
//            final String category = intentReceived.getStringExtra("title");
//            title.setText(category);
//
//
//            if (category.equalsIgnoreCase("lipsticks")) {
//                Lipsticks currentItem = alLipstickItem.get(index);
//                Intent intent = new Intent(getBaseContext(), thirdActivity.class);
//                intent.putExtra("name", currentItem.lipproduct);
//                intent.putExtra("date", currentItem.lipdate);
//                intent.putExtra("choice", currentItem.dateChoice);
//                intent.putExtra("category", "Lipsticks");
//                startActivity(intent);
//            } else if (category.equalsIgnoreCase("face makeup")) {
//                FaceMakeup currentItem = alfaceItem.get(index);
//                Intent intent = new Intent(getBaseContext(), thirdActivity.class);
//                intent.putExtra("name", currentItem.faceprodname);
//                intent.putExtra("date", currentItem.facedate);
//                intent.putExtra("choice", currentItem.dateChoice);
//                intent.putExtra("category", "Face Makeup");
//                startActivity(intent);
//            } else if (category.equalsIgnoreCase("eye/eyebrow makeup")) {
//                EyeEyebrowProducts currentItem = aleyeItem.get(index);
//                Intent intent = new Intent(getBaseContext(), thirdActivity.class);
//                intent.putExtra("name", currentItem.eyeandbrow);
//                intent.putExtra("date", currentItem.date);
//                intent.putExtra("choice", currentItem.choice);
//                intent.putExtra("category", "Eye/Eyebrow Makeup");
//                startActivity(intent);
//            } else if (category.equalsIgnoreCase("skin care/beauty products")) {
//                Skincare currentItem = alskincareItem.get(index);
//                Intent intent = new Intent(getBaseContext(), thirdActivity.class);
//                intent.putExtra("name", currentItem.skincare);
//                intent.putExtra("date", currentItem.date);
//                intent.putExtra("choice", currentItem.choice);
//                intent.putExtra("category", "Skin Care/Beauty Products");
//                startActivity(intent);
//
//            } else {
//                Tools currentItem = altoolItem.get(index);
//                Intent intent = new Intent(getBaseContext(), thirdActivity.class);
//                intent.putExtra("name", currentItem.tool);
//                intent.putExtra("date", currentItem.date);
//                intent.putExtra("choice", currentItem.choice);
//                intent.putExtra("category", "Makeup Tools");
//                startActivity(intent);
//            }
//
//
//        } else if (item.getGroupId() == 1) {
//
//
//            //create dialog builder
//            AlertDialog.Builder myBuilder = new AlertDialog.Builder(secondActivityBackup.this);
//            myBuilder.setTitle("Confirm");
//            myBuilder.setMessage("Delete " + etAddNew.getText().toString() + "?");
//            myBuilder.setCancelable(false);
//
////            config the positive button
//            myBuilder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int which) {
//                    Log.d("secondActivityBackup.this", "it works here inside neg button");
//                    AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//                    int index = info.position;
//
//                    Intent intentReceived = getIntent();
//                    final String category = intentReceived.getStringExtra("title");
//                    title.setText(category);
//
//                    if (category.equalsIgnoreCase("lipsticks")) {
//                        Lipsticks currentItem = alLipstickItem.get(index);
//                        alLipstickItem.remove(currentItem);
//                        aaLipsticks.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("face makeup")) {
//                        FaceMakeup currentItem = alfaceItem.get(index);
//                        alfaceItem.remove(currentItem);
//                        aaface.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("eye/eyebrow makeup")) {
//                        EyeEyebrowProducts currentItem = aleyeItem.get(index);
//                        aleyeItem.remove(currentItem);
//                        aaEye.notifyDataSetChanged();
//                    } else if (category.equalsIgnoreCase("skin care/beauty products")) {
//                        Skincare currentItem = alskincareItem.get(index);
//                        alskincareItem.remove(currentItem);
//                        aaskincare.notifyDataSetChanged();
//
//                    } else {
//                        Tools currentItem = altoolItem.get(index);
//                        altoolItem.remove(currentItem);
//                        aaTool.notifyDataSetChanged();
//                    }
//                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(secondActivityBackup.this);
//                    SharedPreferences.Editor prefEdit = prefs.edit();
//                    prefEdit.clear();
//                    prefEdit.commit();
//
//                }
//            });
//            // config the negative button
//
//            myBuilder.setPositiveButton("Cancel", null);
//            AlertDialog myDialog = myBuilder.create();
//            myDialog.show();
//
//
//        }
//
//        return super.onContextItemSelected(item); //pass menu item to the superclass implementation.
//    }
//
//    public ContentValues Category_Reader(String cat, String mode, String valuestosave) {
//
//        ContentValues file_list = new ContentValues();
//        BufferedReader reader = null;
//        Writer writer = null;
//        AssetManager asset_file = null;
//        asset_file = (AssetManager) App.context.getAssets();
//        String value = "";
//        String modevalue = cat+".txt";
//
//
//        if (mode.equalsIgnoreCase("list")) {
//            try {
//                BufferedReader input = new BufferedReader(new InputStreamReader(openFileInput(modevalue)));
//                String line;
//                while ((line = input.readLine())!=null){
//                    file_list.put(value, cat);
//                }
//
//                input.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//
//            }
//        } else {
//            //default is add/delete
//            try {
//                FileOutputStream openFileOutput = App.context.openFileOutput(modevalue, Context.MODE_PRIVATE);
//                openFileOutput.write(valuestosave.getBytes());
//
//               file_list.put(valuestosave, cat);
//
//                openFileOutput.flush();
//                openFileOutput.close();
//            } catch (IOException b) {
//
//                b.printStackTrace();
//
//            }
//
//
//        }
//
//
//        return file_list;
//    }
//
//
//    public String[] getSplittedValues(String i)
//    {
//
//        String[] ar = null;
//        String ic=i;
//        if(!ic.contains("|"))
//        {
//            ic=i+"|";
//
//        }
//
//        return ic.split(Pattern.quote("|"));
//
//
//
//    }
//
//}
//
//
//
