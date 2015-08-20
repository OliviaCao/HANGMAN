//Import libraries
import java.io.*;
//create class

public class Hangman 
{
	//number is the amount of shows in the array
	static int numberplayer=0;
	//for iteration
	static int i,j;
	static PrintWriter score;
	//multidimensional array
	static String player[] = new String[1000];
	static int playerscore[]= new int [1000];
	public static void main(String[] args) throws IOException 
	{
		BufferedReader in;
		in = new BufferedReader(new InputStreamReader(System.in));
		
		//menu will be shown until user chooses to exit the program		
		while(true)
		{
			System.out.println("Welcome to hangman choose an option (pick a number):\n1. Rules \n2. Play Game \n3. Search Player \n4. Sort Players \n5. Show All Scores  \n6. Delete Player \n7. Save Game \n8. Load Previous Results \n9. Modify \n10. Exit");
			int choice = Integer.parseInt(in.readLine());
			//switch used, a case for each option on the menu
			switch (choice) 
			 {
	         //add a show
			 case 1:  rules();
	         System.out.println("\n");
	                  break;
	         //modify tv show entered
			 case 2:  game();
	         System.out.println("\n");
	                  break;
	        //delete a show
			 case 3:  
				 in = new BufferedReader(new InputStreamReader(System.in));
					System.out.println("Name of player (input is case sensitive): ");
					String name = in.readLine();
				 searchnames(name);
	         		 System.out.println("\n");
	                  break;
	         //sort shows using binary sort
			 case 4:  
	        	 sort();
	     		System.out.println("\n");     
	        	 break;
	         //output shows and amount of shows per day
			 case 5:
	        	 show();
	         System.out.println("\n");
	                  break;
	         //saves show into a file
			 case 6:
	        	 delete();
	        	 break;
	         //loads show from a file
			 case 7:
	        	 save();
	        	 break;
			 case 8:
				 load("/Users/Olivia/Documents/test/TV.txt");
				 break;
	        //exit program
			 case 9:
	        	 modify();
			 case 10:
				 System.exit(0);
	        	//in case of invalid input
			 default: System.out.println("\nInvalid input ");
	         System.out.println("\n");
	                  break;
			 }	
		}
	}
		public static void rules() throws IOException 
		{
			
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("RULES \n This is a two player game. Player 1 will be the person writing a word to guess and player 2 will be the person guessing.\n When entering a word, make sure you press enter after every letter \n Player 1 will choose the amount of tries player 2 can have. \n The score will be kept in a score file and it can be searched and sorted with options on the menu");
			System.out.println("Press 0 to return to menu");
			int rulesexit = Integer.valueOf(in.readLine()).intValue();
			if(rulesexit==0)
			{
				return;	
			}
		}
		public static void game() throws IOException 
		{
			String player1, player2;
			int correct=0;
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(System.in));
			
			//names of players
			System.out.println("Enter name of player 1 (input is case sensitive):");
			player1 = in.readLine();
			System.out.println("Enter name of player 2 (input is case sensitive):");
			player2 = in.readLine();
			//input word being guessed
			System.out.println("\n"+ player1+" pick your word:");
			String input = in.readLine();
			//enter amount of tries
			System.out.println("Enter amount of tries "+ player2+ " can have");
			int tries = Integer.valueOf(in.readLine()).intValue();
			System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			System.out.println(player2 + ", you have " + tries + " tries. Guess a letter");
			
			playerscore[numberplayer]=0;
			player[numberplayer++] = player1;
			playerscore[numberplayer]=0;
			player[numberplayer++] = player2;
			
