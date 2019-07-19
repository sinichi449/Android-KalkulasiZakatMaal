package com.sinichi.kalkulasizakatmaal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.sinichi.kalkulasizakatmaal.adapter.MenuPilihanAdapter;
import com.sinichi.kalkulasizakatmaal.model.MenuPilihanModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MenuPilihanAdapter.OnMenuClickListener{

    private RecyclerView rv;
    private MenuPilihanAdapter menuPilihanAdapter;
    private ArrayList<MenuPilihanModel> pilihanModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tampilkanData();
        rv = findViewById(R.id.rvMenu);

        menuPilihanAdapter = new MenuPilihanAdapter(pilihanModels);
        menuPilihanAdapter.setListener(this);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(menuPilihanAdapter);

    }

    public void tampilkanData() {
        pilihanModels = new ArrayList<>();
        pilihanModels.add(new MenuPilihanModel(R.drawable.ic_local_atm_black_24dp, "Uang","Tiap-tiap uang yang dikumpulkan apabila sudah mencapai nisab", MenuKalkulasiUang.class));
        pilihanModels.add(new MenuPilihanModel(R.drawable.ic_donut_small_black_24dp,"Emas", "Kekayaan berbentuk emas", MenuKalkulasiEmas.class));
        pilihanModels.add(new MenuPilihanModel(R.drawable.ic_monetization_on_black_24dp,"Perak", "Kekayaan berbentuk perak", MenuKalkulasiPerak.class));
        pilihanModels.add(new MenuPilihanModel(R.drawable.ic_pets_black_24dp,"Binatang Ternak", "Diutamakan digembalakan di padang rumput", MenuKalkulasiBinatangTernak.class));
        pilihanModels.add(new MenuPilihanModel(R.drawable.ic_spa_black_24dp,"Hasil Pertanian", "Ada perbedaan untuk jenis irigasi yang digunakannya", MenuKalkulasiHasilPertanian.class));
        pilihanModels.add(new MenuPilihanModel(R.drawable.ic_business_center_black_24dp,"Harta Karun", "Harta temuan seperti harta karunpun wajib dizakatkan", KalkulasiZakatHartaKarun.class));

    }

    public void onClick(MenuPilihanModel menu) {
       Intent intent = new Intent(this, menu.getKlass());
       startActivity(intent);

    }
}