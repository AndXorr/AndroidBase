package android.base.interfaces;

import android.view.View;

/** This interface used as a callback for the list item view click */
public interface OnListItemClickProcess {
	/**
	 * call back for the list item click
	 * @param <T> array list
	 * 
	 * @param view
	 *            List row/item view
	 * @param position
	 *            item position in the list
	 * @param listId
	 *            integer value to identify listview
	 * */
	<T> void onItemClick(View view, int position, int listId, T arrayList);
}
