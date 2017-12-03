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

import org.json.JSONObject;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private Button srch;
    public static String code;
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

        result = (TextView)findViewById(R.id.result);
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
                //getWebsite();
            }
        });
    }

    /*private void getWebsite(){
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.google.com/search?q=nescau&tbm=isch").get();
            Element imageElement = doc.select("img").first();
            result.setText(""+imageElement.attr("abs:scr"));
        } catch (IOException e) {
            result.setText(e.toString());
        }
    }*/

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

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        callbackManager.onActivityResult(requestCode, resultCode, intent);
    }
}
