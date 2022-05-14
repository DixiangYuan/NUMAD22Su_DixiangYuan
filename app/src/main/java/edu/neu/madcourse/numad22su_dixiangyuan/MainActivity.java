package edu.neu.madcourse.numad22su_dixiangyuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button aboutMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aboutMe = findViewById(R.id.button);
        aboutMe.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this,"Name: Dixiang Yuan\nEmail: yuan.di@northeastern.edu",
                Toast.LENGTH_LONG).show();
    }
}