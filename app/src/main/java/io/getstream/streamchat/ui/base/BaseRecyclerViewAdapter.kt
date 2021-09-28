package io.getstream.streamchat.ui.base

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BaseRecyclerViewAdapter<T : Any, VB : ViewBinding>
  : RecyclerView.Adapter<BaseRecyclerViewAdapter.Companion.BaseViewHolder<VB>>() {

  var items = listOf<T>()
    set(value) {
      field = value
      notifyDataSetChanged()
    }

  var itemClickListener: ((view: View, item: T, position: Int) -> Unit)? = null

  abstract fun getViewBinding(parent: ViewGroup): VB
  override fun getItemCount() = items.size

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
    BaseViewHolder(getViewBinding(parent))

  companion object {
    class BaseViewHolder<VB : ViewBinding>(val binding: VB) :
      RecyclerView.ViewHolder(binding.root)
  }
}
