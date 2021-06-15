
public class Training{
    Data data;
    Net[] generation;
    Net[] prevGeneration = null;

    //Constructors
    public Training(Data data){
        this.data = data;
        generation = randomNets();
    }
    public Training(Data data, String[] nets){
        this.data = data;
        generation = calcGeneration(nets)
    }

    //Makes new Networks from the given list of Net Storage Files
    private Net[] calcGeneration(String[] files){
        Net[] nets = new Net[files.length];
        for(int i = 0; i < files.length; i++){
            nets[i] = new Net(file[i]);
        }
    }


    public void evolveOneGeneration(int testCases){
        runGeneration(testCases);
        if(prevGeneration==null){
            prevGeneration = generation;
        } else {
            for(int i = 0; i < generation.length; i++){
                
            }
        }

    }




    //Runs every network in a generation through a given number of data test testCases
    //Then updates the fitness value of each network
    //input: testcases = test cases to run
    public void runGeneration(int testCases){
        Random rand = new Random();
        for(int i = 0; i < generation.length; i++){
            float fitness = 0.0f;
            for(int j = 0; j < testCases; j++){
                int randData = rand.nextInt(data.images.length);
                Image image = data.getInput(randData);
                float[] results = generation[i].propogate(image.pixels);

                for(int k = 0; k < results.length; k++){
                    if(image.label == k){
                        fitness += results[k];
                    } else{
                        fitness -= results[k];
                    }
                }
            }
            generation[i].fitness = fitness;
            fitness = 0.0f;
        }
        rand.close();
    }

    


}