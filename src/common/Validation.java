package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Validation {

    private final static String VALID_USERNAME = "^\\S{5}\\S*$";
    private final static String VALID_PASSWORD = "^\\S{6}\\S*$";

    //check file data exist or not
    public boolean checkFileExist() {
        File file = new File("src\\user.dat");
        if (!file.exists()) {
            try {
                System.err.println("File not exist!!!");
                file.createNewFile();
                System.err.println("File created.");
                return false;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return true;
    }

    //allow user input username
    public boolean checkInputUsername(String result) {
        if (!result.matches(VALID_USERNAME)) {
            System.err.println("You must enter least at 5 character, and no space!");
            return false;
        }
        if (!checkUsernameExist(result)) {
            System.err.println("Username is already exist!");
            return false;
        }
        return true;
    }

    public boolean checkInputUsernameLogin(String result) {
        if (!result.matches(VALID_USERNAME)) {
            System.err.println("You must enter least at 5 character, and no space!");
            return false;
        }
        return true;
    }

    //allow user input password
    public boolean checkInputPassword(String result) {
        if (result.matches(VALID_PASSWORD)) {
            return true;
        }
        System.err.println("You must enter least at 6 character, and no space!");
        return false;
    }

    //check username exist
    public boolean checkUsernameExist(String username) {
        File file = new File("src\\user.dat");
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] account = line.split(";");
                if (username.equalsIgnoreCase(account[0])) {
                    return false;
                }
            }
            bufferedReader.close();
            fileReader.close();
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return true;
    }
}
