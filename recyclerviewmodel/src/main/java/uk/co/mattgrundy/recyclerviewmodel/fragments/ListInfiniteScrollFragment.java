package uk.co.mattgrundy.recyclerviewmodel.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import uk.co.mattgrundy.recyclerviewmodel.R;

/**
 * Created by Matthew Grundy on 09/10/2017.
 */

public abstract class ListInfiniteScrollFragment
        extends ListFragment {

    // debounce the recyclerview scroll
    public boolean isLoading = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getView() == null ?
                inflater.inflate(R.layout.fragment_list_no_refresh, container, false)
                : getView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            main_list.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    int visibleItemCount = getLayoutManager().getChildCount();
                    int totalItemCount = getLayoutManager().getItemCount();
                    int pastVisibleItems = getLayoutManager().findFirstVisibleItemPosition();
                    if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                        if (!isLoading) {
                            loadMoreItems();
                        }
                    }
                }
            });
        }
    }

    public abstract void loadMoreItems();
}
