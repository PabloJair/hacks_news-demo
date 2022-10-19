package com.pabloangeles.hackernewsapp.core.base

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

/* A generic class that extends `RecyclerView.Adapter` and it is used to create a `RecyclerView`
adapter. */
abstract class BaseAdapter<T, I: BaseViewHolder<*, T>>: RecyclerView.Adapter<I>() {
    private  var dragHelper: ItemTouchHelper?=null

    /* A function that is called when an item is clicked. */
    var onClickItem:((item: T,view: ViewBinding)->Unit)?=null

    /* A property that is used to store the items that are going to be displayed in the `RecyclerView`. */
     var  items: ArrayList<T> = arrayListOf()

    private lateinit var recyclerView: RecyclerView

    /**
     * It sets up the items in the adapter.
     *
     * @param items The list of items to be displayed in the recycler view.
     */
    fun setupItems(items:ArrayList<T>){
        this.items = items.ifEmpty { arrayListOf() }
        notifyDataSetChanged()
    }

    /**
     * It clears the items array.
     */
    fun clearItems(){
        items = arrayListOf()
        notifyDataSetChanged()
    }

    /**
     * It adds a new element to the list and notifies the adapter that the list has changed.
     *
     * @param addElement The element you want to add to the list
     */
    fun insertNewElement(addElement:T){
        items.add(addElement)
        notifyItemRangeInserted(items.size+1,items.size)
        recyclerView.smoothScrollToPosition(items.size+1)
    }

    fun removeElement(item:T){
        notifyItemRemoved(items.indexOf(item))
        items.remove(item)
    }

    /**
     * It attaches the recycler view to the adapter.
     *
     * @param recyclerView The RecyclerView this adapter has been attached to.
     */
    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView= recyclerView
    }

    /**
     * Returns the size of the items list.
     */
    override fun getItemCount(): Int = items.size


     /* Setting up the drag helper. */
     fun setupDrag(dragHelper: ItemTouchHelper){
         this.dragHelper = dragHelper
     }

     protected fun startDragging(holder: RecyclerView.ViewHolder) {
        dragHelper?.startDrag(holder)
    }

}