import java.util.Scanner;
import java.io.File;
import java.io.IOException;

class Net{
    private Node[][] layers;

    public Net(){}

    //Initializes a brand spanking new network with all-zero weights
    public Net(int[] layerSizes){
        layers = new Node[layerSizes.length-1][];
        for(int i = 0 i < laterSizes.length; i++){
            layers[i] = new Node(layerSizes[i+1]);
        }
    }
    
    //Initializes a network from a file given a file namme
    //File:
    //# of layers
    //list of # of nodes per layer
    //bias of (1,0) followed by list of weights for(1,0)
    //bias of (1,1) followed by list of weights for(1,1)
    //bias of (1,2) followed by list of weights for(1,2)
    //...
    public Net(String fileName){
        Scanner scan = new Scanner(fileName);
        layers = new Node[scan.nextInt()-1][];
        int layerLengths[] = new int[layers.length];
        
        for(int i = 0; i < layers.length+1;i++){
            layerLengths[i] = scan.nextInt();
        }
        for(int i = 0; i < layers.length;i++){
            layers[i] = new Node[layerLengths[i+1]];
        }
        for(int i = 0; i < layers.length; i++){
            for(int j = 0; j < layers[i].lenght; j++){
                float bias = scan.nextFloat();
                float[] weights = new float[layerLengths[i]];
                for(int k = 0; k < weights.length; k++){
                    weights[k] = scan.nextFloat();
                }
                layers[i][j] = new Node(bias, weights);
            }
        }
    }

    public Save(String fileName){
        try{
            FileWriter file = new FileWriter(fileName);
            
            file.write(layers.length);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //Given the input
    public Propogate(float input){
        float pastValues[] = input;
        float nextValues;

        for(int i = 0; i < layers.length; i++){
            nextValues = new float[layers[i].length];
            float currentLayer[] = layers[i];

            for(int j = 0; j < currentLayer; j++){
                nextValues[j] = currentLayer[j].getValue(pastValues)
            }

            pastValues = nextValues;
        }

        return nextValues;
        
    }



}