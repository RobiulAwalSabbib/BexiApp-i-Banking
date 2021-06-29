package com.bcl.bexiapp_i_banking.Menu_Get;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerRVadapter;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.GetDataModel;
import com.bcl.bexiapp_i_banking.R;
import com.bcl.bexiapp_i_banking.TopupRequestActivity;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    List<DataModel> data;
    Context context;
    OnNoteListener mOnNoteListener;


    public MenuAdapter(List<DataModel> data, Context context,OnNoteListener mOnNoteListener) {
        this.data = data;
        this.context = context;
        this.mOnNoteListener=mOnNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_menu,parent,false);
        ViewHolder viewHolder = new ViewHolder(view,mOnNoteListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {


        final DataModel menuData = data.get(position);

        holder.et_menu_sl.setText(menuData.getSL_NO());
        holder.et_menu_unittype.setText(menuData.getUNIT_TYPE());
        holder.et_menu_menucode.setText(menuData.getMENU_CODE());
        holder.et_menu_parentcode.setText(menuData.getPARENT_CODE());
        holder.et_menu_description.setText(menuData.getDESCRIPTION());
        holder.et_menu_webservicename.setText(menuData.getSL_NO());
        holder.et_menu_webparmemobno.setText(menuData.getWEB_PARM_MOB_NO());
        holder.et_menu_webparmtoaccno.setText(menuData.getWEB_PARM_TO_ACC_NO());
        holder.et_menu_webparmtranamount.setText(menuData.getWEB_PARM_TRAN_AMT());
        holder.et_menu_webparmpinno.setText(menuData.getWEB_PARM_PIN_NO());
        holder.et_menu_menuactionname.setText(menuData.getSL_NO());
        holder.et_menu_permadd.setText(menuData.getPERM_ADD());
        holder.et_menu_permmodify.setText(menuData.getPERM_MODIFY());
        holder.et_menu_permdelete.setText(menuData.getPERM_DELETE());
        holder.et_menu_permview.setText(menuData.getPERM_VIEW());
        holder.et_menu_createdcode.setText(menuData.getCREATED_CODE());
        holder.et_menu_createddate.setText(menuData.getCREATED_DATE());

//        holder.llo_menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(holder.et_menu_sl.getText().toString().equals("20")){
//
//                    Intent intent = new Intent(context.getApplicationContext(), TopupRequestActivity.class);
//
//                    context.startActivity(intent);
//
//                }
//            }
//        });



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout llo_menu;
        TextView et_menu_sl,et_menu_unittype,et_menu_menucode,et_menu_parentcode,et_menu_description,
                et_menu_webservicename,et_menu_webparmemobno,et_menu_webparmtoaccno,et_menu_webparmtranamount,
                et_menu_webparmpinno,et_menu_menuactionname,et_menu_permadd,et_menu_permmodify,et_menu_permdelete,
                et_menu_permview,et_menu_createdcode,et_menu_createddate;

        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView,OnNoteListener onNoteListener) {
            super(itemView);

            this.onNoteListener = onNoteListener;
            llo_menu = itemView.findViewById(R.id.llo_menu);
            et_menu_sl = itemView.findViewById(R.id.et_menu_sl);
            et_menu_unittype = itemView.findViewById(R.id.et_menu_unittype);
            et_menu_menucode = itemView.findViewById(R.id.et_menu_menucode);
            et_menu_parentcode = itemView.findViewById(R.id.et_menu_parentcode);
            et_menu_description = itemView.findViewById(R.id.et_menu_description);
            et_menu_webservicename = itemView.findViewById(R.id.et_menu_webservicename);
            et_menu_webparmemobno = itemView.findViewById(R.id.et_menu_webparmemobno);
            et_menu_webparmtoaccno = itemView.findViewById(R.id.et_menu_webparmtoaccno);
            et_menu_webparmtranamount = itemView.findViewById(R.id.et_menu_webparmtranamount);
            et_menu_webparmpinno = itemView.findViewById(R.id.et_menu_webparmpinno);
            et_menu_menuactionname = itemView.findViewById(R.id.et_menu_menuactionname);
            et_menu_permadd = itemView.findViewById(R.id.et_menu_permadd);
            et_menu_permmodify = itemView.findViewById(R.id.et_menu_permmodify);
            et_menu_permdelete = itemView.findViewById(R.id.et_menu_permdelete);
            et_menu_permview = itemView.findViewById(R.id.et_menu_permview);
            et_menu_createdcode = itemView.findViewById(R.id.et_menu_createdcode);
            et_menu_createddate = itemView.findViewById(R.id.et_menu_createddate);



            itemView.setOnClickListener(this);




        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }




    public interface OnNoteListener{

        void onNoteClick(int position);

    }
}
