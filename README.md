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
MainActivity (Entry Point)
├── New Game
│   └── GameMenuFragment
│       ├── Continue Game (if saved state exists)
│       └── New Game
│           └── GameActivity
│               ├── Game Screen
│               ├── Player Hand Display
│               ├── Dealer Hand Display
│               └── Game Controls (Hit, Stand, etc.)
│
└── Statistics
    └── LeaderboardActivity/Fragment
        ├── Score History
        ├── Win/Loss Statistics
        └── Best Scores
```

**Navigation Details:**
- **MainActivity**: Simple menu with two primary options
  - New Game → Launches game flow
  - Statistics → View leaderboard and game history
- **GameMenuFragment**: Checks for saved game state
  - Offers "Continue" if previous game exists
  - Offers "New Game" to start fresh
- **GameActivity**: Main game screen with Blackjack gameplay
- **LeaderboardActivity**: Displays player statistics and score tracking