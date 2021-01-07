// Designates the package that this class is a part of
package com.example.nodo.model;

// Importing all the dependencies that this class will use
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nodo_table") // Establishes the NoDo class as an entity or table and gives it a name
public class NoDo {
    @PrimaryKey(autoGenerate = true) // Establishes the id as a primary key and causes id to be auto-generated
    private int id; // Creates an integer variable that will hold an id

    @NonNull // Prevents noDo variable from being null
    @ColumnInfo(name = "nodo_col") // Puts noDo strings into a column named "nodo_col"
    private String noDo; // Creates a string variable that will hold a no do

    // This is the constructor for the NoDo class. It only requires one String parameter to be passed and that
    // String parameter may not be null
    public NoDo(@NonNull String noDo) {
        this.noDo = noDo;
    }

    // This is the getter for the NoDo variable. It gives other parts of the application indirect access
    // to the NoDo variable.
    public String getNoDo() {
        return noDo;
    }

    // This is the getter for the id variable. It gives other parts of the application indirect access
    // to the id variable.
    public int getId() {
        return id;
    }

    // This is the setter for the id variable. It allows other parts of the application the ability
    // to indirectly alter the id variable.
    public void setId(int id) {
        this.id = id;
    }

    // This is the setter for the NoDo variable. It allows other parts of the application the ability
    // to indirectly alter the NoDo variable.
    public void setNoDo(@NonNull String noDo) {
        this.noDo = noDo;
    }
}
