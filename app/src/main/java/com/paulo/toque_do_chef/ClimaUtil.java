package com.paulo.toque_do_chef;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class ClimaUtil {

    public static void buscarEExibirClima(Context context) {
        String url = "https://api.open-meteo.com/v1/forecast?latitude=-23.55&longitude=-46.63&current_weather=true";

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        JSONObject currentWeather = response.getJSONObject("current_weather");
                        double temperature = currentWeather.getDouble("temperature");
                        int weatherCode = currentWeather.getInt("weathercode");

                        WeatherInfo weatherInfo = new WeatherInfo(temperature, weatherCode);
                        exibirPopUpClima(context, weatherInfo);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                },
                error -> Toast.makeText(context, "Erro ao obter clima", Toast.LENGTH_SHORT).show()
        );

        queue.add(request);
    }

    private static void exibirPopUpClima(Context context, WeatherInfo info) {
        String descricao = WeatherCodeHelper.getDescription(info.getWeatherCode());
        String mensagem1 = "☀️ Perfeito para cozinhar hoje: " + info.getTemperature() + "°C e " + descricao + "\n\n";
        String mensagem3 = "🌧️ Hoje o tempo está: " + info.getTemperature() + "°C e " + descricao + ". Que tal um prato especial? \n\n";
        String mensagem4 = "❄️ O friozinho chegou! " + info.getTemperature() + "°C e " + descricao + ". Vamos cozinhar algo quentinho! \n\n";
        String mensagem5 = "🌈 Clima do dia: " + info.getTemperature() + "°C e " + descricao + ". Inspire-se para cozinhar! \n\n";
        String mensagem10 = "🥶 Está " + info.getTemperature() + "°C e " + descricao + ". Que tal uma receita para aquecer? \n\n";
        String mensagem = null;



        // Sugestões de pratos dependendo do clima
        if (info.getWeatherCode() == 0) { // céu limpo (sol)
             mensagem = mensagem1 += "Que tal um suco gelado ou um delicioso frango na mostarda?";
        } else if (info.getWeatherCode() >= 1 && info.getWeatherCode() <= 3) {
            mensagem = mensagem3 += "Uma sopa leve ou um prato com legumes cairia bem.";
        } else if (info.getWeatherCode() >= 61 && info.getWeatherCode() <= 63) { // chuva
            mensagem = mensagem4 += "Melhor algo quentinho, como um caldo ou uma sopa.";
        } else if (info.getWeatherCode() >= 71 && info.getWeatherCode() <= 75) { // neve
            mensagem = mensagem10 += "Um chocolate quente e uma sopa reconfortante!";
        } else {
            mensagem = mensagem5 += "Use a criatividade! Que tal algo especial hoje?";
        }

        new AlertDialog.Builder(context)
                .setTitle("Clima do dia")
                .setMessage(mensagem)
                .setPositiveButton("OK", null)
                .show();
    }

}

