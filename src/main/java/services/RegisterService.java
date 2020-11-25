package services;

import connectors.DbConnector;
import model.RegistrationToken;
import model.User;
import providers.RegistrationTokenProvider;
import providers.UserDataProvider;

public class RegisterService {
    public boolean registerUserAndSendToken(final User user) {
        boolean result = false;
        SendEmailService sendEmailService = new SendEmailService();
        DbConnector.addUser(user);
        final User resultUser = DbConnector.loadUserByEmail(user.getLogin());

        if (resultUser != null) {
            String token = DbConnector.createRegistrationToken(resultUser);
            result = sendEmailService.sendEmailWithRegistrationToken(resultUser.getLogin(), token);
        }
        return result;
    }

    public int verifyUserToken(final String login, final String token) {
        RegistrationTokenProvider registrationTokenProvider = new RegistrationTokenProvider();
        UserDataProvider userDataProvider = new UserDataProvider();
        RegistrationToken resultToken = registrationTokenProvider.getRegistrationTokenByValue(token);
        User resultUser;
        if(resultToken != null && resultToken.getUserId() != 0) {
            resultUser = userDataProvider.loadUserByEmail(login);
            if(resultUser != null && resultUser.getId() == resultToken.getUserId()) {
                userDataProvider.setEmailConfirmed(resultUser.getId());
                return resultUser.getId();
            }
            else
                return 0;
        } else return 0;
    }
}
