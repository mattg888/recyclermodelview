package uk.co.mattgrundy.recyclerviewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;
import uk.co.mattgrundy.recyclerviewmodel.R;
import uk.co.mattgrundy.recyclerviewmodel.managers.ListItemManager;
import uk.co.mattgrundy.recyclerviewmodel.models.ListItem;
import uk.co.mattgrundy.recyclerviewmodel.viewholders.NoResultsViewHolder;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew Grundy on 09/10/2017.
 */

public class BaseRecyclerAdapter
        extends RecyclerView.Adapter<RecyclerView.ViewHolder>
        implements View.OnClickListener, View.OnLongClickListener {

    private ListItemManager listItemMananger;

    private RecyclerView recyclerView;

    private OnItemClick onItemClickListener;
    private OnItemLongPress onItemLongPress;

    private static final int VIEW_TYPE_NO_RESULTS = 1;

    private String noResultsText = "No Results";

    public boolean hasHeaderRow = false;

    public BaseRecyclerAdapter(RecyclerView recyclerViewIn) {
        this(recyclerViewIn, new ListItemManager(), null);
    }

    public BaseRecyclerAdapter(RecyclerView recyclerViewIn, OnItemClick onItemClickListenerIn) {
        this(recyclerViewIn, new ListItemManager(), onItemClickListenerIn);
    }

    public BaseRecyclerAdapter(RecyclerView recyclerViewIn, ListItemManager listItemManangerIn) {
        this(recyclerViewIn, listItemManangerIn, null);
    }

    public BaseRecyclerAdapter(RecyclerView recyclerViewIn, ListItemManager listItemManangerIn, OnItemClick onItemClickListenerIn) {
        recyclerView = recyclerViewIn;
        listItemMananger = listItemManangerIn;
        onItemClickListener = onItemClickListenerIn;
        recyclerView.setAdapter(this);
    }

    public void updateItems(List<? extends ListItem> items) {
        updateItems(items, false);
    }

    public void addItems(List<? extends ListItem> items) {
        int startPos = listItemMananger.items.size();

        List<ListItem> itemsNew = new ArrayList<>();
        itemsNew.addAll(listItemMananger.items);
        itemsNew.addAll(items);

        listItemMananger.items = itemsNew;

        notifyItemRangeInserted(startPos, items.size());
    }

    public void addItems(int addAtIndex, List<? extends ListItem> items) {
        int startPos = addAtIndex;

        List<ListItem> itemsNew = new ArrayList<>();
        itemsNew.addAll(listItemMananger.items);
        itemsNew.addAll(addAtIndex, items);

        listItemMananger.items = itemsNew;

        notifyItemRangeInserted(startPos, items.size());
    }


    public void updateItems(List<? extends ListItem> items, boolean hasHeaderRowIn) {
        hasHeaderRow = hasHeaderRowIn;

        listItemMananger.items = items;
        recyclerView.removeAllViews();
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (listItemMananger.items.size() == 0 && viewType == VIEW_TYPE_NO_RESULTS) {

            Timber.d("Returning no results view for view type: " + viewType);

            View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.row_no_results, parent, false);

            return new NoResultsViewHolder(v);

        } else {
            ListItem item = listItemMananger.getObjectForItem(viewType);

            // create a new view
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(item.getLayoutId(), parent, false);

            Class<?> holderClass = listItemMananger.getViewHolderForItem(viewType);

            Constructor<?> ctor;
            Object vh = null;
            try {
                ctor = holderClass.getConstructor(new Class[]{View.class});
                vh = ctor.newInstance(new Object[]{v});
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) vh;
            item.onCreateViewHolder(viewHolder);

            return viewHolder;
        }
    }

    @Override
    public void onClick(View v) {
        int itemIndex = recyclerView.getChildLayoutPosition(v);

        if (onItemClickListener != null) {
            onItemClickListener.itemClicked(listItemMananger.items.get(itemIndex));
        } else {
            Timber.d("Item clicked but not handled");
        }

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NoResultsViewHolder) {
            NoResultsViewHolder noResultsViewHolder = (NoResultsViewHolder) holder;
            noResultsViewHolder.text_no_results.setText(getNoResultsText());

        } else {
            ListItem item = listItemMananger.items.get(position);
            item.bindToHolder(holder);

            holder.itemView.setOnClickListener(this);
            holder.itemView.setOnLongClickListener(this);

        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        if (listItemMananger.items.size() <= (hasHeaderRow ? 1 : 0)) {
            return (getNoResultsText() != null ? 1 : 0) + (hasHeaderRow ? 1 : 0);
        }

        return listItemMananger.items.size();
    }

    /*
    Returns the associated layout ID as recommended
     */
    @Override
    public int getItemViewType(int position) {
        if (position == 0 && hasHeaderRow) {
            return 0;
        }

        if (listItemMananger.items.size() == (hasHeaderRow ? 1 : 0)) {
            return VIEW_TYPE_NO_RESULTS;
        }

        int viewType = listItemMananger.getViewTypeForPosition(position);

        return viewType;
    }

    @Override
    public boolean onLongClick(View v) {
        int itemIndex = recyclerView.getChildLayoutPosition(v);

        if (onItemLongPress != null) {
            onItemLongPress.itemLongPressed(listItemMananger.items.get(itemIndex));
        } else {
            Timber.d("Item long clicked but not handled");
        }

        return true;
    }

    public void setOnItemLongPress(OnItemLongPress onItemLongPressIn) {
        onItemLongPress = onItemLongPressIn;
    }

    public interface OnItemClick {
        void itemClicked(ListItem listItem);
    }

    public interface OnItemLongPress {
        void itemLongPressed(ListItem listItem);
    }

    public String getNoResultsText() {
        return noResultsText;
    }

    public ListItemManager getListItemManager() {
        return listItemMananger;
    }


    public static class Builder {

        private BaseRecyclerAdapter builderInstance;

        public Builder(RecyclerView recyclerView) {
            builderInstance = new BaseRecyclerAdapter(recyclerView);
        }

        public Builder setOnRowClickListener(OnItemClick onRowClickListener) {
            builderInstance.onItemClickListener = onRowClickListener;
            return this;
        }

        public Builder setOnRowLongClickListener(OnItemLongPress onRowLongClickListener) {
            builderInstance.onItemLongPress = onRowLongClickListener;
            return this;
        }

        public Builder setListItemManager(ListItemManager listItemManager) {
            builderInstance.listItemMananger = listItemManager;
            return this;
        }

        public Builder setNoResultsText(String noResultsText) {
            builderInstance.noResultsText = noResultsText;
            return this;
        }

        /*
        public Builder setNoResultsRow(ListItem noResultsRow, Class<? extends RecyclerView.ViewHolder> noResultsViewHolder){
            builderInstance.
        }
        */

        public BaseRecyclerAdapter getBaseRecyclerAdapter() {
            return builderInstance;
        }

    }

}
