package group.sample.advanced.rrk.com.advancedsamplegroupapplication.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.os.Message

/**
 * Created by RyoRyeong Kim on 2018-01-24.
 */

class SampleService : Service(){


    companion object {
        val SHOW_WINODW_MESSAGE : Int = 1000
        val SHOW_WINDOW_ALERT : String = "showWindowAlert"
        val SHOW_BTN_ALERT_ACTION = "group.sample.advanced.rrk.com.advancedsamplegroupapplication.service.SampleService.SHOW_BTN_ALERT_ACTION"
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun onCreate() {
        super.onCreate()
    }


    fun sendIntentMessage(i : Intent ){

    }
//    class RemoteHanlder : Handler() {
//        override fun handleMessage(msg: Message?) {
//            super.handleMessage(msg)
//
//            when( msg?.what ){
//
//                SHOW_WINODW_MESSAGE -> {
//
//                }
//                else ->
//            }
//
//        }
//    }
}