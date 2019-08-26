package com.example.googlemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.googlemap.model.School;

public class DisplayInformationActivity extends AppCompatActivity {

    TextView name,latitude,longitude,tvId;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_information);

        name = findViewById(R.id.tv_name);
        latitude = findViewById(R.id.tv_lat);
        longitude = findViewById(R.id.tv_long);
        button = findViewById(R.id.btn_apply);
        tvId = findViewById(R.id.tv_id);

        Intent intent = getIntent();
        School school = intent.getParcelableExtra("item");

        tvId.setText(String.valueOf(school.getSchoolId()));
        name.setText(school.getLocationName());
        latitude.setText(String.valueOf(school.getLatitude()));
        longitude.setText(String.valueOf(school.getLongitude()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DisplayInformationActivity.this,ApplyActivity.class));
            }
        });
    }
}
