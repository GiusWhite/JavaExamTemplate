package components;

/**
 * Created by Giuseppe on 14/05/2015.
 */
public class GameLogic {
    private static GameLogic instance = null;

    public static final int DIFFICULT_ALDO = 2;
    public static final int DIFFICULT_EASY = 4;
    public static final int DIFFICULT_NORMAL = 5;
    public static final int DIFFICULT_OLIV = 10;

    public int baseSpeed;
    public int speed;
    public int baseHealtPoint;
    public int healtPoint;
    public int score;
    public int difficult;

    private GameLogic(){
        this.baseSpeed = 2;
        this.speed = 2;
        this.baseHealtPoint = 5;
        this.healtPoint = 5;
        this.score = 0;
        this.difficult = DIFFICULT_ALDO;
    }

    public static GameLogic getInstance(){
        if(instance == null){
            instance = new GameLogic();
        }
        return instance;
    }

    public int getDifficult() {
        return difficult;
    }

    public void setDifficult(int difficult) {
        this.difficult = difficult;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHealtPoint() {
        return healtPoint;
    }

    public void setHealtPoint(int healtPoint) {
        this.healtPoint = healtPoint;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getBaseSpeed() {
        return baseSpeed;
    }

    public void setBaseSpeed(int baseSpeed) {
        this.baseSpeed = baseSpeed;
        this.speed = baseSpeed;
    }

    public int getBaseHealtPoint() {
        return baseHealtPoint;
    }

    public void setBaseHealtPoint(int baseHealtPoint) {
        this.baseHealtPoint = baseHealtPoint;
        this.healtPoint = baseHealtPoint;
    }

    public void resetAll(){
        this.setSpeed(getBaseSpeed());
        this.setScore(0);
        this.setHealtPoint(getBaseHealtPoint());
    }
}
