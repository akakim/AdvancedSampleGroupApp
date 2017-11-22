package group.sample.advanced.rrk.com.advancedsamplegroupapplication.inquiry;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.afollestad.inquiry.annotations.ForeignKey;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.inquiry.annotation.Column;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-11-16
 * @since 0.0.1
 *
 * 그러니까.. reflection을 이용하여 어떤 클래스 파일을 집어넣으면 자동으로 sqlite
 * 의 형태로 변환시켜줄 수있는 필드 대리자 .
 */

final public class FieldDelegate {

    @Nullable
    private Field field;
    @Nullable
    private Method getterMethod;
    @Nullable
    private Method setterMethod;
    private boolean readOnly;

    FieldDelegate(@NonNull Class<?> parentCls,
                  @NonNull Class<?> builderCls,
                  @NonNull Method setterMethod ){

        // return type 을 반드시 명시해야한다.
        if (setterMethod.getReturnType() != builderCls) {
            throw new IllegalStateException("Builder setter method " + setterMethod.getName() + " " +
                    "must return " + builderCls.getName());
        }

        // parametertype이 있어야만한다.
        if (setterMethod.getParameterTypes() == null || setterMethod.getParameterTypes().length != 1) {
            throw new IllegalStateException("Builder setter methods must only have 1 parameter.");
        }

        this.setterMethod = setterMethod;

        String targetGetterName = setterMethod.getName();

        if( targetGetterName.startsWith("set")){
            targetGetterName = targetGetterName.substring(3);
        }

        // 동적으로 getter 메소드를 생성한다.
        final String expectedSignature = setterMethod.getParameterTypes()[0].getName()+
                " " + targetGetterName + "()";

        //  TODO: 부모 케이스가 어떤건지 생각해볼것.
        try{
            this.getterMethod = parentCls.getDeclaredMethod( targetGetterName );

            if( this.getterMethod.getReturnType() != setterMethod.getParameterTypes() [0 ]){
                throw new IllegalStateException("Getter " + getterMethod.getName() + "() must return " +
                        setterMethod.getParameterTypes()[0].getName() + " to match the Builder method.");
            }
        }catch ( NoSuchMethodException e){
            throw new IllegalStateException(parentCls.getName() + " must contain a getter method of " +
                    "signature " + expectedSignature + " to match the Builder setter method.");
        }

    }



    FieldDelegate(@Nullable Field field,
                  @Nullable Method method,
                  @NonNull Class<?> rowType){
        if( field == null && method == null){
            throw new IllegalStateException( " Both the given field and method are null");
        }

        this.field = field;
        this.getterMethod = method;

        if(ignore() ) {
            return;
        }
    }
    FieldDelegate(@Nullable Field field,
                  @Nullable Method method,
                  boolean readOnly) {

        if( field == null && method == null){
            throw  new IllegalStateException("Both the given field and method are null");

        }

        this.field          = field;
        this.getterMethod   = method;

        if( ignore() )
            return;

        this.readOnly = readOnly;

        if( this.field != null){
            this.field.setAccessible( true);
        }else {
            getterMethod.setAccessible(true);
            if (getterMethod.getReturnType() == Void.class) {
                throw new IllegalStateException("Column getter methods cannot be return void.");
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    public String name(){
        Column colAnn;
        String name;

        if(getterMethod != null){
            colAnn = getterMethod.getAnnotation( Column.class);
            name = getterMethod.getName();

            if(name.startsWith("get")){
                name = name.substring(3);
            }

        } else {
            colAnn  = field.getAnnotation( Column.class);
            name     = field.getName();
        }

        if( colAnn != null && colAnn.name() != null && !colAnn.name().trim().isEmpty()){
            name = colAnn.name();
        }

        return name;
    }

    @SuppressWarnings("ConstantConditions")
    boolean ignore(){
        return !isId() && getColumn() == null && getFoeignKey() == null;
    }


    public boolean isId(){
        return name().equals("_id");
    }

    @SuppressWarnings("ConstantConditions")
    public Column getColumn(){
        if( getterMethod != null){
            return getterMethod.getAnnotation( Column.class);
        } else {
            return field.getAnnotation( Column.class);
        }
    }

    @SuppressWarnings("ConstantConditions")
    ForeignKey getFoeignKey(){
        if(getterMethod != null){
            return getterMethod.getAnnotation( ForeignKey.class );
        } else {
            return field.getAnnotation(ForeignKey.class);
        }
    }

    @Nullable
    String schema(){




     return "";
    }

    private String getClassTypeString(Class<?> cls){

        if(cls.equals( String.class ) || cls.equals( char[].class ) || cls.equals(Character[].class)){
            return "TEXT";
        } else if ( cls.equals(Float.class) || cls.equals(float.class) ||
                cls.equals(Double.class)|| cls.equals(double.class)){
            return "REAL";
        } else if (cls.equals(Integer.class) || cls.equals(int.class) ||
                cls.equals(Long.class) || cls.equals(long.class) ||
                cls.equals(Boolean.class) || cls.equals(boolean.class)) {
            return "INTEGER";
        } else {
            return "BLOB";
        }


    }
}
