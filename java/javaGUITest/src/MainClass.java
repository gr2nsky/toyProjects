public class MainClass {

    LogIn login;
    SignUp signUp;

    public static void main(String args[]) {
        MainClass main = new MainClass();
        main.login = new LogIn(main);
    }

    public void moveToSignUp(){
        this.signUp = new SignUp();
    }

}
