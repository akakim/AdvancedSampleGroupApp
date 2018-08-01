package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.DummyAdapter
import kotlinx.android.synthetic.main.activity_drawer_coodinator_layout.*
import java.util.*


/**
 *  DrawerLayout과 CoordinatorLayout을 합쳐놓은 화면
 *  RecyclerView를 합쳐놓은 Layout
 */
class DrawerCoodinatorLayout : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_coodinator_layout)

        rvList.adapter = DummyAdapter(this, createDummyData() )
        rvList.layoutManager = LinearLayoutManager(this)


    }


    fun createDummyData() : List<String>{

        val listData = ArrayList<String>()
        var count = 0
        while( count <=20 ){


            listData.add("Dummy value : " + count )

            count = count.inc()
        }

        return listData
    }
}
