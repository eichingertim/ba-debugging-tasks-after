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

    private final List<ShoppingList> shoppingListItems = new ArrayList<>();

    public void addTask(@NonNull ShoppingList shoppingList) {
        shoppingListItems.add(shoppingList);
    }

    @Override
    public int getCount() {
        return shoppingListItems.size();
    }

    @Override
    public ShoppingList getItem(int position) {
        return shoppingListItems.get(position);
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

        ShoppingList shoppingListItem = getItem(position);

        fillData(shoppingListItem, view);

        return view;
    }

    private void fillData(ShoppingList shoppingListItem, View view) {
        TextView tvDescription = view.findViewById(R.id.tv_description);
        TextView tvNum = view.findViewById(R.id.tv_num);

        tvDescription.setText(shoppingListItem.getDescription());
        tvNum.setText(String.format(view.getContext().getString(R.string.num_place_holder),
                shoppingListItem.getNum()));

        if (shoppingListItem.isCompleted()) {
            tvDescription.setPaintFlags(tvDescription.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            view.setEnabled(false);
        } else view.setEnabled(true);
    }
}
