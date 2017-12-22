package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

public interface MainParseUIView {


    void onResumeRefresh();
    void showProgress(String message);
    void responseFailed(String result);
    void responseSuccess(String result,BoardData boardData);

    void showMessage(String msg);
    void showAlert();

}
