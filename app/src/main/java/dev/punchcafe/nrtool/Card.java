package dev.punchcafe.nrtool;

public class Card {
    enum Type{
        LIGHT_HACK,
        DEEP_HACK,
        LIGHT_PROBE,
        DEEP_PROBE,
        SYSTEM
    }
    String name;
    Type type;
    String additionalEffect;
}
