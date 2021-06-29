package com.bcl.bexiapp_i_banking.TranDetail_Get;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bcl.bexiapp_i_banking.R;

import java.util.ArrayList;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.ViewHolder> {

    ArrayList<StatementDataModel> adapterDataList;

    public StatementAdapter(ArrayList<StatementDataModel> data) {
        adapterDataList = data;
    }


    @NonNull
    @Override
    public StatementAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.statement_item_view,parent,false);
        return new StatementAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatementAdapter.ViewHolder holder, int position) {


        final StatementDataModel menuData = adapterDataList.get(position);

        holder.tv_trn_no.setText(menuData.tnumber);
        holder.tv_trn_date.setText(menuData.dot);
        holder.tv_trn_type.setText(menuData.transactionType);
        holder.tv_trn_amount.setText(menuData.transactionAmount);

    }

    @Override
    public int getItemCount() {
        return adapterDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout lo_st_parrent;
        TextView tv_trn_no;
        TextView tv_trn_date;
        TextView tv_trn_type;
        TextView tv_trn_amount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lo_st_parrent = itemView.findViewById(R.id.lo_st_parrent);
            tv_trn_no = itemView.findViewById(R.id.tv_trn_no);
            tv_trn_date = itemView.findViewById(R.id.tv_trn_date);
            tv_trn_type = itemView.findViewById(R.id.tv_trn_type);
            tv_trn_amount = itemView.findViewById(R.id.tv_trn_amount);

        }
    }
}
