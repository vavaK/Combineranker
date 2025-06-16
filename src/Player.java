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
    //private double horizontalJump;
    // Part Two Agility/Hand-Eye-Coordination
    private double threePointJump;
    private double lCone;
    private double threeCone;
    private double twoCone;
    private double tTest;
    // Part Three Routs/Cuts/Speed/Hand-Eye-Coordination
    private double routRunning;
    //private double overShoulderCatchStanding;
    //private double overShoulderCatchRunning;
    private double score;

    private static final int amountSortableAttributes = 8;
    private static final int amountUnsortableAttributes = 1;

    private static final double bonus = 0.5;



    public Player(int id, String name, int gender, double tenYard, double fourtyYard, double verticalJump,
                  double threePointJump, double lCone, double threeCone, double twoCone, double tTest,
                  double routRunning, double score){
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.tenYard = tenYard;
        this.fourtyYard = fourtyYard;
        this.verticalJump = verticalJump;
        this.threePointJump = threePointJump;
        this.lCone = lCone;
        this.threeCone = threeCone;
        this.twoCone = twoCone;
        this.tTest = tTest;
        this.routRunning = routRunning;
        //this.overShoulderCatchStanding = overShoulderCatchStanding;
        //this.overShoulderCatchRunning = overShoulderCatchRunning;
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

    //public double getHorizontalJump() {
        //return horizontalJump;
    //}

    //public void setHorizontalJump(double horizontalJump) {
        //this.horizontalJump = horizontalJump;
    //}

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

    /**
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
     */

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

    /**
     * Creates the calculated and sorted order of all Payer
     *
     * @param playerList List of all player
     *
     * @return List of all player
     */
    public static ArrayList<Player> rankPlayer(ArrayList<Player> playerList) {
        // Size of a group
        int range = (int)Math.round(Math.sqrt(playerList.size()));
        System.out.println("Die Gruppengröße ist: " + range);
        // Amount of groups/max reachable points
        int size = (int)Math.ceil(Math.sqrt(playerList.size()));
        System.out.println("Die maximale Punktanzahl ist: " + size);
        playerList = sortPlayer(playerList, range, size);
        playerList = addUnsortableAttributes(playerList);
        playerList.sort(Comparator.comparingDouble(p -> p.getScore()));
        Collections.reverse(playerList);

        return playerList;
    }

    /**
     * Calculates the score of the attribute of the player
     *
     * @param playerUnranked List of all unranked player
     * @param range Size of a group
     * @param size Amount of points that every player in one group gets
     * @param marioRule If two or more player have the same time they all get the same points
     * @return List of all ranked player
     */
    private static ArrayList<Player> calculateScore(ArrayList<Player> playerUnranked, int range, int size,
                                                    ArrayList<Integer> marioRule) {
        ArrayList<Player> playerRanked = new ArrayList<Player>();
        int counter = 0;
        int streak = 0;
        while(!playerUnranked.isEmpty()) {
            if(playerUnranked.size() % range == 0 && playerUnranked.size()!=size*range) {
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
            /**
            switch(counter) {
                case 0:
                    playerUnranked.get(0).setScore(playerUnranked.get(0).getScore() + bonus);
                    break;
                case 1:
                    playerUnranked.get(0).setScore(playerUnranked.get(0).getScore() + bonus/2);
                    break;
                case 2:
                    playerUnranked.get(0).setScore(playerUnranked.get(0).getScore() + bonus/4);
                    break;
                default:
            }*/
            playerRanked.add(playerUnranked.get(0));
            playerUnranked.remove(0);
            counter += 1;
        }
        return playerRanked;
    }

    /**
     * Determines whether two player have the same time or not
     *
     * @param playerList List of all players
     * @param attributeID Defines the used attribute
     * @return The position of players with the same time
     */
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
                    //storage = playerList.get(i).getHorizontalJump();
                    storage = playerList.get(i).getThreePointJump();
                    break;
                case 4:
                    storage = playerList.get(i).getlCone();
                    break;
                case 5:
                    storage = playerList.get(i).getThreeCone();
                    break;
                case 6:
                    storage = playerList.get(i).getTwoCone();
                    break;
                case 7:
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

    /**
     * Sorts the players based on every attribute
     *
     * @param playerList List of all players
     * @param range Size of a group
     * @param size Amount of points that every player in one group gets
     * @return List of all players
     */
    private static ArrayList<Player> sortPlayer(ArrayList<Player> playerList, int range, int size) {
        ArrayList<Integer> marioRule = new ArrayList<>();
        int placement = 1;
        int amountSkip = 0;
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
                    //playerList.sort(Comparator.comparingDouble(p -> p.getHorizontalJump()));
                    playerList.sort(Comparator.comparingDouble(p -> p.getThreePointJump()));
                    Collections.reverse(playerList);
                    break;
                case 4:
                    playerList.sort(Comparator.comparingDouble(p -> p.getlCone()));
                    break;
                case 5:
                    playerList.sort(Comparator.comparingDouble(p -> p.getThreeCone()));
                    break;
                case 6:
                    playerList.sort(Comparator.comparingDouble(p -> p.getTwoCone()));
                    break;
                case 7:
                    playerList.sort(Comparator.comparingDouble(p -> p.gettTest()));
                    break;
                default:
                    System.out.println("Error attribute is not valid!");
            }
            marioRule = determineDuplicate(playerList, i);
            for (Player player: playerList) {
                switch(i) {
                    case 0:
                        player.setTenYard(placement - amountSkip);
                        break;
                    case 1:
                        player.setFourtyYard(placement - amountSkip);
                        break;
                    case 2:
                        player.setVerticalJump(placement - amountSkip);
                        break;
                    case 3:
                        //player.setHorizontalJump(placement - amountSkip);
                        player.setThreePointJump(placement - amountSkip);
                        break;
                    case 4:
                        player.setlCone(placement - amountSkip);
                        break;
                    case 5:
                        player.setThreeCone(placement - amountSkip);
                        break;
                    case 6:
                        player.setTwoCone(placement - amountSkip);
                        break;
                    case 7:
                        player.settTest(placement - amountSkip);
                        break;
                    default:
                        System.out.println("Error attribute is not valid!");
                }
                switch(placement - amountSkip - 1) {
                    case 0:
                        player.setScore(player.getScore() + bonus);
                        break;
                    case 1:
                        player.setScore(player.getScore() + bonus/2);
                        break;
                    case 2:
                        player.setScore(player.getScore() + bonus/4);
                        break;
                    default:
                }
                if(marioRule.contains(placement)) {
                    amountSkip += 1;
                } else {
                    amountSkip = 0;
                }
                placement += 1;
            }
            placement = 1;
            playerList = calculateScore(playerList, range, size, marioRule);
        }
        return playerList;
    }

    /**
     * Adds the points of all unsortable Attributes to the score
     *
     * @param playerList List of all players
     * @return List of all players with their adjusted score
     */
    private static ArrayList<Player> addUnsortableAttributes(ArrayList<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            playerList.get(i).setScore(playerList.get(i).getScore() + playerList.get(i).getRoutRunning());
            //playerList.get(i).setScore(playerList.get(i).getScore() + playerList.get(i).getOverShoulderCatchStanding());
            //playerList.get(i).setScore(playerList.get(i).getScore() + playerList.get(i).getOverShoulderCatchRunning());
        }
        return playerList;
    }

    /**
     * Creates a list of x random players
     *
     * @param amount the amount of player that will be created
     * @throws IOException
     */
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
