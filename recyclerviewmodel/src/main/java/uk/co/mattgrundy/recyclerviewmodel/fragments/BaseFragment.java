package uk.co.mattgrundy.recyclerviewmodel.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

/**
 * Created by matt on 24/10/2016.
 */

public abstract class BaseFragment extends Fragment {

    public boolean shouldShowUpNavigation = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle(getViewTitle());
    }

    @Override
    public void setMenuVisibility(final boolean visible) {
        super.setMenuVisibility(visible);
        if (visible) {
//            getActivity().setTitle(getViewTitle());
        }
    }

    @Override
    public void onResume(){
        super.onResume();

        getActivity().setTitle(getViewTitle());
    }

    public abstract String getViewTitle();
}
