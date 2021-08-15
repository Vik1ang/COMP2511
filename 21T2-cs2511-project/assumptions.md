# Assumptions

1. Assume that the map size is `8 (width) * 17 (height)`  tiles. 1 tiles == 32 pixel

2. Slug: 
    +   battle radius: `2 tile` (direct distance, center to center, i.e. 64 pixel, include edge)
    +   support radius: `3 tile` (direct distance, center to center, i.e. 96 pixel, include edge)
    +   health: `20 points` total
    +   bite: `10 points` lost
    +   defeat: gain `100 experiences`
    +   award: card 20%, gold 30%, healthPotion 10%, item 10%, rare 2%
    +   spawn: randomly, maximum of 2 in total


3. Zombie: 
    +   battle radius: `3 tile` (direct distance, center to center, i.e. 96 pixel, include edge)
    +   support radius: `5 tile` (direct distance, center to center, i.e. 160 pixel, include edge)
    +   health: `20 points` total
    +   bite: `30 points` lost (30% transfer, no damage if transfer)
    +   defeat: gain `500 experience`
    +   award: card 30%, gold 40%, healthPotion 0%, item 20%, rare 3%
    +   spawn: (< 5 cycles)each cycles from zombie pit, (> 5 cycles)each even cycles from zombie pit
    

4.  Vampire: 
    +   battle radius: `5 tile` (direct distance, center to center, i.e. 160 pixel, include edge)
    +   support radius: `6 tile` (direct distance, center to center, i.e. 192 pixel, include edge)
    +   health: `50 points` total
    +   bite: `40 points` lost (30% critical bite, variance between 0 - 30 points)
    +   defeat: gain `1000 experience`
    +   award: card 50%, gold 50%, healthPotion 0%, item 30%, rare 3%
    +   spawn: each round from vampire castle

    
5.  Doggie: 
    +   battle radius: `2 tile` (direct distance, center to center, i.e. 64 pixel, include edge)
    +   support radius: `3 tile` (direct distance, center to center, i.e. 96 pixel, include edge)
    +   health: `80 points` total
    +   bite: `15 points` lost (10% stune character for 1 round)
    +   defeat: gain `1500 experience`
    +   award: card 30%, gold 10%, healthPotion 10%, item 30%, rare 4%, doggieCoins 100%
    +   spawn: after 20 rounds, each 5 rounds at random pathtile


6.  ElanMaskue(only one in the game): 
    +   battle radius: `2 tile` (direct distance, center to center, i.e. 64 pixel, include edge)
    +   support radius: `3 tile` (direct distance, center to center, i.e. 96 pixel, include edge)
    +   health: `60 points` total
    +   bite: `50 points` lost
    +   defeat: gain `1500 experience`
    +   award: card 50%, gold 30%, healthPotion 10%, item 50%, rare 5%
    +   spawn: after 40 rounds and reached 10000 experience, at random pathtile (ElanMaskue will not encounter the character for the first 5 round after spawn, and have random possibility to encounter the character afterwards)


7. EXTRA- SlugZombie: SlugZombie looks similar as Zombie. It has 5 slugs on his body, which decrease the damage and the transfer rate (SlugZombie can't see everything clearly). However, when SlugZombie dead, it will preduce 5 Slugs and those Slugs join the battle directly
    +   battle radius: `3 tile` (direct distance, center to center, i.e. 96 pixel, include edge)
    +   support radius: `5 tile` (direct distance, center to center, i.e. 160 pixel, include edge)
    +   health: `20 points` total
    +   bite: `20 points` lost (20% transfer, no damage if transfer)
    +   defeat: gain `500 experience`
    +   award: card 40%, gold 40%, healthPotion 0%, item 20%, rare 3%
    +   spawn: (> 5 cycles)each odd cycles from zombie pit



![](https://gitlab.cse.unsw.edu.au/COMP2511/21T2/project-groups/T18A_DORITO/21T2-cs2511-project/-/blob/master/src/images/slugzombie.png)


8.  Assume that the battle order
    + character hit before enemy bite
    + `low-damaged enemy` -> `high-damaged enemy`
    + `old enemy` -> `new enemy`
    + `allied soldiers` will fight first. 


9. Assume that the capacity of items is `15`, extra card is decomposed into` 1 gold` and `100 experiences` and 50% chance for `1 random equipment`. 

10. Assume that the capacity of cards is `10`, every extra item is decomposed into` 1 gold` and `50 experiences`. 

11. Items: 

   |     Item      |    damage           | sell | buy  |
   | :-----------: | :-----------------: | :--: | :--: |
   |    Armour     |      /2             |  2   |  20  |
   |    Shield     |     *0.9            |  1   |  10  |
   |    Helmet     |  -3(-3 attack)      |  1   |  5   |
   |     Stake     |  3(10 vampire)      |  1   |  10  |
   |     Sword     |        4            |  1   |  15  |
   |     Staff     |  3(20% trace)       |  2   |  15  |
   | Health potion |  (+20 health)       |  2   |  5   |
   |    Anduril    | 5 or 15(bosses)     |  5   |  N/A |
   |    Tree Stump | *0.8 or *0.6(bosses)|  5   |  N/A |
   |  The One Ring | (+100 health) can only use if not equipped|  5  |  N/A |
   |  doggieCoins  |         N/A         |  before boss exist: 10 +- 6 |  N/A |
   |               | price variance each clock tick|  after boss exist: 100 +- 6 |      |
   |               |                     |  after boss dead: 1 +- 6    |      |


12. Assume that each hit from character cause `15 points` damage. 

13. health points for allied soldier： `20 points`

14. each hit from allied soldier cause ： `5 points` damage

15. Buildings: 

   |   Buildings   |          assumption           | radius |
   | :-----------: | :----------------------------:| :----: |
   |    Village    | regains `2  points` of health |`2 tile`(direct distance, center to center, i.e. 64 pixel, include edge)|
   |    CampFire   |                               |`2 tile`(direct distance, center to center, i.e. 64 pixel, include edge)|
   |     Trap      |                               |`1 tile`(direct distance, center to center, i.e. 32 pixel, include edge)|
   |     Tower     |  attack power is `5 points`   |`1 tile`(direct distance, center to center, i.e. 32 pixel, include edge)|
   |  ZombiePit    |first 5 cycle: `1 Zombie` each cycle, after 5 cycle: `Zombie` for even cycle, `SlugZombie` for odd cycle|   N/A  |
   | VampireCastle |`1 vampires` every five cycles |   N/A  |

16. Win condition (achieve all): 
    +  achieve all conditions provided in world_with_twists_and_turns.json
    +  kill the boss
