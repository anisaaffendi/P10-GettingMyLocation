package sg.edu.rp.id19030019.p10_gettingmylocation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class RecordActivity extends AppCompatActivity {

    ListView lv;
    Button btnRefresh;
    TextView tvRecord;
    ArrayList<String> record;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        lv = findViewById(R.id.listView);
        btnRefresh = findViewById(R.id.btnRefresh);
        tvRecord = findViewById(R.id.tvRecord);

        record = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, record);
        lv.setAdapter(adapter);

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MyFolder", "location.txt");
                    if(!file.exists()){
                        if(file.createNewFile())
                            Log.d("Create new file", "File created");
                        else{
                            Log.d("Create new file", "Fail to create");
                        }
                    }else{
                        Log.d("Create new file", "Failed to create");
                        Scanner scanner = new Scanner(file);
                        while(scanner.hasNextLine()){
                            record.add(scanner.nextLine());
                        }
                        scanner.close();
                        adapter.notifyDataSetChanged();
                        tvRecord.setText("Number of records: " + record.size());
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}