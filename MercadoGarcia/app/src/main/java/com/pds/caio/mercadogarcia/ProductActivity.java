package com.pds.caio.mercadogarcia;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProductActivity extends AppCompatActivity{
    private SQLiteDatabase db;
    private TextView nome_produto;
    private TextView preco;
    private String productName;
    Button button_map1;
    Button button_map2;
    Button button_map3;
    Button button_map4;

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
                    "(7894900530001,'Água Mineral Crystal com Gás Garrafa 500 ml','4,00','001')," +
                    "(7894900530001,'Água Mineral Crystal com Gás Garrafa 500 ml','3,50','002')," +
                    "(7894900530001,'Água Mineral Crystal com Gás Garrafa 500 ml','5,00','003')," +
                    "(7894900530001,'Água Mineral Crystal com Gás Garrafa 500 ml','6,30','004')," +
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
        button_map1 = (Button) findViewById(R.id.btn_map2);
        button_map1 = (Button) findViewById(R.id.btn_map3);
        button_map1 = (Button) findViewById(R.id.btn_map4);

        button_map1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri gmmIntentUri = Uri.parse("geo:-21.309970, -50.347489");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null){
                    startActivity(mapIntent);
                }
            }
        });

        button_map2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   callExternalMap(-21.302982, -50.374716);
            }
        });

        button_map3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // callExternalMap(-21.304154, -50.377925);
            }
        });

        button_map4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //callExternalMap(-21.304957, -50.380684);
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
        String unityName = "";
        String name_and_unity;
        while (c.moveToNext()){
            int index1 = c.getColumnIndex("name");
            productName = c.getString(index1);
            int index2 = c.getColumnIndex("mercado");
            unityName = c.getString(index2);
        }
        name_and_unity = productName +" - Unidade "+unityName;
        nome_produto.setText(name_and_unity);
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

    /*
    private void callExternalMap(LatLng origem, LatLng destino) {
        try {
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?saddr=" + origem.latitude + "," + origem.longitude + "&daddr=" + destino.latitude + "," + destino.longitude));

            intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));

            startActivity(intent);
        } catch (Exception ex) {
            Toast.makeText(this, "Verifique se o Google Maps está instalado em seu dispositivo", Toast.LENGTH_SHORT).show();
        }
    }*/
}
