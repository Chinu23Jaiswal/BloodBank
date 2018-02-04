package com.andro.bloodbank.activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
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
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int RC_SIGN_IN = 1;
    private static final String TAG = "MAIN_ACTIVITY";
    private ArrayList<DonorProfile> donorProfiles;
    private Context context;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

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
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

        mFirebaseAuth = FirebaseAuth.getInstance();
        donorProfiles = new ArrayList<>();
        context = this;

        mAuthStateListener = firebaseAuth -> {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user != null) {
                //First Run Check
                Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                        .getBoolean("isFirstRun", true);
                if (isFirstRun) {
                    Toast.makeText(context, "First Run Setup Init...", Toast.LENGTH_LONG).show();
                    prepDatabase();
                }
                getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                        .putBoolean("isFirstRun", false).apply();
            } else {
                startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                        .setAvailableProviders(Arrays.asList(
                                new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build(),
                                new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build()))
                        .build(), RC_SIGN_IN);
            }
        };

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this,
                        R.string.msg_signin_success, Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Sign in canceled", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
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
        /*
        if (id == R.id.dir_search) {
            frag = new SearchFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame, frag).commit();
        } else */
        if (id == R.id.dir_look_up) {
            frag = new LookUpFragment();
            transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.main_frame, frag).commit();
        } else if (id == R.id.dir_logout) {
            new NukeDB(context).execute();
            AuthUI.getInstance().signOut(this);
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit().clear().apply();
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

    private static class NukeDB extends AsyncTask<Void, Void, Void> {

        @SuppressLint("StaticFieldLeak")
        private Context context;
        private ProgressDialog progressDialog;

        NukeDB(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Deleting DB");
            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "profile").build();
            db.profileDao().deleteAll();
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
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
