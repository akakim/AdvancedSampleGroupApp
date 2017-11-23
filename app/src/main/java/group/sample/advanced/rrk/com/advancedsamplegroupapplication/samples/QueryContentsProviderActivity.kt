package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import butterknife.BindView
import butterknife.ButterKnife
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter.ContentsAdapter
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.ContentsItem


// TODO : ContentsProvider를 이용한 Contetns 불러오기 구현

class QueryContentsProviderActivity : AppCompatActivity() {



    private lateinit var recyclerView : RecyclerView

    private lateinit var recyclerAdapter : ContentsAdapter

    private lateinit var items :MutableList<ContentsItem>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_query_contents_provider)

        ButterKnife.bind(this)

//        items = MutableList<ContentsItem>()
        recyclerView = findViewById<RecyclerView>(R.id.tvContentsProvider)



//        contentResolver.qu
//        recyclerAdapter = ContentsAdapter(this,)
//        recyclerView.adapter =
    }






}
