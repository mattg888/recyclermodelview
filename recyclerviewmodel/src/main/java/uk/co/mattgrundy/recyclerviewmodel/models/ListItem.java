package uk.co.mattgrundy.recyclerviewmodel.models;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

/**
 * Created by Matthew Grundy on 09/10/2017.
 */

public abstract class ListItem extends BaseModel {

    public transient Context context;

    public transient Class<RecyclerView.ViewHolder> viewHolder;

    public abstract void bindToHolder(RecyclerView.ViewHolder holder);

    public abstract int getLayoutId();

    public void onCreateViewHolder(RecyclerView.ViewHolder viewHolder) {
        // override point
    }

    public int getItemType() {
        return getClass().hashCode();
    }

    private transient OnRowInteractionListener onRowInteractionListener;

    private transient View.OnClickListener onClickListener = null;

    public void bindViewItemToClickListener(final View view) {
        if (onRowInteractionListener != null) {
            final ListItem self = this;

            if (onClickListener == null) {
                onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View clickedView) {
                        onRowInteractionListener.onRowItemClicked(self, clickedView.getId());
                    }
                };
            }

            view.setOnClickListener(onClickListener);
        } else {
            Timber.d("Trying to bind onClick to a null listener");
        }
    }

    public void setOnRowInteractionListener(OnRowInteractionListener onRowInteractionListenerIn) {
        onRowInteractionListener = onRowInteractionListenerIn;
    }

    public OnRowInteractionListener getOnRowInteractionListener() {
        return onRowInteractionListener;
    }

    public interface OnRowInteractionListener {
        void onRowItemClicked(ListItem self, int itemId);
    }
}
