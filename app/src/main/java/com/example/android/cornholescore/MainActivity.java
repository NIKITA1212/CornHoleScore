package com.example.android.cornholescore;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Grow With Google Challenge Scholarship: Android Basics
 * Project: 2
 * Version: 2
 * App Name: Cornhole Score!
 * Author: Joseph McDonald
 */

public class MainActivity extends AppCompatActivity {

    // A score of 21 points wins the game.
    static final int WINNING_SCORE = 21;

    // A team with no more bags to throw.
    static final int NO_BAGS = 0;

    // Total score for Team A.
    int scoreTeamA = 0;

    // Total score for Team B.
    int scoreTeamB = 0;

    // Bags remaining to throw for Team A.
    int bagsTeamA = 4;

    // Bags remaining to throw for Team B.
    int bagsTeamB = 4;

    // Current inning score for Team A.
    int scoreTeamAInning = 0;

    // Current inning score for Team B.
    int scoreTeamBInning = 0;

    // The game starts at Inning 1.
    int currentInning = 1;

    // The game starts without a winner.
    boolean noWinner = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find all the TextViews.
        final TextView viewScoreTeamA = findViewById(R.id.score_team_a_total);
        final TextView viewScoreTeamB = findViewById(R.id.score_team_b_total);
        final TextView viewScoreTeamAInning = findViewById(R.id.score_team_a_inning);
        final TextView viewScoreTeamBInning = findViewById(R.id.score_team_b_inning);
        final TextView viewBagsTeamA = findViewById(R.id.bags_team_a);
        final TextView viewBagsTeamB = findViewById(R.id.bags_team_b);
        final TextView viewInningCurrent = findViewById(R.id.inning_current);
        final TextView viewStatusGame = findViewById(R.id.status_game);

        // Find all the Buttons.
        final Button buttonPlus3TeamA = findViewById(R.id.button_plus3_a);
        final Button buttonPlus1TeamA = findViewById(R.id.button_plus1_a);
        final Button buttonZeroTeamA = findViewById(R.id.button_zero_a);
        final Button buttonMinus1TeamA = findViewById(R.id.button_minus1_a);
        final Button buttonPlus3TeamB = findViewById(R.id.button_plus3_b);
        final Button buttonPlus1TeamB = findViewById(R.id.button_plus1_b);
        final Button buttonZeroTeamB = findViewById(R.id.button_zero_b);
        final Button buttonMinus1TeamB = findViewById(R.id.button_minus1_b);
        final Button buttonResetGame = findViewById(R.id.button_reset);
        final Button buttonNextInning = findViewById(R.id.button_next_inning);

