package uk.co.mattgrundy.lib.viewholders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.mattgrundy.recyclermodelview.R

/**
 * Created by Matthew Grundy on 21/12/2018.
 */
class PigViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textTitle: TextView = itemView.findViewById(R.id.text_title)

}