package group.sample.advanced.rrk.com.advancedsamplegroupapplication.adapter.sampleadapter;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import group.sample.advanced.rrk.com.advancedsamplegroupapplication.R;
import group.sample.advanced.rrk.com.advancedsamplegroupapplication.data.Person;

/**
 * Created by RyoRyeong Kim on 2018-04-16.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {


    private final List<Person> people;


    public PersonAdapter(List<Person> people) {
        this.people = people;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_person, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Person person = people.get(position);
        holder.fullName.setText(person.getFullName());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView fullName;

        public ViewHolder(View view) {
            super(view);


            fullName = (TextView) view.findViewById(R.id.tvPerson);
        }
    }
}
