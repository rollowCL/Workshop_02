package org.school;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private int id;
    private String userName;
    private String email;
    private String password;
    private int admin;
    private int userGroupId;

    public User() {
    }

    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.hashPassword(password);
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
    }

    public int getUserGroupId() {
        return userGroupId;
    }

    public int getAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public User(int id, String userName, String email, String password, int userGroupId) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.hashPassword(password);
        this.userGroupId = userGroupId;
    }

    public User(String userName, String email, String password, int userGroupId) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.hashPassword(password);
        this.userGroupId = userGroupId;
    }

    public void hashPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "org.school.User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public String validate() {
        if (this.userName == null || "".equals(this.userName)) {
            return "Missing userName";
        }
        if (this.email == null || "".equals(this.email)) {
            return "Missing email";
        }
        String regExp = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}";
        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(this.email);

        if (!matcher.matches()) {
            return "Email not OK";
        }

        return "OK";
    }
}
