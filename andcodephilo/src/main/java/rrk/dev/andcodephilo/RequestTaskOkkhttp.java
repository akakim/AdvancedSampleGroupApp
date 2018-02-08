package rrk.dev.andcodephilo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by RyoRyeong Kim on 2018-02-08.
 * AynchTask를 한번 execute하면 다시 객체를 생성하여 실행을해야만한다.
 * 그래서 reqeust를 여러번 날리는 거보다는 parameter를 여러가지를 입력 할 수 있도록
 */

public class RequestTaskOkkhttp extends AsyncTask<Request,Void,String> {

    OkHttpClient okHttpClient;

    // 보통의 request 는 URL 로써 구분이가므로 고유성이 있다.
    // 따라서 동적으로 key 값을 생성가능하다
    // response로 jsonObject에 담겨지게된다.
    JSONObject jsonObject;

    Context context;
    CustomResponseListener responseListener;
    RequestTaskOkkhttp(Context context,@NonNull CustomResponseListener responseData){
        this.context = context;
        this.responseListener = responseData;
        okHttpClient = new OkHttpClient();
        jsonObject = new JSONObject();


    }
    @Override
    protected String doInBackground(Request ... requests) {


        try {

            for (int k = 0; k< requests.length ; k++){
                Response response = okHttpClient.newCall(requests[k]).execute();
                jsonObject.put( requests[k].url().toString(), response.body().toString() );
            }


        }catch (IOException e ){
            e.printStackTrace();
            return CommonResponseListener.RESPONSE_ERROR_CODE;
        }catch (JSONException jsonException){
            jsonException.printStackTrace();
            return CommonResponseListener.RESPONSE_INNER_PARSING_ERROR;
        }


        return CommonResponseListener.RESPONSE_SUCEESS;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        if( s == null ){
            responseListener.onError(CommonResponseListener.RESPONSE_UNEXPEDTED_ERROR);
        }else {
            if( CommonResponseListener.RESPONSE_SUCEESS.equals(s)){
                responseListener.responseData( this.jsonObject );
            }else {
                responseListener.onError( s );
            }

        }

    }
}
