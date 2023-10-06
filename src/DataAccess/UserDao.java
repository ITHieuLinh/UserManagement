package DataAccess;

import common.Library;
import common.Validation;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UserDao {

    private static UserDao instance = null;
    Validation v;
    Library l;

    public UserDao() {
        l = new Library();
        v = new Validation();
    }

    public static UserDao Instance() {
        if (instance == null) {
            synchronized (UserDao.class) {
                if (instance == null) {
                    instance = new UserDao();
                }
            }
        }
        return instance;
    }

    //create a new account
    public void createNewAccount() {
        //check file data exist or not
        if (!v.checkFileExist()) {
            System.out.println("File is not exist");
            return;
        }
        String username = l.inputString("Enter Username: ");
        while (!v.checkInputUsername(username)) {
            username = l.inputString("Enter username again: ");
        }
        String password = l.inputString("Enter password: ");
        while (!v.checkInputPassword(password)) {
            password = l.inputString("Enter password again: ");
        }
        addAccountData(username, password);

    }

    //login system
    public void loginSystem() {
        //check file data exist or not
        if (!v.checkFileExist()) {
            System.out.println("File is not exist");
            return;
        }
        String username = l.inputString("Enter Username: ");
        while (!v.checkInputUsernameLogin(username)) {
            username = l.inputString("Enter Username: ");
        }
        String password = l.inputString("Enter Password: ");
        while (!v.checkInputPassword(password)) {
            password = l.inputString("Enter Password again: ");
        }
        //check username exist or not
        if (!v.checkUsernameExist(username)) {
            if (!password.equalsIgnoreCase(passwordByUsername(username))) {
                System.err.println("Username or Password is incorrect.");
            } else {
                System.err.println("Login success. Welcome to Page");
                String url = "https://www.youtube.com/watch?v=ZO90EwWy19A";
                try {
                    Desktop.getDesktop().browse(new URI(url));
                } catch (IOException | URISyntaxException e) {
                    e.printStackTrace();
                }
            }

        } else {
            System.err.println("Username or Password is incorrect.");
        }
    }

    //write new account to data
    public void addAccountData(String username, String password) {
        File file = new File("src\\user.dat");
        try {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(username + ";" + password + "\n");
            fileWriter.close();
            System.err.println("Create successly.");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //get password by username
    public String passwordByUsername(String username) {
        File file = new File("src\\user.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return account[1];
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
