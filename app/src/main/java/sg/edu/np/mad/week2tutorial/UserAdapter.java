package sg.edu.np.mad.week2tutorial;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter < UserViewHolder > {
    ArrayList < User > data;
    public UserAdapter(ArrayList < User > input) {
        data = input;
    }

    @Override
    public int getItemViewType(int position) {
        Character lastDigit = ((data.get(position).name).charAt((data.get(position).name).length() - 1));
        if (lastDigit == '7') {
            return 1;
        } else {
            return 0;
        }
    }
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        if (viewType == 1) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_enlarged, parent, false);
        } else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        }
        return new UserViewHolder(item);
    }
    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        String uName = data.get(position).name;
        String uDesc = data.get(position).description;
        holder.uName.setText(uName);
        holder.uDesc.setText(uDesc);
        holder.uPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = holder.uPic.getContext();
                Integer uPos = holder.getAdapterPosition();
                //AlertDialog alert = ListActivity.createAlert(holder.getAdapterPosition(), context);

                DBHandler db = new DBHandler(context);
                ArrayList<User> userList = db.getUser();
                User user = userList.get(uPos);


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(user.name);
                builder.setCancelable(true);
                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //action
                    }
                });
                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Random rand = new Random();
                        //Integer genInt = Math.abs(rand.nextInt());
                        Intent act = new Intent(context,MainActivity.class);
                        act.putExtra("userID", user.id);
                        context.startActivity(act);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }





    @Override
    public int getItemCount() {
        return data.size();
    }
}