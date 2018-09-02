# package-challenge

## How to Run
* Open command prompt/terminal in project folder
* Run `mvn clean install` command. A /target folder will be created after conclude
* Access the /target folder and run `java -jar package-challenge-1.0-SNAPSHOT.jar <your-file-path>`
* Result must be showed to you after this process

# Comments:
I really avoid comments unless it is REALLY necessary. A clean code should not need that except by very strict cases (like very complex lambdas, regex and etc), so you will see that I really tried to avoid commenting my source code.

## Strategy:
Since from beginning I could observe some facts in the file like the presence of entities and that they should be parsed.
So, I decided to map the entities before to have an idea about how could I handle them and try to create some regex to extract them easily.
Once we had the entities and only some of their fields were interesting for our results, I could just override the `toString()`.

## Algorithm - Combination:
Combination algorithm basically does what we need: try all combinations of elements following some specific rule.

## Data Structure - Set, Array and Matrix
We don't have repeated items in our combinations, so I believe that a `Set` fits well with picked algorithm.
For the algorithm itself I used an array of arrays (`matrix`) to store the items values and `arrays` to iterate them.

## Design Pattern - Flyweight
I already have many fine grained objects (items with their own specific information) which will operate individually alone, but I want them together to operate as a Pack, in this case, so I considered it the ideal structural pattern.


