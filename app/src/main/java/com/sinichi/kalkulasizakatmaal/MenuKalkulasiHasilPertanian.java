package com.sinichi.kalkulasizakatmaal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MenuKalkulasiHasilPertanian extends AppCompatActivity {

    EditText txtBerat;
    Button btnHitung;
    RadioGroup radioGroup;
    RadioButton pakePenyiram, pakeAirHujan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kalkulasi_hasil_pertanian);

        txtBerat = findViewById(R.id.input_berat);
        btnHitung = findViewById(R.id.btn_hitung_pertanian);
        radioGroup = findViewById(R.id.rg_irigasi);
        pakePenyiram = findViewById(R.id.rb_penyiram_tanaman);
        pakeAirHujan = findViewById(R.id.rb_air_hujan);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtBerat.getText().toString())) {
                    txtBerat.setError("Harap isi hasil pertanian Anda!");
                } else if (radioGroup.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MenuKalkulasiHasilPertanian.this, "Harap pilih jenis irigasi yg digunakan!", Toast.LENGTH_LONG).show();
                } else if (Integer.parseInt(txtBerat.getText().toString()) <= 899) {
                    AlertDialog.Builder builderKurangNisab = new AlertDialog.Builder(MenuKalkulasiHasilPertanian.this);
                    builderKurangNisab.setTitle("Anda belum mencapai nisab");
                    builderKurangNisab.setMessage("Untuk mengeluarkan Zakat Maal Pertanian minimal adalah 900 kg hasil pertanian").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = builderKurangNisab.create();
                    alertDialog.show();
                } else if (pakePenyiram.isChecked()) {
                    float hasilPertanian = Float.parseFloat(txtBerat.getText().toString());
                    float zakatYgDikeluarkan = 0.05f * hasilPertanian;
                    if (zakatYgDikeluarkan == Math.round(zakatYgDikeluarkan)) {
                        int zakatYgDikeluarkanInt = Math.round(zakatYgDikeluarkan);
                        String zakatYgDikeluarkanStr = String.valueOf(zakatYgDikeluarkanInt);
                        tampilHasil(zakatYgDikeluarkanStr);
                    } else {
                        String zakatYgDikeluarkanStr = String.valueOf(zakatYgDikeluarkan);
                        tampilHasil(zakatYgDikeluarkanStr);
                    }

                } else if (pakeAirHujan.isChecked()) {
                    float hasilPertanian = Float.parseFloat(txtBerat.getText().toString());
                    float zakatYgDikeluarkan = 0.1f * hasilPertanian;
                    if (zakatYgDikeluarkan == Math.round(zakatYgDikeluarkan)) {
                        int zakatYgDikeluarkanInt = Math.round(zakatYgDikeluarkan);
                        String zakatYgDikeluarkanStr = String.valueOf(zakatYgDikeluarkanInt);
                        tampilHasil(zakatYgDikeluarkanStr);
                    } else {
                        String zakatYgDikeluarkanStr = String.valueOf(zakatYgDikeluarkan);
                        tampilHasil(zakatYgDikeluarkanStr);
                    }
                }


            }
        });
    }

    private void tampilHasil(String zakatYgDikeluarkan) {
        AlertDialog.Builder builderTampilHasil = new AlertDialog.Builder(MenuKalkulasiHasilPertanian.this);
        builderTampilHasil.setTitle("Hasil Perhitungan");
        builderTampilHasil.setMessage("Zakat yang harus Anda keluarkan adalah : \n\n" + zakatYgDikeluarkan + " kg.").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builderTampilHasil.create();
        alertDialog.show();
    }
}
