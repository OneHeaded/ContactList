package com.example.recyclerex;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recAdapt extends RecyclerView.Adapter<recAdapt.ViewHolder> {
    Context context;
    ArrayList<conatctmodel> arrayList;
    private int lastpos = -1;

    recAdapt(Context context, ArrayList<conatctmodel> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public recAdapt.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull recAdapt.ViewHolder holder, int position) {
        holder.textname.setText(arrayList.get(position).name);
        holder.textnumber.setText(arrayList.get(position).number);
        holder.imageView.setImageResource(arrayList.get(position).img);
        setAnimation(holder.itemView, position);

        holder.llrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.addlayout);

                EditText etname = dialog.findViewById(R.id.edtnam);
                EditText etnumber = dialog.findViewById(R.id.edtnum);
                Button btn = dialog.findViewById(R.id.btnact);
                TextView title = dialog.findViewById(R.id.title);

                btn.setText("Update");
                title.setText("Update Contact");

                etname.setText(arrayList.get(position).name);
                etnumber.setText(arrayList.get(position).number);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = "";
                        String number = "";

                        if (!etname.getText().toString().isEmpty()) {
                            name = etname.getText().toString();
                        } else {
                            Toast.makeText(context, "Enter name", Toast.LENGTH_SHORT).show();
                        }

                        if (!etnumber.getText().toString().isEmpty()) {
                            number = etnumber.getText().toString();
                        } else {
                            Toast.makeText(context, "Enter number", Toast.LENGTH_SHORT).show();
                        }
                        arrayList.set(position, new conatctmodel(R.drawable.b, number, name));
                        notifyItemChanged(position);
                        dialog.dismiss();
                    }

                });
                dialog.show();
            }

        });
        holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context)
                        .setTitle("Delete Contact")
                        .setMessage("Are you sure you want to delete this contact?")
                        .setIcon(R.drawable.outline_block_24)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                arrayList.remove(position);
                                notifyItemRemoved(position);

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                             @Override
                             public void onClick(DialogInterface dialogInterface, int i) {
                             }
                        });
                builder.show();
                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textname, textnumber;
        ImageView imageView;
        LinearLayout llrow;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textname = itemView.findViewById(R.id.txtname);
            textnumber = itemView.findViewById(R.id.txtnumber);
            imageView = itemView.findViewById(R.id.imgv);
            llrow = itemView.findViewById(R.id.llrow);
        }
    }
    private void setAnimation(View viewToAnimate, int position){
        if(position>lastpos){
            Animation slideIn = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(slideIn);
            lastpos = position;
        }
    }
}