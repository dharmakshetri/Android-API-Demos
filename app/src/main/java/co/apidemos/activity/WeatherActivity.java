package co.apidemos.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.apidemos.BaseActivity;
import co.apidemos.R;
import co.apidemos.model.weather.Weather;
import co.apidemos.rest.APIClient;
import co.apidemos.rest.UserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends BaseActivity  {

    public static final String TAG="WeatherActivity";
    @BindView(R.id.editTextCity)
    EditText editTextCity;

    @BindView(R.id.textViewWeatherData)
    TextView textViewWeatherData;

    String strCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.weather));

    }

    @OnClick(R.id.buttonGetWeather)
    public void onClickGet(View view){
        strCity=editTextCity.getText().toString().trim();
        if(TextUtils.isEmpty(strCity)){
            editTextCity.setError("Please enter city");
            return;
        }
        loadWeatherData();
        editTextCity.setText(null);
    }

    private void loadWeatherData() {

        UserEndPoints userEndPoints= APIClient.getWeatherClient().create(UserEndPoints.class);

        Call<Weather> call=userEndPoints.getWeatherData(strCity, APIClient.WEATHER_API_KEY);
        showProgressDialog();
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                StringBuilder sb= new StringBuilder();
                sb.append("City: "+response.body().getName()+"\n");
                sb.append("Country: "+response.body().getSys().getCountry()+"\n");
                sb.append("Temperature :"+response.body().getMain().getTemp()+"\n");
                sb.append("Humedity (%) :"+response.body().getMain().getHumidity()+"\n");
                sb.append("Speed : "+response.body().getWind().getSpeed()+"\n");
                sb.append("Decription : "+response.body().getWeather().get(0).getDescription()+"\n");

                textViewWeatherData.setText(sb.toString());

                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG,"onFailure "+t.toString());
            }
        });

    }

}
