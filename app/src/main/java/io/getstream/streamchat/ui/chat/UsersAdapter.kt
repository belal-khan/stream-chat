package io.getstream.streamchat.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import io.getstream.chat.android.client.models.User
import io.getstream.streamchat.data.UserExtra
import io.getstream.streamchat.databinding.ItemUserBinding
import io.getstream.streamchat.ui.base.BaseRecyclerViewAdapter

class UsersAdapter : BaseRecyclerViewAdapter<User, ItemUserBinding>() {

  override fun getViewBinding(parent: ViewGroup) =
    ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)

  override fun onBindViewHolder(holder: Companion.BaseViewHolder<ItemUserBinding>, position: Int) {
    holder.binding.textViewName.text = items[position].name
    holder.binding.textViewPhone.text = items[position].extraData[UserExtra.PHONE].toString()
  }
}
