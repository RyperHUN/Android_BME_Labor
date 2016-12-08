package ryper.homeworkimprovement.DB;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;

import ryper.homeworkimprovement.R;

/**
 * Created by Ryper on 2016. 10. 22..
 */
public class Categories {
    static private HashMap<String, String> categoriesKeyValue = new HashMap<>();
    static private HashMap<String, String> categoriesValueKey = new HashMap<>();
    static private Context Context;

    static final public String AKey = "A";
    static final public String BKey = "B";
    static final public String CKey = "C";
    static final public String DKey = "D";
    //Values
    static private String AValue = "MustToday"; //TODO extract string resource -> Don't work
    static private String BValue = "MaybeToday";
    static private String CValue = "Later";
    static private String DValue = "DontForget";
    //Minimum 4 categoriesKeyValue, can be expanded

    //You have to call this method in the starting activity
    static public void InitCategories (Context context)
    {
        Context = context;
        AValue = context.getResources().getString(R.string.category_AKey);
        BValue = context.getResources().getString(R.string.category_BKey);
        CValue = context.getResources().getString(R.string.category_CKey);
        DValue = context.getResources().getString(R.string.category_DKey);

        categoriesKeyValue.put (AKey , AValue);
        categoriesKeyValue.put (BKey , BValue);
        categoriesKeyValue.put (CKey , CValue);
        categoriesKeyValue.put (DKey , DValue);

        categoriesValueKey.put (AValue, AKey);
        categoriesValueKey.put (BValue, BKey);
        categoriesValueKey.put (CValue, CKey);
        categoriesValueKey.put (DValue, DKey);
    }

    private static void InitCheck() {
        if (categoriesKeyValue.size () == 0 || Context == null)
            throw new RuntimeException("Categories is not initialized, call Categories.Init with context");
    }

    // By key
    static public boolean isValidCategory (String key)
    {
        InitCheck ();

        if (categoriesKeyValue.containsKey (key))
            return true;

        return false;
    }


    static public String getCategory (String key)
    {
        InitCheck ();

        return categoriesKeyValue.get(key);
    }

    static public String getKey (String category)
    {
        InitCheck ();

        return categoriesValueKey.get(category);
    }

    static public String[] getCategoriesStringArray ()
    {
        InitCheck ();

        String[] stringArray = new String[categoriesKeyValue.size()];
        stringArray[0] = categoriesKeyValue.get(AKey);
        stringArray[1] = categoriesKeyValue.get(BKey);
        stringArray[2] = categoriesKeyValue.get(CKey);
        stringArray[3] = categoriesKeyValue.get(DKey);

        return stringArray;
    }


}
