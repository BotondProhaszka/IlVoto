package hu.proha.ilvoto.service


import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class NotificationService: FirebaseMessagingService() {


    override fun onNewToken(token: String) {
        Log.d("NOTIFICATION", "Refreshed token: $token")

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // FCM registration token to your app server.
        sendTokenToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle incoming message here
        val notification = remoteMessage.notification
        val title = notification?.title
        val body = notification?.body
        // Show notification to the user
        Log.d("NOTIFICATION", "Received ${title}")
        showNotification(title, body)
    }



    private fun showNotification(title: String?, body: String?) {
        // Code to show notification
    }

    private fun sendTokenToServer(token: String) {
        // Code to send token to server
    }
}
