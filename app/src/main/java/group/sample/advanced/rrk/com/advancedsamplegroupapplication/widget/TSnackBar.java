package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v13.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2018-01-02.
 *왜 이런 코드가 나왔지 ??
 * reference : https://github.com/AndreiD/TSnackBar
 */

public class TSnackBar {

    public static abstract class Callback{
        public static final int DISMISS_EVENT_SWIPE   = 0;
        public static final int DISMISS_EVENT_ACTION  = 1;
        public static final int DISMISS_EVENT_TIMEOUT = 2;
        public static final int DISMISS_EVENT_MANUAL  = 3;
        public static final int DISMISS_EVENT_CONSECUTIVE = 4;

        @IntDef({
                DISMISS_EVENT_SWIPE, DISMISS_EVENT_ACTION , DISMISS_EVENT_TIMEOUT,
                DISMISS_EVENT_MANUAL ,DISMISS_EVENT_CONSECUTIVE
        })
        @Retention(RetentionPolicy.SOURCE) // TODO : 알아보기
        public @interface DismissEvent{

        }

        public void onDismiss(TSnackBar snackBar,@DismissEvent int event ){

        }

        public void onShown(TSnackBar snackBar){

        }
    }


    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_SHORT = -1;
    public static final int LENGTH_LONG = 0;

    @IntDef({LENGTH_INDEFINITE, LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration{

    }


    public static final int ANIMATION_DURATION = 250;
    public static final int ANIMATION_FADE_DURATION = 180;
    private static final Handler handler;
    private static final int MSG_SHOW= 0;
    private static final int MSG_DISMISS= 1;

    static {
        handler = new Handler (Looper.getMainLooper(), new Handler.Callback(){

            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_SHOW:
                        ((TSnackBar) msg.obj).showView();
                        return true;
                    case MSG_DISMISS:
                        ((TSnackBar) msg.obj).hideView(msg.arg1);
                        return true;
                }
                return false;
            }
        });
    }
    private final ViewGroup parent;
    private final Context context;
    private final SnackBarLayout view;


    public TSnackBar(ViewGroup parent) {
        this.parent = parent;
        this.context = parent.getContext();
        view = (SnackBarLayout)LayoutInflater.from(this.context).inflate(R.layout.tsnackbar_layout,this.parent,false);
    }

    @NonNull
    public static TSnackBar make(@NonNull View view, @NonNull CharSequence text,
                                 @Duration int duration){

            TSnackBar snackBar = new TSnackBar(findSuitableParent(view));
            snackBar.setText(text);
            snackBar.setDuration(duration);

            return snackBar;
    }

    @NonNull
    public static TSnackBar make(@NonNull View view, @StringRes int resId, @Duration int duration) {
        return make(view, view.getResources()
                .getText(resId), duration);
    }



    private static ViewGroup findSuitableParent(View view){
        ViewGroup fallback = null;

        do{

            if (view instanceof CoordinatorLayout) {
                return (ViewGroup) view;

            } else if (view instanceof FrameLayout) {
                if (view.getId() == android.R.id.content) {
                    return (ViewGroup) view;
                } else {
                    fallback = (ViewGroup) view;
                }
            }

            if(view != null){
                final ViewParent parent = view.getParent();
                view = parent instanceof View? (View)parent : null;
            }

        } while (view != null);

        return fallback;
    }

    private final SnackBarManager.Callback managerCallback = new SnackBarManager.Callback() {
        @Override
        public void show() {
            handler.sendMessage(handler.obtainMessage(MSG_SHOW,TSnackBar.this));
        }

        @Override
        public void dismiss(int event) {
            handler.sendMessage(handler.obtainMessage(MSG_SHOW,event,0,TSnackBar.this));
        }
    };

    public void dimiss(){
        dispatchDismiss( Callback.DISMISS_EVENT_MANUAL);
    }

    public void dispatchDismiss(@Callback.DismissEvent int event){

        SnackBarManager.getInstance().dismiss(managerCallback,event);
    }

