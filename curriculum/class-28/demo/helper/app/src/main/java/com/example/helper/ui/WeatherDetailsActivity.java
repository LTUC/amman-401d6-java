package com.example.helper.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.helper.R;
import com.example.helper.data.GroceryItem;

public class WeatherDetailsActivity extends AppCompatActivity {

    GroceryItem[] groceries = new GroceryItem[]{
            new GroceryItem("Cheese", 23),
            new GroceryItem("Bread", 12),
            new GroceryItem("Falafel", 10),
            new GroceryItem("Humus", 67),
            new GroceryItem("Milk", 16),
            new GroceryItem("Chicken", 17),
            new GroceryItem("Butter", 1),
            new GroceryItem("Coffee", 2),
            new GroceryItem("Ice Cream", 7),
            new GroceryItem("Chocolate", 100),
            new GroceryItem("Fish", 34),
            new GroceryItem("Pasta", 90),
            new GroceryItem("Tea", 99),
            new GroceryItem("Juice", 20),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_details);

        ListView groceriesList = findViewById(R.id.list_groceries);

        ArrayAdapter<GroceryItem> groceryItemArrayAdapter = new ArrayAdapter<GroceryItem>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                groceries
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView name = view.findViewById(android.R.id.text1);
                TextView cost = view.findViewById(android.R.id.text2);

                name.setText(groceries[position].getName());
                cost.setText("The cost is => $" + groceries[position].getCost());

                return view;
            }
        };

        groceriesList.setAdapter(groceryItemArrayAdapter);
        groceriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(
                        WeatherDetailsActivity.this,
                        "The selected item is => " + groceries[i].getName(), Toast.LENGTH_SHORT).show();

                startActivity(new Intent(getApplicationContext(), RecyclerViewActivity.class));
            }
        });
    }
}