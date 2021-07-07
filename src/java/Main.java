import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;


class Main{
    public static void main(String[] args) throws FileNotFoundException{

        File file = new File("src\\resources\\Config.txt");
        Scanner scan = new Scanner(file);

        String imagesFile = scan.nextLine();
        String labelsFile = scan.nextLine();
        float mutateVal = scan.nextFloat();
        int testcases = scan.nextInt();
        int generationsToRun = scan.nextInt();
        scan.nextLine();
        String outputFile = scan.nextLine();
        int netCount = scan.nextInt();
        scan.nextLine();

        String lineScanned = scan.nextLine();
        String[] lengths = lineScanned.split(" ");

        scan.close();

        System.out.println(lineScanned);
        

        int[] intLengths = new int[lengths.length];
        for(int i = 0; i < lengths.length; i++){
            intLengths[i] = Integer.parseInt(lengths[i]);
        }


        Data data = new Data(imagesFile, labelsFile);

        Training train = new Training(data, netCount, intLengths);
        
        try{
            FileWriter chartFile = new FileWriter("out.csv");

            for(int i = 0; i < generationsToRun; i++){
                train.evolveOneGeneration(testcases, mutateVal);

                chartFile.write(Integer.toString(i));
                for(int j = 1; j < netCount; j++){
                    chartFile.write(", " + train.generation[j].fitness);
                }
                chartFile.write("\n");

                System.out.println(i);
            }

            chartFile.close();
        }
        catch(IOException e){
            e.printStackTrace();
            
        }

        Net best = train.generation[0];
        for(int i = 1; i < netCount; i++){
            if(train.generation[i].fitness > best.fitness)
            best = train.generation[i];
        }
        best.Save(outputFile);
        
    }



}