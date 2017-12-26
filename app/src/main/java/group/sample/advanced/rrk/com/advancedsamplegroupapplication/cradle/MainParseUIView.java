package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

import org.json.JSONObject;

public interface MainParseUIView {


    void onResumeRefresh();
    void showProgress(String message);
    void responseFailed(String result);
    void responseSuccess(String result, JSONObject jsonObject);

    void showMessage(String msg);
    void showAlert();

}
