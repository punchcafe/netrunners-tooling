package dev.punchcafe.nrtool.card;

public class LightHackCard {

    public LightHackCard( final String cardName, final String subnetMask, final String additionalEffect){
        this.cardName = cardName;
        this.subnetMask = subnetMask;
        this.additionalEffect = additionalEffect;
    }

    public String cardName;
    public String subnetMask;
    public String additionalEffect;
}
