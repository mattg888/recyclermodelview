package uk.co.mattgrundy.lib.models

import androidx.recyclerview.widget.RecyclerView
import uk.co.mattgrundy.lib.viewholders.PigInteractViewHolder
import uk.co.mattgrundy.recyclermodelview.R
import uk.co.mattgrundy.recyclerviewmodel.models.ListItem

/**
 * Created by Matthew Grundy on 21/12/2018.
 */
class PigInteract(
    val title: String
) : ListItem() {

    init {
        val viewHolderClass = PigInteractViewHolder::class.java as Class<RecyclerView.ViewHolder>
        viewHolder = viewHolderClass
    }

    override fun bindToHolder(holder: RecyclerView.ViewHolder?) {
        val pigInteractViewHolder: PigInteractViewHolder = holder as PigInteractViewHolder

        pigInteractViewHolder.textTitle.setText(title)
        bindViewItemToClickListener(pigInteractViewHolder.btnInteract)
    }

    override fun getLayoutId(): Int {
        return R.layout.row_pig_interaction_item
    }
}