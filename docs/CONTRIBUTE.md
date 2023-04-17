## Contributor's Guide

### List of classes

#### Activities
Enum of all possible activities in the game, in other words, various game screens or events.

#### AssetsImg
Has an `initiate()` function which is called by Entities.

This class has `BufferedImage` objects for every single sprite loaded into the game.

#### Entities
This class takes `BufferedImage` objects from `AssetsImg` and wraps them in an `Entity` object.

The `Entity` type combines the sprite of an entity with its position, making it easier to handle it as an object on the screen.

#### Entity
A wrapper class that combines sprites with position on the screen.

#### AssetsVars
Contains `static` constants and variables which hold states in the game.

#### Console
Takes input and produces output on the screen.

#### GUI
The class that builds the window and other UI elememts.

#### Main
Contains the `main` method.

#### Other classes
- SpriteSheet - Image processing
- ImageLoader - Load images
