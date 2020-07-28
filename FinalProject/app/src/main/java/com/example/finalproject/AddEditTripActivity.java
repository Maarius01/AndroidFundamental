package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.print.pdf.PrintedPdfDocument;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditTripActivity extends AppCompatActivity {
    public static final String Extra_ID = "com.example.finalproject.EXTRA_ID";
    public static final String Extra_NAME = "com.example.finalproject.EXTRA_NAME";
    public static final String Extra_DESTINATION = "com.example.finalproject.EXTRA_DESTINATION";
    public static final String Extra_TYPE = "com.example.finalproject.EXTRA_TYPE";
    public static final String Extra_PRICE = "com.example.finalproject.EXTRA_PRICE";
    public static final String Extra_START_DATE = "com.example.finalproject.EXTRA_START_DATE";
    public static final String Extra_END_DATE = "com.example.finalproject.EXTRA_END_DATE";
    public static final String Extra_RATING = "com.example.finalproject.EXTRA_RATING";


    private EditText editTextName;
    private EditText editTextDestination;
    private EditText editTextType;
    private EditText editTextPrice;
    private EditText editTextStartDate;
    private EditText editTextEndDate;
    private EditText editTextRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        editTextName = findViewById(R.id.edit_text_name);
        editTextDestination = findViewById(R.id.edit_text_destination);
        editTextType = findViewById(R.id.edit_text_type);
        editTextPrice = findViewById(R.id.edit_text_price);
        editTextStartDate = findViewById(R.id.edit_text_start_date);
        editTextEndDate = findViewById(R.id.edit_text_end_date);
        editTextRating = findViewById(R.id.edit_text_rating);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(Extra_ID)) {
            setTitle("Edit Trip");
            editTextName.setText(intent.getStringExtra(Extra_NAME));
            editTextDestination.setText(intent.getStringExtra(Extra_DESTINATION));
            editTextType.setText(intent.getStringExtra(Extra_TYPE));
            editTextPrice.setText(intent.getStringExtra(Extra_PRICE));
            editTextStartDate.setText(intent.getStringExtra(Extra_START_DATE));
            editTextEndDate.setText(intent.getStringExtra(Extra_END_DATE));
            editTextRating.setText(intent.getStringExtra(Extra_RATING));

        } else {
            setTitle("Add Trip");
        }
    }

    private void saveTrip() {
        String name = editTextName.getText().toString();
        String destination = editTextDestination.getText().toString();
        String type = editTextType.getText().toString();
        String price = editTextPrice.getText().toString();
        String startDate = editTextStartDate.getText().toString();
        String endDate = editTextEndDate.getText().toString();
        String rating = editTextRating.getText().toString();

        if (name.trim().isEmpty() || destination.trim().isEmpty() || type.trim().isEmpty() || price.trim().isEmpty()
                || startDate.trim().isEmpty() || endDate.trim().isEmpty() || rating.trim().isEmpty()) {
            Toast.makeText(this, "Please insert info", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(Extra_NAME, name);
        data.putExtra(Extra_DESTINATION, destination);
        data.putExtra(Extra_TYPE, type);
        data.putExtra(Extra_PRICE, price);
        data.putExtra(Extra_START_DATE, startDate);
        data.putExtra(Extra_END_DATE, endDate);
        data.putExtra(Extra_RATING, rating);

        int id = getIntent().getIntExtra(Extra_ID, -1);
        if (id != -1){
            data.putExtra(Extra_ID,id);
        }

            setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_trip_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_trip:
                saveTrip();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
}