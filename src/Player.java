import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.sql.Array;
import java.util.*;

public class Player {
    private int id;
    // Personal information
    private String name;
    private int gender;
    // Part One -> Speed/Athletics
    private double tenYard;
    private double fourtyYard;
    private double verticalJump;
    private double horizontalJump;
    // Part Two Agility/Hand-Eye-Coordination
    private double threePointJump;
    private double lCone;
    private double threeCone;
    private double twoCone;
    private double tTest;
    // Part Three Routs/Cuts/Speed/Hand-Eye-Coordination
    private double routRunning;
    private double overShoulderCatchStanding;
    private double overShoulderCatchRunning;
    private double score;

    private static final int amountSortableAttributes = 9;
    private static final int amountUnsortableAttributes = 3;



    public Player(int id, String name, int gender, double tenYard, double fourtyYard, double verticalJump,
                  double horizontalJump, double threePointJump, double lCone, double threeCone, double twoCone,
                  double tTest, double routRunning, double overShoulderCatchStanding, double overShoulderCatchRunning,
                  double score){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.tenYard = tenYard;
        this.fourtyYard = fourtyYard;
        this.verticalJump = verticalJump;
        this.horizontalJump = horizontalJump;
        this.threePointJump = threePointJump;
        this.lCone = lCone;
        this.threeCone = threeCone;
        this.twoCone = twoCone;
        this.tTest = tTest;
        this.routRunning = routRunning;
        this.overShoulderCatchStanding = overShoulderCatchStanding;
        this.overShoulderCatchRunning = overShoulderCatchRunning;
        this.score = score;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        id = this.id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        name = this. name;
    }
    public String getGender() {
        if(gender == 0) {
            return "female";
        }
        return "male";
    }
    public void setGender(int gender) {
        gender = this.gender;
    }

    public double getTenYard() {
        return tenYard;
    }

    public void setTenYard(double tenYard) {
        this.tenYard = tenYard;
    }

    public double getFourtyYard() {
        return fourtyYard;
    }

    public void setFourtyYard(double fourtyYard) {
        this.fourtyYard = fourtyYard;
    }

    public double getVerticalJump() {
        return verticalJump;
    }
    public void setVerticalJump(double verticalJump) {
        this.verticalJump = verticalJump;
    }

    public double getHorizontalJump() {
        return horizontalJump;
    }

    public void setHorizontalJump(double horizontalJump) {
        this.horizontalJump = horizontalJump;
    }

    public double getThreePointJump() {
        return threePointJump;
    }

    public void setThreePointJump(double threePointJump) {
        this.threePointJump = threePointJump;
    }

    public double getlCone() {
        return lCone;
    }

    public void setlCone(double lCone) {
        this.lCone = lCone;
    }

    public double getThreeCone() {
        return threeCone;
    }

    public void setThreeCone(double threeCone) {
        this.threeCone = threeCone;
    }

    public double getTwoCone() {
        return twoCone;
    }

    public void setTwoCone(double twoCone) {
        this.twoCone = twoCone;
    }

    public double gettTest() {
        return tTest;
    }

    public void settTest(double tTest) {
        this.tTest = tTest;
    }

    public double getRoutRunning() {
        return routRunning;
    }

    public void setRoutRunning(double routRunning) {
        this.routRunning = routRunning;
    }

    public double getOverShoulderCatchStanding() {
        return overShoulderCatchStanding;
    }

    public void setOverShoulderCatchStanding(double overShoulderCatchStanding) {
        this.overShoulderCatchStanding = overShoulderCatchStanding;
    }

    public double getOverShoulderCatchRunning() {
        return overShoulderCatchRunning;
    }

