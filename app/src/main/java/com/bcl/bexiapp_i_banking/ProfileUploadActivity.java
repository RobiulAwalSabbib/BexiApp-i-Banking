package com.bcl.bexiapp_i_banking;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
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
import android.widget.Toast;

import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.bcl.bexiapp_i_banking.image_upload.ImageUtil;
import com.bcl.bexiapp_i_banking.image_upload.ProfileUploadDataM;
import com.bcl.bexiapp_i_banking.retrofit.ApiClient;
import com.bcl.bexiapp_i_banking.retrofit.ApiService;
import com.bcl.bexiapp_i_banking.util.ErrorUtil;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ProfileUploadActivity extends AppCompatActivity {

    final int IMAGE_CAP_CODE = 222;
    final int IMAGE_SELECTION_CODE = 333;
    final int PERMISSION_CAMERA_REQUEST_CODE = 111;
    final int PERMISSION_GELERY_REQUEST_CODE = 112;

    Button btnCapture,btn_select,btn_upload, btn_load;
    ImageView ivProfile;

    AppCompatAutoCompleteTextView edtId;
    TextInputLayout edtIdLyt;

    String imageString = "";

    //Network call
    private ApiService apiService;
    private CompositeDisposable disposable = new CompositeDisposable();
    private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_upload);


        btnCapture = findViewById(R.id.btn_cap);
        btn_select = findViewById(R.id.btn_select);
        btn_upload = findViewById(R.id.btn_upload);
        btn_load = findViewById(R.id.btn_load);

        ivProfile = findViewById(R.id.iv_profile);

        edtId = findViewById(R.id.edtId);
        edtIdLyt = findViewById(R.id.edtIdLyt);

        //Network call
        apiService = ApiClient.getService(getApplicationContext());
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please waite..");

        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA))) {
                    captureImage();
                }else{
                    requestPermission(new String[]{Manifest.permission.CAMERA},PERMISSION_CAMERA_REQUEST_CODE);
                }
            }
        });

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE))) {
                    pickImage();
                }else{
                    requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_GELERY_REQUEST_CODE);
                }
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtId.getText().toString().isEmpty()){
                    edtIdLyt.setError("Please Enter User ID..");
                    edtId.requestFocus();
                }else if(imageString.equals("")){
                    new CustomAlert().showErrorMessage(ProfileUploadActivity.this, "", "Please select or capture image first!");
                }else{
                    pDialog.show();
                    uploadProfile();
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

    private boolean checkPermission(int permission) {
        return permission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission(String[] permissions, int REQ_CODE) {
        ActivityCompat.requestPermissions(this, permissions, REQ_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            if (resultCode == RESULT_OK){
                if(requestCode == IMAGE_CAP_CODE){
                    if(data!=null){
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        ivProfile.setImageBitmap(selectedImage);

                        try{
                            imageString = ImageUtil.getStringFromBitmap(selectedImage);
                        }catch(Exception e){
                            Log.e("Error", e.toString());
                        }

                    }
                }else if(requestCode == IMAGE_SELECTION_CODE){
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
                                ivProfile.setImageBitmap(bitmap);
                                cursor.close();
                                try{
                                    imageString = ImageUtil.getStringFromBitmap(bitmap);
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

    private void showToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

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


        } else if (requestCode == PERMISSION_CAMERA_REQUEST_CODE) {
            if (grantResults.length > 0) {

                boolean external_storage_access = grantResults[0] == PackageManager.PERMISSION_GRANTED;

                if (external_storage_access) {
                    captureImage();
                } else {
                    showToast("Camera Permission denied!");
                }
            }
        }
    }

    private void uploadProfile() {


        disposable.add(
                apiService

                        //change 1
                        .uploadProfilePic(
                                edtId.getText().toString()
                                ,imageString)

                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<ProfileUploadDataM>() {//change 2

                            @Override
                            public void onSuccess(ProfileUploadDataM uplaodDataM) {//change 3

                                pDialog.dismiss();

                                //change 4
                                //Login Result
                                if (uplaodDataM.pErrorFlag.equals("N")) {
                                    new CustomAlert().showSuccessMessage(ProfileUploadActivity.this, "", uplaodDataM.pErrorMessage);
                                } else {
                                    //Failed
                                    new CustomAlert().showErrorMessage(ProfileUploadActivity.this, "", uplaodDataM.pErrorMessage);
                                }

                            }

                            @Override
                            public void onError(Throwable e) {
                                pDialog.dismiss();

                                Log.e("ProfileActivity", "onError: " + e.getMessage());
                                ErrorUtil.showError(e, ProfileUploadActivity.this);
                            }
                        }));
    }

//    private void loadProfile() {
//
//
//        disposable.add(
//                apiService
//
//                        //change 1
//                        .getImage(
//                                edtId.getText().toString())
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribeWith(new DisposableSingleObserver<ProfileImageDataM>() {//change 2
//
//                            @Override
//                            public void onSuccess(ProfileImageDataM imageDataM) {//change 3
//
//                                pDialog.dismiss();
//
//                                try{
//                                    ivProfile.setImageBitmap(ImageUtil.getImageFromString(imageDataM.iamge));
//                                }catch(Exception e){
//                                    Log.e("Error", e.toString());
//                                }
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//                                pDialog.dismiss();
//
//                                Log.e("ProfileActivity", "onError: " + e.getMessage());
//                                ErrorUtil.showError(e, ProfileUploadActivity.this);
//                            }
//                        }));
//    }


}