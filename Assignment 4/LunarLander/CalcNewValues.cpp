#include <iostream>
#include <string>
#include <algorithm>
#include <cctype>

using std::cout;
using std::cin;
using std::endl;
using std::string;
using std::cerr;

//this function is used to check if passed by reference is a number. If it is, it will return true. Otherwise, false.
bool is_number(const std::string& s)
{
	return !s.empty() && std::find_if(s.begin(),
		s.end(), [](char c) { return !std::isdigit(c); }) == s.end();
}

int main()
{

	//Initialize Values - Java Code:
	/*
	final int GRAVITY = 2;
	int altitude = 1000;
	int fuel = 500;
	int velocity = 70;
	int time = 0;
	*/

	//Initialize Values - C++ Code:
	const int GRAVITY = 2;
	
	int altitude = 1000;
	int fuel = 500;
	int velocity = 70;
	int time = 0;

	//CONST MODIFIERS - C++ Code:
	const int MULT_TEN = 10;
	const int MULT_TWO = 2;
	const int SLOW_DOWN = 5;
	const int GROUND = 0;
	const int EXIT_FLAG = -1;
	const int START_POS = 0;
	const int FINAL_POS = 1;
	const string HASH_SYMBOL = "#";
	const string PCT_SYMBOL = "%";

	
	//inputReader - Java Code
	/*
	BufferedReader inputReader = new
	BufferedReader(new InputStreamReader(System.in));
	*/

	// inputReader - C++ Code Not Necessary, see line #69 for inputReader equivalent

	//Print initial values - Java Code:
	/*
	System.out.println("%a" + altitude);
	System.out.println("%f" + fuel);
	System.out.println("%v" + velocity);
	System.out.println("%t" + time);
	*/

	//Print initial values - C++ Code:
	cout << "%a" << altitude << endl;
	cout << "%f" << fuel << endl;
	cout << "%v" << velocity << endl;
	cout << "%t" << time << endl;

	//String Declaration - Java Code:
	/*
	String inputLine = null;
	*/

	//String Declaration - C++ Code:
	string inputLine = "";
	//making sure inputLine has nothing in it, just incase
	inputLine.clear();

	//will need this to test for exceptions
	bool isANumber;

	try
	{
		do
		{
			//refer to previous comment, this is BufferedReader equivalent in C++
			getline(cin, inputLine);

			//C++ cannot compare to NULL, so check if empty and use .length method
			if ((!inputLine.empty()) && (inputLine.length() > START_POS))
			{
				//C++ doesn't have startsWidth method, so we use substring instead
				if (inputLine.substr(START_POS, FINAL_POS) == HASH_SYMBOL)
				{
					/* Print statement - Java Code: 
					 * System.out.println(inputLine);
					*/
					cout << inputLine << endl;
				}
				//C++ doesn't have startsWidth method, so we use substring instead
				else if (inputLine.substr(START_POS, FINAL_POS) == PCT_SYMBOL)
				{
					/* Print statement - Java Code:
					 * System.out.println(inputLine);
					*/
					cout << inputLine << endl;

					//C++ doesn't have parseInt method, so we use stoi and substring to get equivalent
					int burnRate = std::stoi(inputLine.substr(FINAL_POS));

					//is_number should return true
					isANumber = is_number(inputLine.substr(FINAL_POS));

					//if it doesn't return true, throw an exception - C++ Code
					if (!isANumber)
					{
						if (burnRate <= EXIT_FLAG)
						{
							throw "Exit Successful! Thank you for playing!";
						}

						else
						{
							//Note, Java's parseInt will automatically throw an implementation. This is a work-around in C++.
							throw "Not a number!";
						}
					}

					else
					{
						if (altitude <= GROUND)
						{
							/* Print Statement - Java Code:
							 * System.out.println("#The game is over.");
							*/
							cout << "#The game is over." << endl;
						}
						else if (burnRate > fuel)
						{
							/* Print Statemtn - Java Code:
							 * System.out.println("#Sorry, you don't have that much fuel.");
							*/
							cout << "#Sorry, you don't have that much fuel." << endl;
						}
						else
						{
							//Calculate new application state - Universal in Java & C++
							time++;
							altitude = altitude - velocity;
							velocity = ((velocity + GRAVITY) * MULT_TEN - burnRate * MULT_TWO) / MULT_TEN;
							fuel = fuel - burnRate;
							if (altitude <= GROUND)
							{
								altitude = GROUND;
								if (velocity <= SLOW_DOWN)
								{
									/* Print Statement - Java Code:
									* System.out.println("#You have landed safely.");
									*/
									cout << "#You have landed safely." << endl;
								}
								else
								{
									/* Print Statement - Java Code:
									* System.out.println("#The game is over.");
									*/
									cout << "#You have crashed." << endl;
								}
							}
						}
					}

					//Print new values - Java Code:
					/*
					System.out.println("%a" + altitude);
					System.out.println("%f" + fuel);
					System.out.println("%v" + velocity);
					System.out.println("%t" + time);
					*/

					//Print new values - C++ Code:
					cout << "%a" << altitude << endl;
					cout << "%f" << fuel << endl;
					cout << "%v" << velocity << endl;
					cout << "%t" << time << endl;
				}
			}
		} while ((!inputLine.empty()) && (altitude > GROUND));
	}
	//parseInt in Java automatically thows an exception, we have to manually create one in C++
	catch (const char* msg)
	{
		cerr << msg << endl;
	}

	return 0;
}