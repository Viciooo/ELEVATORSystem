# ELEVATORSystem

### Zaprojektuj i zaimplementuj system kontroli wind w budynku. Twój system powinien być w stanie obsłużyć do 16 wind i umożliwiać:
#### Moje założenie:
- windy stoją obok siebie
    - jak nie stoją obok siebie to możemy zrobić z nich 2 niezależne systemy
- windy podłączone są do jednej pary przycisków (góra,dół) na każdym z pięter


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

## Mój algorytm:
Winda najpierw jedzie w jednym kierunku do ostatniego zlecenia w tą stronę np:
- jeśli zamówisz windę na 3 piętrze do jazdy w dół a ona ma jechać na 5 piętro i potem otrzyma zlecenie, że ktoś z 4 piętra też jedzie w dół jak ty,
to otworzy mu drzwi a potem zjedzie na trzecie i otworzy tobie
- wyjątkiem jest sytuacja gdy w czasie wyboru przez tą osobę 4 piętra inna winda będzie dla niego lepsza

Winda jest najlepszą dla użytkownika jeśli jest najbliżej niego.
Użytkownikowi raz przypisanemu do windy nie zmieniamy już nigdy windy - nie jest wykonywana rekalkulacja najlepszej windy na każdym kroku
- wady:
  - gorzej wybierane są najlepsze windy => cały system jest gorszy 
- zalety:
  - złożoność obliczeniowa jak i pamięć są oszczędzane otrzymując mnie więcej okej rozwiązanie(przy 16 windach i np. 30 piętrach taki system byłby bardzo wymagający)
  - użytkownicy w windzie nie dostają epilepsji od zmieniających się podświetleń przycisków windy
