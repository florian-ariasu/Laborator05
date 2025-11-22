package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class StartedService extends Service {

    final public static String TAG = "[Started Service]";

    final public static String ACTION_STRING = "ro.pub.cs.systems.eim.lab05.startedservice.string";
    final public static String ACTION_INTEGER = "ro.pub.cs.systems.eim.lab05.startedservice.integer";
    final public static String ACTION_ARRAY_LIST = "ro.pub.cs.systems.eim.lab05.startedservice.arraylist";

    final public static int MESSAGE_STRING = 1;
    final public static int MESSAGE_INTEGER = 2;
    final public static int MESSAGE_ARRAY_LIST = 3;

    final public static String DATA = "ro.pub.cs.systems.eim.lab05.startedservice.data";

    final public static String STRING_DATA = "EIM";
    final public static int INTEGER_DATA = Integer.parseInt(new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
    final public static ArrayList<String> ARRAY_LIST_DATA = new ArrayList<>(Arrays.asList("EIM", "Laborator 05", "StartedService"));

    final public static long SLEEP_TIME = 20;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() method was invoked");

        if (Build.VERSION.SDK_INT >= 26) {
            String CHANNEL_ID = "my_channel_01";
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);

            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE)).createNotificationChannel(channel);

            Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                    .setContentTitle("")
                    .setContentText("").build();

            startForeground(1, notification);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind() method was invoked");
        return null;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind() method was invoked");
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG, "onRebind() method was invoked");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy() method was invoked");
        super.onDestroy();
    }

    // TODO exercise 5 - implement onStartCommand method
    // it needs to start a thread that sends three different type of broadcast intents
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand() method was invoked");
        ProcessingThread processingThread = new ProcessingThread(this);
        processingThread.start();
        return START_REDELIVER_INTENT;
    }

}
