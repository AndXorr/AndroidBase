package android.base.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by clickapps on 22/2/16.
 */
public class HeaderFooterAdapter<VH extends RecyclerView.ViewHolder, T> extends BaseRecyclerViewAdapter<VH, T> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    public interface onHeaderFooterAdapter<VH extends RecyclerView.ViewHolder> {
        void onCreateHeaderView(ViewGroup parent, int viewType);

        void onBindHeaderView(VH holder, int position);

        void onCreateFooterView(ViewGroup parent, int viewType);

        void onBindFooterView(VH holder, int position);

        void onCreateView(ViewGroup parent, int viewType);

        void onBindView(VH holder, int position);
    }

    private onHeaderFooterAdapter callback;

    public HeaderFooterAdapter(@NonNull onHeaderFooterAdapter l) {
        callback = l;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (callback == null)
            throw new RuntimeException("Callback is null");
        if (viewType == TYPE_HEADER) {
            callback.onCreateHeaderView(parent, viewType);
        } else if (viewType == TYPE_FOOTER) {
            callback.onCreateFooterView(parent, viewType);
        } else if (viewType == TYPE_ITEM) {
            callback.onCreateView(parent, viewType);
        }
        return null;
    }


    @SuppressWarnings("unchecked")
    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (callback == null)
            throw new RuntimeException("Callback is null");
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                callback.onBindHeaderView(holder, position);
                break;
            case TYPE_FOOTER:
                callback.onBindFooterView(holder, position);
                break;
            case TYPE_ITEM:
                callback.onBindView(holder, position);
                break;
            default:
                callback.onBindView(holder, position);
                break;
        }
    }

    //    need to override this method
    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == getList().size() + 1;
    }

    @Override
    public int getItemCount() {
        return getList().size() + 2;
    }

}
