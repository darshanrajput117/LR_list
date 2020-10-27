package com.biz.lrlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private final User[] userListData;

    public UserListAdapter(User[] userListData) {
        this.userListData = userListData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View list_item = layoutInflater.inflate(R.layout.user_list, parent, false);
        return new ViewHolder(list_item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final User user = userListData[position];
        holder.tv_fname.setText(userListData[position].getFName());
        holder.tv_lname.setText(userListData[position].getLName());
        holder.tv_mobileno.setText(userListData[position].getMobileno());
        holder.tv_email.setText(userListData[position].getEmailid());
        holder.layout_list_user.setOnClickListener(v -> Toast.makeText(v.getContext(), "You clicked: " + user.getFName(), Toast.LENGTH_SHORT).show());
    }

    @Override
    public int getItemCount() {
        return userListData.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public AppCompatTextView tv_fname;
        public AppCompatTextView tv_lname;
        public AppCompatTextView tv_mobileno;
        public AppCompatTextView tv_email;
        public LinearLayoutCompat layout_list_user;

        public ViewHolder(View v) {
            super(v);
            this.tv_fname = v.findViewById(R.id.tv_fname);
            this.tv_lname = v.findViewById(R.id.tv_lname);
            this.tv_mobileno = v.findViewById(R.id.tv_mobileno);
            this.tv_email = v.findViewById(R.id.tv_emailid);
            layout_list_user = v.findViewById(R.id.linear_userlist);
        }
    }
}
