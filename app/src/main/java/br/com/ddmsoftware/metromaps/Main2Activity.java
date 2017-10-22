package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "br.com.ddmsoftware.metromaps.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Continent continents = new Continent();
        List<String> myContinentList = continents.getAllContinents();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_row,R.id.tvItemRow, myContinentList);
        ListView listView = (ListView) findViewById(R.id.lstContinents);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

                switch (item) {

                    case "Asia": {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case "América": {
                        Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, "América");
                        startActivity(intent);
                        break;
                    }
                    case "África": {
                        Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                        intent.putExtra(EXTRA_MESSAGE, "África");
                        startActivity(intent);
                        break;
                    }
                    case "Europa": {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                    case "Oceania": {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        break;
                    }
                }

            }
        });

        /*

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

*/
    }

}
