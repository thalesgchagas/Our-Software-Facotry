package com.pds.caio.mercadogarcia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.pds.caio.mercadogarcia.MainActivity.code;

public class ProductActivity extends AppCompatActivity{
    private SQLiteDatabase db;
    private TextView nome_produto;
    private TextView preco;
    String productName;
    ImageView produto;
    Button button_map1;
    Button button_map2;
    Button button_map3;
    Button button_map4;
    String nome_format;

    @SuppressLint("WrongConstant")
    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //Mostrar o botão
        getSupportActionBar().setHomeButtonEnabled(true); //Ativar o botão
        getSupportActionBar().setTitle("Aplicativo Mercado Garcia");//Titulo para ser exibido na sua Action Bar em frente à seta


        db = openOrCreateDatabase( "banco.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);
        try {
            final String CREATE_TABLE_CONTAIN = "CREATE TABLE IF NOT EXISTS Produtos ("
                    + "barcode varchar(13) NOT NULL,"
                    + "name varchar(255) NOT NULL,"
                    + "price varchar(255) DEFAULT NULL,"
                    + "mercado int NOT NULL);";
            db.execSQL(CREATE_TABLE_CONTAIN);
            Toast.makeText(ProductActivity.this, "", Toast.LENGTH_LONG).show();
            String sql = "INSERT OR REPLACE INTO PRODUTOS (barcode,name,price,mercado) VALUES " +
                    "(7891025102113,'Água Mineral Bonafont sem Gás Garrafa 500 ml','3,50','001')," +
                    "(1932433130426,'Água Mineral Bonafont sem Gás Garrafa 500 ml','5,60','002')," +
                    "(1376335269460,'Água Mineral Bonafont sem Gás Garrafa 500 ml','4,00','003')," +
                    "(1381916707174,'Água Mineral Bonafont sem Gás Garrafa 500 ml','4,00','004')," +
                    "(1120912087460,'Água Mineral Lindoya sem Gás Garrafa 510 ml','3,50','001')," +
                    "(1571655878590,'Água Mineral Lindoya sem Gás Garrafa 510 ml','5,00','002')," +
                    "(1305012719084,'Água Mineral Lindoya sem Gás Garrafa 510 ml','5,60','003')," +
                    "(1983843960281,'Água Mineral Lindoya sem Gás Garrafa 510 ml','4,00','004')," +
                    "(1377143490645,'Água Mineral Bonafont sem Gás Garrafa 1500 ml','5,00','001')," +
                    "(1687142704923,'Água Mineral Bonafont sem Gás Garrafa 1500 ml','5,00','002')," +
                    "(1894703290114,'Água Mineral Bonafont sem Gás Garrafa 1500 ml','4,00','003')," +
                    "(1610954717215,'Água Mineral Bonafont sem Gás Garrafa 1500 ml','4,50','004')," +
                    "(1455897308058,'Água Mineral Ice sem Gás Garrafa 500 ml','4,00','001')," +
                    "(1941855753875,'Água Mineral Ice sem Gás Garrafa 500 ml','5,60','002')," +
                    "(7896008005610,'Água Mineral Ice sem Gás Garrafa 500 ml','5,00','003')," +
                    "(1559756353083,'Água Mineral Ice sem Gás Garrafa 500 ml','4,50','004')," +
                    "(7894900530001,'NESCAU LATA NESTLE 400ML','4,00','001')," +
                    "(7894900530001,'NESCAU LATA NESTLE 400ML','3,50','002')," +
                    "(7894900530001,'NESCAU LATA NESTLE 400ML','5,00','003')," +
                    "(7894900530001,'NESCAU LATA NESTLE 400ML','6,30','004')," +
                    "(1318948123603,'Água Mineral Minalba sem Gás Garrafa 1500 ml','8,00','001')," +
                    "(1205119682682,'Água Mineral Minalba sem Gás Garrafa 1500 ml','5,00','002')," +
                    "(1817323326236,'Água Mineral Minalba sem Gás Garrafa 1500 ml','4,50','003')," +
                    "(1951686708463,'Água Mineral Minalba sem Gás Garrafa 1500 ml','8,00','004')," +
                    "(1699982378631,'Água Mineral Lindoya com Gás Garrafa 510 ml','5,60','001')," +
                    "(1661905387623,'Água Mineral Lindoya com Gás Garrafa 510 ml','6,80','002')," +
                    "(1846277404576,'Água Mineral Lindoya com Gás Garrafa 510 ml','5,00','003')," +
                    "(1754051100876,'Água Mineral Lindoya com Gás Garrafa 510 ml','5,00','004');";
            db.execSQL(sql);
        }
        catch (Exception e) {
            Toast.makeText(ProductActivity.this, "ERROR "+e.toString(), Toast.LENGTH_LONG).show();
        }

        button_map1 = (Button) findViewById(R.id.btn_map1);
        button_map2 = (Button) findViewById(R.id.btn_map2);
        button_map3 = (Button) findViewById(R.id.btn_map3);
        button_map4 = (Button) findViewById(R.id.btn_map4);

        button_map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:-21.309970, -50.347489?q=-21.309970, -50.347489");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        button_map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:-21.302982, -50.374716?q=-21.302982, -50.374716");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        button_map3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:-21.304154, -50.377925?q=-21.304154, -50.377925");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

