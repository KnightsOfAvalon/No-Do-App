// Designates the package that this interface is a part of
package com.example.nodo.data;

// Importing all the dependencies that the interface will use
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import com.example.nodo.model.NoDo;
import java.util.List;

@Dao // Establishes this interface as a Data Access Object for Android ROOM, meaning it can interact with SQLite database
public interface NoDoDao {
    // CRUD methods will be located inside this interface
    // Since NoDoDao is an interface, the methods will have no body. They must be implemented at a class level.

    // Method for adding items to the database.
    @Insert // Establishes insert an insert CRUD operation for the NoDoDao interface.
    void insert(NoDo noDo);

    // Designates the SQL code that will run when the deleteAll method is called.
    @Query("DELETE FROM nodo_table")
    // This is the method for deleting all items from the table.
    void deleteAll();

    // Designates the SQL code that will run when the deleteANoDo method is called.
    @Query("DELETE FROM nodo_table WHERE id = :id")
    // This is the method for deleting one particular item.
    int deleteANoDo(int id);

    // Designates the SQL code that will run when the updateNoDoItem method is called.
    @Query("UPDATE nodo_table SET nodo_col = :noDoText WHERE id = :id")
    // This is the method for updating one particular item.
    int updateNoDoItem(int id, String noDoText);

    // Designates the SQL code that will run when the getAllNoDos method is called.
    @Query("SELECT * FROM nodo_table ORDER BY nodo_col DESC")
    // This is the method for returning all the NoDos in the database.
    // Wrapping the list of no-dos inside LiveData allows the data from the db to be attached to the UI.
    // LiveData broadcasts when things inside the db has changed so that the UI can react accordingly.
    LiveData<List<NoDo>> getAllNoDos();
}
