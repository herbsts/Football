package herbsts.soccer;
 //
import android.content.Intent;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import herbsts.soccer.R;
import herbsts.soccer.pkgData.Player;

public class ProfileActivity extends AppCompatActivity {
    /*
    non-gui-attributes
     */
    private Database db = null;

    /*
    gui-attributes
     */
    public ListView listView;
    private ArrayList<String> arrayList;
    private ArrayAdapter<String> adapter;
    private EditText txtinput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        listView=(ListView)findViewById(R.id.listPlayer);
        String[] items = {"A","B"}; //Test daten; wird dann mit den Player Names gefüllt
        arrayList=new ArrayList<>(Arrays.asList(items));
        adapter=new ArrayAdapter<String>(this,R.layout.list_item,R.id.txtitem,arrayList);
        listView.setAdapter(adapter);
        txtinput=(EditText)findViewById(R.id.playerName);
        Button btn = (Button)findViewById(R.id.addPlayer);
        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
             String newPlayer = txtinput.getText().toString();
                arrayList.add(newPlayer);
                adapter.notifyDataSetChanged();
            }
        });
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.delete_id:
                arrayList.remove(info.position);
                adapter.notifyDataSetChanged();
                return true;
            case R.id.sturm_id:
                item.setChecked(true);
                return true;
            case R.id.mittelf_id:
                item.setChecked(true);
                return true;
            case R.id.verteidiger_id:
                item.setChecked(true);
                return true;
            case R.id.torwart_id:
                item.setChecked(true);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
        //return super.onContextItemSelected(item);
    }
}
