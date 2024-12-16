# Лабораторная работа №3

## Задание 1

Реализовать подсчет статистики, аналогичный использованному в лабораторной работе №2, с помощью реактивных потоков Observable на RxJava. Должна обеспечиваться многопоточная асинхронная обработка с использованием Scheduler.


## Задание 2
Провести сравнение производительности обработки 500 и 2000 элементов с включенной задержкой для параллельных потоков (из лабы 2) и для реактивных потоков. Необходимо добиться, чтобы производительность реактивных потоков была такой же или лучше, чем при использовании параллельных потоков.

```
Benchmark                                   (delay)  (size)   Mode  Cnt    Score   Error  Units
PharmacyDrugBenchmark.parallelStream              2     500  thrpt    6    8,705 ± 0,078  ops/s
PharmacyDrugBenchmark.parallelStream              2    2000  thrpt    6    2,178 ± 0,055  ops/s
PharmacyDrugBenchmark.rxJavaObservable            2     500  thrpt    6   14,498 ± 4,951  ops/s
PharmacyDrugBenchmark.rxJavaObservable            2    2000  thrpt    6    6,145 ± 1,006  ops/s
```

## Задание 3
Отключить задержку при создании элементов. Реализовать собственный Subscriber для подсчета статистики, регулирующий скорость поступления элементов из потока. Генерацию элементов с поддержкой backpressure производить асинхронно с помощью Flowable. Убедиться, что при большом количестве элементов (больше 100000) система работает стабильно и без задержек.

```
Benchmark                             (delay)  (size)   Mode  Cnt   Score   Error  Units
PharmacyDrugBenchmark.rxJavaFlowable        0    2000  thrpt    6  38,620 ± 0,182  ops/s
PharmacyDrugBenchmark.rxJavaFlowable        0   50000  thrpt    6  38,290 ± 0,339  ops/s
PharmacyDrugBenchmark.rxJavaFlowable        0  100000  thrpt    6  37,669 ± 2,052  ops/s
PharmacyDrugBenchmark.rxJavaFlowable        0  250000  thrpt    6  37,035 ± 2,042  ops/s
```