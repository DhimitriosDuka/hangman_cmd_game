import java.util.Random;
import java.util.Arrays;
public class Letters {

    int [] letterIndexes(String str){

        Random random = new Random();
        if(str.length() <= 5){
            int[] index = new int[1];
            index[0] = random.nextInt(str.length());
            return index;
        }else if(str.length() > 5 && str.length() <= 10){
            int[] index = new int[2];
            int i = 0;
            while(i < 2){
                index[i] = random.nextInt(str.length());
                i++;
            }
            Arrays.sort(index, 0, 2);
            return index;
        }else{
            int dim = (int)(str.length() / 4);
            int[] index = new int[dim];
            int i = 0;
            while(i < dim){
                index[i] = random.nextInt(str.length());
                i++;
            }
            Arrays.sort(index, 0, dim);
            return index;
        }

    }
}
