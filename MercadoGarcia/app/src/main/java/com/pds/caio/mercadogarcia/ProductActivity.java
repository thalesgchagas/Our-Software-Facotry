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

            String sql = "INSERT OR REPLACE INTO PRODUTOS (barcode,name,price,mercado) VALUES " +
                    "(7894900530001,'AGUA MINERAL CRYSTAL SEM GAS 500ML','1,50','001')," +
                    "(7894900530001,'AGUA MINERAL CRYSTAL SEM GAS 500ML','1,50','002')," +
                    "(7894900530001,'AGUA MINERAL CRYSTAL SEM GAS 500ML','2,00','003')," +
                    "(7894900530001,'AGUA MINERAL CRYSTAL SEM GAS 500ML','2,00','004')," +
                    "(7891035617959,'INSET SBP 450ML','9,69','001')," +
                    "(7891035617959,'INSET SBP 450ML','11,00','002')," +
                    "(7891035617959,'INSET SBP 450ML','9,69','003')," +
                    "(7891035617959,'INSET SBP 450ML','12,00','004')," +
                    "(7896060401313,'SABAO RIO TRADICIONAL 1K','4,19','001')," +
                    "(7896060401313,'SABAO RIO TRADICIONAL 1K','4,19','002')," +
                    "(7896060401313,'SABAO RIO TRADICIONAL 1K','4,19','003')," +
                    "(7896060401313,'SABAO RIO TRADICIONAL 1K','4,19','004')," +
                    "(7896067203033,'PILHA COMUM PANASONIC AA','4,50','001')," +
                    "(7896067203033,'PILHA COMUM PANASONIC AA','4,50','002')," +
                    "(7896067203033,'PILHA COMUM PANASONIC AA','4,50','003')," +
                    "(7896067203033,'PILHA COMUM PANASONIC AA','4,50','004')," +
                    "(7891000053508,'NESCAU LATA NESTLE 400ML','7,50','001')," +
                    "(7891000053508,'NESCAU LATA NESTLE 400ML','7,98','002')," +
                    "(7891000053508,'NESCAU LATA NESTLE 400ML','7,50','003')," +
                    "(7891000053508,'NESCAU LATA NESTLE 400ML','7,50','004')," +
                    "(7894900593709,'DEL VAL KAPO UVA 200ML','2,50','001')," +
                    "(7894900593709,'DEL VAL KAPO UVA 200ML','2,00','002')," +
                    "(7894900593709,'DEL VAL KAPO UVA 200ML','2,00','003')," +
                    "(7894900593709,'DEL VAL KAPO UVA 200ML','2,00','004')," +
                    "(7891095154210,'PIMENTA DO REINO KITANO 15G','1,89','001')," +
                    "(7891095154210,'PIMENTA DO REINO KITANO 15G','1,89','002')," +
                    "(7891095154210,'PIMENTA DO REINO KITANO 15G','1,89','003')," +
                    "(7891095154210,'PIMENTA DO REINO KITANO 15G','1,89','004');";
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
        consultaNomeProduto();
        consultaPrecoProduto(productName, 1);
        consultaPrecoProduto(productName, 2);
        consultaPrecoProduto(productName, 3);
        consultaPrecoProduto(productName, 4);
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
