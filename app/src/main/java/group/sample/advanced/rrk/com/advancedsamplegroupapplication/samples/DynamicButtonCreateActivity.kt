package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples

import android.content.res.TypedArray
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.CardView
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.TextView
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R
import kotlinx.android.synthetic.main.activity_dynamic_button_create.*

class DynamicButtonCreateActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_button_create)


//        val typedArray : TypedArray = obtainStyledAttributes()


//        for ( i  in 1.. 10 ){
//
//        <item name="android:layout_width">73.6dp</item>
//        <item name="android:layout_height">73.6dp</item>
//        <item name="android:background">@color/colorTealish</item>
//        <item name="android:textColor">@color/colorWhite</item>
//        <item name="android:textSize">25sp</item>
//
//            val typedarray = arrayOf( android.R.attr.layout_width,
//                    android.R.attr.layout_height,
//                    android.R.attr.background,
//                    android.R.attr.textColor,
//                    android.R.attr.textSize )
//
//            val typedArray = obtainStyledAttributes( R.style.tableOutFocusedLayoutStyle, typedarray)


        val width : Int = resources.displayMetrics.density.toInt() * 76

        val contentPadding : Int = resources.displayMetrics.density.toInt() * 10
        for ( k in 1 .. 10 ) {

            val button : CardView = CardView(this).apply{
                cardBackgroundColor = resources.getColorStateList(R.color.none_fill_button_selector, null)

                setContentPadding( contentPadding ,contentPadding, contentPadding , contentPadding)



                val txt : TextView = TextView( this@DynamicButtonCreateActivity )

                val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

                txt.layoutParams = layoutParams
                txt.text = k.toString()
                txt.setTextColor( resources.getColorStateList(R.color.none_fill_button_selector, null) )
                txt.setBackgroundColor( Color.WHITE )

                txt.textSize = 12f
                txt.gravity = Gravity.CENTER
                this@apply.addView( txt )


                setOnClickListener {
                    view ->  if ( view.isSelected ) {

                                        view.isSelected = false
                                        txt.isSelected = false
                                    }else {
                                        view.isSelected = true
                                        txt.isSelected = true
                                    }


                }

            }






            val density = resources.displayMetrics.density.toInt()
            val layoutParam =  RelativeLayout.LayoutParams(width, width)
            layoutParam.apply {
                leftMargin = ( width * k  - (56 * density ) )
                topMargin = density * ( 10 )

            }


            rlRoot.addView(button,layoutParam)

        }
//        }
    }
}
