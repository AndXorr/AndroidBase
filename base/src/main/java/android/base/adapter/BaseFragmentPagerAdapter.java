package android.base.adapter;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.base.fragment.BaseFragment;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 9/2/16.
 */
public abstract class BaseFragmentPagerAdapter<T extends BaseFragment> extends FragmentPagerAdapter {
    public List<T> list = new ArrayList<T>();
    private Context context;
    private int count;

    public BaseFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        count = list.size();
    }

    /**
     * @param items
     */
    public void setList(List<T> items) {
        list = new ArrayList<T>(items);
        count = list.size();
        this.notifyDataSetChanged();
    }

    /**
     * @return ListArray<T>
     */
    public List<T> getList() {
        return list;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
