package com.paulo.toque_do_chef;

public class WeatherCodeHelper {
    public static String getDescription(int code) {
        if (code == 0) return "céu limpo";
        else if (code >= 1 && code <= 3) return "parcialmente nublado";
        else if (code >= 45 && code <= 48) return "neblina";
        else if (code >= 51 && code <= 55) return "garoa";
        else if (code >= 61 && code <= 63) return "chuva";
        else if (code >= 71 && code <= 75) return "neve";
        else if (code == 95) return "tempestade";
        else return "clima desconhecido";
    }
}
