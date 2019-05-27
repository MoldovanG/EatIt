package com.mh.eatitremake;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectIngredientActivity extends AppCompatActivity {

    private ArrayAdapter<String> IngredientNameAdapter;
    private TextView mIngredientsTextView;
    private Set<Ingredient> result = new HashSet<>();
    private AutoCompleteTextView editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_select_ingredient);
        mIngredientsTextView = findViewById(R.id.ingredients_list_textview);

        IngredientNameAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getIngredientsNames());


        editText = findViewById(R.id.search_ingredients);
        editText.setAdapter(IngredientNameAdapter);


        // added search button
        Button btn = (Button) findViewById(R.id.search_button_ingredient);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                EditText searchText = (EditText) findViewById(R.id.search_ingredients);
                String ingredientName = searchText.getText().toString();

                editText.setText("");

                List<Ingredient> ingredients = new ArrayList<>(MainMenuActivity.mGlobalIngredients);
                for (Ingredient ingredient: ingredients) {
                    if (ingredient.getName().equals(ingredientName) && !result.contains(ingredient)) {
                        result.add(ingredient);
                        mIngredientsTextView.setText(mIngredientsTextView.getText() + "\n -" + ingredient.getName());
                    }
                }

            }

        });


    }

    public List<String> getIngredientsNames(){
        List<String> mIngredientsNames = new ArrayList<>();
        for (Ingredient ingredient: MainMenuActivity.mGlobalIngredients){
            mIngredientsNames.add(ingredient.getName());
        }
        return mIngredientsNames;
    }

}

