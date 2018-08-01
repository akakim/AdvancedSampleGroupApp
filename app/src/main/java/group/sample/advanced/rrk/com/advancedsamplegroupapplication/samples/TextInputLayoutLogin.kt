package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R
import kotlinx.android.synthetic.main.activity_text_input_layout_login.*


/**
 *  icon의 틴트가 적용이 안되네..
 *
 *  포커싱이 들어갈 때마다 틴트가 안된다...
 */
class TextInputLayoutLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_input_layout_login)


        layoutPassword.setOnFocusChangeListener { v, hasFocus ->


            layoutPassword.editText?.invalidate()

        }
//        edPassword.onFocusChangeListener = object : View.OnFocusChangeListener{
//            override fun onFocusChange(v: View?, hasFocus: Boolean) {
//
//                edPassword.postInvalidate()
////                for( drawbleEd in edPassword.compoundDrawables){
////
////                }
////                edPassword.compoundDrawableTintMode.
//            }
//        }
    }
}
