### staminamod-template-1.19.2
## Minecraft Stamina Mod
# Introduction
Welcome to the Stamina Mod for Minecraft!

# How It Works
The Stamina Mod adds a new stamina bar with abilities and posture bar. 

# Directory Structure
To modify a mob's posture, navigate to the following directory in the data pack:


`data/staminamod/entityposture`
Inside this directory, you will put your JSON files for each mob.

# Modifying Mob Posture and Adding New Mobs
Each mob has its own JSON file, structured as follows:
To add or modify a new mob's posture setting, create a new JSON file in the entityposture directory.

```json
{
  "name": "modid:<mob_name>",
  "posture": <value>
}

posture: This numerical value represents the mob's posture. You can adjust this value to change how the mod interprets the mob's posture.





Use the above JSON structure to define the mob's name and posture.
Examples
Here is an example of what the JSON files might look like:

Zombie


```json
{
  "name": "minecraft:zombie",
  "posture": 21
}
