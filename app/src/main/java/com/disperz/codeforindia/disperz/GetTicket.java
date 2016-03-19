package com.disperz.codeforindia.disperz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class GetTicket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidBarcodeView view = new AndroidBarcodeView(this);

        Intent intent = getIntent();
        final String name = intent.getStringExtra("PLACE");
        setTitle(name);

        String gate = intent.getStringExtra("GATE");
        Toast.makeText(GetTicket.this, "Scan this QR code at " + gate + "!", Toast.LENGTH_LONG).show();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms

                if (name.equals("Red Fort")) // if marker source is clicked
                {
                    Intent intent = new Intent(GetTicket.this, RF.class);
                    intent.putExtra("PLACE", name);
                    intent.putExtra("GATE", "Entry");
                    startActivity(intent);
                }
                if (name.equals("Akshardham")) // if marker source is clicked
                {
                    Intent intent = new Intent(GetTicket.this, AK.class);
                    intent.putExtra("PLACE", name);
                    intent.putExtra("GATE", "Entry");
                    startActivity(intent);
                }
                if (name.equals("Rashtrapati Bhavan")) {
                    Intent intent = new Intent(GetTicket.this, CheckpointsMapsActivity.class);
                    intent.putExtra("PLACE", name);
                    startActivity(intent);
                }
            }
        }, 10000);

        setContentView(view);
    }

}
