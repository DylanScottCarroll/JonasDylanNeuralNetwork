class Image{

    private final char SIZE = 28;

    public float[] pixels = new float[SIZE*SIZE];
    public int label;

    public Image(){}

    public Image(byte[] bytes, byte label ){
        this.label = ((int) label) & 0xFF ;
        
        for(int i = 0; i < SIZE*SIZE; i++){
            pixels[i] = (((int) bytes[i]) & 0xFF) / 255.0f;
        }
    }

    public void print(){
        System.out.print(label);
        System.out.println(": ");
        for(int i = 0; i < SIZE; i++){
            String line = "";
            for(int j = 0; j < SIZE; j++){
                float value = pixels[i*SIZE + j];
                
                if(value < 0.2){
                    line += "  ";
                }
                else if(value < 0.4){
                    line += "░░";
                }
                else if(value < 0.6){
                    line += "▒▒";
                }
                else if(value < 0.8){
                    line += "▓▓";
                }
                else{
                    line += "██";
                }
            }
            System.out.println(line);
        }

    }

}