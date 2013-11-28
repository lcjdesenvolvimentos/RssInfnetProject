package br.edu.infnet.RssInfnet;

import java.util.ArrayList;
import java.util.List;

import br.edu.infnet.RssInfnet.Modelo.Canal;
import br.edu.infnet.RssInfnet.Modelo.CanalAdapter;

import br.edu.infnet.RssInfnet.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class ActivityCanais extends ListActivity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Canal> canalList = new ArrayList<Canal>();

        for (int i = 0; i < Canal.feeds.length; i++) {
            Canal canal = new Canal();
            canal.setNome(Canal.feeds[i]); //nome
            canal.setLogo(Canal.images[i]); //logo

            canalList.add(canal);
        }
        this.getListView().setDividerHeight(0);
        this.setListAdapter(new CanalAdapter(this, canalList));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(ActivityCanais.this, ActivityNoticias.class);
        i.putExtra("FeedSelecionado", i.getIntExtra("FeedSelecionado",position));
        startActivity(i);


    }
}
