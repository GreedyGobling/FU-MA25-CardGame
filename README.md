# Blackjack

An Android card game implementation.

failed to implement resume game.

## Showcase
![blackjackshow.gif](blackjackshow.gif)
<p align="center" >
<img alt="gameplayDemo" src="![blackjackshow.gif](blackjackshow.gif)" width="300" />
</p>


## Project Structure

```
app/src/main/java/com/example/cardgame/
├── BlackJack.kt         # Core game logic and state management
├── Card.kt              # Card data model with Suit and Rank enums
├── Deck.kt              # Deck management (shuffle, draw, reset)
├── GameActivity.kt      # Main game screen activity
├── GameResult.kt        # Enum for game results (WIN, LOSS, PUSH, etc.)
├── GameState.kt         # Game state enums (BETTING, PLAYER_TURN, etc.)
├── GameStats.kt         # Statistics management and persistence
├── Hand.kt              # Hand logic (value calculation, bust/blackjack detection)
├── MainActivity.kt      # Main menu entry point
├── StatisticsActivity.kt # Activity to display game statistics
└── fragment/
    ├── GameMenuFragment.kt   # Dialog fragment for resuming/starting new games
    └── GameResultFragment.kt # Dialog fragment for showing game results
```

### Sketch

![alt text](<Screenshot - 2025-11-25 17.45.31.png>)

### Activity Flow

```
MainActivity (Entry Point)
├── Start Game
│   ├── (Streak > 0) -> GameMenuFragment (Dialog)
│   │   ├── Continue -> GameActivity (Loads saved state)
│   │   └── New Game -> GameActivity (Starts fresh)
│   └── (Streak <= 0) -> GameActivity (Starts fresh)
│
└── Statistics
    └── StatisticsActivity
```

**Navigation Details:**
- **MainActivity**: Entry point with options to Start Game or view Statistics.
- **GameMenuFragment**: Appears if there is an active streak/saved game, offering to continue or start over.
- **GameActivity**: The main game screen where Blackjack is played.
  - Handles game logic, UI updates, and state saving.
  - Shows **GameResultFragment** at the end of a round.
- **StatisticsActivity**: Displays player statistics (wins, losses, streaks).