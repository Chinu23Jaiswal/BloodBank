package com.andro.bloodbank.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.andro.bloodbank.R;
import com.andro.bloodbank.database.AppDatabase;
import com.andro.bloodbank.database.DonorProfile;

public class DonorViewer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_viewer);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "profile").allowMainThreadQueries().build();
        Intent intent = this.getIntent();
        assert intent.getExtras() != null;
        String donorId = intent.getExtras().getString("donor_id");
        DonorProfile donorProfile = db.profileDao().getDonorById(donorId);

        TextView name, dob, age, officepincode, education, occupation, officeaddress, rhtype,
                resvillageortownorcity, nsdod_wb, resdoornoandstreetorroad, reg_centre, nsdod,
                donor_type, nsdod_plasma, respincode, will_wed_day, resaddress, lastdod_platelet,
                will_term, bloodgroup, willl_bday, lastdod_plasma, restaluk, gender, resmobile,
                dor, officetaluk, resarea, nsdod_doubleredcells, officeemail, resbuildingname,
                lastdod_doubleredcells, motivated_by, nsdod_platelet, nsdodaph, officedistrict,
                will_oth_day, resdistrict, resemail, officevillageortownorcity, spousename,
                officemobile, officedoornoandstreetorroad, lastdod_wb, officebuildingname,
                officephone, resphone, officearea;
        name = findViewById(R.id.donor_name);
        dob = findViewById(R.id.donor_dob);
        age = findViewById(R.id.donor_age);
        officepincode = findViewById(R.id.donor_officepincode);
        education = findViewById(R.id.donor_education);
        occupation = findViewById(R.id.donor_occupation);
        officeaddress = findViewById(R.id.donor_officeaddress);
        rhtype = findViewById(R.id.donor_rhtype);
        resvillageortownorcity = findViewById(R.id.donor_resvillageortownorcity);
        nsdod_wb = findViewById(R.id.donor_nsdod_wb);
        resdoornoandstreetorroad = findViewById(R.id.donor_resdoornoandstreetorroad);
        reg_centre = findViewById(R.id.donor_reg_centre);
        nsdod = findViewById(R.id.donor_nsdod);
        donor_type = findViewById(R.id.donor_donor_type);
        nsdod_plasma = findViewById(R.id.donor_nsdod_plasma);
        respincode = findViewById(R.id.donor_respincode);
        will_wed_day = findViewById(R.id.donor_will_wed_day);
        resaddress = findViewById(R.id.donor_resaddress);
        lastdod_platelet = findViewById(R.id.donor_lastdod_platelet);
        will_term = findViewById(R.id.donor_will_term);
        bloodgroup = findViewById(R.id.donor_bloodgroup);
        willl_bday = findViewById(R.id.donor_willl_bday);
        lastdod_plasma = findViewById(R.id.donor_lastdod_plasma);
        restaluk = findViewById(R.id.donor_restaluk);
        gender = findViewById(R.id.donor_gender);
        resmobile = findViewById(R.id.donor_resmobile);
        dor = findViewById(R.id.donor_dor);
        officetaluk = findViewById(R.id.donor_officetaluk);
        resarea = findViewById(R.id.donor_resarea);
        nsdod_doubleredcells = findViewById(R.id.donor_nsdod_doubleredcells);
        officeemail = findViewById(R.id.donor_officeemail);
        resbuildingname = findViewById(R.id.donor_resbuildingname);
        lastdod_doubleredcells = findViewById(R.id.donor_lastdod_doubleredcells);
        motivated_by = findViewById(R.id.donor_motivated_by);
        nsdod_platelet = findViewById(R.id.donor_nsdod_platelet);
        nsdodaph = findViewById(R.id.donor_nsdodaph);
        officedistrict = findViewById(R.id.donor_officedistrict);
        will_oth_day = findViewById(R.id.donor_will_oth_day);
        resdistrict = findViewById(R.id.donor_resdistrict);
        resemail = findViewById(R.id.donor_resemail);
        officevillageortownorcity = findViewById(R.id.donor_officevillageortownorcity);
        spousename = findViewById(R.id.donor_spousename);
        officemobile = findViewById(R.id.donor_officemobile);
        officedoornoandstreetorroad = findViewById(R.id.donor_officedoornoandstreetorroad);
        lastdod_wb = findViewById(R.id.donor_lastdod_wb);
        officebuildingname = findViewById(R.id.donor_officebuildingname);
        officephone = findViewById(R.id.donor_officephone);
        resphone = findViewById(R.id.donor_resphone);
        officearea = findViewById(R.id.donor_officearea);

        if (donorProfile != null) {
            name.setText(String.format(name.getText().toString(), donorProfile.getName()));

            dob.setText(String.format(dob.getText().toString(), donorProfile.getDob()));

            age.setText(String.format(age.getText().toString(), donorProfile.getAge()));

            officepincode.setText(String.format(officepincode.getText().toString(), donorProfile.getOfficepincode()));

            education.setText(String.format(education.getText().toString(), donorProfile.getEducation()));

            occupation.setText(String.format(occupation.getText().toString(), donorProfile.getOccupation()));

            officeaddress.setText(String.format(officeaddress.getText().toString(), donorProfile.getOfficeaddress()));

            rhtype.setText(String.format(rhtype.getText().toString(), donorProfile.getRhtype()));

            resvillageortownorcity.setText(String.format(resvillageortownorcity.getText().toString(), donorProfile.getResvillageortownorcity()));

            nsdod_wb.setText(String.format(nsdod_wb.getText().toString(), donorProfile.getNsdod_wb()));

            resdoornoandstreetorroad.setText(String.format(resdoornoandstreetorroad.getText().toString(), donorProfile.getResdoornoandstreetorroad()));

            reg_centre.setText(String.format(reg_centre.getText().toString(), donorProfile.getReg_centre()));

            nsdod.setText(String.format(nsdod.getText().toString(), donorProfile.getNsdod()));

            donor_type.setText(String.format(donor_type.getText().toString(), donorProfile.getDonor_type()));

            nsdod_plasma.setText(String.format(nsdod_plasma.getText().toString(), donorProfile.getNsdod_plasma()));

            respincode.setText(String.format(respincode.getText().toString(), donorProfile.getRespincode()));

            will_wed_day.setText(String.format(will_wed_day.getText().toString(), donorProfile.getWill_wed_day()));

            resaddress.setText(String.format(resaddress.getText().toString(), donorProfile.getResaddress()));

            lastdod_platelet.setText(String.format(lastdod_platelet.getText().toString(), donorProfile.getLastdod_platelet()));

            will_term.setText(String.format(will_term.getText().toString(), donorProfile.getWill_term()));

            bloodgroup.setText(String.format(bloodgroup.getText().toString(), donorProfile.getBloodgroup()));

            willl_bday.setText(String.format(willl_bday.getText().toString(), donorProfile.getWilll_bday()));

            lastdod_plasma.setText(String.format(lastdod_plasma.getText().toString(), donorProfile.getLastdod_plasma()));

            restaluk.setText(String.format(restaluk.getText().toString(), donorProfile.getRestaluk()));
            
            gender.setText(String.format(gender.getText().toString(), donorProfile.getGender()));

            resmobile.setText(String.format(resmobile.getText().toString(), donorProfile.getResmobile()));

            dor.setText(String.format(dor.getText().toString(), donorProfile.getDor()));

            officetaluk.setText(String.format(officetaluk.getText().toString(), donorProfile.getOfficetaluk()));

            resarea.setText(String.format(resarea.getText().toString(), donorProfile.getResarea()));

            nsdod_doubleredcells.setText(String.format(nsdod_doubleredcells.getText().toString(), donorProfile.getNsdod_doubleredcells()));

            officeemail.setText(String.format(officeemail.getText().toString(), donorProfile.getOfficeemail()));

            resbuildingname.setText(String.format(resbuildingname.getText().toString(), donorProfile.getResbuildingname()));

            lastdod_doubleredcells.setText(String.format(lastdod_doubleredcells.getText().toString(), donorProfile.getLastdod_doubleredcells()));

            motivated_by.setText(String.format(motivated_by.getText().toString(), donorProfile.getMotivated_by()));

            nsdod_platelet.setText(String.format(nsdod_platelet.getText().toString(), donorProfile.getNsdod_platelet()));

            nsdodaph.setText(String.format(nsdodaph.getText().toString(), donorProfile.getNsdodaph()));

            officedistrict.setText(String.format(officedistrict.getText().toString(), donorProfile.getOfficedistrict()));

            will_oth_day.setText(String.format(will_oth_day.getText().toString(), donorProfile.getWill_oth_day()));

            resdistrict.setText(String.format(resdistrict.getText().toString(), donorProfile.getResdistrict()));

            resemail.setText(String.format(resemail.getText().toString(), donorProfile.getResemail()));

            officevillageortownorcity.setText(String.format(officevillageortownorcity.getText().toString(), donorProfile.getOfficevillageortownorcity()));

            spousename.setText(String.format(spousename.getText().toString(), donorProfile.getSpousename()));

            officemobile.setText(String.format(officemobile.getText().toString(), donorProfile.getOfficemobile()));

            officedoornoandstreetorroad.setText(String.format(officedoornoandstreetorroad.getText().toString(), donorProfile.getOfficedoornoandstreetorroad()));

            lastdod_wb.setText(String.format(lastdod_wb.getText().toString(), donorProfile.getLastdod_wb()));

            officebuildingname.setText(String.format(officebuildingname.getText().toString(), donorProfile.getOfficebuildingname()));

            officephone.setText(String.format(officephone.getText().toString(), donorProfile.getOfficephone()));

            resphone.setText(String.format(resphone.getText().toString(), donorProfile.getResphone()));

            officearea.setText(String.format(officearea.getText().toString(), donorProfile.getOfficearea()));
        } else {
            Toast.makeText(this, "Couldn't get Donor", Toast.LENGTH_LONG).show();
        }
    }
}
