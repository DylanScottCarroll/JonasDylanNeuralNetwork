

class Main{
    public static void main(String[] args){
        Net net = new Net("Data.txt");
        net.Save("test.txt");
        
       float[] output = net.propogate(new float[]{0.1f, 0.1f});

       for(float value : output){
           System.out.println(value);
       }

        
    }



}