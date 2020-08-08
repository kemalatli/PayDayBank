package com.paydaybank.android.models

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import com.google.android.material.textview.MaterialTextView
import com.paydaybank.android.R
import com.paydaybank.data.repository.transaction.model.CategorizedSum

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class ModelViewCategory @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : MaterialCardView(context, attrs, defStyleAttr) {


    private val category: MaterialTextView
    private val price: MaterialTextView


    init {
        // Inflate
        inflate(context, R.layout.model_view_category, this)
        shapeAppearanceModel =  ShapeAppearanceModel().withCornerSize(0f)
        // Set views
        category = findViewById(R.id.category)
        price = findViewById(R.id.price)
    }

    @ModelProp
    fun categorizedSum(categorizedSum: CategorizedSum?){
        if(categorizedSum==null) return
        category.text = categorizedSum.title
        price.text = String.format("%.2f", categorizedSum.sum)
        // Price color
        val clr = ContextCompat.getColor(context, if(categorizedSum.sum>0) R.color.balance_green else R.color.balance_red)
        price.setTextColor(clr)
    }

}