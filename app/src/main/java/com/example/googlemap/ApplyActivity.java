package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class ApplyActivity extends AppCompatActivity {

    EditText name, phoneNo;
    Button apply;
    String nameVal, phoneVal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        name = findViewById(R.id.et_name);
        phoneNo = findViewById(R.id.et_phone);
        apply = findViewById(R.id.btn_apply);



        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameVal = name.getText().toString();
                phoneVal = phoneNo.getText().toString();
                if (nameVal.matches("")) {
                    Toast.makeText(ApplyActivity.this, "Enter the Name", Toast.LENGTH_SHORT).show();
                } else if (!nameVal.matches("^[a-zA-Z]+(\\s[a-zA-Z]+)?$")){
                    Toast.makeText(ApplyActivity.this, "Enter the Valid Name", Toast.LENGTH_SHORT).show();
                }else if (phoneVal.matches("")){
                    Toast.makeText(ApplyActivity.this, "Enter the Phone no", Toast.LENGTH_SHORT).show();
                }else if (phoneVal.length() != 11){
                    Toast.makeText(ApplyActivity.this, "Enter the Valid Phone no", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(ApplyActivity.this, "Successfully Applied", Toast.LENGTH_SHORT).show();

            }
        });

    }
}
