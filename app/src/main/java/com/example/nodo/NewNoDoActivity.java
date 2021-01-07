// Designates what package this activity is a part of
package com.example.nodo;

// Importing all the dependencies that this activity will use
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoDoActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.nodo.reply"; // Creates a variable that holds a string representing a reply
    private EditText editText; // Creates a variable that will hold an edit text view from the layout

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_no_do);

        editText = findViewById(R.id.edit_nodo); // Initializes the edit text
        final Button button = findViewById(R.id.button_save); // Initializes the save button

        // Sets an onClick listener on the save button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent(); // Creates a new intent

                // If the edit text box is empty...
                if (TextUtils.isEmpty(editText.getText())) {
                    //...set the result to cancelled with the new intent
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    //...Otherwise...
                    //...Create a string from the text that the user entered in the edit text box
                    String noDoString = editText.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, noDoString); //...mark the string with the tag and put it with the new intent
                    setResult(RESULT_OK, replyIntent); //...then, set the result to okay with the new intent
                }

                finish(); // Finishes this activity
            }
        });
    }
}