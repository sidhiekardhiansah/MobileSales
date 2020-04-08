package com.rkrzmail.mobilesales;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.rkrzmail.mobilesales.APIService.APIClient2;
import com.rkrzmail.mobilesales.APIService.APIInterfacesRest;
import com.rkrzmail.mobilesales.APIService.AppUtil;
import com.rkrzmail.mobilesales.Adapter.UploadAdapter;
import com.rkrzmail.mobilesales.model.activity.PostActivity;
import com.rkrzmail.mobilesales.model.dataupload.DataUpload;
import com.rkrzmail.mobilesales.model.dataupload.Datum;
import com.rkrzmail.mobilesales.model.dataupload.PostUpload;
import com.rkrzmail.mobilesales.model.pickup.ModelPickup;
import com.rkrzmail.mobilesales.model.reason.ModelReason;
import com.rkrzmail.mobilesales.model.upload.Data;
import com.rkrzmail.mobilesales.model.upload.PostUpload2;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import io.github.jrizani.jrdatetimepicker.DateTimePicker;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE;
import static android.provider.MediaStore.Files.FileColumns.MEDIA_TYPE_VIDEO;

public class UpdateActivity extends AppCompatActivity implements LocationListener {
    public static final String TAP_PRODUK = "tap_produk";
    SimpleDateFormat sdf;
    Datum datum;
    Data data;
    Bundle bundle;
    private Context ctx;
    UploadAdapter adapter;
    DataUpload modelupload;
    TextView txtnama, txtphone, txtpos, txtkota, txtalamat, txtid;
    EditText etData, txtdate, txtkonversi, txtnote;
    Button btnktp, btnnpwp, btnbukti, btnkirim, btnkonversi;
    ImageView imagektp, imagenpwp, imagebukti;
    String sm, filePath, latitude, longitude, kode, Gnama, filePath2, filePath3;
    private static final int REQUEST_LOCATION=1;
    Spinner spinnerpickup, spinnerreason;
    LocationManager locationManager;
    DatePickerDialog.OnDateSetListener date;
    private Calendar myCalendar;
    int monthOfYear;
    double latitude2, longitude2;
    SharedPreferences pref;
    double latitude3, longitude3;
Bitmap bitmap, bitmap2, bitmap3;
    Bitmap selectedImage, selectedImage2, selectedImage3, foto;
    private String myLocation;
    private String mylocation2;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sm = pref.getString("mscode", "mscode");
        Gnama = pref.getString("name", "name");
       // etData=findViewById(R.id.date);
        txtdate= findViewById(R.id.txtdate);
        txtnote=findViewById(R.id.txtnote);
        txtid= findViewById(R.id.txtid);
        txtkonversi = findViewById(R.id.txtkonversi);
        myCalendar = Calendar.getInstance();
//        date = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                myCalendar.set(Calendar.YEAR, year);
//                myCalendar.set(Calendar.MONTH, monthOfYear);
//                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//                updateLabel();
//            }
//        };

         //dataItemList = getIntent().getParcelableExtra("id");
       // this.Objects= extras.getParcelableArrayList("Object_list");
        datum = getIntent().getParcelableExtra("id");
       // datum = getIntent().getParcelableExtra(TAP_PRODUK);
        //txtid.setText(modelupload.getData().get(i).get);
//        etData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new DatePickerDialog(SecondActivity.this, date, myCalendar
//                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
//                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
//            }
//        });

