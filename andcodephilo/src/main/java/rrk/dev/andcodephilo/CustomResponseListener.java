package rrk.dev.andcodephilo;

import org.json.JSONObject;

/**
 * Created by RyoRyeong Kim on 2018-02-08.
 */

public interface CustomResponseListener extends CommonResponseListener {

    String RESPONSE_PARSING_ERROR ="9000";


    void responseData(JSONObject responseData);
}
