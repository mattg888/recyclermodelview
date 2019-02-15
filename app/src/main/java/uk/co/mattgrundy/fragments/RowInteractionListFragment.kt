package uk.co.mattgrundy.fragments

import android.view.View
import android.widget.Toast
import uk.co.mattgrundy.lib.models.Pig
import uk.co.mattgrundy.lib.models.PigInteract
import uk.co.mattgrundy.recyclermodelview.R
import uk.co.mattgrundy.recyclerviewmodel.BaseRecyclerAdapter
import uk.co.mattgrundy.recyclerviewmodel.fragments.ListInfiniteScrollRefreshFragment
import uk.co.mattgrundy.recyclerviewmodel.models.ListItem

class RowInteractionListFragment : ListInfiniteScrollRefreshFragment() {

    var currentIndex = 0

    private val onRowInteractionListener = object : ListItem.OnRowInteractionListener {
        override fun onRowItemClicked(self: ListItem, itemId: Int) {
            if (itemId == R.id.btn_pig_interact) {
                Toast.makeText(context, "Clicked Row Item: $itemId (interact ID)", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun afterViews(v: View?, initialLoad: Boolean) {
        super.afterViews(v, initialLoad)

        getInitialItems()
    }

    private fun getInitialItems() {
        currentIndex = 0

        val data = arrayListOf<ListItem>()
        addPigs(data)
        updateData(data)
    }

    private fun addPigs(data: ArrayList<ListItem>) {
        for (i in currentIndex..currentIndex + 20) {
            val pig = PigInteract("Pig " + i)
            pig.onRowInteractionListener = onRowInteractionListener
            data.add(pig)
        }
    }

    override fun getListNoResultsTitle(): String {
        return "No Items"
    }

    override fun onListItemClickListener(): BaseRecyclerAdapter.OnItemClick {
        return BaseRecyclerAdapter.OnItemClick {
            if (it is Pig) {
                Toast.makeText(activity, "Clicked: " + it.title, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRefresh() {
        getInitialItems()
    }

    override fun loadMoreItems() {
        isLoading = false

        val data = arrayListOf<ListItem>()
        addPigs(data)
        addData(data)
    }

    override fun getViewTitle(): String {
        return "Row Interaction List"
    }

}
