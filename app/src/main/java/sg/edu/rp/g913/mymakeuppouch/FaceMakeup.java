package sg.edu.rp.g913.mymakeuppouch;

/**
 * Created by 15004557 on 9/11/2016.
 */

public class FaceMakeup {
    public String faceprodname;
    public String facedate;
    public String dateChoice;

    public FaceMakeup(String faceprodname, String facedate, String dateChoice) {
        this.faceprodname = faceprodname;
        this.facedate = facedate;
        this.dateChoice = dateChoice;
    }


    public String getFaceprodname() {
        return faceprodname;
    }

    public void setFaceprodname(String faceprodname) {
        this.faceprodname = faceprodname;
    }

    public String getFacedate() {
        return facedate;
    }

    public void setFacedate(String facedate) {
        this.facedate = facedate;
    }

    public String getDateChoice() {
        return dateChoice;
    }

    public void setDateChoice(String dateChoice) {
        this.dateChoice = dateChoice;
    }


    @Override
    public String toString() {
        return  faceprodname;
    }
}
