// Designates the package that this class is a part of
package com.example.nodo.util;

// Importing all of the dependencies that this class will use
import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import com.example.nodo.data.NoDoDao;
import com.example.nodo.data.NoDoRoomDatabase;
import com.example.nodo.model.NoDo;
import java.util.List;


// This class will be our Repository. Using a Repository is optional when using a Room Database. Using a
// Repository is helpful because it is a convenient way of separating our concerns. The Room Database, SQL,
// and entities all control the backend while the UI and LiveData controls how and when data is displayed, so our
// Repository only has to worry about retrieving data from the backend (database) and providing it to the frontend (UI).
// The Repository does not care what kind of backend database the data is coming from.
public class NoDoRepository {
    private NoDoDao noDoDao; // Creates a variable that has access to the DAO class
    private LiveData<List<NoDo>> allNoDos; // Creates a variable that has access to LiveData

    // This is the constructor for the NoDoRepository class. We pass in the most global context of
    // application because the lifecycle of the Repository must exist beyond an Activity cycle or anything
    // else in our database.
    public NoDoRepository(Application application) {
        // Creates an instance of the database. Similar to a database handler.
        NoDoRoomDatabase db = NoDoRoomDatabase.getDatabase(application);

        noDoDao = db.noDoDao(); // Creates a Dao for our database
        allNoDos = noDoDao.getAllNoDos(); // Gets all no-dos from the database
    }

    // Implements the getAllNoDos method
    public LiveData<List<NoDo>> getAllNoDos() {
        // Returns all of the items from the database
        return allNoDos;
    }

    // Insert method that calls an asynchronous task
    public void insert(NoDo noDo){
        // This line of code instantiates and executes the insertAsyncTask class
        new insertAsyncTask(noDoDao).execute(noDo);
    }

    // This is the insertAsyncTask method that is called by the insert method. It is asynchronous,
    // meaning that it can be performed in the background without interrupting the main thread.
    private class insertAsyncTask extends AsyncTask<NoDo, Void, Void> {
        private NoDoDao asyncTaskDao; // Creates a variable that represents the DAO for this async task

        // This is the constructor of the insertAsyncTask class
        public insertAsyncTask(NoDoDao dao) {
            asyncTaskDao = dao;
        }

        // Overrides the parent class method
        @Override
        // Performs tasks in the background instead of on the main thread. The NoDo... params in parentheses allows
        // as many parameters as needed to be passed in
        protected Void doInBackground(NoDo... params) {
            // Since this method accepts an unlimited number of parameters, this line of code specifies
            // that only the first NoDo that is passed in will be inserted into the database.
            // Parameters will be passed in inside an array ([obj1, obj2...])
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
