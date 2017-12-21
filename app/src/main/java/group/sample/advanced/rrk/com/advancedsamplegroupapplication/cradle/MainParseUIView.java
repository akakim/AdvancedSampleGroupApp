package group.sample.advanced.rrk.com.advancedsamplegroupapplication.cradle;

public interface MainParseUIView {

    void initContent();

    void showProgress(String message);
    void responseFailed(String result);
    void responseSuccess(String result);

    void showMessage(String msg);
    void showAlert();

}
