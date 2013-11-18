package br.edu.infnet.RssInfnet.Modelo;

/**
 * Created by Luís on 18/11/13.
 */
import java.util.List;


import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import  br.edu.infnet.RssInfnet.R;

public class NoticiaAdapter extends BaseAdapter{
    private Context context;
    private List<Noticia> noticiaList;

    public NoticiaAdapter(Context context, List<Noticia> statelist){
        this.context = context;
        this.noticiaList = statelist;
    }

    @Override
    public int getCount() {
        return noticiaList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticiaList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recupera o estado da posição atual
        Noticia noticia = noticiaList.get(position);

        // Cria uma instância do layout XML para os objetos correspondentes
        // na View
        LayoutInflater inflater;
        inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.listanoticias, null);

        // Título
        TextView textNoticia = (TextView)view.findViewById(R.id.txtTitulo);
        textNoticia.setText(noticia.getTitulo());

        //Corpo da notícia.
        TextView textCorpo = (TextView)view.findViewById(R.id.txtCorpo);

        //Remove o link.
        String Desc = noticia.getDescricao();
        Integer posTagString = Desc.indexOf("</a>");
        Desc = Desc.substring(posTagString + 4, Desc.length());

        textCorpo.setText(Html.fromHtml(Desc));

        // Imagem
        ImageView img = (ImageView)view.findViewById(R.id.img);
        img.setImageResource(noticia.getImg());

        return view;
    }
}
