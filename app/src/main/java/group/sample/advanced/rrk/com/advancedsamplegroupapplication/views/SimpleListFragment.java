package group.sample.advanced.rrk.com.advancedsamplegroupapplication.views;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;


public class SimpleListFragment extends Fragment {

    public static final String ARG_LIST_ITEM = "listItem1";

    private static ArrayList<String> list  = new ArrayList<>();


    private OnFragmentInteractionListener mListener;


    @BindView(R.id.rv)
    RecyclerView rv;
    public SimpleListFragment() {
        // Required empty public constructor
    }

    public static SimpleListFragment newInstance(String param1, String param2) {
        SimpleListFragment fragment = new SimpleListFragment();
        Bundle args = new Bundle();

        args.putStringArrayList(ARG_LIST_ITEM,list);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            list = getArguments().getStringArrayList( ARG_LIST_ITEM );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_simple_list, container, false);
        ButterKnife.bind( this, view  );


//        for(String s : list ){
//            Log.d(getClass().getSimpleName(),"str :" + s );
//        }
        SimpleTextAdapter adapter = new SimpleTextAdapter( getContext(), list );
        rv.setAdapter( adapter );
        rv.setLayoutManager( new LinearLayoutManager(getContext() ));
        rv.setNestedScrollingEnabled( true );
        return  view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    class SimpleTextAdapter extends  RecyclerView.Adapter<CustomViewHolder>{

        Context context;
        private ArrayList<String> list ;
        public SimpleTextAdapter(Context context,ArrayList<String> list) {
            this.context = context;
            this.list = list;
        }


        @Override
        public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1,null,false);
            return new CustomViewHolder(view);

        }

        @Override
        public void onBindViewHolder(CustomViewHolder holder, int position) {

            final String item = this.list.get( position );
            holder.tvSample.setText( item  );
        }



        @Override
        public int getItemCount() {
            return list.size();
        }
    }

    class CustomViewHolder extends RecyclerView.ViewHolder{


        @BindView(android.R.id.text1)
        public TextView tvSample;
        public CustomViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
