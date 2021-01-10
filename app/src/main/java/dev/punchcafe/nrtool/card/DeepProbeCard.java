package dev.punchcafe.nrtool.card;

public class DeepProbeCard {

    public DeepProbeCard(final String cardName, final String predicate, final String additionalEffect){
        this.cardName = cardName;
        this.predicate = predicate;
        this.additionalEffect = additionalEffect;
    }

    public final String cardName;
    public final String predicate;
    public final String additionalEffect;
}
