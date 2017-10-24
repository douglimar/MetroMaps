package br.com.ddmsoftware.metromaps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.List;
import static br.com.ddmsoftware.metromaps.Main2Activity.EXTRA_MESSAGE2;
import static br.com.ddmsoftware.metromaps.Main2Activity.EXTRA_MESSAGE3;

public class ListViewActivity extends AppCompatActivity {

    private int iCountAdvertisement = 0;

    //private PhotoView photoView;
    private String sFilter = "";

    private static final String EXTRA_MESSAGE = "br.com.ddmsoftware.metromaps.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        String sTagExtra;

        String AMERICA = this.getString(R.string.America_button);
        String SOUTH_AMERICA = this.getString(R.string.SouthAmerica_List);
        String NORTH_AMERICA = this.getString(R.string.NorthAmerica_List);
        String CENTRAL_AMERICA = this.getString(R.string.CentralAmerica_List);
        String AFRICA = this.getString(R.string.Africa_button);
        String ASIA = this.getString(R.string.Asia_button);
        String EUROPE = this.getString(R.string.Europe_button);
        String OCEANIA = this.getString(R.string.Oceania_button);

        //Help to identify if the User is clicking over a Country / Continent or a Final List (City)
        //If were a Final List, the next screen shall be the Map Screen.
        String aFilter[] = {"COUNTRY", "CITY"};

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        //TextView itemRow = (TextView)findViewById(R.id.tvItemRow);

