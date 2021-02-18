package ussrfantom.com.example.androidtesttask.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ussrfantom.com.example.androidtesttask.R;
import ussrfantom.com.example.androidtesttask.pojo.Datum;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<Datum> datums;

    public List<Datum> getDatums() {
        return datums;
    }
    private OnUserClickListener onUserClickListener;
    private OnReachEndListener onReachEndListener;


    public interface OnUserClickListener{
        void onUserClick(int position);
    }

    public interface OnReachEndListener{
        void onReachEnd();
    }

    public void setOnReachEndListener(OnReachEndListener onReachEndListener) {
        this.onReachEndListener = onReachEndListener;
    }

    public void setOnUserClickListener(OnUserClickListener onUserClickListener) {
        this.onUserClickListener = onUserClickListener;
    }

    public void setDatums(List<Datum> datums) {
        this.datums = datums;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.users_item, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        if (position > datums.size() - 4 && onReachEndListener != null){
            onReachEndListener.onReachEnd();
        }
        Datum datum = datums.get(position);
        holder.textViewID.setText(datum.getId());
        holder.textViewName.setText(datum.getName());
        holder.textViewCountry.setText(datum.getCountry());
        holder.textViewLat.setText(datum.getLat());
        holder.textViewLon.setText(datum.getLon());
        Picasso.get().load("https://thumbs.dreamstime.com/b/о-но-eggs-с-стороной-35188083.jpg").into(holder.imageViewAvatar);



    }

    @Override
    public int getItemCount() {
        return datums.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewID;
        private TextView textViewName;
        private TextView textViewCountry;
        private TextView textViewLat;
        private TextView textViewLon;
        private ImageView imageViewAvatar;


        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.textViewID);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCountry = itemView.findViewById(R.id.textViewCountry);
            textViewLat = itemView.findViewById(R.id.textViewLat);
            textViewLon = itemView.findViewById(R.id.textViewLon);
            imageViewAvatar = itemView.findViewById(R.id.imageViewAvatar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onUserClickListener != null){
                        onUserClickListener.onUserClick(getAdapterPosition());
                    }
                }
            });


        }
    }
}
