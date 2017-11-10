package com.andro.bloodbank.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.andro.bloodbank.DonorResults;
import com.andro.bloodbank.R;

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

        Spinner sEligibility, sBloodGroup, sRhType, sAgeGroup, sGender, sDonorType, sArea, sCity, sMotivatedBy;
        SpinnerAdapter aEligibility, aBloodGroup, aRhType, aAgeGroup, aGender, aDonorType, aArea, aCity, aMotivatedBy;

        sEligibility = v.findViewById(R.id.spinner_eligibility);
        sBloodGroup = v.findViewById(R.id.spinner_blood_group);
        sRhType = v.findViewById(R.id.spinner_rh_type);
        sAgeGroup = v.findViewById(R.id.spinner_age_group);
        sGender = v.findViewById(R.id.spinner_gender);
        sDonorType = v.findViewById(R.id.spinner_donor_type);
        sArea = v.findViewById(R.id.spinner_area);
        sCity = v.findViewById(R.id.spinner_city);
        sMotivatedBy = v.findViewById(R.id.spinner_motivated_by);

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
        aArea = ArrayAdapter.createFromResource(getContext(), R.array.array_area,
                R.layout.spinner_drop_list);
        aCity = ArrayAdapter.createFromResource(getContext(), R.array.array_city,
                R.layout.spinner_drop_list);
        aMotivatedBy = ArrayAdapter.createFromResource(getContext(), R.array.array_motivated_by,
                R.layout.spinner_drop_list);

        sEligibility.setAdapter(aEligibility);
        sBloodGroup.setAdapter(aBloodGroup);
        sRhType.setAdapter(aRhType);
        sAgeGroup.setAdapter(aAgeGroup);
        sGender.setAdapter(aGender);
        sDonorType.setAdapter(aDonorType);
        sArea.setAdapter(aArea);
        sCity.setAdapter(aCity);
        sMotivatedBy.setAdapter(aMotivatedBy);

        Button searchButton = v.findViewById(R.id.search_look_up);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), DonorResults.class);
                startActivity(intent);
            }
        });
        return v;
    }
}
