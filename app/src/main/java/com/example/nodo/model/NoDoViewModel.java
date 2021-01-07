// Designates the package that this class is a part of
package com.example.nodo.model;

// Importing all the dependencies that this class will use
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.nodo.util.NoDoRepository;
import java.util.List;

// This NoDoViewModel class connects to the Repository and to the UI
public class NoDoViewModel extends AndroidViewModel {
    private NoDoRepository noDoRepository; // Creates a variable that represents the Repository
    private LiveData<List<NoDo>> allNoDos; // Creates a variable that represents the LiveData that contains the list of no-dos

    // This is the constructor for the NoDoViewModel class
    public NoDoViewModel(@NonNull Application application) {
        super(application); // This constructor must call super
        noDoRepository = new NoDoRepository(application); // Initializes the repository
        allNoDos = noDoRepository.getAllNoDos(); // Initializes the LiveData list of no-dos
    }

    // Implements the getAllNoDos method
    public LiveData<List<NoDo>> getAllNoDos() {
        // Returns a list of all the items in the database
        return allNoDos;
    }

    // Implements the insert method
    public void insert(NoDo noDo) {
        // Calls the insert method from NoDoRepository
        noDoRepository.insert(noDo);
    }
}
