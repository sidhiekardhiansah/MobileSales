package com.rkrzmail.mobilesales.model.tes;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rkrzmail.mobilesales.APIService.APIClient2;
import com.rkrzmail.mobilesales.APIService.APIInterfacesRest;
import com.rkrzmail.mobilesales.Adapter.UploadAdapter;
import com.rkrzmail.mobilesales.R;
import com.rkrzmail.mobilesales.model.dataupload.DataUpload;
import com.rkrzmail.mobilesales.model.dataupload.Datum;
import com.rkrzmail.mobilesales.model.pickup.ModelPickup;
import com.rkrzmail.mobilesales.model.reason.ModelReason;
import com.rkrzmail.mobilesales.model.upload.PostUpload2;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TesActivity extends AppCompatActivity implements LocationListener{

    public static final String TAP_PRODUK = "tap_produk";
    SimpleDateFormat sdf;
    Bundle bundle;
    private Context ctx;
    UploadAdapter adapter;
    DataUpload modelupload;
    List<Datum> dataItemList;
    Datum datum;
    TextView txtnama, txtphone, txtpos, txtkota, txtalamat, txtid;
    EditText etData, txtdate;
    String sm, filePath, latitude, longitude, kode, Gnama, filePath2, filePath3;
    private static final int REQUEST_LOCATION=1;
    Spinner spinnerpickup, spinnerreason;
    LocationManager locationManager;
    Button btnktp, btnnpwp, btnbukti, btnkirim;
    ImageView imagektp, imagenpwp, imagebukti;
    EditText txtnote;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tes);
        btnktp=findViewById(R.id.btnktp);
        btnnpwp=findViewById(R.id.btnnpwp);
        btnbukti=findViewById(R.id.btnbukti);
        txtnote= findViewById(R.id.txtnote);

        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sm = pref.getString("mscode", "mscode");
        Gnama = pref.getString("name", "name");
        txtdate= findViewById(R.id.txtdate);
        txtid= findViewById(R.id.txtid);
        datum = getIntent().getParcelableExtra("id");

        //txtid.setText(datum.getId());
        spinnerpickup= findViewById(R.id.spinnerpickup);
        spinnerreason= findViewById(R.id.spinnerreason);
        txtnama= findViewById(R.id.txtnama);
        txtphone= findViewById(R.id.txtphone);
        txtpos= findViewById(R.id.txtpos);
        txtkota= findViewById(R.id.txtkota);
        txtalamat= findViewById(R.id.txtalamat);
        txtnama.setText(datum.getUsername());
        txtphone.setText(datum.getPhone1()+" "+datum.getPhone2()+" "+datum.getPhone3()+" "+datum.getPhone4()+" "+datum.getPhone5()+" "+datum.getPhone6()+" "+datum.getPhone7()+" "+datum.getPhone8()+" "+datum.getPhone9()+" "+datum.getPhone10()+" "+datum.getPhone11()+" "+datum.getPhone12()+" "+datum.getPhone13()+" "+datum.getPhone14()+" "+datum.getPhone15());
        txtpos.setText(datum.getPostCode());
        txtkota.setText(datum.getCity());
        txtalamat.setText(datum.getAddress());

        initSpinnerPickUp();
        initSpinnerReason();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

        if (Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[] {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        }
        spinnerpickup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position != 0 && position != 1 && position != 6)
                    spinnerreason.setVisibility(View.VISIBLE);
                    //etData.setVisibility(View.GONE);
                    txtdate.setVisibility(View.GONE);
                    btnktp.setVisibility(View.GONE);
                    btnnpwp.setVisibility(View.GONE);
                    imagektp.setVisibility(View.GONE);
                    imagenpwp.setVisibility(View.GONE);
                if (position !=2 && position !=3 && position !=4 && position != 5){
                    spinnerreason.setVisibility(View.GONE);
                    txtdate.setVisibility(View.VISIBLE);
                    btnktp.setVisibility(View.VISIBLE);
                    btnnpwp.setVisibility(View.VISIBLE);
                    imagektp.setVisibility(View.VISIBLE);
                    imagenpwp.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        imagenpwp=findViewById(R.id.imagenpwp);
        imagektp=findViewById(R.id.imagektp);
        imagebukti=findViewById(R.id.imagebukti);
        btnkirim=findViewById(R.id.btnkirim);
        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendTes();
                UpdateTes();
                finish();
            }
        });
        btnbukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               capturepickbukti();
            }
        });
        btnktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturepicktp();
            }
        });
        btnnpwp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturepicnpwp();
            }
        });
    }

    private void capturepicktp()
    {

        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        startActivityForResult(intent, 1213);
    }

    private void capturepicnpwp()
    {

        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        startActivityForResult(intent, 1214);
    }

    private void capturepickbukti()
    {

        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        startActivityForResult(intent, 1215);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // if the result is capturing Image
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1213 && resultCode == Activity.RESULT_OK) {
            filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage = BitmapFactory.decodeFile(filePath);
            imagektp.setImageBitmap(selectedImage);
        }
        if (requestCode == 1214 && resultCode == Activity.RESULT_OK) {
            filePath2 = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap  selectedImage2 = BitmapFactory.decodeFile(filePath2);
            imagenpwp.setImageBitmap(selectedImage2);
        }
        if (requestCode == 1215 && resultCode == Activity.RESULT_OK) {
            filePath3 = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage3 = BitmapFactory.decodeFile(filePath3);
            imagebukti.setImageBitmap(selectedImage3);
        }
    }

    private void initSpinnerPickUp(){
        final APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        final Call<ModelPickup> data = apiInterface.getPickUp();
        data.enqueue(new Callback<ModelPickup>() {
            @Override
            public void onResponse(Call<ModelPickup> call, Response<ModelPickup> response) {
                if (response.isSuccessful()) {
                    ModelPickup listdata = response.body();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < listdata.getData().size(); i++){
                        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        pref.edit().putString("kode",listdata.getData().get(i).getKodePickUp().toString()).commit();
                        String code = listdata.getData().get(i).getKodePickUp();
                        String status= listdata.getData().get(i).getStatus();
                        // listSpinner.add(code);
                        listSpinner.add(code +"  "+status);
                        //listSpinner.add(selesai);
                    }
                    listSpinner.add(0, "- Pilih -");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TesActivity.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerpickup.setAdapter(adapter);
                } else {
                    Toast.makeText(TesActivity.this, "Gagal mengambil data shift", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ModelPickup> call, Throwable t) {
                Toast.makeText(TesActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerReason(){
        final APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        final Call<ModelReason> data = apiInterface.getReason();
        data.enqueue(new Callback<ModelReason>() {
            @Override
            public void onResponse(Call<ModelReason> call, Response<ModelReason> response) {
                if (response.isSuccessful()) {
                    ModelReason listdata1 = response.body();
                    List<String> listSpinner1 = new ArrayList<String>();
                    for (int i = 0; i < listdata1.getData().size(); i++){
                        String koder= listdata1.getData().get(i).getReasonCode();
                        String reason= listdata1.getData().get(i).getReason();
                        //listSpinner1.add(koder);
                        listSpinner1.add(koder+"  "+reason);
                        //listSpinner.add(selesai);
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(TesActivity.this,
                            android.R.layout.simple_spinner_item, listSpinner1);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerreason.setAdapter(adapter);
                } else {
                    Toast.makeText(TesActivity.this, "Gagal mengambil data shift", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ModelReason> call, Throwable t) {
                Toast.makeText(TesActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendTes() {
        File foto = new File(filePath);
        File foto2 = new File(filePath2);
        File  foto3 = new File(filePath3);
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), foto);
        MultipartBody.Part imagenpwp = MultipartBody.Part.createFormData("image_name_pemilik", foto.getName() + ".jpg", requestFile1);

        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), foto2);
        MultipartBody.Part imagektp = MultipartBody.Part.createFormData("image_name_ktp", foto2.getName() + ".jpg", requestFile2);

        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), foto3);
        MultipartBody.Part imagebukti = MultipartBody.Part.createFormData("image_name_bukti", foto3.getName() + ".jpg", requestFile3);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String now = formatter.format(new Date());


        APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        Call<modeltes> postAdd = apiInterface.sendTes(
                imagenpwp,
                imagektp,
                imagebukti
        );

        postAdd.enqueue(new Callback<modeltes>() {
            @Override
            public void onResponse(Call<modeltes> call, Response<modeltes> response) {
                modeltes responServer = response.body();
                if (responServer != null) {
                    Toast.makeText(TesActivity.this,responServer.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<modeltes> call, Throwable t) {
                Toast.makeText(TesActivity.this, "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    private void UpdateTes() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String now = formatter.format(new Date());

        String id= datum.getId();
        String idform = datum.getIdForm();
        String unnik = datum.getUniqueId();
        String cab = datum.getCabang();
        String cas = datum.getNoCase();
        String ci = datum.getCis();
        String na = datum.getName();
        String dobi = datum.getDob();
        String gende = datum.getGender();
        String addre = datum.getAddress();
        String adge = datum.getAddressGeotag();
        String post = datum.getPostCode();
        String cit = datum.getCity();
        String ci2 = datum.getCity2();
        String p1 = datum.getPhone1();
        String p2 = datum.getPhone2();
        String p3 = datum.getPhone3();
        String p4 = datum.getPhone4();
        String p5 = datum.getPhone5();
        String p6 = datum.getPhone6();
        String p7 = datum.getPhone7();
        String p8 = datum.getPhone8();
        String p9 = datum.getPhone9();
        String p10 = datum.getPhone10();
        String p11 = datum.getPhone11();
        String p12 = datum.getPhone12();
        String p13 = datum.getPhone13();
        String p14 = datum.getPhone14();
        String p15 = datum.getPhone15();
        String idc = datum.getIdCampaign();
        String ca = datum.getCampaign();
        String ty = datum.getType();
        String user = datum.getUsername();
        String tmn = datum.getTmName();
        String tmc = datum.getTmCode();
        String aggd = datum.getAggreeDate();
        String agt = datum.getAgreeTime();
        String upd = datum.getUploadDate();
        String tgpu = datum.getTglPu();
        String bar = datum.getBarcode();
        String tmpu = datum.getTimePu();
        String rem = datum.getRemark();
        String kpu = datum.getKodePickUp();
        String kgu = datum.getKodeGagalPu();
        String kcu = datum.getKodeCancelPu();
        String pud = datum.getPickUpDate();
        String msc = datum.getMsCode();
        String msn = datum.getMsName();
        String spvc = datum.getSpvCode();
        String asmc = datum.getAsmCode();
        String imaty = datum.getImageType();
        String gip = datum.getGeoInfoPemilik();
        String gik = datum.getGeoInfoKtp();
        String gib = datum.getGeoInfoBukti();
        String tda = datum.getTanggalDistribusiAsm();
        String td = datum.getTanggalDistribusi();
        String note = datum.getNoteIinprocessed();
        String pro = datum.getProduct();
        String resche = datum.getTanggalReschedule();
        String sc = datum.getSourceCode();
        String cd = now;
        String sv = datum.getStatusValidasi();
        String vb = datum.getValidasiBy();
        String sva = datum.getStatusValidasiAdmin();
        String cb = datum.getCreatedBy();
        String scl = datum.getStatusClose();
        String clodat = datum.getCloseDate();
        String cloby = datum.getClosedBy();
        String br = datum.getBranch();
        String tst = datum.getTanggalStatusTerakhir();
        String ir = datum.getIsReturn();
        String rd = datum.getReturnDate();
        String scal = datum.getStatusCall();
        String rcal = datum.getReasonCall();
        String or = datum.getOtherReason();
        String calby = datum.getCallBy();
        String caldat = datum.getCallDate();
        String caltim = datum.getCallTime();
        String no = txtnote.getText().toString();




        RequestBody idd = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        RequestBody idformm = RequestBody.create(MediaType.parse("multipart/form-data"), idform);
        RequestBody unik = RequestBody.create(MediaType.parse("multipart/form-data"), unnik);
        RequestBody caba = RequestBody.create(MediaType.parse("multipart/form-data"), cab);
        RequestBody nocase = RequestBody.create(MediaType.parse("multipart/form-data"), cas);
        RequestBody noci = RequestBody.create(MediaType.parse("multipart/form-data"), ci);
        RequestBody namee = RequestBody.create(MediaType.parse("multipart/form-data"), na);
        RequestBody dobii = RequestBody.create(MediaType.parse("multipart/form-data"), dobi);
        RequestBody gender = RequestBody.create(MediaType.parse("multipart/form-data"), gende);
        RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"), addre);
        RequestBody addressgeo = RequestBody.create(MediaType.parse("multipart/form-data"), adge);
        RequestBody postcode = RequestBody.create(MediaType.parse("multipart/form-data"), post);
        RequestBody city = RequestBody.create(MediaType.parse("multipart/form-data"), cit);
        RequestBody city2 = RequestBody.create(MediaType.parse("multipart/form-data"), ci2);
        RequestBody phon1 = RequestBody.create(MediaType.parse("multipart/form-data"), p1);
        RequestBody phon2 = RequestBody.create(MediaType.parse("multipart/form-data"), p2);
        RequestBody phon3 = RequestBody.create(MediaType.parse("multipart/form-data"), p3);
        RequestBody phon4 = RequestBody.create(MediaType.parse("multipart/form-data"), p4);
        RequestBody phon5 = RequestBody.create(MediaType.parse("multipart/form-data"), p5);
        RequestBody phon6 = RequestBody.create(MediaType.parse("multipart/form-data"), p6);
        RequestBody phon7 = RequestBody.create(MediaType.parse("multipart/form-data"), p7);
        RequestBody phon8 = RequestBody.create(MediaType.parse("multipart/form-data"), p8);
        RequestBody phon9 = RequestBody.create(MediaType.parse("multipart/form-data"), p9);
        RequestBody phon10 = RequestBody.create(MediaType.parse("multipart/form-data"), p10);
        RequestBody phon11 = RequestBody.create(MediaType.parse("multipart/form-data"), p11);
        RequestBody phon12 = RequestBody.create(MediaType.parse("multipart/form-data"), p12);
        RequestBody phon13 = RequestBody.create(MediaType.parse("multipart/form-data"), p13);
        RequestBody phon14 = RequestBody.create(MediaType.parse("multipart/form-data"), p14);
        RequestBody phon15 = RequestBody.create(MediaType.parse("multipart/form-data"), p15);
        RequestBody idcampain = RequestBody.create(MediaType.parse("multipart/form-data"), idc);
        RequestBody campaign = RequestBody.create(MediaType.parse("multipart/form-data"), ca);
        RequestBody type = RequestBody.create(MediaType.parse("multipart/form-data"), ty);
        RequestBody username = RequestBody.create(MediaType.parse("multipart/form-data"), user);
        RequestBody tmname = RequestBody.create(MediaType.parse("multipart/form-data"), tmn);
        RequestBody tmcode = RequestBody.create(MediaType.parse("multipart/form-data"), tmc);
        RequestBody aggreedate = RequestBody.create(MediaType.parse("multipart/form-data"), aggd);
        RequestBody agreetime = RequestBody.create(MediaType.parse("multipart/form-data"), agt);
        RequestBody uploaddate = RequestBody.create(MediaType.parse("multipart/form-data"), upd);
        RequestBody tglpu = RequestBody.create(MediaType.parse("multipart/form-data"), tgpu);
        RequestBody barcode = RequestBody.create(MediaType.parse("multipart/form-data"), bar);
        RequestBody timepu = RequestBody.create(MediaType.parse("multipart/form-data"), tmpu);
        RequestBody remark = RequestBody.create(MediaType.parse("multipart/form-data"), rem);
        RequestBody kodepickup = RequestBody.create(MediaType.parse("multipart/form-data"), kpu);
        RequestBody kodegagalpu = RequestBody.create(MediaType.parse("multipart/form-data"), kgu);
        RequestBody kodecancelup = RequestBody.create(MediaType.parse("multipart/form-data"), kcu);
        RequestBody pickupdate = RequestBody.create(MediaType.parse("multipart/form-data"), pud);
        RequestBody mscode = RequestBody.create(MediaType.parse("multipart/form-data"), msc);
        RequestBody msname = RequestBody.create(MediaType.parse("multipart/form-data"), msn);
        RequestBody spvcode = RequestBody.create(MediaType.parse("multipart/form-data"), spvc);
        RequestBody asmcode = RequestBody.create(MediaType.parse("multipart/form-data"), asmc);
        RequestBody imagetype = RequestBody.create(MediaType.parse("multipart/form-data"), imaty);

//        File foto = new File(filePath);
//        RequestBody propertypemilik = RequestBody.create(MediaType.parse("image/*"),
//                foto);
//        MultipartBody.Part propertyImagePemilik = MultipartBody.Part.createFormData("PropertyImage",
//                foto.getName(),
//                propertypemilik);
//
//        File foto2 = new File(datum.getImageNamePemilik());
//        RequestBody propertyImage = RequestBody.create(MediaType.parse("image/*"),
//                foto2);
//        MultipartBody.Part propertyImagektp = MultipartBody.Part.createFormData("PropertyImage",
//                foto2.getName(),
//                propertyImage);
//
//        File foto3 = new File(datum.getImageNamePemilik());
//        RequestBody propertyImage2 = RequestBody.create(MediaType.parse("image/*"),
//                foto3);
//        MultipartBody.Part propertyImagePart = MultipartBody.Part.createFormData("PropertyImage",
//                foto3.getName(),
//                propertyImage2);


        File foto = new File(filePath);
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), foto);
        MultipartBody.Part image_name_pemilik = MultipartBody.Part.createFormData("image_name_pemilik", foto.getName() + ".jpg", requestFile1);

        File foto2 = new File(filePath2);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), foto2);
        MultipartBody.Part image_name_ktp = MultipartBody.Part.createFormData("image_name_ktp", foto2.getName() + ".jpg", requestFile2);

        RequestBody geoinfopemilik = RequestBody.create(MediaType.parse("multipart/form-data"), gip);
        RequestBody geoinfoktp = RequestBody.create(MediaType.parse("multipart/form-data"), gik);

        File foto3 = new File(filePath3);
        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), foto3);
        MultipartBody.Part image_name_bukti = MultipartBody.Part.createFormData("image_name_bukti", foto3.getName() + ".jpg", requestFile3);

        RequestBody geoinfobukti = RequestBody.create(MediaType.parse("multipart/form-data"), gib);
        RequestBody distribusiasm = RequestBody.create(MediaType.parse("multipart/form-data"), tda );
        RequestBody distribusi = RequestBody.create(MediaType.parse("multipart/form-data"), td);
        RequestBody notelinproses = RequestBody.create(MediaType.parse("multipart/form-data"), note);
        RequestBody product = RequestBody.create(MediaType.parse("multipart/form-data"), pro);
        RequestBody tanggalreschedule = RequestBody.create(MediaType.parse("multipart/form-data"), resche);
        RequestBody sourcecode = RequestBody.create(MediaType.parse("multipart/form-data"), sc);
        RequestBody createddate = RequestBody.create(MediaType.parse("multipart/form-data"), cd);
        RequestBody statusvalidasi = RequestBody.create(MediaType.parse("multipart/form-data"), sv);
        RequestBody validasiby = RequestBody.create(MediaType.parse("multipart/form-data"), vb);
        RequestBody statusvalidasiadmin = RequestBody.create(MediaType.parse("multipart/form-data"), sva);
        RequestBody createdby = RequestBody.create(MediaType.parse("multipart/form-data"), cb);
        RequestBody statusclose = RequestBody.create(MediaType.parse("multipart/form-data"), scl);
        RequestBody closedate = RequestBody.create(MediaType.parse("multipart/form-data"), clodat);
        RequestBody closeby = RequestBody.create(MediaType.parse("multipart/form-data"), cloby);
        RequestBody branch = RequestBody.create(MediaType.parse("multipart/form-data"), br);
        RequestBody tglstatusterakhir = RequestBody.create(MediaType.parse("multipart/form-data"), tst);
        RequestBody isreturn = RequestBody.create(MediaType.parse("multipart/form-data"), ir);
        RequestBody returndate = RequestBody.create(MediaType.parse("multipart/form-data"), rd);
        RequestBody statuscall = RequestBody.create(MediaType.parse("multipart/form-data"), scal);
        RequestBody reasoncall = RequestBody.create(MediaType.parse("multipart/form-data"), rcal);
        RequestBody otherreason = RequestBody.create(MediaType.parse("multipart/form-data"), or);
        RequestBody callby = RequestBody.create(MediaType.parse("multipart/form-data"), calby);
        RequestBody calldate = RequestBody.create(MediaType.parse("multipart/form-data"), caldat);
        RequestBody calltime = RequestBody.create(MediaType.parse("multipart/form-data"), caltim);
        RequestBody notess = RequestBody.create(MediaType.parse("multipart/form-data"), no);

        APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        Call<PostUpload2> postAdd = apiInterface.sendDataUpload2( idd,idformm,unik, caba,nocase, noci, namee, dobii, gender, address, addressgeo, postcode, city, city2, phon1, phon2, phon3, phon4, phon5, phon6, phon7, phon8, phon9, phon10, phon11, phon12, phon13, phon14, phon15, idcampain, campaign,
                type, username, tmname, tmcode, aggreedate, agreetime, uploaddate, tglpu, barcode, timepu, remark, kodepickup, kodegagalpu, kodecancelup, pickupdate, mscode, msname, spvcode, asmcode,
                imagetype,image_name_pemilik, image_name_ktp, geoinfopemilik, geoinfoktp, image_name_bukti, geoinfobukti, distribusiasm, distribusi, notelinproses, product, tanggalreschedule, sourcecode, createddate,
                statusvalidasi, validasiby, statusvalidasiadmin, createdby, statusclose, closedate, closeby, branch, tglstatusterakhir, isreturn, returndate, statuscall, reasoncall, otherreason, callby, calldate, calltime, notess
        );
        postAdd.enqueue(new Callback<PostUpload2>() {
            @Override
            public void onResponse(Call<PostUpload2> call, Response<PostUpload2> response) {
                PostUpload2 responServer = response.body();
                if (responServer != null) {
                    Toast.makeText(TesActivity.this,responServer.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PostUpload2> call, Throwable t) {
                Toast.makeText(TesActivity.this, "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
