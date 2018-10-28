package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Result2Activity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private InterstitialAd mInterstitialAd;
    private DatabaseController CRUD;

    private  MapsDatabaseController mapsDatabaseController;



    private String showAdv;
    private CheckBox chk_DefaultMap;

    private PhotoView photoView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        CoordinatorLayout coordinatorLayoutResult = findViewById(R.id.coordinatorLayoutResult2);

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //final int message = Integer.parseInt(intent.getStringExtra(Main2Activity.EXTRA_MESSAGE));

        final String strExtraMapId = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE);

        showAdv = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE2);
        final String mapName = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE3);

        chk_DefaultMap = findViewById(R.id.chk_defaultmap2);

        Snackbar.make(coordinatorLayoutResult, R.string.map_info_message, Snackbar.LENGTH_LONG).show();


        photoView3 = findViewById(R.id.photo_view3);

        if (!strExtraMapId.equals(getResources().getString(R.string.Default_button)))

            photoView3.setImageResource(Integer.parseInt(strExtraMapId));

            /*
        if (message != R.string.Default_button)
            photoView2.setImageResource(message); */
        else {

            CRUD = new DatabaseController(this);
            checkDefaultMap();
        }

        this.setTitle(mapName);

        chk_DefaultMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle bundle = new Bundle();
                bundle.putString(FirebaseAnalytics.Param.ITEM_ID,"CheckDefaultMap");
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "CheckDefaultMap");
                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
                mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

                CRUD = new DatabaseController(Result2Activity.this);

                if (chk_DefaultMap.isChecked()) {

                    // Comentados em 23.10.2018
                    //CRUD.deleteData2();
                    //CRUD.insertData2(message);

                    //Adicionada a linha aqui
                    CRUD.insertData3(strExtraMapId, mapName);



                    //String resultado = CRUD.insertData(message);
                    //Toast.makeText(ResultActivity.this, resultado, Toast.LENGTH_SHORT).show();

                } else {

                    CRUD.insertData2("");
                    //resulta = CRUD.deleteData();
                    //CRUD.deleteData2();
                    //Toast.makeText(ResultActivity.this, resultado, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Create a AdView
        // Load Advertisement Banner
        AdView mAdView = findViewById(R.id.adViewResult);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fabFavorite = findViewById(R.id.fabFavorite);

        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mapsDatabaseController = new MapsDatabaseController(Result2Activity.this, false);

                mapsDatabaseController.insertData(strExtraMapId, mapName);


            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            //Intent intent = new Intent(this, AdmobActivity.class);
            //startActivity(intent);

            if (showAdv.equals("SHOW_ADV"))
                showInterstitial();

            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private InterstitialAd newInterstitialAd() {
        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id_TESTE));
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

                //mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {

                //mNextLevelButton.setEnabled(true);
            }

            @Override
            public void onAdClosed() {
                // Proceed to the next level.
                goToNextLevel();
            }
        });
        return interstitialAd;
    }

    private void showInterstitial() {
        // Show the ad if it's ready. Otherwise toast and reload the ad.
        if (mInterstitialAd != null && mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            //Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
            goToNextLevel();
        }
    }

    private void loadInterstitial() {
        // Disable the next level button and load the ad.
        //mNextLevelButton.setEnabled(false);
        AdRequest adRequest = new AdRequest.Builder()
                .setRequestAgent("android_studio:ad_template").build();
        mInterstitialAd.loadAd(adRequest);
    }

    private void goToNextLevel() {
        // Show the next level and reload the ad to prepare for the level after.
        //mLevelTextView.setText("Level " + (++mLevel));
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();
    }

    public void onResume(){
        super.onResume();
        //checkDefaultMap();
    }


    private void checkDefaultMap() {

        final Cursor resultSet = CRUD.loadData();

        int iTotReg = resultSet.getCount();

        //Toast.makeText(this.getApplicationContext(), "Tot.Reg:"  + iTotReg, Toast.LENGTH_SHORT ).show();

        if (iTotReg>0){
            resultSet.moveToFirst();

            do {

                if (!resultSet.getString(0).equals("")) {
                    //photoView2.setImageResource(resultSet.getInt(0));
                    //photoView2.setImageResource(resultSet.getInt(0));
                    photoView3.setImageResource(resultSet.getInt(0));
                }
            } while (resultSet.moveToNext());
        }

        resultSet.close();
    }



}
