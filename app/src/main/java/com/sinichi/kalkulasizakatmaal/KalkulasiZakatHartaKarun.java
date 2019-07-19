package com.sinichi.kalkulasizakatmaal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class KalkulasiZakatHartaKarun extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtTotalUang;
    Button btnHitung;
    Spinner jenisPecahanUang;
    float jenisBilangan = 1;
    String perSepuluh = "";
    float totalUang;
    String zakatYgDikeluarkanStr = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulasi_zakat_harta_karun);

        txtTotalUang = findViewById(R.id.input_total_harta_karun);
        btnHitung = findViewById(R.id.btn_hitung_harta_karun);
        jenisPecahanUang = findViewById(R.id.spinner);

        String[] pecahanUang = new String[]{"ribu","juta","milyar","trilyun"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,pecahanUang);
        jenisPecahanUang.setAdapter(adapter);
        jenisPecahanUang.setOnItemSelectedListener(this);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtTotalUang.getText().toString())) {
                    txtTotalUang.setError("Harap isikan total uang yg Anda dapat dari harta karun tersebut!");
                } else {
                    String getTotalUang = txtTotalUang.getText().toString();

                    totalUang = Float.parseFloat(getTotalUang) * jenisBilangan;

                    float zakatYgDikeluarkan = 0.20f * totalUang;

                    final String zakatYgDikeluarkanStr = Float.toString(zakatYgDikeluarkan);
                    tampilkanData(zakatYgDikeluarkanStr);

                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            // inputan ribuan
            case 0:
                jenisBilangan = 1000;
                perSepuluh = "ribu";
                break;

            // inputan jutaan
            case 1:
                jenisBilangan = 1000000;
                perSepuluh = "juta";
                break;

            // inputan milyar
            case 2:
                jenisBilangan = 1000000000;
                perSepuluh = "milyar";
                break;

            // inputan trilyun
            case 3:
                jenisBilangan = 1000000000;
                perSepuluh = "trilyun";
                break;
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void tampilkanData(String zakatYgDikeluarkan) {
        // Convert ke float agar value bisa dihitung
        float zakatYgDikeluarkanFloat = Float.parseFloat(zakatYgDikeluarkan);
        zakatYgDikeluarkanFloat /= jenisBilangan;

        if (zakatYgDikeluarkanFloat == Math.round(zakatYgDikeluarkanFloat)) {
            int zakatYgDikeluarkanInt = Math.round(zakatYgDikeluarkanFloat);
            zakatYgDikeluarkanStr = String.valueOf(zakatYgDikeluarkanInt);

        } else {
            zakatYgDikeluarkanStr = String.valueOf(zakatYgDikeluarkanFloat);
        }
        AlertDialog.Builder tampilHasil = new AlertDialog.Builder(KalkulasiZakatHartaKarun.this);
        tampilHasil.setTitle("Hasil Perhitungan");
        tampilHasil.setMessage("Zakat yg harus Anda keluarkan sebanyak Rp. " + zakatYgDikeluarkanStr + " " + perSepuluh + ".").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = tampilHasil.create();
        alertDialog.show();
    }

}
