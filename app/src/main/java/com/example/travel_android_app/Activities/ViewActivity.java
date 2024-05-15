package com.example.travel_android_app.Activities;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_android_app.Adapters.HotelAdapter;
import com.example.travel_android_app.Database.DBHandler;
import com.example.travel_android_app.R;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends AppCompatActivity {

    TextView tileText, authText, descText;
    Button updateBtn,delBtn;
    DBHandler dbHandler;
    RecyclerView bookContainer;
    List<Hotel> hotelList;
    HotelAdapter hotelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);


        String book_id = getIntent().getStringExtra("id");
        String title = getIntent().getStringExtra("title");
        String author = getIntent().getStringExtra("author");
        String desc = getIntent().getStringExtra("desc");


        //Toast.makeText(this, book_id, Toast.LENGTH_LONG).show();

        tileText = findViewById(R.id.book_title);
        authText = findViewById(R.id.book_auth);
        descText = findViewById(R.id.book_desc);
        updateBtn = findViewById(R.id.update_btn);
        delBtn = findViewById(R.id.del_btn);

        loadBooks();

        tileText.setText(title);
        authText.setText(author);
        descText.setText(desc);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, AddActivity.class);
                intent.putExtra("mode", "update");
                intent.putExtra("id", book_id);
                intent.putExtra("title", title);
                intent.putExtra("author", author);
                intent.putExtra("desc", desc);
                startActivity(intent);
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ViewActivity.this);
                builder.setMessage("You are going to delete this !");
                builder.setTitle("   Delete");
                builder.setCancelable(true);
                builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
                    dialog.cancel();
                });
                builder.setPositiveButton("Delete", (DialogInterface.OnClickListener) (dialog, which) -> {
                    deleteRecord(book_id, title, author, desc);

                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    private void deleteRecord(String bookId, String title, String author, String desc) {
        DBHandler dbHandler = new DBHandler(ViewActivity.this);
        SQLiteDatabase db = dbHandler.getWritableDatabase();

        String selection = "rec_id = ? AND title = ? AND Place = ? AND description = ?";
        String[] selectionArgs = {bookId, title, author, desc};
        db.delete("records", selection, selectionArgs);

        db.close();
        Toast.makeText(ViewActivity.this,"Hotel deleted successfully!", Toast.LENGTH_LONG).show();
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
    }

    private void loadBooks() {

        bookContainer = findViewById(R.id.book_container);
        dbHandler = new DBHandler(this);
        hotelList = new ArrayList<>();
        hotelAdapter = new HotelAdapter(hotelList, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        bookContainer.setLayoutManager(layoutManager);
        bookContainer.setAdapter(hotelAdapter);

        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String[] projection = {"rec_id", "title", "author", "description"};
        Cursor cursor = db.query("records", projection, null, null, null, null, "rec_id DESC");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int rec_id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("rec_id")));
                String id = String.valueOf(rec_id);
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String author = cursor.getString(cursor.getColumnIndexOrThrow("author"));
                String desc = cursor.getString(cursor.getColumnIndexOrThrow("description"));

                Hotel hotel = new Hotel(id, title, author, desc);
                hotelList.add(hotel);
            } while (cursor.moveToNext());

            cursor.close();
            hotelAdapter.notifyDataSetChanged();
        }

        db.close();

    }


}

