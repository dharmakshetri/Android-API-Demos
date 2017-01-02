package co.apidemos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.apidemos.BaseActivity;
import co.apidemos.R;
import co.apidemos.adapter.GitHubAdapter;
import co.apidemos.model.github.GitHubData;
import co.apidemos.model.github.GitHubUser;
import co.apidemos.rest.APIClient;
import co.apidemos.rest.UserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitHubDetailsActivity extends BaseActivity {

    Intent intent;
    String username;

    @BindView(R.id.imageViewAvatar)
    ImageView imageViewAvatar;

    @BindView(R.id.tvGitHubUserName)
    TextView tvGitHubUserName;

    @BindView(R.id.tvGitHubLogin)
    TextView tvGitHubLogin;

    @BindView(R.id.tvGitHubEmail)
    TextView tvGitHubEmail;

    @BindView(R.id.tvGitHubFollowers)
    TextView tvGitHubFollowers;

    @BindView(R.id.tvGitHubFollowing)
    TextView tvGitHubFollowing;

    @BindView(R.id.repo_recycler_view)
    RecyclerView recyclerView;

    RecyclerView.Adapter githubAdapter;

    List<GitHubData> myRepoData= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getResources().getString(R.string.github));

        creatingLayouts();
        ButterKnife.bind(this);

        intent=getIntent();
        username=intent.getStringExtra(GitHubActivity.GITHUB_USERNAME);

        loadUserData();

        loadRepoData(username);
    }


    // load the user details

    private void loadUserData() {
        UserEndPoints userEndPoint= APIClient.getGitHubClient().create(UserEndPoints.class);
        Call<GitHubUser> call=userEndPoint.getGitHubUser(username);
        showProgressDialog();
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                tvGitHubUserName.setText(response.body().getName());
                tvGitHubLogin.setText(response.body().getLogin());
                tvGitHubEmail.setText(response.body().getEmail());
                tvGitHubFollowers.setText("Followers: "+response.body().getFollowers());
                tvGitHubFollowing.setText("Following: "+response.body().getFollowing());

                Picasso.with(getApplicationContext())
                        .load(response.body().getAvatarUrl())
                        .into(imageViewAvatar);

            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }

    // load the repositry details
    private void loadRepoData(String name) {

        UserEndPoints userEndPoint=APIClient.getGitHubClient().create(UserEndPoints.class);
        Call<List<GitHubData>> call= userEndPoint.getRepositories(name);
        call.enqueue(new Callback<List<GitHubData>>() {
            @Override
            public void onResponse(Call<List<GitHubData>> call, Response<List<GitHubData>> response) {

                List<GitHubData> data=response.body();
                /*for (GitHubData g: data){
                    Log.e("Name"," "+g.getName());
                    Log.e("Desc"," "+g.getDescription());
                    Log.e("Lang"," "+g.getLanguage());
                }*/
                  myRepoData.clear();
                  myRepoData.addAll(data);
                  githubAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<GitHubData>> call, Throwable t) {
                    t.printStackTrace();
            }
        });

        hideProgressDialog();

    }

    // creating the layouts
    private void creatingLayouts() {

        setContentView(R.layout.activity_git_hub_details);

        recyclerView=(RecyclerView) findViewById(R.id.repo_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        githubAdapter= new GitHubAdapter(getApplicationContext(),R.layout.github_repo_list_item, myRepoData );
        recyclerView.setAdapter(githubAdapter);

    }
}
