package uk.co.mattgrundy.fragments

import android.view.View
import android.widget.Toast
import uk.co.mattgrundy.lib.models.Pig
import uk.co.mattgrundy.recyclerviewmodel.BaseRecyclerAdapter
import uk.co.mattgrundy.recyclerviewmodel.fragments.ListInfiniteScrollRefreshFragment
import uk.co.mattgrundy.recyclerviewmodel.models.ListItem

class BasicListFragment : ListInfiniteScrollRefreshFragment() {

    var currentIndex = 0

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
            data.add(Pig("Pig " + i))
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
        return "Basic List"
    }

}
