/*
 * Copyright 2020 David Canto-Alcayaga davidcanto01@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.dsm.dcanto.stylesapp;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

  // Member variables for holding the score
  private int mScore1;
  private int mScore2;

  // Variables for the score text view elements
  private TextView mScoreText1;
  private TextView mScoreText2;

  // Tags to be used as the keys in OnSaveInstanceState
  static final String STATE_SCORE_1 = "Team 1 Score";
  static final String STATE_SCORE_2 = "Team 2 Score";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //Find the TextViews by ID
    mScoreText1 = (TextView)findViewById(R.id.score_1);
    mScoreText2 = (TextView)findViewById(R.id.score_2);

    if(savedInstanceState != null){
      mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
      mScore2 = savedInstanceState.getInt(STATE_SCORE_2);

      // Set the score text views
      mScoreText1.setText(String.valueOf(mScore1));
      mScoreText2.setText(String.valueOf(mScore2));
    }
  }

  @Override
  protected void onSaveInstanceState(@NonNull Bundle outState) {
    // Save the scores.
    outState.putInt(STATE_SCORE_1, mScore1);
    outState.putInt(STATE_SCORE_2, mScore2);
    super.onSaveInstanceState(outState);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_menu, menu);
    // Change the label of the menu based on the state of the app
    int nightMode = AppCompatDelegate.getDefaultNightMode();
    if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
      menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
    } else{
      menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
    }
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    // Check if correct item was clicked
    if(item.getItemId()==R.id.night_mode){
      // Get the night mode state of the app
      int nightMode = AppCompatDelegate.getDefaultNightMode();
      // Set the theme mode for the restarted activity
      if(nightMode==AppCompatDelegate.MODE_NIGHT_YES){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
      } else {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
      }
      // Recreate the activity for the theme change to take effect.
      recreate();
    }
    return true;
  }
  /**
   * Method that handles the onClick of both the decrement buttons
   * @param view The button view that was clicked
   */
  public void decreaseScore(View view) {
    // Get the ID of the button that was clicked
    int viewID = view.getId();
    switch (viewID){
      //If it was on Team 1
      case R.id.decreaseTeam1:
        //Decrement the score and update the TextView
        mScore1--;
        mScoreText1.setText(String.valueOf(mScore1));
        break;
      //If it was Team 2
      case R.id.decreaseTeam2:
        //Decrement the score and update the TextView
        mScore2--;
        mScoreText2.setText(String.valueOf(mScore2));
    }
  }

  /**
   * Method that handles the onClick of both the increment buttons
   * @param view The button view that was clicked
   */
  public void increaseScore(View view) {
    //Get the ID of the button that was clicked
    int viewID = view.getId();
    switch (viewID){
      //If it was on Team 1
      case R.id.increaseTeam1:
        //Increment the score and update the TextView
        mScore1++;
        mScoreText1.setText(String.valueOf(mScore1));
        break;
      //If it was Team 2
      case R.id.increaseTeam2:
        //Increment the score and update the TextView
        mScore2++;
        mScoreText2.setText(String.valueOf(mScore2));
    }
  }
}