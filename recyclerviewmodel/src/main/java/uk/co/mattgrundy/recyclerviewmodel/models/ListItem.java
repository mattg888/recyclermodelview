package uk.co.mattgrundy.recyclerviewmodel.models;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

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

}
