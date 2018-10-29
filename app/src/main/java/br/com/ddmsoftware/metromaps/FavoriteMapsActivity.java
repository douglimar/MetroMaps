package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Objects;

import static br.com.ddmsoftware.metromaps.Main2Activity.EXTRA_MESSAGE2;
import static br.com.ddmsoftware.metromaps.Main2Activity.EXTRA_MESSAGE3;

public class FavoriteMapsActivity extends AppCompatActivity {

    private MapsAdapter mapsAdapter;
    private MapsDatabaseController mapsDBController;

    private String sFilter = "";



    private static final String EXTRA_MESSAGE = "br.com.ddmsoftware.metromaps.MESSAGE";
    private int iCountAdvertisement = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_maps);

        final RecyclerView recyclerView = findViewById(R.id.recyclerViewFavorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mapsDBController = new MapsDatabaseController(this, true);

        mapsAdapter = new MapsAdapter(this, mapsDBController.getAllItems());

        recyclerView.setAdapter(mapsAdapter);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        //Firebase Implementation
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "XXX");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "favoritesMaps-Result");
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                removeItem((long) viewHolder.itemView.getTag());
            }
        }).attachToRecyclerView(recyclerView);

        // Construindo OnClickListener para o RecyclerView

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getBaseContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //String item = adapterView.getItemAtPosition(i).toString();

                        String item = ((TextView) Objects.requireNonNull(Objects.requireNonNull(recyclerView.findViewHolderForAdapterPosition(position)).itemView).findViewById(R.id.tvItemRow)).getText().toString();

                        openFavoriteMap(item);

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getBaseContext(), getString(R.string.excluir_mapa_favorito), Toast.LENGTH_LONG).show();

                    }
                })
        );


        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {

            //Intent intent = new Intent(this, AdmobActivity.class);
            //startActivity(intent);

            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }

    private void removeItem(long id) {

        mapsDBController.delete(id);
        mapsAdapter.swapCursor(mapsDBController.getAllItems());


    }

    public String getItem(RecyclerView recyclerView, int position){

        View v = Objects.requireNonNull(recyclerView.getLayoutManager()).findViewByPosition(position);

        assert v != null;
        return  v.toString();
    }

    private void openFavoriteMap(String item) {
        int iMessage;

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

        Intent intent = new Intent(getApplicationContext(), FavoriteMapsResultActivity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(iMessage));
        intent.putExtra(EXTRA_MESSAGE2, ShowAdv);
        intent.putExtra(EXTRA_MESSAGE3, item);// Titulo do Mapa
        startActivity(intent);
    }

}