        sTagExtra = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE);

        List<String> myList;

        //Toast.makeText(getApplicationContext()," ListView => " +sTagExtra, Toast.LENGTH_SHORT).show();

        if (sTagExtra.equals(AMERICA)||sTagExtra.equals(NORTH_AMERICA)|| sTagExtra.equals(CENTRAL_AMERICA)
                || sTagExtra.equals(SOUTH_AMERICA) || sTagExtra.equals(EUROPE)|| sTagExtra.equals(ASIA)
                || sTagExtra.equals(AFRICA) || sTagExtra.equals(OCEANIA)) {

            Pais paises = new Pais();
            myList = paises.getAllCountries(this, sTagExtra);
            sFilter = aFilter[0]; // COUNTRY

        } else {
                Cidade cidades = new Cidade();
                myList = cidades.getAllCities(this, sTagExtra);
                sFilter = aFilter[1]; // CITY
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_row,R.id.tvItemRow, myList);
        ListView listView = (ListView) findViewById(R.id.myListView);
        listView.setBackgroundResource(R.drawable.wallpaper3);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getApplicationContext(),item, Toast.LENGTH_SHORT).show();
                loadDataFromListClick2(item, sFilter);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadDataFromListClick2(String item, String sFilter) {

        int iMessage;

        if (sFilter.equals("COUNTRY")){

            Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
            intent.putExtra(EXTRA_MESSAGE, item);
            startActivity(intent);

        } else
            if (sFilter.equals("CITY")) {

                iMessage = getImageMapID(this, item);
                iCountAdvertisement++;
                String ShowAdv;

                if (iCountAdvertisement >=3) {
                    ShowAdv = "SHOW_ADV";
                    iCountAdvertisement =0;
                }
                else
                    ShowAdv = "NO_SHOW";

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra(EXTRA_MESSAGE, String.valueOf(iMessage));
                intent.putExtra(EXTRA_MESSAGE2, ShowAdv);
                intent.putExtra(EXTRA_MESSAGE3, item); // Titulo do Mapa
                startActivity(intent);
            }
   }

    private int getImageMapID(Context context, String item) {

        int value = 0;

        if (item.equals(context.getString(R.string.porto_alegre))        ) value = R.drawable.mapa_porto_alegre; // BRASIL
        else if (item.equals(context.getString(R.string.sao_paulo))      ) value = R.drawable.mapa_sao_paulo;
        else if (item.equals(context.getString(R.string.rio_de_janeiro)) ) value = R.drawable.mapa_rio_janeiro;
        else if (item.equals(context.getString(R.string.belo_horizonte)) ) value = R.drawable.mapa_belohorizonte;
        else if (item.equals(context.getString(R.string.salvador))       ) value = R.drawable.mapa_salvador;
        else if (item.equals(context.getString(R.string.recife))         ) value = R.drawable.mapa_recife;
        else if (item.equals(context.getString(R.string.maceio))         ) value = R.drawable.mapa_maceio;
        else if (item.equals(context.getString(R.string.fortaleza))      ) value = R.drawable.mapa_fortaleza;
        else if (item.equals(context.getString(R.string.teresina))       ) value = R.drawable.mapa_teresina;
        else if (item.equals(context.getString(R.string.natal))          ) value = R.drawable.mapa_natal;
        else if (item.equals(context.getString(R.string.brasilia))       ) value= R.drawable.mapa_brasilia;
        else if (item.equals(context.getString(R.string.jo_o_pessoa))    ) value= R.drawable.mapa_joaopessoa;
            // ARGENTINA
        else if (item.equals(context.getString(R.string.buenos_aires))   ) value= R.drawable.mapa_buenos_aires;
            // PERU
        else if (item.equals(context.getString(R.string.lima))           ) value= R.drawable.mapa_lima;
            // COLOMBIA
        else if (item.equals(context.getString(R.string.medelin))        ) value= R.drawable.mapa_medelin;
            // CHILE
        else if (item.equals(context.getString(R.string.valparaiso))     ) value= R.drawable.mapa_valparaiso;
        else if (item.equals(context.getString(R.string.santiago))       ) value= R.drawable.mapa_santiago;
            // VENEZUELA
        else if (item.equals(context.getString(R.string.caracas))        ) value= R.drawable.mapa_caracas;
            //AMERICA CENTRAL
        else if (item.equals(context.getString(R.string.panama))         ) value= R.drawable.mapa_panama;
        else if (item.equals(context.getString(R.string.santo_domingo))  ) value= R.drawable.mapa_santo_domingo;
        else if (item.equals(context.getString(R.string.san_juan))       ) value= R.drawable.mapa_san_juan;
            // CANADÁ
        else if (item.equals(context.getString(R.string.calgary))        ) value= R.drawable.mapa_calgary;
        else if (item.equals(context.getString(R.string.edmonton))       ) value= R.drawable.mapa_edmonton;
        else if (item.equals(context.getString(R.string.montreal))       ) value= R.drawable.mapa_montreal;
        else if (item.equals(context.getString(R.string.ottawa))         ) value= R.drawable.mapa_ottawa;
        else if (item.equals(context.getString(R.string.toronto))        ) value= R.drawable.mapa_toronto;
        else if (item.equals(context.getString(R.string.vancouver))      ) value= R.drawable.mapa_vancouver;
            //MEXICO
        else if (item.equals(context.getString(R.string.cidade_do_mexico))) value= R.drawable.mapa_cidade_do_mexico;
        else if (item.equals(context.getString(R.string.guadalajara))     ) value= R.drawable.mapa_guadalajara;
        else if (item.equals(context.getString(R.string.monterrey))       ) value= R.drawable.mapa_monterrey;
            //SOUTH AFRICA
        else if (item.equals(context.getString(R.string.cidade_do_cabo))  ) value= R.drawable.mapa_cidade_do_cabo;
        else if (item.equals(context.getString(R.string.durban))          ) value= R.drawable.mapa_durban;
        else if (item.equals(context.getString(R.string.east_london_port_elisabeth))     ) value= R.drawable.mapa_east_london;
        else if (item.equals(context.getString(R.string.johannesburg_pretoria))   ) value= R.drawable.mapa_johannesburgo;
            //TUNISIA
        else if (item.equals(context.getString(R.string.tunis))           ) value= R.drawable.mapa_tunis;
            //EGITO
        else if (item.equals(context.getString(R.string.cairo))           ) value= R.drawable.mapa_cairo;
            //EMIRADOS ARABES
        else if (item.equals(context.getString(R.string.dubai))           ) value= R.drawable.mapa_dubai;
            //INDIA
        else if (item.equals(context.getString(R.string.calcuta))         ) value= R.drawable.mapa_calcuta;
        else if (item.equals(context.getString(R.string.delhi))           ) value= R.drawable.mapa_delhi;
        else if (item.equals(context.getString(R.string.mumbai))          ) value= R.drawable.mapa_mumbai;
            //ENGLAND
        else if (item.equals(context.getString(R.string.londres))          ) value= R.drawable.mapa_londres;
        else if (item.equals(context.getString(R.string.manchester))          ) value= R.drawable.mapa_manchester;


        return value;
    }

}
