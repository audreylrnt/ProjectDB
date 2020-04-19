package com.example.projectdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBHelper myDb;
    StudentListAdaptor adaptor;
    EditText etname;
    EditText etsurname;
    EditText etmarks;
    Button btnInsert, btnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DBHelper(this);
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptor = new StudentListAdaptor(this, myDb.getData());
        recyclerView.setAdapter(adaptor);
        etname = findViewById(R.id.etname);
        etsurname = findViewById(R.id.etsurname);
        etmarks = findViewById(R.id.etmarks);
        btnInsert = findViewById(R.id.btninsert);
//        btnView = findViewById(R.id.btnview);
        addData();

//        viewData();
    }
    public void addData(){
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(etname.getText().toString(), etsurname.getText().toString(), etmarks.getText().toString());
                if(isInserted){
                    Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data failed to be inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
        adaptor.swapCursor(myDb.getData());
    }


//    public void viewData(){
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Cursor res = myDb.getData();
//                if(res.getCount() == 0){
//                    showMessage("Oops!", "Nothing to display!");
//                    return;
//                }
//                StringBuffer buffer = new StringBuffer();
//                while(res.moveToNext()){
//                    buffer.append("Id: " + res.getString(0) + "\n");
//                    buffer.append("Name: " + res.getString(1) + "\n");
//                    buffer.append("Surname: " + res.getString(2) + "\n");
//                    buffer.append("Marks: " + res.getString(3) + "\n\n");
//                }
//                showMessage("Data", buffer.toString());
//            }
//        });
//    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
