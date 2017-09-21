package com.example.joey.pokedex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by swetha on 9/21/17.
 */

public class PokeProfile extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pokeprofile);
    }
}

/****
 timeText = (TextView)findViewById(R.id.timerTime);
 exitButton = (Button) findViewById(R.id.exitButton);

 random = new Random();

 memberPic = (ImageView) findViewById(R.id.memberPic);

 buttonAnswer1 = (Button) findViewById(R.id.choice1);
 buttonAnswer2 = (Button) findViewById(R.id.choice2);
 buttonAnswer3 = (Button) findViewById(R.id.choice3);
 buttonAnswer4 = (Button) findViewById(R.id.choice4);

 scoreChanger = (TextView) findViewById(R.id.scoreNum);
 updateScore(score);

 pairs = new ArrayList<>();
 ImgNameDatabase imgNameDatabase = new ImgNameDatabase();

 //adding image and name pairs to list
 for (int i = 0; i < imgNameDatabase.cNames.length; i++) {
 pairs.add(new ImgNamePairs(imgNameDatabase.cNames[i], imgNameDatabase.memberImages[i]));
 }

 //randomize the pairing order

 Collections.shuffle(pairs);

 createQuestion(turn);
 timer.start(); //THIS IS THE LINE WHERE EVERYTHING BREAKS FOR SOME REASON



 exitButton.setOnClickListener(new View.OnClickListener(){
 @Override
 public void onClick(View view) {

 AlertDialog.Builder builder2=new AlertDialog.Builder(GameScreen.this);
 builder2.setMessage("Are you sure you want to quit?");
 builder2.setPositiveButton("Yes",new DialogInterface.OnClickListener() {

 @Override
 public void onClick(DialogInterface dialog, int which) {
 Toast.makeText(getApplicationContext(), "Thanks for Playing!", Toast.LENGTH_LONG).show();
 Intent intentReturn = new Intent(getApplicationContext(), StartScreen.class);
 startActivity(intentReturn); //returns to the start screen
 }

 });

 builder2.setNegativeButton("Nope", new DialogInterface.OnClickListener() {

 @Override

 public void onClick(DialogInterface dialog, int which) {
 dialog.dismiss();
 finish();
 }

 });

 builder2.show();

 /*AlertDialog alertDialog = new AlertDialog.Builder(GameScreen.this).create();
 alertDialog.setTitle("Are you sure you want to quit?");
 alertDialog.setMessage("All progress will be lost.");
 alertDialog.setButton(1, "Yes!", new DialogInterface.OnClickListener()
 {
 @Override
 public void onClick(DialogInterface dialog, int id)
 {
 Toast.makeText(GameScreen.this, "Thank You!", Toast.LENGTH_SHORT).show();
 Intent intentReturn = new Intent(getApplicationContext(), StartScreen.class);
 startActivity(intentReturn); //returns to the start screen
 }
 });
 alertDialog.setButton(0, "Cancel", new DialogInterface.OnClickListener()
 {
 @Override
 public void onClick(DialogInterface dialog, int id)
 {
 dialog.dismiss();
 finish();//close the dialog window without doing anything
 }
 }
 );
 alertDialog.show();*/
            }
                    });


                    memberPic.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        timer.cancel(); //don't penalize the player for creating a contact
        //Creates a new Intent to insert a new contact
        Intent contactIntent = new Intent(ContactsContract.Intents.Insert.ACTION);
        contactIntent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
        // Gets the name of the currently displayed member
        String memName = pairs.get(turn - 1).getMemberName();
        // Executes the new contact creation
        contactIntent.putExtra(ContactsContract.Intents.Insert.NAME, memName);
        startActivityForResult(contactIntent, 1);
        }
        });

        buttonAnswer1.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        //check correctness of answer, first check wrong then right
        timer.cancel();
        if (!(buttonAnswer1.getText().toString().equalsIgnoreCase(pairs.get(turn - 1).getMemberName()))) {
        Toast.makeText(GameScreen.this, "Wrong Answer :(", Toast.LENGTH_SHORT).show();
        if (turn < pairs.size()) {
        turn ++;
        createQuestion(turn);
        timer.start();
        } else {
        Toast.makeText(GameScreen.this, "Congrats! You've Finished!", Toast.LENGTH_SHORT).show();
        }

        } else {

        score ++;
        updateScore(score);

        if (turn < pairs.size()) {
        turn ++;
        createQuestion(turn);
        timer.start();
        } else {
        Toast.makeText(GameScreen.this, "Congrats! You've Finished!", Toast.LENGTH_SHORT).show();

        }

        }
        }
        });

        buttonAnswer2.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        timer.cancel();
        //check correctness of answer, first check wrong then right
        if (!(buttonAnswer2.getText().toString().equalsIgnoreCase(pairs.get(turn - 1).getMemberName()))) {
        Toast.makeText(GameScreen.this, "Wrong Answer :(", Toast.LENGTH_SHORT).show();

        if (turn < pairs.size()) {
        turn ++;
        createQuestion(turn);
        timer.start();
        } else {
        Toast.makeText(GameScreen.this, "Congrats! You've Finished!", Toast.LENGTH_SHORT).show();
        }

        } else {

        score ++;
        updateScore(score);

        if (turn < pairs.size()) {
        turn ++;
        createQuestion(turn);
        timer.start();
        } else {
        Toast.makeText(GameScreen.this, "Congrats! You've Finished!", Toast.LENGTH_SHORT).show();
        }

        }
        }
        });

        buttonAnswer3.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        timer.cancel();
        //check correctness of answer, first check wrong then right
        if (!(buttonAnswer3.getText().toString().equalsIgnoreCase(pairs.get(turn - 1).getMemberName()))) {
        Toast.makeText(GameScreen.this, "Wrong Answer :(", Toast.LENGTH_SHORT).show();

        if (turn < pairs.size()) {
        turn ++;
        createQuestion(turn);
        timer.start();
        } else {
        Collections.shuffle(pairs);
        turn = 1;
        }

        } else {

        score ++;
        updateScore(score);

        if (turn < pairs.size()) {
        turn ++;
        createQuestion(turn);
        timer.start();
        } else {
        Collections.shuffle(pairs);
        turn = 1;
        }

        }
        }
        });

        buttonAnswer4.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
        //check correctness of answer, first check wrong then right
        if (!(buttonAnswer4.getText().toString().equalsIgnoreCase(pairs.get(turn - 1).getMemberName()))) {
        Toast.makeText(GameScreen.this, "Wrong Answer :(", Toast.LENGTH_SHORT).show();

        if (turn < pairs.size()) {
        turn ++;
        createQuestion(turn);
        timer.start();
        } else {
        Collections.shuffle(pairs);
        turn = 1;
        }

        } else {

        score ++;
        updateScore(score);

        if (turn < pairs.size()) {
        turn ++;
        createQuestion(turn);
        timer.start();
        } else {
        Collections.shuffle(pairs);
        turn = 1;
        }

        }
        }
        });

***/