package com.chornyiua.myfinance;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.chornyiua.myfinance.adapters.TransactionListAdapter;
import com.chornyiua.myfinance.dto.CategoryDTO;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    TransactionListAdapter adapter;
    int currentValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddTransactionDialog();
            }
        });

        rv = (RecyclerView) findViewById(R.id.rvMain);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new TransactionListAdapter(this);
        rv.setAdapter(adapter);
        if (adapter.getData().size()>0) currentValue = adapter.getData().get(adapter.getData().size()-1).getValue();
                else currentValue = 0;
    }

    private void showAddTransactionDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.add_transaction_dialog, null);
        dialogBuilder.setView(dialogView);
        final EditText etChange = (EditText) dialogView.findViewById(R.id.etChange);
        final EditText etComment = (EditText) dialogView.findViewById(R.id.etComment);
        final Spinner spinner = (Spinner) dialogView.findViewById(R.id.spinner);
        ArrayAdapter<String> categoryDTOArrayAdapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, adapter.getCategory());
        spinner.setAdapter(categoryDTOArrayAdapter);

        dialogBuilder.setTitle("Add transaction");
        dialogBuilder.setMessage("Enter category name");
        dialogBuilder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                adapter.getDbHelper().insertTransaction(spinner.getSelectedItemPosition(), Integer.parseInt(etChange.getText().toString()), currentValue, etComment.getText().toString());

                currentValue = adapter.readDataFromDB();
                adapter.notifyDataSetChanged();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        dialogBuilder.create().show();
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
}
