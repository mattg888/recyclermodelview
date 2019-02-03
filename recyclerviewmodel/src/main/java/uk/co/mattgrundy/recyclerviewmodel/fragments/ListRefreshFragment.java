package uk.co.mattgrundy.recyclerviewmodel.fragments;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler;
import uk.co.mattgrundy.recyclerviewmodel.BaseRecyclerAdapter;
import uk.co.mattgrundy.recyclerviewmodel.R;
import uk.co.mattgrundy.recyclerviewmodel.SimpleDividerItemDecoration;
import uk.co.mattgrundy.recyclerviewmodel.models.ListItem;

import java.util.List;

/**
 * Created by Matthew Grundy on 09/10/2017.
 */

public abstract class ListRefreshFragment
        extends ListFragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipe_container;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getView() == null ? inflater.inflate(R.layout.fragment_list,
                container,
                false) : getView();
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
