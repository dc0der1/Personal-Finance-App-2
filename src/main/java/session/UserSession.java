package session;

public class UserSession {

    private static int id = 1;
    private static String username = "admin";

    public static String getUsername() { return username; }
    public static int getId() { return id; }

    public static void setId(int id) { UserSession.id = id; }
    public static void setUsername(String username) { UserSession.username = username; }
}
