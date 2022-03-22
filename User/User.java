package User;

public class User {
    // Personal Info
    protected String id;
    protected String name;
    protected String password;
    protected String user_name;

    User(String id_, String name_, String password_, String user_name_){
        id = id_;
        name = name_;
        password = password_;
        user_name = user_name_;
    }

    public void changePassword(String pass_){password = pass_;}
    public void changeUserName(String name_){user_name = name_;}
}
