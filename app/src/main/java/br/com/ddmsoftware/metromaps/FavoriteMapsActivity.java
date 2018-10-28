package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;

import static br.com.ddmsoftware.metromaps.Main2Activity.EXTRA_MESSAGE2;
import static br.com.ddmsoftware.metromaps.Main2Activity.EXTRA_MESSAGE3;

public class FavoriteMapsActivity extends AppCompatActivity {

    MapsAdapter mapsAdapter;
    MapsDatabaseController mapsDBController;

    private String sFilter = "";

    private FirebaseAnalytics mFirebaseAnalytics;
    private LinearLayout linearLayout;

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

                        String item = ((TextView) recyclerView.findViewHolderForAdapterPosition(position).itemView.findViewById(R.id.tvItemRow)).getText().toString();

                        openFavoriteMap(item);

                        Toast.makeText(getBaseContext(), "Cliquei no Recycler item " + position, Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getBaseContext(), "Pressionei no Recycler item " + position, Toast.LENGTH_LONG).show();

                    }
                })
        );
    }

    public void removeItem(long id) {

        mapsDBController.delete(id);
        mapsAdapter.swapCursor(mapsDBController.getAllItems());


    }

    public String getItem(RecyclerView recyclerView, int position){

        View v = recyclerView.getLayoutManager().findViewByPosition(position);

        return  v.toString();
    }


    private void loadDataFromRecytclerClick(String item, String sFilter) {


        if (sFilter.equals("COUNTRY")){

            Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
            intent.putExtra(EXTRA_MESSAGE, item);

            startActivity(intent);
        } else
        if (sFilter.equals("CITY")) {

        }
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

        Intent intent = new Intent(getApplicationContext(), Result2Activity.class);
        intent.putExtra(EXTRA_MESSAGE, String.valueOf(iMessage));
        intent.putExtra(EXTRA_MESSAGE2, ShowAdv);
        intent.putExtra(EXTRA_MESSAGE3, item);// Titulo do Mapa
        startActivity(intent);
    }

}