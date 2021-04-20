import java.lang.Math;

class Node{
    private float bias;
    private float[] weights;

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
        return 1 / (1 + Math.pow(Math.E, -x));
    }


}