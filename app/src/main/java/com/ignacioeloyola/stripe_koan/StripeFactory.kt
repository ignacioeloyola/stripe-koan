package com.ignacioeloyola.stripe_koan

import android.content.Context
import com.stripe.android.PaymentConfiguration
import com.stripe.android.Stripe

internal class StripeFactory(
    private val context: Context,
    private val enableLogging: Boolean = true
) {
    fun create(): Stripe {
        return Stripe(
            context,
            PaymentConfiguration.getInstance(context).publishableKey,
            Settings(context).stripeAccountId,
            enableLogging
        )
    }
}
