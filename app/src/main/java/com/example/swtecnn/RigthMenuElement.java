package com.example.swtecnn;

import android.widget.CheckBox;

public class RigthMenuElement {

    String place;
    CheckBox checkBox;

    public RigthMenuElement(String place, CheckBox checkBox) {
        this.place = place;
        this.checkBox = checkBox;
    }

    public String getPlace() {
        return place;
    }

    public void setCheckBox(boolean flag) {
        this.checkBox.setChecked(flag);
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public CheckBox getCheckBox() {
        return checkBox;
    }
}
