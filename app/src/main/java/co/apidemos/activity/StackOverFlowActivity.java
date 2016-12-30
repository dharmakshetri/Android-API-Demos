package co.apidemos.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.apidemos.BaseActivity;
import co.apidemos.R;
import co.apidemos.adapter.StackOverFlowAdapter;
import co.apidemos.model.StackOverFlowData;
import co.apidemos.model.StackOverFlowUser;
import co.apidemos.rest.APIClient;
import co.apidemos.rest.UserEndPoints;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dharma kshetri on 12/27/16.
 */
public class StackOverFlowActivity extends BaseActivity  {

    @BindView(R.id.emptyTextView)
    TextView emptyTextView;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<StackOverFlowUser> myDataSource= new ArrayList<>();
    RecyclerView.Adapter myAdater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setTitle(getApplicationContext().getResources().getString(R.string.stackoverflow));
        creatingLayouts();
        ButterKnife.bind(this);

        loadUsers();
    }

    private void loadUsers() {
        UserEndPoints apiService= APIClient.getStackOverFLowClient().create(UserEndPoints.class);
        Call<StackOverFlowData> call= apiService.getUsers("reputation");
        showProgressDialog();
        call.enqueue(new Callback<StackOverFlowData>() {
            @Override
            public void onResponse(Call<StackOverFlowData> call, Response<StackOverFlowData> response) {

                List<StackOverFlowUser> users=response.body().getUsers();

                myDataSource.clear();
                myDataSource.addAll(response.body().getUsers());
                myAdater.notifyDataSetChanged();
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<StackOverFlowData> call, Throwable t) {
                t.printStackTrace();
                emptyTextView.setText(t.toString());

            }
        });
    }

    private void creatingLayouts() {
        setContentView(R.layout.activity_stackoverflow);
        recyclerView=(RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdater=new StackOverFlowAdapter(getApplicationContext(),myDataSource, R.layout.stackoverflow_list_items);
        recyclerView.setAdapter(myAdater);
    }

}
