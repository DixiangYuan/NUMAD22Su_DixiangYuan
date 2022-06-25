package edu.neu.madcourse.numad22su_dixiangyuan;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class WebServiceActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "WebServiceActivity";

    private Button catBtn;
    private Button helloCatBtn;
    private TextView loadingTV;
    private ImageView catImageView;
    private Boolean  loadingSwitch = true;
    private Handler textHandler = new Handler();
    private URL url = null;
    private LoadingThread loadingIt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_service_activity);

        catBtn = findViewById(R.id.catBtn);
        helloCatBtn = findViewById(R.id.helloCatBtn);
        loadingTV = findViewById(R.id.loadingTV);
        Log.e("test", "22222");
        catImageView = findViewById(R.id.catImageView);


        catBtn.setOnClickListener(this);
        helloCatBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        TextView showText = findViewById(R.id.textViewForGrid);
        switch (v.getId()) {
            case R.id.catBtn:
                try {
                    Log.e("test", "333333");
                    url = new URL("https://cataas.com/cat");
                    runThread(v);
                    runCatThread(v);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;

            case R.id.helloCatBtn:
                try {
                    Log.e("test", "555555");
                    url = new URL("https://cataas.com/cat/says/hello");
                    runThread(v);
                    runCatThread(v);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public void runCatThread(View view) {
        Log.e("test", "6666666");
        catThread runIt = new catThread();
        runIt.start();
    }

    private class catThread extends Thread {
        @Override
        public void run() {
            Log.e("test", "777777");
            HttpURLConnection conn = null;
            try {
                Log.e("test", "888888");
                conn = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.e("test", "URL is "+url.toString());
            try {
                Log.e("test", "999999");
                conn.setRequestMethod("GET");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            Log.e("test", "1010101010");
            conn.setDoInput(true);

            try {
                Log.e("test", "11 11");
                conn.connect();
                Log.e("test", "22 22");
            } catch (IOException e) {
                Log.e("test", "2020");
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("test", e.getMessage());
                e.printStackTrace();
            }

            // Read response.
            Log.e("test", "1212");
            InputStream inputStream = null;
            byte[] imageByte;
            Log.e("test", "2121");
            try {
                Log.e("test", "1313");
                inputStream = conn.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Log.e("test", "1414");
                imageByte = readInputStream(inputStream);
                Log.e("test", " " + imageByte.length);
                Bitmap bitMap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
                Log.e("test", "2424");
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        catImageView.setImageBitmap(bitMap);
                        loadingSwitch = true;
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                Log.e("test", e.getMessage());
                e.printStackTrace();
            }

        }

    }

    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        final int bufferLength = 4 * 0x400; // 4KB
        byte[] buffer = new byte[bufferLength];
        int readLength;
        IOException exception = null;

        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                while ((readLength = inputStream.read(buffer, 0, bufferLength)) != -1)
                    outputStream.write(buffer, 0, readLength);
                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (exception == null) inputStream.close();
            else try {
                inputStream.close();
            } catch (IOException e) {
                exception.addSuppressed(e);
            }
        }
        return null; //return ?
    }



    public void runThread(View view) {
        Log.e("test", "333333");
        if (loadingSwitch) {
            Log.e("test", "4444444");
            loadingSwitch = false;
            LoadingThread loadingIt = new LoadingThread();
            loadingIt.start();
        }
    }

    private class LoadingThread extends Thread {
        @Override
        public void run() {
            Log.e("test", "555555");
            while(!loadingSwitch) {
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        loadingTV.setText("Loading the picture.");
                    }
                });
                try{
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        loadingTV.setText("Loading the picture..");
                    }
                });
                try{
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        loadingTV.setText("Loading the picture...");
                    }
                });
                try{
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            textHandler.post(new Runnable() {
                @SuppressLint("SetTextI18n")
                @Override
                public void run() {
                    loadingTV.setText("Your cat is here ;)");
                }
            });
        }
    }


}
