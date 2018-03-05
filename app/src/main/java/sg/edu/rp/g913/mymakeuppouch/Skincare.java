package sg.edu.rp.g913.mymakeuppouch;

/**
 * Created by 15004557 on 9/11/2016.
 */

public class Skincare {
    public String skincare;
    public String date;
    public String choice;


    public Skincare(String skincare, String date, String choice) {
        this.skincare = skincare;
        this.date = date;
        this.choice = choice;
    }

    public String getSkincare() {
        return skincare;
    }

    public void setSkincare(String skincare) {
        this.skincare = skincare;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }



    @Override
    public String toString() {
        return skincare;
    }
}
