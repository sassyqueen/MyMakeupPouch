package sg.edu.rp.g913.mymakeuppouch;

/**
 * Created by 15004557 on 9/11/2016.
 */

public class Lipsticks{
    public String lipproduct;
    public String lipdate;
    public String dateChoice;

    public Lipsticks(String lipproduct, String lipdate, String dateChoice) {
        this.lipproduct = lipproduct;
        this.lipdate = lipdate;
        this.dateChoice = dateChoice;
    }

    public String getLipproduct() {
        return lipproduct;
    }

    public void setLipproduct(String lipproduct) {
        this.lipproduct = lipproduct;
    }

    public String getLipdate() {
        return lipdate;
    }

    public void setLipdate(String lipdate) {
        this.lipdate = lipdate;
    }

    public String getDateChoice() {
        return dateChoice;
    }

    public void setDateChoice(String dateChoice) {
        this.dateChoice = dateChoice;
    }

    @Override
    public String toString() {
        return lipproduct;
    }
}
