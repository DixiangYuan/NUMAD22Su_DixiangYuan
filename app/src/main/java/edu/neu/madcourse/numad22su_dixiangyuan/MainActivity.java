package edu.neu.madcourse.numad22su_dixiangyuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button aboutMe;
    Button clicky;
    Button linkCollector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutMe = findViewById(R.id.button);
        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, InfoActivity.class);
                startActivity(i);
            }
        });

        clicky = findViewById(R.id.buttonClicky);
        clicky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ClickyActivity.class);
                startActivity(i);
            }
        });

        linkCollector = findViewById(R.id.linkCollectorBTN);
        linkCollector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LinkCollectorActivity.class);
                startActivity(i);
            }
        });

    }
}