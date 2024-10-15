package edu.utsa.cs3443.scc_passwordjuggernaut.model;

/**
 * All the imports that are needed for functionality.
 */

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class is part of the model section and handles all of the file handling.
 * This makes it easier to focus purely on accessing files and keeping it consistent
 * by having this class handle all the files.
 */
public class FileHandler {

    /**
     * The variables below hold the files name for userfile and password file to make them
     * easily accessible.
     */
    private String userfile = "users.csv";
    private String passwordfile = "passwords.csv";

    /**
     * Authenticate User checks if the username and password is correct by checking the userfile
     * which has all the users/passwords stored. Once it finds a username/password that matches
     * what the user input it'll return true allowing the user to move to the next screen (login).
     * @param username
     * @param password
     * @param context
     * @return
     */
    public boolean authenticateUser(String username, String password, Context context){
        try (FileInputStream fis = context.openFileInput(userfile);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                if (tokens[0].equals(username) && tokens[1].equals(password)){
                    return true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * usernameTaken is called when the user is creating a new account.
     * This is needed to ensure there are no duplicates and everyone has a unique username.
     * @param username
     * @param context
     * @return
     */
    public boolean usernameTaken(String username, Context context){
        try (FileInputStream fis = context.openFileInput(userfile);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                if (tokens[0].equals(username)){
                    return true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * isFull is used to ensure that the user doesn't overflow the storage.
     * It is called during the create user process.
     * @param context
     * @return
     */
    public boolean isFull(Context context){
        final int MAX_USERS = 20;
        int count = 0;

        try (FileInputStream fis = context.openFileInput(userfile);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)){
            String line;
            while ((line = reader.readLine()) != null){
                count++;
            }
            if (count >= MAX_USERS){
                return true;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * createUser receives input from the user username/password and
     * writes it into users.csv.
     * @param username
     * @param password
     * @param context
     */
    public void createUser(String username, String password, Context context){
        try{
            FileOutputStream file = context.openFileOutput(userfile, Context.MODE_APPEND);
            OutputStreamWriter output = new OutputStreamWriter(file);
            output.write(username + "," + password + "\n");
            output.close();
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }

    /**
     * savePassword is used when the user wants to save a generated password.
     * It writes the information in passwords.csv
     * @param username
     * @param password
     * @param context
     */
    public void savePassword(String username, String password, Context context){
        try{
            FileOutputStream file = context.openFileOutput(passwordfile, Context.MODE_APPEND);
            OutputStreamWriter output = new OutputStreamWriter(file);
            output.write(username + "," + password + "\n");
            output.close();
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }

    /**
     * repeatedPassword checks if the password that the user wants to save is already saved.
     * @param username
     * @param password
     * @param context
     * @return
     */
    public boolean repeatedPassword(String username, String password, Context context){
        try (FileInputStream fis = context.openFileInput(passwordfile);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                if (tokens[0].equals(username) && tokens[1].equals(password)){
                    return true;
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * storeList is called at the beginning in viewActivity so it can
     * store all the passwords within viewActivity to easily access it through there.
     * @param username
     * @param context
     * @return
     */
    public String[] storeList(String username, Context context){
        ArrayList<String> passwordList = new ArrayList<>();
        try (FileInputStream fis = context.openFileInput(passwordfile);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                if (tokens[0].equals(username)){
                    passwordList.add(tokens[1]);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return passwordList.toArray(new String[0]);
    }

    /**
     * updatePasswords updates the file everytime the user deletes a password.
     * It achieves this by checking each line and first seeing if the username matches.
     * If the username does not match it'll automatically store that information since we are
     * not focused on users that are not logged in. If it does match it checks if it contains
     * a matching password. If it does find a matching password that means the password is
     * still supposed to be store. If a password does not match that means it was removed and won't
     * be added onto the new file.
     * @param username
     * @param list
     * @param context
     */
    public void updatePasswords(String username, String[] list, Context context){
        List<String> lines = new ArrayList<>();

        try (FileInputStream fis = context.openFileInput(passwordfile);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader reader = new BufferedReader(isr)){
            String line;
            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                if (tokens[0].equals(username)){
                    if (Arrays.asList(list).contains(tokens[1])){
                        lines.add(line);
                    }
                }
                else{
                    lines.add(line);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

        try (FileOutputStream fos = context.openFileOutput(passwordfile, Context.MODE_PRIVATE);
             OutputStreamWriter output = new OutputStreamWriter(fos)) {
            for (String line : lines) {
                output.write(line + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
