import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;


class Main{

    static String imagesFile;
    static String labelsFile;
    static float mutateVal;
    static int mutateMultiplier;
    static int retirementRate;
    static int netsToNotRetire;
    static int testcases;
    static int generationsToRun;
    static String outputFile;
    static int netCount;
    static int[] lengths;

    public static void main(String[] args) throws FileNotFoundException{

        readConfig();

        if(((netCount-netsToNotRetire)/netsToNotRetire)%1 !=0){
            throw(new RuntimeException("Config file data for children and retirees does not add up!"));
        }


        Data data = new Data(imagesFile, labelsFile);

        Training train = new Training(data, netCount, lengths);
        
        try{
            FileWriter chartFile = new FileWriter("out.csv");

            for(int i = 0; i < generationsToRun; i++){
                if(i%retirementRate == 0 && i!=0){
                    train.retire(mutateMultiplier, netsToNotRetire, mutateVal);

                }


                train.evolveOneGeneration(testcases, mutateVal);

                Net best = train.generation[0];
                chartFile.write(Integer.toString(i));
                for(int j = 0; j < netCount; j++){
                    chartFile.write(", " + train.generation[j].fitness);

                    if(train.generation[0].fitness > best.fitness){
                        best = train.generation[0];
                    }
                }
                chartFile.write("\n");

                System.out.println(i + ": " + best.fitness + "\n\t" + best.accuracy);
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
        
        
    }

    private static void readConfig(){
        try{
            File file = new File("src\\resources\\Config.txt");
            Scanner scan = new Scanner(file);
            
            
            imagesFile = scan.nextLine().split(": ")[1];
            labelsFile = scan.nextLine().split(": ")[1];
            mutateVal = Float.parseFloat(scan.nextLine().split(": ")[1]);
            mutateMultiplier = Integer.parseInt(scan.nextLine().split(": ")[1]);
            retirementRate = Integer.parseInt(scan.nextLine().split(": ")[1]);
            netsToNotRetire = Integer.parseInt(scan.nextLine().split(": ")[1]);
            testcases = Integer.parseInt(scan.nextLine().split(": ")[1]);
            generationsToRun = Integer.parseInt(scan.nextLine().split(": ")[1]);
            outputFile = scan.nextLine().split(": ")[1];
            netCount = Integer.parseInt(scan.nextLine().split(": ")[1]);
            
            String[] strLengths = scan.nextLine().split(": ")[1].split(" ");
            lengths = new int[strLengths.length];
            for(int i = 0; i < strLengths.length; i++){
                lengths[i] = Integer.parseInt(strLengths[i]);
            }

            
            scan.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            System.exit(0);
        }


    }


}