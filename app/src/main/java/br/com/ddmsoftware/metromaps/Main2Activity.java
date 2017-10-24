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
    public static final String EXTRA_MESSAGE2 = "br.com.ddmsoftware.metromaps.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "br.com.ddmsoftware.metromaps.MESSAGE3";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Continent continents = new Continent();
        List<String> myContinentList = continents.getAllContinents(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_row,R.id.tvItemRow, myContinentList);
        ListView listView = (ListView) findViewById(R.id.lstContinents);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, item);
                startActivity(intent);
            }
        });
    }
}
