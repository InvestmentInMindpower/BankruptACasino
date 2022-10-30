import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Statistics {

    File balanceDataPlayer1;
    File balanceDataPlayer2;
    File investmentsDataPlayer1;
    File winResultDataPlayer1;
    File consecutiveLossesDataPlayer1;



    public Statistics() throws FileNotFoundException {
    }

    PrintStream balanceOut1 = new PrintStream(new File("player1BalanceData.txt"));
    PrintStream balanceOut2 = new PrintStream(new File("player2BalanceData.txt"));
    PrintStream investmentOut1 = new PrintStream(new File("investmentsDataPlayer1.txt"));
    PrintStream winOut1 = new PrintStream(new File("winResultDataPlayer1.txt"));
    PrintStream consecutiveLossesOut1 = new PrintStream(new File("consecutiveLossesDataPlayer1.txt"));

    public void saveBalance(Player player) throws FileNotFoundException {
        if(player.getName().equals("Randy Johnboy"))
        {
            int balance = player.getBalance();
            balanceOut1.println("Had balance of " + balance + "\n");
        }
        else
        {
            int balance = player.getBalance();
            balanceOut2.println("Had balance of " + balance + "\n");
        }
    }


    public void saveInvestments(int investment) throws FileNotFoundException {
        investmentOut1.println("invested " + investment + "\n");
    }


    public void saveWinResult(boolean winResult) throws FileNotFoundException {
        String message = "";
        if(winResult == true)
        {
            message = "Won";
        }
        else
        {
            message = "Lost";
        }

        winOut1.println(message);
    }

    public void saveConsecutiveLosses(Player player1) throws FileNotFoundException {
        int counterWins = 0;
        int counterLosses = 0;
        for(int i = 0; i < player1.getWinsOnRound().size(); i++)
        {
            if(i>1)
            {
                boolean tempBol = player1.getWinsOnRound().get(i-1);
                boolean currentBol = player1.getWinsOnRound().get(i);
                if(tempBol && currentBol)
                {
                    counterWins++;
                }
                else
                {
                    if(counterWins != 0)
                    {
                        consecutiveLossesOut1.println(counterWins + " Consecutive WINS");
                    }
                    counterWins = 0;
                }
                if(!tempBol && !currentBol)
                {
                    counterLosses++;
                }
                else
                {
                    if(counterLosses != 0)
                    {
                        consecutiveLossesOut1.println(counterLosses + " Consecutive LOSSES");
                    }
                    counterLosses = 0;
                }
            }
        }
    }
}
