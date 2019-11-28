import java.util.*;
import java.sql.*;

public class Hangman {

    public static void main(String[] args) {

        System.out.printf(" _                                             \n");
        System.out.printf("| |                                            \n");
        System.out.printf("| |__   __ _ _ __   __ _ _ __ ___   __ _ _ __  \n");
        System.out.printf("| '_ \\ / _` | '_ \\ / _` | '_ ` _ \\ / _` | '_ \\ \n");
        System.out.printf("| | | | (_| | | | | (_| | | | | | | (_| | | | | \n");
        System.out.printf("|_| |_|\\__,_|_| |_|\\__, |_| |_| |_|\\__,_|_| |_| \n");
        System.out.printf("                    __/ |                      \n");
        System.out.printf("                   |___/  \n");

        final int stagenumber = 8;

        Scanner scanner = new Scanner(System.in);

        Stages stages = new Stages();

        Random random = new Random();
        int stageZ = 1;

        ResultSet resultSet = null;
        try {
            ResultSet bound = DatabaseConnnection.Conn().executeQuery("SELECT COUNT(*) FROM words");
            bound.next();
            int randNumber = random.nextInt(bound.getInt(1)) + 1;
            String sql = String.format("SELECT * FROM words WHERE idword = %d", randNumber);
            resultSet = DatabaseConnnection.Conn().executeQuery(sql);
            resultSet.next();
            char[] progress = new char[resultSet.getString(2).length()];

            Letters letters = new Letters();
            int[] index = letters.letterIndexes(resultSet.getString(2));
            System.out.println("Size : " + index.length);
            for (Integer x : index){
                System.out.printf("%d ", x);
            }
            int k = 0;

            if(index.length == 1){
                for(int i = 0; i < resultSet.getString(2).length(); i++){
                    if(i == index[0]){
                        progress[i] =  resultSet.getString(2).charAt(i);
                    }else{
                        progress[i] = '_';
                    }
                }
            }else{
                for(int i = 0; i < resultSet.getString(2).length(); i++){
                    if(i == index[k]){
                        progress[i] = resultSet.getString(2).charAt(i);
                        k++;
                    }else{
                        if( resultSet.getString(2).charAt(i) == ' '){
                            progress[i] = ' ';
                        }else{
                            progress[i] = '_';
                        }
                    }
                }
            }
            System.out.println(resultSet.getString(2));
            int i = 0, j = 0;

            for(char x : progress){
                System.out.printf("%c ", x);
            }

            while (i < stagenumber) {
                System.out.println("\nTry a letter: ");
                char str = scanner.next().charAt(0);
                if (resultSet.getString(2).contains(String.valueOf(str))){//convertion from char to string
                    while (j < resultSet.getString(2).length()) {
                        if(resultSet.getString(2).charAt(j) == str) {
                            progress[j] = str;
                        }
                        j++;
                    }
                    j = 0;
                } else {
                    stages.switchStages(stageZ);
                    stageZ++;
                    i++;
                }

                for(char x: progress){
                    System.out.printf("%c ", x);
                }

                if(String.copyValueOf(progress).equals(resultSet.getString(2))){
                    System.out.println("\nYou Won!!!!");
                    return;
                }
            }
            i++;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\nYou lost the game!!");
    }
}



