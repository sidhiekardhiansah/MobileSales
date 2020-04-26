package com.rkrzmail.mobilesales.Adapter;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.rkrzmail.mobilesales.R;
import com.rkrzmail.mobilesales.UpdateActivity;
import com.rkrzmail.mobilesales.model.dataupload.DataUpload;
import com.rkrzmail.mobilesales.model.dataupload.Datum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
public class UploadAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {

    private List<Datum> dataItemList;
    private List<Datum> mFilteredList;
    //utk membedakan xml
    public UploadAdapter(List<Datum> dataItemList) {
        this.dataItemList = dataItemList;
        this.mFilteredList = new ArrayList<>(dataItemList);
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_upload, parent, false);
        Penampung penampung = new Penampung(view);
        return penampung;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Datum datum = dataItemList.get(position);
        ((Penampung)holder).txtnama.setText(dataItemList.get(position).getUsername());
        ((Penampung)holder).txtcase.setText(dataItemList.get(position).getNoCase());
        ((Penampung)holder).txtketerangan.setText((dataItemList.get(position).getRemark()));
        ((Penampung)holder).txtcis.setText((dataItemList.get(position).getCis()));
        ((Penampung)holder).tctcampaign.setText((dataItemList.get(position).getCampaign()));
        ((Penampung)holder).txtphone.setText((dataItemList.get(position).getPhone1()+" "+(dataItemList.get(position).getPhone2()+" "+(dataItemList.get(position).getPhone3()+" "+(dataItemList.get(position).getPhone4()+" "+(dataItemList.get(position).getPhone5()+" "+(dataItemList.get(position).getPhone6()+
                " "+(dataItemList.get(position).getPhone7()+" "+(dataItemList.get(position).getPhone8()+" "+(dataItemList.get(position).getPhone9()+" "+(dataItemList.get(position).getPhone10()+" "+(dataItemList.get(position).getPhone11()+" "+
                        (dataItemList.get(position).getPhone12()+" "+(dataItemList.get(position).getPhone13()+" "+(dataItemList.get(position).getPhone14()+" "+(dataItemList.get(position).getPhone15()))))))))))))))));
        ((Penampung)holder).txtpos.setText((dataItemList.get(position).getPostCode()));
        ((Penampung)holder).txtkota.setText((dataItemList.get(position).getCity())+" "+(dataItemList.get(position).getCity2()));
        ((Penampung)holder).txtalamat.setText((dataItemList.get(position).getAddress()));
        ((Penampung)holder).cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), UpdateActivity.class);
                intent.putExtra("id",(Parcelable) datum);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataItemList == null ? 0 : dataItemList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                List<Datum> filteredList = new ArrayList<>();
                if(charSequence.toString().isEmpty()){
                    filteredList.addAll(mFilteredList);
                } else {
                    for (Datum datum : mFilteredList){
                        if (datum.getUsername().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            filteredList.add(datum);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }


            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                dataItemList.clear();
                dataItemList.addAll((Collection<? extends Datum>) filterResults.values);
//                mFilteredList = (List<Datum>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


    static class Penampung extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView txtnama, txtcase, txtketerangan, txtcis, tctcampaign, txtphone, txtpos, txtkota, txtalamat, txtid, txtunikid,txtidform;
        public CardView cardView;
        public LinearLayout linearLayout;
        public Context context;
        SharedPreferences pref;
        Datum datum;

        public Penampung(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            cardView= (CardView) itemView.findViewById(R.id.cardview);
            context = itemView.getContext();
            txtnama = (TextView) itemView.findViewById(R.id.txtnama);
            txtcase = (TextView) itemView.findViewById(R.id.txtcase);
            txtketerangan = (TextView) itemView.findViewById(R.id.txtketerangan);
            txtcis = (TextView) itemView.findViewById(R.id.txtcis);
            tctcampaign = (TextView) itemView.findViewById(R.id.txtcampaign);
            txtphone  = (TextView) itemView.findViewById(R.id.txtphone);
            txtpos  = (TextView) itemView.findViewById(R.id.txtpos);
            txtkota  = (TextView) itemView.findViewById(R.id.txtkota);
            txtalamat  = (TextView) itemView.findViewById(R.id.txtalamat);
        }

        @Override
        public void onClick(View view) {
            Log.d("onclick", "onClick " + getLayoutPosition() + " ");
        }
    }

}

