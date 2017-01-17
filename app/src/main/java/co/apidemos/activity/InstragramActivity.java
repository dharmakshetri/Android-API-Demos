package co.apidemos.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.apidemos.BaseActivity;
import co.apidemos.R;
import co.apidemos.model.instagram.InstagramUser;
import co.apidemos.rest.APIClient;
import co.apidemos.rest.UserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class InstragramActivity extends BaseActivity {

    private static final String TAG = "InstragramActivity" ;
    @BindView(R.id.tvInstagramUserName)
     TextView tvInstagramUserName;

    @BindView(R.id.tvInstagramFullname)
     TextView tvInstagramFullname;

    @BindView(R.id.imageViewAvatarInstagram)
    ImageView imageViewAvatarInstagram;

    @BindView(R.id.tvInstagramBio)
      TextView tvInstagramBio;

    @BindView(R.id.tvInstagramMedia)
      TextView tvInstagramMedia;

    @BindView(R.id.tvInstagramFollowedBy)
      TextView tvInstagramFollowedBy;

    @BindView(R.id.tvInstagramFollows)
      TextView tvInstagramFollows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getResources().getString(R.string.instagram));
        creatingLayouts();

        //load data from api

        loadInstagramData();
    }

    private void loadInstagramData() {
        UserEndPoints userEndPoint= APIClient.getInstagramClient().create(UserEndPoints.class);
        Call<InstagramUser> call= userEndPoint.getInstagramUser(APIClient.INSTAGRAM_AUTH_TOKEN);
        showProgressDialog();
        call.enqueue(new Callback<InstagramUser>() {
            @Override
            public void onResponse(Call<InstagramUser> call, Response<InstagramUser> response) {

                //Log.e("RESPONSE", response.body());
               // List<InstagramUser> users=response;

                if (response != null) {
                    Log.e(TAG,"Response Code: "+response.code());
                    InstagramUser instagramUser= response.body();
                    tvInstagramFullname.setText(instagramUser.getData().getUsername());

                    tvInstagramFullname.setText(instagramUser.getData().getFullName());

                    tvInstagramBio.setText(instagramUser.getData().getBio());

                    tvInstagramFollowedBy.setText(getResources().getString(R.string.followed_by)+" : "+instagramUser.getData().getCounts().getFollowedBy());

                    tvInstagramFollows.setText(getResources().getString(R.string.followers)+" : "+instagramUser.getData().getCounts().getFollows());



                    //loading profile images
                    Picasso.with(getApplicationContext())
                            .load(instagramUser.getData().getProfilePicture())
                            .into(imageViewAvatarInstagram);
                }

            }

            @Override
            public void onFailure(Call<InstagramUser> call, Throwable t) {

            }
        });
        hideProgressDialog();
    }

    private void creatingLayouts() {
        setContentView(R.layout.activity_instragram);

        ButterKnife.bind(this);
    }
}
