package com.example.android.basicnotifications;

import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

/**
 * The entry point to the BasicNotification sample.
 */
public class MainActivity extends Activity {
    /**
     * A numeric value that identifies the notification that we'll be sending.
     * This value needs to be unique within this app, but it doesn't need to be
     * unique system-wide.
     */
    public static final int BASIC_NOTIFICATION_ID = 1;
    public static final int ACTIONS_NOTIFICATION_ID=2;
    public static final int WEAREABLE_ACTION_NOTIFICATION_ID=3;
    public static final int BIG_STYLE_ACTION_NOTIFICATION_ID=4;
    public static final int BACKGROUND_ACTION_NOTIFICATION_ID=5;
    public static final int PAGES_ACTION_NOTIFICATION_ID=6;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);

    }

    /**
     * Send a sample notification using the NotificationCompat API.
     */
    public void sendBasicNotification(View view) {


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_stat_notification);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setContentTitle("BasicNotifications Sample");
        builder.setContentText("Time to learn about notifications!");
        builder.setSubText("Tap to view documentation about notifications.");


        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(BASIC_NOTIFICATION_ID, builder.build());

    }



    public void sendAccionsNotification(View view) {

        String location="Vienna";



        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);



        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =PendingIntent.getActivity(this, 0, mapIntent, 0);



        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setContentTitle("Notication with actions")
                        .setContentText("This is a notification which has an action")
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_launcher, "Mapa" ,mapPendingIntent);


        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(getApplicationContext());

        notificationManager.notify(ACTIONS_NOTIFICATION_ID, notificationBuilder.build());


    }




    public void sendWeareableActionNotification(View view){

        // when a wearable sepcific action is added  the regular added action are shown only in the phone and the wereable specific action are shown only on the wereable.
        String location="Vienna";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent viewPendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

        //Intent fpr triggering the actions
        Intent mapIntent = new Intent(Intent.ACTION_VIEW);
        Uri geoUri = Uri.parse("geo:0,0?q=" + Uri.encode(location));
        mapIntent.setData(geoUri);
        PendingIntent mapPendingIntent =PendingIntent.getActivity(this, 0, mapIntent, 0);

        // Create an action for the weareable
        NotificationCompat.Action action= new NotificationCompat.Action.Builder(R.drawable.ic_launcher, "Weareable action",mapPendingIntent).build();

        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setContentTitle("Notication with actions")
                        .setContentText("This is a notification which has an action")
                        .setContentIntent(viewPendingIntent)
                        .addAction(R.drawable.ic_launcher, "Mapa Phone" ,mapPendingIntent) // This action is shown only on the phone
                        .extend(new NotificationCompat.WearableExtender().addAction(action)); // This action is shown only on the weareable


        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(getApplicationContext());

        notificationManager.notify(WEAREABLE_ACTION_NOTIFICATION_ID, notificationBuilder.build());

    }



    public void sendBigStyleNotification (View view){


        NotificationCompat.BigTextStyle bigStyle= new NotificationCompat.BigTextStyle();
        bigStyle.bigText("Big text event descrption. To show additional information");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setContentTitle("Notication with actions")
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setContentText("This is a notification which has an action")
                .setStyle(bigStyle);


        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(BIG_STYLE_ACTION_NOTIFICATION_ID, builder.build());

    }




    public void sendBackgroundNotification (View view){

        Bitmap bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.background_400);


        //Create a WearableExtender to add functionality for wearables
        NotificationCompat.WearableExtender wearableExtender= new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground (bitmap);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setContentTitle("Notication with actions")
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setContentText("This is a notification which has an action")
                .extend(wearableExtender);


        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(BACKGROUND_ACTION_NOTIFICATION_ID, builder.build());


    }


    public void sendPagesNotification(View view){


        // create a big style for the second page
        NotificationCompat.BigTextStyle secondPageStyle= new NotificationCompat.BigTextStyle();
        secondPageStyle.setBigContentTitle("Page 2");
        secondPageStyle.bigText("This is the text which is going to be displayed in the second page. Usually it must have a lot of text....");

        // Create the builder for the second page
        Notification secondPageNotification= new NotificationCompat.Builder(this)
                .setStyle(secondPageStyle)
                .build();


        //Create the wearable extender which has the second page
        NotificationCompat.WearableExtender wearableExtender= new NotificationCompat.WearableExtender()
                .addPage (secondPageNotification);


        //Create the builder for the main notification
        NotificationCompat.Builder mainBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setContentTitle("Page 1")
                .setSmallIcon(R.drawable.ic_stat_notification)
                .setContentText("This is a notification which has a second page")
                .extend(wearableExtender);



        //Issue the notification
        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(getApplicationContext());
        notificationManager.notify(PAGES_ACTION_NOTIFICATION_ID, mainBuilder.build());


    }




}
