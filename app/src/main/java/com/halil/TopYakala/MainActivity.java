package com.halil.TopYakala;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
        scoreText = (TextView) findViewById(R.id.puanText);
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


            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Zaman: " + millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {

                timeText.setText("Süre Bitti");
                handler.removeCallbacks(runnable);
                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Oyunu Sıfırla ?");
                alert.setMessage("Baştan başlasın mı");
                alert.setPositiveButton("Evet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart

                        Intent intent = getIntent(); //bunu çok kullanmayız normalde iki aktivite arasında geçiş yapmak için kullandığmız intent, startactivit intent dediğimizde kendi aktivitemizi baştan başlatıyoruz
                        finish(); //uygulamayı yormamak için güncel aktiviteyi destroy ediyoruz
                        startActivity(intent);

                    }
                });

                alert.setNegativeButton("Hayır", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Oyun Bitti", Toast.LENGTH_SHORT).show();
                    }
                });

                alert.show();

            }
        }.start(); //countdown timeri başlat
    }

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
                int i = random.nextInt(9); //9 yazmamız bize 0-8 arasında herhangi bir rakam getirir. benim dizimde 9 tane eleman var dizilerin endeksi 0 dan başlar öyle devam eder.
                imageArray[i].setVisibility(View.VISIBLE); //i yerine 0 yazsaydık ımagearray içindeki ilk şeyi alır ve görünür hale getirir, yukarıda rastgele olmasını i olarak tanımladığımızdan i yazdık

                handler.postDelayed(this, 500 ); //runnable içinde olduğumuz için this diyebiliriz
            }
        };
        handler.post(runnable);
    }
}