package com.swtec.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "watering_place")
public class WateringPlace {

    private boolean mIsCheckBoxChecked;

    private boolean mIsRadioButtonChecked;

    private final String mPlaceName;

    public WateringPlace(@NonNull String placeName, boolean isCheckBoxChecked, boolean isRadioButtonChecked){
        mPlaceName = placeName;
        mIsCheckBoxChecked = isCheckBoxChecked;
        mIsRadioButtonChecked = isRadioButtonChecked;
    }

    public boolean isCheckBoxChecked() {
        return mIsCheckBoxChecked;
    }

    public void setIsCheckBoxChecked(boolean mIsCheckBoxChecked) {
        this.mIsCheckBoxChecked = mIsCheckBoxChecked;
    }

    public void setIsRadioButtonChecked(boolean mIsRadioButtonChecked) {
        this.mIsRadioButtonChecked = mIsRadioButtonChecked;
    }

    public boolean isRadioButtonChecked() {
        return mIsRadioButtonChecked;
    }

    public String getPlaceName() {
        return mPlaceName;
    }
}
