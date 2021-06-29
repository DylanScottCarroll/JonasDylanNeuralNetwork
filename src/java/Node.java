import java.lang.Math;
import java.util.Random;

class Node{
    public float bias;
    public float[] weights;

    //Constructor that initializes based on given bias and weights
    public Node(float bias, float[] weights){
        this.bias = bias;
        this.weights = weights;
    }
    
    //Constructor that creates an empty node (initialized at all zeroes)
    public Node(int size){
        weights = new float[size];
        bias = 0;
    }

    public Node(Node node, float mutateVal){
        weights = new float[node.weights.length];
        bias = node.bias;

        Random rand = new Random();
        for(int i = 0; i < weights.length; i++){
            weights[i] = node.weights[i] + ((rand.nextFloat()-0.5f)*mutateVal);
            if(weights[i] > 1){weights[i] = 1;}
            if(weights[i] < -1){weights[i] = -1;}
        }
    
    }


    //Calculates the weighted sum of pastValues and adds the bias.
    //Returns that value bounded by the sigmoid function.
    public float getValue(float[] pastValues){
        float runningTotal = bias;
        for(int i = 0; i < weights.length; i++){
            runningTotal += weights[i] * pastValues[i];
        }
        return sigmoid(runningTotal);

    }

    //Calculates the sigmoid function from the given input.
    private float sigmoid(float x){
        return (float)(1.0 / (1.0 + Math.pow(Math.E, -x)));
    }

    public Node createClone(){


        return new Node(10);
    }


}