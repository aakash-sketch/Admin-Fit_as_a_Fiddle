 package com.example.photos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import java.util.ArrayList;
import java.util.List;

public class Create extends AppCompatActivity {
Spinner spinner2;
    EditText name;
    public void create(View view){
        ParseObject object = new ParseObject("CategoryCreation");
        object.put("CategoryName",name.getText().toString());
        object.put("LinearId",spinner2.getSelectedItem().toString());
        object.put("Start","yes");
        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Toast.makeText(Create.this, "done.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_create);
        name=findViewById(R.id.editTextTextPersonName);
        spinner2 = (Spinner) findViewById(R.id.spinner);
        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        list.add(("Benifitials"));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);


//        EditText LId=findViewById(R.id.editTextTextPersonName2);



}}