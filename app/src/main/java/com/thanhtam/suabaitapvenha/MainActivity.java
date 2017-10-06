package com.thanhtam.suabaitapvenha;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    ImageView imgHinhGoc, imgHinhChon;
    public  static ArrayList<String> arrayImage; /*public static cho phép các màn hình khác gọi*/
    int REQUESTCODE = 123;
    String tenhinhgoc = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgHinhChon = (ImageView)findViewById(R.id.imageViewChon);
        imgHinhGoc = (ImageView)findViewById(R.id.imageViewHinhGoc);

        String[] manghinh = getResources().getStringArray(R.array.ListImage);      /* Lấy hình trong danh sách Array
        */
        arrayImage = new ArrayList<String>(Arrays.asList(manghinh));
        Collections.shuffle(arrayImage);/*Thứ tự vị trí trong mãng bị hoán đổi*/
        tenhinhgoc = arrayImage.get(0);
        int Idhinh = getResources().getIdentifier(arrayImage.get(0), "drawable", getPackageName()); /*lấy hình vị trí 0 trong mãng hình*/
        imgHinhGoc.setImageResource(Idhinh);        /*Gán idhinh hình vào ImageView*/

        imgHinhChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,DanhSachHinhAnh.class);
                startActivityForResult(intent,REQUESTCODE);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_hinhanh,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Collections.shuffle(arrayImage);
        tenhinhgoc = arrayImage.get(0);
        int idHinh = getResources().getIdentifier(arrayImage.get(0),"drawable",getPackageName());
        imgHinhGoc.setImageResource(idHinh);
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUESTCODE && resultCode == RESULT_OK){
            String tenhinhnhan = data.getStringExtra("data");
            int idHinh = getResources().getIdentifier(tenhinhnhan,"drawable",getPackageName());     /*Lấy hình từ màn hinh chọn hình ảnh*/
            imgHinhChon.setImageResource(idHinh);           /*Gán hình ảnh*/

            if (tenhinhnhan.equals(tenhinhgoc)){
                Toast.makeText(MainActivity.this,"Chính xác",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(MainActivity.this,"Sai rồi",Toast.LENGTH_SHORT).show();

            }
        }

        if (requestCode == REQUESTCODE && resultCode ==RESULT_CANCELED){
            Toast.makeText(MainActivity.this,"Bạn chưa chọn hình",Toast.LENGTH_SHORT).show();

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

}
