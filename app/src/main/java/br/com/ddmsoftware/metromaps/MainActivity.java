package br.com.ddmsoftware.metromaps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Cria uma variavel para fazer o transporte de valores entre intents
    public static final String EXTRA_MESSAGE = new String ("br.com.ddmsoftware.metromaps.MESSAGE");
    public static final String EXTRA_MESSAGE2 = new String ("br.com.ddmsoftware.metromaps.MESSAGE2");
    public static final String EXTRA_MESSAGE3 = new String ("br.com.ddmsoftware.metromaps.MESSAGE3");

    int iMessage;
    int iCountAdvertisement = 0;
    String mapName;

    PhotoView photoView;

    DatabaseController CRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        photoView = (PhotoView) findViewById(R.id.photo_viewMainActivity);

        CRUD = new DatabaseController(this);

        //CRUD.insertData("");

        checkDefaultMap();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        // Create a AdView
        // Load Advertisement Banner
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

    }

    public void onResume(){
        super.onResume();
        checkDefaultMap();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main22, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id) {

            case R.id.nav_belohorizonte:
                iMessage = R.drawable.mapa_belohorizonte;
                break;
            case R.id.nav_brasilia:
                iMessage = R.drawable.mapa_brasilia;
                break;
            case R.id.nav_joaopessoa:
                iMessage = R.drawable.mapa_joaopessoa;
                break;
            case R.id.nav_maceio:
                iMessage = R.drawable.mapa_maceio;
                break;
            case R.id.nav_natal:
                iMessage = R.drawable.mapa_natal;
                break;
            case R.id.nav_recife:
                iMessage = R.drawable.mapa_recife;
                break;
            case R.id.nav_buenos_aires:
                iMessage = R.drawable.mapa_buenos_aires;
                break;
            case R.id.nav_sao_paulo_sem_legenda:
                iMessage = R.drawable.mapa_sao_paulo;
                break;
            case R.id.nav_sao_paulo:
                iMessage = R.drawable.mapa_sao_paulo2;
                break;
            case R.id.nav_rio_janeiro:
                iMessage = R.drawable.mapa_rio_janeiro;
                break;
            case R.id.nav_teresina:
                iMessage = R.drawable.mapa_teresina;
                break;
            case R.id.nav_fortaleza:
                iMessage = R.drawable.mapa_fortaleza;
                break;
            case R.id.nav_porto_alegre:
                iMessage = R.drawable.mapa_porto_alegre;
                break;
            case R.id.nav_salvador:
                iMessage = R.drawable.mapa_salvador;
                break;
        }

        iCountAdvertisement++;

        String ShowAdv ;

        if (iCountAdvertisement >=3) {

            ShowAdv = "SHOW_ADV";
            iCountAdvertisement =0;

        }
        else
            ShowAdv = "NO_SHOW";

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(iMessage));
        intent.putExtra(EXTRA_MESSAGE2, ShowAdv);
        intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private void writeToFile(int data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("config.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    private String readFromFile(Context context) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private int readIntFromFile(Context context) {

        int ret = 0;

        try {
            InputStream inputStream = context.openFileInput("config.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = Integer.parseInt(stringBuilder.toString());
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private void checkDefaultMap() {


        final Cursor resultSet = CRUD.loadData();

        int iTotReg = resultSet.getCount();

        Toast.makeText(this.getApplicationContext(), "Tot.Reg:"  + iTotReg, Toast.LENGTH_SHORT ).show();

        if ((resultSet!=null)  && (iTotReg>0)){
            resultSet.moveToFirst();

            do {

                if (resultSet.getString(0).equals(""))
                    photoView.setImageResource(R.drawable.wallpaper3);
                else
                    photoView.setImageResource(resultSet.getInt(0));


            }while (resultSet.moveToNext());

        }

        resultSet.close();
    }
}
