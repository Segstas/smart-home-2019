package ru.sbt.mipt.oop;


@FunctionalInterface
public interface Action {
    void start(Actionable actionable);
}
