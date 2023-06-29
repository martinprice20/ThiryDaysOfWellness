package com.example.thirtydaysofwellness.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class WellnessTip(
    @StringRes val dayRes: Int,
    @StringRes val titleRes: Int,
    @StringRes val msgRes: Int,
    @DrawableRes val imgRes: Int
)
