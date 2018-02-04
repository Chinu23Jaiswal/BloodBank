package com.andro.bloodbank.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.andro.bloodbank.R;
import com.andro.bloodbank.activities.DonorResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class LookUpFragment extends Fragment {

    private static final String TAG = "LOOK_UP_FRAGMENT";

    public LookUpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Profile Directory Look Up");
        View v = inflater.inflate(R.layout.fragment_look_up, container, false);

        final Spinner sEligibility, sBloodGroup, sRhType, sAgeGroup, sGender, sDonorType;
        final SpinnerAdapter aEligibility, aBloodGroup, aRhType, aAgeGroup, aGender, aDonorType;

        sEligibility = v.findViewById(R.id.spinner_eligibility);
        sBloodGroup = v.findViewById(R.id.spinner_blood_group);
        sRhType = v.findViewById(R.id.spinner_rh_type);
        sAgeGroup = v.findViewById(R.id.spinner_age_group);
        sGender = v.findViewById(R.id.spinner_gender);
        sDonorType = v.findViewById(R.id.spinner_donor_type);

        aEligibility = ArrayAdapter.createFromResource(getContext(), R.array.array_eligibility,
                R.layout.spinner_drop_list);
        aBloodGroup = ArrayAdapter.createFromResource(getContext(), R.array.array_blood_group,
                R.layout.spinner_drop_list);
        aRhType = ArrayAdapter.createFromResource(getContext(), R.array.array_rh_type,
                R.layout.spinner_drop_list);
        aAgeGroup = ArrayAdapter.createFromResource(getContext(), R.array.array_age_group,
                R.layout.spinner_drop_list);
        aGender = ArrayAdapter.createFromResource(getContext(), R.array.array_gender,
                R.layout.spinner_drop_list);
        aDonorType = ArrayAdapter.createFromResource(getContext(), R.array.array_donor_type,
                R.layout.spinner_drop_list);

        sEligibility.setAdapter(aEligibility);
        sBloodGroup.setAdapter(aBloodGroup);
        sRhType.setAdapter(aRhType);
        sAgeGroup.setAdapter(aAgeGroup);
        sGender.setAdapter(aGender);
        sDonorType.setAdapter(aDonorType);

        FloatingActionButton fab = v.findViewById(R.id.search);
        fab.setOnClickListener(view -> {
            int posBloodGroup = sBloodGroup.getSelectedItemPosition();
            Log.d(TAG, "Blood Group Selection " + posBloodGroup);
            int posRhType = sRhType.getSelectedItemPosition();
            Log.d(TAG, "RH Type Selection " + posRhType);
            int posAge = sAgeGroup.getSelectedItemPosition();
            Log.d(TAG, "Age Selection " + posAge);
            int posGender = sGender.getSelectedItemPosition();
            Log.d(TAG, "Gender Selection " + posGender);

            Intent intent = new Intent(getActivity(), DonorResults.class);
            intent.putExtra("blood_group", posBloodGroup);
            intent.putExtra("rhtype", posRhType);
            intent.putExtra("age", posAge);
            intent.putExtra("gender", posGender);
            startActivity(intent);
        });
        return v;
    }
}
