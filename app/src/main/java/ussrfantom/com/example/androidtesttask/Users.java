package ussrfantom.com.example.androidtesttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ussrfantom.com.example.androidtesttask.adapters.UserAdapter;
import ussrfantom.com.example.androidtesttask.pojo.Datum;

public class Users extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerViewUsers= findViewById(R.id.recyclerViewUsers);
        adapter = new UserAdapter();
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUsers.setAdapter(adapter);
        List<Datum> datums = new ArrayList<>();
        Datum datum = new Datum();
        Datum datum1 = new Datum();
        datum.setId("32423423423");
        datum.setName("Loli");
        datum.setCountry("Russia");
        datum.setLat("12312312");
        datum.setLon("3423423");
        datum1.setId("323123121223");
        datum1.setName("Gogi");
        datum1.setCountry("USA");
        datum1.setLat("112312");
        datum1.setLon("31213423");
        datums.add(datum);
        datums.add(datum1);
        adapter.setDatums(datums);




    }
}