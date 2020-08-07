package com.paydaybank.android.models

import android.content.Context
import android.util.AttributeSet
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import com.google.android.material.textview.MaterialTextView
import com.paydaybank.android.R
import com.paydaybank.android.models.callbacks.MonthControlListener
import com.paydaybank.data.model.TransactionEntity
import com.paydaybank.data.repository.transaction.model.CategorizedSum
import timber.log.Timber
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ModelViewMonthControl @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {

    private val dateFormatShort = SimpleDateFormat("yyyyMM", Locale.getDefault())
    private val dateFormatLong = SimpleDateFormat("MMM yyyy", Locale.getDefault())

    private val monthTitle: MaterialTextView
    private val last: MaterialButton
    private val first: MaterialButton
    private val previous: MaterialButton
    private val next: MaterialButton

    private var selectedMonth:String = ""
    private var months:List<String> = listOf()
    private var listener:MonthControlListener? = null

    init {
        // Inflate
        inflate(context, R.layout.model_view_month_control, this)
        // Set views
        last = findViewById(R.id.last)
        first = findViewById(R.id.first)
        previous = findViewById(R.id.previous)
        next = findViewById(R.id.next)
        monthTitle = findViewById(R.id.monthTitle)
        // Click listeners
        next.setOnClickListener { navigateTo(1) }
        previous.setOnClickListener { navigateTo(-1) }
        last.setOnClickListener { navigateTo(Int.MAX_VALUE) }
        first.setOnClickListener { navigateTo(Int.MIN_VALUE) }
    }

    @ModelProp
    fun months(months:List<String>){
        this.months = months
    }

    @CallbackProp
    fun onSelectMonth(listener: MonthControlListener?){
        this.listener = listener
    }

    @TextProp
    fun selectedMonth(month:CharSequence?){
        try {
            if(month==null) return
            this.selectedMonth = month.toString()
            val date = dateFormatShort.parse(selectedMonth)
            if(date!=null) monthTitle.text = dateFormatLong.format(date)
        }catch (ex:Exception){
            Timber.w(ex)
        }
    }

    private fun navigateTo(increment:Int){
        try {
            val index = when(increment){
                Int.MIN_VALUE -> 0
                Int.MAX_VALUE -> months.size - 1
                else -> months.indexOf(selectedMonth) + increment
            }
            val newMonth = months[index]
            this.listener?.monthSelected(newMonth)
            selectedMonth(newMonth)
        }catch (ex:Exception){
            Timber.w(ex)
        }
    }

}