    final void showView(){
        if(view.getParent() == null){
            final ViewGroup.LayoutParams lp = view.getLayoutParams();

            if(lp instanceof CoordinatorLayout.LayoutParams){

                final Behavior behavior = new Behavior();
                // swipe 길이를 지정하는듯 .
                behavior.setStartAlphaSwipeDistance( 0.1f); //
                behavior.setEndAlphaSwipeDistance( 0.6f);   //
                behavior.setSwipeDirection( SwipeDismissBehavior.SWIPE_DIRECTION_END_TO_START );
                behavior.setListener(new SwipeDismissBehavior.OnDismissListener() {
                    @Override
                    public void onDismiss(View view) {
                        dispatchDismiss(Callback.DISMISS_EVENT_SWIPE);
                    }

                    @Override
                    public void onDragStateChanged(int state) {
                        switch ( state){
                            case SwipeDismissBehavior.STATE_DRAGGING:
                            case SwipeDismissBehavior.STATE_SETTLING:
                                SnackBarManager.getInstance().cancelTimeout(managerCallback);
                                    break;

                            case SwipeDismissBehavior.STATE_IDLE:
                                SnackBarManager.getInstance().restoreTimeOut(managerCallback);
                                break;
                        }
                    }
                });

                ((CoordinatorLayout.LayoutParams) lp).setBehavior(behavior);


            }
            parent.addView(view);
        }

        view.setOnAttachStateChangeListener(new SnackBarLayout.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToView(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                if(isShownOrQueued()){

                }
            }
        });
    }



    public boolean isShownOrQueued(){

        return SnackBarManager.getInstance().isCurrentOrNext( )
    }
    public static class SnackBarLayout extends LinearLayout {


        @BindView(R.id.snackbar_text)
        public TextView messageView;
        @BindView(R.id.snackbar_action)
        public Button actionView;

        private int maxWidth;
        private int maxInlineActionWidth;


        interface OnLayoutChangeListener{
            void onLayoutChange(View view , int left, int top, int right, int bottom);
        }


        interface OnAttachStateChangeListener{
            void onViewAttachedToView(View v);
            void onViewDetachedFromWindow(View v);
        }

        private OnLayoutChangeListener onLayoutChangeListener;
        private OnAttachStateChangeListener onAttachStateChangeListener;

        public SnackBarLayout(Context context) {
            this(context,null);
        }

        public SnackBarLayout(Context context, AttributeSet attrs){
            super(context,attrs);
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.SnackbarLayout );
            maxWidth = a.getDimensionPixelSize( R.styleable.SnackbarLayout_android_maxWidth , -1 );
            maxInlineActionWidth = a.getDimensionPixelSize(
                    R.styleable.SnackbarLayout_maxActionInlineWidth,
                    -1);
            if(a.hasValue( R.styleable.SnackbarLayout_elevation)){
                ViewCompat.setElevation( this ,
                        a.getDimensionPixelSize( R.styleable.SnackbarLayout_elevation,0));

            }

            a.recycle();

            setClickable( true );

            LayoutInflater.from(context).inflate( R.layout.tsnackbar_layout_include,this);

            // TODO : 자료조사 모르겠다 이부분은
            // 동적으로 UI를 표시해야될 경우에 넣어준다.
            // 접근성과 관련된 패치를 읽어보기 Kitkat 4.4 이상부터 적용이되었다.
            ViewCompat.setAccessibilityLiveRegion(
                    this,
                    ViewCompat.ACCESSIBILITY_LIVE_REGION_POLITE
            );
        }

        @Override
        protected void onFinishInflate() {
            super.onFinishInflate();
            ButterKnife.bind(this);
        }



        public TextView getMessageView() {
            return messageView;
        }


        public Button getActionView() {
            return actionView;
        }


        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            // 먼저 너비를 잰다.
            if( maxWidth > 0 && getMeasuredWidth() > maxWidth ){

                widthMeasureSpec = MeasureSpec.makeMeasureSpec(maxWidth, MeasureSpec.EXACTLY );
                super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            }


            // 너비를 재고 난다면 이게 2줄 이상인지 아닌지를 잰다.
            final int multiLineVPadding = getResources().getDimensionPixelSize(
                    R.dimen.design_snackbar_padding_vertical_2lines);
            final int singleLineVPadding = getResources().getDimensionPixelSize(
                    R.dimen.design_snackbar_padding_vertical);
            final boolean isMultiLine = messageView.getLayout()
                    .getLineCount() > 1;

            boolean remeasure = false;

            if( isMultiLine && maxInlineActionWidth > 0
                    && actionView.getMeasuredWidth()> maxInlineActionWidth ){

//                if(updateViewsWithInLa)
            }

            if( remeasure ){
                super.onMeasure( widthMeasureSpec,heightMeasureSpec );
            }
        }

        /**
         * fade in Animation 발생
         * @param delay
         * @param duration
         */
        void animateChildrenIn(int delay , int duration){
            ViewCompat.setAlpha( messageView, 0f);
            ViewCompat.animate(messageView)
                    .alpha(1f)
                    .setDuration(duration)
                    .setStartDelay(delay)
                    .start();

            if(actionView.getVisibility() == VISIBLE){
                ViewCompat.setAlpha( actionView, 0f);
                ViewCompat.animate(actionView)
                        .alpha(1f)
                        .setDuration(duration)
                        .setStartDelay(delay)
                        .start();

            }
        }

        /**
         * fade out Animation 발생
         * @param delay
         * @param duration
         */
        void animateChildrenOut(int delay , int duration){
            ViewCompat.setAlpha( messageView, 1f);
            ViewCompat.animate(messageView)
                    .alpha(0f)
                    .setDuration(duration)
                    .setStartDelay(delay)
                    .start();

            if(actionView.getVisibility() == VISIBLE){
                ViewCompat.setAlpha( actionView, 1f);
                ViewCompat.animate(actionView)
                        .alpha(0f)
                        .setDuration(duration)
                        .setStartDelay(delay)
                        .start();

            }
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);

            if( changed && onLayoutChangeListener != null ){
                onLayoutChangeListener.onLayoutChange( this, l,t,r,b);
            }
        }


        @Override
        protected void onAttachedToWindow() {
            super.onAttachedToWindow();
            if(onAttachStateChangeListener != null){
                onAttachStateChangeListener.onViewAttachedToView(this);
            }
        }


        @Override
        protected void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            if(onAttachStateChangeListener != null){
                onAttachStateChangeListener.onViewDetachedFromWindow(this);
            }
        }

        public void setOnLayoutChangeListener(OnLayoutChangeListener onLayoutChangeListener) {
            this.onLayoutChangeListener = onLayoutChangeListener;
        }

        public void setOnAttachStateChangeListener(OnAttachStateChangeListener onAttachStateChangeListener) {
            this.onAttachStateChangeListener = onAttachStateChangeListener;
        }

        /**
         *
         * @param orientation
         * @param messagePadTop
         * @param messagePadBottom
         * @return
         */
        private boolean updateViewsWithinLayout(final int orientation,final int messagePadTop,final int messagePadBottom){
            boolean change = false;


            if( orientation != getOrientation() ){
                setOrientation( orientation );
                change = true;
            }

            if(messageView.getPaddingTop() != messagePadTop ||
                    messageView.getPaddingBottom() != messagePadBottom) {
                updateTopBottomPadding(messageView, messagePadTop,messagePadBottom );
                change = true;

            }

            return change;
        }

        private void updateTopBottomPadding( View view,int topPadding, int bottomPadding){
            if( ViewCompat.isPaddingRelative( view )){
                ViewCompat.setPaddingRelative(
                        view,
                        ViewCompat.getPaddingStart(view),
                        topPadding,
                        ViewCompat.getPaddingEnd( view ),
                        bottomPadding );
            }else {
                view.setPadding( view.getPaddingLeft(), topPadding,view.getPaddingRight(),bottomPadding );
            }
        }


    }


    final class Behavior extends SwipeDismissBehavior<SnackBarLayout>{
        @Override
        public boolean canSwipeDismissView(@NonNull View view) {

            return view instanceof SnackBarLayout;
        }

        @Override
        public boolean onInterceptTouchEvent(CoordinatorLayout parent, SnackBarLayout child, MotionEvent event) {
            if(parent.isPointInChildBounds( child,(int) event.getX(), (int) event.getY())){

                switch ( event.getActionMasked() ){
                    case MotionEvent.ACTION_DOWN:

                        SnackBarManager.getInstance().cancelTimeout(managerCallback);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        SnackBarManager.getInstance().restoreTimeOut(managerCallback);
                        break;
                }
            }
            return super.onInterceptTouchEvent(parent, child, event);
        }
    }

//    public static abstract class Callback
}
