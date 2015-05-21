package com.example.mireproductor;

import java.io.IOException;

import android.support.v7.app.ActionBarActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends ActionBarActivity { 
	
	
    private MediaPlayer mpl;
    private int Cambio = 1;
    private final int Play = 1 ;
    private final int Pause = 2;
    private Button btplay, btstop, btatras, btsig;
    ImageView idImagen; 
	TextView textoEncima, textoDebajo; 
	int contador;

   	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		
		textoEncima = (TextView) findViewById(R.id.textView_superior);
		textoDebajo = (TextView) findViewById(R.id.textView_inferior);
		idImagen = (ImageView) findViewById(R.id.imageView_imagen);
		
		Bundle bundle = getIntent().getExtras();
		textoEncima.setText(bundle.getString("encima"));
		textoDebajo.setText(bundle.getString("debajo"));
		idImagen.setImageResource(getIntent().getExtras().getInt("imagen"));
		btplay=(Button)findViewById(R.id.btplay);
		btstop=(Button)findViewById(R.id.btstop);
		btatras = (Button)findViewById(R.id.btatras);
		btsig = (Button)findViewById(R.id.btsig);
		
		switch (bundle.getInt("posicion")) {
		case 0:
			contador = 0;
			mpl = MediaPlayer.create(this, R.raw.a_lalala_long);
			break;
		case 1:
			contador = 1;
			mpl = MediaPlayer.create(this, R.raw.este_frio);
			break;
		case 2:
			contador = 2;
			mpl = MediaPlayer.create(this, R.raw.gorilla);
			break;
		case 3:
			contador = 3;
			mpl = MediaPlayer.create(this, R.raw.mil);
			break;
		case 4:
			contador = 4;
			mpl = MediaPlayer.create(this, R.raw.zoe);
			break;
		}
		mpl.start();
	}
    public void btplay(View view){
        if(view == btplay){
            switch (Cambio){
                case Play:
                    mpl.start();
                    Cambio = Pause;
                    btplay.setText("PAUSE");
                    break;
                case Pause:
                   mpl.pause();
                    Cambio = Play;
                    btplay.setText("REPRODUCIR");
                    break;
            }
        }
    }
    public void btstop(View v){
        if(v == btstop){
            mpl.stop();
        }
    }
    public void btsig(View view) {
		if (contador == 4) {
		} else {
			mpl.pause();
			contador++;
			try {
				nexPreviuSong(view);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mpl.start();
		}
	}
	public void btatras(View view) {
		if (contador == 0) {
		} else {
			mpl.pause();
			contador--;
			try {
				nexPreviuSong(view);
			} catch (IOException e) {
				e.printStackTrace();
			}
			mpl.start();
		}
	}
	private void nexPreviuSong(View v) throws IOException {
		switch (contador) {
		case 0:
			contador = 0;
			textoEncima.setText("Bob Marley");
			textoDebajo.setText("A lalala long");
			idImagen.setImageResource(R.drawable.bob);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.a_lalala_long);
				mpl.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			break;
		case 1:
			contador = 1;
			textoEncima.setText("Kalimba");
			textoDebajo.setText("Este frio");
			idImagen.setImageResource(R.drawable.kalimba);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.este_frio);
				mpl.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} 
			break;
		case 2:
			contador = 2;
			textoEncima.setText("Bruno Mars");
			textoDebajo.setText("Gorilla");
			idImagen.setImageResource(R.drawable.bruno_mars);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.gorilla);
				mpl.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			break;
		case 3:
			contador = 3;
			textoEncima.setText("Good Charlotte");
			textoDebajo.setText("1979");
			idImagen.setImageResource(R.drawable.good_charlotte);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.mil);
				mpl.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			break;
		case 4:
			contador = 4;
			textoEncima.setText("Zoe");
			textoDebajo.setText("Luna");
			idImagen.setImageResource(R.drawable.zoe);
			try {
				mpl.reset();
				mpl = MediaPlayer.create(this, R.raw.zoe);
				mpl.prepare();
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			break;
			}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