        button_map4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:-21.304957, -50.380684?q=-21.304957, -50.380684");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //Botão adicional na ToolBar
        switch (item.getItemId()) {
            case android.R.id.home:  //ID do seu botão (gerado automaticamente pelo android, usando como está, deve funcionar
                startActivity(new Intent(this, MainActivity.class));  //O efeito ao ser pressionado do botão (no caso abre a activity)
                finishAffinity();  //Método para matar a activity e não deixa-lá indexada na pilhagem
                break;
            default:break;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){ //Botão BACK padrão do android
        startActivity(new Intent(this, MainActivity.class)); //O efeito ao ser pressionado do botão (no caso abre a activity)
        finishAffinity(); //Método para matar a activity e não deixa-lá indexada na pilhagem
        return;
    }

    @Override
    public void onResume() {
        super.onResume();
        MainActivity mainActivity = new MainActivity();
        mainActivity.searchByBarCode(code, nome_produto);
    }

    private void consultaNomeProduto(){
        nome_produto = (TextView)findViewById(R.id.product_name);
        String columns[] = new String[]{"name", "mercado"};
        Cursor c = db.query("PRODUTOS", columns, "barcode = "+MainActivity.code, null, null, null, null);
        while (c.moveToNext()){
            int index1 = c.getColumnIndex("name");
            productName = c.getString(index1);
        }
        nome_produto.setText(productName);
        nome_format = productName.replaceAll(" ","_").toLowerCase();
        new _JSOUP().execute();
        c.close();
    }

    private void consultaPrecoProduto(String productName, int unidade){
        if(unidade == 1)
            preco = (TextView)findViewById(R.id.price_1);
        else
        if(unidade == 2)
            preco = (TextView)findViewById(R.id.price_2);
        else
        if(unidade == 3)
            preco = (TextView)findViewById(R.id.price_3);
        else
        if(unidade == 4)
            preco = (TextView)findViewById(R.id.price_4);

        String columns[] = new String[]{"price"};
        Cursor c = db.query("PRODUTOS", columns, "name = '"+productName+"' AND mercado = "+unidade, null, null, null, null);
        String productPrice = "";
        while (c.moveToNext()){
            int index = c.getColumnIndex("price");
            productPrice = c.getString(index);
        }
        preco.setText(productPrice);
        c.close();
    }
    public class _JSOUP extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            // can only grab first 100 results
            String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
            String url = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&q="+nome_format+"&gws_rd=cr";

            List<String> resultUrls = new ArrayList<>();

            try {
                Document doc = Jsoup.connect(url).userAgent(userAgent).referrer("https://www.google.com/").get();

                Elements elements = doc.select("div.rg_meta");

                JSONObject jsonObject;
                String buffer;
                for (Element element : elements) {
                    if (element.childNodeSize() > 0) {

                        jsonObject = (JSONObject) new JSONParser().parse(element.childNode(0).toString());
                        resultUrls.add((String) jsonObject.get("ou"));
                    }
                }
                buffer = resultUrls.get(0);
                return buffer;
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
        protected void onPostExecute(String res){
            super.onPostExecute(res);
            produto = (ImageView)findViewById(R.id.product_image);
            Picasso.with(ProductActivity.this).load(res).into(produto);
        }
    }
}
