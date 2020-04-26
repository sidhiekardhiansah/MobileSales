package com.rkrzmail.mobilesales;

import androidx.annotation.RequiresApi;
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
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.rkrzmail.mobilesales.APIService.APIClient2;
import com.rkrzmail.mobilesales.APIService.APIInterfacesRest;
import com.rkrzmail.mobilesales.APIService.AppUtil;
import com.rkrzmail.mobilesales.model.activity.PostActivity;
import com.rkrzmail.mobilesales.model.cancelpickup.GetCancelPickUp;
import com.rkrzmail.mobilesales.model.dataupload.DataUpload;
import com.rkrzmail.mobilesales.model.dataupload.Datum;
import com.rkrzmail.mobilesales.model.gagalpickup.GetGagalPickUp;
import com.rkrzmail.mobilesales.model.pickup2.ModelPickup2;
import com.rkrzmail.mobilesales.model.upload.Data;
import com.rkrzmail.mobilesales.model.upload.PostUpload2;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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

public class UpdateActivity extends AppCompatActivity implements LocationListener {
    public static final int CAMERA1 = 1213;
    public static final int CAMERA2 = 1214;
    public static final int CAMERA3 = 1215;
    Datum datum;
    TextView txtpickup, txtlat;
    EditText txtnama, txtphone, txtpos, txtkota, txtalamat, txtid;
    EditText txtdate, txtkonversi, txtnote;
    Button btnktp, btnnpwp, btnbukti, btnkirim;
    ImageView imagektp, imagenpwp, imagebukti;
    String sm, filePath, latitude, longitude, Gnama, filePath2, filePath3, GNIK;
    private static final int REQUEST_LOCATION = 1;
    Spinner spinnerpickup, spinnerreasongagal, spinnerreasoncancel;
    LocationManager locationManager;
    double latitude2, longitude2;
    SharedPreferences pref;
    double latitude3, longitude3;
    Bitmap selectedImage, selectedImage2, selectedImage3;
    String now1;
    Button btndate;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        sm = pref.getString("mscode", "mscode");
        GNIK = pref.getString("nik", "nik");
        Gnama = pref.getString("name", "name");
        datum = getIntent().getParcelableExtra("id");
        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        initView();
        konversi();
        initSpinnerPickUp();
        initSpinnerGagalPickUp();
        initSpinnerCancelPickUp();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());

