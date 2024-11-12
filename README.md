# Лабораторная работа №2

## Задание 1

В один из методов, использовавшийся для сбора статистики, добавить возможность задать задержку, имитирующую задержку получения результата, например из базы данных. К примеру, был метод getName(), в который нужно добавить параметр getName(long delay).

## Задание 2

Заменить последовательный стрим, собирающий статистику из лабораторной №1, на параллельный. Поменять итоговую коллекцию, где собирается результат, на соответствующую потокобезопасную. Измерить производительность для разного количества элементов с дополнительной задержкой и без задержки. Для случаев с задержкой и без найти количество элементов, при котором сбор статистики последовательным и параллельным стримами дают одинаковую скорость выполнения. 

**Результаты измерений в «Задание 4»**

## Задание 3

Оптимизировать параллельный сбор статистики, реализовав собственный ForkJoinPool или Spliterator. Измерить производительность своего варианта.

**Результаты измерений в «Задание 4»**

## Задание 4

Измерения производительности выполнять с помощью фрейворка JMH.

- PharmacyDrugBenchmark.**customSpliterator** - собственный Spliterator (задание 3)
- PharmacyDrugBenchmark.**parallelStream** - параллельный стрим
- PharmacyDrugBenchmark.**sequentialStream** - последовательный стрим
- **delay** - задержка (0 - без задержки / 1 - с задержкой)
- **size** - количество объектов
- **Score** - скорость выполнения

```
Benchmark                          (delay)  (size)   Mode  Cnt     Score      Error  Units
PharmacyDrugJMH.customSpliterator        0    5000  thrpt    6  9076,595 ± 1616,955  ops/s
PharmacyDrugJMH.customSpliterator        0   50000  thrpt    6   733,463 ±   46,053  ops/s
PharmacyDrugJMH.customSpliterator        0  250000  thrpt    6   154,142 ±    7,033  ops/s
PharmacyDrugJMH.customSpliterator        1    5000  thrpt    6   554,515 ±   38,000  ops/s
PharmacyDrugJMH.customSpliterator        1   50000  thrpt    6    52,834 ±    6,143  ops/s
PharmacyDrugJMH.customSpliterator        1  250000  thrpt    6     8,838 ±    3,197  ops/s
PharmacyDrugJMH.parallelStream           0    5000  thrpt    6  7827,192 ± 1191,936  ops/s
PharmacyDrugJMH.parallelStream           0   50000  thrpt    6   655,500 ±   22,282  ops/s
PharmacyDrugJMH.parallelStream           0  250000  thrpt    6    79,619 ±   27,289  ops/s
PharmacyDrugJMH.parallelStream           1    5000  thrpt    6   491,452 ±   10,153  ops/s
PharmacyDrugJMH.parallelStream           1   50000  thrpt    6    44,951 ±    5,216  ops/s
PharmacyDrugJMH.parallelStream           1  250000  thrpt    6     9,659 ±    1,578  ops/s
PharmacyDrugJMH.sequentialStream         0    5000  thrpt    6  4831,408 ±  486,203  ops/s
PharmacyDrugJMH.sequentialStream         0   50000  thrpt    6   431,200 ±   13,482  ops/s
PharmacyDrugJMH.sequentialStream         0  250000  thrpt    6    35,138 ±    2,400  ops/s
PharmacyDrugJMH.sequentialStream         1    5000  thrpt    6    18,815 ±    0,306  ops/s
PharmacyDrugJMH.sequentialStream         1   50000  thrpt    6     1,881 ±    0,018  ops/s
PharmacyDrugJMH.sequentialStream         1  250000  thrpt    6     0,375 ±    0,003  ops/s
```