package com.paydaybank.android.models

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.paydaybank.android.R
import com.paydaybank.data.model.TransactionEntity
import java.text.SimpleDateFormat
import java.util.*

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ModelViewTransaction @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {


    private val category: MaterialTextView
    private val vendor: MaterialTextView
    private val price: MaterialTextView
    private val date: MaterialTextView


    init {
        // Inflate
        inflate(context, R.layout.model_view_transaction, this)
        // Set views
        category = findViewById(R.id.category)
        vendor = findViewById(R.id.vendor)
        price = findViewById(R.id.price)
        date = findViewById(R.id.date)
    }

    @ModelProp
    fun transaction(transaction: TransactionEntity?){
        if(transaction==null) return
        category.text = transaction.category
        vendor.text = transaction.vendor
        price.text = String.format("%.2f", transaction.amount)
        date.text = SimpleDateFormat("dd\nMMM", Locale.getDefault()).format(transaction.date)
    }

}