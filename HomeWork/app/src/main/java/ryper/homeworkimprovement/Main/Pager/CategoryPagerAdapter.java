package ryper.homeworkimprovement.Main.Pager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import ryper.homeworkimprovement.DB.Categories;

/**
 * Created by Ryper on 2016. 12. 08..
 */
public class CategoryPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    ArrayList<TodoCategoryFragment> fragments = null;

    public CategoryPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        fragments = new ArrayList<>();
        this.context = context;
    }

    // Ez a függvény csak egyszer hívódik meg, nem fog feleslegesen sok
// ugyanolyan Fragment-et létrehozni!
    @Override
    public Fragment getItem(int position) {
        Fragment ret = null;
        TodoCategoryFragment fragment = null;
        switch (position) {
            case 0:
                fragment = new TodoCategoryFragment().Init(context, Categories.AKey);
                ret = fragment;
                break;
            case 1:
                fragment = new TodoCategoryFragment().Init(context, Categories.BKey);
                ret = fragment;
                break;
            case 2:
                fragment = new TodoCategoryFragment().Init(context, Categories.CKey);
                ret = fragment;
                break;
            case 3:
                fragment = new TodoCategoryFragment().Init(context, Categories.DKey);
                ret = fragment;
                break;
        }
        if (fragment != null)
            fragments.add(fragment);
        return ret;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position) {
            case 0:
                title = Categories.getCategory(Categories.AKey);
                break;
            case 1:
                title = Categories.getCategory(Categories.BKey);
                break;
            case 2:
                title = Categories.getCategory(Categories.CKey);
                break;
            case 3:
                title = Categories.getCategory(Categories.DKey);
                break;
            default:
                title = "";
        }
        return title;
    }

    @Override
    public int getCount() {
        return 4;
    }

    public void refresh() {
        for (TodoCategoryFragment fragment : fragments)
            fragment.refreshAdapterDataFromDB();
    }
}

