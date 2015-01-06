package com.untoldstories.signup;

import android.app.Activity;
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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.untoldstories.R;
import com.untoldstories.utils.ImageUtils;
import com.untoldstories.utils.S3Cred;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;


public class SignupActivity extends ActionBarActivity {

    private Toolbar toolbar;

    private static final int REQUEST_CODE = 1;
    private Bitmap mBitmap;
    private CircleImageView mUserProfilePic;
    private Spinner mGenderList;
    private EditText name, email, password;

    private ImageUtils mImageHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mImageHelper = new ImageUtils(this);

        name = (EditText) findViewById(R.id.name_signup);
        email = (EditText) findViewById(R.id.email_signup);
        password = (EditText) findViewById(R.id.password_signup);

        // Set Support Toolbar
        setUpToolbar();

        mUserProfilePic = (CircleImageView) findViewById(R.id.user_image);

        mUserProfilePic.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        mGenderList = (Spinner) findViewById(R.id.gender_signup);
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this, R.array.gender, android.R.layout.simple_spinner_item);
        mAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderList.setAdapter(mAdapter);
        mGenderList.setOnItemSelectedListener(new GenderSpinner(this));
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK)
            try {
                // We need to recyle unused bitmaps
                if (mBitmap != null) {
                    mBitmap.recycle();
                }
                InputStream stream = getContentResolver().openInputStream(
                        data.getData());
                mBitmap = BitmapFactory.decodeStream(stream);
                stream.close();

                mBitmap = getCroppedBitmap(mBitmap, mBitmap.getWidth());
                mImageHelper.createImageFromBitmap(mBitmap);

                mUserProfilePic.setImageBitmap(mBitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius)
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        else
            sbmp = bmp;
        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(),
                sbmp.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xffa19774;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f,
                sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);


        return output;
    }

    public void setDOB(View v) {
        DialogFragment datePicker = new DatePickerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("buttonID", R.id.birthday_signup);

        datePicker.setArguments(bundle);

        datePicker.show(getFragmentManager(), "datePicker");
        //intent.putExtra("dob", ((Button) v).getText().toString());
    }

    public void signUpNow(View v) {
        //Toast.makeText(this,"Sign up placeholder",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignupActivity.this, AttachActivity.class);
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("password", password.getText().toString());
        intent.putExtra("gender", mGenderList.getSelectedItemPosition());
        intent.putExtra("dob", ((Button) findViewById(R.id.birthday_signup)).getText().toString());

        startActivity(intent);
    }


}
