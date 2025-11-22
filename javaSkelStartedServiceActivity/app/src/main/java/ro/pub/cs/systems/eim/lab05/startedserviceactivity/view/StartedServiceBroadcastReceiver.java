package ro.pub.cs.systems.eim.lab05.startedserviceactivity.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;


public class StartedServiceBroadcastReceiver extends BroadcastReceiver {
    final public static String TAG = "[Started Service Activity]";

    final public static String ACTION_STRING = "ro.pub.cs.systems.eim.lab05.startedservice.string";
    final public static String ACTION_INTEGER = "ro.pub.cs.systems.eim.lab05.startedservice.integer";
    final public static String ACTION_ARRAY_LIST = "ro.pub.cs.systems.eim.lab05.startedservice.arraylist";

    final public static String DATA = "ro.pub.cs.systems.eim.lab05.startedservice.data";

    final public static String MESSAGE = "ro.pub.cs.systems.eim.lab05.startedserviceactivity.message";

    private TextView messageTextView;

    // TODO: exercise 10 - default constructor
    public StartedServiceBroadcastReceiver() {
        this.messageTextView = null;
    }

    public StartedServiceBroadcastReceiver(TextView messageTextView) {
        this.messageTextView = messageTextView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: exercise 7 - get the action and the extra information from the intent
        // and set the text on the messageTextView

        String action = intent.getAction();
        String data = null;
        if (ACTION_STRING.equals(action)) {
            data = intent.getStringExtra(DATA);
        }
        if (ACTION_INTEGER.equals(action)) {
            data = String.valueOf(intent.getIntExtra(DATA, -1));
        }
        if (ACTION_ARRAY_LIST.equals(action)) {
            data = intent.getStringArrayListExtra(DATA).toString();
        }
        if (messageTextView != null) {
            messageTextView.setText(messageTextView.getText().toString() + "\n" + data);
        } /*else {
            Intent startedServiceActivityIntent = new Intent(context, StartedServiceActivity.class);
            startedServiceActivityIntent.putExtra(Constants.MESSAGE, data);
            startedServiceActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.startActivity(startedServiceActivityIntent);
        }*/

        // TODO: exercise 10 - restart the activity through an intent
        // if the messageTextView is not available
    }
}
