package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by RyoRyeong Kim on 2018-04-05.
 *
 * View의 모델 나중엔 어노테이션으로 값을 주입하는걸 더 만들어봐야됨.
 */

public interface ViewData {

    int VIEW_PROGRESS   = 0 ;
    int VIEW_HEADER     = 1 ;
    int VIEW_ITEM       = 2;

    // 어뎁터의 ViewType 기본 데이터

//TODO:    @Retention(RetentionPolicy.SOURCE,)
    int getViewType() ;

    String getTitle();
    String getContent();
}
