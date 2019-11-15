package ch.pandamurai.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Activity4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        Intent intent = getIntent();
        String text = intent.getStringExtra(MainActivity.EXTRA_TEXT);
        int number = intent.getIntExtra(MainActivity.EXTRA_NUMBER, 0);

        TextView textView1 = findViewById(R.id.transfert_textView1);
        TextView textView2 = findViewById(R.id.transfert_textView2);

        textView1.setText(text);
        textView2.setText(""+number);

    }
}
