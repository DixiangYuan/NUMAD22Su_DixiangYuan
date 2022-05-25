package edu.neu.madcourse.numad22su_dixiangyuan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button aboutMe;
    Button clicky;
    Button showTextA;
    Button showTextB;
    Button showTextC;
    Button showTextD;
    Button showTextE;
    Button showTextF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutMe = findViewById(R.id.button);
        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Name: Dixiang Yuan\nEmail: yuan.di@northeastern.edu",
                        Toast.LENGTH_LONG).show();
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

        showTextA = findViewById(R.id.buttonA);
        showTextA.setOnClickListener(this);
        showTextB = findViewById(R.id.buttonB);
        showTextB.setOnClickListener(this);
        showTextC = findViewById(R.id.buttonC);
        showTextC.setOnClickListener(this);
        showTextD = findViewById(R.id.buttonD);
        showTextD.setOnClickListener(this);
        showTextE = findViewById(R.id.buttonE);
        showTextE.setOnClickListener(this);
        showTextF = findViewById(R.id.buttonF);
        showTextF.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        TextView showText = findViewById(R.id.textViewForGrid);
        switch (v.getId()) {
            case R.id.buttonA:
                showText.setText("Pressed: A ");
                break;
            case R.id.buttonB:
                showText.setText("Pressed: B ");
                break;

            case R.id.buttonC:
                showText.setText("Pressed: C ");
                break;

            case R.id.buttonD:
                showText.setText("Pressed: D ");
                break;

            case R.id.buttonE:
                showText.setText("Pressed: E ");
                break;

            case R.id.buttonF:
                showText.setText("Pressed: F ");
                break;

        }
    }
}