package com.example.przemek.beeryouwantv2;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Przemek on 11.12.2016.
 */

public class AlcoholicStrengthOfFragment extends Fragment {

    private EditText weight, mlBeer, alcoholContent;
    private Button calculatePromil, startTime;
    private TextView velueOfPromil, timeSobering;
    private double K = 0.65, alcoholContentNumber = 5, mlBeerNumber = 500, weightNumber = 60, velueOfPromilNumber, timeSoberingNumber ;
    public AlcoholicStrengthOfFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alcoholic_strength_of, container, false);
        weight = (EditText) view.findViewById(R.id.weight);
        mlBeer = (EditText) view.findViewById(R.id.mlBeer);
        alcoholContent =  (EditText) view.findViewById(R.id.alcoholContent);
        calculatePromil = (Button) view.findViewById(R.id.calculatePromil);
        startTime = (Button) view.findViewById(R.id.startTime);
        velueOfPromil = (TextView) view.findViewById(R.id.velueOfPromil);
        timeSobering = (TextView) view.findViewById(R.id.timeSobering);
        final RadioGroup colorRadioGroup = (RadioGroup) view.findViewById(R.id.sex);
        colorRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_woman:
                        K = 0.6;
                        break;
                    case R.id.radio_man:
                        K = 0.7;
                        break;
                    default:
                }
            }
        });

        calculatePromil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!isTextViewEmpty(alcoholContent) && !isTextViewEmpty(mlBeer) && !isTextViewEmpty(weight)) {
                    alcoholContentNumber = Integer.parseInt(alcoholContent.getText().toString()) ;
                    mlBeerNumber = Integer.parseInt(mlBeer.getText().toString()) ;
                    weightNumber = Integer.parseInt(weight.getText().toString()) ;
                    double A = (mlBeerNumber * (alcoholContentNumber / 100) * 0.8 );
                    timeSoberingNumber =(A / 10) ;
                    velueOfPromilNumber = A / weightNumber * K;
                    velueOfPromil.setText( "Promile: " + velueOfPromilNumber  );
                    timeSobering.setText( "Time to sobering: " + timeSoberingNumber + " h");
                    startTime.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(v.getContext(), "Empty Field", Toast.LENGTH_SHORT).show();
                }


            }
        });
        startTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                velueOfPromil.setText("");
                timeSobering.setText("");
                startTime.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(v.getContext(), TimeOfAlcohol.class);
                intent.putExtra("UniqueKeyV1", timeSoberingNumber);
                v.getContext().startService(intent);


            }
        });


        return view;
    }
    private boolean isTextViewEmpty(final TextView textView) {
        return !((textView != null) && (textView.getText() != null) && (textView.getText().toString() != null) && !textView
                .getText().toString().equals(""));
    }
}
