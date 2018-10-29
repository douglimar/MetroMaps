package br.com.ddmsoftware.metromaps;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;


@SuppressWarnings("JavaDoc")
public class Result2Activity extends AppCompatActivity {

    //private FirebaseAnalytics mFirebaseAnalytics;
    private InterstitialAd mInterstitialAd;
    private DatabaseController CRUD;

    private  MapsDatabaseController mapsDatabaseController;


    private boolean bFirstRun = true;

    private String showAdv;

    private PhotoView photoView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent intent = getIntent();

        CoordinatorLayout coordinatorLayoutResult = findViewById(R.id.coordinatorLayoutResult2);

        // Obtain the FirebaseAnalytics instance.
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Firebase Implementation
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "XXX");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "favoritesMaps-Result");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        //final int message = Integer.parseInt(intent.getStringExtra(Main2Activity.EXTRA_MESSAGE));

        final String strExtraMapId = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE);

        showAdv = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE2);
        final String mapName = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE3);

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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, getString(R.string.compartilhar), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                shareImagebyProvider(0);
            }
        });

        FloatingActionButton fabFavorite = findViewById(R.id.fabFavorite);

        fabFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, R.string.favorites_adding_message, Snackbar.LENGTH_LONG)
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

    private void shareImagebyProvider(int iRepeat) {

        Bitmap bitmap;

        OutputStream output;

        if (bFirstRun) {
            verifyStoragePermissions(Result2Activity.this);
            bFirstRun = false;
        }

        // Retrieve the image selected in ImageView component
        photoView3.buildDrawingCache(true);

        bitmap = photoView3.getDrawingCache();

        photoView3.buildDrawingCache(false);

        // Find the SD Card path
        File filepath = Environment.getExternalStorageDirectory();
        //File filepath = getExternalCacheDir(); //Environment.geteDownloadCacheDirectory(); //ExternalStorageDirectory();

        // Create a new folder in SD Card
        File dir = new File(filepath.getAbsolutePath() + "/MetroMaps/");

        if (!dir.mkdirs()) {
            //Log.e(getString(R.string.error_share_mkdir),getString(R.string.error_share_mkdir2));
            System.out.println("Erro ao criar Diretorio via MKDIR");
        }

        // Create a name for the saved image
        File file = new File(dir, "MetroMap" + System.currentTimeMillis() + ".png");

        try {

            // Create a New instance of a Share Intent
            Intent intShare = new Intent(Intent.ACTION_SEND);

            // Type of file to share
            intShare.setType("image/jpeg");

            output = new FileOutputStream(file);

            // Workaround - in the first execution, bitmap is null
            if (iRepeat <= 0) {
                shareImagebyProvider(1);
            } else {

                // Compress into png format image from 0% - 100%
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);
                output.flush();
                output.close();

                // Locate the image to Share
                //Uri uri = Uri.fromFile(file);

                Uri photoUri = FileProvider.getUriForFile(Result2Activity.this, getString(R.string.file_provider_authority), file);


                // Pass the image into an Intnet
                intShare.putExtra(Intent.EXTRA_STREAM, photoUri);
                // temp permission for receiving app to read this file
                intShare.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // Show the social share chooser list
                //startActivityForResult(Intent.createChooser(share, "Roberval")),1);
                startActivity(Intent.createChooser(intShare,"Compartilhar"));
                //startActivity(Intent.createChooser(intShare, getString(R.string.compartilhar)));
                //startActivityForResult(Intent.createChooser(intShare, getString(R.string.compartilhar)), 1);

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block

            System.out.println("ERROR: " + e.getMessage());

            e.printStackTrace();
        }
    }

    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**
     * Checks if the app has permission to write to device storage
     * <p>
     * If the app does not has permission then the user will be prompted to grant permissions
     *
     * @param activity
     */
    private static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }




}


