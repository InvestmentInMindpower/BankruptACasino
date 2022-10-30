import java.util.Random;
import java.util.ArrayList;

public class Player {

    private String name;

    ArrayList<Boolean> WinsOnRound = new ArrayList<Boolean>();

    ArrayList<Integer> Investments = new ArrayList<Integer>();
    private int balance;

    private int roundNr = 0;

    public Player(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public void investMoney(int money)
    {
        balance = balance - money;
    }

    public void incrementRound()
    {
        roundNr++;
    }

    public void logWin(boolean didPlayerWin)
    {
        WinsOnRound.add(didPlayerWin);
    }

    public void logInvestment(int investment)
    {
        Investments.add(investment);
        System.out.println(investment);
    }

    public boolean getLastWinResult()
    {
        boolean WinResult = true;
        if(WinsOnRound.size() == 0 || WinsOnRound.size() == 1)
        {
            WinResult = true;
        }
        else
        {
            int index = roundNr-1;
            WinResult = WinsOnRound.get(index);
        }
        return WinResult;
    }

    public int getLastInvestment()
    {
        int investment = 0;
        if(Investments.size() == 0 || Investments.size() == 1)
        {
            investment = 10;
        }
        else
        {
            int index = roundNr-1;
            investment = Investments.get(index);
        }
        return investment;
    }
    public void winMoney(int money)
    {
        balance = balance + money;
    }

    public void displayBalance()
    {
        System.out.println(name + " now has a balance of " + balance + " USD\n");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkPlayerBankruptcy()
    {
        boolean check = false;
        if(balance < 0)
        {
            check = true;
        }
        return check;
    }

    public int getBalance() {
        return balance;
    }

    public ArrayList<Boolean> getWinsOnRound() {
        return WinsOnRound;
    }

    public ArrayList<Integer> getInvestments() {
        return Investments;
    }

}
