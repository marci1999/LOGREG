package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText et_email,et_felhNev,et_jelszo,et_teljesNev;
    Button btn_belep,btn_belep_vissza;
    SQLDatabase adatbzias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        init();
        botunOnClick();
    }

    private boolean egezo(){
        Cursor adatok =  adatbzias.kereses(et_email.getText().toString().trim());
        StringBuilder stringBuilder = new  StringBuilder();
        while (adatok.moveToNext()){
            stringBuilder.append("teljses név : ").append(adatok.getString(0)).append("\n");
        }
        Toast.makeText(this, ""+ adatok.getString(0) +"", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void FehasznaloRogziese() {
        String emil = et_email.getText().toString().trim();
        String felhNev = et_felhNev.getText().toString().trim();
        String jelszo = et_jelszo.getText().toString().trim();
        String teljesNev = et_teljesNev.getText().toString().trim();

        /*if (emil.isEmpty()){
            Toast.makeText(this, "Az emil megadás kötelezö!", Toast.LENGTH_SHORT).show();
        }
        if (felhNev.isEmpty()){
            Toast.makeText(this, "A felhasználonév megadás kötelezö!", Toast.LENGTH_SHORT).show();
        }
        if (jelszo.length() < 5){
            Toast.makeText(this, "a jelszonak minimum 5 maximum 25 kraktrt kell tartalmazni.", Toast.LENGTH_SHORT).show();
        }
        if (teljesNev.isEmpty()){
            Toast.makeText(this, "A teljes név megadás kötelezö!", Toast.LENGTH_SHORT).show();
        }*/
        if (teljesNev.isEmpty() || jelszo.isEmpty() || felhNev.isEmpty() || emil.isEmpty()){
            Toast.makeText(this, "minden mezöt ki kel töltni", Toast.LENGTH_SHORT).show();
        }
        if (jelszo.length() < 5){
            Toast.makeText(this, "a jelszonak minimum 5 maximum 25 karaktert kell tartalmazni.", Toast.LENGTH_SHORT).show();
        }
        boolean sikeres = false;
        if (!teljesNev.isEmpty() && jelszo.length() >= 5 && !felhNev.isEmpty() && !emil.isEmpty()){
            sikeres = adatbzias.adatRogzites(emil,felhNev,jelszo,teljesNev);
        }
        if (sikeres){
            Toast.makeText(this, "sikeres regisztráció", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "sikertelen regisztráció", Toast.LENGTH_SHORT).show();
        }
    }

    private void botunOnClick() {
        btn_belep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FehasznaloRogziese();
            }
        });
        btn_belep_vissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vissza =new  Intent(RegisterActivity.this,MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });
    }

    private void init() {
        et_email = findViewById(R.id.et_email);
        et_felhNev = findViewById(R.id.et_felhasznalonev);
        et_jelszo = findViewById(R.id.et_jelszo);
        et_teljesNev = findViewById(R.id.et_teljesnev);

        btn_belep =findViewById(R.id.btn_regisztracio_regisztrcio);
        btn_belep_vissza =findViewById(R.id.btn_vissza_regisztracio);

        adatbzias = new SQLDatabase(RegisterActivity.this);
    }
}