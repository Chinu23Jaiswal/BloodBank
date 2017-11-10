package com.andro.bloodbank;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Created by androdev700 on 28/10/17.
 */

@Entity
public class DonorProfile implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    @NonNull
    private String donorid;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "dob")
    private String dob;

    @ColumnInfo(name = "age")
    private String age;

    @ColumnInfo(name = "officepincode")
    private String officepincode;

    @ColumnInfo(name = "education")
    private String education;

    @ColumnInfo(name = "occupation")
    private String occupation;

    @ColumnInfo(name = "officeaddress")
    private String officeaddress;

    @ColumnInfo(name = "rhtype")
    private String rhtype;

    @ColumnInfo(name = "resvillageortownorcity")
    private String resvillageortownorcity;

    @ColumnInfo(name = "nsdod_wb")
    private String nsdod_wb;

    @ColumnInfo(name = "resdoornoandstreetorroad")
    private String resdoornoandstreetorroad;

    @ColumnInfo(name = "reg_centre")
    private String reg_centre;

    @ColumnInfo(name = "nsdod")
    private String nsdod;

    @ColumnInfo(name = "donor_type")
    private String donor_type;

    @ColumnInfo(name = "nsdod_plasma")
    private String nsdod_plasma;

    @ColumnInfo(name = "respincode")
    private String respincode;

    @ColumnInfo(name = "will_wed_day")
    private String will_wed_day;

    @ColumnInfo(name = "resaddress")
    private String resaddress;

    @ColumnInfo(name = "lastdod_platelet")
    private String lastdod_platelet;

    @ColumnInfo(name = "will_term")
    private String will_term;

    @ColumnInfo(name = "bloodgroup")
    private String bloodgroup;

    @ColumnInfo(name = "willl_bday")
    private String willl_bday;

    @ColumnInfo(name = "lastdod_plasma")
    private String lastdod_plasma;

    @ColumnInfo(name = "restaluk")
    private String restaluk;

    @ColumnInfo(name = "gender")
    private String gender;

    @ColumnInfo(name = "resmobile")
    private String resmobile;

    @ColumnInfo(name = "dor")
    private String dor;

    @ColumnInfo(name = "officetaluk")
    private String officetaluk;

    @ColumnInfo(name = "resarea")
    private String resarea;

    @ColumnInfo(name = "nsdod_doubleredcells")
    private String nsdod_doubleredcells;

    @ColumnInfo(name = "officeemail")
    private String officeemail;

    @ColumnInfo(name = "resbuildingname")
    private String resbuildingname;

    @ColumnInfo(name = "lastdod_doubleredcells")
    private String lastdod_doubleredcells;

    @ColumnInfo(name = "motivated_by")
    private String motivated_by;

    @ColumnInfo(name = "nsdod_platelet")
    private String nsdod_platelet;

    @ColumnInfo(name = "nsdodaph")
    private String nsdodaph;

    @ColumnInfo(name = "officedistrict")
    private String officedistrict;

    @ColumnInfo(name = "will_oth_day")
    private String will_oth_day;

    @ColumnInfo(name = "resdistrict")
    private String resdistrict;

    @ColumnInfo(name = "resemail")
    private String resemail;

    @ColumnInfo(name = "officevillageortownorcity")
    private String officevillageortownorcity;

    @ColumnInfo(name = "spousename")
    private String spousename;

    @ColumnInfo(name = "officemobile")
    private String officemobile;

    @ColumnInfo(name = "officedoornoandstreetorroad")
    private String officedoornoandstreetorroad;

    @ColumnInfo(name = "lastdod_wb")
    private String lastdod_wb;

    @ColumnInfo(name = "officebuildingname")
    private String officebuildingname;

    @ColumnInfo(name = "officephone")
    private String officephone;

    @ColumnInfo(name = "resphone")
    private String resphone;

    @ColumnInfo(name = "officearea")
    private String officearea;

    @NonNull
    public String getDonorid() {
        return donorid;
    }

    public void setDonorid(@NonNull String donorid) {
        this.donorid = donorid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getOfficepincode() {
        return officepincode;
    }

    public void setOfficepincode(String officepincode) {
        this.officepincode = officepincode;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getOfficeaddress() {
        return officeaddress;
    }

    public void setOfficeaddress(String officeaddress) {
        this.officeaddress = officeaddress;
    }

    public String getRhtype() {
        return rhtype;
    }

    public void setRhtype(String rhtype) {
        this.rhtype = rhtype;
    }

    public String getResvillageortownorcity() {
        return resvillageortownorcity;
    }

    public void setResvillageortownorcity(String resvillageortownorcity) {
        this.resvillageortownorcity = resvillageortownorcity;
    }

    public String getNsdod_wb() {
        return nsdod_wb;
    }

    public void setNsdod_wb(String nsdod_wb) {
        this.nsdod_wb = nsdod_wb;
    }

    public String getResdoornoandstreetorroad() {
        return resdoornoandstreetorroad;
    }

    public void setResdoornoandstreetorroad(String resdoornoandstreetorroad) {
        this.resdoornoandstreetorroad = resdoornoandstreetorroad;
    }

    public String getReg_centre() {
        return reg_centre;
    }

    public void setReg_centre(String reg_centre) {
        this.reg_centre = reg_centre;
    }

    public String getNsdod() {
        return nsdod;
    }

    public void setNsdod(String nsdod) {
        this.nsdod = nsdod;
    }

    public String getDonor_type() {
        return donor_type;
    }

    public void setDonor_type(String donor_type) {
        this.donor_type = donor_type;
    }

    public String getNsdod_plasma() {
        return nsdod_plasma;
    }

    public void setNsdod_plasma(String nsdod_plasma) {
        this.nsdod_plasma = nsdod_plasma;
    }

    public String getRespincode() {
        return respincode;
    }

    public void setRespincode(String respincode) {
        this.respincode = respincode;
    }

    public String getWill_wed_day() {
        return will_wed_day;
    }

    public void setWill_wed_day(String will_wed_day) {
        this.will_wed_day = will_wed_day;
    }

    public String getResaddress() {
        return resaddress;
    }

    public void setResaddress(String resaddress) {
        this.resaddress = resaddress;
    }

    public String getLastdod_platelet() {
        return lastdod_platelet;
    }

    public void setLastdod_platelet(String lastdod_platelet) {
        this.lastdod_platelet = lastdod_platelet;
    }

    public String getWill_term() {
        return will_term;
    }

    public void setWill_term(String will_term) {
        this.will_term = will_term;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getWilll_bday() {
        return willl_bday;
    }

    public void setWilll_bday(String willl_bday) {
        this.willl_bday = willl_bday;
    }

    public String getLastdod_plasma() {
        return lastdod_plasma;
    }

    public void setLastdod_plasma(String lastdod_plasma) {
        this.lastdod_plasma = lastdod_plasma;
    }

    public String getRestaluk() {
        return restaluk;
    }

    public void setRestaluk(String restaluk) {
        this.restaluk = restaluk;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getResmobile() {
        return resmobile;
    }

    public void setResmobile(String resmobile) {
        this.resmobile = resmobile;
    }

    public String getDor() {
        return dor;
    }

    public void setDor(String dor) {
        this.dor = dor;
    }

    public String getOfficetaluk() {
        return officetaluk;
    }

    public void setOfficetaluk(String officetaluk) {
        this.officetaluk = officetaluk;
    }

    public String getResarea() {
        return resarea;
    }

    public void setResarea(String resarea) {
        this.resarea = resarea;
    }

    public String getNsdod_doubleredcells() {
        return nsdod_doubleredcells;
    }

    public void setNsdod_doubleredcells(String nsdod_doubleredcells) {
        this.nsdod_doubleredcells = nsdod_doubleredcells;
    }

    public String getOfficeemail() {
        return officeemail;
    }

    public void setOfficeemail(String officeemail) {
        this.officeemail = officeemail;
    }

    public String getResbuildingname() {
        return resbuildingname;
    }

    public void setResbuildingname(String resbuildingname) {
        this.resbuildingname = resbuildingname;
    }

    public String getLastdod_doubleredcells() {
        return lastdod_doubleredcells;
    }

    public void setLastdod_doubleredcells(String lastdod_doubleredcells) {
        this.lastdod_doubleredcells = lastdod_doubleredcells;
    }

    public String getMotivated_by() {
        return motivated_by;
    }

    public void setMotivated_by(String motivated_by) {
        this.motivated_by = motivated_by;
    }

    public String getNsdod_platelet() {
        return nsdod_platelet;
    }

    public void setNsdod_platelet(String nsdod_platelet) {
        this.nsdod_platelet = nsdod_platelet;
    }

    public String getNsdodaph() {
        return nsdodaph;
    }

    public void setNsdodaph(String nsdodaph) {
        this.nsdodaph = nsdodaph;
    }

    public String getOfficedistrict() {
        return officedistrict;
    }

    public void setOfficedistrict(String officedistrict) {
        this.officedistrict = officedistrict;
    }

    public String getWill_oth_day() {
        return will_oth_day;
    }

    public void setWill_oth_day(String will_oth_day) {
        this.will_oth_day = will_oth_day;
    }

    public String getResdistrict() {
        return resdistrict;
    }

    public void setResdistrict(String resdistrict) {
        this.resdistrict = resdistrict;
    }

    public String getResemail() {
        return resemail;
    }

    public void setResemail(String resemail) {
        this.resemail = resemail;
    }

    public String getOfficevillageortownorcity() {
        return officevillageortownorcity;
    }

    public void setOfficevillageortownorcity(String officevillageortownorcity) {
        this.officevillageortownorcity = officevillageortownorcity;
    }

    public String getSpousename() {
        return spousename;
    }

    public void setSpousename(String spousename) {
        this.spousename = spousename;
    }

    public String getOfficemobile() {
        return officemobile;
    }

    public void setOfficemobile(String officemobile) {
        this.officemobile = officemobile;
    }

    public String getOfficedoornoandstreetorroad() {
        return officedoornoandstreetorroad;
    }

    public void setOfficedoornoandstreetorroad(String officedoornoandstreetorroad) {
        this.officedoornoandstreetorroad = officedoornoandstreetorroad;
    }

    public String getLastdod_wb() {
        return lastdod_wb;
    }

    public void setLastdod_wb(String lastdod_wb) {
        this.lastdod_wb = lastdod_wb;
    }

    public String getOfficebuildingname() {
        return officebuildingname;
    }

    public void setOfficebuildingname(String officebuildingname) {
        this.officebuildingname = officebuildingname;
    }

    public String getOfficephone() {
        return officephone;
    }

    public void setOfficephone(String officephone) {
        this.officephone = officephone;
    }

    public String getResphone() {
        return resphone;
    }

    public void setResphone(String resphone) {
        this.resphone = resphone;
    }

    public String getOfficearea() {
        return officearea;
    }

    public void setOfficearea(String officearea) {
        this.officearea = officearea;
    }
}
