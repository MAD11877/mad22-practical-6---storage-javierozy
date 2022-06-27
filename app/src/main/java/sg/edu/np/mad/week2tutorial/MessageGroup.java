package sg.edu.np.mad.week2tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.security.acl.Group;

public class MessageGroup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);
        Button grp1button, grp2button;
        grp1button = findViewById(R.id.grp1button);
        grp2button = findViewById(R.id.grp2button);

        grp1button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFrag(new g1());
            }
        });

        grp2button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                replaceFrag(new g2());
            }
        });
    }

    public void replaceFrag(Fragment frag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame, frag);
        ft.commit();
    }
}