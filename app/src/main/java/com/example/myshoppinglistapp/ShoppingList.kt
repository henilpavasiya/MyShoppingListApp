package com.example.myshoppinglistapp

data class ShoppingList(
    var id: Int,
    var itemName: String,
    var itemQuantity: Int,
    var isEditing: Boolean
)
