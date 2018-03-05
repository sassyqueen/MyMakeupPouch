package sg.edu.rp.g913.mymakeuppouch;

/**
 * Created by 15004557 on 9/11/2016.
 */

public class EyeEyebrowProducts {
    public String eyeandbrow;
    public String date;
    public String choice;


    public EyeEyebrowProducts(String eyeandbrow, String date, String choice) {
        this.eyeandbrow = eyeandbrow;
        this.date = date;
        this.choice = choice;
    }

    public String getEyeandbrow() {
        return eyeandbrow;
    }

    public void setEyeandbrow(String eyeandbrow) {
        this.eyeandbrow = eyeandbrow;
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
        return eyeandbrow;
    }
}
