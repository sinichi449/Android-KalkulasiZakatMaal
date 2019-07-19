package com.sinichi.kalkulasizakatmaal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MenuKalkulasiBinatangTernak extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText txtJumlahBinatang;
    Button btnHitung;
    Spinner binatang;
    int nisab = 0;
    String nama_binatang = "";
    String jumlahYgDizakatkan = "";
    String selectedItem;
    TextView keterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_kalkulasi_binatang_ternak);

        txtJumlahBinatang = findViewById(R.id.input_jumlah_binatang);
        btnHitung = findViewById(R.id.btn_hitung_zakat_binatang);
        binatang = findViewById(R.id.spinner_binatang);
        keterangan = findViewById(R.id.keterangan);
        keterangan.setPaintFlags(keterangan.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        String[] jenisBinatang = new String[]{"Onta","Sapi", "Kambing/Domba"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,jenisBinatang);
        binatang.setAdapter(adapter);
        binatang.setOnItemSelectedListener(this);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(txtJumlahBinatang.getText().toString())) {
                    txtJumlahBinatang.setError("Harap isi jumlah binatang yang Anda miliki!");
                } else if (Integer.parseInt(txtJumlahBinatang.getText().toString()) <= (nisab - 1)) {
                    AlertDialog.Builder builderKurangNisab = new AlertDialog.Builder(MenuKalkulasiBinatangTernak.this);
                    builderKurangNisab.setTitle("Anda belum mencapai nisab");
                    builderKurangNisab.setMessage("Untuk mengeluarkan Zakat Maal " + nama_binatang + " minimal adalah " + String.valueOf(nisab) + " ekor").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });

                    AlertDialog alertDialog = builderKurangNisab.create();
                    alertDialog.show();

                } else {
                    switch (selectedItem) {
                        case "Sapi":
                            int jumlahSapi = Integer.parseInt(txtJumlahBinatang.getText().toString());
                            if (jumlahSapi <= 40 &&
                                    jumlahSapi >= 29) {
                                jumlahYgDizakatkan = "1 ekor sapi jantan/betina tabi'";
                            } else if (jumlahSapi <= 60 &&
                                    jumlahSapi >= 39) {
                                jumlahYgDizakatkan = "1 ekor sapi jantan/betina musinnah";
                            } else if (jumlahSapi >= 59 &&
                                    jumlahSapi <= 70) {
                                jumlahYgDizakatkan = "2 ekor sapi jantan/betina tabi'";
                            } else if (jumlahSapi >= 69 &&
                                    jumlahSapi <= 80) {
                                jumlahYgDizakatkan = "1 ekor sapi musinnah dan 1 ekor tabi'";
                            } else if (jumlahSapi >= 79 &&
                                    jumlahSapi <= 90) {
                                jumlahYgDizakatkan = "2 ekor sapi musinnah";
                            } else if (jumlahSapi >= 89 &&
                                    jumlahSapi <= 100) {
                                jumlahYgDizakatkan = "3 ekor tabi'";
                            } else if (jumlahSapi >= 99 &&
                                    jumlahSapi <= 108) {
                                jumlahYgDizakatkan = "2 ekor tabi' dan 1 ekor musinnah";
                            } else if (jumlahSapi >= 109 &&
                                    jumlahSapi <= 120) {
                                jumlahYgDizakatkan = "2 ekor musinnah dan 1 ekor tabi'";
                            } else if (jumlahSapi >= 119 &&
                                    jumlahSapi <= 130) {
                                jumlahYgDizakatkan = "3 ekor musinnah atau 4 ekor tabi'";
                            } else if (jumlahSapi >= 130) {

                                txtJumlahBinatang.setError("Untuk versi sekarang maksimal sebanyak 130 ekor sapi");
                            }

                            tampilAlertDialog(jumlahYgDizakatkan);
                            break;
                        case "Kambing/Domba":
                            int jumlahKambing = Integer.parseInt(txtJumlahBinatang.getText().toString());
                            if (jumlahKambing >= 39 && jumlahKambing <= 120) {
                                jumlahYgDizakatkan = "1 ekor kambing (2th) atau domba (1th)";
                            } else if (jumlahKambing >= 120 && jumlahKambing <= 200) {
                                jumlahYgDizakatkan = "2 ekor kambing (2th) atau domba (1th)";
                            } else if (jumlahKambing >= 200 && jumlahKambing <= 399) {
                                jumlahYgDizakatkan = "3 ekor kambing (2th) atau domba (1th)";
                            } else if (jumlahKambing >= 399 && jumlahKambing <= 499) {
                                jumlahYgDizakatkan = "4 kambing (2th) atau domba (1th)";
                            } else if (jumlahKambing >= 499) { // lebih dari 500
                                // jika hewan lebih dari 1000
                                if (jumlahKambing >= 1000 && jumlahKambing <= 9999) {
                                    int input = Integer.parseInt(txtJumlahBinatang.getText().toString()); // misal 6367
                                    String convertToString = String.valueOf(input);// menjadi string 6367
                                    String ribuan = convertToString.substring(0, 2); // menjadi string 63
                                    int ribuanInt = Integer.parseInt(ribuan); // menjadi int 63;
                                    String ygDizakatkanStr = String.valueOf(ribuanInt); // menjadi string 63
                                    jumlahYgDizakatkan = ygDizakatkanStr + " ekor kambing (2th) atau domba (1th)"; // 63 ekor kambing (2th) atau domba (1th)

                                    // jika hewan lebih dari 10 ribu
                                } else if (jumlahKambing >= 10000 && jumlahKambing <= 99999) {
                                    int input = Integer.parseInt(txtJumlahBinatang.getText().toString()); // misal 10976
                                    String convertToString = String.valueOf(input); // string 10976
                                    String puluhRibuan = convertToString.substring(0, 3); // string 109
                                    int puluhRibuanInt = Integer.parseInt(puluhRibuan); // int 109
                                    String ygDizakatkanStr = String.valueOf(puluhRibuanInt); // string 109
                                    jumlahYgDizakatkan = ygDizakatkanStr + " ekor kambing (2th) atau domba (1th)"; // 109 ekor kambing (2th) atau domba (1th)

                                    // jika hewan lebih dari 100 ribu
                                } else if (jumlahKambing >= 100000) {
                                    txtJumlahBinatang.setError("Maksimum jumlah hewan adalah 99 ribu");

                                    // jika hewan kurang dari 1000, ratusan
                                } else {
                                    int input = Integer.parseInt(txtJumlahBinatang.getText().toString()); // misal 789
                                    String convertToString = String.valueOf(input); // string 789
                                    String ratusan = convertToString.substring(0, 1); // String 7
                                    jumlahYgDizakatkan = ratusan + " ekor kambing (2th) atau domba (1th)"; // 7 ekor kambing (2th) atau domba (1th)
                                }
                            }

                            tampilAlertDialog(jumlahYgDizakatkan);
                            break;
                        case "Onta":
                            int jumlahOnta = Integer.parseInt(txtJumlahBinatang.getText().toString());
                            if (jumlahOnta >= 4 && jumlahOnta <= 10) {
                                jumlahYgDizakatkan = "1 ekor kambing umur 2 tahun, atau 1 ekor domba umur 1 tahun";
                            } else if (jumlahOnta >= 10 && jumlahOnta <= 15) {
                                jumlahYgDizakatkan = "2 ekor kambing umur 2 tahun, atau 2 ekor domba umur 1 tahun";
                            } else if (jumlahOnta >= 15 && jumlahOnta <= 20) {
                                jumlahYgDizakatkan = "3 ekor kambing umur 2 tahun, atau 4 ekor domba umur 1 tahun";
                            } else if (jumlahOnta >= 20 && jumlahOnta <= 25) {
                                jumlahYgDizakatkan = "4 ekor kambing umur 2 tahun, atau 4 ekor domba umur 1 tahun";
                            } else if (jumlahOnta >= 25 && jumlahOnta <= 36) {
                                jumlahYgDizakatkan = "1 ekor onta betina umur 1 tahun";
                            } else if (jumlahOnta >= 36 && jumlahOnta <= 45) {
                                jumlahYgDizakatkan = "1 ekor onta betina umur 2 tahun";
                            } else if (jumlahOnta >= 45 && jumlahOnta <= 60) {
                                jumlahYgDizakatkan = "1 ekor onta betina umur 3 tahun";
                            } else if (jumlahOnta >= 60 && jumlahOnta <= 75) {
                                jumlahYgDizakatkan = "1 ekor onta betina umur 4 tahun";
                            } else if (jumlahOnta >= 75 && jumlahOnta <= 90) {
                                jumlahYgDizakatkan = "2 ekor onta betina umur 2 tahun";
                            } else if (jumlahOnta >= 90 && jumlahOnta <= 120) {
                                jumlahYgDizakatkan = "2 ekor onta betina umur 3 tahun";
                            } else if (jumlahOnta == 121) {
                                jumlahYgDizakatkan = "3 ekor onta betina umur 2 tahun";
                            } else {
                                txtJumlahBinatang.setError("Untuk versi sekarang maksimal sebanyak 121 ekor onta");
                            }

                            tampilAlertDialog(jumlahYgDizakatkan);
                            break;
                    }
                }
            }

        });

        keterangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builderKeterangan = new AlertDialog.Builder(MenuKalkulasiBinatangTernak.this);
                builderKeterangan.setTitle("Keterangan");
                builderKeterangan.setMessage("Tabi  : sapi jantan yang berusia satu tahun (masuk tahun ke-2)\nTabi'ah  : sapi betina yang berusia satu tahun (masuk tahun ke-2)\nMusinnah : sapi jantan/betina yang berusia 2 tahun (masuk tahun ke-3)").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog alertDialog = builderKeterangan.create();
                alertDialog.show();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        selectedItem = adapterView.getItemAtPosition(i).toString();

        switch (i) {
            // onta
            case 0:
                nisab = 5;
                nama_binatang = "Onta";
                break;

            // sapi
            case 1:
                nisab = 30;
                nama_binatang = "Sapi";
                break;

            // kambing/domba
            case 2:
                nisab = 40;
                nama_binatang = "Kambing/Domba";
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public void tampilAlertDialog(String jumlahZakat) {
        AlertDialog.Builder builderHasilZakat = new AlertDialog.Builder(MenuKalkulasiBinatangTernak.this);
        builderHasilZakat.setTitle("Hasil Perhitungan");
        builderHasilZakat.setMessage("Zakat yg harus Anda keluarkan adalah : \n\n" + jumlahZakat + ".").setNeutralButton("Tutup", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog alertDialog = builderHasilZakat.create();
        alertDialog.show();
    }
}
