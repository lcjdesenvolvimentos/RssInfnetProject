package br.edu.infnet.RssInfnet.Modelo;

/**
 * Created by Luís on 18/11/13.
 */
import java.util.List;


import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import br.edu.infnet.RssInfnet.ImageLoader;
import  br.edu.infnet.RssInfnet.R;

public class NoticiaAdapter extends BaseAdapter{
    private Context context;
    private List<Noticia> noticiaList;

    // Modificação
    public ImageLoader imageLoader;
    private String[] data;
    private static LayoutInflater inflater=null;
    private Activity activity;

    public NoticiaAdapter(Context context, List<Noticia> statelist){
        this.context = context;
        this.noticiaList = statelist;
    }

    public NoticiaAdapter(Activity a, String[] d) {
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        imageLoader=new ImageLoader(activity.getApplicationContext());
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
//        // Recupera o estado da posição atual
//        Noticia noticia = noticiaList.get(position);
//
//        // Cria uma instância do layout XML para os objetos correspondentes
//        // na View
//        LayoutInflater inflater;
//        inflater = (LayoutInflater)
//                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view = inflater.inflate(R.layout.listanoticias, null);
//
//        // Título
//        TextView textNoticia = (TextView)view.findViewById(R.id.txtTitulo);
//        textNoticia.setText(noticia.getTitulo());
//
//        //Corpo da notícia.
//        TextView textCorpo = (TextView)view.findViewById(R.id.txtCorpo);
//
//        //Remove o link.
//        String Desc = noticia.getDescricao();
//        Integer posTagString = Desc.indexOf("</a>");
//        Desc = Desc.substring(posTagString + 4, Desc.length());
//
//        textCorpo.setText(Html.fromHtml(Desc));
//
//        // Imagem
//        ImageView img = (ImageView)view.findViewById(R.id.img);
//        img = noticia.getImg();
//
//        return view;


        View vi=convertView;
        ViewHolder holder;
        if(convertView==null){
            vi = inflater.inflate(R.layout.listanoticias, null);
            holder=new ViewHolder();
            holder.text=(TextView)vi.findViewById(R.id.txtTitulo );
            holder.image=(ImageView)vi.findViewById(R.id.image);
            vi.setTag(holder);
        }
        else
            holder=(ViewHolder)vi.getTag();

        holder.text.setText("item "+position);
        holder.image.setTag(data[position]);
        imageLoader.DisplayImage(data[position], activity, holder.image);
        return vi;
    }

    public static class ViewHolder{
        public TextView text;
        public ImageView image;
    }
}
