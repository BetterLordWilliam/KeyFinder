# KeyFinder - Key hunting game <img src="./res/player&#47;movement/Player_D1.png" width="25" />

## About the Project

KeyFinder was a game that I created with Java back during the end of 2022 and during the start of 2023. The project took approximately 3 months to fully complete due to a busy school schedule, but in March of 2023 I made the last official changes.

Towards the end of the project, while conversing with my Computer Programming 12 instructor, he suggested that I submit this project not only as a final project for his course, but also to the District Authority for the chance of winning $1250. After some anxious waiting, I heard back from the District Authority with good news. KeyFinder and its documentation had secured me the scholarship!

The creation of this game was an exercise in object-oriented design as well as a test of my knowledge of programming concepts such as recursion.

## Readme Contents

- [General info](#general-info)
  - [How to Run](#How-to-Run)
  - [What to do](#what-to-do)
- [Technologies](#technologies)

## General Info

### How to Run

This is very easy! So long as you have Java installed, simply download this folder and run the [provided jar](./2DJavaGame%20SUBMISSION-SC.jar) file give the name '2DJavaGame SUBMISSION-SC.jar'

This is what that might look like inside of your downloaded folder:
![downloadedimage](./githubstuff/JarFileScreenshot.png)

Enjoy!

*Java is required to be able to run this project. Download java from [here](https://www.java.com/en/download/).*

### What to do

In the KeyFinder game you are the key finder, a character who must collect keys to open locked doors and access the final purple chest of each level. There are four levels in total each containing three to four different keys. 

Key example:
![bkey](./res/objects/B_Key.png)![rkey](./res/objects/R_Key.png)![ykey](./res/objects/Y_Key.png)

Once you reach the final level and unlock the last chest ![alt text](./res/objects/ChestClosed.png),

you will have successfully completed the game. Congratulations! From here you are free to play again or quit the game by pressing the 'esc' key.

*If you want more information, feel free to read the documentation that I wrote for this game [here](./KeyFinder%20Design%20Document.pdf).*

## Technologies

- Java
- BeepBox (for the music)

## Contents

```
Top Level of Project:
├── githubstuff                                          # Folder containing GitHub related information
├── res                                                  # Folder containing graphics
├── src                                                  # Folder containing source code
├── 2DJavaGame SUBMISSIOn-SC.jar                         # Executable jar file
├── InstructorFeedback.pdf                               # Instructors feedback
├── KeyFinder Design Document.pdf                        # Documentation
├── NOTE.txt                                             # Note for scholarship committee
└── README.md                                            # This!

res contents:
├── UI                                                   # Folder containing UI graphics
|
└────| Text                                                    # Folder containing fonts
|    |
|    └───── ARCADECLASSIC.TTF                                        # ttf font used
|    └───── FFFFORWA.TTF                                             # ttf font
|    └───── pizzadudedotdk.txt                                       # ttf font
|    └───── read_me.txt                                              # Read me text file copntaining font license
|
└───── Key.png                                                 # Blank key image
└───── KeyFinderUI.png                                         # Key image container
└───── KeyFinderUI.psd                                         # psd for key image container
└───── ShowBoxClosed.png                                       # Controls container closed
└───── ShowBoxClosed.psd                                       # psd for controls container closed
└───── ShowBoxOpen.png                                         # Controls container opened
└───── SpeedIcon.png                                           # Sprint icon
|
├── maps                                                 # Folder containing map text data
├── objects                                              # Folder containing object graphics
├── player/movement                                      # Folder containing player graphics
├── sound                                                # Folder containing sound
└── tiles                                                # Folder containing tile graphcis

src content:


```

<br/><br/>
