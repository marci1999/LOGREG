package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    EditText text_adat,text_jelszo;
    Button btn_belepes,btn_regisztratco;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        butononclick();
    }

    private void butononclick() {
        btn_regisztratco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rigisztral = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(rigisztral);
                finish();
            }
        });
        btn_belepes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nev = text_adat.getText().toString();
                editor.putString("neved", nev);
                editor.commit();

                Intent belep = new Intent(MainActivity.this,LoggedInActivity.class);
                startActivity(belep);
                finish();
            }
        });
    }

    private void init() {
        text_adat = findViewById(R.id.et_felhasnalo_main);
        text_jelszo = findViewById(R.id.et_jelszo_main);
        btn_belepes = findViewById(R.id.btn_bejelentkezes);
        btn_regisztratco =findViewById(R.id.btn_regisztracio_main);
        sharedPreferences = getSharedPreferences("adatok", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
}