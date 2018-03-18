package com.example.arif.muhammadarifwicaksono_1202154205_studycase4;


import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import java.io.InputStream;
import java.net.URL;

public class gambar extends AppCompatActivity {

    private EditText URLgambar;
    private ImageView Gaambar;
    private Button mButtonLoad;
    private ProgressDialog mLoading; //deklarasi Variabel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);// akan tersambung ke layout gambar


        //Mendefinisikan atribut pada layout activity  gambar
        URLgambar = (EditText) findViewById(R.id.url);
        Gaambar = (ImageView) findViewById(R.id.gambarr);
        mButtonLoad = (Button) findViewById(R.id.btnCari);
    }



    public void cari (View view) {
        loadImagetInit();
    }

    private void loadImagetInit() {
        String ImageUrl = URLgambar.getText().toString();
        //AsyncTask mencari gambar di internet
        new loadImage().execute(ImageUrl);
    }

    private class loadImage extends AsyncTask<String, Void, Bitmap> {
        //method ketika proses asynctask belum mulai
        @Override
        protected void onPreExecute() { //awal dari pengerjaan dalam menampilkan loading
            super.onPreExecute();

            // Menampilkan Proses Dialog
            mLoading = new ProgressDialog(gambar.this);
            mLoading.setTitle("Downloading image");
            mLoading.setMessage("Loading...");
            mLoading.show();
        }
        //method saat proses asynctask dijalankan
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap a = null;
            try {
                //download gambar dr url
                URL x = new URL(params[0]);
                //konversi gambar ke bitmap (decode to bitmap)

                a = BitmapFactory.decodeStream((InputStream) x.getContent());
            } catch (Exception e) {
                e.printStackTrace();
            }
            return a;
        }

        //method sesudah asynctask sudah dijalankan
        @Override
        protected void onPostExecute(Bitmap a) { // akhir dari pengerjaan ketika di klik lg button maka akan menampilkan gambar yang telah dicari
            super.onPostExecute(a);
            //menampung gambar ke imageview dan menampilkan
            Gaambar.setImageBitmap(a);
            //menghilangkan progress dialog
            mLoading.dismiss();
        }
    }
}
