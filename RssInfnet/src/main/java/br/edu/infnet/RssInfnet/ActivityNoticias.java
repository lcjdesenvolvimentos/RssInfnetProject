package br.edu.infnet.RssInfnet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import br.edu.infnet.RssInfnet.Modelo.Canal;
import  br.edu.infnet.RssInfnet.Modelo.Noticia;
import  br.edu.infnet.RssInfnet.Modelo.NoticiaAdapter;


public class ActivityNoticias extends ListActivity {
    /**
     * @see android.app.Activity#onCreate(Bundle)
     */

    private Intent i;
    List<Noticia> noticiaList;
    ArrayAdapter<Noticia> adapter;
    private Integer nFeedEscolhido;
    private String sLinkRSS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        noticiaList = null;
        //NOTICIAS
        noticiaList = new ArrayList<Noticia>();

        i = getIntent();
        nFeedEscolhido = i.getIntExtra("FeedSelecionado",0);

        if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[0])){
            sLinkRSS = "http://g1.globo.com/dynamo/rss2.xml"; // Todas as Noticias
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[1])){
            sLinkRSS = "http://g1.globo.com/dynamo/brasil/rss2.xml"; // Brasil
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[2])){
            sLinkRSS = "http://g1.globo.com/dynamo/carros/rss2.xml"; // Carros
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[3])){
            sLinkRSS = "http://g1.globo.com/dynamo/ciencia-e-saude/rss2.xml"; // Ciencia e Saude
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[4])){
            sLinkRSS = "http://g1.globo.com/dynamo/concursos-e-emprego/rss2.xml"; // Consursos e Emprego
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[5])){
            sLinkRSS = "http://g1.globo.com/dynamo/economia/rss2.xml"; // Economia
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[6])){
            sLinkRSS = "http://g1.globo.com/dynamo/educacao/rss2.xml"; // Educação
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[7])){
            sLinkRSS = "http://g1.globo.com/dynamo/loterias/rss2.xml"; // Loterias
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[8])){
            sLinkRSS = "http://g1.globo.com/dynamo/mundo/rss2.xml"; // Mundo
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[9])){
            sLinkRSS = "http://g1.globo.com/dynamo/musica/rss2.xml"; //Musica
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[10])){
            sLinkRSS = "http://g1.globo.com/dynamo/natureza/rss2.xml"; // Natureza
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[11])){
            sLinkRSS = "http://g1.globo.com/dynamo/planeta-bizarro/rss2.xml"; // Planeta Bizarro
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[12])){
            sLinkRSS = "http://g1.globo.com/dynamo/politica/mensalao/rss2.xml"; // Política
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[13])){
            sLinkRSS = "http://g1.globo.com/dynamo/pop-arte/rss2.xml"; // Pop & Arte
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[14])){
            sLinkRSS = "http://g1.globo.com/dynamo/tecnologia/rss2.xml"; // Tecnologia e Games
        }else if (Canal.feeds[nFeedEscolhido].equals(Canal.feeds[15])){
            sLinkRSS = "http://g1.globo.com/dynamo/turismo-e-viagem/rss2.xml"; // Turismo e Viagem
        }

        if (sLinkRSS.equals("")){
            Toast.makeText( this, "O feed selecionado não possui RSS", 1000 ).show();
            Intent i = new Intent(this, ActivityCanais.class);
            startActivity(i);
        }else{
            // Criando AsyncTask que buscará o RSS da globo.com
            new RssAsyncTask().execute(sLinkRSS);
        }

        //this.setListAdapter(new NoticiaAdapter(this, noticiaList));

        //Bitmap bitmap = getImage("http://globoesporte.globo.com/ImageShow/0,,197044,00.gif");
        //img = (ImageView) findViewById(R.id.img);
        //img.setImageBitmap(bitmap);
    }

    // Método que lê o XML do RSS
    private List<Noticia> readXML(InputStream is){

        try {
            // Criando os objetos que representam o XML
            DocumentBuilderFactory factory =
                    DocumentBuilderFactory.newInstance();

            DocumentBuilder builder =
                    factory.newDocumentBuilder();
            Document xmlDocument = builder.parse(is);

            // Cada notícia é representada pela tag <item>
            // Aqui obtemos a lista de nós com essa tag
            NodeList posts =
                    xmlDocument.getElementsByTagName("item");

            // Vamos iterar sobre a lista de itens
            String titulo = null, descricao = null,
                    link = null, imageLink = null;

            for (int i = 0; i < posts.getLength(); i++) {
                Node post = posts.item(i);

                // Cada nó ITEM tem os filhos:
                // TITLE, DESCRIPTION e LINK
                NodeList postInfo = post.getChildNodes();
                for (int j = 0; j < postInfo.getLength(); j++){
                    Node info = postInfo.item(j);

                    if ("title".equals(info.getNodeName())){
                        titulo = info.getTextContent();

                    } else if ("link".equals(
                            info.getNodeName())){
                        link = info.getTextContent();

                    } else if ("description".equals(
                            info.getNodeName())){
                        descricao = info.getTextContent();

                        if (descricao.toLowerCase().contains("<img"))
                        {
                            String[] palavras = descricao.split("'");
                            imageLink = "";
                            for(String elemento: palavras){

                                if (elemento.toLowerCase().contains(".jpg"))
                                {
                                    imageLink = elemento;
                                }
                            }

                        }
                    }



                }
                // Com as informações das tags, cria o
                // objeto notícia e adiciona na lista
                ImageView imagem = (ImageView) findViewById(R.id.img);

                if (!TextUtils.isEmpty((imageLink)))
                {
//                    Bitmap figura = DownloadImageTask((ImageView) findViewById(R.id.img)).execute(imageLink).get();
//                    imagem.setImageBitmap(figura);
                }

                Noticia noticia = new Noticia();
                noticia.setDescricao(descricao);
                noticia.setLink(link);
                noticia.setTitulo(titulo);
                noticia.setImg(imagem);
                noticiaList.add(noticia);

            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return noticiaList;
    }



    // A AsyncTask realiza a comunicação em background
    class RssAsyncTask extends
            AsyncTask<String, Void, List<Noticia>>{

        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Antes de iniciar as tarefas, mostra o dialog
            dialog = ProgressDialog.show(
                    ActivityNoticias.this,
                    "Aguarde", "Baixando RSS");
        }

        @Override
        protected List<Noticia> doInBackground(
                String... params) {

            List<Noticia> lista = null;
            HttpURLConnection conexao = null;
            InputStream is = null;

            try {
                URL url = new URL(params[0]);
                conexao = (HttpURLConnection)
                        url.openConnection();
                conexao.connect();

                is = conexao.getInputStream();
                lista = readXML(is);

            } catch (Throwable t){
                t.printStackTrace();
            } finally {
                try {
                    if (is != null) is.close();
                    if (conexao != null) conexao.disconnect();
                } catch (Throwable t){
                }
            }
            return lista;
        }

        @Override
        protected void onPostExecute(List<Noticia> result){
            super.onPostExecute(result);
            dialog.dismiss();
            ActivityNoticias.this.setListAdapter(new NoticiaAdapter(ActivityNoticias.this, noticiaList));
        }



    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("ErroNessaBosta", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }

}