			char[] wordstore= input.toCharArray();
			int wordlen = input.length();
			String word[][]= new String[wordlen][2];
			//output for player 2 to show the amount of letters
			for(i=0; i<wordlen; i++)
			{
				word[i][0]= String.valueOf(wordstore[i]);
				word[i][1]="0";
				System.out.print("_");
			}
			for(i=0;i<tries&&(correct!=wordlen);i++)
			{
				System.out.print("\nEnter a letter");
				String guess = in.readLine();
				
				//linear search
				for(j=0;j<wordlen;j++)
				{
					if(word[j][0].compareTo(guess)==0)
					{
						word[j][1]="1";
						correct++;
					}
					if(word[j][1].compareTo("1")==0)
					{
						System.out.print(word[j][0]);
					}
					if((word[j][0].compareTo(guess)!=0)&&(word[j][1].compareTo("1")!=0))
					{
						System.out.print("_");
					}
				}
				System.out.print("\n");
				if(correct==wordlen)
				{
					System.out.print("You win!" + player2 +" gets one more point");
					playerscore[searchnames(player2)]++;
					
				}
			}
		}
		//prints out all players and their scores in the database
		public static void show() throws IOException 
		{
			for(i=0; i<numberplayer; i++)
			{
				System.out.println("Player: "+player[i]+ " Score: "+playerscore[i]);
			}
		}
		public static void sort() throws IOException
		{
			//selection sort
			int min, k, j;
			String temp;
			int temp1;
			for(k=0;k<(numberplayer-1);k++)
			{
				min=k;
				for(j=k+1;j<numberplayer;j++)
				{
					if (player[min].compareTo(player[j])>0)
					{
						min = j;
						
					}
				}
				temp = player[k];
				player[k] = player[min];
				player[min] = temp;
				
				temp1 = playerscore[k];
				playerscore[k] = playerscore[min];
				playerscore[min] = temp1;
				
			}
		}
		public static int searchnames(String playername) throws IOException
		{
			if(numberplayer==0)
			{
				return -1;
			}
			int high = (numberplayer-1);
			int low = 0;
			int middle;
			while(low<=high)
			{
				middle = low+ ((high-low)/2);
				if(player[middle].compareTo(playername)==0)
				{
					return middle;
				}
				if(player[middle].compareTo(playername)>0)
				{
					high = middle-1;
				}
				else
				{
					low = middle +1;
				}
			}
			return -1;
		}
		public static void modify() throws FileNotFoundException, IOException
		{
			int found = 0;
			BufferedReader in;
			in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Name of player (input is case sensitive): ");
			String name = in.readLine();
			for(i=0; i<numberplayer; i++)
			{
				//if show exists, enter the new data for the show
				if (name.compareTo(player[i])==0)
				{
					System.out.println("Enter new name: ");
					player[i]= in.readLine();
					System.out.println("Enter new score (integer): ");
					playerscore[i] = Integer.valueOf(in.readLine()).intValue();
					found = 1;
				}
			}
			//if player not found
			if(found==0)
			{
				System.out.println("Show not in directory");	
			}
				
		}
		//delete chosen name
		public static void delete() throws FileNotFoundException, IOException
		{
			int found = 0;
			BufferedReader in;
			int j;
			in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Name of player to be deleted: ");
			String name = in.readLine();
			//iterate through main array
			for(i=0; i<numberplayer; i++)
			{
				//if show is found, decrease 'number' of shows and delete that show
				if (name.compareTo(player[i])==0)
				{
					found = 1;
					for(j=i;j<(numberplayer-1);j++)
					{
						
						player[j]= player[j+1];
						playerscore[j]= playerscore[j+1];
					}
					numberplayer--;
				}
			}
			//if show not found
			if(found ==0)
			{
				System.out.println("Name not in directory");
			}
		}
		
		
		//save array into file
		public static void save() throws FileNotFoundException, IOException
		{
			//initiate savefile by creating a fail.txt file...the location of the file will vary, in my case it is "/Users/Olivia/Documents/test/fail.txt"
			score = new PrintWriter (new FileWriter("/Users/Olivia/Documents/test/score.txt"));
			//print number of shows
			score.println(numberplayer);
			//iterate trhoug array and print contents into the TV text file
			for(i=0; i<numberplayer; i++)
			{
				{
					score.println(player[i]);
					score.println(String.valueOf(playerscore[i]));
				}
			}
			score.close();
			
		}
		//load file information into array
		public static void load(String filename) throws FileNotFoundException, IOException
		{
			int readnumber;
			BufferedReader in;
			//read file
			in = new BufferedReader(new FileReader(filename));
			//read number of shows added to array
			readnumber=Integer.valueOf(in.readLine()).intValue();
			System.out.println("Loading "+readnumber+" players");
			
			//create array
			for(i=numberplayer;i<(numberplayer+readnumber);i++)
			{
				player[i]=in.readLine();
				playerscore[i]= Integer.valueOf(in.readLine());
			}
			//update total amount of shows
			numberplayer+=readnumber;
			in.close();
		}
		
}