package uk.co.mattgrundy.lib.viewholders

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.mattgrundy.recyclermodelview.R

/**
 * Created by Matthew Grundy on 21/12/2018.
 */
class PigInteractViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textTitle: TextView = itemView.findViewById(R.id.text_title)
    var btnInteract: Button = itemView.findViewById(R.id.btn_pig_interact)

}