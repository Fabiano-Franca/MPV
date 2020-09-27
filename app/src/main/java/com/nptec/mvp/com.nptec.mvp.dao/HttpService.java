package com.nptec.mvp.com.nptec.mvp.dao;

import android.os.AsyncTask;
import android.util.Log;
import com.nptec.mvp.com.nptec.mvp.model.Sensores;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class HttpService extends AsyncTask<String, Void, Sensores> {

    /*
    * url_base = victortirano.pythonanywhere.com
    média aritmética = url_base/view-all-average
    desvio padrão = url_base/view-all-standard-deviation
    último dado = url_base/view-last-data
    todos os dados = url_base/view-all-data
    * */

    @Override
    protected Sensores doInBackground(String... params) {
        StringBuilder resposta = new StringBuilder();
        URL url = null;
        try {
            if(params.length > 0){
                url = new URL("http://victortirano.pythonanywhere.com/" + params[0]);
            }else{
                url = new URL("http://victortirano.pythonanywhere.com/" + params.toString());
            }

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            //connection.setReadTimeout(5000);
            //connection.setConnectTimeout(5000);
            connection.connect();

            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                resposta.append(scanner.next());
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sensores sensores = new Utils().extraiSensorSemArray(resposta.toString());
        //Sensores sensores = new Gson().fromJson(resposta.toString(), Sensores.class);
        Log.i("Resposta:", sensores.toString());

        //List<Sensores> produtos = new Gson().fromJson(resposta.toString(), new TypeToken<List<Sensores>>(){}.getType());

        return sensores;
    }

}
