package hu.bme.aut.a03_weatherinfo.DB.Entities;

import com.orm.SugarRecord;

import hu.bme.aut.a03_weatherinfo.Model.Categories;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class TodoProgress extends SugarRecord{
    String desc;
    long   start; //TODO Start time
    String categoryKey;

    public TodoProgress() {}

    public TodoProgress(String desc, String categoryKey) throws Exception {
        this.desc     = desc;
        if (Categories.isValidCategory(categoryKey))
            this.categoryKey = categoryKey;
        else
            throw new Exception ("Now valid category");
        this.start    = System.currentTimeMillis();
    }
}
