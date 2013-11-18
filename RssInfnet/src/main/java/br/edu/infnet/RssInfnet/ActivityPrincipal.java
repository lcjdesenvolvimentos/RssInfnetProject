package br.edu.infnet.RssInfnet;

/**
 * Created by Luís on 18/11/13.
 */
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import br.edu.infnet.RssInfnet.Modelo.*;



public class ActivityPrincipal extends Activity {

    private Intent i;
    private Button btnNoticias;
    private Button btnJogosAnteriores;
    private Button btnProximasNoticias;
    private Button btnTabela;
    private TextView lblCabecalho;
    List<Noticia> news;
    ArrayAdapter<Noticia> adapter;
    private ImageView escudoprincipal;

    /** Called when the activity ais first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activityprincipal);
        i = getIntent();

        lblCabecalho = (TextView) findViewById(R.id.lblCabecalho);
        btnNoticias = (Button) findViewById(R.id.btnNoticias);
        btnJogosAnteriores = (Button) findViewById(R.id.btnAnteriores);
        btnProximasNoticias = (Button) findViewById(R.id.btnProximos);
        btnTabela = (Button) findViewById(R.id.btnTabela);
        escudoprincipal = (ImageView) findViewById(R.id.logoprincipal);

        lblCabecalho.setText(Canal.feeds[i.getIntExtra("FeedSelecionado", 0)]);
        escudoprincipal.setImageResource(Canal.imagesGrande[i.getIntExtra("FeedSelecionado", 0)]);

        btnNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ActivityPrincipal.this, ActivityNoticias.class);
                in.putExtra("FeedSelecionado", i.getIntExtra("FeedSelecionado",0));
                startActivity(in);
            }
        });

        btnJogosAnteriores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent(ActivityPrincipal.this, ActivityJogos.class);
//                in.putExtra("TipoJogos", 0); //0 - Anteriores, 1 - Próximos
//                in.putExtra("TimeSelecionado", i.getIntExtra("TimeSelecionado",0));
//                startActivity(in);
            }
        });

        btnProximasNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent in = new Intent(ActivityPrincipal.this, ActivityJogos.class);
//                in.putExtra("TipoJogos", 1); //0 - Anteriores, 1 - Próximos
//                in.putExtra("TimeSelecionado", i.getIntExtra("TimeSelecionado", 0));
//                startActivity(in);
            }
        });

        btnTabela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(ActivityPrincipal.this, ActivityTabela.class);
//                startActivity(i);
            }
        });


    }

}

