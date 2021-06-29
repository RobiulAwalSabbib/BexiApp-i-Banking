package com.bcl.bexiapp_i_banking.customView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bcl.bexiapp_i_banking.R;

public class CustomAlert {

//    public void accountOpeningPreview(final Context context, String title, AccountOpeningModelForCustomAlert data){
//
//        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
//
//        if(title==null || title.equals("")){
//            title = "Alert Message";
//        }
//
//        alertDialogBuilder.setTitle(title);
//        alertDialogBuilder.setIcon(context.getResources().getDrawable(R.drawable.alert_icon));
//
//        //Custom View
//        View view = LayoutInflater.from(context).inflate(R.layout.custom_alert_view, null);
//
//
//        TextView personName = view.findViewById(R.id.tv_personName);
//        TextView motherName = view.findViewById(R.id.tv_motherName);
//        TextView fatherName = view.findViewById(R.id.tv_fatherName);
//
//
//
//        personName.setText(data.getPersonName());
//        motherName.setText(data.getMotherName());
//        fatherName.setText(data.getFatherName());
//
//
//        alertDialogBuilder.setView(view);
//
//
//        alertDialogBuilder.setCancelable(false).setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                showToast(context, "Submit Successful!");
//                dialogInterface.cancel();
//            }
//        })
//
//                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        showToast(context, "Submit Canceled");
//                        dialogInterface.cancel();
//                    }
//                });
//
//
//        // create alert dialog
//        AlertDialog alertDialog = alertDialogBuilder.create();
//
//        // show it
//        alertDialog.show();
//
//    }


    private void showToast(Context context, String message){
        Toast.makeText(context.getApplicationContext(), "Gender Clicked", Toast.LENGTH_SHORT).show();
    }

    public  void showErrorMessage(final Context context, String title, String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        if(title==null || title.equals("")){
            title = "Error Message";
        }
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setIcon(context.getResources().getDrawable(R.drawable.alert_icon));
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public  void showSuccessMessage(final Context context, String title, String message){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        if(title==null || title.equals("")){
            title = "Success Message";
        }
        alertDialogBuilder.setTitle(title);
        //alertDialogBuilder.setIcon(context.getResources().getDrawable(R.drawable.alert_icon));
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

}
