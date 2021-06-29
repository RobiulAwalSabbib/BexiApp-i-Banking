package com.bcl.bexiapp_i_banking.db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerRVadapter;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.GetDataModel;
import com.bcl.bexiapp_i_banking.R;

import java.util.ArrayList;
import java.util.List;

public class UserViewAllRV extends RecyclerView.Adapter<UserViewAllRV.ViewHolder> {

    ArrayList<UserM> data;
    Context context;

    public UserViewAllRV(ArrayList<UserM> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_userdb_alldatafromlocalstorage,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tv_userdb_id.setText(data.get(position).getId());
        holder.tv_userdb_name.setText(data.get(position).getName());
        holder.tv_userdb_mobile.setText(data.get(position).getMobile());
        holder.tv_userdb_email.setText(data.get(position).getEmail());
        holder.tv_userdb_dob.setText(data.get(position).getDob());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_userdb_id,tv_userdb_name,tv_userdb_mobile,tv_userdb_dob,tv_userdb_email;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_userdb_id = itemView.findViewById(R.id.tv_userdb_id);
            tv_userdb_name = itemView.findViewById(R.id.tv_userdb_name);
            tv_userdb_mobile = itemView.findViewById(R.id.tv_userdb_mobile);
            tv_userdb_dob = itemView.findViewById(R.id.tv_userdb_dob);
            tv_userdb_email = itemView.findViewById(R.id.tv_userdb_email);
        }
    }
}
