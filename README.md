# ELEVATORSystem

#### Moje założenie:

Windy stoją obok siebie i są podłączone do jednej pary przycisków do sterowania (dół, góra) na każdym piętrze

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

- W tym przykładzie stan windy (metoda: status()) opisuje trójka: (ID windy, obecny numer piętra, docelowy numer piętra)
  , a sama metoda powinna zwracać kolekcje takich trójek.

- Aktualizacja (update(...)) zmienia te liczby dla jednej z wind.
- Zgłoszenie (pickup(...)) do windy to dwie liczby: piętro zgłoszenia oraz kierunek (ujemny oznacza dół, dodatni oznacza
  górę).
- step() wykonuje jeden krok symulacji.

Przykładowy interfejs może wymagać poprawy i pozostawia wiele pytań otwartych np. co w przypadku, jeśli windą jedzie
więcej osób i mają różne piętra docelowe? Kiedy i jak dokonujemy wyboru piętra? Możesz go dowolnie zmodyfikować.

Najprostsze rozwiązanie to FCFS (first-come, first-serve) wg kolejności zgłoszeń - ale być może można to zrobić lepiej?

## Mój algorytm:

### Która winda dostanie zlecenie odbioru (pickup) ?

Winda, która jest wolna lub jej kierunek jazdy jest zgodny ze zleceniem i jest najbliżej

### Jak jeździ winda ?

Można podzielić to na kilka przypadków I. winda jest NIŻEJ niż piętro odbioru gdzie kierunek to UP

- pojedzie do góry zgarniając wszystkie zlecenia jazdy w górę po drodze (oczywiście, jeśli jest najbardziej opłacalna).
  - kierunek jazdy windy to UP II. winda jest WYŻEJ niż piętro odbioru gdzie kierunek to UP
- zjedzie w dół zatrzymując się na piętrze, z którego przyszło zlecenie, a następnie mamy przypadek I. - kierunek jazdy
  windy to UP III. winda jest NIŻEJ niż piętro odbioru gdzie kierunek to DOWN
- jedzie do góry zatrzymując się na najwyższym dodanym piętrze (nie decyduje tu kolejność zgłoszeń), a potem zjeżdża w
  dół zgarniając rozkazy jazdy w dół, dla których jest najbardziej opłacalna. - kierunek jazdy windy to DOWN IV. winda
  jest WYŻEJ niż piętro odbioru gdzie kierunek to DOWN
- jedzie w dół zgarniając po drodze wszystkie zlecenia, które jadą w dół i ta winda jest dla nich najbardziej opłacalna.
  - kierunek jazdy windy to DOWN

### Kierunek jazdy, dlaczego tak działa ?

ElevatorDirection nie mówi o tym, czy winda teraz fizycznie jedzie w górę, czy w dół tylko w jakiej "fazie ruchu jest".

- Winda będąca w fazie ruchu "UP" jest potencjalnym kandydatem na najlepszą windę dla zleceń, których kierunek to "UP"
  czyli chcących jechać w górę.
- Analogicznie dla kierunku "DOWN".
- Kierunek "NONE" oznacza, że winda stoi bezczynnie. W skrócie:
- dla zlecenia użytkownika (ElevatorOrder) kierunek jazdy (ElevatorDirection) to "chęć" poruszenia się w danym kierunku
  w stosunku do obecnej pozycji w budynku.
- dla windy to "faza ruchu". Winda musi ukończyć jedną fazę ruchu (wykonać wszystkie zlecenia dla danej fazy), żeby
  przejść do następnej

### Kiedy winda jest najkorzystniejsza dla zlecenia odbioru?

Gdy jej odległość bezwzględna liczona w piętrach od pozycji użytkownika jest najmniejsza (spośród innych wind) i
kierunki jazdy (ElevatorDirection) użytkownika i windy są zgodne lub winda jest bezczynna (kierunek windy to "NONE").

### Co jak użytkownik zamówi windę, a nie wejdzie?

Drzwi się otwierają, ale winda nie dostaje zlecenia i zamyka drzwi (odpowiada to w symulacji wpisanie "SKIP" po
komunikacie o otwarciu drzwi)

### Jak działa symulacja?

Przyjąłem konwencję, że można wprowadzić tylko 1 żądanie na 1 turę. Tura to ruch wind o 1 piętro lub brak ruchu, jeśli
nie mają zleceń. Jest to pewne uproszczeniem, którego celem jest ułatwienie testowania.

Zlecenia dla systemu wind wprowadzane są w formacie, który zaprezentuję na przykładzie:

- D10 - takie polecenie oznacza wyrażenie chęci jazdy w dół (dlatego D jak "DOWN") z piętra numer 10. Odpowiada to
  sytuacji, gdy w budynku, ktoś na 10 piętrze wcisnął strzałkę w dół.
- U3 - ktoś chce jechać w górę (dlatego U jak "UP") z piętra numer 3.
- SKIP - podczas tury winda nie dostała żadnego zlecenia i chcemy po prostu wykonać krok symulacji bez dodawania
  zlecenia.
- SHOW-ALL - działa jak SKIP tylko dodatkowo wywołuje funkcję, która pokazuje co się dzieje we wszystkich windach (ich
  pozycję, identyfikator, zlecenia (typu ElevatorOrder) i cel podróży (finalDestination)), Gdy drzwi windy się otwierają
  należy wprowadzić numer piętra lub SKIP.

### Dlaczego Java?

Kocham paradygmat obiektowy i lubię pisać w Javie. Jeśli, rzeczywiście ktoś chciałby sterować windami używając tego
algorytmu, ja przepisałbym go na C albo Asemblera (ale, aż tak odważny jeszcze nie jestem). Poza tym traktuję ten
projekt jako ćwiczenie umiejętności pisania w Javie i przypomnienie jak się pisze algorytmy, bo nie robiłem tego od pół
roku :)

### Plik .jar do testowania

W folderze out/artifacts/E
Oczywiście możesz też testować w swoim ulubionym IDE ;)

### Masz jakieś inne pytania?

Jeśli tak, to napisz na gicie Issue lub napisz do mnie na maila. Z chęcią odpowiem na pytania i wprowadzę poprawki :)

#################################

Piotr Witek @Viciooo
                                
#################################