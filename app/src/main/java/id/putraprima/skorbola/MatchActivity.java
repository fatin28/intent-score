package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private TextView homeTeamInput;
    private TextView awayTeamInput;
    private ImageView homeTeamLogo;
    private ImageView awayTeamLogo;
    private Integer scoreHome;
    private Integer scoreAway;
    private TextView HomeScoreInput;
    private TextView AwayScoreInput;

    private static final String HASIL_KEY = "hasil";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeTeamInput = findViewById(R.id.txt_home);
        awayTeamInput = findViewById(R.id.txt_away);
        homeTeamLogo = findViewById(R.id.home_logo);
        awayTeamLogo = findViewById(R.id.away_logo);
        HomeScoreInput = findViewById(R.id.score_home);
        AwayScoreInput = findViewById(R.id.score_away);

        scoreHome = 0;
        scoreAway = 0;
        HomeScoreInput.setText("0");
        AwayScoreInput.setText("0");

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle extra = getIntent().getExtras();
            Bitmap bmp = extra.getParcelable("homeTeamLogo");
            Bitmap bmp2 = extra.getParcelable("awayTeamLogo");

            homeTeamLogo.setImageBitmap(bmp);
            awayTeamLogo.setImageBitmap(bmp2);

            homeTeamInput.setText(extras.getString("homeTeam"));
            awayTeamInput.setText(extras.getString("awayTeam"));

        }
    }

        public void handleAddHomeScore(View view){
            scoreHome++;
            HomeScoreInput.setText(String.valueOf(scoreHome));
        }

        public void handleAddAwayScore(View view){
            scoreAway++;
            AwayScoreInput.setText(String.valueOf(scoreAway));
        }
        public void handleHasil(View view) {
            String hasil;
            if(scoreHome>scoreAway){
                hasil = homeTeamInput.getText().toString();
            }else if(scoreAway>scoreHome){
                hasil = awayTeamInput.getText().toString();
            }else{
                hasil = "Draw";
            }
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra(HASIL_KEY, hasil);
            startActivity(intent);
        }


    }





