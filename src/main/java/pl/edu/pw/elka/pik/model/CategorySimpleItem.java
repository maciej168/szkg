package pl.edu.pw.elka.pik.model;

import pl.edu.pw.elka.pik.model.db.Category;

import java.io.Serializable;

/**
 * Created by Komatta on 2015-05-22.
 */
public class CategorySimpleItem implements Serializable {
    private int id;
    private String name;
    private boolean selected;

    public CategorySimpleItem(){}
    public CategorySimpleItem(Category cat){
        setId(cat.getId());
        setName(cat.getName());
    }

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
