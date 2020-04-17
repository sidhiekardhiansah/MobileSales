package com.rkrzmail.mobilesales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.rkrzmail.mobilesales.APIService.APIClient2;
import com.rkrzmail.mobilesales.APIService.APIInterfacesRest;
import com.rkrzmail.mobilesales.model.tes.UpdateTes;
import com.rkrzmail.mobilesales.model.dataupload.Datum;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import in.mayanknagwanshi.imagepicker.ImageSelectActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.os.Environment.getExternalStoragePublicDirectory;

public class GambarActivity extends AppCompatActivity {
    Button btnktp, btnnpwp, btnbukti, btnkirim, btnubah;
    ImageView imagektp, imagenpwp, imagebukti;
    EditText txtid, txtalamat, txtkonversi;
    String sm, filePath, latitude, longitude, kode, mylocation2, filePath2, filePath3, pathToFile;
    double latitude3, longitude3;
    Datum datum;
    private Address myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);
        btnubah=findViewById(R.id.btnubah);
        txtalamat=findViewById(R.id.txtalamat);
        txtkonversi=findViewById(R.id.txtkonversi);
        btnktp=findViewById(R.id.btnktp);
        btnnpwp=findViewById(R.id.btnnpwp);
        btnbukti=findViewById(R.id.btnbukti);
        txtid= findViewById(R.id.txtid);
        imagenpwp=findViewById(R.id.imagenpwp);
        imagektp=findViewById(R.id.imagektp);
        imagebukti=findViewById(R.id.imagebukti);
        btnkirim=findViewById(R.id.btnkirim);
        btnkirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendTes();
                UpdateTes();

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

        btnubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String location= txtalamat.getText().toString();
                Geocoder geocoder = new Geocoder(GambarActivity.this);
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
        txtkonversi.setText(latitude3+","+longitude3);
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
            filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap  selectedImage2 = BitmapFactory.decodeFile(filePath);
            imagenpwp.setImageBitmap(selectedImage2);
        }
        if (requestCode == 1215 && resultCode == Activity.RESULT_OK) {
            filePath = data.getStringExtra(ImageSelectActivity.RESULT_FILE_PATH);
            Bitmap selectedImage3 = BitmapFactory.decodeFile(filePath);
            imagebukti.setImageBitmap(selectedImage3);
        }
    }

    private void UpdateTes() {
        String id= txtid.getText().toString();
        File foto = new File(filePath);
        RequestBody requestFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), foto);
        MultipartBody.Part image_name_pemilik = MultipartBody.Part.createFormData("image_name_pemilik", foto.getName() + ".jpg", requestFile1);

        File foto2 = new File(filePath2);
        RequestBody requestFile2 = RequestBody.create(MediaType.parse("multipart/form-data"), foto2);
        MultipartBody.Part image_name_ktp = MultipartBody.Part.createFormData("image_name_ktp", foto2.getName() + ".jpg", requestFile2);


        File foto3 = new File(filePath3);
        RequestBody requestFile3 = RequestBody.create(MediaType.parse("multipart/form-data"), foto3);
        MultipartBody.Part image_name_bukti = MultipartBody.Part.createFormData("image_name_bukti", foto3.getName() + ".jpg", requestFile3);

        RequestBody idd = RequestBody.create(MediaType.parse("multipart/form-data"), id);

        //MultipartBody requestBody = builder.build();
        APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        Call<UpdateTes> postAdd = apiInterface.UpdateTes(idd, image_name_pemilik, image_name_ktp, image_name_bukti
        );
        postAdd.enqueue(new Callback<UpdateTes>() {
            @Override
            public void onResponse(Call<UpdateTes> call, Response<UpdateTes> response) {
                UpdateTes responServer = response.body();
                if (responServer != null) {
                    Toast.makeText(GambarActivity.this,responServer.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<UpdateTes> call, Throwable t) {
                Toast.makeText(GambarActivity.this, "Maaf koneksi bermasalah", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

}
