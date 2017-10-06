package com.thanhtam.suabaitapvenha;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

public class DanhSachHinhAnh extends Activity {
    TableLayout tblHinhAnh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_hinh_anh);
        tblHinhAnh =  (TableLayout)findViewById(R.id.tableLayoutHinhAnh);
        tblHinhAnh.setBackgroundColor(Color.CYAN);

        int soDong = 6;
        int soCot = 3;
//        Khai báo số dòng số cột và Hình Ảnh
        for (int x = 1; x <= soDong; x++){
            TableRow tableRow = new TableRow(this);

            for (int y = 1; y <= soCot; y++){
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new TableRow.LayoutParams(180,180));      /*Kích thước*/

                final int vitri = soCot * (x - 1) + (y - 1);          /*Công thức lấy hình không trùng*/

                int idHinh = getResources().getIdentifier(MainActivity.arrayImage.get(vitri),"drawable",getPackageName());
                imageView.setImageResource(idHinh);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.putExtra("data", MainActivity.arrayImage.get(vitri));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });

                tableRow.addView(imageView);        /*Thêm hình vào dòng*/
            }
            tblHinhAnh.addView(tableRow);           /*Thêm hình vào mãng*/

        }

    }
}
