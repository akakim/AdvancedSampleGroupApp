package tripath.com.kot.mvp

import android.content.Context

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 * reference : https://github.com/taehwandev/AndroidMVPSample
 */

interface MainContract{
    interface View{
        fun updateItems(items : ArrayList<ImageItem>,isClear : Boolean)

        fun showToast(pos : Int)
        fun notifyAdapter()
    }

    // 리스트로 표현하는 activity 화면에 넣어주기위한 Presenter
    interface Presenter{
        // Main Activity를 위한 변수
        var view :View
        var imageData : TaeWonImageRepository

        //
        var adapterModel : ImageAdapterContract.Model
        var adapterView : ImageAdapterContract.View?
        fun loadItem(context: Context, isClear: Boolean )
    }
}