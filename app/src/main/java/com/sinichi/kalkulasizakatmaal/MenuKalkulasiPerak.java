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

public class MenuKalkulasiPerak extends AppCompatActivity {

    // Deklarasi
    EditText txtHargaPerak, txtTotalPerak;
    RadioGroup rg;
    Button btnHitung;
    RadioButton rbHaulYa, rbHaulTidak;
    float hargaPerak, totalPerak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kalkulasi_perak);

        txtHargaPerak = findViewById(R.id.input_total_harta_karun);
        txtTotalPerak = findViewById(R.id.input_total_perak);
        btnHitung = findViewById(R.id.btn_hitung_harta_karun);
        rbHaulYa = findViewById(R.id.rd_perak_setahun_ya);
        rbHaulTidak = findViewById(R.id.rd_perak_setahun_tidak);
        rg = findViewById(R.id.radioGroup);

        // Event ketika button Hitung di klik
        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Jika form Harga Perak kosong
                if (TextUtils.isEmpty(txtHargaPerak.getText().toString())) {
                    txtHargaPerak.setError("Harap isi harga perak sekarang!");
                }

                // Jika form total perak (gram) kosong
                else if (TextUtils.isEmpty(txtTotalPerak.getText().toString())) {
                    txtTotalPerak.setError("Harap isi total perak yang Anda miliki!");
                }

                // Jika pilihan haul tidak ada yg dicentang
                else if (rg.getCheckedRadioButtonId() == -1) {
                    Toast.makeText(MenuKalkulasiPerak.this, "Harap pilih apakah perak Anda telah mencapai satu tahun (haul)!",Toast.LENGTH_LONG).show();
                }

                // Jika pilihan belum haul dicentang
                else if (rbHaulTidak.isChecked()) {
                    AlertDialog.Builder builderTidakHaul = new AlertDialog.Builder(MenuKalkulasiPerak.this);
                    builderTidakHaul.setTitle("Anda tidak wajib mengeluarkan zakat maal");
                    builderTidakHaul.setMessage("Ini dikarenakan harta Anda masih belum mencapai haul (satu tahun) kepemilikan").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = builderTidakHaul.create();
                    alertDialog.show();
                }

                // Ketentuan nisab perak = 595 gram
                else if (Integer.parseInt(txtTotalPerak.getText().toString()) <= 595) {
                    AlertDialog.Builder builderKurangNisab = new AlertDialog.Builder(MenuKalkulasiPerak.this);
                    builderKurangNisab.setTitle("Anda belum mencapai nisab");
                    builderKurangNisab.setMessage("Untuk mengeluarkan Zakat Maal Perak minimal adalah 595 gram perak.").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = builderKurangNisab.create();
                    alertDialog.show();
                } else {
                    // Ambil data dari EditText
                    String getHargaPerak = txtHargaPerak.getText().toString();
                    String getTotalPerak = txtTotalPerak.getText().toString();

                    // Convert ke float
                    hargaPerak = Float.parseFloat(getHargaPerak) * 1000; // dibagi seribu karena inputannya berupa angka biasa yg diembel-embeli "ribu"
                    totalPerak = Float.parseFloat(getTotalPerak);

                    // Rumus zakat perak
                    float zakatYgDikeluarkan = 0.025f * totalPerak;
                    float diRupiahkan = zakatYgDikeluarkan * hargaPerak / 1000000; // dibagi satu juta karena diembel-embeli "juta"

                    final String diRupiahkanStr = Float.toString(diRupiahkan);

                    // Apabila nilai gram perak berbentuk bilangan bulat
                    if (Math.round(zakatYgDikeluarkan) == zakatYgDikeluarkan) {
                        // Membulatkan bilangan
                        int intZakatYgDikeluarkanStr = Math.round(zakatYgDikeluarkan);
                        final String zakatYgDikeluarkanStr = Integer.toString(intZakatYgDikeluarkanStr);
                        AlertDialog.Builder tampilHasil = new AlertDialog.Builder(MenuKalkulasiPerak.this);
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
                        AlertDialog.Builder tampilHasil = new AlertDialog.Builder(MenuKalkulasiPerak.this);
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
