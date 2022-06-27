package sg.edu.np.mad.week2tutorial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("Users");

        ((Button)findViewById(R.id.loginbutton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView inName = findViewById(R.id.Iusername);
                TextView inPw = findViewById(R.id.Ipassword);
                String iName = inName.getText().toString();
                String iPw = inPw.getText().toString();
                ref.child("mad").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if ((iName.equals(snapshot.child("Username").getValue().toString())) && (iPw.equals(snapshot.child("Password").getValue().toString()))){
                            Intent i = new Intent(LoginPage.this, ListActivity.class);
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(LoginPage.this,"Incorrect Username or Password",Toast.LENGTH_SHORT).show();
                            inName.setText("");
                            inPw.setText("");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.w("fail", "Failed to read value written.", error.toException());
                    }
                });
            }
        });

    }
}