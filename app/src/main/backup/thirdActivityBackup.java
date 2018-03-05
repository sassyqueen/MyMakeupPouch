package backup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class thirdActivityBackup extends AppCompatActivity {
    ImageView ivCategory ;
    TextView tvCategory, tvProdName, tvExpiryPAO, tvDate;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        ivCategory = (ImageView)findViewById(R.id.imageViewCategory);


        tvCategory = (TextView)findViewById(R.id.textViewMakeupCategory);
        tvProdName = (TextView)findViewById(R.id.textViewProductName);
        tvExpiryPAO = (TextView)findViewById(R.id.textViewSpinnerSelected);
        tvDate = (TextView)findViewById(R.id.textViewDateExpiry);
        btnBack = (Button)findViewById(R.id.buttonBack);

        Intent intentReceived = getIntent();
        String category = intentReceived.getStringExtra("category");
        tvCategory.setText(category);

        tvProdName.setText(intentReceived.getStringExtra("name"));
        tvExpiryPAO.setText(intentReceived.getStringExtra("choice"));
        tvDate.setText(intentReceived.getStringExtra("date"));

        if (tvCategory.getText().toString().equalsIgnoreCase("lipsticks")){
            ivCategory.setImageResource(R.drawable.lipstick);



        }
        else if (tvCategory.getText().toString().equalsIgnoreCase("face makeup")){
            ivCategory.setImageResource(R.drawable.foundation);



        }
        else if (tvCategory.getText().toString().equalsIgnoreCase("eye/eyebrow makeup")){
            ivCategory.setImageResource(R.drawable.eyemakeup);




        }
        else if (tvCategory.getText().toString().equalsIgnoreCase("skin care/beauty products")){
            ivCategory.setImageResource(R.drawable.skincare);



        }
        else {
            ivCategory.setImageResource(R.drawable.tools);


        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), secondActivityBackup2.class);
                startActivity(intent);
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

        } else if (id == R.id.action_home) {
            Intent intent = new Intent(this, MainActivityBackup2.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
    }
}
