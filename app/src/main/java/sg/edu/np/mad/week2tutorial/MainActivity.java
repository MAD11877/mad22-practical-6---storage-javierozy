package sg.edu.np.mad.week2tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent receivingEnd = getIntent();
        Integer userID = receivingEnd.getIntExtra("userID", 0);
        DBHandler dataB = new DBHandler(this);
        //User user = ListActivity.userList.get(position);
        User user = dataB.SpecificUser(userID);

        TextView nameText = findViewById(R.id.nameText);
        TextView descText = findViewById(R.id.descText);
        nameText.setText(String.format("%s", user.name));
        descText.setText(String.format("%s", user.description));
        Button btn = findViewById(R.id.fButton);
        setF(user, btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toastN;
                if (user.followed == false) { //if user is not following
                    user.followed = true; //set to follow
                    toastN = "Followed";
                } else { //if user is following
                    user.followed = false; //set to unfollow
                    toastN = "Unfollowed";
                }
                setF(user, btn);

                //Toast message
                Toast tNotif = Toast.makeText(MainActivity.this, toastN, Toast.LENGTH_SHORT);
                tNotif.show();
            }
        });

        //event/onclick listener for message button
        Button messageButton = findViewById(R.id.mButton);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newAct = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(newAct);
            }
        });

    }

    //method to initialise user
    public User initUser() {
        User user = new User("username", "desc", 1, false);
        return user;
    }

    //method to set the text based on the condition
    public void setF(User user, Button btn) {
        TextView txt = btn;
        if (user.followed == false) {
            txt.setText("Follow");
        } else {
            txt.setText("Unfollow");
        }
    }
}