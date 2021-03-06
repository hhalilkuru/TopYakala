package com.halil.TopYakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView0;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        timeText = (TextView) findViewById(R.id.zamanText);
        scoreText = (TextView) findViewById(R.id.scoreText);
        imageView0 = findViewById(R.id.imageView0);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);

        imageArray = new ImageView[] {imageView0, imageView1, imageView2, imageView3, imageView4, imageView5, imageView6, imageView7, imageView8,};
        hideImages();


        score = 0;

        new CountDownTimer(10000,1000) {


            @SuppressLint("SetTextI18n")
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Zaman: " + millisUntilFinished/1000);

            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onFinish() {

                timeText.setText("S??re Bitti");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Oyunu S??f??rla ?");
                alert.setMessage("Ba??tan ba??las??n m??");
                alert.setPositiveButton("Evet", (dialog, which) -> {
                    //restart

                    Intent intent = getIntent(); //bunu ??ok kullanmay??z normalde iki aktivite aras??nda ge??i?? yapmak i??in kulland????m??z intent, startactivit intent dedi??imizde kendi aktivitemizi ba??tan ba??lat??yoruz
                    finish(); //uygulamay?? yormamak i??in g??ncel aktiviteyi destroy ediyoruz
                    startActivity(intent);

                });

                alert.setNegativeButton("Hay??r", (dialog, which) -> Toast.makeText(MainActivity.this, "Oyun Bitti", Toast.LENGTH_SHORT).show());

                alert.show();

            }
        }.start(); //countdown timeri ba??lat
    }

    @SuppressLint("SetTextI18n")
    public void skorattir (View view) {

        score++; //score = score + 1;
        scoreText.setText("Puan: " + score);
    }

    public void hideImages() {

        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }
                Random random = new Random ();
                int i = random.nextInt(9); //9 yazmam??z bize 0-8 aras??nda herhangi bir rakam getirir. benim dizimde 9 tane eleman var dizilerin endeksi 0 dan ba??lar ??yle devam eder.
                imageArray[i].setVisibility(View.VISIBLE); //i yerine 0 yazsayd??k ??magearray i??indeki ilk ??eyi al??r ve g??r??n??r hale getirir, yukar??da rastgele olmas??n?? i olarak tan??mlad??????m??zdan i yazd??k

                handler.postDelayed(this, 500 ); //runnable i??inde oldu??umuz i??in this diyebiliriz
            }
        };
        handler.post(runnable);
    }
}