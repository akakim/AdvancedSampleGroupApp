package group.sample.advanced.rrk.com.advancedsamplegroupapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
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
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.common.BaseFragment;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.AudioColumnClass;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MusicFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MusicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MusicFragment extends BaseFragment {

    public static String ITEMS_KEY = "MusicItem";

    private static ArrayList<AudioColumnClass> items = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    @BindView(R.id.rvMusicList)
    RecyclerView rvMusicList;

    public MusicFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static MusicFragment newInstance(String param1, String param2) {
        MusicFragment fragment = new MusicFragment();
        Bundle args = new Bundle();

        args.putParcelableArrayList(ITEMS_KEY, items);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            items = getArguments().getParcelableArrayList( ITEMS_KEY );
        }else {
            items =new ArrayList<>();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_music, container, false);

        ButterKnife.bind( this, view);

        MusicListAdapter adapter = new MusicListAdapter(items);
        rvMusicList.setAdapter(  adapter );
        rvMusicList.setLayoutManager( new LinearLayoutManager( getContext() ));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri,fragmentName);
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri ur,String fragmentID);
    }

    private class MusicListAdapter extends RecyclerView.Adapter<ViewHolder>{

        List<AudioColumnClass> audioColumnClasses;
        public MusicListAdapter( @NonNull List<AudioColumnClass> audioColumnClasses) {
            this.audioColumnClasses = new ArrayList<>();
            this.audioColumnClasses.addAll(audioColumnClasses);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_music_item,null);
            return new ViewHolder( view );
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            final AudioColumnClass audioColumnClass = audioColumnClasses.get(position);



            holder.tvAlbum.setText( audioColumnClass.getAlbum() );
            holder.tvArtist.setText( audioColumnClass.getArtist() );
            holder.tvTitle.setText( audioColumnClass.getTitle() );
            holder.tvDisplayData.setText( audioColumnClass.getData() );
            holder.tvDisplayName.setText( audioColumnClass.getDisplayName() );
            holder.tvContentType.setText( audioColumnClass.getContentType() );


        }


        @Override
        public int getItemCount() {
            return audioColumnClasses.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        @BindView(R.id.tvAlbum)
        public TextView tvAlbum;

        @BindView(R.id.tvArtist)
        public TextView tvArtist;

        @BindView(R.id.tvTitle)
        public TextView tvTitle;

        @BindView(R.id.tvDisplayData)
        public TextView tvDisplayData;

        @BindView(R.id.tvDisplayName)
        public TextView tvDisplayName;

        @BindView(R.id.tvContentType)
        public TextView tvContentType;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
