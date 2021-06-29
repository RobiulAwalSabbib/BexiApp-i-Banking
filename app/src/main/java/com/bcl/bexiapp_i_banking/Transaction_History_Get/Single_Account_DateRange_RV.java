package com.bcl.bexiapp_i_banking.Transaction_History_Get;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerRVadapter;
import com.bcl.bexiapp_i_banking.R;

import java.util.List;

public class Single_Account_DateRange_RV extends RecyclerView.Adapter<Single_Account_DateRange_RV.ViewHolder> {

    List<Single_Account_DateRange_Record_Data_Model> dataModels;

    public Single_Account_DateRange_RV(List<Single_Account_DateRange_Record_Data_Model> dataModels) {
        this.dataModels = dataModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_transaction_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.et_transaction_history_sl.setText(dataModels.get(position).getSL_NO());
        holder.et_transaction_history_tranno.setText(dataModels.get(position).getTRAN_NO());
        holder.et_transaction_history_date.setText(dataModels.get(position).getDAT());
        holder.et_transaction_history_fromacc.setText(dataModels.get(position).getFROM_ACC());
        holder.et_transaction_history_tranmedium.setText(dataModels.get(position).getTRAN_MEDIUM());
        holder.et_transaction_history_trantype.setText(dataModels.get(position).getTRAN_TYPE());
        holder.et_transaction_history_tranamt.setText(dataModels.get(position).getTRAN_AMOUNT());
        holder.et_transaction_history_toacc.setText(dataModels.get(position).getTO_ACC());
        holder.et_transaction_history_oldbal.setText(dataModels.get(position).getOLD_BALANCE());
        holder.et_transaction_history_newbal.setText(dataModels.get(position).getNEW_BALANCE());

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView et_transaction_history_sl,et_transaction_history_tranno,et_transaction_history_date,et_transaction_history_fromacc,et_transaction_history_tranmedium,et_transaction_history_trantype,et_transaction_history_tranamt,et_transaction_history_toacc,et_transaction_history_oldbal,et_transaction_history_newbal;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            et_transaction_history_sl = itemView.findViewById(R.id.et_transaction_history_sl);
            et_transaction_history_tranno = itemView.findViewById(R.id.et_transaction_history_tranno);
            et_transaction_history_date = itemView.findViewById(R.id.et_transaction_history_date);
            et_transaction_history_fromacc = itemView.findViewById(R.id.et_transaction_history_fromacc);
            et_transaction_history_tranmedium = itemView.findViewById(R.id.et_transaction_history_tranmedium);
            et_transaction_history_trantype = itemView.findViewById(R.id.et_transaction_history_trantype);
            et_transaction_history_tranamt = itemView.findViewById(R.id.et_transaction_history_tranamt);
            et_transaction_history_toacc = itemView.findViewById(R.id.et_transaction_history_toacc);
            et_transaction_history_oldbal = itemView.findViewById(R.id.et_transaction_history_oldbal);
            et_transaction_history_newbal = itemView.findViewById(R.id.et_transaction_history_newbal);


        }
    }
}
