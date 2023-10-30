package com.example.audiotest;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.Serial;

public class MainActivity extends AppCompatActivity {
    private TextView startTV, stopTV, playTV, stopplayTV, statusTV;
    private MediaRecorder mRecorder;
    private MediaPlayer mPlayer;
    private String mFileName = null;
    private static final int REQUEST_AUDIO_PERMISSION_CODE = 1;
    private static  int REQUEST_WRITE_EXTERNAL_STORAGE_PERMISSION ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTV = findViewById(R.id.idTVstatus);
        startTV = findViewById(R.id.btnRecord);
        stopTV = findViewById(R.id.btnStop);
        playTV = findViewById(R.id.btnPlay);
        stopplayTV = findViewById(R.id.btnStopPlay);
        stopTV.setBackgroundColor(getResources().getColor(R.color.gray));
        playTV.setBackgroundColor(getResources().getColor(R.color.gray));
        stopplayTV.setBackgroundColor(getResources().getColor(R.color.gray));

        startTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });
        stopTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });
        playTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });
        stopplayTV.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              pausePlaying();
                                          }
                                      }
        );
    }

    private void startRecording() {
        if (CheckPermissions()) {
            Log.d("Debug", "After check Permissions Function Success");
            stopTV.setBackgroundColor(getResources().getColor(R.color.purple_200));
            startTV.setBackgroundColor(getResources().getColor(R.color.gray));
            playTV.setBackgroundColor(getResources().getColor(R.color.gray));
            stopplayTV.setBackgroundColor(getResources().getColor(R.color.gray));

            mFileName = getExternalCacheDir().getAbsolutePath() + "/AudioRecording.3gp";
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile(mFileName);
            Log.d("Debug","The Filename is set as " + mFileName.toString());
            Toast.makeText(getApplicationContext(), mFileName, Toast.LENGTH_LONG).show();

            try {
                mRecorder.prepare();
                mRecorder.start();
                statusTV.setText("Recording Started");
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error starting recording.", Toast.LENGTH_SHORT).show();
            }
        } else {
            RequestPermissions();
        }
    }

    private void stopRecording() {
        stopTV.setBackgroundColor(getResources().getColor(R.color.gray));
        startTV.setBackgroundColor(getResources().getColor(R.color.purple_200));
        playTV.setBackgroundColor(getResources().getColor(R.color.purple_200));
        stopplayTV.setBackgroundColor(getResources().getColor(R.color.purple_200));

        try {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
            statusTV.setText("Recording Stopped");
        } catch (Exception e) {
            Log.d("Error", e.toString());
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error stopping recording.", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean CheckPermissions() {
        int result = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.RECORD_AUDIO);
        int result1;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            result1 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_MEDIA_AUDIO);
        }
        else {
            result1 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
//        int result1 = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        boolean LogResult = result ==  PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
        Log.d("Debug", "The Result of the checkpermission function is " + LogResult);
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    private void RequestPermissions() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Toast.makeText(getApplicationContext(), "Android Version 13+", Toast.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_MEDIA_AUDIO}, REQUEST_AUDIO_PERMISSION_CODE);

        }
        else {
             ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
        }
        // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_AUDIO_PERMISSION_CODE);
    }

    public void playAudio() {
        stopTV.setBackgroundColor(getResources().getColor(R.color.gray));
        startTV.setBackgroundColor(getResources().getColor(R.color.purple_200));
        playTV.setBackgroundColor(getResources().getColor(R.color.gray));
        stopplayTV.setBackgroundColor(getResources().getColor(R.color.purple_200));

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFileName);
            mPlayer.prepare();
            mPlayer.start();
            statusTV.setText("Recording Started Playing");
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Error playing audio.", Toast.LENGTH_SHORT).show();
        }
    }

    private void pausePlaying() {
        if (mPlayer != null) {
            mPlayer.release();
            mPlayer = null;
            stopTV.setBackgroundColor(getResources().getColor(R.color.gray));
            startTV.setBackgroundColor(getResources().getColor(R.color.purple_200));
            playTV.setBackgroundColor(getResources().getColor(R.color.purple_200));
            stopplayTV.setBackgroundColor(getResources().getColor(R.color.gray));
            statusTV.setText("Recording Play Stopped");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_AUDIO_PERMISSION_CODE) {
            if (grantResults.length > 0) {
                boolean permissionToRecord = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean permissionToStore = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (permissionToRecord && permissionToStore) {
                    Toast.makeText(getApplicationContext(), "Permissions Granted", Toast.LENGTH_LONG).show();
                } else {
                    StringBuilder deniedPermissions = new StringBuilder("Denied Permissions: ");

                    if (!permissionToRecord) {
                        deniedPermissions.append("Record Audio ");
                    }

                    if (!permissionToStore) {
                        deniedPermissions.append("Write to Storage ");
                    }

                    Toast.makeText(getApplicationContext(), deniedPermissions.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
