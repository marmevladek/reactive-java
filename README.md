# Лабораторная работа №2

## Задание 1

В один из методов, использовавшийся для сбора статистики, добавить возможность задать задержку, имитирующую задержку получения результата, например из базы данных. К примеру, был метод getName(), в который нужно добавить параметр getName(long delay)

## Задание 2

Заменить последовательный стрим, собирающий статистику из лабораторной №1, на параллельный. Поменять итоговую коллекцию, где собирается результат, на соответствующую потокобезопасную. Измерить производительность для разного количества элементов с дополнительной задержкой и без задержки. Для случаев с задержкой и без найти количество элементов, при котором сбор статистики последовательным и параллельным стримами дают одинаковую скорость выполнения. 

---

- Sequential - последовательный
- Parallel - параллельный

### БЕЗ ЗАДЕЖКИ
#### Первый запуск
Размер коллекции: 5000
```
Sequential Stream API : 11 мс. Задержка: 0
Parallel Stream API: 12 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 9 мс. Задержка: 0
Parallel Stream API: 21 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 17 мс. Задержка: 0
Parallel Stream API: 22 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 23 мс. Задержка: 0
Parallel Stream API: 27 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 31 мс. Задержка: 0
Parallel Stream API: 30 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 55 мс. Задержка: 0
Parallel Stream API: 43 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 96 мс. Задержка: 0
Parallel Stream API: 54 мс. Задержка: 0
```
#### Второй запуск
Размер коллекции: 5000
```
Sequential Stream API : 2 мс. Задержка: 0
Parallel Stream API: 3 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 7 мс. Задержка: 0
Parallel Stream API: 5 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 15 мс. Задержка: 0
Parallel Stream API: 9 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 23 мс. Задержка: 0
Parallel Stream API: 13 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 30 мс. Задержка: 0
Parallel Stream API: 54 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 47 мс. Задержка: 0
Parallel Stream API: 29 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 104 мс. Задержка: 0
Parallel Stream API: 46 мс. Задержка: 0
```
#### Третий запуск
Размер коллекции: 5000
```
Sequential Stream API : 2 мс. Задержка: 0
Parallel Stream API: 1 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 8 мс. Задержка: 0
Parallel Stream API: 6 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 15 мс. Задержка: 0
Parallel Stream API: 16 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 23 мс. Задержка: 0
Parallel Stream API: 30 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 31 мс. Задержка: 0
Parallel Stream API: 26 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 48 мс. Задержка: 0
Parallel Stream API: 45 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 105 мс. Задержка: 0
Parallel Stream API: 73 мс. Задержка: 0
```
#### Четвертый запуск
Размер коллекции: 5000
```
Sequential Stream API : 2 мс. Задержка: 0
Parallel Stream API: 2 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 8 мс. Задержка: 0
Parallel Stream API: 6 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 17 мс. Задержка: 0
Parallel Stream API: 15 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 22 мс. Задержка: 0
Parallel Stream API: 23 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 30 мс. Задержка: 0
Parallel Stream API: 31 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 48 мс. Задержка: 0
Parallel Stream API: 38 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 98 мс. Задержка: 0
Parallel Stream API: 75 мс. Задержка: 0
```
#### Пятый запуск
Размер коллекции: 5000
```
Sequential Stream API : 2 мс. Задержка: 0
Parallel Stream API: 3 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 8 мс. Задержка: 0
Parallel Stream API: 8 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 15 мс. Задержка: 0
Parallel Stream API: 17 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 22 мс. Задержка: 0
Parallel Stream API: 19 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 30 мс. Задержка: 0
Parallel Stream API: 30 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 48 мс. Задержка: 0
Parallel Stream API: 42 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 95 мс. Задержка: 0
Parallel Stream API: 86 мс. Задержка: 0
```
#### Шестой запуск
Размер коллекции: 5000
```
Sequential Stream API : 2 мс. Задержка: 0
Parallel Stream API: 2 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 8 мс. Задержка: 0
Parallel Stream API: 7 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 15 мс. Задержка: 0
Parallel Stream API: 15 мс. Задержка: 0

```
Размер коллекции: 75000
```
Sequential Stream API : 23 мс. Задержка: 0
Parallel Stream API: 22 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 30 мс. Задержка: 0
Parallel Stream API: 31 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 47 мс. Задержка: 0
Parallel Stream API: 52 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 133 мс. Задержка: 0
Parallel Stream API: 176 мс. Задержка: 0
```
#### Седьмой запуск
Размер коллекции: 5000
```
Sequential Stream API : 2 мс. Задержка: 0
Parallel Stream API: 3 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 8 мс. Задержка: 0
Parallel Stream API: 4 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 14 мс. Задержка: 0
Parallel Stream API: 8 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 23 мс. Задержка: 0
Parallel Stream API: 17 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 39 мс. Задержка: 0
Parallel Stream API: 19 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 51 мс. Задержка: 0
Parallel Stream API: 37 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 121 мс. Задержка: 0
Parallel Stream API: 109 мс. Задержка: 0
```
#### Восьмой запуск
Размер коллекции: 5000
```
Sequential Stream API : 2 мс. Задержка: 0
Parallel Stream API: 1 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 8 мс. Задержка: 0
Parallel Stream API: 4 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 16 мс. Задержка: 0
Parallel Stream API: 11 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 23 мс. Задержка: 0
Parallel Stream API: 44 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 30 мс. Задержка: 0
Parallel Stream API: 19 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 48 мс. Задержка: 0
Parallel Stream API: 26 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 94 мс. Задержка: 0
Parallel Stream API: 82 мс. Задержка: 0
```
#### Девятый запуск
Размер коллекции: 5000
```
Sequential Stream API : 2 мс. Задержка: 0
Parallel Stream API: 2 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 12 мс. Задержка: 0
Parallel Stream API: 5 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 14 мс. Задержка: 0
Parallel Stream API: 8 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 22 мс. Задержка: 0
Parallel Stream API: 13 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 30 мс. Задержка: 0
Parallel Stream API: 16 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 50 мс. Задержка: 0
Parallel Stream API: 25 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 102 мс. Задержка: 0
Parallel Stream API: 43 мс. Задержка: 0
```
#### Десятый запуск
Размер коллекции: 5000
```
Sequential Stream API : 3 мс. Задержка: 0
Parallel Stream API: 1 мс. Задержка: 0
```
Размер коллекции: 25000
```
Sequential Stream API : 8 мс. Задержка: 0
Parallel Stream API: 4 мс. Задержка: 0
```
Размер коллекции: 50000
```
Sequential Stream API : 15 мс. Задержка: 0
Parallel Stream API: 8 мс. Задержка: 0
```
Размер коллекции: 75000
```
Sequential Stream API : 23 мс. Задержка: 0
Parallel Stream API: 10 мс. Задержка: 0
```
Размер коллекции: 100000
```
Sequential Stream API : 32 мс. Задержка: 0
Parallel Stream API: 17 мс. Задержка: 0
```
Размер коллекции: 150000
```
Sequential Stream API : 48 мс. Задержка: 0
Parallel Stream API: 23 мс. Задержка: 0
```
Размер коллекции: 250000
```
Sequential Stream API : 93 мс. Задержка: 0
Parallel Stream API: 41 мс. Задержка: 0
```

