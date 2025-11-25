<h1 align="center"> Hunger Games: Financial Survival Edition</h1>

<p align="center"><i>A Java Console Game Using OOP Concepts</i></p>

<p align="center">
  <b>IT-2108 Project</b><br>
  Created by: <br>
  Matanguihan, Bridget O. <br>
  Navarro, Precious Adelline V. <br>
  Sitjar, Cyril P. <br>
</p>

<hr>

<!-- Navigation Buttons -->
<p align="center">
  <a href="#overview"><kbd>OVERVIEW</kbd></a>
  <a href="#features"><kbd>FEATURES</kbd></a>
  <a href="#oop"><kbd>OOP CONCEPTS</kbd></a>
  <a href="#structure"><kbd>PROGRAM STRUCTURE</kbd></a>
  <a href="#run"><kbd>HOW TO RUN</kbd></a>
  <a href="#sample"><kbd>SAMPLE OUTPUT</kbd></a>
  <a href="#acknowledgement"><kbd>ACKNOWLEDGEMENT</kbd></a>
</p>

<hr>

<!-- OVERVIEW -->
<h2 id="overview">Overview</h2>

<p>
<b>Hunger Games</b> is a Java-based console adventure game where the player's goal is to survive 
until day 10. The player selects one of three characters — each with unique strengths and weaknesses — 
and faces daily challenges involving food choices, random events, risks, and resource management.
</p>

<ul>
  <li> 3 unique playable characters</li>
  <li> Start with Health and Endurance status based on characters chosen</li>
  <li> Goal: survive 10 days without hitting 0 Health/Endurance</li>
  <li> Randomized daily challenges</li>
  <li> Fully object-oriented game using inheritance & polymorphism</li>
</ul>

<hr>

<!-- FEATURES -->
<h2 id="features"> Features</h2>

