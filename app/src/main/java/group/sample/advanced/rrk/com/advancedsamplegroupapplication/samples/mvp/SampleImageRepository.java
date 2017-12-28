package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.mvp;

/**
 * Created by RyoRyeong Kim on 2017-12-28.
 */

public class SampleImageRepository {

    private static SampleImageRepository sampleImageRepository;


    public static SampleImageRepository getInstance(){
        if( sampleImageRepository == null){
            sampleImageRepository = new SampleImageRepository();
        }

        return sampleImageRepository;
    }


    private SampleImageLocalDataSource sampleImageLocalDataSource;


    public SampleImageRepository() {
        sampleImageLocalDataSource = new SampleImageLocalDataSource();
    }
}
