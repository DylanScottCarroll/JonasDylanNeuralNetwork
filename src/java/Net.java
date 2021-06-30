import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;


class Net{
    private Node[][] layers;
    private int[] layerLengths;

    public Net(){}

    public float fitness = 0.0f;

    //Initializes a brand spanking new network with all-zero weights
    public Net(int[] layerLengths){
        this.layerLengths = layerLengths;
        layers = new Node[layerLengths.length-1][];

        for(int i = 0; i < layers.length; i++){
            layers[i] = new Node[layerLengths[i+1]];

            Node[] currentLayer = layers[i];
            for(int j = 0; j < currentLayer.length; j++){
                currentLayer[j] = new Node(layerLengths[i]);
            }

        }
    }
    
    public Net(Net net, float mutateVal){
        this.layerLengths = net.layerLengths.clone();

        layers = new Node[layerLengths.length-1][];
        for(int i = 0; i < layers.length; i++){
            layers[i] = new Node[layerLengths[i+1]];
            for(int j = 0; j < layers[i].length; j++){
                this.layers[i][j] = net.layers[i][j].createClone();
                layers[i][j] = new Node(net.layers[i][j], mutateVal);
            }
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
    //bias of (x,y) followed by list of weights for(x,y)
    //...
    public Net(String fileName){
        try{

        Scanner scan = new Scanner(new File(fileName));

        layers = new Node[scan.nextInt()-1][];
        layerLengths = new int[layers.length+1];
        
        for(int i = 0; i < layerLengths.length;i++){
            layerLengths[i] = scan.nextInt();
        }

        for(int i = 0; i < layers.length;i++){
            layers[i] = new Node[layerLengths[i+1]];
        }
        for(int i = 0; i < layers.length; i++){
            for(int j = 0; j < layers[i].length; j++){
                float bias = scan.nextFloat();
                float[] weights = new float[layerLengths[i]];
                for(int k = 0; k < weights.length; k++){
                    weights[k] = scan.nextFloat();
                }
                layers[i][j] = new Node(bias, weights);
            }
        }
        scan.close();

        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(InputMismatchException e){
            System.out.println("Oppsie!: Tried to load an invalid file :(");
            e.printStackTrace();
        }
    }

    public void Save(String fileName){
        try{
            FileWriter file = new FileWriter(fileName);
            
            //Write the number of layers
            file.write(layerLengths.length + "\n");

            //Write the layer lengths
            for(int i = 0; i < layerLengths.length; i++){
                file.write(layerLengths[i] + " ");
            }
            file.write("\n");

            //Write all of the nodes
            for(int i = 0; i < layers.length; i++){
                if(i!=0){file.write("\n\n");}

                int currentLength = layers[i].length;
                for(int j = 0; j < currentLength; j++){
                    if(j!=0) {file.write("\n");}
                    
                    Node node = layers[i][j];

                    file.write(node.bias + " ");

                    for(int k = 0; k < node.weights.length; k++){
                        file.write(node.weights[k] + " ");
                    }

                   
                }
                
            }

            file.close();
        }
        catch(IOException e){
            e.printStackTrace();
        }

    
    }

    //Given the input layer activations, calculate the acitvations of all nodes
    //  and return the activation of the output layer.
    //
    //Runs the network :)
    public float[] propogate(float[] input){
        float[] pastValues = input;
        float[] nextValues = null;

        for(int i = 0; i < layers.length; i++){
            nextValues = new float[layers[i].length];
            Node[] currentLayer = layers[i];

            for(int j = 0; j < currentLayer.length; j++){
                nextValues[j] = currentLayer[j].getValue(pastValues);
            }

            pastValues = nextValues;
        }

        return nextValues;
        
    }

    public Net createMutatedClone(float mutateVal){
        return new Net(this, mutateVal);

    }




}