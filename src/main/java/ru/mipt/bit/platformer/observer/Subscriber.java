package ru.mipt.bit.platformer.observer;

import ru.mipt.bit.platformer.level.Field;

public interface Subscriber {
    void takeHit(Field field);

    void Subscribe(Publisher publisher);

    void Unsubscribe(Publisher publisher);
}
