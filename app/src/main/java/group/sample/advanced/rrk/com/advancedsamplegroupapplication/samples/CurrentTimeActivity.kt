package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R
import kotlinx.android.synthetic.main.item_custom_circle.*
import java.text.SimpleDateFormat
import java.util.*

class CurrentTimeActivity : AppCompatActivity() {


    val simpleDateFormed = SimpleDateFormat("yyyy.MM.dd hh:mm:ss")


    lateinit var tvInitFormed       : TextView
    lateinit var tvInitNotForemd    : TextView

    lateinit var tvRefershTimeFormed : TextView
    lateinit var tvRefershTimeNotFormed : TextView


    lateinit var tvDiffTimeFormed : TextView
    lateinit var tvDiffTimeNotFormed : TextView



    lateinit var btnTimeRefresh     : Button


    var currentInitValue : Long = System.currentTimeMillis()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_time)


//        var currentInitValue : Long = SystemClock.currentThreadTimeMillis()


        tvInitFormed = findViewById( R.id.tvInitTimeFormed )
        tvInitNotForemd = findViewById( R.id.tvInitTimeNotFormed )


        tvRefershTimeFormed = findViewById( R.id.tvRefreshTimeFormed )
        tvRefershTimeNotFormed = findViewById( R.id.tvRefreshTimeNotFormed )


        tvDiffTimeFormed = findViewById( R.id.tvDiffTimeFormed )
        tvDiffTimeNotFormed = findViewById( R.id.tvDiffTimeNotFormed )






        tvInitFormed.text = simpleDateFormed.format(  Date(currentInitValue) )
        tvInitNotForemd.text = currentInitValue.toString()


        btnTimeRefresh = findViewById(R.id.btnTimeRefresh)


        btnTimeRefresh.setOnClickListener {

            var clicked : Long = System.currentTimeMillis()

            val diff  = clicked - currentInitValue

            tvRefershTimeFormed.text = simpleDateFormed.format(  Date(clicked) )
            tvRefershTimeNotFormed.text = clicked.toString()




            tvDiffTimeFormed.text = simpleDateFormed.format(  Date(diff) )
            tvDiffTimeNotFormed.text = diff.toString()


        }

    }
}
