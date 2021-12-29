![splash](https://user-images.githubusercontent.com/30992311/133886844-88899994-fcee-4850-af65-3d9aab4726cb.png)

[![License](https://img.shields.io/github/license/Blackoutburst/QuakeCraft.svg)](LICENSE)
[![Release](https://img.shields.io/github/release/Blackoutburst/QuakeCraft.svg)](https://github.com/Blackoutburst/QuakeCraft/releases)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/a0e7c7d8e9b44594b5c8469ac6be0f21)](https://www.codacy.com/gh/Blackoutburst/QuakeCraft/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Blackoutburst/QuakeCraft&amp;utm_campaign=Badge_Grade)

# QuakeCraft
This plugin re-creates the base game of QuakeCraft from Hypixel for learning purposes, extra features got added due to community demands.

Note this plugin is designed to have multiples map in different folder, test were effectued with [Multiverse-Core](https://dev.bukkit.org/projects/multiverse-core)

And this plugin is made for spigot / paper 1.8.8 (V1_8_R3)

## Commands
`/start <world name>` start the game on the specified map.\
`/end` end the game.\
`/triggerSpeed <value>` change the trigger speed for everyone.\
`/dashDelay <value>` change the dash delay for everyone.\
`/scan <x1> <y1> <z1> <x2> <y2> <z2>` scan sponges block in a specific area and write the location in a file named after the world name (define the map spawnpoints).\
`/loadSpawn <world name>` load the spawnpoint inside the memory (usually this should not be used).\
`/clean <world name>` remove the sponge block from a map.\
`/showSpawn <world name>` put back the sponge block on a map loaded from the file generated with the scan command.

Commands such as:
- scan
- loadSpawn
- clean
- showSpawn

Require the permission `quake.debug` or operator status to be used

## Features
All features implemented so far are:

### Railgun
A really obvious one just take you gun and right click to shoot.\
![image](https://user-images.githubusercontent.com/30992311/147683395-80eb1880-d993-4784-bb88-ca12d3791c7f.png)

### Dash
Left click your gun to dash forward (once every second).

### Kill Message
![image](https://user-images.githubusercontent.com/30992311/147683633-ee76adea-fe6f-4fc5-b09c-cc25b9dad69b.png)

### Scoreboard
![image](https://user-images.githubusercontent.com/30992311/147683718-85786885-fe3c-45d5-b179-4ff8949b09de.png)


### Reloading time
The reloading bar show the progression as well as the reloading time (0.85s).\
![image](https://user-images.githubusercontent.com/30992311/133885065-7e333c50-7e2e-4d61-bb21-de20064cfafd.png)

### Full game
The game time is endless.\
The first player who reach 25 point win the game.\
![image](https://user-images.githubusercontent.com/30992311/147683533-70661634-ab65-4c72-ba5e-91e5e6c768d5.png)

### Gun customisation
Let you customise your gun only 3 elements are customisable at the moment.\
![image](https://user-images.githubusercontent.com/30992311/147684238-7c603dca-6a21-488a-ae3f-4c42025faaf0.png)

#### Guns
Change your gun item.\
![image](https://user-images.githubusercontent.com/30992311/134068406-9a8f8187-1437-49f1-a681-9e161686491d.png)

#### Explosion shape
Change the shape of the explosion when you make a kill, this is all possible firework shape.\
![image](https://user-images.githubusercontent.com/30992311/134068543-6a72cef6-2668-47ae-9c84-2019f23d82c9.png)

#### Explosion color
Change the color of the explosion.\
![image](https://user-images.githubusercontent.com/30992311/134068636-73fa2077-f11b-462b-82aa-b545f2725985.png)

#### Kill sound
Change the sound played when you kill somone\
![image](https://user-images.githubusercontent.com/30992311/147684295-e009c9c6-43b4-40c9-a343-fba21f5d5fb2.png)

#### Name color
Change the color of your name everywhere (chat, scoreboard, tab, nametag)\
![image](https://user-images.githubusercontent.com/30992311/147684386-19cb0e8d-181a-479d-a0d6-32f3b2e95178.png)

### New Demo video
Coming soon.

### Unedited gameplay footage
https://www.youtube.com/watch?v=rEMI-Klku4Q

### Old Demo video
https://www.youtube.com/watch?v=-bWFTFtxhPQ
