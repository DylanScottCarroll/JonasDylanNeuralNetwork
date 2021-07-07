import java.io.*;

//Some code coppied from here:
//https://www.codejava.net/java-se/file-io/how-to-read-and-write-binary-files-in-java

class Data{
    
    final int imageDataOffset = 16;
    final int labelDataOffset = 8;
    final int SIZE = 28;

    private Image[] images;
    public int imageCount;
    
    //TODO: 
    public Data(String imageFile, String labelFile){
        byte[] imagesBytes;
        byte[] labelsBytes;
        

        //Get all the bytes out of the file for God's sake.
         try {

            InputStream imageInputStream = new FileInputStream(imageFile);
            long imageFileSize = new File(imageFile).length();
            imagesBytes = new byte[(int) imageFileSize];
            imageInputStream.read(imagesBytes);
            imageInputStream.close();

            InputStream labelInputStream = new FileInputStream(labelFile);
            long labelFileSize = new File(labelFile).length();
            labelsBytes = new byte[(int) labelFileSize];
            labelInputStream.read(labelsBytes);
            labelInputStream.close();
 

            
            //Loop through each image in the binary data and create an Image object
            int imageCount = (imagesBytes.length - imageDataOffset) / (SIZE*SIZE);
            images = new Image[imageCount];

            for(int i = 0; i < imageCount; i++){
                byte[] imageData = new byte[SIZE*SIZE];
                
                int k = 0;
                for(int j = i*SIZE*SIZE + imageDataOffset; j < (i+1)*SIZE*SIZE + imageDataOffset; j++){
                    imageData[k++] = imagesBytes[j];
                }

                byte label = labelsBytes[i + labelDataOffset];

                images[i] = new Image(imageData, label);

            }

            imageCount = images.length;


        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);

        }

        


    }

    public Image getInput(int n){
        return images[n];
    }





}