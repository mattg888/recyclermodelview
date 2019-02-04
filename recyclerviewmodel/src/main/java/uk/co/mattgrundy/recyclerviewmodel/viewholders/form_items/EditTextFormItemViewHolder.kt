package uk.co.mattgrundy.recyclerviewmodel.viewholders.form_items

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import uk.co.mattgrundy.recyclermodelview.R

/**
 * Created by Matthew Grundy on 21/12/2018.
 */
class EditTextFormItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var textTitle: TextView = itemView.findViewById(R.id.text_title)

}