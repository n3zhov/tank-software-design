package ru.mipt.bit.platformer.observer;

import ru.mipt.bit.platformer.level.Field;

public class Publisher {
    private Subscriber subscriber;
    private Field field;
    public Publisher() {
    };

    public Subscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public void notifySubscriber(Field field) {
        if (this.subscriber != null) {
            this.subscriber.takeHit(field);
        }
    }
}
