package co.apidemos.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.apidemos.R;
import co.apidemos.model.ip.IPData;
import co.apidemos.rest.APIClient;
import co.apidemos.rest.UserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IPDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tvIPDetails)
    TextView tvIPDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipdetails);

        ButterKnife.bind(this);

        loadIPDetails();
    }

    private void loadIPDetails() {
        UserEndPoints ipService= APIClient.getIPClient().create(UserEndPoints.class);

        Call<IPData> call= ipService.getIpDetails();
        call.enqueue(new Callback<IPData>() {
            @Override
            public void onResponse(Call<IPData> call, Response<IPData> response) {
                Log.e(" City ", "city=" +response.body().getCity());
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

            }

            @Override
            public void onFailure(Call<IPData> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
