package dev.punchcafe.nrtool.card;

public class LightProbeCard {

    public LightProbeCard(final String cardName, final String predicate, final String additionalEffect){
        this.cardName = cardName;
        this.predicate = predicate;
        this.additionalEffect = additionalEffect;
    }

    public String cardName;
    public String predicate;
    public String additionalEffect;
}
