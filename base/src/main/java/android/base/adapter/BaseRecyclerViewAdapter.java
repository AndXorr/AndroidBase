package android.base.adapter;

import android.base.ui.widget.BaseRecyclerView;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.google.common.base.Optional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by clickapps on 2/12/15.
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;
    private Context context;

    public interface OnItemClickListener<T> {
        void onItemClick(BaseRecyclerView recyclerView, View view, int position, T model);
    }

    public interface OnItemLongClickListener<T> {
        boolean onItemLongClick(BaseRecyclerView recyclerView, View view, int position, T model);
    }

    private List<T> list = new ArrayList<>();

    public List<T> getList() {
        return list;
    }

    public void setList(@Nullable List<T> list) {
        if (list != null) {
            this.list = new ArrayList<>(list);
            notifyDataSetChanged();
        }
    }

    public void setOnItemClickListener(@NonNull OnItemClickListener l) {
        itemClickListener = l;
    }

    public void setOnItemLongClickListener(@NonNull OnItemLongClickListener l) {
        itemLongClickListener = l;
    }

    public Optional<OnItemClickListener> getItemClickListener() {
        Optional<OnItemClickListener> onItemClick = Optional.absent();
        onItemClick = Optional.fromNullable(itemClickListener).or(onItemClick);
        return onItemClick;
    }

    public Optional<OnItemLongClickListener> getItemLongClickListener() {
        Optional<OnItemLongClickListener> onItemLongClick = Optional.absent();
        onItemLongClick = Optional.fromNullable(itemLongClickListener).or(onItemLongClick);
        return onItemLongClick;
    }

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
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
