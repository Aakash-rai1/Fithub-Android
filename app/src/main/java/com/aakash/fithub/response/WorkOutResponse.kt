package com.aakash.fithub.response

import com.aakash.fithub.entity.Workout

data class WorkOutResponse (
        val success: Boolean? = null,
        val data: MutableList<Workout>? = null
        )