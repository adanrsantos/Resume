package edu.utsa.cs3443.scc_passwordjuggernaut;

/**
 * All the imports that are needed for functionality.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.utsa.cs3443.scc_passwordjuggernaut.model.FileHandler;

/**
 * CreateActivity is focused on creating a new account for a new user.
 */
public class CreateActivity extends AppCompatActivity {

    /**
     * Variables required to store and access information.
     */
    private EditText usernameEditText;
    private EditText passwordEditText;
    private FileHandler fileHandler;

    /**
     * onCreate runs when the CreateActivity started.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);

        /**
         * Making the button and textEdit accessible.
         */
        usernameEditText = findViewById(R.id.edittext_username);
        passwordEditText = findViewById(R.id.edittext_password);
        Button doneButton = findViewById(R.id.button_done);
        Button backButton = findViewById(R.id.button_back);

        /**
         * This enables us to use filehandler.
         */
        fileHandler = new FileHandler();

        /**
         * done button that first checks if all the input is valid and will then call
         * createUser to store the new user.
         * It checks for errors by calling usernameTaken, isEmpty, and isFull to ensure problems
         * are avoided.
         */
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (fileHandler.usernameTaken(username, CreateActivity.this)) {
                    Toast.makeText(CreateActivity.this, "Username Taken", Toast.LENGTH_SHORT).show();
                }
                else if (username.isEmpty() || password.isEmpty()){
                    Toast.makeText(CreateActivity.this, "Missing Inputs", Toast.LENGTH_SHORT).show();
                }
                else if (fileHandler.isFull(CreateActivity.this)){
                    Toast.makeText(CreateActivity.this, "Max 20 Users", Toast.LENGTH_SHORT).show();
                }
                else{
                    fileHandler.createUser(username, password, CreateActivity.this);
                    Toast.makeText(CreateActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        /**
         * back button that returns the user to MainActivity to display them the login screen.
         */
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
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