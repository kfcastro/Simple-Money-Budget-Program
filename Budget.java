import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
* The Daily Money Budget program is a hard code program that
* simply solve for your Total Budget Left,
* given the income and expense amount. 
*
* @author  Karmela F. Castro
* @version 1.3
* @since   2021-01-10 
*/


public class Budget {
	/**
	 * This Method is used to solved your remaining budget
	 * for the day.
	 */
		
	//Initializing Fields
	private	static int balanceUpdate = 0;
	private static int expenseUpdate = 0;
	private static int incomeTotal = 0;
	
	//Local Date Time
	private static LocalDateTime dateInfo = LocalDateTime.now();
	private static DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy,HH:mm");
	private static String formattedDate = dateInfo.format(dateFormat);
	private static String[] dateSplit = formattedDate.split(",");
	private static String date = dateSplit[0];
	private static String time = dateSplit[1];
	
	//Empty List for budget information tracker
	private static List<String> budgetList = new ArrayList<String>();
	private static Object[] convertToArray;
	
	/*Category Method that takes the parameter of:
	 * a String category Information : Income or Expense
	 * a String expense Type : Food, Transportation, Clothes, etc.
	 * and an amount of either Income or Expense category
	 */
	public static void category(String categoryInfo, String expenseType, int amount) {
		
		if (categoryInfo == "Income" ) {
			
			incomeTotal += amount;
			balanceUpdate += amount;
			
			try {
				//add date, time, and amount to Budget List
				budgetList.add(date + " " + time + " Income " + String.valueOf(amount));
								 
				//Open and Write in text file "BudgetData.txt"
				FileWriter writeInFile = new FileWriter("BudgetData.txt", true);
			    BufferedWriter buffWrite = new BufferedWriter(writeInFile);
			    buffWrite.write(date + " " + time + " Income ");
			    buffWrite.newLine();
			    buffWrite.write(String.valueOf(amount));
			    buffWrite.newLine();
			    buffWrite.close();
			    			      
			} catch (IOException e) { //throw error
			    	
			      System.out.println("[@income] An error occurred.\n");
			      e.printStackTrace();
			      
			}
						 
		}else if (categoryInfo == "Expense"){
			
			expenseUpdate += amount;
			balanceUpdate = balanceUpdate - amount;
			
			try {
				//add date, time, expense type, and amount to Budget List
				budgetList.add(date + " " + time + " Expense " + expenseType + " " + String.valueOf(amount)); 
				
				//Open and Write in text file "BudgetData.txt"
			    FileWriter writeInFile = new FileWriter("BudgetData.txt", true);
			    BufferedWriter buffWrite = new BufferedWriter(writeInFile);
			    buffWrite.write(date + " " + time + " Expense " + expenseType);
			    buffWrite.newLine();
			    buffWrite.write(String.valueOf(amount));
			    buffWrite.newLine();
			    buffWrite.close();
			     
			} catch (IOException e) {
			    	
			      System.out.println("[@expense] An error occurred.\n");
			      e.printStackTrace();
			      
			}
			
		}
		
	}
	
	//A method that prints all the known data
	public static void daily() {
				
		convertToArray = budgetList.toArray();
			
		for(Object content: convertToArray) {
			
			System.out.println(content);
			
		}
		
		System.out.println("\n----------------------------------\n");
		System.out.println("\t UPDATED BALANCE\n");
		System.out.println("\nDate :  " + date + " " + time + "\n");
		System.out.println("----------------------------------\n");
		
		System.out.println("Total Income :  " + incomeTotal + "\n");
		System.out.println("Total Expense :  " + expenseUpdate + "\n");
		System.out.println("Total Money Left (Income - Expense) :  " + balanceUpdate + "\n");
		
	}
		
	
	 /**
	   * This is the main method which makes use of category and daily method.
	   * @param args Unused.
	   */

	public static void main(String[] args) {
	
		//Create Folder and Text File
		//-----------------------------------------------
		try {
			
			 File budgetData = new File("C:\\Users\\Nene\\eclipse-workspace\\BudgetData.txt");
		     
			 if (budgetData.createNewFile()) {
		     		
				 System.out.println("\nFile created: " + budgetData.getName() + "\n");
		     		
			 } else {
		    	  
				 System.out.println("\nFile already exists.\n");
		        
		     }
			 
		} catch (IOException e) {
			
		    	System.out.println("An error occurred.\n");
		    	e.printStackTrace();
		    	
		}
		//-----------------------------------
		
		
		//-----------Main Menu---------------
		
		System.out.print("\t Daily Money Budget\n\n");
		System.out.print("\tExpense Category");
		System.out.print("\n----------------------------------\n\n"
				+ "Food | Transportation | Clothes | Education\n"
				+ "Beauty | Health | Others\n\n");
		
			
		String categIncome = "Income";
		String categExpense = "Expense";
		
		try {
			//Given inputs
			category(categIncome, null, 2_000);
			category(categExpense, "Food", 550);
			category(categExpense, "Clothes", 600);
			category(categExpense, "Transportation", 250);
			category(categExpense, "Food", 20);
			category(categIncome, null, 500);
			category(categExpense, "Education", 90);
			category(categExpense, "Beauty", 150);
			category(categExpense, "Health", 430);
			category(categExpense, "Others", 10);
			category(categExpense, "Transportation", 50);
			category(categExpense, "Food", 70);
			
			daily();
			
		}catch(Exception e) {
			
			System.out.print("\nFailed 12 test cases");
			e.printStackTrace();
			
		}
				
	}
		
}