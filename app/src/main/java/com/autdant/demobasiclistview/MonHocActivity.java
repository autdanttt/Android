package com.autdant.demobasiclistview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MonHocActivity extends AppCompatActivity {
    Button btnAdd, btnEdit;
    EditText edtText;

    ListView lvMonHoc;
    List<String> listMonHoc;
    ArrayAdapter adapter;
    int index = -1;
    boolean clickDelete = false;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_monhoc);
        lvMonHoc = findViewById(R.id.lvMonHoc);
        btnAdd = findViewById(R.id.btnAdd);
        btnEdit = findViewById(R.id.btnEdit);
        edtText = findViewById(R.id.edtMonHoc);

        listMonHoc = new ArrayList<>();
        listMonHoc.add("Java");
        listMonHoc.add("PHP");
        listMonHoc.add("Android");
        listMonHoc.add("JavaScript");
        listMonHoc.add("C#");

        adapter = new ArrayAdapter(MonHocActivity.this, android.R.layout.simple_list_item_1, listMonHoc);
        lvMonHoc.setAdapter(adapter);
        lvMonHoc.setOnItemClickListener((parent, view, position, id) -> {
            String monHoc = listMonHoc.get(position);
            edtText.setText(monHoc);
            index = position;
        });
        lvMonHoc.setOnItemLongClickListener((parent, view, position, id) -> {
            showDeleteConfirmationDialog();
            adapter.notifyDataSetChanged();
            if(clickDelete == true){
                listMonHoc.remove(position);
            }
            return true;
        });

    }

    public void addMonHoc(View view){
        String monHoc = edtText.getText().toString();
        listMonHoc.add(monHoc);
        adapter.notifyDataSetChanged();
    }
    public void editMonHoc(View view){
        String monHoc = edtText.getText().toString();
        listMonHoc.set(index, monHoc);
        adapter.notifyDataSetChanged();
    }

    private void showDeleteConfirmationDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MonHocActivity.this);

        // Đặt tiêu đề và nội dung thông báo
        builder.setTitle("Cảnh báo");
        builder.setMessage("Bạn thật sự muốn xóa?");

        // Đặt nút "CÓ"
        builder.setPositiveButton("CÓ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn "CÓ"
                clickDelete = true;
            }
        });

        // Đặt nút "KHÔNG"
        builder.setNegativeButton("KHÔNG", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Xử lý khi người dùng chọn "KHÔNG" (đóng thông báo)
                dialog.dismiss();
            }
        });

        // Tạo và hiển thị bảng thông báo
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
