package apps.mainpage.pages.settings.logic;

import apps.mainpage.logic.MainPageAgent;
import apps.mainpage.pages.settings.event.EditFormEvent;
import db.UserDB;
import utils.Validations;

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
        MainPageAgent.getMainPageAgent().getUser().setUsername(editFormEvent.getUsername());
        MainPageAgent.getMainPageAgent().getUser().setPassword(editFormEvent.getPassword());
        MainPageAgent.getMainPageAgent().getUser().setName(editFormEvent.getName());
        MainPageAgent.getMainPageAgent().getUser().setPhoneNumber(editFormEvent.getPhoneNumber());
        MainPageAgent.getMainPageAgent().getUser().setBio(editFormEvent.getBio());
        MainPageAgent.getMainPageAgent().getUser().setBirthDate(editFormEvent.getBirthDate());
        MainPageAgent.getMainPageAgent().getUser().getProfile().setPrivate(editFormEvent.isPrivateState());
        MainPageAgent.getMainPageAgent().getUser().getProfile().setInfoState(editFormEvent.isInfoState());
        MainPageAgent.getMainPageAgent().getUser().getProfile().setLastSeenState(editFormEvent.getLastSeenState());
        MainPageAgent.getMainPageAgent().getUser().getProfile().setPicturePath(editFormEvent.getPicPath());
        UserDB.getUserDB().save(MainPageAgent.getMainPageAgent().getUser());
    }
}
