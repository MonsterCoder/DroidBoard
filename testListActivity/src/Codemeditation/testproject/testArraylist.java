package Codemeditation.testproject;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class testArraylist extends ListActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String[] array = {"t1", "t2", "t3"};
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);
        setListAdapter(adapter);
    }
}