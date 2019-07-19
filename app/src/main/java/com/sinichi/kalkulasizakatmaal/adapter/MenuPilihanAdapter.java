package com.sinichi.kalkulasizakatmaal.adapter;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sinichi.kalkulasizakatmaal.R;
import com.sinichi.kalkulasizakatmaal.model.MenuPilihanModel;

import java.util.ArrayList;

public class MenuPilihanAdapter extends RecyclerView.Adapter<MenuPilihanAdapter.MyViewHolder> {

    private ArrayList<MenuPilihanModel> dataList;

    public MenuPilihanAdapter(ArrayList<MenuPilihanModel> dataList) {
        this.dataList = dataList;
    }

    private OnMenuClickListener listener;

    public interface OnMenuClickListener {
        public void onClick(MenuPilihanModel menu);
    }

    public void setListener (OnMenuClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuPilihanAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuPilihanView = LayoutInflater.from(parent.getContext()).inflate(R.layout.jenis_zakat,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(menuPilihanView);
        return  myViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MenuPilihanModel menuPilihanModel = dataList.get(position);
        holder.txtNama.setText(menuPilihanModel.getNama());
        holder.txtDeskripsi.setText(menuPilihanModel.getDeskripsi());
        holder.imageView.setImageResource(menuPilihanModel.getImage());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtNama, txtDeskripsi;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNama = itemView.findViewById(R.id.txtNamaZakat);
            txtDeskripsi = itemView.findViewById(R.id.txtKeterangan);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MenuPilihanModel menu = dataList.get(getAdapterPosition());
                    listener.onClick(menu);
                }
            });
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
