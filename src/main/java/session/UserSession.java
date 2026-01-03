package session;

import java.util.ArrayList;
import java.util.List;

public class UserSession {

    private static int id;
    private static String username;

    public static String getUsername() { return username; }
    public static int getId() { return id; }

    public static void setId(int id) { UserSession.id = id; }
    public static void setUsername(String username) { UserSession.username = username; }
}
