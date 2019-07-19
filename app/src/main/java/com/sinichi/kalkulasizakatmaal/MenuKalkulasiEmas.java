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

public class MenuKalkulasiEmas extends AppCompatActivity {

    // Deklarasi
    EditText txtHargaEmas, txtTotalEmas;
    RadioGroup rg;
    Button btnHitung;
    RadioButton rbHaulYa, rbHaulTidak;
    float hargaEmas, totalEmas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kalkulasi_emas);

        txtHargaEmas = findViewById(R.id.input_harga_emas);
        txtTotalEmas = findViewById(R.id.input_total_perak);
        btnHitung = findViewById(R.id.btn_hitung_harta_karun);
        rbHaulYa = findViewById(R.id.rd_perak_setahun_ya);
        rbHaulTidak = findViewById(R.id.rd_perak_setahun_tidak);
        rg = findViewById(R.id.radioGroup);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Jika form harga emas kosong
                if (TextUtils.isEmpty(txtHargaEmas.getText().toString())) {
                    txtHargaEmas.setError("Harap isi harga emas sekarang!");
                }

                // Jika form jumlah emas kosong
                else if (TextUtils.isEmpty(txtTotalEmas.getText().toString())) {
                    txtTotalEmas.setError("Harap isi total emas yang Anda miliki");
                }


                // Jika haul tidak ada yg dicentang
                else if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MenuKalkulasiEmas.this,"Harap pilih apakah harta Anda sudah mencapai satu tahun (haul)!",Toast.LENGTH_LONG).show();
                }

                // Jika belum mencapai haul
                else if (rbHaulTidak.isChecked()) {
                    AlertDialog.Builder buildTidakHaul = new AlertDialog.Builder(MenuKalkulasiEmas.this);
                    buildTidakHaul.setTitle("Anda tidak wajib mengeluarkan zakat maal");
                    buildTidakHaul.setMessage("Ini dikarenakan harta Anda masih belum mencapai satu tahun (haul)").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = buildTidakHaul.create();
                    alertDialog.show();

                }

                // Ketentuan nisab emas minimal 85 gram
                else if (Integer.parseInt(txtTotalEmas.getText().toString()) <= 84) {
                    AlertDialog.Builder buildKurangNisab = new AlertDialog.Builder(MenuKalkulasiEmas.this);
                    buildKurangNisab.setTitle("Anda belum mencapai nisab");
                    buildKurangNisab.setMessage("Untuk mengeluarkan Zakat Maal Emas minimal adalah 85 gram emas murni (24 karat).").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = buildKurangNisab.create();
                    alertDialog.show();
                } else {
                    // Ambil data dari EditText
                    String getHargaEmas = txtHargaEmas.getText().toString();
                    String getTotalEmas = txtTotalEmas.getText().toString();

                    // Convert ke float supaya bisa dihitung secara matematis
                    hargaEmas = Float.parseFloat(getHargaEmas) * 1000;
                    totalEmas = Float.parseFloat(getTotalEmas);

                    // Rumus zakat
                    float zakatYgDikeluarkan = 0.025f * totalEmas;
                    float diRupiahkan = zakatYgDikeluarkan * hargaEmas / 1000000;
//                    final String zakatYgDikeluarkanStr = Float.toString(zakatYgDikeluarkan);
                    final String diRupiahkanStr = Float.toString(diRupiahkan);

                    // Jika hasil zakatYgDikeluarkan berupa bilangan bulat
                    if (Math.round(zakatYgDikeluarkan) == zakatYgDikeluarkan) {
                        int intZakatYgDikeluarkan = Math.round(zakatYgDikeluarkan);
                        final String zakatYgDikeluarkanStr = Integer.toString(intZakatYgDikeluarkan);
                        AlertDialog.Builder tampilHasil = new AlertDialog.Builder(MenuKalkulasiEmas.this);
                        tampilHasil.setTitle("Hasil Perhitungan");
                        tampilHasil.setMessage("Zakat yang harus Anda keluarkan sebanyak " + zakatYgDikeluarkanStr + " gram\nAtau sebesar Rp. " + diRupiahkanStr + " juta").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog alertDialog = tampilHasil.create();
                        alertDialog.show();
                    } else {
                        final String zakatYgDikeluarkanStr = Float.toString(zakatYgDikeluarkan);
                        AlertDialog.Builder tampilHasil = new AlertDialog.Builder(MenuKalkulasiEmas.this);
                        tampilHasil.setTitle("Hasil Perhitungan");
                        tampilHasil.setMessage("Zakat yang harus Anda keluarkan sebanyak " + zakatYgDikeluarkanStr + " gram\nAtau sebesar Rp. " + diRupiahkanStr + " juta").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        AlertDialog alertDialog = tampilHasil.create();
                        alertDialog.show();
                    }


                }
            }
        });

    }
}
