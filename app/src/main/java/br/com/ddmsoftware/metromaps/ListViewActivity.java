package br.com.ddmsoftware.metromaps;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
//import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;
import java.util.Random;

import static br.com.ddmsoftware.metromaps.Main2Activity.EXTRA_MESSAGE2;
import static br.com.ddmsoftware.metromaps.Main2Activity.EXTRA_MESSAGE3;

public class ListViewActivity extends AppCompatActivity {

    //private PhotoView photoView;
    private String sFilter = "";

    private FirebaseAnalytics mFirebaseAnalytics;

    private static final String EXTRA_MESSAGE = "br.com.ddmsoftware.metromaps.MESSAGE";
    private int iCountAdvertisement = 0;

    String AMERICA ;
    String SOUTH_AMERICA ;
    String NORTH_AMERICA ;
    String CENTRAL_AMERICA ;
    String AFRICA ;
    String ASIA ;
    String EUROPE;
    String OCEANIA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        AMERICA = this.getString(R.string.America_button);
        SOUTH_AMERICA = this.getString(R.string.SouthAmerica_List);
        NORTH_AMERICA = this.getString(R.string.NorthAmerica_List);
        CENTRAL_AMERICA = this.getString(R.string.CentralAmerica_List);
        AFRICA = this.getString(R.string.Africa_button);
        ASIA = this.getString(R.string.Asia_button);
        EUROPE = this.getString(R.string.Europe_button);
        OCEANIA = this.getString(R.string.Oceania_button);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        LinearLayout linearLayout = findViewById(R.id.linearLayoutListView);

        String sTagExtra;

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

        ListView listView;
        ArrayAdapter<String> adapter;

        if (sTagExtra.equals(EUROPE)) {
            adapter = new ArrayAdapter<>(this, R.layout.item_row, R.id.tvItemRow, myList);
            //listView = findViewById(R.id.myListView2); -- COMENTADO EM 22/10/2018 -- DOUGLIMAR -- PQ INSTANCIEI O MYLISTVIEW2 ???
            listView = findViewById(R.id.myListView);
        } else {

            adapter = new ArrayAdapter<>(this, R.layout.item_row, R.id.tvItemRow, myList);
            listView = findViewById(R.id.myListView);
        }
        // Set Background color in aleatory mode
        //linearLayout.setBackgroundResource(getAleatoryBackgroundColor());

        linearLayout.setBackgroundResource(getBackground(sTagExtra));
        listView.setAdapter(adapter);


        //getBackground(sTagExtra);



