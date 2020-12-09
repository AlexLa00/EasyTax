package app.cyberzen.easytax.auth;
//CyberZen
import app.cyberzen.easytax.model.User;

public interface AuthListener {
    void OnAuthentication(User user);
}
