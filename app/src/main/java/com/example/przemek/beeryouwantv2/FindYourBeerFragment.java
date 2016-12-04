package com.example.przemek.beeryouwantv2;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.GridLayout;
import android.widget.RadioGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class FindYourBeerFragment extends Fragment {


    private String color = "unknown";
    private int bitter = 0;
    private int malt = 0;
    private int alcohol = 0;
    private String maltWheat = "unknown";
    private String fermentation = "unknown";
    private GridLayout advChoiceLayout;

    public FindYourBeerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_find_your_beer, container, false);

        advChoiceLayout = (GridLayout) v.findViewById(R.id.adv_choice_layout);

        final RadioGroup colorRadioGroup = (RadioGroup) v.findViewById(R.id.find_your_beer_radio_group);
        colorRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radio_light:
                        color = "light";
                        break;
                    case R.id.radio_dark:
                        color = "dark";
                        break;
                    default:
                }
            }
        });

        final RadioGroup bitterRadioGroup = (RadioGroup) v.findViewById(R.id.find_your_beer_bitter_radio_group);
        bitterRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.bitter_radio_weak:
                        bitter = 1;
                        break;
                    case R.id.bitter_radio_normal:
                        bitter = 2;
                        break;
                    case R.id.bitter_radio_strong:
                        bitter = 3;
                        break;
                    default:
                }
            }
        });
        final RadioGroup maltRadioGroup = (RadioGroup) v.findViewById(R.id.find_your_beer_malt_radio_group);
        maltRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.malt_radio_weak:
                        malt = 1;
                        break;
                    case R.id.malt_radio_normal:
                        malt = 2;
                        break;
                    case R.id.malt_radio_strong:
                        malt = 3;
                        break;
                    default:
                }
            }
        });
        final RadioGroup alcoholRadioGroup = (RadioGroup) v.findViewById(R.id.find_your_beer_alcohol_radio_group);
        alcoholRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.alcohol_radio_low:
                        alcohol = 1;
                        break;
                    case R.id.alcohol_radio_normal:
                        alcohol = 2;
                        break;
                    case R.id.alcohol_radio_high:
                        alcohol = 3;
                        break;
                    default:
                }
            }
        });
        final RadioGroup maltWheatRadioGroup = (RadioGroup) v.findViewById(R.id.find_your_beer_malt_wheat_radio_group);
        maltWheatRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.malt_wheat_radio_yes:
                        maltWheat = "yes";
                        break;
                    case R.id.malt_wheat_radio_not_necessarily:
                        maltWheat = "not_nes";
                        break;
                    default:
                }
            }
        });
        final RadioGroup fermentationRadioGroup = (RadioGroup) v.findViewById(R.id.find_your_beer_fermentation_radio_group);
        fermentationRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.fermentation_radio_upper:
                        fermentation = "upper";
                        break;
                    case R.id.fermentation_radio_lower:
                        fermentation = "lower";
                        break;
                    default:
                }
            }
        });

        final CheckBox advChoiceCheckBox = (CheckBox) v.findViewById(R.id.find_your_beer_adv_choice);
        advChoiceCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    advChoiceLayout.setVisibility(GridLayout.VISIBLE);
                } else {
                    advChoiceLayout.setVisibility(GridLayout.GONE);
                }
            }
        });

        final Button findYourBeerButton = (Button) v.findViewById(R.id.find_your_beer_button);
        findYourBeerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChosenStylesActivity.class);
                intent.putExtra(ChosenStylesActivity.EXTRA_COLOR, color);
                intent.putExtra(ChosenStylesActivity.EXTRA_BITTER, bitter);
                intent.putExtra(ChosenStylesActivity.EXTRA_MALT, malt);
                intent.putExtra(ChosenStylesActivity.EXTRA_ALCOHOL, alcohol);
                intent.putExtra(ChosenStylesActivity.EXTRA_WHEAT_MALT, maltWheat);
                intent.putExtra(ChosenStylesActivity.EXTRA_FERMENTATION, fermentation);
                startActivity(intent);
            }
        });

        return v;
    }
}
