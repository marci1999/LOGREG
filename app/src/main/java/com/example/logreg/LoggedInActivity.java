package com.example.logreg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoggedInActivity extends AppCompatActivity {

    TextView text_udvozlo;
    Button btn_kilep;
    SQLDatabase adatbzias;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        
        init();
        kiir();
        butonOnClick();
    }

    private void kiir() {
        String nev = sharedPreferences.getString("neved","");
        Cursor adatok = adatbzias.adatLekerdezes(nev);
        if (adatok == null){
            Toast.makeText(this, "sikertelen adatlekérdezés", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount() == 0){
            Toast.makeText(this, "nincs lekérdezhető adat", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder stringBuilder = new  StringBuilder();
        while (adatok.moveToNext()){
            stringBuilder.append("teljses név : ").append(adatok.getString(0)).append("\n");
        }
        text_udvozlo.setText(stringBuilder.toString());
        Cursor adatok2 = adatbzias.adatLekerdezesEmail(nev);
        if (adatok2 == null){
            Toast.makeText(this, "sikertelen adatlekérdezés", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok2.getCount() == 0){
            Toast.makeText(this, "nincs lekérdezhető adat", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuilder stringBuilder2 = new  StringBuilder();
        while (adatok2.moveToNext()){
            stringBuilder2.append("teljses név : ").append(adatok2.getString(0)).append("\n");
        }
        text_udvozlo.setText(stringBuilder.toString());
        Toast.makeText(this, "sikeres lekérdezés", Toast.LENGTH_SHORT).show();
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

        adatbzias = new SQLDatabase(LoggedInActivity.this);

        sharedPreferences=getSharedPreferences("adatk", Context.MODE_PRIVATE);
    }


}