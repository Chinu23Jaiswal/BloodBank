package com.andro.bloodbank.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.andro.bloodbank.R;
import com.andro.bloodbank.database.AppDatabase;
import com.andro.bloodbank.database.DonorProfile;
import com.andro.bloodbank.fragments.LookUpFragment;
import com.andro.bloodbank.fragments.SearchFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MAIN_ACTIVITY";
    private ArrayList<DonorProfile> donorProfiles;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(1).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(1));

        donorProfiles = new ArrayList<>();
        context = this;

        //First Run Check
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (isFirstRun) {
            Toast.makeText(this, "First Run Setup Initialised", Toast.LENGTH_LONG).show();
            prepDatabase();
        }
        getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                .putBoolean("isFirstRun", false).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void prepDatabase() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Looking for Cloud Database Changes, Make sure you have a good internet connection");
        progressDialog.show();
        Log.d(TAG, "Preparing Database");
        if (checkConnection()) {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = database.getReference().child("profile");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.d(TAG, "Counting Number of Donors");
                    Log.d(TAG, Long.toString(dataSnapshot.getChildrenCount()));
                    GenericTypeIndicator<ArrayList<DonorProfile>> t = new GenericTypeIndicator<ArrayList<DonorProfile>>() {};
                    donorProfiles = dataSnapshot.getValue(t);
                    progressDialog.dismiss();
                    new PutData(context, donorProfiles).execute();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.d(TAG, "Cancelled");
                    progressDialog.dismiss();
                }
            });
        } else {
            Toast.makeText(this, "NO INTERNET CONNECTION", Toast.LENGTH_LONG).show();
        }
    }

    private static class PutData extends AsyncTask<Void, Void, Void> {

        private ProgressDialog progressDialog;
        @SuppressLint("StaticFieldLeak")
        private Context context;
        private ArrayList<DonorProfile> donorProfiles;

        PutData(Context context, ArrayList<DonorProfile> donorProfiles) {
            this.context = context;
            this.donorProfiles = donorProfiles;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Making Changes to Local Database, This may take a moment");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... aVoid) {
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "profile").build();
            for (DonorProfile e : donorProfiles) {
                db.profileDao().insertAll(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment frag;
        FragmentTransaction transaction;

        int id = item.getItemId();
        if (id == R.id.dir_search) {
            frag = new SearchFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame, frag).commit();
        } else if (id == R.id.dir_look_up) {
            frag = new LookUpFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame, frag).commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh_database) {
            prepDatabase();
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean checkConnection() {
        ConnectivityManager cm = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && (
                activeNetwork.getType() == ConnectivityManager.TYPE_WIFI ||
                        activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE ||
                        activeNetwork.getType() == ConnectivityManager.TYPE_ETHERNET);
    }
}
