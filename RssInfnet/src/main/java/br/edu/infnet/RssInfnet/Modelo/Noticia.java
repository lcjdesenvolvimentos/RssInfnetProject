package br.edu.infnet.RssInfnet.Modelo;

import android.widget.ImageView;

/**
 * Created by Luís on 18/11/13.
 */
public class Noticia {
    private String titulo;
    private String descricao;
    private String link;
    private ImageView img;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

}

