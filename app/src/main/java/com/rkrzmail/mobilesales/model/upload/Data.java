
package com.rkrzmail.mobilesales.model.upload;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_form")
    @Expose
    private String idForm;
    @SerializedName("unique_id")
    @Expose
    private String uniqueId;
    @SerializedName("cabang")
    @Expose
    private String cabang;
    @SerializedName("no_case")
    @Expose
    private String noCase;
    @SerializedName("cis")
    @Expose
    private String cis;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("address_geotag")
    @Expose
    private String addressGeotag;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("city2")
    @Expose
    private String city2;
    @SerializedName("phone1")
    @Expose
    private String phone1;
    @SerializedName("phone2")
    @Expose
    private String phone2;
    @SerializedName("phone3")
    @Expose
    private String phone3;
    @SerializedName("phone4")
    @Expose
    private String phone4;
    @SerializedName("phone5")
    @Expose
    private String phone5;
    @SerializedName("phone6")
    @Expose
    private String phone6;
    @SerializedName("phone7")
    @Expose
    private String phone7;
    @SerializedName("phone8")
    @Expose
    private String phone8;
    @SerializedName("phone9")
    @Expose
    private String phone9;
    @SerializedName("phone10")
    @Expose
    private String phone10;
    @SerializedName("phone11")
    @Expose
    private String phone11;
    @SerializedName("phone12")
    @Expose
    private String phone12;
    @SerializedName("phone13")
    @Expose
    private String phone13;
    @SerializedName("phone14")
    @Expose
    private String phone14;
    @SerializedName("phone15")
    @Expose
    private String phone15;
    @SerializedName("id_campaign")
    @Expose
    private String idCampaign;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("tm_name")
    @Expose
    private String tmName;
    @SerializedName("tm_code")
    @Expose
    private String tmCode;
    @SerializedName("aggree_date")
    @Expose
    private String aggreeDate;
    @SerializedName("agree_time")
    @Expose
    private String agreeTime;
    @SerializedName("upload_date")
    @Expose
    private String uploadDate;
    @SerializedName("tgl_pu")
    @Expose
    private String tglPu;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("time_pu")
    @Expose
    private String timePu;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("kode_pick_up")
    @Expose
    private String kodePickUp;
    @SerializedName("kode_gagal_pu")
    @Expose
    private String kodeGagalPu;
    @SerializedName("kode_cancel_pu")
    @Expose
    private String kodeCancelPu;
    @SerializedName("pick_up_date")
    @Expose
    private String pickUpDate;
    @SerializedName("ms_code")
    @Expose
    private String msCode;
    @SerializedName("ms_name")
    @Expose
    private String msName;
    @SerializedName("spv_code")
    @Expose
    private String spvCode;
    @SerializedName("asm_code")
    @Expose
    private String asmCode;
    @SerializedName("image_type")
    @Expose
    private String imageType;
    @SerializedName("image_name_pemilik")
    @Expose
    private String imageNamePemilik;
    @SerializedName("image_name_ktp")
    @Expose
    private String imageNameKtp;
    @SerializedName("geo_info_pemilik")
    @Expose
    private String geoInfoPemilik;
    @SerializedName("geo_info_ktp")
    @Expose
    private String geoInfoKtp;
    @SerializedName("image_name_bukti")
    @Expose
    private String imageNameBukti;
    @SerializedName("geo_info_bukti")
    @Expose
    private String geoInfoBukti;
    @SerializedName("tanggal_distribusi_asm")
    @Expose
    private String tanggalDistribusiAsm;
    @SerializedName("tanggal_distribusi")
    @Expose
    private String tanggalDistribusi;
    @SerializedName("note_Iinprocessed")
    @Expose
    private String noteIinprocessed;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("tanggal_reschedule")
    @Expose
    private String tanggalReschedule;
    @SerializedName("source_code")
    @Expose
    private String sourceCode;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("status_validasi")
    @Expose
    private String statusValidasi;
    @SerializedName("validasi_by")
    @Expose
    private String validasiBy;
    @SerializedName("status_validasi_admin")
    @Expose
    private String statusValidasiAdmin;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("status_close")
    @Expose
    private String statusClose;
    @SerializedName("close_date")
    @Expose
    private String closeDate;
    @SerializedName("closed_by")
    @Expose
    private String closedBy;
    @SerializedName("branch")
    @Expose
    private String branch;
    @SerializedName("tanggal_status_terakhir")
    @Expose
    private String tanggalStatusTerakhir;
    @SerializedName("is_return")
    @Expose
    private String isReturn;
    @SerializedName("return_date")
    @Expose
    private String returnDate;
    @SerializedName("status_call")
    @Expose
    private String statusCall;
    @SerializedName("reason_call")
    @Expose
    private String reasonCall;
    @SerializedName("other_reason")
    @Expose
    private String otherReason;
    @SerializedName("call_by")
    @Expose
    private String callBy;
    @SerializedName("call_date")
    @Expose
    private String callDate;
    @SerializedName("call_time")
    @Expose
    private String callTime;
    @SerializedName("notes")
    @Expose
    private String notes;
    public final static Creator<Data> CREATOR = new Creator<Data>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        public Data[] newArray(int size) {
            return (new Data[size]);
        }

    }
    ;
    private final static long serialVersionUID = -1654624648312592596L;

    protected Data(Parcel in) {
        this.id = ((String) in.readValue((String.class.getClassLoader())));
        this.idForm = ((String) in.readValue((String.class.getClassLoader())));
        this.uniqueId = ((String) in.readValue((String.class.getClassLoader())));
        this.cabang = ((String) in.readValue((String.class.getClassLoader())));
        this.noCase = ((String) in.readValue((String.class.getClassLoader())));
        this.cis = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.dob = ((String) in.readValue((String.class.getClassLoader())));
        this.gender = ((String) in.readValue((String.class.getClassLoader())));
        this.address = ((String) in.readValue((String.class.getClassLoader())));
        this.addressGeotag = ((String) in.readValue((String.class.getClassLoader())));
        this.city = ((String) in.readValue((String.class.getClassLoader())));
        this.city2 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone1 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone2 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone3 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone4 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone5 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone6 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone7 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone8 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone9 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone10 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone11 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone12 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone13 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone14 = ((String) in.readValue((String.class.getClassLoader())));
        this.phone15 = ((String) in.readValue((String.class.getClassLoader())));
        this.idCampaign = ((String) in.readValue((String.class.getClassLoader())));
        this.type = ((String) in.readValue((String.class.getClassLoader())));
        this.username = ((String) in.readValue((String.class.getClassLoader())));
        this.tmName = ((String) in.readValue((String.class.getClassLoader())));
        this.tmCode = ((String) in.readValue((String.class.getClassLoader())));
        this.aggreeDate = ((String) in.readValue((String.class.getClassLoader())));
        this.agreeTime = ((String) in.readValue((String.class.getClassLoader())));
        this.uploadDate = ((String) in.readValue((String.class.getClassLoader())));
        this.tglPu = ((String) in.readValue((String.class.getClassLoader())));
        this.barcode = ((String) in.readValue((String.class.getClassLoader())));
        this.timePu = ((String) in.readValue((String.class.getClassLoader())));
        this.remark = ((String) in.readValue((String.class.getClassLoader())));
        this.kodePickUp = ((String) in.readValue((String.class.getClassLoader())));
        this.kodeGagalPu = ((String) in.readValue((String.class.getClassLoader())));
        this.kodeCancelPu = ((String) in.readValue((String.class.getClassLoader())));
        this.pickUpDate = ((String) in.readValue((String.class.getClassLoader())));
        this.msCode = ((String) in.readValue((String.class.getClassLoader())));
        this.msName = ((String) in.readValue((String.class.getClassLoader())));
        this.spvCode = ((String) in.readValue((String.class.getClassLoader())));
        this.asmCode = ((String) in.readValue((String.class.getClassLoader())));
        this.imageType = ((String) in.readValue((String.class.getClassLoader())));
        this.imageNamePemilik = ((String) in.readValue((String.class.getClassLoader())));
        this.imageNameKtp = ((String) in.readValue((String.class.getClassLoader())));
        this.geoInfoPemilik = ((String) in.readValue((String.class.getClassLoader())));
        this.geoInfoKtp = ((String) in.readValue((String.class.getClassLoader())));
        this.imageNameBukti = ((String) in.readValue((String.class.getClassLoader())));
        this.geoInfoBukti = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggalDistribusiAsm = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggalDistribusi = ((String) in.readValue((String.class.getClassLoader())));
        this.noteIinprocessed = ((String) in.readValue((String.class.getClassLoader())));
        this.product = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggalReschedule = ((String) in.readValue((String.class.getClassLoader())));
        this.sourceCode = ((String) in.readValue((String.class.getClassLoader())));
        this.createdDate = ((String) in.readValue((String.class.getClassLoader())));
        this.statusValidasi = ((String) in.readValue((String.class.getClassLoader())));
        this.validasiBy = ((String) in.readValue((String.class.getClassLoader())));
        this.statusValidasiAdmin = ((String) in.readValue((String.class.getClassLoader())));
        this.createdBy = ((String) in.readValue((String.class.getClassLoader())));
        this.statusClose = ((String) in.readValue((String.class.getClassLoader())));
        this.closeDate = ((String) in.readValue((String.class.getClassLoader())));
        this.closedBy = ((String) in.readValue((String.class.getClassLoader())));
        this.branch = ((String) in.readValue((String.class.getClassLoader())));
        this.tanggalStatusTerakhir = ((String) in.readValue((String.class.getClassLoader())));
        this.isReturn = ((String) in.readValue((String.class.getClassLoader())));
        this.returnDate = ((String) in.readValue((String.class.getClassLoader())));
        this.statusCall = ((String) in.readValue((String.class.getClassLoader())));
        this.reasonCall = ((String) in.readValue((String.class.getClassLoader())));
        this.otherReason = ((String) in.readValue((String.class.getClassLoader())));
        this.callBy = ((String) in.readValue((String.class.getClassLoader())));
        this.callDate = ((String) in.readValue((String.class.getClassLoader())));
        this.callTime = ((String) in.readValue((String.class.getClassLoader())));
        this.notes = ((String) in.readValue((String.class.getClassLoader())));
    }

    public Data() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdForm() {
        return idForm;
    }

    public void setIdForm(String idForm) {
        this.idForm = idForm;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getCabang() {
        return cabang;
    }

    public void setCabang(String cabang) {
        this.cabang = cabang;
    }

    public String getNoCase() {
        return noCase;
    }

    public void setNoCase(String noCase) {
        this.noCase = noCase;
    }

    public String getCis() {
        return cis;
    }

    public void setCis(String cis) {
        this.cis = cis;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressGeotag() {
        return addressGeotag;
    }

    public void setAddressGeotag(String addressGeotag) {
        this.addressGeotag = addressGeotag;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity2() {
        return city2;
    }

    public void setCity2(String city2) {
        this.city2 = city2;
    }

    public String getPhone1() {
        return phone1;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getPhone3() {
        return phone3;
    }

    public void setPhone3(String phone3) {
        this.phone3 = phone3;
    }

    public String getPhone4() {
        return phone4;
    }

    public void setPhone4(String phone4) {
        this.phone4 = phone4;
    }

    public String getPhone5() {
        return phone5;
    }

    public void setPhone5(String phone5) {
        this.phone5 = phone5;
    }

    public String getPhone6() {
        return phone6;
    }

    public void setPhone6(String phone6) {
        this.phone6 = phone6;
    }

    public String getPhone7() {
        return phone7;
    }

    public void setPhone7(String phone7) {
        this.phone7 = phone7;
    }

    public String getPhone8() {
        return phone8;
    }

    public void setPhone8(String phone8) {
        this.phone8 = phone8;
    }

    public String getPhone9() {
        return phone9;
    }

    public void setPhone9(String phone9) {
        this.phone9 = phone9;
    }

    public String getPhone10() {
        return phone10;
    }

    public void setPhone10(String phone10) {
        this.phone10 = phone10;
    }

    public String getPhone11() {
        return phone11;
    }

    public void setPhone11(String phone11) {
        this.phone11 = phone11;
    }

    public String getPhone12() {
        return phone12;
    }

    public void setPhone12(String phone12) {
        this.phone12 = phone12;
    }

    public String getPhone13() {
        return phone13;
    }

    public void setPhone13(String phone13) {
        this.phone13 = phone13;
    }

    public String getPhone14() {
        return phone14;
    }

    public void setPhone14(String phone14) {
        this.phone14 = phone14;
    }

    public String getPhone15() {
        return phone15;
    }

    public void setPhone15(String phone15) {
        this.phone15 = phone15;
    }

    public String getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(String idCampaign) {
        this.idCampaign = idCampaign;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTmName() {
        return tmName;
    }

    public void setTmName(String tmName) {
        this.tmName = tmName;
    }

    public String getTmCode() {
        return tmCode;
    }

    public void setTmCode(String tmCode) {
        this.tmCode = tmCode;
    }

    public String getAggreeDate() {
        return aggreeDate;
    }

    public void setAggreeDate(String aggreeDate) {
        this.aggreeDate = aggreeDate;
    }

    public String getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(String agreeTime) {
        this.agreeTime = agreeTime;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getTglPu() {
        return tglPu;
    }

    public void setTglPu(String tglPu) {
        this.tglPu = tglPu;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getTimePu() {
        return timePu;
    }

    public void setTimePu(String timePu) {
        this.timePu = timePu;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKodePickUp() {
        return kodePickUp;
    }

    public void setKodePickUp(String kodePickUp) {
        this.kodePickUp = kodePickUp;
    }

    public String getKodeGagalPu() {
        return kodeGagalPu;
    }

    public void setKodeGagalPu(String kodeGagalPu) {
        this.kodeGagalPu = kodeGagalPu;
    }

    public String getKodeCancelPu() {
        return kodeCancelPu;
    }

    public void setKodeCancelPu(String kodeCancelPu) {
        this.kodeCancelPu = kodeCancelPu;
    }

    public String getPickUpDate() {
        return pickUpDate;
    }

    public void setPickUpDate(String pickUpDate) {
        this.pickUpDate = pickUpDate;
    }

    public String getMsCode() {
        return msCode;
    }

    public void setMsCode(String msCode) {
        this.msCode = msCode;
    }

    public String getMsName() {
        return msName;
    }

    public void setMsName(String msName) {
        this.msName = msName;
    }

    public String getSpvCode() {
        return spvCode;
    }

    public void setSpvCode(String spvCode) {
        this.spvCode = spvCode;
    }

    public String getAsmCode() {
        return asmCode;
    }

    public void setAsmCode(String asmCode) {
        this.asmCode = asmCode;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getImageNamePemilik() {
        return imageNamePemilik;
    }

    public void setImageNamePemilik(String imageNamePemilik) {
        this.imageNamePemilik = imageNamePemilik;
    }

    public String getImageNameKtp() {
        return imageNameKtp;
    }

    public void setImageNameKtp(String imageNameKtp) {
        this.imageNameKtp = imageNameKtp;
    }

    public String getGeoInfoPemilik() {
        return geoInfoPemilik;
    }

    public void setGeoInfoPemilik(String geoInfoPemilik) {
        this.geoInfoPemilik = geoInfoPemilik;
    }

    public String getGeoInfoKtp() {
        return geoInfoKtp;
    }

    public void setGeoInfoKtp(String geoInfoKtp) {
        this.geoInfoKtp = geoInfoKtp;
    }

    public String getImageNameBukti() {
        return imageNameBukti;
    }

    public void setImageNameBukti(String imageNameBukti) {
        this.imageNameBukti = imageNameBukti;
    }

    public String getGeoInfoBukti() {
        return geoInfoBukti;
    }

    public void setGeoInfoBukti(String geoInfoBukti) {
        this.geoInfoBukti = geoInfoBukti;
    }

    public String getTanggalDistribusiAsm() {
        return tanggalDistribusiAsm;
    }

    public void setTanggalDistribusiAsm(String tanggalDistribusiAsm) {
        this.tanggalDistribusiAsm = tanggalDistribusiAsm;
    }

    public String getTanggalDistribusi() {
        return tanggalDistribusi;
    }

    public void setTanggalDistribusi(String tanggalDistribusi) {
        this.tanggalDistribusi = tanggalDistribusi;
    }

    public String getNoteIinprocessed() {
        return noteIinprocessed;
    }

    public void setNoteIinprocessed(String noteIinprocessed) {
        this.noteIinprocessed = noteIinprocessed;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getTanggalReschedule() {
        return tanggalReschedule;
    }

    public void setTanggalReschedule(String tanggalReschedule) {
        this.tanggalReschedule = tanggalReschedule;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatusValidasi() {
        return statusValidasi;
    }

    public void setStatusValidasi(String statusValidasi) {
        this.statusValidasi = statusValidasi;
    }

    public String getValidasiBy() {
        return validasiBy;
    }

    public void setValidasiBy(String validasiBy) {
        this.validasiBy = validasiBy;
    }

    public String getStatusValidasiAdmin() {
        return statusValidasiAdmin;
    }

    public void setStatusValidasiAdmin(String statusValidasiAdmin) {
        this.statusValidasiAdmin = statusValidasiAdmin;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getStatusClose() {
        return statusClose;
    }

    public void setStatusClose(String statusClose) {
        this.statusClose = statusClose;
    }

    public String getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTanggalStatusTerakhir() {
        return tanggalStatusTerakhir;
    }

    public void setTanggalStatusTerakhir(String tanggalStatusTerakhir) {
        this.tanggalStatusTerakhir = tanggalStatusTerakhir;
    }

    public String getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(String isReturn) {
        this.isReturn = isReturn;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getStatusCall() {
        return statusCall;
    }

    public void setStatusCall(String statusCall) {
        this.statusCall = statusCall;
    }

    public String getReasonCall() {
        return reasonCall;
    }

    public void setReasonCall(String reasonCall) {
        this.reasonCall = reasonCall;
    }

    public String getOtherReason() {
        return otherReason;
    }

    public void setOtherReason(String otherReason) {
        this.otherReason = otherReason;
    }

    public String getCallBy() {
        return callBy;
    }

    public void setCallBy(String callBy) {
        this.callBy = callBy;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(idForm);
        dest.writeValue(uniqueId);
        dest.writeValue(cabang);
        dest.writeValue(noCase);
        dest.writeValue(cis);
        dest.writeValue(name);
        dest.writeValue(dob);
        dest.writeValue(gender);
        dest.writeValue(address);
        dest.writeValue(addressGeotag);
        dest.writeValue(city);
        dest.writeValue(city2);
        dest.writeValue(phone1);
        dest.writeValue(phone2);
        dest.writeValue(phone3);
        dest.writeValue(phone4);
        dest.writeValue(phone5);
        dest.writeValue(phone6);
        dest.writeValue(phone7);
        dest.writeValue(phone8);
        dest.writeValue(phone9);
        dest.writeValue(phone10);
        dest.writeValue(phone11);
        dest.writeValue(phone12);
        dest.writeValue(phone13);
        dest.writeValue(phone14);
        dest.writeValue(phone15);
        dest.writeValue(idCampaign);
        dest.writeValue(type);
        dest.writeValue(username);
        dest.writeValue(tmName);
        dest.writeValue(tmCode);
        dest.writeValue(aggreeDate);
        dest.writeValue(agreeTime);
        dest.writeValue(uploadDate);
        dest.writeValue(tglPu);
        dest.writeValue(barcode);
        dest.writeValue(timePu);
        dest.writeValue(remark);
        dest.writeValue(kodePickUp);
        dest.writeValue(kodeGagalPu);
        dest.writeValue(kodeCancelPu);
        dest.writeValue(pickUpDate);
        dest.writeValue(msCode);
        dest.writeValue(msName);
        dest.writeValue(spvCode);
        dest.writeValue(asmCode);
        dest.writeValue(imageType);
        dest.writeValue(imageNamePemilik);
        dest.writeValue(imageNameKtp);
        dest.writeValue(geoInfoPemilik);
        dest.writeValue(geoInfoKtp);
        dest.writeValue(imageNameBukti);
        dest.writeValue(geoInfoBukti);
        dest.writeValue(tanggalDistribusiAsm);
        dest.writeValue(tanggalDistribusi);
        dest.writeValue(noteIinprocessed);
        dest.writeValue(product);
        dest.writeValue(tanggalReschedule);
        dest.writeValue(sourceCode);
        dest.writeValue(createdDate);
        dest.writeValue(statusValidasi);
        dest.writeValue(validasiBy);
        dest.writeValue(statusValidasiAdmin);
        dest.writeValue(createdBy);
        dest.writeValue(statusClose);
        dest.writeValue(closeDate);
        dest.writeValue(closedBy);
        dest.writeValue(branch);
        dest.writeValue(tanggalStatusTerakhir);
        dest.writeValue(isReturn);
        dest.writeValue(returnDate);
        dest.writeValue(statusCall);
        dest.writeValue(reasonCall);
        dest.writeValue(otherReason);
        dest.writeValue(callBy);
        dest.writeValue(callDate);
        dest.writeValue(callTime);
        dest.writeValue(notes);
    }

    public int describeContents() {
        return  0;
    }

}
