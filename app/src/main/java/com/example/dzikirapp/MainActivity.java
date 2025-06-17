package com.example.dzikirapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    CardView pagiCard, soreCard, CalCard, ShareCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pagiCard = findViewById(R.id.pagi);
        soreCard = findViewById(R.id.sore);
        CalCard  = findViewById(R.id.calender);
        ShareCard = findViewById(R.id.shareapp);

        pagiCard.setOnClickListener(v -> openDzikir("pagi"));
        soreCard.setOnClickListener(v -> openDzikir("petang"));
        CalCard.setOnClickListener(v -> calApp());
        ShareCard.setOnClickListener(v -> shareApplication());
    }

    private void openDzikir(String kategori) {
        Intent intent = new Intent(MainActivity.this, DzikirActivity.class);
        intent.putExtra("kategori", kategori);
        startActivity(intent);
    }
    private void shareApplication() {
        String shareText = "Assalamu’alaikum! Yuk dzikir bareng lewat aplikasi Dzikir Pagi Petang. Download di sini: https://play.google.com/store/apps/details?id=com.example.dzikirpagipetang";

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, "Bagikan aplikasi lewat:");
        startActivity(shareIntent);
    }
    private void calApp() {
        Intent intent = new Intent(MainActivity.this, KalenderActivity.class);
        startActivity(intent);
    }

}