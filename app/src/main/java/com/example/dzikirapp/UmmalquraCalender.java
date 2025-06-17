package com.example.dzikirapp;

import java.util.Calendar;

public class UmmalquraCalender {
    private static final String[] bulanHijriah = {
            "Muharram", "Safar", "Rabiul Awal", "Rabiul Akhir",
            "Jumadil Awal", "Jumadil Akhir", "Rajab", "Sya'ban",
            "Ramadhan", "Syawal", "Dzulqaidah", "Dzulhijjah"
    };

    public static String getTanggalHijriah() {
        Calendar calendar = Calendar.getInstance();
        int julianDay = gregorianToJD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        int[] hijri = jdToHijri(julianDay);
        return hijri[2] + " " + bulanHijriah[hijri[1] - 1] + " " + hijri[0] + " H";
    }

    // Konversi Gregorian ke Julian Day
    private static int gregorianToJD(int y, int m, int d) {
        return (1461 * (y + 4800 + (m - 14) / 12)) / 4 +
                (367 * (m - 2 - 12 * ((m - 14) / 12))) / 12 -
                (3 * ((y + 4900 + (m - 14) / 12) / 100)) / 4 + d - 32075;
    }

    // Konversi Julian Day ke Hijriah
    private static int[] jdToHijri(int jd) {
        int l = jd - 1948440 + 10632;
        int n = (l - 1) / 10631;
        l = l - 10631 * n + 354;
        int j = ((10985 - l) / 5316) * ((50 * l) / 17719) + (l / 5670) * ((43 * l) / 15238);
        l = l - ((30 - j) / 15) * ((17719 * j) / 50) - (j / 16) * ((15238 * j) / 43) + 29;
        int m = (24 * l) / 709;
        int d = l - (709 * m) / 24;
        int y = 30 * n + j - 30;
        return new int[]{y, m, d};
    }
    public static int[] getHijriArray() {
        Calendar calendar = Calendar.getInstance();
        int julianDay = gregorianToJD(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1, calendar.get(Calendar.DAY_OF_MONTH));
        return jdToHijri(julianDay);
    }
    public static String getNamaBulan(int bulanKe) {
        String[] bulanHijriah = {
                "Muharram", "Safar", "Rabiul Awal", "Rabiul Akhir",
                "Jumadil Awal", "Jumadil Akhir", "Rajab", "Sya'ban",
                "Ramadhan", "Syawal", "Dzulqaidah", "Dzulhijjah"
        };
        if (bulanKe >= 1 && bulanKe <= 12) {
            return bulanHijriah[bulanKe - 1];
        } else {
            return "Bulan Tidak Diketahui";
        }
    }

}
