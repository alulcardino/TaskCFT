package com.example.taskcft.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskcft.ui.utils.Error
import kotlin.time.Duration.Companion.seconds


data class RegistrationState(
    var firstName: Pair<String, Boolean>? = null,
    var surname: Pair<String, Boolean>? = null,
    var dataOfBirth: String = "",
    var password: Pair<String, Boolean>? = null,
    var repeatedPassword: Pair<String, Boolean>? = null,
    var isButtonActive: Int = 0
)

class RegistrationViewModel : ViewModel() {

    private val _registrationState = MutableLiveData<RegistrationState>()
    val registrationState: LiveData<RegistrationState>
        get() = _registrationState

    init {
        _registrationState.value = RegistrationState(dataOfBirth = "01.01.1970")
    }

    fun validateFirstName(firstName: String): Error? {
        if (!firstName.matches("^[A-Za-zА-Яа-я]*$".toRegex())) {
            _registrationState.value =
                registrationState.value?.copy(firstName = Pair(firstName, false))
            return Error.INCORRECT_SYMBOLS
        }
        if (firstName.length < 2) {
            _registrationState.value =
                registrationState.value?.copy(firstName = Pair(firstName, false))
            return Error.INCORRECT_LENGTH
        }
        _registrationState.value = registrationState.value?.copy(firstName = Pair(firstName, true))
        return null
    }

    fun validateSurname(surname: String): Error? {
        if (!surname.matches("^[A-Za-zА-Яа-я]*$".toRegex())) {
            _registrationState.value = registrationState.value?.copy(surname = Pair(surname, false))
            return Error.INCORRECT_SYMBOLS
        }
        if (surname.length < 2) {
            _registrationState.value = registrationState.value?.copy(surname = Pair(surname, false))
            return Error.INCORRECT_LENGTH
        }
        _registrationState.value = registrationState.value?.copy(surname = Pair(surname, true))
        return null
    }

    fun validatePassword(password: String): Error? {
        if (password.length < 8) {
            _registrationState.value =
                registrationState.value?.copy(password = Pair(password, false))
            return Error.INCORRECT_LENGTH
        }
        _registrationState.value = registrationState.value?.copy(password = Pair(password, true))
        return null
    }

    fun validateRepeatPassword(repeatedPassword: String): Error? {
        if (repeatedPassword.length < 8) {
            _registrationState.value =
                registrationState.value?.copy(repeatedPassword = Pair(repeatedPassword, false))
            return Error.INCORRECT_LENGTH
        }
        if (repeatedPassword != _registrationState.value?.password?.first) {
            _registrationState.value =
                registrationState.value?.copy(repeatedPassword = Pair(repeatedPassword, false))
            return Error.PASSWORDS_NOT_MATCH
        }
        _registrationState.value =
            registrationState.value?.copy(repeatedPassword = Pair(repeatedPassword, true))
        return null
    }

    fun setDate(date: String) {
        _registrationState.value = registrationState.value?.copy(dataOfBirth = date)
    }


    fun isButtonActive(): Boolean {
        return _registrationState.value?.surname?.second == true &&
                _registrationState.value?.firstName?.second == true &&
                _registrationState.value?.password?.second == true &&
                _registrationState.value?.repeatedPassword?.second == true
    }

}