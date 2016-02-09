package android.base.adapter;

import android.base.widget.BaseRecyclerView;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 2/12/15.
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    public interface OnItemClickListener {
        <T, VH> void onItemClick(BaseRecyclerView recyclerView, VH viewHolder, int position, T model);
    }

    public interface OnItemLongClickListener {
        <T, VH> boolean onItemLongClick(BaseRecyclerView recyclerView, VH viewHolder, int position, T model);
    }

    private List<T> list = new ArrayList<>();

    public void setList(@Nullable List<T> list) {
        if (list != null) {
            this.list = new ArrayList<>(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public T getItem(int pos) {
        return this.list.get(pos);
    }

    public void removeItem(int pos) {
        this.list.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }

    public void addItem(int pos, T item) {
        this.list.add(pos, item);
        notifyItemInserted(pos);
    }

    public void addItem(T item) {
        this.list.add(item);
        notifyItemInserted(list.size() - 1);
    }
}
