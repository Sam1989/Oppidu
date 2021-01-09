package com.ems.oppidu.webservice;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface ApiInterface {


    @FormUrlEncoded
    @POST
    Call<ResponseBody> login(@Url String url,
                             @Field("email") String email,
                             @Field("password") String password
    );



    @FormUrlEncoded
    @POST
    Call<ResponseBody> postGetToken(@Url String url,
                                    @Field("company_id") String company_Id,
                                    @Field("access_code") String access_Code);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postGetHomeData(@Url String url,
                                       @Field("token") String token);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postGetMerchantData(@Url String url,
                                           @Field("token") String token,
                                           @Field("category_id") String category_id,
                                           @Field("subcategory_id") String subcategory_id,
                                           @Field("merchant_name") String merchant_name);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postCmsList(@Url String url,
                                   @Field("token") String token);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postGetVisitedAndSimilarMerchant(@Url String url,
                                                        @Field("token") String token,
                                                        @Field("member_id") String member_id);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postMyRewards(@Url String url,
                                     @Field("token") String token,
                                     @Field("member_id") String member_id);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postMyTransactionsummary(@Url String url,
                                                @Field("token") String token,
                                                @Field("member_id") String member_id);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postMyMyWithdrawalHistory(@Url String url,
                                                 @Field("token") String token,
                                                 @Field("member_id") String member_id);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postTrackAndRedirect(@Url String url,
                                            @Field("token") String token,
                                            @Field("member_id") String member_id,
                                            @Field("member_name") String member_name,
                                            @Field("tracking_url") String tracking_url);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postSubmitContactUs(@Url String url,
                                           @Field("token") String token,
                                           @Field("subject") String subject,
                                           @Field("email") String email,
                                           @Field("name") String name,
                                           @Field("message") String message,
                                           @Field("contact_number") String contact_number);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postGetContactUsSubject(@Url String url,
                                               @Field("token") String token);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postMemberDetails(@Url String url,
                                         @Field("token") String token,
                                         @Field("email_id") String email_id,
                                         @Field("password") String password
    );

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postReferralDetails(@Url String url,
                                           @Field("token") String token,
                                           @Field("member_id") String email_id,
                                           @Field("referral_code") String password
    );


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postUpdateMemberDetails(@Url String url,
                                               @Field("token") String token,
                                               @Field("member_id") String member_id,
                                               @Field("first_name") String first_name,
                                               @Field("sur_name") String sur_name,
                                               @Field("gender") String gender,
                                               @Field("mobile_number") String mobile_number,
                                               @Field("dob") String dob,
                                               @Field("post_code") String post_code


    );

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postChangeEmail(@Url String url,
                                       @Field("token") String token,
                                       @Field("member_id") String email_id,
                                       @Field("current_password") String current_password,
                                       @Field("new_email") String new_email);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postChangePassword(@Url String url,
                                          @Field("token") String token,
                                          @Field("member_id") String email_id,
                                          @Field("current_password") String current_password,
                                          @Field("new_password") String new_password);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postUpdateBankDetails(@Url String url,
                                             @Field("token") String token,
                                             @Field("member_id") String email_id,
                                             @Field("account_name") String account_name,
                                             @Field("bsb") String bsb,
                                             @Field("account_number") String account_number,
                                             @Field("reward_password") String reward_password);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postCloseMemberAccount(@Url String url,
                                              @Field("token") String token,
                                              @Field("member_id") String email_id,
                                              @Field("close_account") String close_account);

    @FormUrlEncoded
    @POST
    Call<ResponseBody> postPayToMyAccount(@Url String url,
                                          @Field("token") String token,
                                          @Field("account_name") String account_name,
                                          @Field("account_number") String account_number,
                                          @Field("bank_name") String bank_name,
                                          @Field("bsb_number") String bsb_number,
                                          @Field("withdrawal_amount") String withdrawal_amount,
                                          @Field("member_commission") String member_commission,
                                          @Field("member_id") String member_id,
                                          @Field("member_name") String member_name);


    @FormUrlEncoded
    @POST
    Call<ResponseBody> postSubmitClaim(@Url String url,
                                       @Field("token") String token,
                                       @Field("member_id") String member_id,
                                       @Field("member_name") String member_name,
                                       @Field("member_email") String member_email,
                                       @Field("contact_number") String contact_number,
                                       @Field("retailer_name") String retailer_name,
                                       @Field("purchase_amount") String purchase_amount,
                                       @Field("purchase_date") String purchase_date,
                                       @Field("member_comment") String member_comment);
}
