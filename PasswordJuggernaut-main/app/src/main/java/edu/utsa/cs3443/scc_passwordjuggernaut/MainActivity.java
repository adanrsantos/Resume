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
 * MainActivity is our login screen and the start of the program.
 * It's purpose is to verify if the login credentials are correct.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Variables for the login section and fileHandler to access functions in filehandler.
     */
    private EditText usernameEditText;
    private EditText passwordEditText;
    private FileHandler fileHandler;

    /**
     * onCreate which is started when MainActivity is started.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        /**
         * Making the button and textEdit accessible.
         */
        usernameEditText = findViewById(R.id.edittext_username);
        passwordEditText = findViewById(R.id.edittext_password);
        Button loginButton = findViewById(R.id.button_login);
        Button createButton = findViewById(R.id.button_create);
        fileHandler = new FileHandler();

        /**
         * login button this gets what the user has in the edittext boxes.
         * calls authenticateUser to confirm information is correct and it is stored
         * in users.csv
         * If credentials are incorrect it'll display a toast to let the user know.
         */
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                if (fileHandler.authenticateUser(username, password, MainActivity.this)) {
                    Toast.makeText(MainActivity.this, "Logging In", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("username", username);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });

        /**
         * create button takes the user to the Create Screen so they can create a user.
         */
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
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