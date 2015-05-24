package pl.edu.pw.elka.pik.model;

/**
 * Created by Komatta on 2015-05-22.
 */
public class CategorySimpleItem {
    private int id;
    private String name;
    private boolean selected;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
