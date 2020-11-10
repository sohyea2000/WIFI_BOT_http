package com.example.wifibot;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

import cz.msebera.android.httpclient.*;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpGet;

import cz.msebera.android.httpclient.impl.client.HttpClientBuilder;

public class MainActivity extends AppCompatActivity {

    public String ip_address;
    public String status;
    ImageView fwdBtn;
    ImageView backBtn;
    ImageView leftBtn;
    ImageView rightBtn;
    Button up;
    Button down;
    Button open;
    Button close;
    final Context context = this;
    SeekBar seekBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip_address = "192.168.4.1";

        fwdBtn = findViewById(R.id.forward);
        backBtn = findViewById(R.id.backward);
        leftBtn = findViewById(R.id.left);
        rightBtn = findViewById(R.id.right);
        up = findViewById(R.id.up);
        down = findViewById(R.id.down);
        open = findViewById(R.id.open);
        close = findViewById(R.id.close);
        seekBar = findViewById(R.id.seekbar);
        fwdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "F";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "B";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);
            }
        });
        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "L";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "R";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "U";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "D";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);
            }
        });
        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "O";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "C";
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int i = seekBar.getProgress();
                status = Integer.toString(i);
                String serverAddress = ip_address+":"+"80" ;
                HttpRequestTask requestTask = new HttpRequestTask(serverAddress);
                requestTask.execute(status);

                Toast.makeText(MainActivity.this, status, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
    class HttpRequestTask extends AsyncTask<String, Void, String> {


        private String serverAddress;
        private String serverResponse = "";
        private AlertDialog dialog;




        HttpRequestTask(String serverAddress) {
            this.serverAddress = serverAddress;


           /* AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("HTTP Response from Ip Address:");
            builder.setCancelable(true);
            dialog = builder
                    .create();*/
        }


        @Override
        protected void onPostExecute(String s) {
            /* dialog.setMessage(serverResponse);


            if (!dialog.isShowing())
            dialog.show();*/
        }


        @Override
        public String doInBackground(String... params) {
            //dialog.setMessage("Data sent , waiting response from server...");


          // if (!dialog.isShowing())
            //    dialog.show();


            String val = params[0];
            final String url = "http://" + serverAddress + "/wifi/" + val;
            try {
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet getRequest = new HttpGet();
                getRequest.setURI(new URI(url));
                HttpResponse response = client.execute(getRequest);


                InputStream inputStream;
                inputStream = response.getEntity().getContent();
                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(inputStream));


                serverResponse = bufferedReader.readLine();
                inputStream.close();


            } catch (URISyntaxException | IOException e) {
                e.printStackTrace();
                serverResponse = e.getMessage();
            }


            return serverResponse;
        }


        @Override
        protected void onPreExecute() {
           /* dialog.setMessage("Sending data to server, please wait...");


            if (!dialog.isShowing())
                dialog.show();*/
        }
    }
}
