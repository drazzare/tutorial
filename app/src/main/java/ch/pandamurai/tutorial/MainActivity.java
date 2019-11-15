package ch.pandamurai.tutorial;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{

    public static final String EXTRA_TEXT = "ch.pandamurai.tutorial.EXTRA_TEXT";
    public static final String EXTRA_NUMBER = "ch.pandamurai.tutorial.EXTRA_NUMBER";
    private AnimationDrawable batteryAnimationDrawable;

    private FrameLayout fragmentContainer;
    private EditText transfertEditText;
    private Button transfertButton;

    private Button openActivity2Button;
    private Button openActivity3Button;
    private Button openReciclerViewButton;

    private long  backPressedTime;
    private Toast backToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        openActivity2Button = findViewById(R.id.open_activity2_button);
        openActivity2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });
        fragmentContainer = findViewById(R.id.fragment_container);
        transfertEditText = findViewById(R.id.transfert_editText);
        transfertButton = findViewById(R.id.transfert_button);
        transfertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = transfertEditText.getText().toString();
                openFragment(text);
            }
        });
        openActivity3Button = findViewById(R.id.open_activity3_button);
        openActivity3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity3();
            }
        });

        ImageView imageView = findViewById(R.id.image_battery);
        imageView.setBackgroundResource(R.drawable.animation_battery);
        batteryAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        openReciclerViewButton = findViewById(R.id.open_recicler_activity);
        openReciclerViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReciclerView();
            }
        });
        Button button = findViewById(R.id.open_activity4_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity4();
            }
        });
    }

    private void openActivity4() {
        EditText editText1 = findViewById(R.id.transfert_editText);
        EditText editText2 = findViewById(R.id.transfert_editText2);
        String text = editText1.getText().toString();
        int number = Integer.parseInt(editText2.getText().toString());
        Intent intent = new Intent(this, Activity4.class);
        intent.putExtra(EXTRA_TEXT, text);
        intent.putExtra(EXTRA_NUMBER, number);
        startActivity(intent);
    }

    private void openReciclerView() {
        Intent reciclerIntent = new Intent(this, ReciclerActivity.class);
        startActivity(reciclerIntent);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        batteryAnimationDrawable.start();
    }

    private void openActivity3() {
        Intent activity3Intent = new Intent(this, MainActivity3.class);
        startActivity(activity3Intent);
    }

    private void openFragment(String text) {
        BlankFragment fragment = BlankFragment.newInstance(text);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_right, R.anim.enter_from_right,R.anim.exit_to_right);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fragment_container, fragment, "BLANK_FRAGMENT").commit();
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        } else{
            backToast = Toast.makeText(getBaseContext(), "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    private void openActivity2() {
        Intent activity2Intent = new Intent(this, Activity2.class);
        startActivity(activity2Intent);
    }

    @Override
    public void onFragmentInteraction(String sendBackText) {
        transfertEditText.setText(sendBackText);
        onBackPressed();
    }
}
