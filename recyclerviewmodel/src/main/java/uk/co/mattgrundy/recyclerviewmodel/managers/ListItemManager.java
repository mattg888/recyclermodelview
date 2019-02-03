package uk.co.mattgrundy.recyclerviewmodel.managers;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import uk.co.mattgrundy.recyclerviewmodel.models.ListItem;

/**
 * Created by Matthew Grundy on 09/10/2017.
 */
public class ListItemManager {
    public List<? extends ListItem> items;

    public ListItemManager() {
        items = new ArrayList<>();
    }

    public ListItemManager(ArrayList<? extends ListItem> itemsIn) {
        items = itemsIn;
    }

    public int getViewTypeForPosition(int pos) {
        return items.get(pos).getClass().hashCode();
    }

    public ListItem getObjectForItem(int itemType) {
        for (ListItem item : items) {
            if (item.getClass().hashCode() == itemType) {
                return item;
            }
        }

        return null;
    }

    public Class<? extends RecyclerView.ViewHolder> getViewHolderForItem(int itemType) {
        return getObjectForItem(itemType).viewHolder;
    }
}