package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText homeTeamInput;
    private EditText awayTeamInput;
    private ImageView homeTeamLogo;
    private ImageView awayTeamLogo;

    public static final String HOMETEAM_KEY = "homeTeam";
    public static final String AWAYTEAM_KEY = "awayTeam";
    public static final String HOMETEAMIMG_KEY = "homeTeamLogo";
    public static final String AWAYTEAMIMG_KEY = "awayTeamLogo";

    private static final String TAG = MainActivity.class.getCanonicalName();
    private static final int GALLERY_REQUEST_CODE = 1&2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        homeTeamInput = findViewById(R.id.home_team);
        awayTeamInput = findViewById(R.id.away_team);
        homeTeamLogo = findViewById(R.id.home_logo);
        awayTeamLogo = findViewById(R.id.away_logo);

    }

    public void nextAction(View view) {
        String homeTeam = homeTeamInput.getText().toString();
        String awayTeam = awayTeamInput.getText().toString();

        if ((homeTeamInput).equals("") || (awayTeamInput).equals("")){
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }

        else {
            Intent intent = new Intent(this, MatchActivity.class);
            homeTeamLogo.buildDrawingCache();
            awayTeamLogo.buildDrawingCache();
            Bitmap homeTeamImage = homeTeamLogo.getDrawingCache();
            Bitmap awayTeamImage = awayTeamLogo.getDrawingCache();
            Bundle extras = new Bundle();
            extras.putParcelable(HOMETEAMIMG_KEY, homeTeamImage);
            extras.putParcelable(AWAYTEAMIMG_KEY, awayTeamImage);
            intent.putExtras(extras);
            intent.putExtra(HOMETEAM_KEY, homeTeam);
            intent.putExtra(AWAYTEAM_KEY, awayTeam);
            startActivity(intent);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            return;
        }
        if (requestCode == 1) {
            if(data != null){
                try{
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    homeTeamLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        if(requestCode == 2){
            if (data != null){
                try {
                    Uri imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    awayTeamLogo.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void changeImage1(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }
    public void changeImage2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}


