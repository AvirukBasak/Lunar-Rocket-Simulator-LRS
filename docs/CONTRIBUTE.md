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

Each entity is accessed by a function.

#### Entity
A wrapper class that combines sprites with position on the screen.

#### Environments
This class contains wrapped versions of `Environment`s.

Each environment is accessed by a function, which loads that environment onto the passed canvas.

#### Environment
An `Environment` is essentially the background of an entity.

An environment wraps the background image and its position into a single class. In addition, it maintains a list of entities that exist in it.

An environment as 3 points of view, set by using the `Environment.Pov` enum.

- `FOCUSSED_POV` - The entity in focus is kept static and everything else moves with the background.
- `ENTITY_POV` - All entities remain static and the backgound moves.
- `ENVIRONMENT_POV` - The background is fixed and entities move wrt it.

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

#### Flushing
An `Entity` maintains a list of children entities to which it is connected to.

An `Environment` maintains a lsit of `Entiti`es that it contains.

Both classes come with a `flush()` method that flushes resources from itself and its connected/contained entities.
