package com.fedorrroff.custom;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final Game game = new Game();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button arr[] = new Button[]{
            findViewById(R.id.button0_0),
            findViewById(R.id.button0_1),
            findViewById(R.id.button0_2),
            findViewById(R.id.button1_0),
            findViewById(R.id.button1_1),
            findViewById(R.id.button1_2),
            findViewById(R.id.button2_0),
            findViewById(R.id.button2_1),
            findViewById(R.id.button2_2),
        };

        game.createField(arr);
        game.attachButtons(arr);

        game.setOnWinnerListener((winner) -> {
            DialogFragment dialog = new WinnerDialogFragment(winner, game, arr);
            dialog.show(getSupportFragmentManager(), "DIALOG");
        });
    }

    public static class WinnerDialogFragment extends DialogFragment {

        private String winner;
        private Game game;
        private Button[] buttons;

        public WinnerDialogFragment(String winner, Game game, Button[] buttons) {
            this.winner = winner;
            this.game = game;
            this.buttons = buttons;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("The WINNER is: " + winner)
                    .setPositiveButton("OK", (dialog, id) -> game.createField(buttons))
                    .setNegativeButton("NOT OK", (dialog, id) -> getActivity().finish());
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}
