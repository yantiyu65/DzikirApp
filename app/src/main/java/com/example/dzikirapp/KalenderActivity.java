package com.example.dzikirapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class KalenderActivity extends AppCompatActivity {

    int currentHijriYear;
    int currentHijriMonth;
    GridView gridHijriah;
    TextView textKalender, btnPrev, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalender);

        textKalender = findViewById(R.id.textKalender);
        gridHijriah = findViewById(R.id.gridHijriah);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);


        Toolbar toolbar = findViewById(R.id.back_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> finish());
        getSupportActionBar().setTitle("Kalender Hijriah");

        int[] hijriNow = UmmalquraCalender.getHijriArray();
        currentHijriYear = hijriNow[0];
        currentHijriMonth = hijriNow[1] - 1;

        showCalendar(currentHijriYear, currentHijriMonth);

        btnPrev.setOnClickListener(v -> {
            currentHijriMonth--;
            if (currentHijriMonth < 0) {
                currentHijriMonth = 11;
                currentHijriYear--;
            }
            showCalendar(currentHijriYear, currentHijriMonth);
        });

        btnNext.setOnClickListener(v -> {
            currentHijriMonth++;
            if (currentHijriMonth > 11) {
                currentHijriMonth = 0;
                currentHijriYear++;
            }
            showCalendar(currentHijriYear, currentHijriMonth);
        });
    }

    private void showCalendar(int year, int month) {
        String namaBulan = UmmalquraCalender.getNamaBulan(month + 1);
        textKalender.setText(namaBulan + " " + year + " H");

        List<String> tanggal = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            tanggal.add(String.valueOf(i));
        }

        HijriGridAdapter adapter = new HijriGridAdapter(this, tanggal, month + 1, year);
        gridHijriah.setAdapter(adapter);
    }
}
