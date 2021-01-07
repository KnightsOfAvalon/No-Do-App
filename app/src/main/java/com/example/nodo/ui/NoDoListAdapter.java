// Designates the package that this class is a part of
package com.example.nodo.ui;

// Importing all the dependencies that this class will use
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nodo.R;
import com.example.nodo.model.NoDo;
import java.util.List;

// This is a recycler view adapter. It is used to set up recycler views that are used in the app's activities.
public class NoDoListAdapter extends RecyclerView.Adapter<NoDoListAdapter.NoDoViewHolder> {

    private final LayoutInflater noDoInflater; // Creates a variable that holds the layout inflater
    private List<NoDo> noDoList; // Creates a variable that will hold a cached copy of no-do items

    // This is the constructor for the NoDoListAdapter
    public NoDoListAdapter(Context context) {
        noDoInflater = LayoutInflater.from(context); // Initializes the layout adapter
    }

    @NonNull // Ensures that the method does not return null
    @Override // Overrides the parent class method
    // The onCreateViewHolder method is called when the view holder is first created
    public NoDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // This line of code creates a variable that holds a view that inflates the recycler_view_item.xml
        // using the layout inflater
        View view = noDoInflater.inflate(R.layout.recycler_view_item, parent, false);
        return new NoDoViewHolder(view); // Returns a new view holder and passes in the created view
    }

    @Override // Overrides the parent class method
    // This onBindViewHolder method is called when the user interface is bound
    public void onBindViewHolder(@NonNull NoDoViewHolder holder, int position) {

        // If the list of no-dos is not empty...
        if (noDoList != null) {
            NoDo current = noDoList.get(position); // ... create a variable that holds the no-do that is located at the specified position
            holder.noDoTextView.setText(current.getNoDo()); // ... and set the text view of the passed in holder equal to the current no-do
        } else {
            // ... Otherwise, set the text view of the passed in holder equal to a string that indicates there are no no-dos
            holder.noDoTextView.setText(R.string.no_no_todo);
        }
    }

    // This setNoDo method is a helper method. When outside the list adapter, it can be called to
    // update the list of no-dos and inform the UI that the data set has changed.
    public void setNoDos(List<NoDo> noDos) {
        noDoList = noDos; // Sets the noDoList equal to the list of passed in noDos
        notifyDataSetChanged(); // Notifies the UI that the data from the database has changed
    }

    @Override // Overrides the parent class method
    // This getItemCount method returns a count of no-do items
    public int getItemCount() {
        // If the list of no-dos is not empty...
        if (noDoList != null) {
            return noDoList.size(); // ...then return an integer that represents the size of the list
        } else return 0; // ...Otherwise, return 0
    }

    // This is the view holder. This will create references to the different components of the
    // xml file that it inflates. In this case, it is inflating the recycler_view_item.xml.
    public class NoDoViewHolder extends RecyclerView.ViewHolder {
        public TextView noDoTextView; // Creates a variable that will hold the text view from the xml

        // This is the constructor for the view holder
        public NoDoViewHolder(@NonNull View itemView) {
            super(itemView); // The view holder constructor must call super
            noDoTextView = itemView.findViewById(R.id.textView); // Initializes the variable that holds the text view from the xml
        }
    }
}