        // Create a AdView
        // Load Advertisement Banner
        AdView mAdView = findViewById(R.id.adViewListView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();
                //Toast.makeText(getApplicationContext(),item, Toast.LENGTH_SHORT).show();
                loadDataFromListClick2(item, sFilter);

                //Firebase Implementation
                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, String.valueOf(i));
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, item);
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

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

                //Toast.makeText(getApplicationContext(), "Show Adv: " + ShowAdv + " | " + iCountAdvertisement, Toast.LENGTH_SHORT).show();

                if (iCountAdvertisement >=3) {
                    ShowAdv = "SHOW_ADV";
                    iCountAdvertisement =0;
                    //Toast.makeText(getApplicationContext(), "Show Adv: " + ShowAdv + " | " + iCountAdvertisement, Toast.LENGTH_SHORT).show();
                }
                else
                    ShowAdv = "NO_SHOW";

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra(EXTRA_MESSAGE, String.valueOf(iMessage));
                intent.putExtra(EXTRA_MESSAGE2, ShowAdv);
                intent.putExtra(EXTRA_MESSAGE3, item);// Titulo do Mapa
                startActivity(intent);
            }
   }

    private int getAleatoryBackgroundColor(){

        int[] iColors = {R.color.colorAccent, R.color.colorBlue, R.color.colorGreen, R.color.colorYellow, R.color.colorOrange};

        Random random = new Random();

        int i = random.nextInt(5);

        //Toast.makeText(getApplicationContext(), "Color: " + iColors[i], Toast.LENGTH_SHORT).show();

        return iColors[i];

    }

    private int getBackground(String sTagExtra) {

        int retorno = 0;
        if (sTagExtra.equals(AMERICA)) {

            retorno = R.drawable.bg_americas;
        } else if (sTagExtra.equals(NORTH_AMERICA)) {
            retorno = R.drawable.bg_north_america;
        } else if (sTagExtra.equals(CENTRAL_AMERICA)) {
            retorno = R.drawable.bg_central_america;

        } else if (sTagExtra.equals(SOUTH_AMERICA)) {
            retorno = R.drawable.bg_south_america;
        } else if (sTagExtra.equals(EUROPE)) {
            retorno =  R.drawable.bg_europa;
        } else if (sTagExtra.equals(ASIA)) {
            retorno =  R.drawable.bg_asia;

        } else if (sTagExtra.equals(AFRICA)) {
            retorno =  R.drawable.bg_africa;
        } else if (sTagExtra.equals(OCEANIA))    {
            retorno =  R.drawable.bg_oceania;
        } else {

            getAleatoryBackgroundColor();
        }

        return retorno;


    };


    private int getImageMapID(Context context, String item) {

        int value = 0;

        // BRASIL
        if (item.equals(context.getString(R.string.porto_alegre))        ) value = R.drawable.mapa_porto_alegre;
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
        else if (item.equals(context.getString(R.string.abu_dhabi))           ) value= R.drawable.mapa_abu_dhabi;
            //INDIA
        else if (item.equals(context.getString(R.string.calcuta))         ) value= R.drawable.mapa_calcuta;
        else if (item.equals(context.getString(R.string.delhi))           ) value= R.drawable.mapa_delhi;
        else if (item.equals(context.getString(R.string.mumbai))          ) value= R.drawable.mapa_mumbai;
            //ENGLAND
        else if (item.equals(context.getString(R.string.londres))         ) value= R.drawable.mapa_londres;
        else if (item.equals(context.getString(R.string.manchester))      ) value= R.drawable.mapa_manchester;
            //SPAIN
        else if (item.equals(context.getString(R.string.barcelona))       ) value= R.drawable.mapa_barcelona;
        else if (item.equals(context.getString(R.string.madrid))           ) value= R.drawable.mapa_madrid;
        else if (item.equals(context.getString(R.string.sevilha))         ) value= R.drawable.mapa_sevilha;
        else if (item.equals(context.getString(R.string.valencia))        ) value= R.drawable.mapa_valencia;
            //PORTUGAL
        else if (item.equals(context.getString(R.string.lisboa))          ) value= R.drawable.mapa_lisboa;
            //NETHERLANDS
        else if (item.equals(context.getString(R.string.amsterdam))       ) value= R.drawable.mapa_amsterdam;
        else if (item.equals(context.getString(R.string.rotterdam))       ) value= R.drawable.mapa_rotterdam;
            //FRANCE
        else if (item.equals(context.getString(R.string.marselha))        ) value= R.drawable.mapa_marselha;
        else if (item.equals(context.getString(R.string.paris))           ) value= R.drawable.mapa_paris;
            //AUSTRALIA
        else if (item.equals(context.getString(R.string.sydney))          ) value= R.drawable.mapa_sydney;
        else if (item.equals(context.getString(R.string.melbourne))       ) value= R.drawable.mapa_melbourne;
            //SWITZERLAND
        else if (item.equals(context.getString(R.string.zurich))         ) value= R.drawable.mapa_zurich;
        else if (item.equals(context.getString(R.string.moscow))          ) value= R.drawable.mapa_moscow;
        else if (item.equals(context.getString(R.string.kiev))            ) value= R.drawable.mapa_kiev;
        else if (item.equals(context.getString(R.string.viena))           ) value= R.drawable.mapa_viena;
        else if (item.equals(context.getString(R.string.copenhague))      ) value= R.drawable.mapa_copenhagen;
        else if (item.equals(context.getString(R.string.helsinki))        ) value= R.drawable.mapa_helsinki;
        else if (item.equals(context.getString(R.string.milao))           ) value= R.drawable.mapa_milan;
        else if (item.equals(context.getString(R.string.roma))            ) value= R.drawable.mapa_roma;
        else if (item.equals(context.getString(R.string.estocolmo))       ) value= R.drawable.mapa_estocolmo;
            //GERMANY
        else if (item.equals(context.getString(R.string.berlim))          ) value= R.drawable.mapa_berlim;
            //NEW ZEALAND
        else if (item.equals(context.getString(R.string.auckland))        ) value= R.drawable.mapa_auckland;
            //UNITED STATUS
        else if (item.equals(context.getString(R.string.atlanta      ))         ) value= R.drawable.mapa_atlanta      ;
        else if (item.equals(context.getString(R.string.baltimore    ))       ) value= R.drawable.mapa_baltimore    ;
        else if (item.equals(context.getString(R.string.boston       ))          ) value= R.drawable.mapa_boston       ;
        else if (item.equals(context.getString(R.string.chicago      ))         ) value= R.drawable.mapa_chicago      ;
        else if (item.equals(context.getString(R.string.detroit      ))         ) value= R.drawable.mapa_detroit      ;
        else if (item.equals(context.getString(R.string.houston      ))         ) value= R.drawable.mapa_houston      ;
        else if (item.equals(context.getString(R.string.las_vegas    ))       ) value= R.drawable.mapa_las_vegas    ;
        else if (item.equals(context.getString(R.string.los_angeles  ))     ) value= R.drawable.mapa_los_angeles  ;
        else if (item.equals(context.getString(R.string.miami        ))           ) value= R.drawable.mapa_miami        ;
        else if (item.equals(context.getString(R.string.nova_york    ))     ) value= R.drawable.mapa_nova_iorque  ;
        else if (item.equals(context.getString(R.string.sao_francisco))   ) value= R.drawable.mapa_sao_francisco;
        else if (item.equals(context.getString(R.string.seattle      ))         ) value= R.drawable.mapa_seattle      ;
        else if (item.equals(context.getString(R.string.washington   ))      ) value= R.drawable.mapa_washington2  ;
            //CHINA
        else if (item.equals(context.getString(R.string.beijing   ))          ) value= R.drawable.mapa_beijing  ;
        else if (item.equals(context.getString(R.string.guangzhou   ))       ) value= R.drawable.mapa_guangzhou  ;
        else if (item.equals(context.getString(R.string.shanghai   ))          ) value= R.drawable.mapa_shanghai  ;
        else if (item.equals(context.getString(R.string.shenzhen   ))        ) value= R.drawable.mapa_shenzhen  ;
        else if (item.equals(context.getString(R.string.hongkong   ))       ) value= R.drawable.mapa_hong_kong;
            //SAUDI ARABIA
        else if (item.equals(context.getString(R.string.mecca))           ) value= R.drawable.mapa_mecca;
            //JAPAN
        else if (item.equals(context.getString(R.string.nagoya))          ) value= R.drawable.mapa_nagoya;
        else if (item.equals(context.getString(R.string.tokyo))          ) value= R.drawable.mapa_tokyo;
        else if (item.equals(context.getString(R.string.yokohama))        ) value= R.drawable.mapa_yokohama;
            //TURKEY
        else if (item.equals(context.getString(R.string.ankara))          ) value= R.drawable.mapa_ankara;
        else if (item.equals(context.getString(R.string.istambul))        ) value= R.drawable.mapa_istambul;
            //TAIWAN
        else if (item.equals(context.getString(R.string.taipei))          ) value= R.drawable.mapa_taipei;
            //SOUTH KOREA
        else if (item.equals(context.getString(R.string.seoul))           ) value= R.drawable.mapa_seoul;
            //SINGAPORE
        else if (item.equals(context.getString(R.string.singapura))       ) value= R.drawable.mapa_singapura;

        return value;
    }

}
