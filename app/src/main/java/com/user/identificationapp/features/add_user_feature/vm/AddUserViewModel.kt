package com.user.identificationapp.features.add_user_feature.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.user.identificationapp.domain.model.User
import com.user.identificationapp.domain.use_case.AddUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddUserViewModel @Inject constructor(private val addUserUseCase: AddUserUseCase) :
    ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user


    fun updateUser(newUser: User) {

        _user.value = newUser

        viewModelScope.launch {
            user.value?.let { addUserUseCase.invoke(it) }
        }

    }
}