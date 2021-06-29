package com.bcl.bexiapp_i_banking.ekyc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bcl.bexiapp_i_banking.R;
import com.bcl.bexiapp_i_banking.image_upload.ImageUtil;

public class Ekyc_Nid_Picture_Upload_Activity extends AppCompatActivity {

    final int IMAGE_CAP_CODE = 222;
    final int IMAGE_SELECTION_CODE = 333;
    final int PERMISSION_CAMERA_REQUEST_CODE = 111;
    final int PERMISSION_GELERY_REQUEST_CODE = 112;

    final int IMAGE_CAP_CODE2 = 444;
    final int IMAGE_SELECTION_CODE2 = 555;
    final int PERMISSION_CAMERA_REQUEST_CODE2 = 666;
    final int PERMISSION_GELERY_REQUEST_CODE2 = 777;

    TextView tv_ekyc_nid,tv_ekyc_applicant_name,tv_ekyc_applicant_dob;

    SharedPreferences sharedPreferences;

    ImageView iv_ekyc_front_side_picture,iv_ekyc_back_side_picture;

    String imageString1 = "";
    String imageString2 = "";

    Button btn_ekyc_nid_picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ekyc__nid__picture_upload);

        tv_ekyc_nid = findViewById(R.id.tv_ekyc_nid);
        tv_ekyc_applicant_name = findViewById(R.id.tv_ekyc_applicant_name);
        tv_ekyc_applicant_dob = findViewById(R.id.tv_ekyc_applicant_dob);

        iv_ekyc_front_side_picture = findViewById(R.id.iv_ekyc_front_side_picture);
        iv_ekyc_back_side_picture = findViewById(R.id.iv_ekyc_back_side_picture);


        btn_ekyc_nid_picture = findViewById(R.id.btn_ekyc_nid_picture);
        btn_ekyc_nid_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Ekyc_Nid_Picture_Upload_Activity.this, Ekyc_Nominee_Information_Activity.class);
                startActivity(intent);
            }
        });



        sharedPreferences = getSharedPreferences("ekyc_app_session", MODE_PRIVATE);

        tv_ekyc_nid.setText(sharedPreferences.getString("ekyc_nid",""));
        tv_ekyc_applicant_name.setText(sharedPreferences.getString("ekyc_name",""));
        tv_ekyc_applicant_dob.setText(sharedPreferences.getString("ekyc_dob",""));

        iv_ekyc_front_side_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPermission(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA))) {
                    captureImage();
                }else{
                    requestPermission(new String[]{Manifest.permission.CAMERA},PERMISSION_CAMERA_REQUEST_CODE);
                }
            }
        });


        iv_ekyc_back_side_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkPermission(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA))) {
                    captureImage2();
                }else{
                    requestPermission(new String[]{Manifest.permission.CAMERA},PERMISSION_CAMERA_REQUEST_CODE2);
                }
            }
        });

    }



    private void pickImage(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, IMAGE_SELECTION_CODE);
    }

    private void captureImage(){
        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, IMAGE_CAP_CODE);
    }


    private void pickImage2(){
        Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickPhoto, IMAGE_SELECTION_CODE2);
    }

    private void captureImage2(){
        Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicture, IMAGE_CAP_CODE2);
    }

    private boolean checkPermission(int permission) {
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String[] permissions, int REQ_CODE) {
        ActivityCompat.requestPermissions(this, permissions, REQ_CODE);
    }


    //////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            if (resultCode == RESULT_OK){
                if(requestCode == IMAGE_CAP_CODE){
                    if(data!=null){
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        iv_ekyc_front_side_picture.setImageBitmap(selectedImage);

                        try{
                            imageString1 = ImageUtil.getStringFromBitmap(selectedImage);
                        }catch(Exception e){
                            Log.e("Error", e.toString());
                        }

                    }
                }else  if(requestCode == IMAGE_CAP_CODE2){
                    if(data!=null){
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        iv_ekyc_back_side_picture.setImageBitmap(selectedImage);

                        try{
                            imageString2 = ImageUtil.getStringFromBitmap(selectedImage);
                        }catch(Exception e){
                            Log.e("Error", e.toString());
                        }

                    }
                }
                    else if(requestCode == IMAGE_SELECTION_CODE){
                    if(data!=null){
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                                iv_ekyc_front_side_picture.setImageBitmap(bitmap);
                                cursor.close();
                                try{
                                    imageString1 = ImageUtil.getStringFromBitmap(bitmap);
                                }catch(Exception e){
                                    Log.e("Error", e.toString());
                                }
                            }
                        }
                    }
                }  else if(requestCode == IMAGE_SELECTION_CODE2){
                    if(data!=null){
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                                iv_ekyc_back_side_picture.setImageBitmap(bitmap);
                                cursor.close();
                                try{
                                    imageString2 = ImageUtil.getStringFromBitmap(bitmap);
                                }catch(Exception e){
                                    Log.e("Error", e.toString());
                                }
                            }
                        }
                    }
                }
            }
        }else{
            showToast("Action Canceled");
        }
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_GELERY_REQUEST_CODE) {

            if (grantResults.length > 0) {

                boolean external_storage_access = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (external_storage_access) {
                    pickImage();
                } else {
                    showToast("Gallary Permission denied!");
                }
            }


        } if (requestCode == PERMISSION_GELERY_REQUEST_CODE2) {

            if (grantResults.length > 0) {

                boolean external_storage_access = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (external_storage_access) {
                    pickImage2();
                } else {
                    showToast("Gallary Permission denied!");
                }
            }


        }
        else if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0) {

                boolean external_storage_access = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (external_storage_access) {
                    captureImage();
                } else {
                    showToast("Camera Permission denied!");
                }
            }
        }else if (requestCode == PERMISSION_CAMERA_REQUEST_CODE2) {
            if (grantResults.length > 0) {

                boolean external_storage_access = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (external_storage_access) {
                    captureImage2();
                } else {
                    showToast("Camera Permission denied!");
                }
            }
        }
    }

    //////////////////////////////////////////////////////////////////////////////////////////

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}