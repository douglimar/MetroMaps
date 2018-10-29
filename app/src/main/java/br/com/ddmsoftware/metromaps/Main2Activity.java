package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Main2Activity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE  = "br.com.ddmsoftware.metromaps.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "br.com.ddmsoftware.metromaps.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "br.com.ddmsoftware.metromaps.MESSAGE3";

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);



        final String[] strClickable_button = {""};

        final Button btnAmerica = findViewById(R.id.btnAmerica);
        final Button btnAfrica = findViewById(R.id.btnAfrica);
        final Button btnAsia = findViewById(R.id.btnAsia);
        final Button btnEuropa = findViewById(R.id.btnEuropa);
        final Button btnOceania = findViewById(R.id.btnOceania);
        final Button btnFavoritos = findViewById(R.id.btnFavorite);

        btnAmerica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                openListViewActivity(btnAmerica.getText().toString());

            }
        });


        btnAsia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListViewActivity(btnAsia.getText().toString());

            }
        });


        btnAfrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListViewActivity(btnAfrica.getText().toString());

            }
        });


        btnEuropa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListViewActivity(btnEuropa.getText().toString());

            }
        });


        btnOceania.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListViewActivity(btnOceania.getText().toString());

            }
        });


        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                strClickable_button[0] = btnFavoritos.getText().toString();

                intent = new Intent(getApplicationContext(), FavoriteMapsActivity.class);

                intent.putExtra(EXTRA_MESSAGE, strClickable_button[0]);
                intent.putExtra(EXTRA_MESSAGE2, "");

                startActivity(intent);

            }
        });

        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Firebase Implementation
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, strClickable_button[0]);
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, strClickable_button[0]);
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


        // Create a AdView
        // Load Advertisement Banner
        AdView mAdView = findViewById(R.id.adViewMain);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    private void openListViewActivity(String pExtra) {

        intent = new Intent(getApplicationContext(), ListViewActivity.class);

        intent.putExtra(EXTRA_MESSAGE, pExtra);
        intent.putExtra(EXTRA_MESSAGE2, "");

        startActivity(intent);

    }

}
