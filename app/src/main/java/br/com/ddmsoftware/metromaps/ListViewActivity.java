package br.com.ddmsoftware.metromaps;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        final String NORTH_AMERICA = getString(R.string.NorthAmerica_List).toUpperCase();
        final String SOUTH_AMERICA = getString(R.string.SouthAmerica_List).toUpperCase();
        final String CENTRAL_AMERICA = getString(R.string.CentralAmerica_List).toUpperCase();

        String[] countryArray = {NORTH_AMERICA, CENTRAL_AMERICA, SOUTH_AMERICA};

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.item_row,R.id.tvItemRow, countryArray);
        ListView listView = (ListView) findViewById(R.id.myListView);
        listView.setAdapter(adapter);

        /*TextView tvClick = (TextView) findViewById(R.id.tvItemRow);

        tvClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Passou...", Toast.LENGTH_SHORT).show();
            }
        }); */

        ListView list = (ListView) findViewById(R.id.myListView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();

                if (item.equals(NORTH_AMERICA)) {


                } else if (item.equals(SOUTH_AMERICA)) {


                } else if (item.equals(CENTRAL_AMERICA)) {


                }

            }
        });
    }
}
