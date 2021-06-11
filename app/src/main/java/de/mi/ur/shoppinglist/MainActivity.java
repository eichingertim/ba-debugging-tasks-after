package de.mi.ur.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

/**
 * Projekt Features:
 *  - Über ein Eingabefeld können Produkte beschrieben werden
 *  - Per Button kann ein solches Produkt zu einer Liste hinzugefügt werden
 *  - Optional kann auch die Stückzahl per 'Plus' und 'Minus' festgelegt werden
 *  - Durch Langes klicken, kann ein Produkt abgehakt/durchgestrichen werden.
 */
public class MainActivity extends AppCompatActivity implements OnViewsReadyListener {

    private ShoppingItemAdapter adapter;

    private ListView lvTasks;
    private TextInputEditText etTaskInput;
    private TextView tvNum;
    private ImageButton btnAddNum;
    private ImageButton btnRemoveNum;
    private Button btnAddTask;

    private ShoppingItem currentEditedShoppingItem = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupUI();
        setupListView();
    }

    @Override
    public void onViewsReady(View v) {
        setupListeners();
    }

    public void setupUI() {
        setContentView(R.layout.activity_main);
        lvTasks = findViewById(R.id.lv_shopping_list);
        btnAddNum = findViewById(R.id.btn_add_num);
        btnRemoveNum = findViewById(R.id.btn_remove_num);
        tvNum = findViewById(R.id.tv_num);
        etTaskInput = findViewById(R.id.et_shopping_item_input);
        btnAddTask = findViewById(R.id.btn_add);
    }

    private void setupButtonListeners() {
        btnAddNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onNumChanged(1);
            }
        });
        btnRemoveNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onNumChanged(-1);
            }
        });

        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.this.onAddTaskClick();
            }
        });
    }

    private void setupListView() {
        adapter = new ShoppingItemAdapter();
        lvTasks.setAdapter(adapter);
    }

    private void setupListViewListener() {
        lvTasks.setOnItemLongClickListener((parent, view, position, id) ->
                onTaskLongClicked(position));
    }


    private void onNumChanged(int i) {
        if (currentEditedShoppingItem == null) {
            currentEditedShoppingItem = new ShoppingItem();
        }
        if (currentEditedShoppingItem.getNum() + i <= 0) return;
        currentEditedShoppingItem.setNum(currentEditedShoppingItem.getNum() + i);
        tvNum.setText(String.format(Locale.GERMAN, getString(R.string.num_place_holder),
                currentEditedShoppingItem.getNum()));
    }

    private void setupListeners() {
        setupListViewListener();
        setupButtonListeners();
    }

    private void resetInput() {
        currentEditedShoppingItem = null;
        tvNum.setText(R.string.one_item);
        etTaskInput.setText("");
    }

    private void onAddTaskClick() {
        Editable taskInputText = etTaskInput.getText();
        if (taskInputText != null && !taskInputText.toString().trim().isEmpty()) {
            currentEditedShoppingItem = currentEditedShoppingItem == null ? new ShoppingItem() : currentEditedShoppingItem;
            currentEditedShoppingItem.setDescription(taskInputText.toString());
            adapter.addTask(new ShoppingItem(currentEditedShoppingItem));
            adapter.notifyDataSetChanged();
            resetInput();
        } else {
            Toast.makeText(this, R.string.empty_input_warning, Toast.LENGTH_LONG).show();
        }
    }

    private boolean onTaskLongClicked(int position) {
        ShoppingItem longClickedShoppingItem = adapter.getItem(position);
        if (longClickedShoppingItem.isCompleted()) return false;
        longClickedShoppingItem.setCompleted(true);
        adapter.notifyDataSetChanged();
        return true;
    }

}