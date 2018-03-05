package sg.edu.rp.g913.mymakeuppouch;

/**
 * Created by 15004557 on 9/11/2016.
 */

public class Tools {

    public String tool;
    public String date;
    public String choice;


    public Tools(String tool, String date, String choice) {
        this.tool = tool;
        this.date = date;
        this.choice = choice;
}

    public String getTool() {
        return tool;
    }

    public void setTool(String tool) {
        this.tool = tool;
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
        return tool;
    }


}
