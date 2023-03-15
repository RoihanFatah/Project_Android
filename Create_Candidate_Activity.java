package com.example.votingsystem;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.votingsystem.helper.RealPathUtil;

import java.util.ArrayList;
import java.util.List;

public class Create_Candidate_Activity extends AppCompatActivity {

    private ImageView ketuaImg, wakilImg;
    private EditText candidateName, visiMisi;
    private Spinner kelasSpinner;
    private Button submitBtn;
    private TextView ketuatv, wakiltv, tv_test;
    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_candidate);

        ketuaImg = findViewById(R.id.ketua_image);
        candidateName = findViewById(R.id.candidate_name);
        wakilImg = findViewById(R.id.wakil_image);
        visiMisi = findViewById(R.id.visi_text);
        kelasSpinner = findViewById(R.id.kelas_spinner);
        submitBtn = findViewById(R.id.candidate_submit_button);
        ketuatv = findViewById(R.id.ketua_tv);
        wakiltv = findViewById(R.id.wakil_tv);

        subKelas();
        bukaGaleri();
        submitButton();

    }

    public void submitButton(){

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hasil = candidateName.getText().toString();
                Toast.makeText(getApplicationContext(), hasil, Toast.LENGTH_LONG).show();

            }
        });
    }

    public void subKelas(){

        List<String> kelasArray = new ArrayList<String>();
        kelasArray.add("XI RPL");
        kelasArray.add("XI AK 1");
        kelasArray.add("XI AK 2");
        kelasArray.add("XI AK 3");
        kelasArray.add("XI BDP 1");
        kelasArray.add("XI BDP 2");
        kelasArray.add("XI PBK");
        kelasArray.add("XI MM 1");
        kelasArray.add("XI MM 2");
        kelasArray.add("XI MM 3");
        kelasArray.add("XI OTKP 1");
        kelasArray.add("XI OTKP 2");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, kelasArray
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.kelas_spinner);
        sItems.setAdapter(adapter);

    }

    public void bukaGaleri(){
        ketuaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                    Intent data = new Intent(Intent.ACTION_GET_CONTENT);
                    data.setType("image/*");
                    data = Intent.createChooser(data, "Pilih Gambar");
                    sActivityRes.launch(data);
                } else {
                    ActivityCompat.requestPermissions(Create_Candidate_Activity.this,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    Intent data = new Intent(Intent.ACTION_GET_CONTENT);
                    data.setType("image/*");
                    data = Intent.createChooser(data, "Pilih Gambar");
                    sActivityRes.launch(data);
                }
            }
        });


        wakilImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                    Intent data = new Intent(Intent.ACTION_GET_CONTENT);
                    data.setType("image/*");
                    data = Intent.createChooser(data, "Pilih Gambar");
                    sActivityRes2.launch(data);
                } else {
                    ActivityCompat.requestPermissions(Create_Candidate_Activity.this,
                            new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                    Intent data = new Intent(Intent.ACTION_GET_CONTENT);
                    data.setType("image/*");
                    data = Intent.createChooser(data, "Pilih Gambar");
                    sActivityRes2.launch(data);
                }
            }
        });
    }


    ActivityResultLauncher<Intent> sActivityRes = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        Context context = Create_Candidate_Activity.this;
                        path = RealPathUtil.getRealPath(context, uri);
                        Toast.makeText(context, path, Toast.LENGTH_SHORT).show();

                        ketuaImg.setImageURI(uri);
                    } else {
                        Toast.makeText(Create_Candidate_Activity.this, result.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

    ActivityResultLauncher<Intent> sActivityRes2 = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri uri = data.getData();
                        Context context = Create_Candidate_Activity.this;
                        path = RealPathUtil.getRealPath(context, uri);
                        Toast.makeText(context, path, Toast.LENGTH_SHORT).show();

                        wakilImg.setImageURI(uri);
                    } else {
                        Toast.makeText(Create_Candidate_Activity.this, result.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );

}