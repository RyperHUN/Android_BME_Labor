package hu.bme.aut.a03_weatherinfo.Model;

import java.util.HashMap;

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
    static final private String AValue = "MustToday"; //TODO extract string resource
    static final private String BValue = "MaybeToday";
    static final private String CValue = "Later";
    static final private String DValue = "DontForget";
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
