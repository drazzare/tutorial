package ch.pandamurai.tutorial;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.NotificationCompat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class Activity2 extends AppCompatActivity implements ExampleDialog.ExampleDialogListener, IncrementDialog.IncrementDialogListener {

    private Button customToastButton;

    private CoordinatorLayout coordinatorLayout;
    private Button snackbarButton;

    private EditText editTextTitle;
    private EditText editTextMessage;
    private Button buttonChannel1;
    private Button buttonChannel2;

    private NotificationHelper notificationHelper;

    private TextView savedTextTextview;
    private EditText inputTextEditText;
    private Button apllyTextButton;
    private Switch saveinputSwitch;
    private Button saveButtonButton;
    public static final String SHARED_PREFS="sharedPrefs";
    public static final String TEXT ="text";
    public static final String SWITCHINPUT ="switchInput";
    private String text;
    private boolean switchOnOff;



    private TextView countDownText;
    private Button countDownButton;
    private CountDownTimer countDownTimer;
    private long timeLeftInMilliSeconds = 600000; //10 minutes in milliseconds
    private boolean timerRunning;

    private TextView counterText;
    private BroadcastReceiver minuteBroadcastReceiver;
    private int counter;
    private Button counterButton;

    private Button incrementButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        countDownText = findViewById(R.id.countdown_text);
        countDownText.setText("10:00");
        countDownButton = findViewById(R.id.countdown_button);

        countDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });
        counterText = findViewById(R.id.counter_text);
        counterButton=findViewById(R.id.counter_button);

        counterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
        incrementButton=findViewById(R.id.increment_button);
        incrementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opennDialogIncrement();
            }
        });
        savedTextTextview = findViewById(R.id.savedText_Textview);
        inputTextEditText = findViewById(R.id.inputText_EditText);
        apllyTextButton = findViewById(R.id.apllyText_Button);
        saveinputSwitch = findViewById(R.id.saveinput_Switch);
        saveButtonButton = findViewById(R.id.saveButton_Button);

        apllyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedTextTextview.setText(inputTextEditText.getText().toString());
            }
        });

        saveButtonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        loadData();
        updateViews();

        editTextTitle = findViewById(R.id.editText_title);
        editTextMessage = findViewById(R.id.editText_message);
        buttonChannel1 = findViewById(R.id.button_channel1);
        buttonChannel2 = findViewById(R.id.button_channel2);

        notificationHelper = new NotificationHelper(this);
        buttonChannel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnChannel1(editTextTitle.getText().toString(),editTextMessage.getText().toString());
            }
        });

        buttonChannel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendOnChannel2(editTextTitle.getText().toString(),editTextMessage.getText().toString());
            }
        });

        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        snackbarButton = findViewById(R.id.snackbar_button);
        snackbarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSnackbar();
            }
        });
        customToastButton = findViewById(R.id.custom_toast_button);
        customToastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomToast();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Toast.makeText(this, "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this, "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem1:
                Toast.makeText(this, "subItem 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subitem2:
                Toast.makeText(this, "subItem 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showCustomToast() {
        LayoutInflater inflater = getLayoutInflater();
        View toastLayout = inflater.inflate(R.layout.custom_toast_layout, (ViewGroup) findViewById(R.id.toast_root));
        Toast customToast = new Toast(getApplicationContext());
        customToast.setGravity(Gravity.CENTER, 0, 0);
        customToast.setDuration(Toast.LENGTH_LONG);
        customToast.setView(toastLayout);
        customToast.show();
    }

    private void showSnackbar() {
        final Snackbar snackbar = Snackbar.make(coordinatorLayout,"This is a Snackbar",Snackbar.LENGTH_INDEFINITE)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Undo successful",Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                }).setActionTextColor(Color.RED);
        View snackView = snackbar.getView();
        TextView textView = snackView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    private void sendOnChannel2(String title, String message) {
        NotificationCompat.Builder nb = notificationHelper.getChannel2Notification(title, message);
        notificationHelper.getManager().notify(2,nb.build());
    }

    private void sendOnChannel1(String title, String message) {
        NotificationCompat.Builder nb = notificationHelper.getChannel1Notification(title, message);
        notificationHelper.getManager().notify(1,nb.build());
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(TEXT, savedTextTextview.getText().toString());
        editor.putBoolean(SWITCHINPUT, saveinputSwitch.isChecked());
        editor.apply();
        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        text = sharedPreferences.getString(TEXT, "");
        switchOnOff = sharedPreferences.getBoolean(SWITCHINPUT, false);
    }

    public void updateViews(){
        savedTextTextview.setText(text);
        saveinputSwitch.setChecked(switchOnOff);
    }

    private void opennDialogIncrement() {
        IncrementDialog dialog = new IncrementDialog();
        dialog.show(getSupportFragmentManager(), "example dialog");
    }

    private void openDialog() {
        ExampleDialog dialog = new ExampleDialog();
        dialog.show(getSupportFragmentManager(), "example dialog");
    }

    private void startMinuteUpdater(){
        IntentFilter intentFilter =  new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        minuteBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                counter++;
                counterText.setText(""+counter);
            }
        };

        registerReceiver(minuteBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startMinuteUpdater();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(minuteBroadcastReceiver);
    }

    private void startStop() {
        if(timerRunning){
            stopTimer();
            countDownButton.setText("start");
        } else{
            startTimer();
            countDownButton.setText("stop");
        }
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMilliSeconds, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliSeconds = l;
                updateTimer();
            }
            @Override
            public void onFinish() { }
        }.start();
        timerRunning = true;
    }

    private void updateTimer() {
        int minutes = (int) timeLeftInMilliSeconds/60000;
        int seconds = (int) timeLeftInMilliSeconds%60000/1000;
        String timeLeftText = "";
        if (minutes<10)timeLeftText ="0";
        timeLeftText += minutes + ":";
        if(seconds < 10) timeLeftText += "0";
        timeLeftText+= seconds;
        countDownText.setText(timeLeftText);
    }

    private void stopTimer() {
        countDownTimer.cancel();
        timerRunning = false;
    }

    @Override
    public void onYesClick() {
        counter++;
        counterText.setText(""+counter);
    }

    @Override
    public void applyIncrement(String incrementNumber) {
        counterText.setText(incrementNumber);
        counter = Integer.parseInt(incrementNumber);
    }
}
