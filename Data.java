import java.io.*;

//Some code coppied from here:
//https://www.codejava.net/java-se/file-io/how-to-read-and-write-binary-files-in-java

class Data{
    
    final int imageDataOffset = 16;
    final int labelDataOffset = 8;

    private Image[] images;
    
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
 
 
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        //Loop through each image in the binary data and create an Image object
        int imageCount = (imagesBytes.length - imageDataOffset) / SIZE*SIZE

        for(int i = 0; i < imageCount; i++){
            char[] imageData = new char[SIZE*SIZE];

            for(int j = i*SIZE*SIZE + image; j < (i+1)*SIZE*SIZE + 16; j++){
                imageData[i] = imagesBytes[j];
            }

            char label = labelsData[i + labelDataOffset];

            images[i] = new Image(imageData, label);

        }


    }

    public Image getInput(int n){
        return images[n];
    }





}