<ul>
  <li><b>Three Character Classes:</b>
    <ul>
      <li><b>Warrior: (Strong, Durable | High STR (9), Low AGI (5), High HP (100)</li>
      <li><b>Ranger:  (Balanced, Agile | Balanced STR/AGI (6/8), Good END (90)</li>
      <li><b>Scout:   (Evasion Focus | Low STR (4), High AGI (9), High END (100)</li>
    </ul>
  </li>
  <li> Daily food decisions</li>
  <li> Random routes & danger events</li>
  <li> Polymorphic challenge handling</li>
  <li> Dynamic Health & Endurance loss or gain</li>
  <li> Win by completing 10 days</li>
</ul>

<hr>

<!-- OOP CONCEPTS -->
<h2 id="oop"> OOP Concepts Used</h2>

<h3>1. Encapsulation</h3>
<p>
Encapsulation ensures data integrity by controlling access to the internal state of objects. 

The vital statistics of a hero (name, health, endurance, strength, agility) are safeguarded by declaring them as private fields within the Hero class, preventing direct, unauthorized manipulation.

The program provides controlled access to this data through a set of public accessor (getter) methods (getHealth(), getAgility()) for reading values.

Modification is strictly handled by public mutator methods (changeHealth(), changeEndurance()), which contain built-in validation logic to clamp values (e.g., preventing health from dropping below zero or exceeding the maximum of 100).

</p>

<h3>2. Inheritance</h3>
<p>
The code establishes a rigid hierarchy using inheritance to promote code consistency and reuse.

The core player structure is defined by the Hero abstract superclass. All concrete player types—Warrior, Ranger, and Scout—explicitly extend this class, guaranteeing they share the same foundational attributes (e.g., health, endurance).

A set of consumable item classes (GlowingApple, AncientVial, etc.) are unified by implementing the Rechargeable interface. This forces them to adhere to a common behavioral contract, specifically the use(Hero hero) method.

Subclasses correctly utilize the super() keyword to delegate the initial setup of inherited fields, ensuring the hero's state is initialized according to the parent class's logic.

</p>

<h3>3. Polymorphism</h3>
<p>
Polymorphism allows the game to interact with diverse object types through a uniform interface, simplifying event handling.

The abstract fight(String enemy) method serves as a prime example of method overriding. Although called identically across all heroes, the runtime execution is dynamically dispatched to the unique implementation defined by the specific subclass (e.g., a Scout's agility-focused defense versus a Warrior's strength-based offense).

All items are handled polymorphically via the generic Rechargeable type. When the use(hero) method is invoked, the Java Virtual Machine dynamically selects and executes the specific item's effect (healing health, boosting endurance, etc.).

The overall battle system can call high-level methods on abstract or interface types (Hero, Rechargeable), reducing the need for extensive conditional checks against concrete class names.

</p>

<h3>4. Abstraction</h3>
<p>
Abstraction focuses on presenting only the essential functionality to the user while concealing the underlying complexities.

The Hero abstract class provides a conceptual definition of a player character, defining the mandatory methods (fight) that the game relies on, but hiding the specific combat algorithms within the subclasses.

The Rechargeable interface acts as an abstract contract, informing the main game logic what an item can do (use) without revealing how it achieves its specific effect.

Numerous utility functions (slowPrint, getStatusString, runCombatEvent) are used to hide complex, repetitive tasks—such as text formatting, screen alignment, and multi-step game logic—behind simple, high-level method calls, keeping the main game loop clean and readable.

</p>

<hr>

<!-- PROGRAM STRUCTURE -->
<h2 id="structure"> Program Structure</h2>

<pre>
FinancialHungerGame/
 ├── Main.java                                      # Application entry point, main loop, and configuration constants
 ├── game/
 │   ├── GameEvent.java                            # Encapsulates a day's challenge and type (COMBAT, ITEM_FIND_...)
 │   └── LogicHub.java                             # Static methods controlling main game flow (runDay, runCombatEvent, runItemFindEvent)
 ├── character/
 │   ├── Hero.java (abstract)                      # Abstract base class for players (stats, getters/setters, fight contract)
 │   └── classes/
 │       ├── Warrior.java                          # Concrete player class (high strength, low agility)
 │       ├── Ranger.java                           # Concrete player class (balanced stats)
 │       └── Scout.java                            # Concrete player class (high agility, low strength)
 ├── item/
 │   ├── Rechargeable.java (interface)             # Contract for consumable items (use, getItemName)
 │   └── consumables/
 │       ├── GlowingApple.java                     # Concrete item: Health restoration
 │       ├── ShadowyBerries.java                   # Concrete item: Endurance restoration
 │       ├── PaleRoot.java                         # Concrete item: Health/Endurance restoration (less effective)
 │       └── AncientVial.java                      # Concrete item: Major health and endurance restoration
 └── utility/
     ├── Scanner.java (usage)                      # Handles user input for choices
     ├── Random.java (usage)                       # Generates random numbers for events and outcomes
     └── DisplayHub.java                           # Contains static methods for I/O and styling (slowPrint, getStatusString)
</pre>

<hr>

<!-- HOW TO RUN -->
<h2 id="run"> How to Run</h2>

<p>
Step-by-step to run the program: <br>
   Save the entire code in a file named Main.java(same file). <br>
   Open the file on any available java programming language compiler. <br>
   If the user runs the file on VSC(Visual Studio Code), make sure that all java extensions needed are downloaded. <br>
<br>
Step-by-step to play the game: <br>
   Upon starting the program, it will display a question where it asks the user's chosen name to act as the hero’s name. <br>
   After that they will choose a ‘Role’ that will serve as their character all throughout the journey.  <br>
   Then they will start their adventure and  then encounter the challenges. <br>

</p>

<hr>

<!-- SAMPLE OUTPUT -->
<h2 id="sample"> Sample Output</h2>

<pre>
============================================================
                     HUNGER GAMES
============================================================

Please enter your hero’s name: Cy

Hello, Cy! Choose your Role:
     [1] Warrior: (Strong, Durable | High STR (9), Low AGI (5), High HP (100)
     [2] Ranger:  (Balanced, Agile | Balanced STR/AGI (6/8), Good END (90)
     [3] Scout:   (Evasion Focus | Low STR (4), High AGI (9), High END (100)
> Enter your choice   (1-3): 1

Cy, the Warrior, begins the Adventure!

======================== DAY 1: NEW CHALLENGE ========================
                             << STATUS >>
  > Health: 100/100  |  Endurance: 70/100
  > STR: 9  |  AGI: 5
----------------------------------------------------------------------------------------------------------------------------------
--- CHALLENGE: You stumble upon a cache of abandoned provisions, perfectly preserved. Take only what you need.
----------------------------------------------------------------------------------------------------------------------------------
You examine the selection of consumables. Choose wisely:
    [1] Consume the Glowing Apple
    [2] Consume the Shadowy Berries
    [3] Consume the Pale Root
    [4] Ignore all items (Risk minimal, but gain nothing)
</pre>

<hr>

<!-- ACKNOWLEDGEMENT -->
<h2 id="acknowledgment"> Acknowledgement</h2>

<table>
  <tr>
    <th> </th>
    <th>Member</th>
    <th>Role</th>
  </tr>
  <tr>
    <td>img</td>
    <td>Matanguihan, Bridget O.</td>
    <td>Developer, Designer and Tester</td>
  </tr>
  <tr>
    <td>img</td>
    <td>Navarro, Precious Adelline V.</td>
    <td>Developer, Designer and Tester</td>
  </tr>
  <tr>
    <td>img</td>
    <td>Sitjar, Cyril P.</td>
    <td>Developer, Designer and Tester</td>
  </tr>
</table>

<p>
FUTURE ENHANCEMENT

 Addition of graphical user interface for a better game experience. <br>
 In terms of  challenges,the future developer could add more to it for long game experience. <br>
 The rules can be modified. <br>
<p>
<hr>

<p align="center"><i>End of Documentation</i></p>
