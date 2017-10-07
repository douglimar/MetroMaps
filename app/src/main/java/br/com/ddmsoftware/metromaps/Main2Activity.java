package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Button btnAfrica = (Button) findViewById(R.id.btnAfrica);
        btnAfrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                startActivity(intent);
            }
        });

        Button btnAmerica = (Button) findViewById(R.id.btnAmerica);
        btnAmerica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);

                startActivity(intent);
            }
        });


    }

    private void initializeData() {

        Cidade cidades = new Cidade("São Paulo");

        cidades.setCityName("");

        Pais paises = new Pais();

        Continent continents = new Continent();



    }
}
