import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        Player test = new Player(0, "", 0, 0, 0, 0, 0,
            0, 0, 0, 0, 0, 0, 0,
            0);

        Information information = new Information("C:\\Users\\Jelle\\Desktop\\test.txt");
        ArrayList<String> data = Information.getInput();
        ArrayList<Player> playerList = new ArrayList<>();
        while(data.isEmpty() == false) {
            playerList.add(Information.convertInformation(data.get(0)));
            data.remove(0);
        }
        while(playerList.isEmpty() == false) {
            System.out.println(Information.printPlayer(playerList.get(0)));
            playerList.remove(0);
        }
    }
}