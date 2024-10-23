public class PasswordPanelBackend extends AuthorizedUsers {
    private String username;
    private String password;
    private boolean isManager;
    private boolean accessGranted = false;

    // Constructor
    public void grantAccess() {
        for (int i = 0; i < getUsers().length; i++) {
            if(username.equals(getUsers()[i]) && password.equals(getPasswords()[i])) {
                setAccessGranted(true);
            }
        }
        if((username.equals("m1") || username.equals("m2")) && (isAccessGranted())) {
            setManager(true);
        }
    }

    // Getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public boolean isAccessGranted() {
        return accessGranted;
    }

    public void setAccessGranted(boolean accessGranted) {
        this.accessGranted = accessGranted;
    }
}
