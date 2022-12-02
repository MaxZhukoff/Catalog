package ru.onlineshop.catalog.logic

import ru.onlineshop.catalog.api.*
import ru.quipy.domain.AggregateState
import java.util.*

class CatalogAggregateState : AggregateState<String, CatalogAggregate>{
    private lateinit var projectId: String
    private var createdAt: Long = System.currentTimeMillis()
    private var updatedAt: Long = System.currentTimeMillis()
    
//    var itemList: MutableMap<UUID, Item> = mutableMapOf()

    override fun getId() = projectId

//    fun createCatalog(id: UUID = UUID.randomUUID()) : CatalogCreatedEvent {
//        return CatalogCreatedEvent(id)
//    }

//    fun addItemToCatalog (id: UUID, title : String, description: String, amount: Int, price: Int, category: String) : ItemAddedEvent {
//        if(itemList.containsKey(id)){
//            throw IllegalArgumentException("Item with id $id is already exists")
//        }
//        //TODO хз, надо ли создавать итем и добавлять его в мапу
//        return ItemAddedEvent(id, title, description, amount, price, category)
//    }
//    fun deleteItemFromCatalog (id : UUID) : ItemRemovedEvent {
//        if (!itemList.containsKey(id)){
//            throw IllegalArgumentException("No such item with id $id")
//        }
//        return ItemRemovedEvent(id)
//    }
//    fun changeItemPrice (id : UUID, price : Int) : ItemPriceChangedEvent {
//        if (!itemList.containsKey(id)){
//            throw IllegalArgumentException("No such item with id $id")
//        }
//        return ItemPriceChangedEvent(id, price)
//    }
//    fun changeItemAmount(id : UUID, amount : Int) : ItemRefiledEvent {
//        if (!itemList.containsKey(id)){
//            throw IllegalArgumentException("No such item with id $id")
//        }
//        return ItemRefiledEvent(id, amount)
//    }
    //TODO не уверен надо делать изменение количества товара или только его пополнения, если что пофиксите
}

//data class Item(
//    val id : UUID,
//    val title : String,
//    val description : String,
//    val price : Int,
//    val amount : Int,
//    val category : String,
//)