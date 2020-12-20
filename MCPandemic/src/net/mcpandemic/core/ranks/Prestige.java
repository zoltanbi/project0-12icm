package net.mcpandemic.core.ranks;

public enum Prestige {

    ONE("I-", "❄"),
    TWO("II-", "❊"),
    THREE("III-", "✽"),
    FOUR("IV-", "❋"),
    FIVE("V-", "❇"),
    SIX("VI-", "✵"),
    SEVEN("VII-", "❀"),
    EIGHT("VIII-", "❁"),
    NINE("IX-", "✬"),
    TEN("X-", "✫"),
    ELEVEN("XI-", "✷"),
    TWELVE("XII-", "✺"),
    THIRTEEN("XIII-", "✪"),
    FOURTEEN("XIV-", "❂"),;

    private String prNum;
    private String prSymbol;

    Prestige(String prNum, String prSymbol) {
        this.prNum = prNum;
        this.prSymbol = prSymbol;
    }

    public String getPrNum() {
        return prNum;
    }

    public String getPrSymbol() {
        return prSymbol;
    }
}

