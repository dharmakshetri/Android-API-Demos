package co.apidemos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import co.apidemos.R;

/**
 * Created by dharma kshetri on 12/27/16.
 */
public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayoutStackOverFlow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    creatingLayouts();
    }

    public void creatingLayouts(){
        setContentView(R.layout.activity_main);
       // linearLayoutStackOverFlow=(LinearLayout) findViewById(R.id.)
    }
    // stackoverflow onclick
    public void onClickStackOverFlow(View view){

        Intent intentStackOverFlow= new Intent(MainActivity.this, StackOverFlowActivity.class);
        startActivity(intentStackOverFlow);
    }

    // github onclick
    public void onClickGitHub(View view){

        Intent intentGitHub= new Intent(MainActivity.this, GitHubActivity.class);
        startActivity(intentGitHub);
    }

}
