# Blackjack










## Project Structure

```
app/src/main/java/com/example/cardgame/
├── Card.kt              # Card data model with Suit and Rank enums
├── Deck.kt              # Deck management (shuffle, draw, reset)
├── Hand.kt              # Hand logic (value calculation, bust/blackjack detection)
├── Blackjack.kt         # Core game logic and state management
├── GameState.kt         # Game state enums (BETTING, PLAYER_TURN, etc.)
├── MainActivity.kt      # Main menu activity
├── GameActivity.kt      # Game screen (in progress)
└── Gamemenu.kt          # Menu fragment
```

### Sketch

![alt text](<Screenshot - 2025-11-25 17.45.31.png>)



### Activity Flow

```
            run score card. 
        leaderboard. activity/fragment
├───main activity       simple menu with 2 options: new game and statistics
    └───gamemenu use to if continue or new game     
        └───game activity start of game 

```