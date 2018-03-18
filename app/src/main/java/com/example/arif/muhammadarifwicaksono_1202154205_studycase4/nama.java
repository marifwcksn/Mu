package com.example.arif.muhammadarifwicaksono_1202154205_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class nama extends AppCompatActivity {

    ListView listMahasiswa; //Deklarasi Variabel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nama);//akan tersambung ke layout nama
        setTitle("AsyncTask"); //Deklarasi Array Mahasiswa
        listMahasiswa = (ListView) findViewById(R.id.listM);
    }
    //method ketika btn di pencet
    public void mulai(View view) {
        new getData(listMahasiswa).execute(); //proses asynctask dimulai
    }
    //subclass assynctask
    class getData extends AsyncTask<String, Integer, String> {
        ListView listMhs;
        ArrayAdapter adapter;
        ArrayList<String> listNama;
        ProgressDialog dialog;

        //constructor saat asynctask diinisialisasi
        public getData(ListView listMhs) {
            this.listMhs = listMhs;
            dialog = new ProgressDialog(nama.this);
            listNama = new ArrayList<>();
        }

        //method ketika proses asynctask belum dimulai
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //menampilkan proses dialog
            dialog.setTitle("Loading Data");
            dialog.setIndeterminate(true);
            dialog.setProgress(0);
            dialog.setMax(100);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setCancelable(true);
            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialog.dismiss();
                    getData.this.cancel(true);
                }
            });

            dialog.show();
        }

        //method saat proses asynctask dijalankan
        @Override
        protected String doInBackground(String... strings) {
            adapter = new ArrayAdapter<>(nama.this, android.R.layout.simple_list_item_1, listNama); //membuat adapter

            //menyimpan array pada sebuah variabel
            String[] mahasiswa = getResources().getStringArray(R.array.namaa);
            //perulangan untuk menyimpan array
            for (int a = 0; a < mahasiswa.length; a++) {
                final long persen = 100L * a / mahasiswa.length;
                final String nama = mahasiswa[a];
                try {
                    Runnable change = new Runnable() {
                        @Override
                        public void run() {
                            dialog.setMessage((int) persen+"% - Adding "+nama);
                        }
                    };
                    runOnUiThread(change);
                    Thread.sleep(300);
                    listNama.add(mahasiswa[a]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //method sesudah asynctask sudah dijalankan
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listMhs.setAdapter(adapter);
            dialog.dismiss();
        }
    }
}