        btnkirim= findViewById(R.id.btnkirim);
        spinnerpickup= findViewById(R.id.spinnerpickup);
        spinnerreason= findViewById(R.id.spinnerreason);
        btnktp= findViewById(R.id.btnktp);
        btnnpwp= findViewById(R.id.btnnpwp);
        btnbukti= findViewById(R.id.btnbukti);
        btnkonversi= findViewById(R.id.btnkonversi);
        imagektp= findViewById(R.id.imagektp);
        imagenpwp= findViewById(R.id.imagenpwp);
        imagebukti= findViewById(R.id.imagebukti);
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
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

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
            btnktp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
                    capturepicktp();

                }
            });
            btnkonversi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String location= txtalamat.getText().toString();
                    Geocoder geocoder = new Geocoder(UpdateActivity.this);
                    List<Address> addresses = null;
                    try {
                        addresses = geocoder.getFromLocationName(location, 1);
                        Address add= addresses.get(0);
                        latitude3 = add.getLatitude();
                        longitude3 = add.getLongitude();
                        txtkonversi.setText(latitude3+","+longitude3);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            btnnpwp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    capturepicnpwp();
                }
            });

            btnbukti.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        onGPS();
                    } else {
                        //gps already exist
                        getLocation();
                    }
                    capturepicbukti();
                }
            });

            btnkirim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //sendDataActivity();
                    sendDataUpload();
                    finish();
                }
            });
            txtkonversi.setText(latitude3+","+longitude3);
    }


    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        sdf = new SimpleDateFormat(myFormat, Locale.US);
      //  etData.setText(sdf.format(myCalendar.getTime()));
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

    private void capturepicbukti()
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
            selectedImage = BitmapFactory.decodeFile(filePath);
            imagektp.setImageBitmap(selectedImage);
        }
        if (requestCode == 1214 && resultCode == Activity.RESULT_OK) {
            filePath2 = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            selectedImage2 = BitmapFactory.decodeFile(filePath2);
            imagenpwp.setImageBitmap(selectedImage2);
        }
        if (requestCode == 1215 && resultCode == Activity.RESULT_OK) {
            filePath3 = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            selectedImage3 = BitmapFactory.decodeFile(filePath3);
            imagebukti.setImageBitmap(selectedImage3);
        }
    }

    private void onGPS() {
        final AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setMessage("Enabled GPS").setCancelable(false).setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        final AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    public void getLocation(){
        if(ActivityCompat.checkSelfPermission(UpdateActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(UpdateActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else{
            Location locationGPS= locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetwork= locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive= locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if(locationGPS != null){
                double lat= locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                //  txtlocation.setText("your location"+"\n"+"latitude"+latitude+"\n"+"longitude"+longitude);
            } else if(locationPassive != null){
                double lat= locationPassive.getLatitude();
                double longi = locationPassive.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                // txtlocation.setText("your location"+"\n"+"latitude"+latitude+"\n"+"longitude"+longitude);
            } else if(locationNetwork != null){
                double lat= locationNetwork.getLatitude();
                double longi = locationNetwork.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                // txtlocation.setText("your location"+"\n"+"latitude"+latitude+"\n"+"longitude"+longitude);
            } else {
                Toast.makeText(this, "Cant get your location", Toast.LENGTH_SHORT).show();
            }
            //try run
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateActivity.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerpickup.setAdapter(adapter);
                } else {
                    Toast.makeText(UpdateActivity.this, "Gagal mengambil data shift", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelPickup> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
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
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateActivity.this,
                            android.R.layout.simple_spinner_item, listSpinner1);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerreason.setAdapter(adapter);
                } else {
                    Toast.makeText(UpdateActivity.this, "Gagal mengambil data shift", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ModelReason> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude2 =location.getLatitude();
        longitude2=location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude","disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude","enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude","status");
    }

    //send post data with image
    private void sendDataUpload() {


        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String now = formatter.format(new Date());

        File foto = new File(filePath);
        File foto2 = new File(filePath2);
        File  foto3 = new File(filePath3);
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), foto);
        MultipartBody.Part imagenpwp = MultipartBody.Part.createFormData("image_name_pemilik", foto.getName() + ".jpg", requestFile1);

        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), foto2);
        MultipartBody.Part imagektp = MultipartBody.Part.createFormData("image_name_ktp", foto2.getName() + ".jpg", requestFile2);

        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), foto3);
        MultipartBody.Part imagebukti = MultipartBody.Part.createFormData("image_name_bukti", foto3.getName() + ".jpg", requestFile3);
        String lspinner= spinnerpickup.getSelectedItem().toString();
        String[] shift = lspinner.split(" ");
        String spinner = shift[0];

        String spinnerr= spinnerreason.getSelectedItem().toString();
        String[] shiftr = spinnerr.split(" ");
        String spinnerrr = shiftr[0];

        APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        Call<PostUpload2> postAdd = apiInterface.sendDataUpload2(
                toRequestBody(AppUtil.replaceNull(datum.getId())),
                toRequestBody(AppUtil.replaceNull(datum.getIdForm())),
                toRequestBody(AppUtil.replaceNull(datum.getUniqueId())),
                toRequestBody(AppUtil.replaceNull(datum.getCabang())),
                toRequestBody(AppUtil.replaceNull(datum.getNoCase())),
                toRequestBody(AppUtil.replaceNull(datum.getCis())),
                toRequestBody(AppUtil.replaceNull(datum.getName())),
                toRequestBody(AppUtil.replaceNull(datum.getDob())),
                toRequestBody(AppUtil.replaceNull(datum.getGender())),
                toRequestBody(AppUtil.replaceNull(datum.getAddress())),
                toRequestBody(AppUtil.replaceNull(txtkonversi.getText().toString())),
                toRequestBody(AppUtil.replaceNull(datum.getPostCode())),
                toRequestBody(AppUtil.replaceNull(datum.getCity())),
                toRequestBody(AppUtil.replaceNull(datum.getCity2())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone1())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone2())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone3())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone4())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone5())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone6())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone7())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone8())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone9())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone10())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone11())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone12())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone13())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone14())),
                toRequestBody(AppUtil.replaceNull(datum.getPhone15())),
                toRequestBody(AppUtil.replaceNull(datum.getIdCampaign())),
                toRequestBody(AppUtil.replaceNull(datum.getCampaign())),
                toRequestBody(AppUtil.replaceNull(datum.getType())),
                toRequestBody(AppUtil.replaceNull(datum.getUsername())),
                toRequestBody(AppUtil.replaceNull(datum.getTmName())),
                toRequestBody(AppUtil.replaceNull(datum.getTmCode())),
                toRequestBody(AppUtil.replaceNull(datum.getAggreeDate())),
                toRequestBody(AppUtil.replaceNull(datum.getAgreeTime())),
                toRequestBody(AppUtil.replaceNull(datum.getUploadDate())),
                toRequestBody(AppUtil.replaceNull(datum.getTglPu())),
                toRequestBody(AppUtil.replaceNull(datum.getBarcode())),
                toRequestBody(AppUtil.replaceNull(datum.getTimePu())),
                toRequestBody(AppUtil.replaceNull(datum.getRemark())),
                toRequestBody(AppUtil.replaceNull(spinner)),
                toRequestBody(AppUtil.replaceNull(spinnerrr)),
                toRequestBody(AppUtil.replaceNull(datum.getKodeCancelPu())),
                toRequestBody(AppUtil.replaceNull(datum.getPickUpDate())),
                toRequestBody(AppUtil.replaceNull(datum.getMsCode())),
                toRequestBody(AppUtil.replaceNull(datum.getMsName())),
                toRequestBody(AppUtil.replaceNull(datum.getSpvCode())),
                toRequestBody(AppUtil.replaceNull(datum.getAsmCode())),
                toRequestBody(AppUtil.replaceNull(datum.getImageType())),
                imagenpwp,
                imagektp,
                toRequestBody(AppUtil.replaceNull(datum.getGeoInfoPemilik())),
                toRequestBody(AppUtil.replaceNull(datum.getGeoInfoKtp())),
                imagebukti,
                toRequestBody(AppUtil.replaceNull(latitude+","+longitude)),
                toRequestBody(AppUtil.replaceNull(datum.getTanggalDistribusiAsm())),
                toRequestBody(AppUtil.replaceNull(datum.getTanggalDistribusi())),
                toRequestBody(AppUtil.replaceNull(datum.getNoteIinprocessed())),
                toRequestBody(AppUtil.replaceNull(datum.getProduct())),
                toRequestBody(AppUtil.replaceNull(txtdate.getText().toString())),
                toRequestBody(AppUtil.replaceNull(datum.getSourceCode())),
                toRequestBody(AppUtil.replaceNull(now)),
                toRequestBody(AppUtil.replaceNull(datum.getStatusValidasi())),
                toRequestBody(AppUtil.replaceNull(datum.getValidasiBy())),
                toRequestBody(AppUtil.replaceNull(datum.getStatusValidasiAdmin())),
                toRequestBody(AppUtil.replaceNull(datum.getCreatedBy())),
                toRequestBody(AppUtil.replaceNull(datum.getStatusClose())),
                toRequestBody(AppUtil.replaceNull(datum.getCloseDate())),
                toRequestBody(AppUtil.replaceNull(datum.getClosedBy())),
                toRequestBody(AppUtil.replaceNull(datum.getBranch())),
                toRequestBody(AppUtil.replaceNull(datum.getTanggalStatusTerakhir())),
                toRequestBody(AppUtil.replaceNull(datum.getIsReturn())),
                toRequestBody(AppUtil.replaceNull(datum.getReturnDate())),
                toRequestBody(AppUtil.replaceNull(datum.getStatusCall())),
                toRequestBody(AppUtil.replaceNull(datum.getReasonCall())),
                toRequestBody(AppUtil.replaceNull(datum.getOtherReason())),
                toRequestBody(AppUtil.replaceNull(datum.getCallBy())),
                toRequestBody(AppUtil.replaceNull(datum.getCallDate())),
                toRequestBody(AppUtil.replaceNull(datum.getCallTime())),
                toRequestBody(AppUtil.replaceNull(txtnote.getText().toString()))
        );

        postAdd.enqueue(new Callback<PostUpload2>() {
            @Override
            public void onResponse(Call<PostUpload2> call, Response<PostUpload2> response) {

                PostUpload2 responServer = response.body();
                if (responServer != null) {
                    Toast.makeText(UpdateActivity.this,responServer.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PostUpload2> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    //change string to requestbody
    public RequestBody toRequestBody(String value) {
        if (value == null) {
            value = "";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);

        return body;
    }

    private void sendDataActivity() {
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        kode = pref.getString("kode", "kode");

        String idform = datum.getIdForm();
        String id_detail = "id_detail";
        String work = "worklist";
        String df = "worklist";
        String dt = "worklist";
        String mc = sm;
        String status = kode;
        String ket = "worklist";

        String cb = Gnama;

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String now = formatter.format(new Date());
        RequestBody judul = RequestBody.create(MediaType.parse("multipart/form-data"), idform);
        RequestBody iddetail = RequestBody.create(MediaType.parse("multipart/form-data"), id_detail);
        RequestBody worklist = RequestBody.create(MediaType.parse("multipart/form-data"), work);
        RequestBody disfrom = RequestBody.create(MediaType.parse("multipart/form-data"), df);
        RequestBody disto = RequestBody.create(MediaType.parse("multipart/form-data"), dt);
        RequestBody ms = RequestBody.create(MediaType.parse("multipart/form-data"), mc);
        RequestBody sts = RequestBody.create(MediaType.parse("multipart/form-data"), status);
        RequestBody keterangan = RequestBody.create(MediaType.parse("multipart/form-data"), ket);
        RequestBody waktu = RequestBody.create(MediaType.parse("multipart/form-data"), now);
        RequestBody cby = RequestBody.create(MediaType.parse("multipart/form-data"), cb);

        APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        Call<PostActivity> postAdd = apiInterface.sendDataActivity(judul, iddetail, worklist, disfrom, disto, ms, sts,keterangan, waktu, cby);

        postAdd.enqueue(new Callback<PostActivity>() {
            @Override
            public void onResponse(Call<PostActivity> call, Response<PostActivity> response) {
                PostActivity responServer = response.body();
                if (responServer != null) {
                    Toast.makeText(UpdateActivity.this,responServer.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<PostActivity> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });

    }

    //change string to requestbody
    public RequestBody toRequestBody1(String value) {
        if (value == null) {
            value = "";
        }
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), value);
        return body;
    }
}
