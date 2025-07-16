package com.example.recyclerex;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.helper.widget.Carousel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recview;
    FloatingActionButton btnOpen;
    ArrayList<conatctmodel> arrcon = new ArrayList<>();
    recAdapt con;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOpen = findViewById(R.id.btnAction);

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.addlayout);
                EditText edtname = dialog.findViewById(R.id.edtnam);
                EditText edtnum = dialog.findViewById(R.id.edtnum);
                Button btnact = dialog.findViewById(R.id.btnact);

                btnact.setOnClickListener(new View.OnClickListener() {

                    String name = "";
                    String num = "";

                    @Override
                    public void onClick(View view) {
                        if (!edtname.getText().toString().isEmpty()&&!edtnum.getText().toString().isEmpty()) {
                            name = edtname.getText().toString();
                            num = edtnum.getText().toString();
                            arrcon.add(new conatctmodel(R.drawable.b, num, name));
                        } else {
                            Toast.makeText(MainActivity.this, "Enter name and number", Toast.LENGTH_SHORT).show();
                        }

                        con.notifyItemInserted(arrcon.size()-1);
                        dialog.dismiss();
                    }

                });
                dialog.show();

            }

        });
        con = new recAdapt(this, arrcon);
        recview.setAdapter(con);

    }
}