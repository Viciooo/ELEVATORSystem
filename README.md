# LIFTsystem

### Zaprojektuj i zaimplementuj system kontroli wind w budynku. Twój system powinien być w stanie obsłużyć do 16 wind i umożliwiać:

- Obsługę przywołań windy

- Uaktualnienie stanu windy

- Wykonanie kroku symulacji

- Sprawdzenie stanu systemu (na którym piętrze windy są i do którego zmierzają)

### Przykładowy interfejs mógłby wyglądać następująco (pseudokod):

    ElevatorSystem {

       def pickup(Int, Int)

       def update(Int, Int, Int)

       def step()

       def status(): [(Int, Int, Int), (Int, Int, Int), ...] 

    }

- W tym przykładzie stan windy (metoda: status()) opisuje trójka: (ID windy, obecny numer piętra, docelowy numer piętra), a sama metoda powinna zwracać kolekcje takich trójek.

- Aktualizacja (update(...)) zmienia te liczby dla jednej z wind. 
- Zgłoszenie (pickup(...)) do windy to dwie liczby: piętro zgłoszenia oraz kierunek (ujemny oznacza dół, dodatni oznacza górę). 
- step() wykonuje jeden krok symulacji.

Przykładowy interfejs może wymagać poprawy i pozostawia wiele pytań otwartych np. co w przypadku, jeśli windą jedzie więcej osób i mają różne piętra docelowe? Kiedy i jak dokonujemy wyboru piętra? Możesz go dowolnie zmodyfikować.

Najprostsze rozwiązanie to FCFS (first-come, first-serve) wg kolejności zgłoszeń - ale być może można to zrobić lepiej?