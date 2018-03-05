package sg.edu.rp.g913.mymakeuppouch;

/**
 * Created by 15004557 on 30/10/2016.
 */

public class Makeup {
    public String makeupGroup;
    public String selectedCategory;
    public String description;


    public Makeup(String makeupGroup, String selectedCategory, String description) {
        this.makeupGroup = makeupGroup;
        this.selectedCategory = selectedCategory;
        this.description = description;
    }

    public String getMakeupGroup() {
        return makeupGroup;
    }

    public void setMakeupGroup(String makeupGroup) {
        this.makeupGroup = makeupGroup;
    }

    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
