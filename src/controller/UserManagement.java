package controller;

import Repository.UserRepository;
import view.Menu;

public class UserManagement extends Menu<String> {

    static String[] mc = {"Create a new account", "Login system", "Exit"};
    UserRepository program;

    public UserManagement() {
        super("     USER MANAGEMENT SYSTEM", mc);
        program = new UserRepository();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                program.createNewAccount();
                break;
            case 2:
                program.loginSystem();
                break;
            case 3:
                System.exit(0);
        }
    }

}
