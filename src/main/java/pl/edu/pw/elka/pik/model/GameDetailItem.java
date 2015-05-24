package pl.edu.pw.elka.pik.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Komatta on 2015-05-22.
 */
public class GameDetailItem  extends GameSimpleItem
{
    String description;
    List<Integer> categorys;

    public List<Integer> getCategory()
    {
        if (categorys == null)
        {
            categorys = new ArrayList<Integer>();
        }
        return categorys;
    }

    public void setCategory(List<Integer> category)
    {
        this.categorys = category;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
