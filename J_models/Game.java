package J_models;

import java.util.HashMap;

public class Game {

    private HashMap<Team, Integer> scoreboard;
    private static int gameCount;
    private static final int QUAFFLE_POINTS = 10;
    private static final int SNITCH_POINTS = 150;

    public Game(Team home, Team away){
        this.scoreboard = new HashMap<Team,Integer>();
        this.scoreboard.put(new Team(home),0);
        this.scoreboard.put(new Team(away),0);
        gameCount++;
    }

    public int getScore(Team team){
        return this.scoreboard.get(team);
    }

    public void setScore(Team team,Integer score){
        if(team == null) {
            throw new IllegalArgumentException("Cannot add null to the Scoreboard.");
        }
        this.scoreboard.put(team,score);
    }

    public Team getTeam(String name){
        return this.scoreboard.keySet().stream()
                .filter((key) -> key.getHouse().equals(name))
                .findFirst()
                .orElse(null);

        //or
        //return this.scoreboard.entrySet().stream()
        //  .filter((entry) -> entry.getKey().getHouse().equals(name))
        //  .findFirst()
        //  .orElse(null)
        //  .getKey();

        //or
        //return this.scoreboard.keySet().stream()
        //  .filter((key) -> key.getHouse().equals(name))
        //  .collect(Collectors.toList( ))
        //.get(0);

        //or
        //return this.scoreboard.entrySet().stream()
        //  .filter((entry) -> entry.getKey().getHouse().equals(name))
        //  .collect(Collectors.toList())
        //  .get(O)
        //  .getKey();
    }

    public static int getGameCount() {
        return gameCount;
    }

    public static int getQuafflePoints() {
        return QUAFFLE_POINTS;
    }

    public static int getSnitchPoints() {
        return SNITCH_POINTS;
    }

    public String getPlaceholder(String play){
        return play.substring(play.indexOf("<")+1,play.indexOf(">"));
    }

    public String replacePlaceholder(String play,String placeholder, String value){
        return play.replace("<"+placeholder+">",value);
    }

    public void quaffleScore(Team team){
        this.setScore(team,this.getScore(team)+QUAFFLE_POINTS);
    }

    public void catchSnitch(Team team){
        this.setScore(team,this.getScore(team)+SNITCH_POINTS);
    }

    public Team getRandomTeam(){
        Object[] teams = scoreboard.keySet().toArray();
        return (Team)teams[random(teams.length)];
    }
    public int random(int range){
        double d = Math.random() * range;
        return (int)d;
    }

    public String simulate(String play){
        String placeholder = getPlaceholder(play);
        Team team = getRandomTeam();
        String value = "";

        if(placeholder.equals(Team.getPositionChaser())){
            quaffleScore(team);
            value = team.getChasers()[random(team.getChasers().length)];
        }else if(placeholder.equals(Team.getPositionSeeker())){
            catchSnitch(team);
            value = team.getSeeker();
        }else if(placeholder.equals(Team.getPositionKeeper())) {
            value = team.getKeeper();
        }
        return replacePlaceholder(play,placeholder,value);
    }
}
//        if(placeholder.equals(Team.getPositionChaser())){
//            quaffleScore(team);
//            return replacePlaceholder(play,placeholder,team.getChasers()[random(team.getChasers().length)]);
//        }else if(placeholder.equals(Team.getPositionSeeker())){
//            catchSnitch(team);
//            return replacePlaceholder(play,placeholder,team.getSeeker());
//        }else if(placeholder.equals(Team.getPositionKeeper())){
//            return replacePlaceholder(play,placeholder,team.getKeeper());
//        }else{
//            return "";



