package com.chornyiua.myfinance.adapters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chornyiua.myfinance.R;
import com.chornyiua.myfinance.dto.BillDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionListAdapter.TransactionViewHolder> {

    private List<BillDTO> data;
    private List<String> category;
    private DataBaseHelper dbHelper;
    private Context context;

    public TransactionListAdapter(Context context) {
        this.context = context;
        this.dbHelper = new DataBaseHelper(this.context);
        readDataFromDB();
        readCategoryFromDB();
    }

    public List<BillDTO> getData() {
        return data;
    }

    public List<String> getCategory() {
        return category;
    }

    public void readDataFromDB() {
        data = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(dbHelper.BILL_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(dbHelper.BILL_KEY_ID);
            int dateIndex = cursor.getColumnIndex(dbHelper.BILL_DATE);
            int categoryIDIndex = cursor.getColumnIndex(dbHelper.BILL_CATEGORY_ID);
            int changeIndex = cursor.getColumnIndex(dbHelper.BILL_CHANGE);
            int valueIndex = cursor.getColumnIndex(dbHelper.BILL_VALUE);
            int commentIndex = cursor.getColumnIndex(dbHelper.BILL_COMMENT);
            do {
                data.add(new BillDTO(cursor.getInt(idIndex),
                                     cursor.getLong(dateIndex),
                                     cursor.getInt(categoryIDIndex),
                                     cursor.getInt(changeIndex),
                                     cursor.getInt(valueIndex),
                                     cursor.getString(commentIndex)));

            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
    }

    private void readCategoryFromDB() {
        category = new ArrayList<>();
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.query(dbHelper.CATEGORY_TABLE, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int nameIndex = cursor.getColumnIndex(dbHelper.CATEGORY_NAME);
            do {
                category.add(cursor.getString(nameIndex));

            } while (cursor.moveToNext());
        }
        cursor.close();
        dbHelper.close();
    }

    public DataBaseHelper getDbHelper() {
        return dbHelper;
    }

    @Override
    public TransactionListAdapter.TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transaction_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransactionListAdapter.TransactionViewHolder holder, int position) {
        holder.change.setText(data.get(position).getChange());
        holder.value.setText(data.get(position).getValue());
        holder.date.setText(DateFormat.format("MM.dd.yyyy", new Date(data.get(position).getDate())).toString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class TransactionViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView change;
        TextView value;
        TextView date;

        public TransactionViewHolder(View itemView) {
            super(itemView);
            change = (TextView) itemView.findViewById(R.id.tvChange);
            value = (TextView) itemView.findViewById(R.id.tvValue);
            date = (TextView) itemView.findViewById(R.id.tvDate);
            cardView = (CardView) itemView.findViewById(R.id.cvTransaction);
        }
    }
}
