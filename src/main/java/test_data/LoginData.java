package test_data;

public class LoginData {
    public final static String USERNAME = required("APP_USERNAME");
    public final static String PASSWORD = required("APP_PASSWORD");

    private static String required(String key) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            value = System.getProperty(key);
        }
        if (value == null || value.isBlank()) {
            throw new IllegalStateException(
                    "Missing credential '" + key + "'. Export it as an environment variable or pass -D" + key + "=... (see .env.example)");
        }
        return value;
    }
}