    public void setOverShoulderCatchRunning(double overShoulderCatchRunning) {
        this.overShoulderCatchRunning = overShoulderCatchRunning;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public static ArrayList<Player> splitGender(ArrayList<Player> players, String gender) {
        ArrayList<Player> split = new ArrayList<Player>();
        for (Player playerGender : players) {
            if(playerGender.getGender() == gender) {
                split.add(playerGender);
            }
        }
        return split;
    }

    public static ArrayList<Player> rankPlayer(ArrayList<Player> playerList) {
        // Size of a group
        int range = (int)Math.round(Math.sqrt(playerList.size()));
        // Amount of groups/max reachable points
        int size = (int)Math.ceil(Math.sqrt(playerList.size()));
        playerList = sortPlayer(playerList, range, size);
        playerList = addUnsortableAttributes(playerList);
        playerList.sort(Comparator.comparingDouble(p -> p.getScore()));
        Collections.reverse(playerList);

        return playerList;
    }

    private static ArrayList<Player> calculateScore(ArrayList<Player> playerUnranked, int range, int size,
                                                    ArrayList<Integer> marioRule) {
        ArrayList<Player> playerRanked = new ArrayList<Player>();
        int counter = 0;
        int streak = 0;
        while(!playerUnranked.isEmpty()) {
            if(playerUnranked.size() % range == 0) {
                size -=1;
                if(!marioRule.isEmpty() && marioRule.get(0) == counter) {
                    streak += 1;
                }
            }
            playerUnranked.get(0).setScore(playerUnranked.get(0).getScore() + size);
            if(!marioRule.isEmpty()) {
                if(marioRule.get(0) == counter) {
                    playerUnranked.get(0).setScore(playerUnranked.get(0).getScore() + streak);
                    marioRule.remove(0);
                } else {
                    streak = 0;
                }
            }
            playerRanked.add(playerUnranked.get(0));
            playerUnranked.remove(0);
            counter += 1;
        }
        return playerRanked;
    }

    private static ArrayList<Integer> determineDuplicate(ArrayList<Player> playerList, int attributeID){
        Map<Double, Integer> firstAppearance = new HashMap<>();
        ArrayList<Integer> duplicate = new ArrayList<Integer>();
        double storage = -1;

        for (int i = 0; i < playerList.size(); i++) {
            switch (attributeID) {
                case 0:
                    storage = playerList.get(i).getTenYard();
                    break;
                case 1:
                    storage = playerList.get(i).getFourtyYard();
                    break;
                case 2:
                    storage = playerList.get(i).getVerticalJump();
                    break;
                case 3:
                    storage = playerList.get(i).getHorizontalJump();
                    break;
                case 4:
                    storage = playerList.get(i).getThreePointJump();
                    break;
                case 5:
                    storage = playerList.get(i).getlCone();
                    break;
                case 6:
                    storage = playerList.get(i).getThreeCone();
                    break;
                case 7:
                    storage = playerList.get(i).getTwoCone();
                    break;
                case 8:
                    storage = playerList.get(i).gettTest();
                    break;
                default:
                    System.out.println("Error attribute is not valid!");
            }
            if(firstAppearance.containsKey(storage)) {
                duplicate.add(i);
            } else {
                firstAppearance.put(storage, i);
            }
        }
        return duplicate;
    }

    private static ArrayList<Player> sortPlayer(ArrayList<Player> playerList, int range, int size) {
        ArrayList<Integer> marioRule = new ArrayList<>();
        for(int i = 0; i < amountSortableAttributes; i++) {
            switch(i) {
                case 0:
                    playerList.sort(Comparator.comparingDouble(p -> p.getTenYard()));
                    break;
                case 1:
                    playerList.sort(Comparator.comparingDouble(p -> p.getFourtyYard()));
                    break;
                case 2:
                    playerList.sort(Comparator.comparingDouble(p -> p.getVerticalJump()));
                    Collections.reverse(playerList);
                    break;
                case 3:
                    playerList.sort(Comparator.comparingDouble(p -> p.getHorizontalJump()));
                    Collections.reverse(playerList);
                    break;
                case 4:
                    playerList.sort(Comparator.comparingDouble(p -> p.getThreePointJump()));
                    Collections.reverse(playerList);
                    break;
                case 5:
                    playerList.sort(Comparator.comparingDouble(p -> p.getlCone()));
                    break;
                case 6:
                    playerList.sort(Comparator.comparingDouble(p -> p.getThreeCone()));
                    break;
                case 7:
                    playerList.sort(Comparator.comparingDouble(p -> p.getTwoCone()));
                    break;
                case 8:
                    playerList.sort(Comparator.comparingDouble(p -> p.gettTest()));
                    break;
                default:
                    System.out.println("Error attribute is not valid!");
            }
            marioRule = determineDuplicate(playerList, i);
            playerList = calculateScore(playerList, range, size, marioRule);

        }
        return playerList;
    }

    private static ArrayList<Player> addUnsortableAttributes(ArrayList<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).setScore(playerList.get(i).getScore() + playerList.get(i).getRoutRunning());
            playerList.get(i).setScore(playerList.get(i).getScore() + playerList.get(i).getOverShoulderCatchStanding());
            playerList.get(i).setScore(playerList.get(i).getScore() + playerList.get(i).getOverShoulderCatchRunning());
        }
        return playerList;
    }

    public static void randomPlayer(int amount) throws IOException {
        File randomPlayerText = new File("C:\\Users\\Jelle\\Desktop\\randomPlayer.txt");
        PrintWriter writer = new PrintWriter(randomPlayerText, "UTF-8");
        double[] randomArray = new double[amountSortableAttributes + amountUnsortableAttributes + 1];
        double random;
        String randomName="";
        Random randomString = new Random();
        int leftLimit = 97;
        int rightLimit = 122;
        for(int i = 0; i < amount; i++) {
            for(int j = 0; j < randomArray.length; j++) {
                randomName = randomString.ints(leftLimit, rightLimit + 1)
                        .limit(7)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();
                switch (j) {
                    case 0: // Gender
                        random = Math.random() * 5;
                        break;
                    case 10:
                    case 11:
                    case 12:
                        random = Math.random() * 4;
                        break;
                    default:
                        random = Math.random() * 16;

                }
                randomArray[j] = (Math.round(random * 100.0))/100.0;
            }
            writer.print(i+","+randomName);
            for (int k = 0; k < randomArray.length; k++) {
                if(k == 0 || (k <= 12 && k >= 10)) {
                    writer.print("," + (int)randomArray[k]);
                } else {
                    writer.print("," + randomArray[k]);
                }
            }
            writer.print(",0");
            writer.println();
        }
        writer.close();
    }

}
