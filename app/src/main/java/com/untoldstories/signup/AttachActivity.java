package com.untoldstories.signup;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.untoldstories.R;
import com.untoldstories.utils.ImageUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class AttachActivity extends ActionBarActivity {

    private ImageUtils mImageHelper;

    private Toolbar toolbar;

    private static final int REQUEST_CODE = 2;
    private Bitmap mBitmap;
    private CircleImageView mUserProfilePic;
    private Spinner mGenderList;
    private Button accept, reject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attach);
        mImageHelper = new ImageUtils(this);

        // Set Support Toolbar
        setUpToolbar();

        // Get Intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String password = intent.getStringExtra("password");
        int gender = intent.getIntExtra("gender", 0);
        String dob = intent.getStringExtra("dob");

        try {
            mBitmap = BitmapFactory.decodeStream(this.openFileInput("profileImage"));
        } catch (FileNotFoundException e) {
            Toast.makeText(this,"No Profile Image Set",Toast.LENGTH_SHORT).show();
        }

        mUserProfilePic = (CircleImageView) findViewById(R.id.user_image_attach);
        if(mImageHelper.imageExists()) {
            mUserProfilePic.setImageBitmap(mBitmap);
        }



        ((EditText) findViewById(R.id.name_attach)).setText(name.toString());
        (findViewById(R.id.name_attach)).setEnabled(false);
        ((EditText) findViewById(R.id.email_attach)).setText(email.toString());
        (findViewById(R.id.email_attach)).setEnabled(false);
        (findViewById(R.id.password_attach)).setEnabled(false);
        ((EditText) findViewById(R.id.password_attach)).setText(password.toString());
        ((Button) findViewById(R.id.birthday_attach)).setText(dob.toString());


        mGenderList = (Spinner) findViewById(R.id.gender_attach);
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderList.setAdapter(mAdapter);
        mGenderList.setSelection(gender);
        mGenderList.setEnabled(false);


        final Dialog dialog = new Dialog(this);
        Window window = dialog.getWindow();
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.customdialog);
        dialog.show();
        accept = (Button) dialog.findViewById(R.id.accept);
        reject = (Button) dialog.findViewById(R.id.reject);


        /*mUserProfilePic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });*/
        accept.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //When Yes button is clicked
                Toast.makeText(getBaseContext(), "Yes Button Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        reject.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //When No button is clicked
                dialog.dismiss();
            }
        });

        /*mGenderList = (Spinner) findViewById(R.id.gender_attach);
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,R.array.gender,android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderList.setAdapter(mAdapter);
        mGenderList.setOnItemSelectedListener(new GenderSpinner(this));*/

    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void attachNow(View v) {
        //Toast.makeText(this, "Attach placeholder", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(AttachActivity.this,SetAnniversaryActivity.class);
        //i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
