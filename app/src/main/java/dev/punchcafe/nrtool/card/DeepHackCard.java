package dev.punchcafe.nrtool.card;

public class DeepHackCard {

    public DeepHackCard(final String cardName, final String systemType,final String additionalEffect){
        this.systemType = systemType;
        this.additionalEffect = additionalEffect;
    }

    public String cardName;
    public String systemType;
    public String additionalEffect;
}
