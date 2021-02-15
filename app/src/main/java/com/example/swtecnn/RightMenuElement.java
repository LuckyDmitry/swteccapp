package com.example.swtecnn;

public class RightMenuElement {

    private String place;

    private boolean isCheckBoxChecked;
    private boolean isRadioButtonEnabled;
    private boolean isRadioButtonChecked;
    public RightMenuElement(String place,
                            boolean isCheckBoxEnabled,
                            boolean isRadioButtonEnabled,
                            boolean isRadioButtonChecked) {
        this.place = place;
        this.isCheckBoxChecked = isCheckBoxEnabled;
        this.isRadioButtonEnabled = isRadioButtonEnabled;
        this.isRadioButtonChecked = isRadioButtonChecked;
    }

    public String getPlace() {
        return place;
    }

    public boolean isRadioButtonChecked() {
        return isRadioButtonChecked;
    }

    public void setRadioButtonChecked(boolean radioButtonChecked) {
        isRadioButtonChecked = radioButtonChecked;
    }

    public boolean isRadioButtonEnabled() {
        return isRadioButtonEnabled;
    }

    public void setRadioButtonEnabled(boolean radioButtonEnabled) {
        isRadioButtonEnabled = radioButtonEnabled;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isCheckBoxChecked() {
        return isCheckBoxChecked;
    }

    public void setCheckBoxChecked(boolean checkBoxChecked) {
        isCheckBoxChecked = checkBoxChecked;
    }
}
