package uk.co.mattgrundy.recyclerviewmodel.models.form_items

import androidx.recyclerview.widget.RecyclerView
import uk.co.mattgrundy.lib.viewholders.PigViewHolder
import uk.co.mattgrundy.recyclermodelview.R
import uk.co.mattgrundy.recyclerviewmodel.models.ListItem

/**
 * Created by Matthew Grundy on 21/12/2018.
 */
class EditTextFormItem(
    val title: String
) : ListItem() {

    init {
        val viewHolderClass = PigViewHolder::class.java as Class<RecyclerView.ViewHolder>
        viewHolder = viewHolderClass
    }

    override fun bindToHolder(holder: RecyclerView.ViewHolder?) {
        val pigViewHolder: PigViewHolder = holder as PigViewHolder

        pigViewHolder.textTitle.setText(title)
    }

    override fun getLayoutId(): Int {
        return R.layout.row_pig_item
    }
}