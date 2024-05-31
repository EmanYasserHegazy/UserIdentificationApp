package com.user.identificationapp.features.get_user_feature.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.identificationapp.domain.model.User
import com.user.identificationapp.domain.use_case.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase) :
    ViewModel() {

    private val _users = MutableLiveData<List<User>>()
    var users: LiveData<List<User>> =_users

    init {
        fetchUsers()
    }


    private fun fetchUsers() {
        viewModelScope.launch {
            _users.value = getUserUseCase.invoke()
        }
    }
}