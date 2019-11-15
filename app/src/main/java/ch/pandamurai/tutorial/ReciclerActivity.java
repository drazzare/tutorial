package ch.pandamurai.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.pandamurai.tutorial.ui.ExampleRecyclerAdapter;
import ch.pandamurai.tutorial.ui.ExampleRecyclerItem;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class ReciclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;
    private ArrayList<ExampleRecyclerItem> mExampleList;
    private Button buttonInsert;
    private Button buttonRemove;
    private EditText editTextInsert;
    private EditText editTextRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler);

        createExampleList();
        buildRecyclerView();
        editTextInsert = findViewById(R.id.editText_insert);
        editTextRemove = findViewById(R.id.editText_remove);
        buttonInsert = findViewById(R.id.insert_button);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });
        buttonRemove = findViewById(R.id.remove_button);
        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(editTextRemove.getText().toString());
                removeItem(position);
            }
        });
    }
    public void insertItem(int position){
        mExampleList.add(position, new ExampleRecyclerItem(R.drawable.ic_android, "New item at position "+position, "this is line "+position));
        mAdapter.notifyItemInserted(position);
    }
    public void removeItem(int position){
        mExampleList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
    private void createExampleList() {
        mExampleList = new ArrayList<ExampleRecyclerItem>();
        mExampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        mExampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        mExampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));

    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mAdapter = new ExampleRecyclerAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
