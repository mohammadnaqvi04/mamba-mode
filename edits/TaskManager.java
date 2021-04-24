package application;

import java.util.ArrayList;

/**
 * TaskManager - takes care of manipulating the data from the data given from the GUI input of the user and pushes the data to the Database
 * @author Edward P, Mohammad N, Aravind U
 *
 */
public class TaskManager {
	private Database db;

	public TaskManager(String dataBaseName) {
		db = new Database(dataBaseName);
	}

	/**
	 * gets the information from the GUI and adds the habit to the Database
	 */

	/**
	 * addFromGUIToDB - gets the information from the GUI and turns the information
	 * into the habit and pushes it to the database
	 * 
	 * @param habit - the string of the habit to be made
	 * @param days  - the boolean array representing the days of the week that the
	 *              habit is planned to be done on
	 * @param goals - an int array where the index represents the days and Habit
	 *               where 0 = missed, 1 = completed, 2 = still to come
	 */
	public void addFromGUIToDB(String habit, boolean[] days) {
		int[] defaultGoal = {0, 0 ,0 ,0 ,0 ,0 ,0};
		Habit h = new Habit(habit, daysToGoal(days), days, defaultGoal);
		db.addHabit(h);
	}

//--------------------------------DATABASE MANIPULATION METHODS--------------------------------------------------------------------
	/**
	 * int array where a[0] = days completed, days[1] = days missed, days[2] = days to occur
	 *
	 */
	public void updateWeeklyStats(Habit habit, int[] days) {
		db.updateWeeklyStat(habit, arrToString(days));
	}
	
	public void updateOverallStats(Habit habit, int[] days) {
		db.updateOverallStat(habit, arrToString(days));
	}
	
	/**
	 * int array where a[0] = days completed, days[1] = days missed, days[2] = days to occur
	 *
	 */
	public int[] getWeeklyHabitStats(Habit habit) {
		return db.getWeeklyStat(habit);
	}
	
	
	/**
	 * int array where a[0] = days completed, days[1] = days missed, days[2] = days to occur
	 *
	 */
	public int[] getOverallHabitStats(Habit habit) {
		return db.getOverallStat(habit);
	}

	public ArrayList<Habit> getHabits(){
		return db.getHabits();
	}
//-----------------------------------------HELPER METHODS-------------------------------------------------------------------

	/**
	 * arrToString - changes the integer arr into a string 
	 * @param arr - the int[] that you would like to convert into a string
	 * @return a String where the values of the arr are side by side.
	 */
	private String arrToString(int[] arr) {
		String s = "";
		for(int i : arr) {
			s += i + " ";
		}
		
		return s;
	}
	
	/**
	 * daysToGoals - takes in a boolean array and counts the number of true values
	 * in the boolean
	 * 
	 * @param days - the array of the days that a habit is planned to be done
	 * @return the number of days that the habit should be done per day.
	 */
	private int daysToGoal(boolean[] days) {
		int count = 0;

		for (boolean bool : days) {
			if (bool) {
				count++;
			}
		}
		
		return count;
	}
	
}