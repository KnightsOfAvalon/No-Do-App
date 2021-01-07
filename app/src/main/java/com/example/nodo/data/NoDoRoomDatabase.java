// Designates the package that this class is a part of
package com.example.nodo.data;

// Importing all the dependencies that this class will use
import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.nodo.model.NoDo;

// This annotation establishes the NoDoRoomDatabase class as a database type and connects the entities to it.
@Database(entities = {NoDo.class}, version = 1)
// An abstract class cannot be instantiated, which is good in this case.
// As an abstract class, this class can only be implemented at the class level, just like an interface.
// All of the methods inside an abstract class must be abstract as well.
public abstract class NoDoRoomDatabase extends RoomDatabase {

    // Volatile means that this variable will not be saved in any way
    public static volatile NoDoRoomDatabase INSTANCE;
    // Implements the Data Access Object
    public abstract NoDoDao noDoDao();

    // The goal at this point is to use the DAO to create a singular instance of the Room Database

    // Creates a singleton instance of the database
    public static NoDoRoomDatabase getDatabase(final Context context) {
        // These nested if statements will make sure that there aren't any previous instances of
        // the NoDoRoomDatabase before creating one.
        if (INSTANCE == null) {
            synchronized (NoDoRoomDatabase.class) {
                if (INSTANCE == null) {
                    // This line of code creates the database. The context, the database class, and the name of the
                    // database is passed in.
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            NoDoRoomDatabase.class, "nodo_database")
                            .addCallback(roomDatabaseCallback) // Calls the callback before building
                            .build(); // builds the database
                }
            }
        }

        // returns the single instance of the database class
        return INSTANCE;
    }

    // This is the room database callback that is called right before building the room database
    private static RoomDatabase.Callback roomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {
        // Creates a variable that will hold the Data Access Object
        private final NoDoDao noDoDao;

        public PopulateDbAsync(NoDoRoomDatabase db) {
            noDoDao = db.noDoDao(); // Gives the Data Access Object access to the database
        }

        @Override
        protected Void doInBackground(Void... voids) {
            // noDoDao.deleteAll(); // removes all items from the table
            // for testing
            // NoDo noDo = new NoDo("Buy a new Ferrari");
            // noDoDao.insert(noDo);

            // noDo = new NoDo("Buy a big house");
            // noDoDao.insert(noDo);

            return null;
        }
    }
}
