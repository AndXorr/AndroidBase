package android.base.adapter;

import android.app.FragmentManager;
import android.base.fragment.BaseFragment;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 9/2/16.
 */
public abstract class BaseFragmentStatePagerAdapter<T extends BaseFragment> extends FragmentStatePagerAdapter {
    public List<T> list = new ArrayList<>();
    private Context context;
    private int count;

    public BaseFragmentStatePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        count = list.size();
    }

    /**
     * @param items
     */
    public void setList(List<T> items) {
        list = new ArrayList<>(items);
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
}
