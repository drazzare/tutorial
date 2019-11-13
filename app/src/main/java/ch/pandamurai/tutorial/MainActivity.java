package ch.pandamurai.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements BlankFragment.OnFragmentInteractionListener{

    private FrameLayout fragmentContainer;
    private EditText transfertEditText;
    private Button transfertButton;

    private Button openActivity2Button;
    private Button openActivity3Button;

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
