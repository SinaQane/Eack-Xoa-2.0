package apps.authentication.signup.logic;

import apps.authentication.signup.event.SignUpFormEvent;
import db.UserDB;
import models.User;
import utils.Validations;

public class SignUpAgent
{
    private final SignUpFormEvent signUpFormEvent;

    public SignUpAgent(SignUpFormEvent signUpFormEvent)
    {
        this.signUpFormEvent = signUpFormEvent;
    }

    public String check()
    {
        if (!Validations.getValidations().usernameIsValid(signUpFormEvent.getUsername()))
        {
            return "Please enter a valid username.";
        }
        if (signUpFormEvent.getPassword().length() < 8)
        {
            return "Please enter a valid password.";
        }
        if (signUpFormEvent.getName().equals(""))
        {
            return "Name cannot be empty.";
        }
        if (!Validations.getValidations().emailIsValid(signUpFormEvent.getEmail()))
        {
            return "Please enter a valid email address.";
        }
        if (!Validations.getValidations().phoneNumberIsValid(signUpFormEvent.getPhoneNumber()))
        {
            return "Please enter a valid phone number.";
        }
        if (!Validations.getValidations().usernameIsAvailable(signUpFormEvent.getUsername()))
        {
            return "There is already an account with this username.";
        }
        if (!Validations.getValidations().emailIsAvailable(signUpFormEvent.getEmail()))
        {
            return "There is already an account with this email.";
        }
        if (!Validations.getValidations().phoneNumberIsAvailable(signUpFormEvent.getPhoneNumber()))
        {
            return "There is already an account with this phone number.";
        }
        return "Valid";
    }

    public User signUp()
    {
        User user = new User(signUpFormEvent.getUsername(), signUpFormEvent.getPassword());
        user.setName(signUpFormEvent.getName());
        user.setEmail(signUpFormEvent.getEmail());
        user.setBio(signUpFormEvent.getBio());
        user.setPhoneNumber(signUpFormEvent.getPhoneNumber());
        user.setBirthDate(signUpFormEvent.getBirthDate());
        user.getProfile().setPicturePath(signUpFormEvent.getPicPath());
        UserDB.getUserDB().save(user);
        return user;
    }
}