//        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    Activity#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for Activity#requestPermissions for more details.
//            return;
//        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 2);
        }
        spinnerpickup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectspinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        btndate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datepicker();
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
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            onGPS();
        } else {
            //gps already exist
            getLocation();
        }
        btnbukti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                capturepicbukti();
            }
        });

        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView errorText = (TextView)spinnerpickup.getSelectedView();
                if(txtlat.getText().toString().equalsIgnoreCase("null,null")){
                    Snackbar.make(v, "silahkan nyalakan permission location", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                }
                else if (spinnerpickup.getSelectedItem().toString().contains("- Pilih -")){
                    errorText.setText("Cek kembali jenis pick up");
                    errorText.setError("");
                    Snackbar.make(v, "Cek kembali jenis pick up", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if (!txtlat.getText().toString().equalsIgnoreCase("null,null") && !spinnerpickup.getSelectedItem().toString().contains("- Pilih -")){
                if (txtdate.getText().toString().length() == 0) {
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
                    now1 = formatter.format(new Date());
                    txtpickup.setText(now1);
                        sendDataActivity();
                        sendDataUpload();
                        finish();
                } else if (txtdate.getText().toString().length() >= 0){
                    txtpickup.setText("0000-00-00 00:00:00");
                    sendDataActivity();
                    sendDataUpload();
                    finish();
                } else if (spinnerpickup.getSelectedItem().toString().contains("1004")){
                    spinnerreasoncancel.setSelection(0);
                    spinnerreasongagal.setSelection(0);
                    sendDataActivity();
                    sendDataUpload();
                    finish();
                }
                }
            }
        });
        txtlat.setText(latitude + "," + longitude);
        txtkonversi.setText(latitude3 + "," + longitude3);
    }

    private void datepicker() {
        /**
         * Calendar untuk mendapatkan tanggal sekarang
         */
        Calendar newCalendar = Calendar.getInstance();

        /**
         * Initiate DatePicker dialog
         */
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                /**
                 * Method ini dipanggil saat kita selesai memilih tanggal di DatePicker
                 */

                /**
                 * Set Calendar untuk menampung tanggal yang dipilih
                 */
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                /**
                 * Update TextView dengan tanggal yang kita pilih
                 */
                txtdate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        /**
         * Tampilkan DatePicker dialog
         */
        datePickerDialog.show();
    }



    public void selectspinner(){
        if (spinnerpickup.getSelectedItemPosition() == 0) {
            btndate.setVisibility(View.GONE);
            spinnerpickup.setVisibility(View.VISIBLE);
            spinnerreasongagal.setVisibility(View.GONE);
            spinnerreasoncancel.setVisibility(View.GONE);
            txtdate.setVisibility(View.GONE);
            btnktp.setVisibility(View.VISIBLE);
            btnnpwp.setVisibility(View.VISIBLE);
            imagektp.setVisibility(View.VISIBLE);
            imagenpwp.setVisibility(View.VISIBLE);
        }
        if (spinnerpickup.getSelectedItemPosition() == 1) {
            btndate.setVisibility(View.GONE);
            spinnerreasongagal.setVisibility(View.GONE);
            spinnerreasoncancel.setVisibility(View.GONE);
            txtpickup.setVisibility(View.VISIBLE);
            txtdate.setVisibility(View.GONE);
            btnktp.setVisibility(View.VISIBLE);
            btnnpwp.setVisibility(View.VISIBLE);
            imagektp.setVisibility(View.VISIBLE);
            imagenpwp.setVisibility(View.VISIBLE);
        }
        if (spinnerpickup.getSelectedItemPosition() == 2) {
            btndate.setVisibility(View.GONE);
            txtpickup.setVisibility(View.GONE);
            spinnerreasongagal.setVisibility(View.VISIBLE);
            spinnerreasoncancel.setVisibility(View.GONE);
            txtdate.setVisibility(View.GONE);
            btnktp.setVisibility(View.GONE);
            btnnpwp.setVisibility(View.GONE);
            imagektp.setVisibility(View.GONE);
            imagenpwp.setVisibility(View.GONE);
        }
        if (spinnerpickup.getSelectedItemPosition() == 3) {
            btndate.setVisibility(View.GONE);
            txtpickup.setVisibility(View.GONE);
            spinnerreasongagal.setVisibility(View.GONE);
            spinnerreasoncancel.setVisibility(View.VISIBLE);
            txtdate.setVisibility(View.GONE);
            btnktp.setVisibility(View.GONE);
            btnnpwp.setVisibility(View.GONE);
            imagektp.setVisibility(View.GONE);
            imagenpwp.setVisibility(View.GONE);
        }
        if (spinnerpickup.getSelectedItemPosition() == 4) {
            btndate.setVisibility(View.VISIBLE);
            txtpickup.setVisibility(View.GONE);
            spinnerreasongagal.setVisibility(View.GONE);
            spinnerreasoncancel.setVisibility(View.GONE);
            txtdate.setVisibility(View.VISIBLE);
            btnktp.setVisibility(View.VISIBLE);
            btnnpwp.setVisibility(View.VISIBLE);
            imagektp.setVisibility(View.VISIBLE);
            imagenpwp.setVisibility(View.VISIBLE);
        }
    }

    private void konversi() {
        String location = txtalamat.getText().toString();
        Geocoder geocoder = new Geocoder(UpdateActivity.this);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(location, 1);
            Address add = addresses.get(0);
            latitude3 = add.getLatitude();
            longitude3 = add.getLongitude();
            txtkonversi.setText(latitude3 + "," + longitude3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        btndate= findViewById(R.id.btndate);
        txtlat = findViewById(R.id.txtlat);
        txtpickup = findViewById(R.id.txtpickup);
        txtdate = findViewById(R.id.txtdate);
        txtnote = findViewById(R.id.txtnote);
        txtid = findViewById(R.id.txtid);
        txtkonversi = findViewById(R.id.txtkonversi);
        btnkirim = findViewById(R.id.btnkirim);
        spinnerpickup = findViewById(R.id.spinnerpickup);
        spinnerreasongagal = findViewById(R.id.spinnerreasongagal);
        spinnerreasoncancel = findViewById(R.id.spinnerreasoncancel);
        btnktp = findViewById(R.id.btnktp);
        btnnpwp = findViewById(R.id.btnnpwp);
        btnbukti = findViewById(R.id.btnbukti);
        imagektp = findViewById(R.id.imagektp);
        imagenpwp = findViewById(R.id.imagenpwp);
        imagebukti = findViewById(R.id.imagebukti);
        txtnama = findViewById(R.id.txtnama);
        txtphone = findViewById(R.id.txtphone);
        txtpos = findViewById(R.id.txtpos);
        txtkota = findViewById(R.id.txtkota);
        txtalamat = findViewById(R.id.txtalamat);
        txtnama.setText(datum.getUsername());
        txtphone.setText(datum.getPhone1() + " " + datum.getPhone2() + " " + datum.getPhone3() + " " + datum.getPhone4() + " " + datum.getPhone5() + " " + datum.getPhone6() + " " + datum.getPhone7() + " " + datum.getPhone8() + " " + datum.getPhone9() + " " + datum.getPhone10() + " " + datum.getPhone11() + " " + datum.getPhone12() + " " + datum.getPhone13() + " " + datum.getPhone14() + " " + datum.getPhone15());
        txtpos.setText(datum.getPostCode());
        txtkota.setText(datum.getCity() + " " + datum.getCity2());
        txtalamat.setText(datum.getAddress());
    }

    private void capturepicktp() {
        Intent intent2 = new Intent(this, ImageSelectActivity.class);
        intent2.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
        intent2.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent2.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        startActivityForResult(intent2, CAMERA1);
    }

    private void capturepicnpwp() {
        Intent intent = new Intent(this, ImageSelectActivity.class);
        intent.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        startActivityForResult(intent, CAMERA2);
    }

    private void capturepicbukti() {
        Intent intent1 = new Intent(this, ImageSelectActivity.class);
        intent1.putExtra(ImageSelectActivity.FLAG_COMPRESS, true);//default is true
        intent1.putExtra(ImageSelectActivity.FLAG_CAMERA, true);//default is true
        intent1.putExtra(ImageSelectActivity.FLAG_GALLERY, true);//default is true
        startActivityForResult(intent1, CAMERA3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
// if the result is capturing Image
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA1 && resultCode == Activity.RESULT_OK) {
            filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            //Loading Image from URL
            Picasso.get()
                    .load(filePath).
                    resize(400, 400)
                    .into(imagenpwp);
            selectedImage = BitmapFactory.decodeFile(filePath);
            imagenpwp.setImageBitmap(selectedImage);
            Log.d("UpdateActivity", "Onactivityresultsetimagenpwp" +filePath);
        }
        if (requestCode == CAMERA2 && resultCode == Activity.RESULT_OK) {
            filePath2 = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Picasso.get()
                    .load(filePath2).
                    resize(400, 400)
                    .into(imagektp);
            selectedImage2 = BitmapFactory.decodeFile(filePath2);
            imagektp.setImageBitmap(selectedImage2);
            Log.d("UpdateActivity", "Onactivityresultsetimagektp" +filePath2);
        }
        if (requestCode == CAMERA3 && resultCode == Activity.RESULT_OK) {
            filePath3 = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Picasso.get()
                    .load(filePath3).
                    resize(400, 400)
                    .into(imagebukti);
            selectedImage3 = BitmapFactory.decodeFile(filePath3);
            imagebukti.setImageBitmap(selectedImage3);
            Log.d("UpdateActivity", "Onactivityresultsetimagebukti" +filePath3);
        }
    }

    private void onGPS() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
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
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(UpdateActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(UpdateActivity.this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);

        } else {
            Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (locationGPS != null) {
                double lat = locationGPS.getLatitude();
                double longi = locationGPS.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                txtlat.setText(latitude + "," + longitude);
            } else if (locationPassive != null) {
                double lat = locationPassive.getLatitude();
                double longi = locationPassive.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                txtlat.setText(latitude + "," + longitude);
            } else if (locationNetwork != null) {
                double lat = locationNetwork.getLatitude();
                double longi = locationNetwork.getLongitude();

                latitude = String.valueOf(lat);
                longitude = String.valueOf(longi);

                txtlat.setText(latitude + "," + longitude);
            } else {
                Toast.makeText(this, "Cant get your location", Toast.LENGTH_SHORT).show();
            }
            //try run
        }
    }

    private void initSpinnerPickUp() {
        final APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        final Call<ModelPickup2> data = apiInterface.getPickUp("B3ndhilDik4");
        data.enqueue(new Callback<ModelPickup2>() {
            @Override
            public void onResponse(Call<ModelPickup2> call, Response<ModelPickup2> response) {
                if (response.isSuccessful()) {
                    ModelPickup2 listdata = response.body();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < listdata.getData().size(); i++) {
                        String code = listdata.getData().get(i).getKodePickUp();
                        String status = listdata.getData().get(i).getStatus();
                        listSpinner.add(code + "  " + status);
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
            public void onFailure(Call<ModelPickup2> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerGagalPickUp() {
        final APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        final Call<GetGagalPickUp> data = apiInterface.getGagalPickUp("B3ndhilDik4");
        data.enqueue(new Callback<GetGagalPickUp>() {
            @Override
            public void onResponse(Call<GetGagalPickUp> call, Response<GetGagalPickUp> response) {
                if (response.isSuccessful()) {
                    GetGagalPickUp listdata = response.body();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < listdata.getData().size(); i++) {
                        String code = listdata.getData().get(i).getKodeReasonGagal();
                        String status = listdata.getData().get(i).getNamaReasonGagal();
                        listSpinner.add(code + "  " + status);
                    }
                    listSpinner.add(0, "- Pilih -");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateActivity.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerreasongagal.setAdapter(adapter);
                } else {
                    Toast.makeText(UpdateActivity.this, "Gagal mengambil data shift", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetGagalPickUp> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initSpinnerCancelPickUp() {
        final APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        final Call<GetCancelPickUp> data = apiInterface.getCancelPickUp("B3ndhilDik4");
        data.enqueue(new Callback<GetCancelPickUp>() {
            @Override
            public void onResponse(Call<GetCancelPickUp> call, Response<GetCancelPickUp> response) {
                if (response.isSuccessful()) {
                    GetCancelPickUp listdata = response.body();
                    List<String> listSpinner = new ArrayList<String>();
                    for (int i = 0; i < listdata.getData().size(); i++) {
                        String code = listdata.getData().get(i).getKodeResonCancel();
                        String status = listdata.getData().get(i).getNamaResonCancel();
                        listSpinner.add(code + "  " + status);
                    }
                    listSpinner.add(0, "- Pilih -");
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(UpdateActivity.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerreasoncancel.setAdapter(adapter);
                } else {
                    Toast.makeText(UpdateActivity.this, "Gagal mengambil data shift", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<GetCancelPickUp> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Koneksi internet bermasalah", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude2 = location.getLatitude();
        longitude2 = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("Latitude", "enable");
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("Latitude", "status");
    }

    //send post data with image
    private void sendDataUpload() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
       String now = formatter.format(new Date());

        MultipartBody.Part imagenpwp = null;
        MultipartBody.Part imagektp = null;
        MultipartBody.Part imagebukti = null;

        if (filePath != null) {
            File foto = new File(filePath);
            RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), foto);
            imagenpwp = MultipartBody.Part.createFormData("image_name_pemilik", foto.getName() + ".jpg", requestFile1);
        }

        if (filePath2 != null) {
            File foto2 = new File(filePath2);
            RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), foto2);
            imagektp = MultipartBody.Part.createFormData("image_name_ktp", foto2.getName() + ".jpg", requestFile2);
        }

        if (filePath3 != null) {
            File foto3 = new File(filePath3);
            RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), foto3);
            imagebukti = MultipartBody.Part.createFormData("image_name_bukti", foto3.getName() + ".jpg", requestFile3);
        }

        String lspinner = spinnerpickup.getSelectedItem().toString();
        String[] shift = lspinner.split(" ");
        String spinner = shift[0];

        String spinnerr = spinnerreasoncancel.getSelectedItem().toString();
        String[] shiftr = spinnerr.split(" ");
        String spinnerrr = shiftr[0];

        String spinnerg = spinnerreasongagal.getSelectedItem().toString();
        String[] shiftg = spinnerg.split(" ");
        String spinnergg = shiftg[0];

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
                toRequestBody(AppUtil.replaceNull(spinnergg)),
                toRequestBody(AppUtil.replaceNull(spinnerrr)),
                toRequestBody(AppUtil.replaceNull(now1)),
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
                toRequestBody(AppUtil.replaceNull(txtlat.getText().toString())),
                toRequestBody(AppUtil.replaceNull(datum.getTanggalDistribusiAsm())),
                toRequestBody(AppUtil.replaceNull(datum.getTanggalDistribusi())),
                toRequestBody(AppUtil.replaceNull(datum.getNoteIinprocessed())),
                toRequestBody(AppUtil.replaceNull(datum.getProduct())),
                toRequestBody(AppUtil.replaceNull(txtdate.getText().toString())),
                toRequestBody(AppUtil.replaceNull(datum.getSourceCode())),
                toRequestBody(AppUtil.replaceNull(datum.getCreatedDate())),
                toRequestBody(AppUtil.replaceNull(datum.getStatusValidasi())),
                toRequestBody(AppUtil.replaceNull(datum.getValidasiBy())),
                toRequestBody(AppUtil.replaceNull(datum.getStatusValidasiAdmin())),
                toRequestBody(AppUtil.replaceNull(datum.getCreatedBy())),
                toRequestBody(AppUtil.replaceNull(datum.getStatusClose())),
                toRequestBody(AppUtil.replaceNull(datum.getCloseDate())),
                toRequestBody(AppUtil.replaceNull(datum.getClosedBy())),
                toRequestBody(AppUtil.replaceNull(datum.getBranch())),
                toRequestBody(AppUtil.replaceNull(now)),
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
                    Toast.makeText(UpdateActivity.this, responServer.getMessage(), Toast.LENGTH_LONG).show();
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
        String lspinner = spinnerpickup.getSelectedItem().toString();
        String[] shift = lspinner.split(" ");
        String spinner = shift[0];

        String idform = datum.getIdForm();
        String id_detail = datum.getId();
        String work = "worklist";
        String df = "";
        String dt = "";
        String mc = GNIK;
        String status = spinner;
        String ket = "";
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
        Call<PostActivity> postAdd = apiInterface.sendDataActivity(judul, iddetail, worklist, disfrom, disto, ms, sts, keterangan, waktu, cby);

        postAdd.enqueue(new Callback<PostActivity>() {
            @Override
            public void onResponse(Call<PostActivity> call, Response<PostActivity> response) {
                PostActivity responServer = response.body();
                if (responServer != null) {
                    Toast.makeText(UpdateActivity.this, responServer.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<PostActivity> call, Throwable t) {
                Toast.makeText(UpdateActivity.this, "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }
}
