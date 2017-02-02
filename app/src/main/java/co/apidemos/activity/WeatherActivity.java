package co.apidemos.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

public class WeatherActivity extends BaseActivity implements OnMapReadyCallback {

    public static final String TAG="WeatherActivity";
    @BindView(R.id.editTextCity)
    EditText editTextCity;

    @BindView(R.id.textViewWeatherData)
    TextView textViewWeatherData;

    String strCity;
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.weather));

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        mapFragment.getView().setVisibility(View.GONE);
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
                latitude=response.body().getCoord().getLat();
                longitude=response.body().getCoord().getLon();

                hideProgressDialog();
                mapFragment.getView().setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e(TAG,"onFailure "+t.toString());
            }
        });

    }

    private void loadMap() {

        String message=strCity+" \n"+latitude+":"+longitude;
        //Creating a LatLng Object to store Coordinates
        LatLng latLng = new LatLng(latitude, longitude);

        //Adding marker to map
        mMap.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .draggable(true) //Making the marker draggable
                .title(strCity)//Adding a title
                .snippet(message)
        );


        //Moving the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng ipLocation = new LatLng(longitude, longitude);
        mMap.addMarker(new MarkerOptions().position(ipLocation).title(strCity));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ipLocation,16));

    }
}
