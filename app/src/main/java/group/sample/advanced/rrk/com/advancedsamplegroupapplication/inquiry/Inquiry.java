package group.sample.advanced.rrk.com.advancedsamplegroupapplication.inquiry;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.Nullable;



/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-16
 * @since 0.0.1
 * sqlite로부터 캐싱이나 기타등등을 하는 것..
 *
 */

public class Inquiry extends InquiryBase {
    Inquiry(Context context) throws IllegalAccessException {
        super(context);
    }

    Context context;
    Handler handler;
    @Nullable
    String databaseName;
    private int databaseVersion = 1;
    private String instanceName;
    private SQLiteHelper databaseHelper;


//    public SQLiteHelper _getDatabase(){
//        if(databaseHelper == null){
//            if( databaseName == null || databaseName.trim().isEmpty() ){
//                throw new IllegalStateException(" you must initialize your inquiry instance with a non-null database name");
//                databaseHelper = new SQLiteHelper( context,databaseName,databaseVersion );
//            }
//        }
//    }
}
