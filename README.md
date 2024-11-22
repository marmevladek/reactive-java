# Лабораторная работа №3

## Задание 1

Реализовать подсчет статистики, аналогичный использованному в лабораторной работе №2, с помощью реактивных потоков Observable на RxJava. Должна обеспечиваться многопоточная асинхронная обработка с использованием Scheduler.


## Задание 2

Провести сравнение производительности обработки 500 и 2000 элементов с включенной задержкой для параллельных потоков (из лабы 2) и для реактивных потоков. Необходимо добиться, чтобы производительность реактивных потоков была такой же или лучше, чем при использовании параллельных потоков.


## Задание 3

Отключить аздержку при создании элементов. Реализовать собственный Subscriber для подсчета статистики, регулирующий скорость поступления элементов из потока. Генерацию элементов с поддержкой backpressure производить асинхронно с помощью Flowable. Убедиться, что при большом количестве элементов (больше 100000) система работает стабильно и без задержек.
