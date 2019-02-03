package uk.co.mattgrundy.recyclerviewmodel.viewholders;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.mattgrundy.recyclerviewmodel.R;

/**
 * Created by Matthew Grundy on 09/10/2017.
 */

public class NoResultsViewHolder extends RecyclerView.ViewHolder {

    public TextView text_no_results;

    public NoResultsViewHolder(View itemView) {
        super(itemView);

        text_no_results = (TextView) itemView.findViewById(R.id.text_no_results);
    }
}