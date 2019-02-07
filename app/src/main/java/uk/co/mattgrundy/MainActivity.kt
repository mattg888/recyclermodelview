package uk.co.mattgrundy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import uk.co.mattgrundy.fragments.BasicListFragment
import uk.co.mattgrundy.fragments.RowInteractionListFragment
import uk.co.mattgrundy.recyclermodelview.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_standard.setOnClickListener {
            replaceFragment(BasicListFragment())
        }

        btn_interactive.setOnClickListener {
            replaceFragment(RowInteractionListFragment())
        }
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()

    }
}
