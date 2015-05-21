package com.example.mireproductor;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayList<Reproductor> pista = new ArrayList<Reproductor>();  
        
        pista.add(new Reproductor(R.drawable.kalimba, "Kalimba", "Este frio"));
        pista.add(new Reproductor(R.drawable.bob, "Bob Marley", "A lalala long"));
        pista.add(new Reproductor(R.drawable.bruno_mars, "Bruno Mars", "Gorilla"));
        pista.add(new Reproductor(R.drawable.good_charlotte, "Good Charlotte","1979"));
        pista.add(new Reproductor(R.drawable.zoe, "Zoe","Luna"));
        
        lista = (ListView) findViewById(R.id.ListView_listado);
        lista.setAdapter(new AdaptadorActivity (this, R.layout.activity_adaptador, pista){
			
        	@Override
			public void onEntrada(Object activity_adaptador , View view) {
		        if (activity_adaptador != null) {
		            TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textView_superior); 
		            if (texto_superior_entrada != null) 
		            	texto_superior_entrada.setText(((Reproductor) activity_adaptador).get_textoEncima()); 
		              
		            TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textView_inferior); 
		            if (texto_inferior_entrada != null)
		            	texto_inferior_entrada.setText(((Reproductor) activity_adaptador).get_textoDebajo()); 
		              
		            ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imageView_imagen); 
		            if (imagen_entrada != null)
		            	imagen_entrada.setImageResource(((Reproductor) activity_adaptador).get_idImagen());
		           
		        }
		        
			}
		});
        
        lista.setOnItemClickListener(new OnItemClickListener() { 
			@Override
			public void onItemClick(AdapterView<?> pariente, View view, int posicion, long id) {
				Reproductor elegido = (Reproductor) pariente.getItemAtPosition(posicion); 
                
                CharSequence texto = "Abriendo Pista de: " + elegido.get_textoEncima();
                Toast toast = Toast.makeText(MainActivity.this, texto, Toast.LENGTH_LONG);
                toast.show();
                nuevo(pariente,view,posicion,id);   
			}
        });
    }    
        public void nuevo(AdapterView<?> pariente, View view, int posicion, long id){
    	Intent intent = new Intent(this, ItemActivity.class );
    	Reproductor item = (Reproductor) lista.getAdapter().getItem(posicion);
    	intent.putExtra("imagen",item.get_idImagen());
    	intent.putExtra("debajo",item.get_textoDebajo().toString());
    	intent.putExtra("encima",item.get_textoEncima().toString()); 
    	intent.putExtra("posicion",posicion);
        startActivity(intent);
       
 
    }	

}


