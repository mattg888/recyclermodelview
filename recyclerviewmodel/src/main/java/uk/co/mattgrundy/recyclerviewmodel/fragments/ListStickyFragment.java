package uk.co.mattgrundy.recyclerviewmodel.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.brandongogetap.stickyheaders.StickyLayoutManager;
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler;
import uk.co.mattgrundy.recyclerviewmodel.R;

import java.util.List;

/**
 * Created by Matthew Grundy on 09/10/2017.
 */

public abstract class ListStickyFragment
        extends ListFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return getView() == null ?
                inflater.inflate(R.layout.fragment_list, container, false)
                : getView();
    }

    @Override
    protected LinearLayoutManager getLayoutManager() {
        StickyLayoutManager stickyLayoutManager = new StickyLayoutManager(
                getActivity(),
                new StickyHeaderHandler() {
                    @Override
                    public List<?> getAdapterData() {
                        return getItems();
                    }
                });
        stickyLayoutManager.elevateHeaders(true);

        return stickyLayoutManager;
    }
}
