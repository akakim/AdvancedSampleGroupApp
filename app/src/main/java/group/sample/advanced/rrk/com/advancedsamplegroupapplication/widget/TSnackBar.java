package group.sample.advanced.rrk.com.advancedsamplegroupapplication.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v13.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;

/**
 * Created by RyoRyeong Kim on 2018-01-02.
 *
 * reference : https://github.com/AndreiD/TSnackBar
 */

public class TSnackBar {


//    private final ViewGroup parent;
//    private final Context context;
//    private final SnackBarLayout view;



    public static class SnackBarLayout extends LinearLayout {


        @BindView(R.id.snackbar_text)
        private TextView messageView;
        @BindView(R.id.snackbar_action)
        private Button actionView;

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


//    public static abstract class Callback
}
