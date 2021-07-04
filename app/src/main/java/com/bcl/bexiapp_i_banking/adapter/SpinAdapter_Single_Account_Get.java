package com.bcl.bexiapp_i_banking.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bcl.bexiapp_i_banking.Benificier_Single_Account_Get.Single_Account_Get_Data_Model;
import com.bcl.bexiapp_i_banking.Transaction_Medium_Get_All_Data.TransactionMediumDataModel;

import java.util.List;

public class SpinAdapter_Single_Account_Get extends ArrayAdapter<Single_Account_Get_Data_Model> {

    // Your sent context
    private Context context;
    // Your custom values for the spinner (User)
    private List<Single_Account_Get_Data_Model> values;

    public SpinAdapter_Single_Account_Get(@NonNull Context context, int resource, @NonNull List<Single_Account_Get_Data_Model> objects) {
        super(context, resource, objects);

        this.context = context;
        this.values = objects;

    }


    @Override
    public int getCount(){
        return values.size();
    }

    @Override
    public Single_Account_Get_Data_Model getItem(int position){
        return values.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getBENI_ACC());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setTextColor(Color.BLACK);
        label.setText(values.get(position).getBENI_ACC());

        return label;
    }


}
