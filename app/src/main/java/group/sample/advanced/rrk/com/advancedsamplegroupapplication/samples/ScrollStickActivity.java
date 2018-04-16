package group.sample.advanced.rrk.com.advancedsamplegroupapplication.samples;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter.PersonAdapter;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.PeopleRepo;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.Person;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.util.RecyclerSectionItemDecoration;


/**
 * TODO 나중에 구현 .
 */
public class ScrollStickActivity extends AppCompatActivity {


    @BindView( R.id.rvPerson)
    RecyclerView rvPerson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_stick);

        ButterKnife.bind( this );
        ButterKnife.apply(rvPerson, new ButterKnife.Action<RecyclerView>() {
            @Override
            public void apply(@NonNull RecyclerView view, int index) {

                rvPerson.setLayoutManager(
                        new LinearLayoutManager(
                                ScrollStickActivity.this,
                                LinearLayoutManager.VERTICAL,
                                false));

                final List<Person>  people = PeopleRepo.getPeopleSorted();


                float density =                         ScrollStickActivity.this.getResources().getDisplayMetrics().density;
//                ScrollStickActivity.this.get
                rvPerson.setAdapter(new PersonAdapter( people ) );
                RecyclerSectionItemDecoration deco = new RecyclerSectionItemDecoration(
                        density,
                        (int)density * 30
                        , false , getSectionCallback( people ));

                rvPerson.addItemDecoration( deco );
            }
        });
    }
    RecyclerSectionItemDecoration.SectionCallback getSectionCallback( final List<Person> people){


        return new RecyclerSectionItemDecoration.SectionCallback() {
            @Override
            public boolean isSection(int position) {

                if( position == 0 ) {
                    return true;
                }else {
                    return people.get(position).getLastName().charAt(0)
                            != people.get(position -1 ).getLastName().charAt(0);
                }
            }

            @Override
            public CharSequence getSectionHeader(int position) {

                return people.get(position).getLastName().subSequence(0,1);
            }
        };
    }

}
