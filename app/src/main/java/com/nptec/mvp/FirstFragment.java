package com.nptec.mvp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.ekn.gruzer.gaugelibrary.HalfGauge;
import com.ekn.gruzer.gaugelibrary.Range;

import java.util.HashMap;
import java.util.Map;

public class FirstFragment extends Fragment {

    HalfGauge halfGaugeTemp;
    HalfGauge halfGaugePh;
    CardView cardViewEstatistica;

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
        Map<String, Double> valores = new HashMap<>();
        valores.put("redLeftI", 0.0);
        valores.put("redLeftF", 50.0);
        valores.put("yellowLeftI", 50.1);
        valores.put("yellowLeftF", 100.0);
        valores.put("greenI", 100.1);
        valores.put("greenF", 150.0);
        valores.put("yellowRigthI", 150.1);
        valores.put("yellowRigthF", 200.0);
        valores.put("redRigthI", 200.1);
        valores.put("redRigthF", 250.0);
        valores.put("minValue", 0.0);
        valores.put("maxValue", 250.0);
        valores.put("valueInitial", 200.0);
        configuraGauge(halfGaugeTemp, valores);

        valores.put("valueInitial", 150.0);
        configuraGauge(halfGaugePh, valores);

        cardViewEstatistica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

    }

    private void inicializaComponentes(@NonNull View view) {
        halfGaugeTemp = view.findViewById(R.id.halfGauge_temp);
        halfGaugePh = view.findViewById(R.id.halfGauge_ph);
        cardViewEstatistica = view.findViewById(R.id.carView_estatistica);
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



}