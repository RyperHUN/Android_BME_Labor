package hu.bme.aut.a03_weatherinfo.Model;

import java.util.HashMap;

import hu.bme.aut.a03_weatherinfo.R;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class Categories {
    static private HashMap<String, String> categories = new HashMap<>();

    static final public String AKey = "A";
    static final public String BKey = "B";
    static final public String CKey = "C";
    static final public String DKey = "D";
    //Values
    static private String AValue = "MustToday"; //TODO extract string resource
    static private String BValue = "MaybeToday";
    static private String CValue = "Later";
    static private String DValue = "DontForget";
    //Minimum 4 categories, can be expanded

    static private void initCategories ()
    {
        categories.put (AKey , AValue);
        categories.put (BKey , BValue);
        categories.put (CKey , CValue);
        categories.put (DKey , DValue);
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
