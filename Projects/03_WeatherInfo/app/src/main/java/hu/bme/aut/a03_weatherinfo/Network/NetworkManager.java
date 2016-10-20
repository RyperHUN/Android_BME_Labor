package hu.bme.aut.a03_weatherinfo.Network;

import hu.bme.aut.a03_weatherinfo.model.WeatherData;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ryper on 2016. 10. 20..
 */
public class NetworkManager {
    private static final String ENDPOINT_ADDRESS = "http://api.openweathermap.org";
    private static final String APP_ID = "f3d694bc3e1d44c1ed5a97bd1120e8fe";
    private static NetworkManager instance;

    public static NetworkManager getInstance() {
        if (instance == null) {
            instance = new NetworkManager();
        }
        return instance;
    }

    private Retrofit retrofit;
    private WeatherApi weatherApi;

    private NetworkManager() {
        retrofit = new Retrofit.Builder().baseUrl(ENDPOINT_ADDRESS).client
                (new OkHttpClient.Builder().build()).addConverterFactory
                (GsonConverterFactory.create()).build();
        weatherApi = retrofit.create(WeatherApi.class);
    }

    public Call<WeatherData> getWeather(String city) {
        return weatherApi.getWeather(city, "metric", APP_ID);
    }
}