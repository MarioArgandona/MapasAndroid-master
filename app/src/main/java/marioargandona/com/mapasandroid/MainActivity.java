package marioargandona.com.mapasandroid;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import marioargandona.com.mapasandroid.adapter.CustomGridViewAdapter;
import marioargandona.com.mapasandroid.constantes.Constantes;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<Item> gridArray = new ArrayList<Item>();
    CustomGridViewAdapter customGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //preparaPrincipal();
    }


    /*private void preparaPrincipal()
    {
        Bitmap lunaParc = BitmapFactory.decodeResource(this.getResources(), R.drawable.lunaparc);
        Bitmap lirios = BitmapFactory.decodeResource(this.getResources(), R.drawable.lirios);
        Bitmap guadalupe = BitmapFactory.decodeResource(this.getResources(), R.drawable.guadalupe);
        Bitmap hambre = BitmapFactory.decodeResource(this.getResources(), R.drawable.hambre);

        gridArray.add(new Item(lunaParc,"LunaParc"));
        gridArray.add(new Item(lirios,"Lago de los Lirios"));
        gridArray.add(new Item(guadalupe,"Lago de Guadalupe"));
        gridArray.add(new Item(hambre,"Calle del Hambre"));

        gridView = (GridView) findViewById(R.id.gridView1);
        customGridAdapter = new CustomGridViewAdapter(this, R.layout.row_grid, gridArray);
        gridView.setAdapter(customGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),gridArray.get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });



    }*/


    public void mostrarUbicacion(View v)
    {
        int idImageView = v.getId();

        switch(idImageView)
        {
            case R.id.imvLunaParc:
                crearIntentMapa(MapaHogar.class , Constantes.latitudLunaParc , Constantes.longitudLunaParc , R.drawable.waze1 , Constantes.lunaparc);
                break;

            case R.id.imvLirios:
                crearIntentMapa(MapaHogar.class , Constantes.latitudLagoLirios , Constantes.longitudLagoLirios , R.drawable.waze2 , Constantes.lagodeloslirios);
                break;

            case R.id.imvGuadalupe:
                crearIntentMapa(MapaHogar.class , Constantes.latitudLagoGuadalupe , Constantes.longitudLagoGuadalupe , R.drawable.waze3 , Constantes.lagodeguadalupe);
                break;

            case R.id.imvHambre:
                crearIntentMapa(MapaHogar.class , Constantes.latitudCalleHambre , Constantes.longitudCalleHambre , R.drawable.waze4 , Constantes.calledelhambre);
                break;
        }
    }



    public void crearIntentMapa(Class claseActividad , String latitud , String longitud , int tipoImagen , String descripcion)
    {
        Intent intentMapa = new Intent(this , claseActividad);
        intentMapa.putExtra("latitud" , latitud);
        intentMapa.putExtra("longitud" , longitud);
        intentMapa.putExtra("icono" , tipoImagen);
        intentMapa.putExtra("descripcion" , descripcion);
        startActivity(intentMapa);
    }


}
