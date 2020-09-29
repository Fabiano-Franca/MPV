package com.nptec.mvp;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.ekn.gruzer.gaugelibrary.Range;
import com.nptec.mvp.com.nptec.mvp.dao.Utils;
import com.nptec.mvp.com.nptec.mvp.model.Sensores;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class FirstFragment extends Fragment {

    HalfGauge halfGaugeTemp;
    HalfGauge halfGaugePh;
    CardView cardViewEstatistica;
    TextView textViewTensao01;
    TextView textViewTensao02;
    TextView textViewCorrente01;
    TextView textViewCorrente02;
    Sensores sensores;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializaComponentes(view);
        setaValoresGauge();

        valoresInicial();

        cardViewEstatistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    private void valoresInicial() {
        try {
            sensores = new HttpService().execute("view-last-data").get();
            if (sensores != null){
                alteraView(sensores);
            }else {
                Log.i("SENSORES: ", "Lista de sensores vazia. Verificar o retorno do WebService.");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void alteraView(Sensores sensores) {
        halfGaugeTemp.setValue(Double.parseDouble(sensores.getTemp()));
        halfGaugePh.setValue(Double.parseDouble(sensores.getPh()));
        textViewTensao01.setText(sensores.getT1() + " V");
        textViewTensao02.setText(sensores.getT2() + " V");
        textViewCorrente01.setText(sensores.getC1() + " A");
        textViewCorrente02.setText(sensores.getC2() + " A");
    }

    private void inicializaComponentes(@NonNull View view) {
        halfGaugeTemp = view.findViewById(R.id.halfGauge_temp);
        halfGaugePh = view.findViewById(R.id.halfGauge_ph);
        cardViewEstatistica = view.findViewById(R.id.carView_estatistica);
        textViewTensao01 = view.findViewById(R.id.textView_tensao_01);
        textViewTensao02 = view.findViewById(R.id.textView_tensao_02);
        textViewCorrente01 = view.findViewById(R.id.textView_corrente_01);
        textViewCorrente02 = view.findViewById(R.id.textView_corrente_02);
    }

    private void setaValoresGauge() {
        Map<String, Double> valores = new HashMap<>();
        valores.put("redLeftI", 22.0);
        valores.put("redLeftF", 25.0);
        valores.put("yellowLeftI", 25.0);
        valores.put("yellowLeftF", 28.0);
        valores.put("greenI", 28.0);
        valores.put("greenF", 31.0);
        valores.put("yellowRigthI", 31.0);
        valores.put("yellowRigthF", 33.0);
        valores.put("redRigthI", 33.0);
        valores.put("redRigthF", 36.0);
        valores.put("minValue", 22.0);
        valores.put("maxValue", 36.0);
        valores.put("valueInitial", 29.0);
        configuraGauge(halfGaugeTemp, valores);

        valores.put("redLeftI", 5.5);
        valores.put("redLeftF", 6.5);
        valores.put("yellowLeftI", 6.5);
        valores.put("yellowLeftF", 7.5);
        valores.put("greenI", 7.5);
        valores.put("greenF", 8.5);
        valores.put("yellowRigthI", 8.5);
        valores.put("yellowRigthF", 9.5);
        valores.put("redRigthI", 9.5);
        valores.put("redRigthF", 10.5);
        valores.put("minValue", 5.5);
        valores.put("maxValue", 10.5);
        valores.put("valueInitial", 8.0);
        configuraGauge(halfGaugePh, valores);
    }

    private void configuraGauge(HalfGauge v, Map<String, Double> valores) {

        Range rangeRedLeft = new Range();
        rangeRedLeft.setColor(Color.parseColor("#FF0000"));
        rangeRedLeft.setFrom(valores.get("redLeftI"));
        rangeRedLeft.setTo(valores.get("redLeftF"));

        Range rangeYellowLeft = new Range();
        rangeYellowLeft.setColor(Color.parseColor("#FFFF00"));
        rangeYellowLeft.setFrom(valores.get("yellowLeftI"));
        rangeYellowLeft.setTo(valores.get("yellowLeftF"));

        Range rangeGreen = new Range();
        rangeGreen.setColor(Color.parseColor("#70DB93"));
        rangeGreen.setFrom(valores.get("greenI"));
        rangeGreen.setTo(valores.get("greenF"));

        Range rangeYellowRigth = new Range();
        rangeYellowRigth.setColor(Color.parseColor("#FFFF00"));
        rangeYellowRigth.setFrom(valores.get("yellowRigthI"));
        rangeYellowRigth.setTo(valores.get("yellowRigthF"));

        Range rangeRedRigth = new Range();
        rangeRedRigth.setColor(Color.parseColor("#FF0000"));
        rangeRedRigth.setFrom(valores.get("redRigthI"));
        rangeRedRigth.setTo(valores.get("redRigthF"));

        //add color ranges to gauge
        v.addRange(rangeRedLeft);
        v.addRange(rangeYellowLeft);
        v.addRange(rangeGreen);
        v.addRange(rangeYellowRigth);
        v.addRange(rangeRedRigth);


        //set min max and current value
        v.setMinValue(valores.get("minValue"));
        v.setMaxValue(valores.get("maxValue"));
        v.setValue(valores.get("valueInitial"));

    }

    private class HttpService extends AsyncTask<String, Void, Sensores> {

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

            Sensores sensores = new Utils().extraiSensor(resposta.toString());
            //Sensores sensores = new Gson().fromJson(resposta.toString(), Sensores.class);
            //Log.i("Resposta:", sensores.toString());

            //List<Sensores> produtos = new Gson().fromJson(resposta.toString(), new TypeToken<List<Sensores>>(){}.getType());

            return sensores;
        }

    }

}