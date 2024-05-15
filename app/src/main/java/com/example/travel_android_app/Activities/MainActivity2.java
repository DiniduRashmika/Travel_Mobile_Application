package com.example.travel_android_app.Activities;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.travel_android_app.Adapters.HotelAdapter;
import com.example.travel_android_app.Adapters.UserAdapter;
import com.example.travel_android_app.Adapters.Users;
import com.example.travel_android_app.Database.DBHandler;
import com.example.travel_android_app.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    private DBHandler dbHandler;
    private RecyclerView bookContainer, userContainer;
    private List<Hotel> hotelList;
    private HotelAdapter hotelAdapter;
    private List<Users> userList;
    private UserAdapter userAdapter;

    private Button addBook, addMem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        dbHandler = new DBHandler(MainActivity2.this);
        if (isRecordsTableEmpty() || isMembersTableEmpty()){
            addFirstRecs();
            Toast.makeText(MainActivity2.this,"Loading initial records...", Toast.LENGTH_LONG).show();
        }
        loadBooks();
        loadUsers();

        addBook = findViewById(R.id.insert_btn);
        addMem = findViewById(R.id.user_insert_btn);

        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, AddActivity.class);
                intent.putExtra("mode", "book");
                startActivity(intent);
            }
        });

        addMem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, AddActivity.class);
                intent.putExtra("mode", "member");
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadBooks();
        loadUsers();
    }

    private void addFirstRecs() {

        long currentTimeMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String formattedTimestamp = sdf.format(new Date(currentTimeMillis));

        dbHandler.addRecord("Hotel Ananthaya", "Anuradhapura","Anuradhapura, an ancient city in Sri Lanka, is renowned for its well-preserved ruins of an early Sinhala civilization. It features majestic stupas, monasteries, and historic palaces, showcasing rich cultural heritage.");
        dbHandler.addRecord("Hotel kola-Net","Galle","Galle, a historic city in Sri Lanka, is famous for its fortified old town, colonial architecture, and vibrant coastal scenery. The Galle Fort, a UNESCO World Heritage site, highlights its rich heritage.");
        dbHandler.addRecord("Hotel Rizza","Jaffna","Jaffna, a city in northern Sri Lanka, is known for its vibrant Tamil culture, historic temples, and colonial-era buildings. It offers unique cuisine, rich traditions, and beautiful coastal landscapes.");
        dbHandler.addRecord("Hotel Volio","Hikkaduwa","Hikkaduwa, a coastal city in Sri Lanka, is famed for its vibrant coral reefs, golden beaches, and lively nightlife. It's a popular destination for snorkeling, surfing, and diving enthusiasts. The city's relaxed atmosphere and diverse marine life make it a tropical paradise.");
        dbHandler.addRecord("Hotel Leriya","Pasikuda","Pasikuda, located on Sri Lanka's east coast, is renowned for its shallow, crystal-clear waters and pristine beaches. It's an ideal spot for swimming and water sports. The area's calm seas, luxurious resorts, and stunning sunsets make it a favorite destination for relaxation and leisure.");

        dbHandler.addMembers("Miss.Dinithi Pramodya",formattedTimestamp);
        dbHandler.addMembers("Mr.Rizwan Ahamed",formattedTimestamp);
        dbHandler.addMembers("Mr.Savindu Vishmika",formattedTimestamp);
        dbHandler.addMembers("Mr.Ashoka Kodikara",formattedTimestamp);
        dbHandler.addMembers("Mr.Palitha Seelarathna",formattedTimestamp);
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

    private void loadUsers() {

        userContainer = findViewById(R.id.user_container);
        dbHandler = new DBHandler(this);
        userList = new ArrayList<>();
        userAdapter = new UserAdapter(userList, this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        userContainer.setLayoutManager(layoutManager);
        userContainer.setAdapter(userAdapter);

        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String[] projection = {"mem_id", "name", "date"};
        Cursor cursor = db.query("members", projection, null, null, null, null, "mem_id DESC");

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int rec_id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("mem_id")));
                String id = String.valueOf(rec_id);
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String date = cursor.getString(cursor.getColumnIndexOrThrow("date"));

                Users user = new Users(id, name, date);
                userList.add(user);
            } while (cursor.moveToNext());

            cursor.close();
            userAdapter.notifyDataSetChanged();
        }

        db.close();

    }

    public boolean isRecordsTableEmpty() {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM records", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count == 0;
        }
        return true; // Assume empty if cursor is null
    }

    public boolean isMembersTableEmpty() {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM members", null);
        if (cursor != null) {
            cursor.moveToFirst();
            int count = cursor.getInt(0);
            cursor.close();
            return count == 0;
        }
        return true; // Assume empty if cursor is null
    }

}
