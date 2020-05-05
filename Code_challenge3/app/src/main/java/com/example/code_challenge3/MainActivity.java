package com.example.code_challenge3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText emailAdressEditText;
    private CheckBox acceptT_C;
    private EditText phoneNumberEditText;

    private Spinner spinner;
    private List<String> androidList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        //setContentView(R.layout.spinner);


        emailAdressEditText = findViewById(R.id.emailAdressEditText);
        acceptT_C = findViewById(R.id.acceptT_C);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);


        spinner = findViewById(R.id.spinnerAndroid);
        setSpineerSource();
        spinner.setAdapter(getSpinnerAdapter());
        spinner.setOnItemSelectedListener(this);

    }

    public void submitOnCLick(View view) {
        String email = emailAdressEditText.getText().toString().trim();
        String phone = phoneNumberEditText.getText().toString().trim();
        if (email.isEmpty())
            emailAdressEditText.setError("This field can't be empty");
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailAdressEditText.setError("Fill the input with a valid email adress");
        }
        if (phone.isEmpty())
            phoneNumberEditText.setError("Tris field can't be empty");
    }

    private void setSpineerSource() {
        androidList = new ArrayList<>();
        androidList.add("Cupcake");
        androidList.add("Donut");
        androidList.add("Eclair");
        androidList.add("Kitkat");
        androidList.add("Pie");
    }

    private ArrayAdapter<String> getSpinnerAdapter() {
        return new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, androidList);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
