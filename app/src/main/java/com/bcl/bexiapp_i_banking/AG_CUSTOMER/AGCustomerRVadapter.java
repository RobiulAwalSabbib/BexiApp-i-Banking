package com.bcl.bexiapp_i_banking.AG_CUSTOMER;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcl.bexiapp_i_banking.R;

import java.util.List;

public class AGCustomerRVadapter extends RecyclerView.Adapter<AGCustomerRVadapter.ViewHolder> {

    List<GetDataModel> data;

    public AGCustomerRVadapter(List<GetDataModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public AGCustomerRVadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ag_customer_getall_data,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AGCustomerRVadapter.ViewHolder holder, int position) {

        final GetDataModel dataM = data.get(position);

        holder.tv_ag_customer_custID.setText(dataM.custId);
        holder.tv_ag_customer_custName.setText(dataM.custName);
        holder.tv_ag_customer_custGender.setText(dataM.custGender);
        holder.tv_ag_customer_custDOB.setText(dataM.custDob);

    }

    @Override
    public int getItemCount() {
        return  data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_ag_customer_custID,tv_ag_customer_custName,tv_ag_customer_custGender,tv_ag_customer_custDOB;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ag_customer_custID = itemView.findViewById(R.id.tv_ag_customer_custID);
            tv_ag_customer_custName = itemView.findViewById(R.id.tv_ag_customer_custName);
            tv_ag_customer_custGender = itemView.findViewById(R.id.tv_ag_customer_custGender);
            tv_ag_customer_custDOB = itemView.findViewById(R.id.tv_ag_customer_custDOB);
        }
    }
}
