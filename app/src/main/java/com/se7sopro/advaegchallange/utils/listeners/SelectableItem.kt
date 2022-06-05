package com.se7sopro.advaegchallange.utils.listeners

import java.io.Serializable


interface SelectableItem : Serializable {
    val id: Int?
    val name: String?
}