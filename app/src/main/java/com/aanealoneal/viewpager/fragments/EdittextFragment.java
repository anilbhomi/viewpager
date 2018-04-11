package com.aanealoneal.viewpager.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.aanealoneal.viewpager.R;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * A simple {@link Fragment} subclass.
 */
public class EdittextFragment extends Fragment {

    private static final String ARG_INDIVIDUAL_QUESTION = "individual_question";
    private static JsonObject individualQuestion;


    private EditText etdEdittext;
    private TextView tvLabel;


    public EdittextFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_edittext, container, false);
        individualQuestion = (JsonObject) new JsonParser().parse(getArguments().getString(ARG_INDIVIDUAL_QUESTION));

        etdEdittext = view.findViewById(R.id.et_edittext);
        tvLabel = view.findViewById(R.id.tv_label);

        tvLabel.setText(individualQuestion.get("label").getAsString());
        checkInputType();
        return view;
    }

    private void checkInputType() {
        switch (individualQuestion.get("input_type").getAsString()){
            case "email":{
                etdEdittext.setInputType(InputType.TYPE_TEXT_VARIATION_WEB_EMAIL_ADDRESS);
                break;
            }
            case "number":{
                etdEdittext.setInputType(InputType.TYPE_CLASS_NUMBER);
                break;
            }
            case "number_decimal":{
                etdEdittext.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
                break;
            }
            case "text":{
                etdEdittext.setInputType(InputType.TYPE_CLASS_TEXT);
                break;
            }
        }
    }
}
