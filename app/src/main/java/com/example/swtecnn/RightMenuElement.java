package com.example.swtecnn;

public class RightMenuElement {

    private String place;

    private boolean isEnabled;

    public RightMenuElement(String place, boolean isEnabled) {
        this.place = place;
        this.isEnabled = isEnabled;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
