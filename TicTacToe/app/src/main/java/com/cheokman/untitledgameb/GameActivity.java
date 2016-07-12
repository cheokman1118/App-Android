package com.cheokman.untitledgameb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by Administrator on 6/25/2016.
 */
public class GameActivity extends Activity {
    public static final String KEY_RESTORE = "key_restore";
    public static final String PREF_RESTORE = "pref_restore";
    private GameFragment gameFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //continuted game
        gameFragment = (GameFragment) getFragmentManager().findFragmentById(R.id.fragment_game);
        boolean restore = getIntent().getBooleanExtra(KEY_RESTORE,false);
        if (restore){
            String gameData = getPreferences(MODE_PRIVATE).getString(PREF_RESTORE,null);
            if (gameData !=null){
                gameFragment.putState(gameData);
            }
        }
        Log.d("UT3","restore="+restore);
    }

    @Override
    protected void onPause() {
        super.onPause();
        String gameData = gameFragment.getState();
        getPreferences(MODE_PRIVATE).edit().putString(PREF_RESTORE,gameData).commit();
        Log.d("UT3","state="+gameData);
    }
    public void restartGame(){
        gameFragment.restartGame();
    }

    public void reportWinner(final Tile.Owner winner){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.declare_winner,winner));
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               finish();
            }
        });
        final Dialog dialog = builder.create();
        dialog.show();
        gameFragment.initGame();
    }
}
