package android.base.adapter;

import android.app.FragmentManager;
import android.base.fragment.BaseFragment;
import android.content.Context;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 9/2/16.
 *
 * @param <T> the type parameter
 */
public abstract class BaseFragmentPagerAdapter<T extends BaseFragment> extends FragmentPagerAdapter {
    private List<T> list = new ArrayList<>();
    private Context context;
    private int count;

    /**
     * Instantiates a new Base fragment pager adapter.
     *
     * @param fm      the fm
     * @param context the context
     */
    public BaseFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
        count = list.size();
    }

    /**
     * Sets list.
     *
     * @param items the items
     */
    public void setList(List<T> items) {
        list = new ArrayList<>(items);
        count = list.size();
        this.notifyDataSetChanged();
    }

    /**
     * Gets list.
     *
     * @return ListArray<T> list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Gets context.
     *
     * @return the context
     */
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
