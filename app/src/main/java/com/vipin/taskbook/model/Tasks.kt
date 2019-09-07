package com.vipin.taskbook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by vipin.c on 07/09/2019
 */

//Define this entity class in the TaskDatabase scope
@Entity
data class Tasks(

    //autoGenerate works only for int and long
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = "",
    var completed: Boolean = false
)