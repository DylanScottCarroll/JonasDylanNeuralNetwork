class Image{

    private final char SIZE = 28;

    public float[] pixels = new float[SIZE*SIZE];
    public int label;

    public Image(){}

    public Image(char[] bytes, char label ){
        this.label = (int) label;
        
        for(int i = 0; i < SIZE*SIZE; i++){
            pixels[i] = bytes[i] / 255.0f;
        }
    }

    public void print(){
        System.out.print(label);
        System.out.println(": ");
        for(int i = 0; i < SIZE; i++){
            String line = "";
            for(int j = 0; j < SIZE; j++){
                if(pixels[i*SIZE + j] > 0.5){
                    line += "#";
                }
                else{
                    line += " ";
                }
            }
            System.out.println(line);
        }

    }

}