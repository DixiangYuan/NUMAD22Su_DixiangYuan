package edu.neu.madcourse.numad22su_dixiangyuan;

import static java.sql.DriverManager.println;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.snackbar.Snackbar;

public class FindPrimesActivity extends AppCompatActivity implements Varification.NoticeDialogListener{
    private static final String TAG = "This is ";
    int numberChecked = 2;
    int primeNumber = 2;
    TextView checkedNumber;
    TextView lastestPrime;
    Boolean runSwitch = false;
    Button startBtn;
    Boolean stopBtn;

    private Handler textHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_primes);

        checkedNumber = findViewById(R.id.checkedNumber);
        lastestPrime = findViewById(R.id.primeNumber);
        Log.e("This is ", "onCreate");
        //startBtn.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        runThread(v);
        //    }
        //});
    }



   public boolean checkPrimes(int number) {
        if(number >= 3) {
            for (int i = 2; i <= Math.sqrt(number); i++ ) {
                if (number%i == 0) {
                    return false;
                }
            }
        }
        return true;
   }

   public void runThread(View view) {
        if (runSwitch == false) {
            Log.e("This is ", "runThread");
            numberChecked = 2;
            primeNumber = 2;
            runSwitch = true;
            PrimeThread runIt = new PrimeThread();
            runIt.start();
        }
   }

    public void stopThread(View view) {
        Log.e("This is ", "stopThread");
        runSwitch = false;
    }

    @Override
    public void onDialogPositiveClick() {
        finish();
    }

    class PrimeThread extends Thread {

        @Override
        public void run() {
            while (runSwitch) {
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        checkedNumber.setText(String.valueOf(numberChecked));
                        lastestPrime.setText(String.valueOf(primeNumber));
                    }
                });
                try{
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                numberChecked++;
                if (checkPrimes(numberChecked)) {
                    primeNumber = numberChecked;
                }
            }
        }
    }

    public void dissmissConfir() {
        Varification dissmiss = new Varification();
        dissmiss.show(getSupportFragmentManager(), "Dismiss Confirmation");
    }

    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        dissmissConfir();
    }
}
