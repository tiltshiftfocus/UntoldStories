package com.untoldstories;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.untoldstories.signup.SignupActivity;
import com.untoldstories.utils.ImageUtils;


public class LoginActivity extends ActionBarActivity {

    private TextView mSignupText;
    private ImageUtils mImageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme_Dark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // If profile image exists, remove it
        mImageHelper = new ImageUtils(this);
        if(mImageHelper.imageExists()){
            mImageHelper.removeImage();
        }

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        mSignupText = (TextView) findViewById(R.id.sign_up);
        SpannableString content = new SpannableString("Sign up");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        mSignupText.setText(content);

        mSignupText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void signUp(View v) {
        Toast.makeText(this, "Sign up placeholder", Toast.LENGTH_SHORT).show();
    }

    public void loginUser(View v) {
        Toast.makeText(this, "Log in placeholder", Toast.LENGTH_SHORT).show();
    }
}
