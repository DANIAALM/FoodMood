package uk.ac.yorksj.mobileapplication.foodmood;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.google.gson.Gson;
import java.util.ArrayList;


public class MoodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moods);

        int buttonId = R.id.preferencePage;

        SharedPreferences prefs = getPreferences(getBaseContext().MODE_PRIVATE);
        String history = prefs.getString("history", "DEFAULT");

        Gson gson = new Gson();
        try {
            ArrayList<Mood> historyList = gson.fromJson(history, ArrayList.class);

            RecyclerView recyclerView = findViewById(R.id.moodlistView);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            RecycleViewAdapter adapter = new RecycleViewAdapter(historyList, this);
            recyclerView.setAdapter(adapter);
        } catch (Exception e){
            System.out.print("Couldn't load any moods");
        }


            View v = findViewById(buttonId);

            Button preferenceButton = (Button) v;

            preferenceButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent preferencesPage = new Intent(view.getContext(), PreferencesActivity.class);
                    startActivity(preferencesPage);
                }
            });
        }
    }