        // Create OnClickListener for all the Button actions.
        View.OnClickListener mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.button_plus3_a:
                        // If we have a no winner, and team still has bags, add 3 points to score
                        //    and decrement bag count and display them.
                        //    If we have a winner, display score and announce winner.
                        // else if both teams are out of bags, remind to press next inning.
                        if (noWinner) {
                            if (bagsTeamA > NO_BAGS) {
                                viewScoreTeamAInning.setText(scoreBag("teamA", 3));
                                viewBagsTeamA.setText(decrBag("teamA"));
                                viewStatusGame.setText(R.string.status_three_points);

                                if (scoreTeamA >= WINNING_SCORE) {
                                    noWinner = false;
                                    viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_a)));

                                } else if (scoreTeamB >= WINNING_SCORE) {
                                    noWinner = false;
                                    viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_b)));
                                }

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_plus1_a:
                        // If we have a no winner, and team still has bags, add 1 point to score
                        //    and decrement bag count and display them.
                        //    If we have a winner, display score and announce winner.
                        // else if both teams are out of bags, remind to press next inning.
                        if (noWinner) {
                            if (bagsTeamA > NO_BAGS) {
                                viewScoreTeamAInning.setText(scoreBag("teamA", 1));
                                viewBagsTeamA.setText(decrBag("teamA"));
                                viewStatusGame.setText(R.string.status_one_point);

                                if (scoreTeamA >= WINNING_SCORE) {
                                    noWinner = false;
                                    viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_a)));

                                } else if (scoreTeamB >= WINNING_SCORE) {
                                    noWinner = false;
                                    viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_b)));
                                }

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_zero_a:
                        // If we have a no winner, and team still has bags, decrement bag count and display.
                        // else if both teams are out of bags, remind to press next inning.
                        if (noWinner) {
                            if (bagsTeamA > NO_BAGS) {
                                viewBagsTeamA.setText(decrBag("teamA"));
                                viewStatusGame.setText(R.string.status_zero_points);

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_minus1_a:
                        // If we have a no winner, and team still has bags, subtract 1 point to score
                        //    and display.
                        // else if both teams are out of bags, remind to press next inning.
                        if (noWinner) {
                            if (scoreTeamAInning > 0) {
                                viewScoreTeamAInning.setText(scoreBag("teamA", -1));
                                viewStatusGame.setText(R.string.status_minus_point);

                            } else {
                                viewStatusGame.setText(R.string.status_zero_score);
                            }
                        }
                        break;

                    case R.id.button_plus3_b:
                        // If we have a no winner, and team still has bags, add 3 points to score
                        //    and decrement bag count and display them.
                        //    If we have a winner, display score and announce winner.
                        // else if both teams are out of bags, remind to press next inning.
                        if (noWinner) {
                            if (bagsTeamB > NO_BAGS) {
                                viewScoreTeamBInning.setText(scoreBag("teamB", 3));
                                viewBagsTeamB.setText(decrBag("teamB"));
                                viewStatusGame.setText(R.string.status_three_points);

                                if (scoreTeamA >= WINNING_SCORE) {
                                    noWinner = false;
                                    viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_a)));

                                } else if (scoreTeamB >= WINNING_SCORE) {
                                    noWinner = false;
                                    viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_b)));
                                }

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_plus1_b:
                        // If we have a no winner, and team still has bags, add 1 point to score
                        //    and decrement bag count and display them.
                        //    If we have a winner, display score and announce winner.
                        // else if both teams are out of bags, remind to press next inning.
                        if (noWinner) {
                            if (bagsTeamB > NO_BAGS) {
                                viewScoreTeamBInning.setText(scoreBag("teamB", 1));
                                viewBagsTeamB.setText(decrBag("teamB"));
                                viewStatusGame.setText(R.string.status_one_point);

                                if (scoreTeamA >= WINNING_SCORE) {
                                    noWinner = false;
                                    viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_a)));

                                } else if (scoreTeamB >= WINNING_SCORE) {
                                    noWinner = false;
                                    viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                    viewStatusGame.setText((getString(R.string.status_winner) + getString(R.string.name_team_b)));
                                }

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_zero_b:
                        // If we have a no winner, and team still has bags, decrement bag count and display.
                        // else if both teams are out of bags, remind to press next inning.
                        if (noWinner) {
                            if (bagsTeamB > NO_BAGS) {
                                viewBagsTeamB.setText(decrBag("teamB"));
                                viewStatusGame.setText(R.string.status_zero_points);

                            } else if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                viewStatusGame.setText(R.string.status_prompt_next_inning);
                            }
                        }
                        break;

                    case R.id.button_minus1_b:
                        // If we have a no winner, and team still has bags, subtract 1 point to score
                        //    and display.
                        // else if both teams are out of bags, remind to press next inning.
                        if (noWinner) {
                            if (scoreTeamBInning > 0) {
                                viewScoreTeamBInning.setText(scoreBag("teamB", -1));
                                viewStatusGame.setText(R.string.status_minus_point);

                            } else {
                                viewStatusGame.setText(R.string.status_zero_score);
                            }
                        }
                        break;

                    case R.id.button_reset:
                        // Reset variables and views.
                        scoreTeamA = 0;
                        scoreTeamB = 0;
                        bagsTeamA = 4;
                        bagsTeamB = 4;
                        scoreTeamAInning = 0;
                        scoreTeamBInning = 0;
                        currentInning = 1;
                        noWinner = true;
                        viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                        viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                        viewBagsTeamA.setText(String.valueOf(bagsTeamA));
                        viewBagsTeamB.setText(String.valueOf(bagsTeamB));
                        viewScoreTeamAInning.setText(String.valueOf(scoreTeamAInning));
                        viewScoreTeamBInning.setText(String.valueOf(scoreTeamBInning));
                        viewInningCurrent.setText(String.valueOf(currentInning));
                        viewStatusGame.setText(R.string.status_init);
                        break;

                    case R.id.button_next_inning:
                        // If we have no winner, and if both teams have no bags, reset bag counts,
                        //    inning scores, increment inning and update views.
                        // else if notify that there are still bags left to throw.
                        if (noWinner) {
                            if (bagsTeamA == NO_BAGS && bagsTeamB == NO_BAGS) {
                                bagsTeamA = 4;
                                bagsTeamB = 4;
                                scoreTeamAInning = 0;
                                scoreTeamBInning = 0;
                                currentInning += 1;
                                viewScoreTeamA.setText(String.valueOf(scoreTeamA));
                                viewScoreTeamB.setText(String.valueOf(scoreTeamB));
                                viewBagsTeamA.setText(String.valueOf(bagsTeamA));
                                viewBagsTeamB.setText(String.valueOf(bagsTeamB));
                                viewScoreTeamAInning.setText(String.valueOf(scoreTeamAInning));
                                viewScoreTeamBInning.setText(String.valueOf(scoreTeamBInning));
                                viewInningCurrent.setText(String.valueOf(currentInning));
                                viewStatusGame.setText((getString(R.string.status_start_inning) + String.valueOf(currentInning)));

                            } else {
                                viewStatusGame.setText((R.string.status_bags_remain));
                            }
                        }
                        break;
                }
            }
        };

        // Set all the OnClickListeners.
        buttonPlus3TeamA.setOnClickListener(mOnClickListener);
        buttonPlus1TeamA.setOnClickListener(mOnClickListener);
        buttonZeroTeamA.setOnClickListener(mOnClickListener);
        buttonMinus1TeamA.setOnClickListener(mOnClickListener);
        buttonPlus3TeamB.setOnClickListener(mOnClickListener);
        buttonPlus1TeamB.setOnClickListener(mOnClickListener);
        buttonZeroTeamB.setOnClickListener(mOnClickListener);
        buttonMinus1TeamB.setOnClickListener(mOnClickListener);
        buttonResetGame.setOnClickListener(mOnClickListener);
        buttonNextInning.setOnClickListener(mOnClickListener);
    }

    /**
     * Updates team's score with points and returns String of team's current inning score.
     *
     * @param team   - team that just threw a bag.
     * @param points - number of points of bag.
     * @return - String of team's inning score to display.
     **/
    private String scoreBag(String team, int points) {
        if (team.equals("teamA")) {
            scoreTeamAInning += points;
            scoreTeamA += points;
            return String.valueOf(scoreTeamAInning);

        } else {
            scoreTeamBInning += points;
            scoreTeamB += points;
            return String.valueOf(scoreTeamBInning);
        }
    }

    /**
     * Decrements team's bag count and returns String of team's current bag count.
     *
     * @param team - team that just threw a bag.
     * @return - String of team's bag count to display.
     **/
    private String decrBag(String team) {
        if (team.equals("teamA")) {
            bagsTeamA -= 1;
            return String.valueOf(bagsTeamA);

        } else {
            bagsTeamB -= 1;
            return String.valueOf(bagsTeamB);
        }
    }
}
