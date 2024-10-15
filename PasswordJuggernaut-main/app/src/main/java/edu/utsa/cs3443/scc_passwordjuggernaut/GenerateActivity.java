package edu.utsa.cs3443.scc_passwordjuggernaut;

/**
 * All the imports that are needed for functionality.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.utsa.cs3443.scc_passwordjuggernaut.model.FileHandler;
import edu.utsa.cs3443.scc_passwordjuggernaut.model.PasswordHandler;

/**
 * GenerateActivity allows the user to customize their generated password.
 * Also gives an option to save their password.
 */
public class GenerateActivity extends AppCompatActivity {

    /**
     * Variables required to store and access information.
     */
    private String username;
    private boolean includeNumbers;
    private boolean includeSpecialChars;
    private int length;
    private EditText lengthEditText;
    private TextView passwordTextView;
    private String result;
    private FileHandler fileHandler;
    private PasswordHandler passwordHandler;

    /**
     * onCreate runs when the GenerateActivity started.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_generate);

        /**
         * Variables that are grabbed / set when GenerateActivity is ran.
         */
        username = getIntent().getStringExtra("username");
        includeSpecialChars = true;
        includeNumbers = true;
        length = 0;

        /**
         * Making the button and textEdit accessible.
         */
        lengthEditText = findViewById(R.id.edittext_length);
        Button trueChar = findViewById(R.id.button_trueChar);
        Button falseChar = findViewById(R.id.button_falseChar);
        Button trueNum = findViewById(R.id.button_trueNum);
        Button falseNum = findViewById(R.id.button_falseNum);
        Button generate = findViewById(R.id.button_generate);
        Button save = findViewById(R.id.button_save);
        Button back = findViewById(R.id.button_back);
        passwordTextView = findViewById(R.id.textview_results);

        /**
         * This enables us to use filehandler and passwordhandler classes.
         */
        fileHandler = new FileHandler();
        passwordHandler = new PasswordHandler();

        /**
         * true button for the user to decide if they want to include special characters.
         */
        trueChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                includeSpecialChars = true;
                Toast.makeText(GenerateActivity.this, "Including Special Characters!", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * false button for the user to decide if they want to include special characters.
         */
        falseChar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                includeSpecialChars = false;
                Toast.makeText(GenerateActivity.this, "Not Including Special Characters!", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * true button for the user to decide if they want to include number characters.
         */
        trueNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                includeNumbers = true;
                Toast.makeText(GenerateActivity.this, "Including Numbers!", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * false button for the user to decide if they want to include number characters.
         */
        falseNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                includeNumbers = false;
                Toast.makeText(GenerateActivity.this, "Not Including Numbers!", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * generate button that checks if the input is correct and then generates the password.
         * it calls passwordHandler.generatePassword to generate password passing in the values
         * the user has decided.
         * calls passwordHandler.getPassword to get and store the password here for accessibility.
         * TextView to show the password that was generated.
         */
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    length = Integer.parseInt(lengthEditText.getText().toString());
                    if (length >= 8 && length <= 20){
                        passwordHandler.generatePassword(includeSpecialChars, includeNumbers, length);
                        result = passwordHandler.getPassword();
                        passwordTextView.setText(result);
                    }
                    else{
                        Toast.makeText(GenerateActivity.this, "Invalid Length", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(NumberFormatException e){
                    Toast.makeText(GenerateActivity.this, "Invalid Length Input", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * save button that stores the generated password calling fileHandler.savePassword.
         * before doing that it checks for errors that could potentially mess up the app.
         * For example saving a repeated password or if there is no generated password to be saved.
         */
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fileHandler.repeatedPassword(username, result, GenerateActivity.this)){
                    Toast.makeText(GenerateActivity.this, "Already Saved Password", Toast.LENGTH_SHORT).show();
                }
                else if (result == null || result.isEmpty()){
                    Toast.makeText(GenerateActivity.this, "Missing Generated Password", Toast.LENGTH_SHORT).show();
                }
                else{
                    fileHandler.savePassword(username, result, GenerateActivity.this);
                    Toast.makeText(GenerateActivity.this, "Password Saved!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * Back button so the user can go back towards the Home Screen to decide if they want to
         * logout, generate new password, or go back into view again.
         */
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GenerateActivity.this, HomeActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}