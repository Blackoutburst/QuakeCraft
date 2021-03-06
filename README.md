![splash](https://user-images.githubusercontent.com/30992311/133886844-88899994-fcee-4850-af65-3d9aab4726cb.png)

[![License](https://img.shields.io/github/license/Blackoutburst/QuakeCraft.svg)](LICENSE)
[![Release](https://img.shields.io/github/release/Blackoutburst/QuakeCraft.svg)](https://github.com/Blackoutburst/QuakeCraft/releases)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/a0e7c7d8e9b44594b5c8469ac6be0f21)](https://www.codacy.com/gh/Blackoutburst/QuakeCraft/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Blackoutburst/QuakeCraft&amp;utm_campaign=Badge_Grade)
[<img src="https://img.shields.io/badge/Webpage-QuakeCraft-blueviolet">](https://www.blackoutburst.com/quake.html)

# QuakeCraft
This plugin re-creates the base game of QuakeCraft from Hypixel for learning purposes, extra features got added due to community demands.

Note this plugin is designed to have multiples map in different folder, test were effectued with [Multiverse-Core](https://dev.bukkit.org/projects/multiverse-core)

And this plugin is made for spigot / paper 1.8.8 (V1_8_R3)

## Information
To create spawn point I recommend you to only use the spawnwand, scan command still exist if you want to place a massive number of spawn but it's not something I recomand you.

## Permission
`quake.admin` Is necessary for every command

## Commands
`reload` Reload the configuration\
`setQuakeSpawn` Set the quake lobby spawnpoint (main world outside games)\
`leaveQueue` Remove you from the queue\
`joinQueue` Add you to the queue\
`queue` Toggle your queue status\
`listmap` List maps and number of spawns available\
`start <world name>` Start a game\
`end` End a game\
`triggerSpeed <value>` Set the trigger speed\
`gravity <value>` Set the gravity strength (for the beam not the players)\
`maxScore <value>` Set the score goal to end a game\
`dashDelay <value>` Set the Dash delay\
`playerSpeed <value>` Set the player movement speed\
`playerJump <value>` Set the player jump strength\
`playerSlow <value>` Set the player slowness\
`bounceCount <value>` Set the number of bounces a beam can do\
`rayLength <value>` Set the length of the beam\
`shatterCount <value>` Set the number of sub beam created on impact\
`shatterLength <value>` Set the length of the sub beam\
`toggleBlindness` Toggle blindness effect\
`toggleInvisibility` Toggle Invisibility effect\
`toggleNametag` Toggle nametag visibility\
`toggleDash` Toggle dash usage\
`toggleVerticalDash` Toggle the ability to dash on the Y axys\
`dashStrength <value>` Set the dash strength\
`toggleWalk` Toggle the ability to walk\
`toggleJump` Toggle the ability to jump\
`resetConfig` Reset the game configuration to default value\
`showConfig` Show the current game configuration\
`config` Open the configuration menu\
`play` Open the map selector (unavailable on third party server sorry)\
`scan <x1> <y1> <z1> <x2> <y2> <z2>` Scan the world for sponge block and created a spawnpoints files with them\
`loadSpawn <world name>` Load a world spawnpoint\
`clean <world name>` Removed sponge block on every spawnpoint saved\
`spawnWand` Give you the spawnwand\
`showSpawn <world name>` Place sponge block on spawnpoints\

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

### Launch Pad
The two type are here, blue and red\
Blue pad launch the player in the sky while red launch the player in the direction they are facing\
![2022-01-11_16 40 25](https://user-images.githubusercontent.com/30992311/148974020-e89d8ea3-f5b8-4f2f-9a2c-036b9830e9a5.png)

### Configuration menu
In this menu you can pretty much change everything and create custom quake game\
![image](https://user-images.githubusercontent.com/30992311/148975116-a890f694-b668-49ff-b170-88fad846d049.png)

### Map Selector
Display official Hypixel map in gold and custom maps in blue and the number of spawnpoints available on the map\
![unknown](https://user-images.githubusercontent.com/30992311/149159890-39a1b2f7-624e-4d3f-80e2-ccfa0769d19b.png)


### New Demo video
Coming soon.

### Unedited gameplay footage
https://www.youtube.com/watch?v=rEMI-Klku4Q

### Old Demo video
https://www.youtube.com/watch?v=-bWFTFtxhPQ
