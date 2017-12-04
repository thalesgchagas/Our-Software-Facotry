package com.pds.caio.mercadogarcia;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static com.pds.caio.mercadogarcia.MainActivity.JSON_STRING;

/**
 * Created by Thales on 04/12/2017.
 */

public class ApiConnector extends AsyncTask<Void, Void, String>{
    String json_url;
    InputStream inputStream;

    @Override
    protected void onPreExecute()
    {
        json_url = "http://oursoftwarefactory.000webhostapp.com/searchByBarCode.php";
    }

    @Override
    protected String doInBackground(Void... voids) {
       try {
            URL url = new URL(json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
           inputStream = httpURLConnection.getInputStream();
           BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
           StringBuilder stringBuilder = new StringBuilder();
           while ((JSON_STRING = bufferedReader.readLine()) != null)
           {
               stringBuilder.append(JSON_STRING+"\n");
           }

           bufferedReader.close();
           inputStream.close();
           httpURLConnection.disconnect();

           return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
           e.printStackTrace();
       }

       return null;
    }

    @Override
    protected void onProgressUpdate(Void... values)
    {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result)
    {
        JSON_STRING = result;
    }
}
