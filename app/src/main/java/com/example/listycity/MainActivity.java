package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    int selectedCityPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList = new ArrayList<>(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        final Button addCityButton = findViewById(R.id.add_city_button);
        final Button deleteCityButton = findViewById(R.id.delete_city_button);
        final LinearLayout addCityLayout = findViewById(R.id.add_city_layout);
        final EditText newCityEditText = findViewById(R.id.new_city_edit_text);
        final Button confirmAddCityButton = findViewById(R.id.confirm_add_city_button);

        addCityButton.setOnClickListener(v -> addCityLayout.setVisibility(View.VISIBLE));

        deleteCityButton.setOnClickListener(v -> {
            if (selectedCityPosition != -1) {
                dataList.remove(selectedCityPosition);
                cityAdapter.notifyDataSetChanged();
                cityList.clearChoices();
                selectedCityPosition = -1;
            }
        });

        confirmAddCityButton.setOnClickListener(v -> {
            String newCity = newCityEditText.getText().toString();
            if (!newCity.isEmpty()) {
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                newCityEditText.setText("");
                addCityLayout.setVisibility(View.GONE);
            }
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedCityPosition = position;
        });
    }
}
