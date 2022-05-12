package com.example.xquotes;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CopyListener{

    RecyclerView recyclerView;
    requestManager RequestManager;
    Adapter quoteAdapter;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#3700B3"));
        actionBar.setBackgroundDrawable(colorDrawable);
        actionBar.setTitle("Quotes");

        recyclerView = findViewById(R.id.rec_view);

        RequestManager = new requestManager(this);
        RequestManager.getAllQuotes(listener);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading");
        dialog.show();

    }

    private final Listener listener = new Listener() {
        @Override
        public void fetch(List<Model> response, String message) {
            showData(response);
            dialog.dismiss();

        }

        @Override
        public void Error(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this,message, Toast.LENGTH_SHORT).show();

        }
    };

    private void showData(List<Model> response) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        quoteAdapter = new Adapter(MainActivity.this,response,this);
        recyclerView.setAdapter(quoteAdapter);
    }

    @Override
    public void onCopyClicked(String text) {

        ClipboardManager clipboard = (ClipboardManager) getSystemService(MainActivity.this.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("copied", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(MainActivity.this, "Copied to Clipboard", Toast.LENGTH_SHORT).show();

    }
}