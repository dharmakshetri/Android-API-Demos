package co.apidemos.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import co.apidemos.R;
import co.apidemos.model.StackOverFlowUser;

/**
 * Created by horror on 12/27/16.
 */

public class StackOverFlowAdapter extends RecyclerView.Adapter<StackOverFlowAdapter.UserViewHolder>{

    //oops principle encapsulations enchancement here

    //declared private member variables
    private List<StackOverFlowUser> users;
    private Context mcontext;
    private int rowLayout;


// constructor
    public StackOverFlowAdapter(Context mcontext, List<StackOverFlowUser> users, int rowLayout) {
        this.setMcontext(mcontext);
        this.setUsers(users);
        this.setRowLayout(rowLayout);
    }

    // declared public methods

    public Context getMcontext() { return mcontext; }

    public void setMcontext(Context mcontext) {
        this.mcontext = mcontext;
    }

    public List<StackOverFlowUser> getUsers() {
        return users;
    }

    public void setUsers(List<StackOverFlowUser> users) {
        this.users = users;
    }

    public int getRowLayout() {
        return rowLayout;
    }

    public void setRowLayout(int rowLayout) {
        this.rowLayout = rowLayout;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(rowLayout,parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        holder.userName.setText(users.get(position).getUserName());
        holder.userName.setTypeface(Typeface.DEFAULT_BOLD);
        holder.userReputation.setText(mcontext.getResources().getString(R.string.reputation)+" : "+users.get(position).getReputation());
        holder.userLocation.setText(users.get(position).getLocation());

        Iterator<Map.Entry<String, Integer>> iterator=users.get(position).getBadges().entrySet().iterator();

        Map.Entry<String, Integer> pair=iterator.next();
        holder.goldenBadge.setText(pair.getKey()+ " : ");
        holder.goldenValue.setText(Integer.toString(pair.getValue()));

        pair=iterator.next();
        holder.silverBadge.setText(pair.getKey()+ " : ");
        holder.silverValue.setText(Integer.toString(pair.getValue()));

        pair=iterator.next();
        holder.bronzeBadge.setText(pair.getKey()+ " : ");
        holder.bronzeValue.setText(Integer.toString(pair.getValue()));

        holder.usersLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext, " "+users.get(position).getUserName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public static  class UserViewHolder extends RecyclerView.ViewHolder {

        LinearLayout usersLinearLayout;
        TextView userName;
        TextView userReputation;
        TextView userLocation;
        TextView goldenBadge;
        TextView goldenValue;
        TextView silverBadge;
        TextView silverValue;
        TextView bronzeBadge;
        TextView bronzeValue;

        public UserViewHolder(View itemView) {
            super(itemView);

            usersLinearLayout=(LinearLayout) itemView.findViewById(R.id.userLayout);
            userName=(TextView) itemView.findViewById(R.id.textviewUserName);
            userReputation=(TextView) itemView.findViewById(R.id.textviewReputation);
            userLocation=(TextView) itemView.findViewById(R.id.textviewUserLocation);
            goldenBadge=(TextView) itemView.findViewById(R.id.textviewGoldenBadge);
            goldenValue=(TextView) itemView.findViewById(R.id.textviewGoldenValue);
            silverBadge=(TextView) itemView.findViewById(R.id.textviewSilverBadge);
            silverValue=(TextView) itemView.findViewById(R.id.textviewSilverValue);
            bronzeBadge=(TextView) itemView.findViewById(R.id.textviewBronzeBadge);
            bronzeValue=(TextView) itemView.findViewById(R.id.textviewBronzeValue);



        }
    }
}
