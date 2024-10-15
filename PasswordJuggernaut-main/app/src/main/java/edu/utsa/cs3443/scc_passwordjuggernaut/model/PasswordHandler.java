package edu.utsa.cs3443.scc_passwordjuggernaut.model;

/**
 * All the imports that are needed for functionality.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * This class handles the passwords that don't involve accessing file.
 */
public class  PasswordHandler {

    /**
     * This variable is used for storing the generated password.
     */
    private String password;

    /**
     * generatePassword is called within generateActivity.
     * There are 4 variables that store specific characters.
     * This allows us to easily change what the password generates based on what the user wants.
     * It then returns the generated password at the end.
     * @param includeSpecialChar
     * @param includeNumbers
     * @param length
     */
    public void generatePassword(boolean includeSpecialChar, boolean includeNumbers, int length){
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:'.<>?/\\";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";

        StringBuilder charPool = new StringBuilder();
        charPool.append(upperCase);
        charPool.append(lowerCase);

        if (includeNumbers){
            charPool.append(numbers);
        }
        if (includeSpecialChar){
            charPool.append(specialChars);
        }

        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0 ; i < length; i++){
            result.append(charPool.charAt(random.nextInt(charPool.length())));
        }

        this.password = result.toString();
    }

    /**
     * A getter for password
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * deleteArray receives a index from the user and what password they want to remove.
     * Creates a new arrayList and then removes the password.
     * It then returns the arrayList overriding the old one in ViewActivity.
     * @param list
     * @param index
     * @return
     */
    public String[] deleteArray(String[] list, int index){
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(list));
        arrayList.remove(index - 1);
        return arrayList.toArray(new String[0]);
    }

}
