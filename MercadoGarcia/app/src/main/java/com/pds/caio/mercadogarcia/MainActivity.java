package com.pds.caio.mercadogarcia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button srch;
    public static String code = "7891000053508";
    public static TextView result;
    LoginButton login_button;
    CallbackManager callbackManager;
    TextView tv_profile_name;
    ImageView iv_profile_pic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        tv_profile_name = (TextView) findViewById(R.id.tv_profile_name);
        iv_profile_pic = (ImageView) findViewById(R.id.iv_profile_pic);
        login_button = (LoginButton)findViewById(R.id.login_button);
        login_button.setReadPermissions(Arrays.asList("public_profile"));
        callbackManager = CallbackManager.Factory.create();

        loginWithFB();
        getData();

        btn = (Button)findViewById(R.id.scan_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScanActivity.class);
                startActivity(intent);
            }
        });

        srch = (Button)findViewById(R.id.search_button);
        srch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProductActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                getData();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException error) {
            }
        });
    }

    public void getData() {
        Bundle params = new Bundle();
        params.putString("fields", "id,name,picture.type(normal)");
        new GraphRequest(AccessToken.getCurrentAccessToken(), "me", params, HttpMethod.GET,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        if (response != null) {
                            try {
                                JSONObject data = response.getJSONObject();
                                if (data.has("name")){
                                    String name = data.getString("name");
                                    tv_profile_name.setText("Bem vindo(a), " + name + "!");
                                }
                                if (data.has("picture")) {
                                    String profilePicUrl = data.getJSONObject("picture").getJSONObject("data").getString("url");
                                    Picasso.with(MainActivity.this).load(profilePicUrl).into(iv_profile_pic);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).executeAsync();
    }

    public void searchByBarCode(String barCode, TextView resultView)
    {
        String result = "";
        InputStream inputStream = null;
        String serachByBarCode_url = "http://oursoftwarefactory.000webhostapp.com/searchByBarCode.php";

        try
        {
            URL url = new URL(serachByBarCode_url);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

            String data_string = URLEncoder.encode("barCode","UTF-8")+"="+URLEncoder.encode(barCode,"UTF-8");

            bufferedWriter.write(data_string);
            bufferedWriter.flush();
            bufferedWriter.close();

            outputStream.close();

            inputStream = httpURLConnection.getInputStream();

            httpURLConnection.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"),8);
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;

            while((line = bufferedReader.readLine()) != null)
            {
                stringBuilder.append(line + "\n");
            }
            inputStream.close();

            result = stringBuilder.toString();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try
        {
            String str = "";
            JSONArray jsonArray = new JSONArray(result);
            String[] unidades = {"Rua Azul, 826", "Aldo Cinquini, 254", "Aldo Cinquini, 541", "Aldo Cinquini, 789"};

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                str = str +
                        "Unidade: " + unidades[i] +
                        "\nProduto: " + jsonObject.getString("DESC_PRODUTO")+
                        "\nPreço: " + jsonObject.getString("PRECO") + "\n";
            }

            resultView.setText(str);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        callbackManager.onActivityResult(requestCode, resultCode, intent);
    }
}
