package com.example.projectdb;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentListAdaptor extends RecyclerView.Adapter<StudentListAdaptor.ViewHolder> {
    private Context leContext;
    private Cursor res;
    public StudentListAdaptor(Context context, Cursor cursor) {
        leContext = context;
        res = cursor;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView idText;
        public TextView nameText;
        public TextView surnameText;
        public TextView marksText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.tvid);
            nameText = itemView.findViewById(R.id.tvname);
            surnameText = itemView.findViewById(R.id.tvsurname);
            marksText = itemView.findViewById(R.id.tvmarks);
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(leContext);
        View view = inflater.inflate(R.layout.student_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(!res.moveToPosition(position)){
            return;
        }
        String id = res.getString(res.getColumnIndex(DBHelper.COL_1));
        String name = res.getString(res.getColumnIndex(DBHelper.COL_2));
        String surname = res.getString(res.getColumnIndex(DBHelper.COL_3));
        String marks = res.getString(res.getColumnIndex(DBHelper.COL_4));
        holder.idText.setText(id);
        holder.nameText.setText(name);
        holder.surnameText.setText(surname);
        holder.marksText.setText(marks);
    }

    @Override
    public int getItemCount() {
        return res.getCount();
    }
    public void swapCursor (Cursor newCursor){
        if(res != null){
            res.close();
        }
        res = newCursor;
        if(newCursor != null){
            notifyDataSetChanged();
        }
    }

}
