package com.sinichi.kalkulasizakatmaal;

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
import androidx.appcompat.app.AlertDialog;

public class MenuKalkulasiUang extends AppCompatActivity {


    // Deklarasi
    EditText txtHargaEmas,txtTotalUang;
    Button btnHitung;
    RadioGroup rg;
    RadioButton rbUangSetahunYa, rbUangSetahunTidak;
    float hargaEmas, totalUang;
    float floatNisab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kalkulasi_uang);

        txtHargaEmas = findViewById(R.id.input_harga_emas);
        txtTotalUang = findViewById(R.id.input_total_perak);
        rg = findViewById(R.id.radioGroup);
        rbUangSetahunYa = findViewById(R.id.rd_perak_setahun_ya);
        rbUangSetahunTidak = findViewById(R.id.rd_perak_setahun_tidak);
        btnHitung = findViewById(R.id.btn_hitung_harta_karun);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Jika form harga emas kosong
                if (TextUtils.isEmpty(txtHargaEmas.getText().toString())) {
                    txtHargaEmas.setError("Harap isi harga emas sekarang ini di pasaran!");
                }

                // Jika form total uang kosong
                else if (TextUtils.isEmpty(txtTotalUang.getText().toString())) {
                    txtTotalUang.setError("Harap isi total uang yang Anda miliki sekarang ini!");
                }

                // Jika pilihan haul tidak ada yang tercentang
                else if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MenuKalkulasiUang.this, "Harap pilih apakah harta Anda sudah mencapai satu tahun (haul)!",Toast.LENGTH_LONG).show();
                }

                // Jika masih belum mencapai haul
                else if (rbUangSetahunTidak.isChecked()) {
                    AlertDialog.Builder buildUangBlmHaul = new AlertDialog.Builder(MenuKalkulasiUang.this);
                    buildUangBlmHaul.setTitle("Anda tidak wajib mengeluarkan zakat maal");
                    buildUangBlmHaul.setMessage("Ini dikarenakan harta Anda masih belum mencapai satu tahun (haul)").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = buildUangBlmHaul.create();
                    alertDialog.show();
                }

                // Ketentuan nisab =
                // uang > harga emas murni 24 karat (85 gram)
                else if ((Integer.parseInt(txtTotalUang.getText().toString()) * 1000000) <= (Integer.parseInt(txtHargaEmas.getText().toString()) * 85 * 1000)) {
                    AlertDialog.Builder buildKurangNisab = new AlertDialog.Builder(MenuKalkulasiUang.this);
                    buildKurangNisab.setTitle("Anda belum mencapai nisab");
                    buildKurangNisab.setMessage("Untuk mengeluarkan Zakat Maal Uang minimal sejumlah Rp. " + ((Float.parseFloat(txtHargaEmas.getText().toString()) * 85) / 1000) + " juta.").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = buildKurangNisab.create();
                    alertDialog.show();
                } else {
                    // Ambil data EditText
                    String getTotalUang = txtTotalUang.getText().toString();

                    // Convert ke float
                    totalUang = (Float.parseFloat(getTotalUang) * 1000000) ;

                    // Rumus zakat
                    float zakatYgDikeluarkan = 0.025f * totalUang;
                    final String zakatYgDikeluarkanStr = Float.toString(zakatYgDikeluarkan/1000000);

                    AlertDialog.Builder tampilHasil = new AlertDialog.Builder(MenuKalkulasiUang.this);
                    tampilHasil.setTitle("Hasil Perhitungan");
                    tampilHasil.setMessage("Zakat yg harus Anda keluarkan sebanyak Rp." + zakatYgDikeluarkanStr + " juta").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = tampilHasil.create();
                    alertDialog.show();
                }
            }
        });
    }
}
