package ro.pub.cs.systems.eim.lab05.startedservice.service;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ProcessingThread extends Thread {

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

    final public static long SLEEP_TIME = 1000;

    private Context context;

    public ProcessingThread(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        Log.d(TAG, "Thread.run() was invoked, PID: " + android.os.Process.myPid() + " TID: " + android.os.Process.myTid());
        while(true) {
            sendMessage(MESSAGE_STRING);
            sleep();
            sendMessage(MESSAGE_INTEGER);
            sleep();
            sendMessage(MESSAGE_ARRAY_LIST);
            sleep();
        }
    }

    private void sendMessage(int messageType) {
        Intent intent = new Intent();
        switch (messageType) {
            case MESSAGE_STRING:
                intent.setAction(ACTION_STRING);
                intent.putExtra(DATA, STRING_DATA);
                break;
            case MESSAGE_INTEGER:
                intent.setAction(ACTION_INTEGER);
                intent.putExtra(DATA, INTEGER_DATA);
                break;
            case MESSAGE_ARRAY_LIST:
                intent.setAction(ACTION_ARRAY_LIST);
                intent.putExtra(DATA, ARRAY_LIST_DATA);
                break;
        }
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException interruptedException) {
            Log.e(TAG, interruptedException.getMessage());
            interruptedException.printStackTrace();
        }
    }

}
