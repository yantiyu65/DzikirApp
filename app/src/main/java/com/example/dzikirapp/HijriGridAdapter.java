package com.example.dzikirapp;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HijriGridAdapter extends BaseAdapter {
    private final Context context;
    private final List<String> days;
    private final int currentMonth; // Sudah pakai 1–12 dari activity
    private final int currentYear;

    public HijriGridAdapter(Context context, List<String> days, int currentMonth, int currentYear) {
        this.context = context;
        this.days = days;
        this.currentMonth = currentMonth;
        this.currentYear = currentYear;
    }

    // Hari-hari besar Islam (format: "tanggal-bulan")
    private final Map<String, String> hariBesarMap = new HashMap<String, String>() {{
        put("1-1", "Tahun Baru Hijriah");
        put("10-1", "Hari Asyura");
        put("12-3", "Maulid Nabi Muhammad SAW");
        put("27-7", "Isra' Mi'raj");
        put("1-9", "Awal Ramadhan");
        put("17-9", "Nuzulul Qur'an");
        put("1-10", "Idul Fitri");
        put("10-12", "Idul Adha");
    }};

    @Override
    public int getCount() {
        return days.size();
    }

    @Override
    public Object getItem(int position) {
        return days.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView dayView;
        if (convertView == null) {
            dayView = new TextView(context);
            dayView.setLayoutParams(new ViewGroup.LayoutParams(130, 130));
            dayView.setPadding(8, 8, 8, 8);
            dayView.setTextSize(16);
            dayView.setGravity(Gravity.CENTER);
        } else {
            dayView = (TextView) convertView;
        }

        String dayText = days.get(position).trim();
        dayView.setText(dayText);
        dayView.setBackgroundColor(Color.LTGRAY);
        dayView.setTextColor(Color.BLACK);
        dayView.setOnClickListener(null); // Reset default

        // 🔶 Cek Hari Besar
        String key = dayText + "-" + currentMonth;
        if (hariBesarMap.containsKey(key)) {
            dayView.setBackgroundColor(Color.parseColor("#FFECB3")); // Kuning muda
            dayView.setTextColor(Color.BLACK);
            dayView.setOnClickListener(v ->
                    Toast.makeText(context, "Hari Besar: " + hariBesarMap.get(key), Toast.LENGTH_SHORT).show()
            );
        }

        // ✅ Cek Hari Ini (Hijriah)
        int[] todayHijri = UmmalquraCalender.getHijriArray();
        int todayDay = todayHijri[2];
        int todayMonth = todayHijri[1]; // 1–12
        int todayYear = todayHijri[0];

        if (Integer.parseInt(dayText) == todayDay &&
                currentMonth == todayMonth &&
                currentYear == todayYear) {
            dayView.setBackgroundColor(Color.parseColor("#A5D6A7")); // Hijau muda
            dayView.setTextColor(Color.BLACK);
        }

        return dayView;
    }
}
