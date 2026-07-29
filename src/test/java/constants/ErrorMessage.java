package constants;

public enum ErrorMessage {
    // Login
    WRONG_CREDENTIALS("Epic sadface: Username and password do not match any user in this service"),
    NO_USERNAME("Epic sadface: Username is required"),
    LOCKED_USER("Epic sadface: Sorry, this user has been locked out."),

    // Checkout
    FIRST_NAME_REQUIRED("Error: First Name is required"),
    LAST_NAME_REQUIRED("Error: Last Name is required"),
    POSTAL_CODE_REQUIRED("Error: Postal Code is required");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
