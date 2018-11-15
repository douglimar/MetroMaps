package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;
import java.util.Random;

import static br.com.ddmsoftware.metromaps.MainActivity.EXTRA_MESSAGE2;
import static br.com.ddmsoftware.metromaps.MainActivity.EXTRA_MESSAGE3;

public class ListViewActivity extends AppCompatActivity {

    //private PhotoView photoView;
    private String sFilter = "";

    private FirebaseAnalytics mFirebaseAnalytics;

    private static final String EXTRA_MESSAGE = "br.com.ddmsoftware.metromaps.MESSAGE";
    private int iCountAdvertisement = 0;

    private String AMERICA ;
    private String SOUTH_AMERICA ;
    private String NORTH_AMERICA ;
    private String CENTRAL_AMERICA ;
    private String AFRICA ;
    private String ASIA ;
    private String EUROPE;
    private String OCEANIA;


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

        //Help to identify if the User is clicking over a Country a Final List (City)
        //If were a Final List, the next screen shall be the Map Screen.
        String aFilter[] = {"COUNTRY", "CITY"};

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        //TextView itemRow = (TextView)findViewById(R.id.tvItemRow);

        sTagExtra = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

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

                //linearLayout.setBackgroundResource(getAleatoryBackgroundColor());

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

                iMessage = MapsUtils.getImageMapID(this, item);
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

    @SuppressWarnings("ConstantConditions")
    private int getBackground(String sTagExtra) {

        int retorno;

        if (sTagExtra.equals(AMERICA)) {

            retorno = R.drawable.bg_americas;
        } else if (sTagExtra.equals(NORTH_AMERICA)) {
            retorno = R.drawable.bg_north_america;
        } else if (sTagExtra.equals(CENTRAL_AMERICA)) {
            retorno = R.drawable.bg_central_america;

        } else if (sTagExtra.equals(SOUTH_AMERICA)) {
            retorno = R.drawable.bg_south_america;
        } else if (sTagExtra.equals(EUROPE)) {
            retorno = R.drawable.bg_europa;
        } else if (sTagExtra.equals(ASIA)) {
            retorno = R.drawable.bg_asia;

        } else if (sTagExtra.equals(AFRICA)) {
            retorno = R.drawable.bg_africa;
        } else if (sTagExtra.equals(OCEANIA)) {
            retorno = R.drawable.bg_oceania;
        }

        else {

            retorno = getAleatoryBackgroundColor();

        }

        return retorno;
    }

}
