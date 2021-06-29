package com.bcl.bexiapp_i_banking.retrofit;

import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerGetAllDataM;
import com.bcl.bexiapp_i_banking.AG_CUSTOMER.AGCustomerRecM;
import com.bcl.bexiapp_i_banking.Benificier_Info_Post.ReturnModel;
import com.bcl.bexiapp_i_banking.Login.LoginDataM;
import com.bcl.bexiapp_i_banking.Menu_Get.ReceiveModel;
import com.bcl.bexiapp_i_banking.TopUp_Request_Post.Recharge_Request_Post_Receive_Data_Model;
import com.bcl.bexiapp_i_banking.TranDetail_Get.StatementReceiveModel;
import com.bcl.bexiapp_i_banking.Transaction_History_Get.Single_Account_DateRange_Record_Receive_Model;
import com.bcl.bexiapp_i_banking.Transaction_Medium_Get_All_Data.Transaction_Medium_Get_All_Data_Return_M;
import com.bcl.bexiapp_i_banking.Transaction_Post.Transaction_Post_Receive_Model;
import com.bcl.bexiapp_i_banking.Transaction_Type_Get_All_Data.Transaction_Type_Get_All_Data_Return_M;
import com.bcl.bexiapp_i_banking.image_upload.ProfileUploadDataM;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {

//    @FormUrlEncoded
//    @POST("LoginApi")
//    Single<LoginDataM> getLogin(
//
//            @Field("user_id") String user_id,
//            @Field("user_password") String user_password
//    );


//    @FormUrlEncoded
//    @POST("LoginPostApi")
//    Single<LoginDataM> postLogin(
//
//            @Field("user_id") String user_id,
//            @Field("user_password") String user_password,
//            @Field("user_name") String user_name
//    );

//
//    @FormUrlEncoded
//    @POST("CreateUserS")
//    Single<CustomerDataM> postCustomerRequest(
//
//            @Field("custid") String custid,
//            @Field("dob") String dob
//    );

//    @FormUrlEncoded
//    @POST("StatementS")
//    List<StatementDataM> getAllStatement(
//            @Field("acNo") StatementReqM acNo
//    );

//
//    @POST("StatementS?acNo=A00001")
//    Call<List<StatementDataM>> getAllStatement(
//
//    );


//
//    @FormUrlEncoded
//    @POST("StatementS?acNo=A00001")
//    List<StatementDataM> getAllStatement2();


//    @GET("LoginApi")
//    Single<LoginDataM> getLogin(
//
//            @Query("user_id") String user_id,
//            @Query("user_password") String user_password
//    );

//    @FormUrlEncoded
//    @POST("Customer")
//    Single<CustomerDataM> customer_registration(
//            @Field("customer_id") String customer_id,
//            @Field("customer_name") String customer_name,
//            @Field("customer_mobile") String customer_mobile,
//            @Field("customer_father_name") String customer_father_name,
//            @Field("customer_mother_name") String customer_mother_name,
//            @Field("customer_dob") String customer_dob,
//            @Field("branch_id") String branch_id,
//            @Field("customer_nid") String customer_nid
//    );

    @FormUrlEncoded
    @POST("AG_Customer_Api")
    Single<AGCustomerRecM> postAGcustomer(

            @Field("cust_id") String cust_id,
            @Field("cust_name") String cust_name,
            @Field("cust_gender") String cust_gender,
            @Field("cust_dob") String cust_dob
    );


    @POST("Ag_Customer_Getall_Data_Api")
    Single<List<AGCustomerGetAllDataM>> getAGcustomerAllData();



    @FormUrlEncoded
    @POST("Transaction_Post_Api")
    Single<Transaction_Post_Receive_Model> postTransaction(

            @Field("sender_acc") String sender_acc,
            @Field("receiver_acc") String receiver_acc,
            @Field("transaction_amount") String transaction_amount,
            @Field("transaction_medium") String transaction_medium,
            @Field("transaction_type") String transaction_type
    );


    @POST("Transaction_Type_Get_All_Data_Api")
    Single<List<Transaction_Type_Get_All_Data_Return_M>> getTransactionTypeAllData();





    @POST("Transaction_Medium_Get_All_Data_Api")
    Single<List<Transaction_Medium_Get_All_Data_Return_M>> getTransactionMediumAllData();



    @FormUrlEncoded
    @POST("StatementS")
    Single<List<StatementReceiveModel>> getAllStatementData(
            @Field("acNo") String acNo
    );


    @FormUrlEncoded
    @POST("Get_Transaction_History_curProcedure_Api")
    Single<List<Single_Account_DateRange_Record_Receive_Model>> get_Tran_Hist_Single_Acc_Record(

            @Field("acc_no") String acc_no,
            @Field("from_date") String from_date,
            @Field("to_date") String to_date
    );


    @FormUrlEncoded
    @POST("Benificiary_Info_Post_Api")
    Single<ReturnModel> postBenificierInfo(

            @Field("cust_id") String cust_id,
            @Field("acc_no") String acc_no,
            @Field("acc_type") String acc_type,
            @Field("acc_title") String acc_title
    );


    @FormUrlEncoded
    @POST("Topup_Request_Post_Api")
    Single<Recharge_Request_Post_Receive_Data_Model> postRechargeRequest(

            @Field("MOBILE") String MOBILE,
            @Field("OPERATORS_CODE") String OPERATORS_CODE,
            @Field("SIM_TYPE_NAME") String SIM_TYPE_NAME,
            @Field("AMOUNT") String AMOUNT,
            @Field("RECHARGE_CODE") String RECHARGE_CODE
    );


    @FormUrlEncoded
    @POST("LoginApi")
    Single<LoginDataM> getLogin(

            @Field("user_id") String user_id,
            @Field("user_password") String user_password
    );

    @POST("Menu_Get_Api")
    Single<List<ReceiveModel>> getMenuAllData();



    @FormUrlEncoded
    @POST("ProfilePicUploadS")
    Single<ProfileUploadDataM> uploadProfilePic(
            @Field("id") String id,
            @Field("image") String image
    );

//    @FormUrlEncoded
//    @POST("DownloadProfileS")
//    Single<ProfileUploadDataM> getImage(
//            @Field("id") String id
//    );

}
