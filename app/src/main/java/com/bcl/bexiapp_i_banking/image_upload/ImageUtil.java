package com.bcl.bexiapp_i_banking.image_upload;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class ImageUtil {

    public static Bitmap getImageFromString(String stringImage) throws NullPointerException{
        byte[] decodedString = Base64.decode(stringImage,Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedImage;
    }

    public static String getStringFromBitmap(Bitmap bitmapImage) throws NullPointerException{
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();
        //String encodedStringImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        String encodedStringImage = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return encodedStringImage;
    }

}
