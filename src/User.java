import java.util.Scanner;

public class User {
    private String userName;
    private String password;
    private String numberPhone;
    private boolean isMediator;

    public User(String userName , String password , String numberPhone, boolean isMediator){
        this.userName = userName;
        this.password = password;
        this.numberPhone = numberPhone;
        this.isMediator = isMediator;
    }

    public String getUserName () {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword () {
        return this.password;
    }

    public  void  setPassword (String password) {
        this.password = password;
    }

    public String getNumberPhone() {
        return this.numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public boolean getIsMediator() {
        return this.isMediator;
    }

    public void setIsMediator(boolean isMediator) {
        this.isMediator = isMediator;
    }

    public String toString (){
        StringBuilder output = new StringBuilder();
        output.append(this.userName).append(" ").append(this.numberPhone).append(" (");
        if (isMediator) {
            output.append("Real Estate Broker)");
        } else {
            output.append("Private User)");
        }
        return output.toString();
    }
}