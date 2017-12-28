package tripath.com.kot.mvp

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 * reference : https://github.com/taehwandev/AndroidMVPSample
 */
class ImageData{

    val imgResource : Int
    val  name : String

    constructor(imgResourse : Int, name : String){
        this.imgResource = imgResourse
        this.name = name
    }
}