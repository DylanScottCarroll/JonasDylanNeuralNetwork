# JonasDylanNeuralNetwork
Dylan and Jonas are working together to make a Neural Network for fun and glory.


To Do:

☑ Hack together the actual network. And get it to run.

☑ Save and load weights

☑ Write training algorithm

☑ Write way to store and load training data.

Put it all together in Main

Add printing stuff to display internal state

Train.





TEST SET LABEL FILE (t10k-labels-idx1-ubyte):
[offset] [type]          [value]          [description]
0000     32 bit integer  0x00000801(2049) magic number (MSB first)
0004     32 bit integer  10000            number of items
0008     unsigned byte   ??               label
0009     unsigned byte   ??               label
........
xxxx     unsigned byte   ??               label
The labels values are 0 to 9.


TEST SET IMAGE FILE (t10k-images-idx3-ubyte):
[offset] [type]          [value]          [description]
0000     32 bit integer  0x00000803(2051) magic number
0004     32 bit integer  10000            number of images
0008     32 bit integer  28               number of rows
0012     32 bit integer  28               number of columns
0016     unsigned byte   ??               pixel
0017     unsigned byte   ??               pixel
........
xxxx     unsigned byte   ??               pixel


Pixels are organized row-wise. Pixel values are 0 to 255. 0 means background (white), 255 means foreground (black).