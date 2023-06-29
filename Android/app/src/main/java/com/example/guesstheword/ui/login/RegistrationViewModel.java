package com.example.guesstheword.ui.login;

import android.util.Patterns;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.guesstheword.logic.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationViewModel extends ViewModel {
    private MutableLiveData<User> userLiveData = new MutableLiveData<>();
    private MutableLiveData<RegistrationFormState> registrationFormState = new MutableLiveData<>();

    public MutableLiveData<User> getUserLiveData() {
        return userLiveData;
    }

    public MutableLiveData<RegistrationFormState> getRegistrationFormState() {
        return registrationFormState;
    }

    /**
     * It creates the main User, if valid, and it sends it to the server
     *
     * @param email    must respect the RFC 5322 email format (unique attribute)
     * @param password at least 5 characters
     * @param username (this will be used as the User ID, as Primary key)
     * @param avatar   chosen between 16 images (must be a number between 1-16, you can use the MACROS of this class)
     */
    public void signUp(String email, String password, String repeatPassword, String username, int avatar) {
        if (isEmailAlreadyExisting(email))
            registrationFormState.setValue(new RegistrationFormState());
        userLiveData.setValue(new User(em));
    }

    public void registrationDataChanged() {

    }

    /**
     * A placeholder email validation check
     */
    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }

        final String regular_expression = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regular_expression);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean isEmailAlreadyExisting(String email) {
        //TODO: chiedi al server se questa email già esiste o no
        return false;
    }

    /**
     * A placeholder password validation check
     */
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    /**
     * A placeholder username validation check
     */
    private boolean isUsernameValid(String username) {
        if (username == null)
            return false;

        final String regular_expression = "^[A-Za-z][A-Za-z0-9_]{0,29}$";
        Pattern pattern = Pattern.compile(regular_expression);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    private boolean isUsernameAlreadyTaken(String email) {
        //TODO: chiedi al server se questo username già esiste o no
        return false;
    }
}
