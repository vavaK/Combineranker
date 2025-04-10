import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Player test = new Player(0, "", 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0,
            0, 0);

        //Information information = new Information("C:\\Users\\Jelle\\Desktop\\test.txt");
        Player.randomPlayer(21);
        Information information = new Information("C:\\Users\\Jelle\\Desktop\\randomPlayer.txt");
        ArrayList<String> data = Information.getInput();
        ArrayList<Player> playerList = new ArrayList<>();
        ArrayList<Player> storagePlayerList = new ArrayList<>();
        ArrayList<Player> playerListMale = new ArrayList<>();
        ArrayList<Player> playerListFemale = new ArrayList<>();
        while(data.isEmpty() == false) {
            playerList.add(Information.convertInformation(data.get(0)));
            storagePlayerList.add(Information.convertInformation(data.get(0)));
            data.remove(0);
        }
        ;

        playerListMale = Player.splitGender(playerList, "male");
        playerListFemale = Player.splitGender(playerList, "female");

        playerListMale = Player.rankPlayer(playerListMale);
        playerListFemale = Player.rankPlayer(playerListFemale);

        playerList = playerListFemale;
        playerList.addAll(playerListMale);

        while(!playerList.isEmpty()) {
            System.out.println(Information.printPlayer(playerList.get(0),
                    storagePlayerList.get(playerList.get(0).getId())));
            playerList.remove(0);
        }
    }
}