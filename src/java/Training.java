import java.util.Random;

public class Training{
    Data data;
    Net[] nextGeneration;
    Net[] generation = null;

    //Constructors
    
    //Random Generations
    public Training(Data data, int netCount){
        this.data = data;
        nextGeneration = randomNets(netCount);
    }

    //From a list of file names
    public Training(Data data, String[] nets){
        this.data = data;
        nextGeneration = loadGeneration(nets)
    }

    //Makes new Networks from the given list of Net Storage Files
    private Net[] loadGeneration(String[] files){
        Net[] nets = new Net[files.length];
        for(int i = 0; i < files.length; i++){
            nets[i] = new Net(file[i]);
        }
    }


    public void evolveOneGeneration(int testCases, float mutateVal){
        runNextGeneration(testCases);
        if(generation==null){
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
            nextGeneration[i] = generation[i].mutate(mutateVal);
        }

    }




    //Runs every network in a nextGeneration through a given number of data test testCases
    //Then updates the fitness value of each network
    //input: testcases = test cases to run
    public void runNextGeneration(int testCases){
        Random rand = new Random();
        for(int i = 0; i < nextGeneration.length; i++){
            float fitness = 0.0f;
            for(int j = 0; j < testCases; j++){
                int randData = rand.nextInt(data.images.length);
                
                Image image = data.getInput(randData);
                float[] results = nextGeneration[i].propogate(image.pixels);


                fitness += calculateFitness(results, image.label);

            }
            nextGeneration[i].fitness = fitness / testCases;
            fitness = 0.0f;
        }
        rand.close();
    }
    

    //Calculate the fitness of a comparison based on the guess 
    private float calculateFitness(float[] results, int label){
        for(int k = 0; k < results.length; k++){
            fitness += results[k] * (label == k ? 1 : -1);
        }
    }



    public Net[]
}