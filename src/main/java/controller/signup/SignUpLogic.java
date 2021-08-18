package controller.signup;

import listener.signup.SignUpFormEvent;
import db.UserDB;
import model.Notification;
import model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.Validations;

import java.util.Date;

public class SignUpLogic
{
    private static final Logger logger = LogManager.getLogger(SignUpLogic.class);

    private final SignUpFormEvent signUpFormEvent;

    public SignUpLogic(SignUpFormEvent signUpFormEvent)
    {
        this.signUpFormEvent = signUpFormEvent;
    }

    public String check()
    {
        if (Validations.getValidations().usernameIsInvalid(signUpFormEvent.getUsername()))
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
        if (Validations.getValidations().emailIsInvalid(signUpFormEvent.getEmail()))
        {
            return "Please enter a valid email address.";
        }
        if (Validations.getValidations().phoneNumberIsInvalid(signUpFormEvent.getPhoneNumber()))
        {
            return "Please enter a valid phone number.";
        }
        if (Validations.getValidations().usernameIsUnavailable(signUpFormEvent.getUsername()))
        {
            logger.error("Someone tried to create an account with username " + signUpFormEvent.getUsername());
            return "There is already an account with this username.";
        }
        if (Validations.getValidations().emailIsUnavailable(signUpFormEvent.getEmail()))
        {
            logger.error("Someone tried to create an account with email " + signUpFormEvent.getEmail());
            return "There is already an account with this email.";
        }
        if (Validations.getValidations().phoneNumberIsUnavailable(signUpFormEvent.getPhoneNumber()))
        {
            logger.error("Someone tried to create an account with phone number " + signUpFormEvent.getPhoneNumber());
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
        user.getProfile().addToNotifications(new Notification(user.getId(), "New login to your account at " + new Date()));

        UserDB.getUserDB().changeEmail("0", signUpFormEvent.getEmail());
        if (!signUpFormEvent.getPhoneNumber().equals("")) UserDB.getUserDB().changePhoneNumber("0", signUpFormEvent.getPhoneNumber());

        UserDB.getUserDB().save(user);

        logger.warn(user.getId() + " signed up and entered the app.");

        return user;
    }
}
