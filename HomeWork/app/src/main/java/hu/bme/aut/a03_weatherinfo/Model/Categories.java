package hu.bme.aut.a03_weatherinfo.Model;

import java.util.HashMap;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class Categories {
    static private HashMap<String, String> categoriesKeyValue = new HashMap<>();
    static private HashMap<String, String> categoriesValueKey = new HashMap<>();

    static final public String AKey = "A";
    static final public String BKey = "B";
    static final public String CKey = "C";
    static final public String DKey = "D";
    //Values
    static private String AValue = "MustToday"; //TODO extract string resource
    static private String BValue = "MaybeToday";
    static private String CValue = "Later";
    static private String DValue = "DontForget";
    //Minimum 4 categoriesKeyValue, can be expanded

    static private void initCategories ()
    {
        categoriesKeyValue.put (AKey , AValue);
        categoriesKeyValue.put (BKey , BValue);
        categoriesKeyValue.put (CKey , CValue);
        categoriesKeyValue.put (DKey , DValue);

        categoriesValueKey.put (AValue, AKey);
        categoriesValueKey.put (BValue, BKey);
        categoriesValueKey.put (CValue, CKey);
        categoriesValueKey.put (DValue, DKey);
    }

    // By key
    static public boolean isValidCategory (String key)
    {
        if (categoriesKeyValue.size () == 0)
            initCategories();

        if (categoriesKeyValue.containsKey (key))
            return true;

        return false;
    }

    static public String getCategory (String key)
    {
        if (categoriesKeyValue.size () == 0)
            initCategories();

        return categoriesKeyValue.get(key);
    }

    static public String getKey (String category)
    {
        if (categoriesKeyValue.size () == 0)
            initCategories();

        return categoriesValueKey.get(category);
    }

    static public String[] getCategoriesStringArray ()
    {
        if (categoriesKeyValue.size () == 0)
            initCategories();

        String[] stringArray = new String[categoriesKeyValue.size()];
        stringArray[0] = categoriesKeyValue.get(AKey);
        stringArray[1] = categoriesKeyValue.get(BKey);
        stringArray[2] = categoriesKeyValue.get(CKey);
        stringArray[3] = categoriesKeyValue.get(DKey);

        return stringArray;
    }


}
