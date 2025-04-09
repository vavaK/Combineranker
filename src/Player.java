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



    public Player(int id, String name, int gender, double tenYard, double fourtyYard, double verticalJump,
                  double horizontalJump, double threePointJump, double lCone, double threeCone, double twoCone,
                  double tTest, double routRunning, double overShoulderCatchStanding, double overShoulderCatchRunning){
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
            return "male";
        }
        return "female";
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

}
