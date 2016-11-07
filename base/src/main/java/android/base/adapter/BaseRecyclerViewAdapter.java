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
 *
 * @param <VH> the type parameter
 * @param <T>  the type parameter
 */
public abstract class BaseRecyclerViewAdapter<VH extends RecyclerView.ViewHolder, T> extends RecyclerView.Adapter<VH> {

    private OnItemClickListener itemClickListener;
    private OnItemLongClickListener itemLongClickListener;
    private Context context;

    /**
     * Instantiates a new Base recycler view adapter.
     *
     * @param context the context
     */
    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    /**
     * The interface On item click listener.
     *
     * @param <T> the type parameter
     */
    public interface OnItemClickListener<T> {
        /**
         * On item click.
         *
         * @param recyclerView the recycler view
         * @param view         the view
         * @param position     the position
         * @param model        the model
         */
        void onItemClick(BaseRecyclerView recyclerView, View view, int position, T model);
    }

    /**
     * The interface On item long click listener.
     *
     * @param <T> the type parameter
     */
    public interface OnItemLongClickListener<T> {
        /**
         * On item long click boolean.
         *
         * @param recyclerView the recycler view
         * @param view         the view
         * @param position     the position
         * @param model        the model
         * @return the boolean
         */
        boolean onItemLongClick(BaseRecyclerView recyclerView, View view, int position, T model);
    }

    private List<T> list = new ArrayList<>();

    /**
     * Gets list.
     *
     * @return the list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Sets list.
     *
     * @param list the list
     */
    public void setList(@Nullable List<T> list) {
        if (list != null) {
            this.list = new ArrayList<>(list);
            notifyDataSetChanged();
        }
    }

    /**
     * Sets on item click listener.
     *
     * @param l the l
     */
    public void setOnItemClickListener(@NonNull OnItemClickListener l) {
        itemClickListener = l;
    }

    /**
     * Sets on item long click listener.
     *
     * @param l the l
     */
    public void setOnItemLongClickListener(@NonNull OnItemLongClickListener l) {
        itemLongClickListener = l;
    }

    /**
     * Gets item click listener.
     *
     * @return the item click listener
     */
    public Optional<OnItemClickListener> getItemClickListener() {
        Optional<OnItemClickListener> onItemClick = Optional.absent();
        onItemClick = Optional.fromNullable(itemClickListener).or(onItemClick);
        return onItemClick;
    }

    /**
     * Gets item long click listener.
     *
     * @return the item long click listener
     */
    public Optional<OnItemLongClickListener> getItemLongClickListener() {
        Optional<OnItemLongClickListener> onItemLongClick = Optional.absent();
        onItemLongClick = Optional.fromNullable(itemLongClickListener).or(onItemLongClick);
        return onItemLongClick;
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

    /**
     * Gets item.
     *
     * @param pos the pos
     * @return the item
     */
    public T getItem(int pos) {
        return this.list.get(pos);
    }

    /**
     * Remove item.
     *
     * @param pos the pos
     */
    public void removeItem(int pos) {
        this.list.remove(pos);
        notifyItemRemoved(pos);
        notifyDataSetChanged();
    }

    /**
     * Add item.
     *
     * @param pos  the pos
     * @param item the item
     */
    public void addItem(int pos, T item) {
        this.list.add(pos, item);
        notifyItemInserted(pos);
    }

    /**
     * Add item.
     *
     * @param item the item
     */
    public void addItem(T item) {
        this.list.add(item);
        notifyItemInserted(list.size() - 1);
    }
}
