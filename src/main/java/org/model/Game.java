package org.model;

public class Game {
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;
    private boolean isFinished;

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeTeamScore = 0;
        this.awayTeamScore = 0;
        this.isFinished = false;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public int getTotalScore() {
        return homeTeamScore + awayTeamScore;
    }

    public void updateScore(int homeTeamScore, int awayTeamScore){
        this.homeTeamScore = homeTeamScore;
        this.awayTeamScore = awayTeamScore;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game other = (Game) o;
        return homeTeam.equalsIgnoreCase(other.homeTeam) && awayTeam.equalsIgnoreCase(other.awayTeam);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + homeTeam.toLowerCase().hashCode();
        result = 31 * result + awayTeam.toLowerCase().hashCode();
        return result;
    }


    @Override
    public String toString() {
        return "Game{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamScore=" + awayTeamScore +
                ", isFinished=" + isFinished +
                '}';
    }
}
