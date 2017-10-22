package br.com.ddmsoftware.metromaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.github.chrisbanes.photoview.PhotoView;
import java.util.List;
import static br.com.ddmsoftware.metromaps.MainActivity.EXTRA_MESSAGE2;
import static br.com.ddmsoftware.metromaps.R.color.colorBlueDark;
import static br.com.ddmsoftware.metromaps.R.color.colorGray;
import static br.com.ddmsoftware.metromaps.R.color.colorOrange;

public class ListViewActivity extends AppCompatActivity {

    private int iCountAdvertisement = 0;

    PhotoView photoView;

    private static final String EXTRA_MESSAGE = "br.com.ddmsoftware.metromaps.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        Intent intent = getIntent();

        TextView itemRow = (TextView)findViewById(R.id.tvItemRow);

        String sTagExtra = intent.getStringExtra(Main2Activity.EXTRA_MESSAGE);

        List<String> myList = null;

        Toast.makeText(getApplicationContext(),sTagExtra, Toast.LENGTH_SHORT).show();

        switch (sTagExtra) {

            case "América": {
                Pais paises = new Pais();
                myList = paises.getAllCountries(sTagExtra);
//                itemRow.setBackgroundColor(getResources().getColor(colorGray));


                break;
            }
            case "América do Sul": {
                Pais paises = new Pais();
                myList = paises.getAllCountries(sTagExtra);
  //              itemRow.setBackgroundColor(getResources().getColor(colorBlueDark));
                break;
            }
            case "Brasil": {
                Cidade cidades = new Cidade();
                myList = cidades.getAllCities(sTagExtra);
    //            itemRow.setBackgroundColor(getResources().getColor(colorOrange));
                break;
            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_row,R.id.tvItemRow, myList);
        ListView listView = (ListView) findViewById(R.id.myListView);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String item = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),item, Toast.LENGTH_SHORT).show();
                loadDataFromListClick(item);
            }
        });
    }


    private void loadDataFromListClick(String item) {

        int iMessage;

        switch (item) {

            case "Asia": {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            }
            case "América": {
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "América");
                startActivity(intent);
                break;
            }
            case "África": {
                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "África");
                startActivity(intent);
                break;
            }
            case "Europa": {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            }
            case "Oceania": {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
            }
            case "América do Sul": {

                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "América do Sul");
                startActivity(intent);
                break;
            }

            case "Brasil": {

                Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
                intent.putExtra(EXTRA_MESSAGE, "Brasil");
                startActivity(intent);
                break;
            }

            case "Belo Horizonte": {

                iMessage = R.drawable.mapa_belohorizonte;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Brasília": {

                iMessage = R.drawable.mapa_brasilia;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "São Paulo": {

                iMessage = R.drawable.mapa_sao_paulo5;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Rio de Janeiro": {

                iMessage = R.drawable.mapa_rio_janeiro;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Fortaleza": {

                iMessage = R.drawable.mapa_fortaleza;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Teresina": {

                iMessage = R.drawable.mapa_teresina;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Porto Alegre": {

                iMessage = R.drawable.mapa_porto_alegre;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Recife": {

                iMessage = R.drawable.mapa_recife;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Maceió": {

                iMessage = R.drawable.mapa_maceio;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "João Pessoa": {

                iMessage = R.drawable.mapa_joaopessoa;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Natal": {

                iMessage = R.drawable.mapa_natal;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }

            case "Salvador": {

                iMessage = R.drawable.mapa_salvador;
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
                //intent.putExtra(EXTRA_MESSAGE3, item.getTitle()); // Titulo do Mapa
                startActivity(intent);

                break;
            }








        }

    }

}
