package hu.bme.aut.a03_weatherinfo.Model;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class Categories {
    static private HashMap<String, String> categories = new HashMap<>();

    //Values
    static final public String A = "MustToday"; //TODO extract string resource
    static final public String B = "MaybeToday";
    static final public String C = "Later";
    static final public String D = "DontForget";
    //Minimum 4 categories, can be expanded

    static private void initCategories ()
    {
        categories.put ("A", A);
        categories.put ("B", B);
        categories.put ("C", C);
        categories.put ("D", D);
    }

    // By key
    static public boolean isValidCategory (String key)
    {
        if (categories.size () == 0)
            initCategories();

        if (categories.containsKey (key))
            return true;

        return false;
    }

    static public String getCategory (String key)
    {
        if (categories.size () == 0)
            initCategories();
        
        return categories.get(key);
    }


}
