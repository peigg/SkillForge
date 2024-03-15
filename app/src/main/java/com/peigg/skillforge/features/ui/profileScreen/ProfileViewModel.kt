package com.peigg.skillforge.features.ui.profileScreen

import androidx.lifecycle.ViewModel
import com.peigg.skillforge.data.bd.repositories.ProfileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject


@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel(){


    val _state = MutableStateFlow<ProfileState>(ProfileState.Loading)
    val state = _state
            init{
                _state.value = ProfileState.Success(
                    profile = profileRepository.getProfile(ProfileEvent.Profile.name),
                    notifications = profileRepository.getProfile(ProfileEvent.Notifications.name),
                    name = profileRepository.getProfile(ProfileEvent.Name.name),
                    email = profileRepository.getProfile(ProfileEvent.Email.name),
                    phone = profileRepository.getProfile(ProfileEvent.Phone.name)
                )
            }
    fun onCheckedChange(profileEvent: ProfileEvent, isChecked: Boolean) {
        val currentState = _state.value

        if (currentState is ProfileState.Success) {
            profileRepository.saveProfile(profileEvent.name, isChecked)
            _state.value = currentState.update(profileEvent, isChecked)
        }
    }
}

private fun ProfileState.Success.update(event: ProfileEvent, isChecked: Boolean): ProfileState.Success {
    return when (event) {
        ProfileEvent.Profile -> copy(profile = isChecked)
        ProfileEvent.Notifications -> copy(notifications = isChecked)
        ProfileEvent.Name -> copy(name = isChecked)
        ProfileEvent.Email -> copy(email = isChecked)
        ProfileEvent.Phone -> copy(phone = isChecked)
    }
}

sealed interface ProfileState{
    object Loading: ProfileState
    data class Success(
        val profile: Boolean,
        val notifications: Boolean,
        val name: Boolean,
        val email: Boolean,
        val phone: Boolean
    ): ProfileState

}

enum class ProfileEvent{
    Profile,
    Notifications,
    Name,
    Email,
    Phone
}