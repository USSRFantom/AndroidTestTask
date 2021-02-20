package ussrfantom.com.example.androidtesttask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import ussrfantom.com.example.androidtesttask.adapters.UserAdapter;
import ussrfantom.com.example.androidtesttask.api.ApiFactory;
import ussrfantom.com.example.androidtesttask.api.ApiService;
import ussrfantom.com.example.androidtesttask.pojo.Datum;
import ussrfantom.com.example.androidtesttask.pojo.Example;

public class Users extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private UserAdapter adapter;
    private Disposable disposable;
    private List<Datum> datums;
    private Datum datum;
    private String code;
    private int page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        recyclerViewUsers= findViewById(R.id.recyclerViewUsers);
        adapter = new UserAdapter();
        adapter.setDatums(new ArrayList<Datum>());
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUsers.setAdapter(adapter);
        dataLoading();


        adapter.setOnUserClickListener(new UserAdapter.OnUserClickListener() {
            @Override
            public void onUserClick(int position) {
                Toast.makeText(Users.this, "Click " + position, Toast.LENGTH_SHORT).show();
                datums = adapter.getDatums();
                datum = datums.get(position);
                Toast.makeText(Users.this, "Click " + datum.getName() + position, Toast.LENGTH_SHORT).show();
            }
        });

        adapter.setOnReachEndListener(new UserAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                Toast.makeText(Users.this, "Конец списка", Toast.LENGTH_SHORT).show();
                dataLoading();
            }
        });



    }

    public  void dataLoading(){
        code = "4431084557";
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = ApiFactory.getApiService();
        disposable =  apiService.getExample(code, String.valueOf(page))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Example>() {
                    @Override
                    public void accept(Example example) throws Exception {
                        adapter.setDatums(example.getData());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(Users.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        page++;
    }


    @Override
    protected void onDestroy() {
        if (disposable != null){
            disposable.dispose();
        }
        super.onDestroy();
    }
}