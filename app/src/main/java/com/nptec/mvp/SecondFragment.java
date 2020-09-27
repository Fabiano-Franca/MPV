package com.nptec.mvp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.nptec.mvp.com.nptec.mvp.dao.HttpService;
import com.nptec.mvp.com.nptec.mvp.model.Sensores;

import java.util.concurrent.ExecutionException;

public class SecondFragment extends Fragment {

    TextView textViewMediaTemp;
    TextView textViewMediaPh;
    TextView textViewMediaRpm;
    TextView textViewMediaRotacao;
    TextView textViewDesvioPadraoTemp;
    TextView textViewDesvioPadraoPh;
    TextView textViewDesvioPadraoRpm;
    TextView textViewDesvioPadraoRotacao;
    Sensores sensoresMedia;
    Sensores sensoresDesvioPadrao;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inicializaComponentes(view);

        try {
            sensoresMedia = new HttpService().execute("view-all-average").get();
            sensoresDesvioPadrao = new HttpService().execute("view-all-standard-deviation").get();
            if(sensoresMedia != null && sensoresDesvioPadrao != null){
                alteraView();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void alteraView() {
        textViewMediaTemp.setText(sensoresMedia.getTemp());
        textViewMediaPh.setText(sensoresMedia.getPh());
        textViewMediaRpm.setText(sensoresMedia.getRpm());
        textViewMediaRotacao.setText(sensoresMedia.getN());
        textViewDesvioPadraoTemp.setText(sensoresDesvioPadrao.getTemp());
        textViewDesvioPadraoPh.setText(sensoresDesvioPadrao.getPh());
        textViewDesvioPadraoRpm.setText(sensoresDesvioPadrao.getRpm());
        textViewDesvioPadraoRotacao.setText(sensoresDesvioPadrao.getN());
    }

    private void inicializaComponentes(@NonNull View view) {
        textViewMediaTemp = view.findViewById(R.id.textView_valor_temp_media);
        textViewMediaPh = view.findViewById(R.id.textView_valor_ph_media);
        textViewMediaRpm = view.findViewById(R.id.textView_valor_rpm_media);
        textViewMediaRotacao = view.findViewById(R.id.textView_valor_rotacao_media);
        textViewDesvioPadraoTemp = view.findViewById(R.id.textView_valor_temp_DesvioPadrao);
        textViewDesvioPadraoPh = view.findViewById(R.id.textView_valor_ph_DesvioPadrao);
        textViewDesvioPadraoRpm = view.findViewById(R.id.textView_valor_rpm_DesvioPadrao);
        textViewDesvioPadraoRotacao = view.findViewById(R.id.textView_valor_rotacao_DesvioPadrao);
    }
}