--- 


### ЗАДЕРЖКА: 5 МС

#### Первый запуск
Размер коллекции: 5000
```
Sequential Stream API : 6412 мс. Задержка: 1
Parallel Stream API: 809 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 32058 мс. Задержка: 1
Parallel Stream API: 3997 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 64190 мс. Задержка: 1
Parallel Stream API: 8025 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 96311 мс. Задержка: 1
Parallel Stream API: 12007 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 127811 мс. Задержка: 1
Parallel Stream API: 20005 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 216848 мс. Задержка: 1
Parallel Stream API: 28539 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 340980 мс. Задержка: 1
Parallel Stream API: 40031 мс. Задержка: 1
```
#### Второй запуск
Размер коллекции: 5000
```
Sequential Stream API : 6413 мс. Задержка: 1
Parallel Stream API: 804 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 32181 мс. Задержка: 1
Parallel Stream API: 4762 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 76517 мс. Задержка: 1
Parallel Stream API: 11908 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 110753 мс. Задержка: 1
Parallel Stream API: 15009 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 128450 мс. Задержка: 1
Parallel Stream API: 16028 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 222149 мс. Задержка: 1
Parallel Stream API: 34387 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 380049 мс. Задержка: 1
Parallel Stream API: 47311 мс. Задержка: 1
```
#### Третий запуск
Размер коллекции: 5000
```
Sequential Stream API : 7596 мс. Задержка: 1
Parallel Stream API: 947 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 37965 мс. Задержка: 1
Parallel Stream API: 4749 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 75991 мс. Задержка: 1
Parallel Stream API: 9433 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 114107 мс. Задержка: 1
Parallel Stream API: 17836 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 151813 мс. Задержка: 1
Parallel Stream API: 18395 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 228089 мс. Задержка: 1
Parallel Stream API: 28057 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 379185 мс. Задержка: 1
Parallel Stream API: 47582 мс. Задержка: 1
```
#### Четвертый запуск
Размер коллекции: 5000
```
Sequential Stream API : 7584 мс. Задержка: 1
Parallel Stream API: 954 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 37913 мс. Задержка: 1
Parallel Stream API: 4730 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 75998 мс. Задержка: 1
Parallel Stream API: 9526 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 114033 мс. Задержка: 1
Parallel Stream API: 14257 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 151242 мс. Задержка: 1
Parallel Stream API: 18990 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 227200 мс. Задержка: 1
Parallel Stream API: 28501 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 379617 мс. Задержка: 1
Parallel Stream API: 58832 мс. Задержка: 1
```
#### Пятый запуск
Размер коллекции: 5000
```
Sequential Stream API : 7591 мс. Задержка: 1
Parallel Stream API: 950 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 37970 мс. Задержка: 1
Parallel Stream API: 4747 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 75931 мс. Задержка: 1
Parallel Stream API: 9505 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 113977 мс. Задержка: 1
Parallel Stream API: 14237 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 151931 мс. Задержка: 1
Parallel Stream API: 18967 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 228467 мс. Задержка: 1
Parallel Stream API: 28522 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 382286 мс. Задержка: 1
Parallel Stream API: 45936 мс. Задержка: 1
```
#### Шестой запуск
Размер коллекции: 5000
```
Sequential Stream API : 7542 мс. Задержка: 1
Parallel Stream API: 923 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 38091 мс. Задержка: 1
Parallel Stream API: 4752 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 76527 мс. Задержка: 1
Parallel Stream API: 9517 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 114930 мс. Задержка: 1
Parallel Stream API: 14313 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 152246 мс. Задержка: 1
Parallel Stream API: 18255 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 228516 мс. Задержка: 1
Parallel Stream API: 28622 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 380074 мс. Задержка: 1
Parallel Stream API: 59154 мс. Задержка: 1
```
#### Седьмой запуск
Размер коллекции: 5000
```
Sequential Stream API : 7610 мс. Задержка: 1
Parallel Stream API: 951 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 37995 мс. Задержка: 1
Parallel Stream API: 4749 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 75966 мс. Задержка: 1
Parallel Stream API: 9503 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 114075 мс. Задержка: 1
Parallel Stream API: 14164 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 152020 мс. Задержка: 1
Parallel Stream API: 18946 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 227998 мс. Задержка: 1
Parallel Stream API: 28431 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 380192 мс. Задержка: 1
Parallel Stream API: 47289 мс. Задержка: 1
```
#### Восьмой запуск
Размер коллекции: 5000
```
Sequential Stream API : 7590 мс. Задержка: 1
Parallel Stream API: 959 мс. Задержка: 1

```
Размер коллекции: 25000
```
Sequential Stream API : 38017 мс. Задержка: 1
Parallel Stream API: 4739 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 75999 мс. Задержка: 1
Parallel Stream API: 9476 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 113990 мс. Задержка: 1
Parallel Stream API: 14168 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 151990 мс. Задержка: 1
Parallel Stream API: 18909 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 227987 мс. Задержка: 1
Parallel Stream API: 28374 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 379891 мс. Задержка: 1
Parallel Stream API: 47269 мс. Задержка: 1
```
#### Девятый запуск
Размер коллекции: 5000
```
Sequential Stream API : 7565 мс. Задержка: 1
Parallel Stream API: 924 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 38008 мс. Задержка: 1
Parallel Stream API: 4722 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 76012 мс. Задержка: 1
Parallel Stream API: 9456 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 114010 мс. Задержка: 1
Parallel Stream API: 14154 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 152539 мс. Задержка: 1
Parallel Stream API: 18942 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 226561 мс. Задержка: 1
Parallel Stream API: 27650 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 380467 мс. Задержка: 1
Parallel Stream API: 47655 мс. Задержка: 1
```
#### Десятый запуск
Размер коллекции: 5000
```
Sequential Stream API : 7595 мс. Задержка: 1
Parallel Stream API: 948 мс. Задержка: 1
```
Размер коллекции: 25000
```
Sequential Stream API : 38001 мс. Задержка: 1
Parallel Stream API: 4762 мс. Задержка: 1
```
Размер коллекции: 50000
```
Sequential Stream API : 75979 мс. Задержка: 1
Parallel Stream API: 9515 мс. Задержка: 1
```
Размер коллекции: 75000
```
Sequential Stream API : 113999 мс. Задержка: 1
Parallel Stream API: 14276 мс. Задержка: 1
```
Размер коллекции: 100000
```
Sequential Stream API : 152011 мс. Задержка: 1
Parallel Stream API: 19047 мс. Задержка: 1
```
Размер коллекции: 150000
```
Sequential Stream API : 228040 мс. Задержка: 1
Parallel Stream API: 28594 мс. Задержка: 1
```
Размер коллекции: 250000
```
Sequential Stream API : 379905 мс. Задержка: 1
Parallel Stream API: 47571 мс. Задержка: 1
```

--- 