package br.com.ddmsoftware.metromaps;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MapsAdapter extends RecyclerView.Adapter<MapsAdapter.MapsViewHolder> {
    private final Context mContext;
    Cursor mCursor;


    public MapsAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class MapsViewHolder extends RecyclerView.ViewHolder {

        final TextView tvMapsName;

        public MapsViewHolder(View itemView) {
            super(itemView);

            tvMapsName = itemView.findViewById(R.id.tvItemRow);
        }
    }


    @NonNull
    @Override
    public MapsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.item_row, viewGroup, false);

        return new MapsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MapsViewHolder mapsViewHolder, int i) {

        if (!mCursor.moveToPosition(i)) {
            return;
        }

        String mapsName = mCursor.getString(mCursor.getColumnIndex(MapsContract.MapsEntry.COLUMN_MAPS_NAME));
        long id = mCursor.getLong(mCursor.getColumnIndex(MapsContract.MapsEntry._ID));

        mapsViewHolder.tvMapsName.setText(mapsName);
        mapsViewHolder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }



    public void swapCursor(Cursor newCursor){

        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {

            notifyDataSetChanged();
        }

    }

}

/*


public class GroceryAdapter extends RecyclerView.Adapter<GroceryAdapter.GroceryViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public GroceryAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder {

        public TextView nameText;
        public TextView countText;

        public GroceryViewHolder(View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.tvNameItem);
            countText = itemView.findViewById(R.id.tvAmountItem);
        }
    }


    @NonNull
    @Override
    public GroceryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item, viewGroup, false);

        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder( GroceryViewHolder groceryViewHolder, int i) {

        if (!mCursor.moveToPosition(i)) {
            return;
        }

        String name = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT));
        long id = mCursor.getLong(mCursor.getColumnIndex(GroceryContract.GroceryEntry._ID));


        groceryViewHolder.nameText.setText(name);
        groceryViewHolder.countText.setText(String.valueOf(amount));
        groceryViewHolder.itemView.setTag(id);

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {

        if (mCursor != null) {
            mCursor.close();
        }

        mCursor = newCursor;

        if (newCursor != null) {

            notifyDataSetChanged();
        }


    }
}


 */
