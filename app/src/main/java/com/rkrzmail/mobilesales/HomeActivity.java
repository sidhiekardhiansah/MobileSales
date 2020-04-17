package com.rkrzmail.mobilesales;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import com.rkrzmail.mobilesales.APIService.APIClient2;
import com.rkrzmail.mobilesales.APIService.APIInterfacesRest;
import com.rkrzmail.mobilesales.Adapter.UploadAdapter;
import com.rkrzmail.mobilesales.model.dataupload.DataUpload;
import com.rkrzmail.mobilesales.model.dataupload.Datum;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity  {
    RecyclerView rcv1;
    List<Datum> itemlist;
    UploadAdapter adapter;
    SharedPreferences pref;
    Datum datum;
    SearchView searchView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rcv1= findViewById(R.id.rcv1);
        getUpload();
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
      //  mSwipeRefreshLayout.setOnRefreshListener(this);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
    }
    private void getUpload() {
        final APIInterfacesRest apiInterface = APIClient2.getClient().create(APIInterfacesRest.class);
        final Call<DataUpload> nikita = apiInterface.getUpload("sidik123");

        nikita.enqueue(new Callback<DataUpload>() {
            @Override
            public void onResponse(Call <DataUpload> call, Response<DataUpload> response) {
                DataUpload modelproduk =  response.body();
                if (response.body() != null) {

                    for (int i = 0; i < modelproduk.getData().size(); i++) {
                        //  itemlist2= modelproduk.getData().get(i).getUsername();
                        adapter = new UploadAdapter(modelproduk.getData());
                        rcv1.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                        rcv1.setItemAnimator(new DefaultItemAnimator());
                        rcv1.setAdapter(adapter);
                    }
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(HomeActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(HomeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call <DataUpload> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "Terjadi gangguan koneksi", Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem search = menu.findItem(R.id.search);
        searchView = (SearchView) search.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
               return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
