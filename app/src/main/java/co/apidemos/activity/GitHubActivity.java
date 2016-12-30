package co.apidemos.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.apidemos.R;

public class GitHubActivity extends AppCompatActivity {
    @BindView(R.id.editTextUserName)
    EditText editTextUserName;

    public static final String GITHUB_USERNAME="username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle(getResources().getString(R.string.github));
        creatingLayouts();

    }

    private void creatingLayouts() {
        setContentView(R.layout.activity_git_hub);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnFetch)
    public void fetchGitHubData(){
        String username=editTextUserName.getText().toString().trim();
        if(TextUtils.isEmpty(username)){
            editTextUserName.setError("Please enter user name !");
            return;
        }
        editTextUserName.setText(null);
        Intent iGitHub= new Intent(GitHubActivity.this, GitHubDetailsActivity.class);
        iGitHub.putExtra(GITHUB_USERNAME, username);
        startActivity(iGitHub);
    }

}
