package co.apidemos.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.apidemos.BaseActivity;
import co.apidemos.R;
import co.apidemos.model.ip.IPData;
import co.apidemos.rest.APIClient;
import co.apidemos.rest.UserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IPDetailsActivity extends BaseActivity implements OnMapReadyCallback{

    @BindView(R.id.tvIPDetails)
    TextView tvIPDetails;
    private GoogleMap mMap;

    private double latitude;
    private double longitude;
    private String city;
    private String ipAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipdetails);

        ButterKnife.bind(this);
        setTitle(getResources().getString(R.string.ip_details));

        loadIPDetails();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void loadIPDetails() {

        UserEndPoints ipService= APIClient.getIPClient().create(UserEndPoints.class);

        Call<IPData> call= ipService.getIpDetails();
        showProgressDialog();
        call.enqueue(new Callback<IPData>() {
            @Override
            public void onResponse(Call<IPData> call, Response<IPData> response) {
                IPData ipData=response.body();
                StringBuilder sb= new StringBuilder();
                sb.append("IP Address : "+ipData.getQuery()+"\n");
                sb.append("City : "+ipData.getCity()+"\n");
                sb.append("Region : "+ipData.getRegion()+"\n");
                sb.append("Region Name : "+ipData.getRegionName()+"\n");
                sb.append("ZIP : "+ipData.getZip()+"\n");
                sb.append("Country : "+ipData.getCountry()+"\n");
                sb.append("ISP : "+ipData.getIsp()+"\n");
                sb.append("Latitude : "+ipData.getLat()+"\n");
                sb.append("Lognitude : "+ipData.getLon()+"\n");
                sb.append("Time Zone : "+ipData.getTimezone()+"\n");
                sb.append("AS : "+ipData.getAs());

                tvIPDetails.setText(sb.toString());



                city=ipData.getCity();
                latitude=ipData.getLat();
                longitude=ipData.getLon();
                ipAddress=ipData.getQuery();

                loadMap();
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<IPData> call, Throwable t) {
                t.printStackTrace();
                hideProgressDialog();
            }
        });
    }

    private void loadMap() {

        String message=ipAddress+" \n"+latitude+":"+longitude;
        //Creating a LatLng Object to store Coordinates
        LatLng latLng = new LatLng(latitude, longitude);

        //Adding marker to map
        mMap.addMarker(new MarkerOptions()
                .position(latLng) //setting position
                .draggable(true) //Making the marker draggable
                .title(city)//Adding a title
                .snippet(message)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mobile)));


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
        mMap.addMarker(new MarkerOptions().position(ipLocation).title(city));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ipLocation,16));
    }
}

