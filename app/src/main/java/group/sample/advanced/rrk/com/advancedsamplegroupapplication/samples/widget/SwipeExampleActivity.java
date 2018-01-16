package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples.widget;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.util.RecyclerItemDeleteItem;

public class SwipeExampleActivity extends AppCompatActivity {


    @BindView(R.id.rvMain)
    RecyclerView rvMain;


    MainAdapter mainAdapter;

    List<String> titleArray = new ArrayList<>();
    List<String> contentsArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_example);

        ButterKnife.bind(this);

        rvMain.setLayoutManager(new LinearLayoutManager(this ));


        for( int k = 0; k<20; k++){
            titleArray.add(" title ::::: "  + k );
            contentsArray.add( "contentsssssssss ;:::: " + k );
        }

        mainAdapter = new MainAdapter(this, titleArray,contentsArray);
        rvMain.setAdapter( mainAdapter );


    }


    public class MainAdapter extends RecyclerView.Adapter<ViewHolder>{

        Context context;
        List<String> titleArray;
        List<String> contentsArray;
        public MainAdapter(Context context,        List<String> titleArray , List<String> contentsArray){

            this.context = context;
            this.titleArray = titleArray;
            this.contentsArray = contentsArray;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view  = LayoutInflater.from( context ).inflate(R.layout.contens_item,null,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {



            holder.title.setText( titleArray.get(position));
            holder.contents.setText( contentsArray.get(position));
        }

        @Override
        public int getItemCount() {
            return titleArray.size();
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        public TextView title;

//        @BindView(R.id.contents)


        public TextView contents;
        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView );


        }
    }

}
