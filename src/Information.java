import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Information {
    private static ArrayList<String> input;
    public Information(String path) throws IOException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        input = new ArrayList<String>();
        while(scanner.hasNextLine()) {
            input.add(scanner.nextLine());
        }
    }

    public static ArrayList<String> getInput(){
        return input;
    }

    public static Player convertInformation(String data) {
        String[] dataArray = convertDataToArray(data);
        Double[] dataNumber = new Double[15];
        String name = dataArray[1];
        if (dataArray.length == 16) {
            for(int i = 0; i < 15; i++) {
                if (i == 1) i += 1;
                dataNumber[i]= arrayToDouble(dataArray[i]);
            }
            Player player = new Player((int)Math.round(dataNumber[0]), name, (int)Math.round(dataNumber[2]), dataNumber[3],
            dataNumber[4], dataNumber[5], dataNumber[6], dataNumber[7], dataNumber[8], dataNumber[9], dataNumber[10],
            dataNumber[11], dataNumber[12], dataNumber[13], dataNumber[14], 0);
            return player;
        }
        return new Player(-1, "Fehler", -1, -1, -1, -1, -1,
                -1, -1, -1, -1, -1, -1, -1,
                -1, -1);
    }

    private static String[] convertDataToArray(String data){
        String regex = ",";
        String[] dataArray = data.split(regex);
        return dataArray;
    }

    private static double arrayToDouble(String data) {
        return Double.parseDouble(data);
    }

    public static String printPlayer(Player player) {
        String output = "";
        output += player.getId();
        output += (", " + player.getName());
        output += (", " + player.getGender());
        output += (", " + player.getTenYard());
        output += (", " + player.getFourtyYard());
        output += (", " + player.getVerticalJump());
        output += (", " + player.getHorizontalJump());
        output += (", " + player.getThreePointJump());
        output += (", " + player.getlCone());
        output += (", " + player.getThreeCone());
        output += (", " + player.getTwoCone());
        output += (", " + player.gettTest());
        output += (", " + player.getRoutRunning());
        output += (", " + player.getOverShoulderCatchStanding());
        output += (", " + player.getOverShoulderCatchRunning());
        output += (", " + player.getScore());
        return output;
    }
}
