package rrk.dev.andcodephilo;

import org.json.JSONObject;

/**
 * Created by RyoRyeong Kim on 2018-02-08.
 */

public interface CommonResponseListener {

    String responseCode = "rescode";
    String responseMessage = "resmsg";


    String RESPONSE_SUCEESS = "0000";
    String NETWORK_STATUS_ERROR = "0001";
    String RESPONSE_ERROR_CODE = "0404";
    String RESPONSE_INNER_PARSING_ERROR = "0405";
    String RESPONSE_SERVER_ERROR = "0500";
    String RESPONSE_SQL_NOT_DELETED = "9001";
    String RESPONSE_UNEXPEDTED_ERROR= "9999";

    void onError(String errorCode);
    void responseStrData(String data);
}
