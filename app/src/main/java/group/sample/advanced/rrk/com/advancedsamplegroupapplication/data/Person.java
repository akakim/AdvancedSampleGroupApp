package group.sample.advanced.rrk.com.advancedsamplegroupapplication.data;

import android.support.annotation.NonNull;

import java.util.Locale;

/**
 * Created by RyoRyeong Kim on 2018-04-16.
 */

public class Person implements Comparable<Person> {
    private final CharSequence firstName;
    private final CharSequence lastName;
    private static final String NAME_DISPLAY = "%s %s";


    public Person(@NonNull CharSequence firstName, @NonNull CharSequence lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public CharSequence getFirstName() {
        return firstName;
    }

    public CharSequence getLastName() {
        return lastName;
    }

    public String getFullName(){
        return String.format(
                Locale.getDefault(),
                NAME_DISPLAY,
                getLastName(),
                getFirstName());
    }

    @Override
    public int compareTo(@NonNull Person o) {
        return getLastName().toString()
                .compareTo(o.getLastName().toString());
    }
}
