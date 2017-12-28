package tripath.com.kot.mvp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Log
import android.widget.ImageView
import java.lang.ref.WeakReference

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 */

class ImageAsynchronize(val context: Context, imageView: ImageView?): AsyncTask<Int,Void, Bitmap>(){


    val imageViewReference : WeakReference<ImageView?>

    init{
        imageViewReference = WeakReference(imageView)
    }


    override fun onPreExecute() {
        super.onPreExecute()
        Log.d(this.javaClass.simpleName,"onPreExecuted()")
        imageViewReference.get()?.setImageResource(0)
    }

    override fun doInBackground(vararg params: Int?): Bitmap {

        val options = BitmapFactory.Options()
        options.inSampleSize = 2

        return BitmapFactory.decodeResource(context.resources,params[0] as Int,options)
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)

        if (result == null){
            Log.e(javaClass.simpleName,"foo result Bitmap is null ");
        }
        result?.let { imageViewReference.get()?.setImageBitmap( result )}
    }
}