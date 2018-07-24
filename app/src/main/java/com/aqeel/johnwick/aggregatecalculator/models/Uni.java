package com.aqeel.johnwick.aggregatecalculator.models;



import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Uni {
    @NonNull
    @PrimaryKey
    private String uid;

    @ColumnInfo(name = "uni_Name")
    private String uniName;

    @ColumnInfo(name = "entry_Test_Weightage")
    private String entryTestWeightage;

    @ColumnInfo(name = "fsc_Weightage")
    private String fscWeightage;

    @ColumnInfo(name = "matric_Weightage")
    private String matricWeightage;

    public Uni(@NonNull String uid, String uniName, String entryTestWeightage, String fscWeightage, String matricWeightage) {
        this.uid = uid;
        this.uniName = uniName;
        this.entryTestWeightage = entryTestWeightage;
        this.fscWeightage = fscWeightage;
        this.matricWeightage = matricWeightage;
    }

    @NonNull
    public String getUid() {
        return uid;
    }

    public void setUid(@NonNull String uid) {
        this.uid = uid;
    }

    public String getUniName() {
        return uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getEntryTestWeightage() {
        return entryTestWeightage;
    }

    public void setEntryTestWeightage(String entryTestWeightage) {
        this.entryTestWeightage = entryTestWeightage;
    }

    public String getFscWeightage() {
        return fscWeightage;
    }

    public void setFscWeightage(String fscWeightage) {
        this.fscWeightage = fscWeightage;
    }

    public String getMatricWeightage() {
        return matricWeightage;
    }

    public void setMatricWeightage(String matricWeightage) {
        this.matricWeightage = matricWeightage;
    }
}

