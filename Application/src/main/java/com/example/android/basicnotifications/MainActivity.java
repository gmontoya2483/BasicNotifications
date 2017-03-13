package com.example.android.basicnotifications;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
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

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_layout);

    }

    /**
     * Send a sample notification using the NotificationCompat API.
     */
    public void sendBasicNotification(View view) {

        // BEGIN_INCLUDE(build_action)
        /** Create an intent that will be fired when the user clicks the notification.
         * The intent needs to be packaged into a {@link android.app.PendingIntent} so that the
         * notification service can fire it on our behalf.
         */
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://developer.android.com/reference/android/app/Notification.html"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        // END_INCLUDE(build_action)

        // BEGIN_INCLUDE (build_notification)
        /**
         * Use NotificationCompat.Builder to set up our notification.
         */
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);

        /** Set the icon that will appear in the notification bar. This icon also appears
         * in the lower right hand corner of the notification itself.
         *
         * Important note: although you can use any drawable as the small icon, Android
         * design guidelines state that the icon should be simple and monochrome. Full-color
         * bitmaps or busy images don't render well on smaller screens and can end up
         * confusing the user.
         */
        builder.setSmallIcon(R.drawable.ic_stat_notification);

        // Set the intent that will fire when the user taps the notification.
        builder.setContentIntent(pendingIntent);

        // Set the notification to auto-cancel. This means that the notification will disappear
        // after the user taps it, rather than remaining until it's explicitly dismissed.
        builder.setAutoCancel(true);

        /**
         *Build the notification's appearance.
         * Set the large icon, which appears on the left of the notification. In this
         * sample we'll set the large icon to be the same as our app icon. The app icon is a
         * reasonable default if you don't have anything more compelling to use as an icon.
         */
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));

        /**
         * Set the text of the notification. This sample sets the three most commononly used
         * text areas:
         * 1. The content title, which appears in large type at the top of the notification
         * 2. The content text, which appears in smaller text below the title
         * 3. The subtext, which appears under the text on newer devices. Devices running
         *    versions of Android prior to 4.2 will ignore this field, so don't use it for
         *    anything vital!
         */
        builder.setContentTitle("BasicNotifications Sample");
        builder.setContentText("Time to learn about notifications!");
        builder.setSubText("Tap to view documentation about notifications.");

        // END_INCLUDE (build_notification)

        // BEGIN_INCLUDE(send_notification)
        /**
         * Send the notification. This will immediately display the notification icon in the
         * notification bar.
         */

        //Standard notification line
        //NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        //for adding Android wear functionality the line above have to be changed by:
        NotificationManagerCompat notificationManager= NotificationManagerCompat.from(getApplicationContext());

        notificationManager.notify(BASIC_NOTIFICATION_ID, builder.build());
        // END_INCLUDE(send_notification)
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




}
