package group.sample.advanced.rrk.com.advancedsamplegroupapplication.data;

import android.graphics.Shader;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-04-08
 * @since 0.0.1
 */

public class GradientData {

    private String simpleMemo;
    private Shader shader;


    public GradientData(){

    }
    public GradientData(String simpleMemo, Shader shader) {
        this.simpleMemo = simpleMemo;
        this.shader = shader;
    }

    public String getSimpleMemo() {
        return simpleMemo;
    }

    public void setSimpleMemo(String simpleMemo) {
        this.simpleMemo = simpleMemo;
    }

    public Shader getShader() {
        return shader;
    }

    public void setShader(Shader shader) {
        this.shader = shader;
    }
}
