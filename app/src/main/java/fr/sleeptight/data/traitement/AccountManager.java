package fr.sleeptight.data.traitement;

/**
 * Created by zhengrui on 2016/5/11.
 */
public interface AccountManager {
    User createAccount(String Name, String password);
    User setUsername(String Name);
    User setPassword(String password);
    User setTokenGoogle(Token token);
    User setTokenfitbit(Token token);
    User deleteTokenGoogle();
    User deleteTokenfitbit();
    User login(String Name, String password);
    User logout();

}
