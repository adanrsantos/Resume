package edu.utsa.cs3443.scc_passwordjuggernaut;

/**
 * All the imports that are needed for functionality.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.utsa.cs3443.scc_passwordjuggernaut.model.FileHandler;
import edu.utsa.cs3443.scc_passwordjuggernaut.model.PasswordHandler;

/**
 * ViewActivity class handles displaying the passwords stored and removes them.
 */
public class ViewActivity extends AppCompatActivity {

    /**
     * Variables required to store and access information.
     */
    private String username;
    private EditText deletePass;
    private int index;
    private PasswordHandler passwordHandler;
    private FileHandler fileHandler;
    private String[] list;
    int count;

    /**
     * onCreate runs when the ViewActivity started.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view);
        /**
         * this gets the username from other activities so we can easily track the user.
         */
        username = getIntent().getStringExtra("username");
        index = -1;

        /**
         * Making the button and textEdit accessible.
         */
        deletePass = findViewById(R.id.edittext_delete);
        Button delete = findViewById(R.id.button_delete);
        Button back = findViewById(R.id.button_back);

        /**
         * This enables us to use filehandler and passwordhandler classes.
         */
        passwordHandler = new PasswordHandler();
        fileHandler = new FileHandler();

        /**
         * gets the information from passwords file and stores it into a String array.
         */
        list = fileHandler.storeList(username, ViewActivity.this);

        /**
         * TextView array so we can easily access multiple textViews and
         * set the size depending on the size of the list array.
         */
        TextView[] textViews = new TextView[] {findViewById(R.id.text1), findViewById(R.id.text2), findViewById(R.id.text3),
                findViewById(R.id.text4), findViewById(R.id.text5), findViewById(R.id.text6), findViewById(R.id.text7), findViewById(R.id.text8),
                findViewById(R.id.text9), findViewById(R.id.text10), findViewById(R.id.text11), findViewById(R.id.text12), findViewById(R.id.text13),
                findViewById(R.id.text14), findViewById(R.id.text15), findViewById(R.id.text16), findViewById(R.id.text17), findViewById(R.id.text18),
                findViewById(R.id.text19), findViewById(R.id.text20)};

        updateView(textViews, list);

        /**
         * The delete checks if input is correct and deletes the password the user requested.
         * It does this by calling passwordHandler.deleteArray and receives a new array overriding
         * the previous one.
         * It also calls updateView so the screen refreshes what is being display.
         */
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    index = Integer.parseInt(deletePass.getText().toString());
                    if (index >= 1 && index <= 20){
                        if (index > list.length){
                            Toast.makeText(ViewActivity.this, "Invalid Number Input", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            list = passwordHandler.deleteArray(list, index);
                            updateView(textViews, list);
                            Toast.makeText(ViewActivity.this, "Password Deleted", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(ViewActivity.this, "Pick a Number 1 - 20", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(NumberFormatException e){
                    Toast.makeText(ViewActivity.this, "Invalid Number Input", Toast.LENGTH_SHORT).show();
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
                fileHandler.updatePasswords(username, list, ViewActivity.this);
                Intent intent = new Intent(ViewActivity.this, HomeActivity.class);
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

    /**
     * Our updateView that is called when delete button is clicked.
     * This allows to the user to see an updated version of their list.
     * @param textViews
     * @param list
     */
    private void updateView(TextView[] textViews, String[] list) {
        for (TextView textView: textViews){
            textView.setText("");
        }

        for (int i = 0; i < list.length; i++){
            textViews[i].setText((i + 1) + ":    " + list[i]);
            textViews[i].setVisibility(View.VISIBLE);
        }
    }

}