
package com.rkrzmail.mobilesales.APIService;

/**
 * Created by user on 1/10/2018.
 */


import com.rkrzmail.mobilesales.model.Login.ModelLogin;
import com.rkrzmail.mobilesales.model.activity.PostActivity;
import com.rkrzmail.mobilesales.model.tes.UpdateTes;
import com.rkrzmail.mobilesales.model.tes.modeltes;
import com.rkrzmail.mobilesales.model.dataupload.DataUpload;
import com.rkrzmail.mobilesales.model.pickup.ModelPickup;
import com.rkrzmail.mobilesales.model.reason.ModelReason;
import com.rkrzmail.mobilesales.model.upload.PostUpload2;

import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
public interface APIInterfacesRest {
//    @GET("login_sales?username=K1001005&password=1981-04-17")
//    Call<ModelLogin> getData(@Query("x-api-key") String apikey);

    @GET("login_sales")
    Call<ModelLogin> getData(@QueryMap Map<String, String> params1, @QueryMap Map<String, String> params2);

    //@Headers("Content-Type: application/json")
    @Multipart
    @POST("upload2")
    Call<PostUpload2> sendDataUpload2(
            @Part("id") RequestBody id,
            @Part("id_form") RequestBody id_form,
            @Part("unique_id") RequestBody unique_id,
            @Part("cabang") RequestBody cabang,
            @Part("no_case") RequestBody no_case,
            @Part("cis") RequestBody cis,
            @Part("name") RequestBody name,
            @Part("dob") RequestBody dob,
            @Part("gender") RequestBody gender,
            @Part("address") RequestBody address,
            @Part("address_geotag") RequestBody address_geotag,
            @Part("post_code") RequestBody post_code,
            @Part("city") RequestBody city,
            @Part("city2") RequestBody city2,
            @Part("phone1") RequestBody phone1,
            @Part("phone2") RequestBody phone2,
            @Part("phone3") RequestBody phone3,
            @Part("phone4") RequestBody phone4,
            @Part("phone5") RequestBody phone5,
            @Part("phone6") RequestBody phone6,
            @Part("phone7") RequestBody phone7,
            @Part("phone8") RequestBody phone8,
            @Part("phone9") RequestBody phone9,
            @Part("phone10") RequestBody phone10,
            @Part("phone11") RequestBody phone11,
            @Part("phone12") RequestBody phone12,
            @Part("phone13") RequestBody phone13,
            @Part("phone14") RequestBody phone14,
            @Part("phone15") RequestBody phone15,
            @Part("id_campaign") RequestBody id_campaign,
            @Part("campaign") RequestBody campaign,
            @Part("type") RequestBody type,
            @Part("username") RequestBody username,
            @Part("tm_name") RequestBody tm_name,
            @Part("tm_code") RequestBody tm_code,
            @Part("aggree_date") RequestBody aggree_date,
            @Part("agree_time") RequestBody agree_time,
            @Part("upload_date") RequestBody upload_date,
            @Part("tgl_pu") RequestBody tgl_pu,
            @Part("barcode") RequestBody barcode,
            @Part("time_pu") RequestBody time_pu,
            @Part("remark") RequestBody remark,
            @Part("kode_pick_up") RequestBody kode_pick_up,
            @Part("kode_gagal_pu") RequestBody kode_gagal_pu,
            @Part("kode_cancel_pu") RequestBody kode_cancel_pu,
            @Part("pick_up_date") RequestBody pick_up_date,
            @Part("ms_code") RequestBody ms_code,
            @Part("ms_name") RequestBody ms_name,
            @Part("spv_code") RequestBody spv_code,
            @Part("asm_code") RequestBody asm_code,
            @Part("image_type") RequestBody image_type,
            @Part MultipartBody.Part image_name_pemilik,
            @Part MultipartBody.Part image_name_ktp,
            @Part("geo_info_pemilik") RequestBody geo_info_pemilik,
            @Part("geo_info_ktp") RequestBody geo_info_ktp,
            @Part MultipartBody.Part image_name_bukti,
            @Part("geo_info_bukti") RequestBody geo_info_bukti,
            @Part("tanggal_distribusi_asm") RequestBody tanggal_distribusi_asm,
            @Part("tanggal_distribusi") RequestBody tanggal_distribusi,
            @Part("note_linprocessed") RequestBody note_linprocessed,
            @Part("product") RequestBody product,
            @Part("tanggal_reschedule") RequestBody tanggal_reschedule,
            @Part("source_code") RequestBody source_code,
            @Part("created_date") RequestBody created_date,
            @Part("status_validasi") RequestBody status_validasi,
            @Part("validasi_by") RequestBody validasi_by,
            @Part("status_validasi_admin") RequestBody status_validasi_admin,
            @Part("created_by") RequestBody created_by,
            @Part("status_close") RequestBody status_close,
            @Part("close_date") RequestBody close_date,
            @Part("closed_by") RequestBody closed_by,
            @Part("branch") RequestBody branch,
            @Part("tanggal_status_terakhir") RequestBody tanggal_status_terakhir,
            @Part("is_return") RequestBody is_return,
            @Part("return_date") RequestBody return_date,
            @Part("status_call") RequestBody status_call,
            @Part("reason_call") RequestBody reason_call,
            @Part("other_reason") RequestBody other_reason,
            @Part("call_by") RequestBody call_by,
            @Part("call_date") RequestBody call_date,
            @Part("call_time") RequestBody call_time,
            @Part("notes") RequestBody notes
    );

    @Multipart
    @POST("activity")
    Call<PostActivity> sendDataActivity(
            @Part("id_form") RequestBody id_form,
            @Part("id_detail") RequestBody unique_id,
            @Part("action") RequestBody cabang,
            @Part("distribusi_from") RequestBody no_case,
            @Part("distribusi_to") RequestBody cis,
            @Part("ms_code") RequestBody name,
            @Part("status") RequestBody dob,
            @Part("keterangan") RequestBody gender,
            @Part("created_date") RequestBody address,
            @Part("created_by") RequestBody address_geotag
    );
    @Multipart
    @POST("tes")
    Call<modeltes> sendTes(

            @Part MultipartBody.Part fotonpwp,
            @Part MultipartBody.Part fotoktp,
            @Part MultipartBody.Part fotobukti
    );

    @Multipart
    @POST("tesedit")
    Call<UpdateTes> UpdateTes(
            @Part("id") RequestBody id,

            @Part MultipartBody.Part fotonpwp,
            @Part MultipartBody.Part fotoktp,
            @Part MultipartBody.Part fotobukti
    );


    @GET("upload")
    Call<DataUpload> getUpload();

    @GET("pickup")
    Call<ModelPickup> getPickUp();

    @GET("reason")
    Call<ModelReason> getReason();

}