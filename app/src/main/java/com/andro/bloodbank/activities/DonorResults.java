package com.andro.bloodbank.activities;

import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.andro.bloodbank.R;
import com.andro.bloodbank.database.AppDatabase;
import com.andro.bloodbank.database.DonorProfile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DonorResults extends AppCompatActivity {

    private Intent intent;
    private List<DonorProfile> donorProfiles;
    private List<DonorProfile> bloodFilterDonors;
    private List<DonorProfile> rhTypeFilterDonors;
    private List<DonorProfile> ageFilterDonors;
    private List<DonorProfile> genderFilterDonors;
    private ArrayList<String> donorNames;
    private ListView donorListView;
    private ArrayAdapter<? extends String> donorAdapter;
    private ProgressDialog progressDialog;
    private static final String TAG = "DONOR_RESULTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_results);

        intent = this.getIntent();
        donorProfiles = new ArrayList<>();
        bloodFilterDonors = new ArrayList<>();
        rhTypeFilterDonors = new ArrayList<>();
        ageFilterDonors = new ArrayList<>();
        genderFilterDonors = new ArrayList<>();

        progressDialog = new ProgressDialog(this);

        donorListView = findViewById(R.id.donor_result_list);
        donorListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(DonorResults.this, DonorViewer.class);
            intent.putExtra("donor_id", donorProfiles.get(position).getDonorid());
            Log.d(TAG + " sent ", donorProfiles.get(position).getDonorid());
            startActivity(intent);
        });

        progressDialog.setCancelable(false);
        progressDialog.setMessage("Querying Database");
        progressDialog.show();
        new GetData().execute();
    }

    private class GetData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = Room.databaseBuilder(getBaseContext(),
                    AppDatabase.class, "profile").build();

            if (intent.getExtras() == null) {
                donorProfiles = db.profileDao().getAll();
            } else {
                int posBloodGroup = intent.getExtras().getInt("blood_group");
                Log.d(TAG, "Blood Group Selection Received " + posBloodGroup);

                int posRhType = intent.getExtras().getInt("rhtype");
                Log.d(TAG, "Rh Type Selection Received " + posRhType);

                int posAge = intent.getExtras().getInt("age");
                Log.d(TAG, "Age Selection Received " + posAge);

                int posGender = intent.getExtras().getInt("gender");
                Log.d(TAG, "Gender Selection Received " + posGender);

                switch (posBloodGroup) {
                    case 0:
                        bloodFilterDonors = db.profileDao().getAll();
                        break;
                    case 1:
                        bloodFilterDonors = db.profileDao().getDonorWithBloodGroup("A1");
                        break;
                    case 2:
                        bloodFilterDonors = db.profileDao().getDonorWithBloodGroup("B");
                        break;
                    case 3:
                        bloodFilterDonors = db.profileDao().getDonorWithBloodGroup("O");
                        break;
                    case 4:
                        bloodFilterDonors = db.profileDao().getDonorWithBloodGroup("A1B");
                        break;
                }

                switch (posRhType) {
                    case 0:
                        rhTypeFilterDonors = db.profileDao().getAll();
                        break;
                    case 1:
                        rhTypeFilterDonors = db.profileDao().getDonorWithRhType("+");
                        break;
                    case 2:
                        rhTypeFilterDonors = db.profileDao().getDonorWithRhType("-");
                        break;
                }

                switch (posAge) {
                    case 0:
                        ageFilterDonors = db.profileDao().getAll();
                        break;
                    case 1:
                        ageFilterDonors = db.profileDao().getDonorsInAgeRange("18", "30");
                        break;
                    case 2:
                        ageFilterDonors = db.profileDao().getDonorsInAgeRange("31", "40");
                        break;
                    case 3:
                        ageFilterDonors = db.profileDao().getDonorsInAgeRange("41", "50");
                        break;
                    case 4:
                        ageFilterDonors = db.profileDao().getDonorsInAgeRange("51", "60");
                        break;
                }

                switch (posGender) {
                    case 0:
                        genderFilterDonors = db.profileDao().getAll();
                        break;
                    case 1:
                        genderFilterDonors = db.profileDao().getDonorsWithGender("Male");
                        break;
                    case 2:
                        genderFilterDonors = db.profileDao().getDonorsWithGender("Female");
                        break;
                    case 3:
                        genderFilterDonors = db.profileDao().getDonorsWithGender("TransGender");
                        break;
                }

                List<DonorProfile> finalList = new ArrayList<>();
                for (DonorProfile e : bloodFilterDonors) {
                    for (DonorProfile i : rhTypeFilterDonors) {
                        if (Objects.equals(i.getDonorid(), e.getDonorid())) {
                            finalList.add(e);
                            break;
                        }
                    }
                }

                List<DonorProfile> finalList2 = new ArrayList<>();
                for (DonorProfile e : ageFilterDonors) {
                    for (DonorProfile i : genderFilterDonors) {
                        if (Objects.equals(i.getDonorid(), e.getDonorid())) {
                            finalList2.add(e);
                            break;
                        }
                    }
                }

                List<DonorProfile> finalList3 = new ArrayList<>();
                for (DonorProfile e : finalList) {
                    for (DonorProfile i : finalList2) {
                        if (Objects.equals(i.getDonorid(), e.getDonorid())) {
                            finalList3.add(e);
                            break;
                        }
                    }
                }
                donorProfiles.addAll(finalList3);
            }

            donorNames = new ArrayList<>();
            for (int i = 0; i < donorProfiles.size(); i++) {
                donorNames.add(donorProfiles.get(i).getName());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            donorAdapter = new ArrayAdapter<>(getBaseContext(),
                    android.R.layout.simple_list_item_1, donorNames);
            donorListView.setAdapter(donorAdapter);
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            progressDialog = null;
            super.onPostExecute(aVoid);
        }
    }
}
