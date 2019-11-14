package ch.pandamurai.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import ch.pandamurai.tutorial.ui.ExampleRecyclerAdapter;
import ch.pandamurai.tutorial.ui.ExampleRecyclerItem;

import android.os.Bundle;

import java.util.ArrayList;

public class ReciclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recicler);
        ArrayList<ExampleRecyclerItem> exampleList = new ArrayList<ExampleRecyclerItem>();
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_android,"Titre 1", "text 1"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_audio,"Titre 2", "text 2"));
        exampleList.add(new ExampleRecyclerItem(R.drawable.ic_sun,"Titre 2", "text 2"));
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mManager = new LinearLayoutManager(this);
        mAdapter = new ExampleRecyclerAdapter(exampleList);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
