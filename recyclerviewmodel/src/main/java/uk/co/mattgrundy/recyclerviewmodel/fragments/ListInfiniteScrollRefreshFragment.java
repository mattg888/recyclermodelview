package uk.co.mattgrundy.recyclerviewmodel.fragments;

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

public abstract class ListInfiniteScrollRefreshFragment
        extends ListInfiniteScrollFragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipe_container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getView() == null ?
                inflater.inflate(R.layout.fragment_list, container, false)
                : getView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipe_container = view.findViewById(R.id.swipe_container);
        if (swipe_container != null) {
            swipe_container.setOnRefreshListener(this);
        }

    }

}
