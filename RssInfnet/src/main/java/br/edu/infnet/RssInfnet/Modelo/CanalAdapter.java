package br.edu.infnet.RssInfnet.Modelo;

import java.util.List;

import br.edu.infnet.RssInfnet.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Luís on 18/11/13.
 */
public class CanalAdapter extends BaseAdapter {

    private Context context;
    private List<Canal> canalList;

    public CanalAdapter(Context context, List<Canal> statelist){
        this.context = context;
        this.canalList = statelist;
    }

    @Override
    public int getCount() {
        return canalList.size();
    }

    @Override
    public Object getItem(int position) {
        return canalList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o canal da posição atual
        Canal canal = canalList.get(position);

        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listacanais, null);

        // Nome do Feed
        TextView textTime = (TextView)view.findViewById(R.id.txtCanal);
        textTime.setText(canal.getNome());

        // Logo
        ImageView img = (ImageView)view.findViewById(R.id.imgLogo);
        img.setImageResource(canal.getLogo());

        return view;
    }

}
