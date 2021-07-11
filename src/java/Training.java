import java.util.Arrays;
import java.util.Random;

public class Training{
    Data data;
    Net[] nextGeneration;
    Net[] generation = null;

    //Constructors
    
    //Random Generations
    public Training(Data data, int netCount, int[] layerLengths){
        this.data = data;
        nextGeneration = randomNets(netCount, layerLengths);
    }

    //From a list of file names
    public Training(Data data, String[] nets){
        this.data = data;
        nextGeneration = loadGeneration(nets);
    }

    //Makes new Networks from the given list of Net Storage Files
    private Net[] loadGeneration(String[] files){
        Net[] nets = new Net[files.length];
        for(int i = 0; i < files.length; i++){
            nets[i] = new Net(files[i]);
        }
        return nets;
    }


    public void evolveOneGeneration(int testCases, float mutateVal){
        runNextGeneration(testCases);
        if(generation==null){
            generation = new Net[nextGeneration.length];
            for(int i = 0; i < nextGeneration.length; i++){
                generation[i] = nextGeneration[i];
            }

        } else {
            for(int i = 0; i < nextGeneration.length; i++){
                if(generation[i].fitness < nextGeneration[i].fitness){
                    generation[i] = nextGeneration[i];
                }
            }
        }
        
        for(int i = 0; i < nextGeneration.length; i++){
            nextGeneration[i] = new Net(generation[i], mutateVal);
        }

    }

    public void retire(int mutateMultiplier, int netsToNotRetire, float mutateVal, String outputFile){
        Arrays.sort(generation);
        
        System.out.println("Saving Best...");
        generation[0].Save(outputFile);
        for(int i = netsToNotRetire; i < generation.length; i++ ){
            generation[i] = new Net(generation[i%netsToNotRetire], mutateMultiplier * mutateVal);
        }
        for(int i = 0; i < generation.length; i++){
            nextGeneration[i] = generation[i];
        }
    }




    //Runs every network in a nextGeneration through a given number of data test testCases
    //Then updates the fitness value of each network
    //input: testcases = test cases to run
    public void runNextGeneration(int testCases){
        Random rand = new Random();
        for(int i = 0; i < nextGeneration.length; i++){
            float fitness = 0.0f;
            float accuracy = 0.0f;
            for(int j = 0; j < testCases; j++){
                
                int randData = rand.nextInt(data.imageCount);
                
                Image image = data.getInput(randData);
                float[] results = nextGeneration[i].propogate(image.pixels);


                fitness += calculateFitness(results, image.label);
                
                accuracy += isCorrectGuess(results, image.label) ? 1 : 0;
            }
            nextGeneration[i].fitness = fitness / testCases;
            nextGeneration[i].accuracy = accuracy / (float)testCases;
            fitness = 0.0f;
        }
    }
    

    //Calculate the fitness of a comparison based on the guess 
    private float calculateFitness(float[] results, int label){
        float fitness = 0;
        for(int k = 0; k < results.length; k++){
            if(label==k){
                fitness += results[k]*9;
            }
            else{
                fitness -= results[k];
            }
        }
        return fitness/9;
    }

    private boolean isCorrectGuess(float[] results, int label){
        float largestGuess = results[1];
        int largesIndex = 0;

        for(int i = 1; i < results.length; i++){
            if(results[i] > largestGuess){
                largestGuess = results[i];
                largesIndex = i;
            }
        }

        return (largesIndex == label);

    }

    private Net[] randomNets(int length, int[] layerLengths){
        Net[] nets = new Net[length];
        Net emptyNet = new Net(layerLengths);
        for(int i = 0; i < nets.length; i++){
            nets[i] = new Net(emptyNet, 2);
        }

        return nets;
    }
}