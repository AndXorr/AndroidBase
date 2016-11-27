package android.base.adapter;

import android.app.FragmentManager;
import android.base.fragment.BaseFragment;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 9/2/16.
 *
 * @param <T> the type parameter
 */
public abstract class BaseFragmentStatePagerAdapter<T extends BaseFragment> extends FragmentStatePagerAdapter {
    private List<T> mList = new ArrayList<>();
    private Context mContext;
    private int mCount;

    /**
     * Instantiates a new Base fragment state pager adapter.
     *
     * @param fm      the fm
     * @param context the mContext
     */
    public BaseFragmentStatePagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.mContext = context;
        mCount = mList.size();
    }

    /**
     * Sets mList.
     *
     * @param items the items
     */
    public void setmList(List<T> items) {
        mList = new ArrayList<>(items);
        mCount = mList.size();
        this.notifyDataSetChanged();
    }

    /**
     * Gets mList.
     *
     * @return ListArray<T> mList
     */
    public List<T> getmList() {
        return mList;
    }

    /**
     * Gets mContext.
     *
     * @return the mContext
     */
    public Context getmContext() {
        return mContext;
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mCount;
    }
}
