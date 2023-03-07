package com.ignacioeloyola.stripe_koan

import android.app.Application
import android.net.TrafficStats
import android.os.StrictMode
import com.stripe.android.CustomerSession
import com.stripe.android.PaymentConfiguration
import com.ignacioeloyola.stripe_koan.service.StoreEphemeralKeyProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoreApplication : Application() {

    override fun onCreate() {
        TrafficStats.setThreadStatsTag(TAG)

        StrictMode.setThreadPolicy(
            StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        StrictMode.setVmPolicy(
            StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build()
        )

        super.onCreate()

        val settings = Settings(this)

        CoroutineScope(Dispatchers.IO).launch {
            PaymentConfiguration.init(
                this@StoreApplication,
                publishableKey = settings.publishableKey,
                stripeAccountId = settings.stripeAccountId
            )
        }

        CustomerSession.initCustomerSession(
            this,
            StoreEphemeralKeyProvider(this, settings.stripeAccountId),
            shouldPrefetchEphemeralKey = false
        )
    }

    private companion object {
        private const val TAG = 99999
    }
}
