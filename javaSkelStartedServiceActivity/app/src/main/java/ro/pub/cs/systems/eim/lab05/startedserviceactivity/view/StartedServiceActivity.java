package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import ro.pub.cs.systems.eim.lab05.startedserviceactivity.R;

public class StartedServiceActivity extends AppCompatActivity {

    final public static String TAG = "[Started Service Activity]";

    final public static String ACTION_STRING = "ro.pub.cs.systems.eim.lab05.startedservice.string";
    final public static String ACTION_INTEGER = "ro.pub.cs.systems.eim.lab05.startedservice.integer";
    final public static String ACTION_ARRAY_LIST = "ro.pub.cs.systems.eim.lab05.startedservice.arraylist";

    final public static String DATA = "ro.pub.cs.systems.eim.lab05.startedservice.data";

    final public static String MESSAGE = "ro.pub.cs.systems.eim.lab05.startedserviceactivity.message";

    private TextView messageTextView;
    private StartedServiceBroadcastReceiver startedServiceBroadcastReceiver;
    private IntentFilter startedServiceIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_started_service);

        messageTextView = (TextView)findViewById(R.id.message_text_view);
        messageTextView.setMovementMethod(new ScrollingMovementMethod());

        // TODO: exercise 6 - start the service
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.lab05.startedservice", "ro.pub.cs.systems.eim.lab05.startedservice.service.StartedService"));
        startForegroundService(intent);

        // TODO: exercise 8a - create an instance of the StartedServiceBroadcastReceiver broadcast receiver
        startedServiceBroadcastReceiver = new StartedServiceBroadcastReceiver(messageTextView);

        // TODO: exercise 8b - create an instance of an IntentFilter
        // with all available actions contained within the broadcast intents sent by the service
        startedServiceIntentFilter = new IntentFilter();
        startedServiceIntentFilter.addAction(ACTION_STRING);
        startedServiceIntentFilter.addAction(ACTION_INTEGER);
        startedServiceIntentFilter.addAction(ACTION_ARRAY_LIST);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // TODO: exercise 8c - register the broadcast receiver with the corresponding intent filter
        registerReceiver(startedServiceBroadcastReceiver, startedServiceIntentFilter);
    }

    @Override
    protected void onPause() {
        // TODO: exercise 8c - unregister the broadcast receiver
        unregisterReceiver(startedServiceBroadcastReceiver);

        super.onPause();
    }

    @Override
    protected void onDestroy() {
        // TODO: exercise 8d - stop the service
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("ro.pub.cs.systems.eim.lab05.startedservice", "ro.pub.cs.systems.eim.lab05.startedservice.service.StartedService"));
        stopService(intent);

        super.onDestroy();
    }

    // TODO: exercise10 - implement the onNewIntent callback method
    // get the message from the extra field of the intent
    // and display it in the messageTextView
    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String message = intent.getStringExtra(MESSAGE);
        if (message != null) {
            messageTextView.setText(messageTextView.getText().toString() + "\n" + message);
        }
    }

}
