import java.io.FileNotFoundException;
import java.sql.SQLOutput;
import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class Casino {

    private int holdings;
    Player player1;
    Player player2;
    boolean gameOver;
    Random random;
    Scanner scanner;

    Player[] players = new Player[2];

    public Casino(int holdings) throws FileNotFoundException {
        this.holdings = holdings;
        this.player1 = new Player("Randy Johnboy",100000);
        this.player2 = new Player("Elon Musky", 100000);
        this.random = new Random();
        this.scanner = new Scanner(System.in);
        this.gameOver = false;
        players[0] = player1;
        players[1] = player2;
    }

    Statistics statistics = new Statistics();

    public void looseToPlayer(int money) {
        holdings = holdings - money;
    }

    public void goBankrupt()
    {
        System.out.println("God damn you players, you have ruined us!");
    }

    public int rollRoulette()
    {
        int randomOutcome = random.nextInt(1, 3);
        return randomOutcome;
    }


    public boolean playRound(int investment, int betChoice, Player player, int randomOutcome)
    {
        boolean wonRound = false;
        player.investMoney(investment);
        if (randomOutcome % 2 > 0) {
            if (betChoice == 1) {
                System.out.println("Unequal numbers wins!");
                player.winMoney(investment * 2);
                System.out.println("Player " + player.getName() + " has won " + investment + " USD");
                player.displayBalance();
                looseToPlayer(investment);
                wonRound = true;
            }
        } else if (randomOutcome % 2 == 0) {
            if (betChoice == 2) {
                System.out.println("Equal numbers wins!");
                player.winMoney(investment * 2);
                System.out.println("Player " + player.getName() + " has won " + investment + " USD");
                player.displayBalance();
                looseToPlayer(investment);
                wonRound = true;
            }
        }
        return wonRound;
    }

    public boolean checkBankruptcy()
    {
        boolean check = false;
        if(holdings < 0)
        {
            goBankrupt();
            check = true;
        }
        return check;
    }


    public void playRoulette() throws FileNotFoundException {
        outerloop:
        while(!gameOver)
        {
            int[] investments = new int[2];
            int[] choices = new int [2];
            int result  = rollRoulette();
            for(Player player : players)
            {
                System.out.println("[Player" + player.getName() + "s turn]...");
                if (!player.checkPlayerBankruptcy())
                {
                    System.out.println("What amount would you like to invest " + player.getName() + "?");
                    System.out.println("You can max invest " + player.getBalance() + " USD");
                    if(Arrays.asList(players).indexOf(player) == 0) {
                        if (player.getLastWinResult() == true) {
                            int investment = 10;
                            investments[Arrays.asList(players).indexOf(player)] = investment;
                            player.logInvestment(investment);
                            statistics.saveInvestments(investment);
                        } else
                        {
                            int investment = player.getLastInvestment() * 3;
                            investments[Arrays.asList(players).indexOf(player)] = investment;
                            player.logInvestment(investment);
                            statistics.saveInvestments(investment);
                        }
                    }
                    else
                    {
                        int investment = 100;
                        investments[Arrays.asList(players).indexOf(player)] = investment;
                    }
                    System.out.println("Choose whether you would like to bet on unequal numbers [1] or equal numbers [2]");
                    int choice = random.nextInt(1,3);
                    System.out.println(choice);
                    choices[Arrays.asList(players).indexOf(player)] = choice;
                }
                else if (player.checkPlayerBankruptcy())
                {
                    System.out.println("You´re bankrupt " + player.getName());
                    statistics.saveConsecutiveLosses(player1);
                    gameOver = true;
                    break outerloop;
                }
            }
            System.out.println("\n[PLAYING ROULETTE...]");
            System.out.println("=======================================================");
            System.out.println("The roulette has rolled: " + result + "\n");
            for(Player player : players)
            {
                player.incrementRound();
                boolean wonRound = playRound(investments[Arrays.asList(players).indexOf(player)], choices[Arrays.asList(players).indexOf(player)], player, result);
                if(wonRound)
                {
                    player.logWin(true);
                    statistics.saveWinResult(true);
                    statistics.saveBalance(player);
                }
                else
                {
                    player.logWin(false);
                    statistics.saveWinResult(false);
                    statistics.saveBalance(player);
                }
            }
            if(checkBankruptcy())
            {
                statistics.saveConsecutiveLosses(player1);
                gameOver = true;
            }
            System.out.println("=======================================================");
        }
    }


}
