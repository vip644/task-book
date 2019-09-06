package com.vipin.taskbook.model

/**
 * Created by vipin.c on 07/09/2019
 */
data class Tasks(
    val id: Int = 0,
    var title: String = "",
    val completed: Boolean = false
)