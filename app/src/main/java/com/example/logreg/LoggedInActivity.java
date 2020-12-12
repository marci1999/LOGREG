package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoggedInActivity extends AppCompatActivity {

    TextView text_udvozlo;
    Button btn_kilep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        
        init();
        kiir();
        butonOnClick();
    }

    private void kiir() {

    }

    private void butonOnClick() {
        btn_kilep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza = new Intent(LoggedInActivity.this,MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
    }

    private void init() {
        text_udvozlo = findViewById(R.id.text_kiir);
        btn_kilep = findViewById(R.id.btn_vissza_belepes);
    }


}