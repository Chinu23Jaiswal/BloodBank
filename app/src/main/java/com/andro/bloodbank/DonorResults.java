package com.andro.bloodbank;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andro.bloodbank.database.AppDatabase;

import java.util.ArrayList;
import java.util.List;

public class DonorResults extends AppCompatActivity {

    private List<DonorProfile> donorProfiles;
    private ArrayList<String> donorNames;
    private static final String TAG = "DONOR_RESULTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_results);

        donorProfiles = new ArrayList<>();

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "profile")
                .allowMainThreadQueries().build();

        donorProfiles = db.profileDao().getAll();

        donorNames = new ArrayList<>();
        for (int i = 0; i < donorProfiles.size(); i++) {
            donorNames.add(donorProfiles.get(i).getName());
        }

        ListView donorListView = findViewById(R.id.donor_result_list);

        ArrayAdapter<? extends String> donorAdapter = new ArrayAdapter<>(getBaseContext(),
                android.R.layout.simple_list_item_1, donorNames);
        donorListView.setAdapter(donorAdapter);
    }
}
