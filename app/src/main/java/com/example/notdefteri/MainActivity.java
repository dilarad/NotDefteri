package com.example.notdefteri;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.notdefteri.adapter.NotAdapter;
import com.example.notdefteri.model.Not;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NoteListener {

    ImageView imageAddNote;
    Database db;

    NotAdapter adapter;
    RecyclerView recyclerView;
    ArrayList<Not> notListesi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }
    void initialize(){

        db=new Database(this);
        notListesi = new ArrayList<>();

        imageAddNote = findViewById(R.id.imageAddNote);
        recyclerView = findViewById(R.id.recylerviewNotlar);

        notListesi.addAll(db.getNotlarim());

        adapter = new NotAdapter(notListesi,this);
        recyclerView.setAdapter(adapter);
                

        imageAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NoteDetailsActivity.class);
                intent.putExtra("type", "newNote");
                startActivity(intent);
            }
        });
    }
    void updateNotListesi(){
        notListesi.clear();

        notListesi.addAll(db.getNotlarim());

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume(){
        super.onResume();
        updateNotListesi();
    }

    @Override
    public void NoteClick(Not not) {
        Intent intent = new Intent(getApplicationContext(), NoteDetailsActivity.class);
        intent.putExtra("type", "updateNote");
        intent.putExtra("selectedNote",not);
        startActivity(intent);
    }


}