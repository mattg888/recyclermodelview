package uk.co.mattgrundy.recyclerviewmodel.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.mattgrundy.recyclerviewmodel.BaseRecyclerAdapter;
import uk.co.mattgrundy.recyclerviewmodel.R;
import uk.co.mattgrundy.recyclerviewmodel.SimpleDividerItemDecoration;
import uk.co.mattgrundy.recyclerviewmodel.models.ListItem;

import java.util.List;

/**
 * Created by Matthew Grundy on 09/10/2017.
 */

public abstract class ListFragment
        extends Fragment {

    public boolean showLineDivider = false;

    protected RecyclerView main_list;
    private LinearLayoutManager linearLayoutManager;
    private BaseRecyclerAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getView() == null ? inflater.inflate(
                R.layout.fragment_list_no_refresh,
                container,
                false) : getView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        linearLayoutManager = getLayoutManager();

        main_list = view.findViewById(R.id.main_list);
        main_list.setLayoutManager(getLayoutManagerInstance());

        if (showLineDivider) {
            main_list.addItemDecoration(new SimpleDividerItemDecoration(getActivity()));
        }

        boolean initialLoad = adapter == null;

        if (initialLoad) {
            resetAdapter();
        }

        main_list.setAdapter(adapter);

        afterViews(view, initialLoad);

    }

    protected LinearLayoutManager getLayoutManagerInstance() {
        if (linearLayoutManager != null) {
            return linearLayoutManager;
        }

        return getLayoutManager();
    }


    protected LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(getActivity());
    }

    protected BaseRecyclerAdapter getAdapter() {
        return adapter;
    }
    
    protected void resetAdapter() {
        adapter = new BaseRecyclerAdapter(main_list, onListItemClickListener()) {
            @Override
            public String getNoResultsText() {
                return getListNoResultsTitle();
            }
        };
    }

    public void updateData(List<? extends ListItem> items) {
        resetAdapter();
        adapter.updateItems(items);
    }

    public void addData(List<? extends ListItem> items) {
        adapter.addItems(items);
    }

    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    public void afterViews(View v, boolean initialLoad) {

    }

    public List<? extends ListItem> getItems() {
        return adapter.getListItemManager().items;
    }

    public abstract String getListNoResultsTitle();

    public abstract BaseRecyclerAdapter.OnItemClick onListItemClickListener();

    // Override point for no results view
    public int getNoResultsView() {
        return 0;
    }
}
