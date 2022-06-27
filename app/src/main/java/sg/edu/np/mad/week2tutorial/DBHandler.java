package sg.edu.np.mad.week2tutorial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.Random;

//table values
public class DBHandler extends SQLiteOpenHelper {

    private String tableName = "USER";
    private String col0 = "NAME";
    private String col1 = "DESCRIPTION";
    private String col2 = "ID";
    private String col3 = "FOLLOWED";

    public DBHandler(Context c) {
        super(c, "users.dataB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dataB) {
        String query = "CREATE TABLE USER (NAME TEXT, DESCRIPTION TEXT, ID INTEGER PRIMARY KEY, FOLLOWED BOOLEAN)";
        dataB.execSQL(query);

        ArrayList<User> userList = initRandomUser();
        for (User user : userList) {
            ContentValues values = new ContentValues();
            values.put(col0, user.name);
            values.put(col1, user.description);
            values.put(col2, user.id);
            values.put(col3, user.followed);
            dataB.insert(tableName, null, values);
        }

    }


    //get 20 random users
    private ArrayList<User> initRandomUser() {
        ArrayList<User> userList = new ArrayList<User>();
        Integer randId = 0;

        while (userList.size() < 20) {
            Random rand = new Random();
            String randName = "Name" + Integer.toString(rand.nextInt());
            String randDesc = "Description " + Integer.toString(rand.nextInt());
            Boolean randFollowed = rand.nextBoolean();

            User usr = new User(randName, randDesc, randId, randFollowed);//create new user
            userList.add(usr);
            randId += 1;
        }
        return userList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase dataB, int i, int i1) {
        dataB.execSQL("DROP TABLE IF EXISTS USER");
        dataB.close();

    }
//int to bool
    public boolean GetBoolbyInt(Integer i){
        if (i == 1){
            return true;
        }
        else{
            return false;
        }
    }
//bool to int
    public int GetIntbyBool(Boolean b){
        if (b == true){
            return 1;
        }
        else{
            return 0;
        }
    }


    //getUsers
    public ArrayList<User> getUser() {
        String query = "SELECT * FROM USER";
        SQLiteDatabase dataB = this.getWritableDatabase();
        Cursor cursor = dataB.rawQuery(query,null);

        ArrayList<User> userList = new ArrayList<>();
        while (cursor.moveToNext()){
            User user = new User();
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            user.followed = GetBoolbyInt(cursor.getInt(3));

            userList.add(user);
        }
        cursor.close();
        dataB.close();

        return userList;
    }

    public User SpecificUser(Integer id){
        String query = "SELECT * FROM USER WHERE ID = " + "\"" + id + "\"";
        SQLiteDatabase dataB = this.getWritableDatabase();
        Cursor cursor = dataB.rawQuery(query,null);

        User user = new User();
        if (cursor.moveToFirst()){
            Boolean bool = GetBoolbyInt(cursor.getInt(3));
            user.name = cursor.getString(0);
            user.description = cursor.getString(1);
            user.id = cursor.getInt(2);
            user.followed = bool;
        }

        return user;
    }

    public void updateUser(User user){
        SQLiteDatabase dataB = this.getWritableDatabase();
        Integer iFollowed = GetIntbyBool(user.followed);
        String query = "UPDATE USER SET FOLLOWED = \"" + iFollowed + "\" WHERE ID = \"" + user.id + "\"";
        dataB.execSQL(query);
        dataB.close();
    }
}