package com.milenabromm.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * HighScore class, made to separate the code that writes the high score to file from the model
 * @author Milena Bromm 555851
 * @version 2.1
 */
public class HighScore {
	//HighScore Fields
	private String highScorePath;
	private String line;
	
	private FileWriter writer;
	private BufferedWriter bufferedWriter;
	
	private FileReader reader;
	private BufferedReader bufferedReader;
	
	/**
	 * HighScore Constructor, creates the "HighScore.txt" file if not already created
	 */
	public HighScore() {
		highScorePath = "src/com/milenabromm/exfiles/HighScore.txt";
		
		//to Ensure "HighScore.txt" is always created and not overwritten when creating it.
		try {
			writer = new FileWriter(highScorePath, true);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to write \"HighScore.txt\"");
		}//end tryCatch
		
	}//end constructor
		
	/**
	 * Copies any existing scores from the "HighScore.txt" and writes a new "HighScore.txt"<br>
	 * with the passed in name and score at the first line<br> 
	 * and the copied scores added after.
	 * @param name - String name to hold name of current high score player
	 * @param score - int score to hold the score of current high score player
	 */
	public void writeScore(String name, int score) {
		//copy will hold any pre-existing scores from the high score file
		String copy = "";
		//input takes the parameters to construct the first line of the file
		String input = "Name: " + name + " Score: " + score;
		int count = 0;
		
		try {	
			//Creates the reader to read and copy the file
			reader = new FileReader(highScorePath);
	        bufferedReader = new BufferedReader(reader);

	      //Reads in the document line by line and adds each to the copy unless the line is empty, 
	      //in which case the loop stops. If the document is empty no lines will be added to the copy
	        while ((line = bufferedReader.readLine()) != null) {
	        	count++;
			 	copy = copy + line + "\n";
			 	if (count >= 10) {
			 		break;
			 	}//end if
		 }//end while
		 
		 reader.close();
		
		 //Creates a new empty "HighScore.txt" file overwriting the existing one
		 writer = new FileWriter(highScorePath, false);
		 bufferedWriter = new BufferedWriter(writer);
		 
		 //Writes the new high score as the first line and then writes the copy to the file
            bufferedWriter.write(input);
            bufferedWriter.newLine();
            bufferedWriter.write(copy);
            bufferedWriter.close();
		}catch(IOException e) {
            e.printStackTrace();
            System.out.println("Failed when trying to write a new score to the high score file");
        }//end tryCatch
	}//end writeScore
	
	/**
	 * Gets the current highest score from "HighScore.txt" or returns 0 if the file is still empty
	 * @return highScore - the game high score
	 */
	public String getHighScore() {
		//Initialise highScore as 0, in case there are no pre-existing scores
		String highScore = "0";
		
		try {
		//Creates a reader	
		reader = new FileReader(highScorePath);
        bufferedReader = new BufferedReader(reader);
		
        //Takes the first line of the document takes the score value from if the first line is not null
        if ((line = bufferedReader.readLine()) != null){
         	highScore = line.substring(line.lastIndexOf(" ") + 1, line.length());
         }//end if 
         reader.close();
         
		} catch (IOException e ) {
			e.printStackTrace();
			System.out.println("Failed to extract score from high score file");
		}//end tryCatch
		
         return highScore;	
	}//end getHighScore
	
	/**
	 * Reads in and returns the first line of the document,<br>
	 * only used for debug purposes at the moment, might be configured for the high score menu option
	 * @return firstLine - first line of "HighScore.txt"
	 */
	public String firstLine() {
		//returns "empty document" if not overwritten
		String firstLine = "empty document";
		
		try {
		//Creates a new reader	
		reader = new FileReader(highScorePath);
        bufferedReader = new BufferedReader(reader);
		
        	//if first line is not null, the first line is saves in firstLine variable
			if((line = bufferedReader.readLine()) != null) {
				reader.close();
				firstLine = line;
			}//end if
			
			} catch (IOException e ) {
				e.printStackTrace();
			}//end tryCatch
		 
		 return firstLine;
	}//end firstLine
	
	/**
	 * gets top scores of the high score txt file
	 * @return highScores - top high scores
	 */
	public String getTopHighScores() {
		String highScores = "High Scores:\n", line;
		
		try {	
			reader = new FileReader(highScorePath);
	        bufferedReader = new BufferedReader(reader);

	        while ((line = bufferedReader.readLine()) != null) {
	        	highScores = highScores + line + "\n";
		 }//end while
		
		}catch(IOException e ) {
		e.printStackTrace();
		System.out.println("Failed to extract top scores from high score file");
		
		}//end tryCatch
	
		return highScores;

	}//end getTopHighScores
	
	/**
	 * Method only for testing purposes. Overwrites the high score text file 
	 * with an empty text file
	 */
	public void resetHighScore() {
		try {
			//Similar code to initialising the file, in the constructor but by 
			//setting the flag to false the file is overwritten with a new empty file
			writer = new FileWriter(highScorePath, false);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Failed to write \"HighScore.txt\"");
		}//end tryCatch
	}//end resetHighScore

}//end HighScore
