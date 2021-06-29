package com.bcl.bexiapp_i_banking.util;

import android.content.Context;
import android.text.TextUtils;

import com.bcl.bexiapp_i_banking.customView.CustomAlert;
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException;

import java.io.IOException;

public class ErrorUtil {

    public static void showError(Throwable e, Context context) {
        String message = "";
        try {
            if (e instanceof IOException) {
                message = "No internet connection!";
            } else if (e instanceof HttpException) {
                message = "Http Exception: "+e.toString();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (TextUtils.isEmpty(message)) {
            message = "Unknown error occurred! Check LogCat.";
        }

        new CustomAlert().showErrorMessage(context, "", message);
    }
}
