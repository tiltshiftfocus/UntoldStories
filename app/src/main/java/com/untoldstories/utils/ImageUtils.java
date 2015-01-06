package com.untoldstories.utils;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by zm on 11/12/2014.
 */
public class ImageUtils {

    private String imageName = "profileImage";
    private Context context;
    private File image;

    public ImageUtils(Context context){
        this.context = context;
        image = context.getFileStreamPath(imageName);
    }

    public String createImageFromBitmap(Bitmap bitmap) {

        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            FileOutputStream fo = context.openFileOutput(imageName, Context.MODE_PRIVATE);
            fo.write(bytes.toByteArray());
            // remember close file output
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
            imageName = null;
        }
        return imageName;
    }

    public boolean imageExists() {
        if(image.exists()){
            return true;
        }
        return false;
    }

    public void removeImage(){
        image.delete();
    }

    public File getImage(){
        return image;
    }

}
