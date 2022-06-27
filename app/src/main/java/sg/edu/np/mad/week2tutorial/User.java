package sg.edu.np.mad.week2tutorial;

import android.os.Parcelable;
import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String description;
    public int id;
    public boolean followed;

    public User(){}

    public User(String _name, String _description, int _id, boolean _followed){
        name = _name;
        description = _description;
        id = _id;
        followed = _followed;

    }



}
