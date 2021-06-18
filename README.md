# Jonas-Dylan Neural Network Project
Dylan and Jonas are working together to make a Neural Network for fun and glory.


&nbsp


## To Do:

☑ Hack together the actual network. And get it to run.

☑ Save and load weights

☑ Write evolutionaty training algorithm

☑ Write way to load training data.

☐ Put it all together in Main (and debug 😖)

☐ Add printing stuff to display internal state

☐ Train the network to recognize digits

❔ Write graphical interface that lets us draw our own digits

❔ Apply to other problems

❔ Clean up the interface and package it as a library

❔ Implement Backprobagation algorithm


&nbsp


## Digit Training Data File Structure


**LABEL FILE**

|  offset  |  type           |value             | description              | 
|----------|-----------------|------------------|---------------------     |
| 0000     | 32 bit integer  | 0x00000801(2049) | magic number (MSB first) |
| 0004     | 32 bit integer  | 10000            | number of items          |
| 0008     | unsigned byte   | 0-9              | label                    |
| 0009     | unsigned byte   | 0-9              | label                    |
| ....     | unsigned byte   | 0-9              | label                    |


**IMAGE FILE**

| offset   | type            | value            | description         |
|----------|-----------------|------------------|---------------------|
| 0000     | 32 bit integer  | 0x00000803(2051) | magic number        |
| 0004     | 32 bit integer  | 10000            | number of images    |
| 0008     | 32 bit integer  | 28               | number of rows      |
| 0012     | 32 bit integer  | 28               | number of columns   |
| 0016     | unsigned byte   | 0-255            | pixel               |
| 0017     | unsigned byte   | 0-255            | pixel               |
| ....     | unsigned byte   | 0-255            | pixel               |


Images are greyscale with pixel values 0 to 255. 0 is background (white), 255 is foreground (black)
Pixels are stored in row-major order.
