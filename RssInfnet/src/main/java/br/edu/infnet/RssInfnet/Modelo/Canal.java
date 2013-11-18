package br.edu.infnet.RssInfnet.Modelo;

import br.edu.infnet.RssInfnet.R;

/**
 * Created by Luís on 18/11/13.
 */
public class Canal {
    private String nome;
    private int logo;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getLogo() {
        return logo;
    }
    public void setLogo(int escudo) {
        this.logo = escudo;
    }

    public static String feeds[] =
            {"Todas as Notícias",
                    "Brasil",
                    "Carros",
                    "Ciência e Saúde",
                    "Concursos e Emprego",
                    "Economia",
                    "Educação",
                    "Loterias",
                    "Mundo",
                    "Música",
                    "Natureza",
                    "Planeta Bizarro",
                    "Política",
                    "Pop & Arte",
                    "Tecnologia e Games",
                    "Turismo e Viagem"
            };

    public static int[] images = new int[]{
            R.drawable.todasnoticias,
            R.drawable.brasilnoticia,
            R.drawable.carros,
            R.drawable.cienciaesaude,
            R.drawable.concursosemprego,
            R.drawable.economia,
            R.drawable.educacao,
            R.drawable.loterias,
            R.drawable.mundo,
            R.drawable.musica,
            R.drawable.natureza,
            R.drawable.planetabizarro,
            R.drawable.politica,
            R.drawable.poparte,
            R.drawable.tecnologiagames,
            R.drawable.turismoviagem
    };

    public static int[] imagesGrande = new int[]{
            R.drawable.todasnoticiasgrande,
            R.drawable.brasilnoticiagrande,
            R.drawable.carrosgrande,
            R.drawable.cienciaesaudegrande,
            R.drawable.concursosempregogrande,
            R.drawable.economiagrande,
            R.drawable.educacaogrande,
            R.drawable.loteriasgrande,
            R.drawable.mundogrande,
            R.drawable.musicagrande,
            R.drawable.naturezagrande,
            R.drawable.planetabizarrogrande,
            R.drawable.politicagrande,
            R.drawable.popartegrande,
            R.drawable.tecnologiagamesgrande,
            R.drawable.turismoviagemgrande
    };

}
