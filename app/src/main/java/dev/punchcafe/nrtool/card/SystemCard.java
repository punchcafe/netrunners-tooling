package dev.punchcafe.nrtool.card;

public class SystemCard {

    public SystemCard(final String systemType, final String subnetMask, final String additionalEffect){
        this.systemType = systemType;
        this.subnetMask = subnetMask;
        this.additionalEffect = additionalEffect;
    }

    public String systemType;
    public String subnetMask;
    public String additionalEffect;
}
