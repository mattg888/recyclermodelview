package uk.co.mattgrundy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uk.co.mattgrundy.fragments.BasicListFragment
import uk.co.mattgrundy.recyclermodelview.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, BasicListFragment())
            .commit()
    }
}
