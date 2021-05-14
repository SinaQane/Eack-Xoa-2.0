package controller.pages.settings;

import controller.mainpage.MainPageController;
import listener.pages.settings.EditFormEvent;
import db.UserDB;
import model.User;
import util.Validations;

import java.util.List;
import java.util.Objects;

public class SettingsLogic
{
    private final EditFormEvent editFormEvent;

    public SettingsLogic(EditFormEvent editFormEvent)
    {
        this.editFormEvent = editFormEvent;
    }

    public String check()
    {
        if (Validations.getValidations().usernameIsInvalid(editFormEvent.getUsername()))
        {
            return "Please enter a valid username.";
        }
        if (editFormEvent.getPassword().length() < 8)
        {
            return "Please enter a valid password.";
        }
        if (editFormEvent.getName().equals(""))
        {
            return "Name cannot be empty.";
        }
        if (Validations.getValidations().emailIsInvalid(editFormEvent.getEmail()))
        {
            return "Please enter a valid email address.";
        }
        if (Validations.getValidations().phoneNumberIsInvalid(editFormEvent.getPhoneNumber()))
        {
            return "Please enter a valid phone number.";
        }
        if (Validations.getValidations().usernameIsUnavailable(editFormEvent.getUsername()) &&
                !MainPageController.getMainPageAgent().getUser().getUsername().equals(editFormEvent.getUsername()))
        {
            return "There is already an account with this username.";
        }
        if (Validations.getValidations().emailIsUnavailable(editFormEvent.getEmail()) &&
                !MainPageController.getMainPageAgent().getUser().getEmail().equals(editFormEvent.getEmail()))
        {
            return "There is already an account with this email.";
        }
        if (Validations.getValidations().phoneNumberIsUnavailable(editFormEvent.getPhoneNumber()) &&
                !MainPageController.getMainPageAgent().getUser().getPhoneNumber().equals(editFormEvent.getPhoneNumber()))
        {
            return "There is already an account with this phone number.";
        }
        return "Valid";
    }

    public void edit()
    {
        String oldUsername = MainPageController.getMainPageAgent().getUser().getUsername();
        String newUsername = editFormEvent.getUsername();
        MainPageController.getMainPageAgent().getUser().setUsername(newUsername);
        if (!oldUsername.equals(newUsername))
        {
            UserDB.getUserDB().changeUsername(Objects.requireNonNullElse(oldUsername, "0"), newUsername);
        }

        String oldEmail = MainPageController.getMainPageAgent().getUser().getEmail();
        String newEmail = editFormEvent.getEmail();
        MainPageController.getMainPageAgent().getUser().setEmail(newEmail);
        if (!oldEmail.equals(newEmail))
        {
            UserDB.getUserDB().changeEmail(Objects.requireNonNullElse(oldEmail, "0"), newEmail);
        }

        String oldPhoneNumber = MainPageController.getMainPageAgent().getUser().getPhoneNumber();
        String newPhoneNumber = editFormEvent.getPhoneNumber();
        MainPageController.getMainPageAgent().getUser().setPhoneNumber(newPhoneNumber);
        if (!oldPhoneNumber.equals(newPhoneNumber))
        {
            UserDB.getUserDB().changePhoneNumber(Objects.requireNonNullElse(oldPhoneNumber, "0"), newPhoneNumber);
        }

        MainPageController.getMainPageAgent().getUser().setPassword(editFormEvent.getPassword());
        MainPageController.getMainPageAgent().getUser().setName(editFormEvent.getName());
        MainPageController.getMainPageAgent().getUser().setBio(editFormEvent.getBio());
        MainPageController.getMainPageAgent().getUser().setBirthDate(editFormEvent.getBirthDate());
        MainPageController.getMainPageAgent().getUser().getProfile().setPrivate(editFormEvent.isPrivateState());
        MainPageController.getMainPageAgent().getUser().getProfile().setInfoState(editFormEvent.isInfoState());
        MainPageController.getMainPageAgent().getUser().getProfile().setLastSeenState(editFormEvent.getLastSeenState());
        MainPageController.getMainPageAgent().getUser().getProfile().setPicturePath(editFormEvent.getPicPath());

        UserDB.getUserDB().save(MainPageController.getMainPageAgent().getUser());
    }

    public void deactivate()
    {
        User user = MainPageController.getMainPageAgent().getUser();
        user.deactivate();
        UserDB.getUserDB().save(user);
    }

    public void deleteAccount()
    {
        User user = MainPageController.getMainPageAgent().getUser();
        
        List<User> users = UserDB.getUserDB().getALl();

        for (User otherUser : users)
        {
            otherUser.getProfile().removeFromFollowings(user);
            otherUser.getProfile().removeFromFollowers(user);
            otherUser.getProfile().removeFromRequests(user);
            otherUser.getProfile().removeFromPending(user);
            otherUser.getProfile().removeFromBlocked(user);
            otherUser.getProfile().removeFromMuted(user);
            UserDB.getUserDB().save(otherUser);
        }

        UserDB.getUserDB().changeUsername(user.getUsername(), "");
        UserDB.getUserDB().changeEmail(user.getEmail(), "");
        UserDB.getUserDB().changePhoneNumber(user.getPhoneNumber(), "");

        user.deleteAccount();
        UserDB.getUserDB().save(user);
    }
}