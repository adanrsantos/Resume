package edu.utsa.cs3443.scc_passwordjuggernaut;

/**
 * All the imports that are needed for functionality.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * HomeActivity focused on displaying the options the user can perform in this app.
 */
public class HomeActivity extends AppCompatActivity {

    /**
     * Variable to keep track of the username to send to other activities.
     */
    private String username;

    /**
     * onCreate which is started when HomeActivity is started.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        /**
         * gets the username from MainActivity to keep track of who is logged in.
         */
        username = getIntent().getStringExtra("username");

        /**
         * Making the button and textEdit accessible.
         */
        Button generateButton = findViewById(R.id.button_generate);
        Button viewButton = findViewById(R.id.button_view);
        Button logoutButton = findViewById(R.id.button_logout);

        /**
         * generateButton that takes us to the GenerateActivity and
         * passes username to keep track of user.
         */
        generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, GenerateActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

        /**
         * viewButton that takes us to the ViewActivity and
         * passes username to keep track of user.
         */
        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ViewActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
                finish();
            }
        });

        /**
         * logoutButton that takes us back to MainActivity / login screen.
         * this doesn't need to pass the username since we're logging out.
         */
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
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