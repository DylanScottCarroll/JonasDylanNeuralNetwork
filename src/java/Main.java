import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


class Main{
    public static void main(String[] args) throws FileNotFoundException{

        File file = new File("Config.txt");
        Scanner scan = new Scanner(file);
	//booya
        String imagesFile = scan.next();
        String labelsFile = scan.next();
        float mutateVal = scan.nextFloat();
        int testcases = scan.nextInt();
        int generationsToRun = scan.nextInt();
        String outputFile = scan.next();
        int netCount = scan.nextInt();
        int saveCount = 

        scan.close();

        Data data = new Data(imagesFile, labelsFile);

        Training train = new Training(data);
        
        for(int i = 0; i < generationsToRun; i++){
            train.evolveOneGeneration(testcases, mutateVal);


        }
        
        for(int i = 0; i < netCount; i++){


        }

        
    }



}