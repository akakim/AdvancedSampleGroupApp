package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 */
class ImageItem {

    var  imageItem : Int
    lateinit var  name : String


    constructor( imageItem : Int, name : String ){
        this.imageItem = imageItem
        this.name = name
    }


}