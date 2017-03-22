package creativelizard.in.swipablerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by siddhartha on 22/3/17.
 */

class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder>{

    private List<MyListItem> items;
    private int itemLayout;
    private Context context;

    public MyListAdapter(Context context, List<MyListItem> items, int itemLayout) {
        this.items = items;
        this.itemLayout = itemLayout;
        this.context = context;
    }

    @Override
    public MyListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyListAdapter.ViewHolder holder, int position) {

        holder.tvName.setText(items.get(position).getName());
        holder.tvNumber.setText(items.get(position).getPhone());
        holder.tvMail.setText(items.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public void remove(int position){

        ((MainActivity)context).deleteItem(position,items.get(position));


    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNumber, tvMail;
        ViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvNumber = (TextView)itemView.findViewById(R.id.tvNumber);
            tvMail = (TextView)itemView.findViewById(R.id.tvEmail);
        }
    }
}
