package de.mi.ur.shoppinglist;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItemAdapter extends BaseAdapter {

    private final List<ShoppingItem> shoppingItemItems = new ArrayList<>();

    public void addTask(@NonNull ShoppingItem shoppingItem) {
        shoppingItemItems.add(shoppingItem);
    }

    @Override
    public int getCount() {
        return shoppingItemItems.size();
    }

    @Override
    public ShoppingItem getItem(int position) {
        return shoppingItemItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.layout_shopping_item, parent, false);
        }

        ShoppingItem shoppingItemItem = getItem(position);

        fillData(shoppingItemItem, view);

        return view;
    }

    private void fillData(ShoppingItem shoppingItemItem, View view) {
        TextView tvDescription = view.findViewById(R.id.tv_description);
        TextView tvNum = view.findViewById(R.id.tv_num);

        tvDescription.setText(shoppingItemItem.getDescription());
        tvNum.setText(R.string.num_place_holder + shoppingItemItem.getNum());

        if (shoppingItemItem.isCompleted()) {
            tvDescription.setPaintFlags(tvDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            view.setEnabled(false);
        } else view.setEnabled(true);
    }
}
