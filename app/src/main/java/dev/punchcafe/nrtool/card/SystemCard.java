package dev.punchcafe.nrtool.card;

public class SystemCard {

    public String cardName;
    public String systemType;
    public String subnetMask;
    public String additionalEffect;

    public SystemCard(final String cardName,
                      final String systemType,
                      final String subnetMask,
                      final String additionalEffect) {
        this.cardName = cardName;
        this.systemType = systemType;
        this.subnetMask = subnetMask;
        this.additionalEffect = additionalEffect;
    }

}
