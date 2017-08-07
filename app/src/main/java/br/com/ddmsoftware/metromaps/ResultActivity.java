package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class ResultActivity extends AppCompatActivity {


    private InterstitialAd mInterstitialAd;

    DatabaseController CRUD;

    String showAdv;
    CheckBox chk_DefaultMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent intent = getIntent();

        final int message = Integer.parseInt(intent.getStringExtra(MainActivity.EXTRA_MESSAGE));
        showAdv = intent.getStringExtra(MainActivity.EXTRA_MESSAGE2);
        String mapName = intent.getStringExtra(MainActivity.EXTRA_MESSAGE3);

        chk_DefaultMap = (CheckBox)findViewById(R.id.chk_defaultmap);

        Toast.makeText(this.getBaseContext(), showAdv, Toast.LENGTH_SHORT).show();

        PhotoView photoView2 = (PhotoView) findViewById(R.id.photo_view2);
        photoView2.setImageResource(message);

        this.setTitle(mapName);

        chk_DefaultMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CRUD = new DatabaseController(ResultActivity.this);

                if (chk_DefaultMap.isChecked()) {

                    //CRUD.deleteData2();

                    CRUD.deleteData2();


                    String resultado = CRUD.insertData(Integer.toString(message));
                    Toast.makeText(ResultActivity.this, resultado, Toast.LENGTH_SHORT).show();

                } else {

                    String resultado = CRUD.insertData("");
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
        AdView mAdView = (AdView) findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        // Create the InterstitialAd and set the adUnitId (defined in values/strings.xml).
        mInterstitialAd = newInterstitialAd();
        loadInterstitial();


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
        interstitialAd.setAdUnitId(getString(R.string.interstitial_ad_unit_id));
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
            Toast.makeText(this, "Ad did not load", Toast.LENGTH_SHORT).show();
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
}
