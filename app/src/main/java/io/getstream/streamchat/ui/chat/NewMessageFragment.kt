package io.getstream.streamchat.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.View
import dagger.hilt.android.AndroidEntryPoint
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.api.models.QueryUsersRequest
import io.getstream.chat.android.client.models.Filters
import io.getstream.streamchat.R
import io.getstream.streamchat.databinding.FragmentNewMessageBinding
import io.getstream.streamchat.ui.base.BaseFragment

@AndroidEntryPoint
class NewMessageFragment : BaseFragment(R.layout.fragment_new_message) {

  private val adapter = UsersAdapter()
  private lateinit var binding: FragmentNewMessageBinding

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    binding = FragmentNewMessageBinding.bind(view)
    binding.recyclerViewUsers.adapter = adapter
    getUsers()
  }

  private fun getUsers() {
    val request = QueryUsersRequest(
      filter = Filters.ne("id", currentUser.uid),
      offset = 0,
      limit = 100,
    )
    ChatClient.instance().queryUsers(request).enqueue {
      if (it.isSuccess) {
        adapter.items = it.data()
      }
    }
  }
}
