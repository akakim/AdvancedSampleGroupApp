package group.sample.advanced.rrk.com.advancedsamplegroupapplication.inquiry.annotation;

//TODO : annotation 타입에 대한 정리
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by KIM on 2017-11-12.
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface Column {

    boolean primaryKey() default false;

    boolean autoIncrement() default false;

    boolean notNull() default false;

    public String name() default "";
}
