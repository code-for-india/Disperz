package com.disperz.codeforindia.disperz;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class GetTicket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AndroidBarcodeView view = new AndroidBarcodeView(this);

        setContentView(view);
    }

}
