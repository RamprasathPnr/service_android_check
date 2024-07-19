package com.ram.service_android_check

import android.os.Parcelable


data class DataModel(
    var data    : Data?    = Data(),
    var support : Support? = Support()
)


data class Data (

    var id        : Int?    = null,
    var email     : String? = null,
    var firstName : String? = null,
    var lastName  : String? = null,
    var avatar    : String? = null

)

data class Support (

    var url  : String? = null,
    var text : String? = null

)