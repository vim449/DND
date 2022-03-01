# Notes

So the general process for creating a character here would be to:

1. import books from the user
2. parse all books to find possible races
3. ask the user to choose a race, save that race, and keep track of the modifiers it provides (Depends on 2)
4. parse books for valid class options 
5. ask user for a class, store it, and record the hit dice, spellcasting features, etc, that class provides (prompt user for choices regarding starting proficiencies) (depends on 4)
6. parse and ask user for background
7. ask the user to choose a stat generation method (point buy, fixed array, 4d6 drop low, etc)
8. allow the user to allocate stats accordingly
8.5 hit dice rolls, height/weight rolls, etc (everything that requires a roll should happen here)
9. starting equipment (offer user a choice between equipment buy-in and class equipment)
10. allow user to fill-in remaining background bits (personality, alignment, bonds, ideals, flaws, etc) Tasha adds some tables for this kind of background stuff, maybe support it?
11. Determine how their class' spellcasting functions, and potentially ask user to chose spells (depends on 2-4)
12. Subclass?

Feel free to work on any of these. Just throw your username on a line to claim it, and no one else will work on it until you remove it. For now, keep these behaviors to mostly separate classes, and don't call anything in main(){} execpt for testing


If you have any other notes or project design choices you'd like to record, put them in here.
