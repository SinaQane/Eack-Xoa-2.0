package apps.mainpage.pages.settings.logic;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.pages.settings.event.EditFormEvent;
import db.UserDB;
import models.User;
import utils.Validations;

import java.util.List;
import java.util.Objects;

public class EditAgent
{
    private final EditFormEvent editFormEvent;

    public EditAgent(EditFormEvent editFormEvent)
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
                !MainPageAgent.getMainPageAgent().getUser().getUsername().equals(editFormEvent.getUsername()))
        {
            return "There is already an account with this username.";
        }
        if (Validations.getValidations().emailIsUnavailable(editFormEvent.getEmail()) &&
                !MainPageAgent.getMainPageAgent().getUser().getEmail().equals(editFormEvent.getEmail()))
        {
            return "There is already an account with this email.";
        }
        if (Validations.getValidations().phoneNumberIsUnavailable(editFormEvent.getPhoneNumber()) &&
                !MainPageAgent.getMainPageAgent().getUser().getPhoneNumber().equals(editFormEvent.getPhoneNumber()))
        {
            return "There is already an account with this phone number.";
        }
        return "Valid";
    }

    public void edit()
    {
        String oldUsername = MainPageAgent.getMainPageAgent().getUser().getUsername();
        String newUsername = editFormEvent.getUsername();
        MainPageAgent.getMainPageAgent().getUser().setUsername(newUsername);
        if (!oldUsername.equals(newUsername))
        {
            UserDB.getUserDB().changeUsername(Objects.requireNonNullElse(oldUsername, "0"), newUsername);
        }

        String oldEmail = MainPageAgent.getMainPageAgent().getUser().getEmail();
        String newEmail = editFormEvent.getEmail();
        MainPageAgent.getMainPageAgent().getUser().setEmail(newEmail);
        if (!oldEmail.equals(newEmail))
        {
            UserDB.getUserDB().changeEmail(Objects.requireNonNullElse(oldEmail, "0"), newEmail);
        }

        String oldPhoneNumber = MainPageAgent.getMainPageAgent().getUser().getPhoneNumber();
        String newPhoneNumber = editFormEvent.getPhoneNumber();
        MainPageAgent.getMainPageAgent().getUser().setPhoneNumber(newPhoneNumber);
        if (!oldPhoneNumber.equals(newPhoneNumber))
        {
            UserDB.getUserDB().changePhoneNumber(Objects.requireNonNullElse(oldPhoneNumber, "0"), newPhoneNumber);
        }

        MainPageAgent.getMainPageAgent().getUser().setPassword(editFormEvent.getPassword());
        MainPageAgent.getMainPageAgent().getUser().setName(editFormEvent.getName());
        MainPageAgent.getMainPageAgent().getUser().setBio(editFormEvent.getBio());
        MainPageAgent.getMainPageAgent().getUser().setBirthDate(editFormEvent.getBirthDate());
        MainPageAgent.getMainPageAgent().getUser().getProfile().setPrivate(editFormEvent.isPrivateState());
        MainPageAgent.getMainPageAgent().getUser().getProfile().setInfoState(editFormEvent.isInfoState());
        MainPageAgent.getMainPageAgent().getUser().getProfile().setLastSeenState(editFormEvent.getLastSeenState());
        MainPageAgent.getMainPageAgent().getUser().getProfile().setPicturePath(editFormEvent.getPicPath());

        UserDB.getUserDB().save(MainPageAgent.getMainPageAgent().getUser());
    }

    public void deactivate()
    {
        User user = MainPageAgent.getMainPageAgent().getUser();
        user.deactivate();
        UserDB.getUserDB().save(user);
    }

    public void deleteAccount()
    {
        User user = MainPageAgent.getMainPageAgent().getUser();
        
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
