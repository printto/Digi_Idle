package com.printto.printmov.digi_idle.adapters;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.printto.printmov.digi_idle.R;
import com.printto.printmov.digi_idle.item.Item;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class is unusable. Used for code backup only.
 */
public class FeedRecyclerViewAdapterBU extends RecyclerView.Adapter<FeedRecyclerViewAdapterBU.ViewHolder> {

    private ArrayList<Item> mItems = new ArrayList<>();
    private Context mContext;
    private Map<Item, Integer> itemsMap;

    public FeedRecyclerViewAdapterBU(Context context, Map<Item, Integer> items) {
        mContext = context;
        mItems.addAll(items.keySet());
        this.itemsMap = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
//        @BindView(R.id.item_picture) ImageView item_picture;
//        @BindView(R.id.item_name) TextView item_name;
//        @BindView(R.id.itemCount) TextView itemCount;
//        @BindView(R.id.itemOverlay) ImageView itemOverlay;

        private Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
//            ButterKnife.bind(this, itemView);
//            mContext = itemView.getContext();
        }

        public void bindItem(Item item) {
//            item_name.setText(item.getName());
//            itemCount.setText(itemsMap.get(item).toString());
//            item_picture.setImageResource(item.getPicture());
//            AnimationDrawable overlayAnimation = (AnimationDrawable) itemOverlay.getDrawable();;
//            overlayAnimation.start();
        }
    }

    @Override
    public FeedRecyclerViewAdapterBU.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FeedRecyclerViewAdapterBU.ViewHolder holder, int position) {
        holder.bindItem(mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

}