// Designates what package this activity is a part of
package com.example.nodo;

// Importing all dependencies that this activity will use
import android.content.Intent;
import android.os.Bundle;

import com.example.nodo.model.NoDo;
import com.example.nodo.model.NoDoViewModel;
import com.example.nodo.ui.NoDoListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private static final int NEW_NODO_REQUEST_CODE = 1; // Creates a variable that holds the request code
    private NoDoListAdapter noDoListAdapter; // Creates a variable that will hold the recycler view adapter
    private NoDoViewModel noDoViewModel; // Creates a variable that will hold the view model

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar); // Initializes the toolbar view
        setSupportActionBar(toolbar);

        // Initializes the view model
        noDoViewModel = ViewModelProviders.of(this)
                .get(NoDoViewModel.class);

        RecyclerView recyclerView = findViewById(R.id.recyclerView); // Initializes the recycler view
        noDoListAdapter = new NoDoListAdapter(this); // Initializes the recycler view adapter
        recyclerView.setAdapter(noDoListAdapter); // Sets the recycler view adapter on the recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Sets a layout manager on the recycler view
        FloatingActionButton fab = findViewById(R.id.fab); // Initializes the floating action button

        // Sets an onClick listener on the floating action button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creates a new intent
               Intent intent = new Intent(MainActivity.this, NewNoDoActivity.class);

               // Starts a new activity -- moves from the current activity (main activity) to
                // the NewNoDoActivity. Also, the request code is passed to the new activity.
               startActivityForResult(intent, NEW_NODO_REQUEST_CODE);
            }
        });

        noDoViewModel.getAllNoDos().observe(this, new Observer<List<NoDo>>() {
            @Override
            public void onChanged(List<NoDo> noDos) {
                // Update the saved copy of no-dos in the adapter
                noDoListAdapter.setNoDos(noDos);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == NEW_NODO_REQUEST_CODE && resultCode == RESULT_OK) {
            assert data != null;
            NoDo noDo = new NoDo(Objects.requireNonNull(data.getStringExtra(NewNoDoActivity.EXTRA_REPLY)));
            noDoViewModel.insert(noDo);
        } else {
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_LONG).show();
        }
    }
}