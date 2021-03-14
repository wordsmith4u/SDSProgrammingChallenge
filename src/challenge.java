public class challenge {

    public static void updateItems(Item[] items) {
        for (int i = 0; i < items.length; i++) {
            Item currentItem = items[i];
            updateSellIn(currentItem);
            updateQuality(currentItem);

        }
    }

    private static void updateQuality(Item currentItem) {
        assert (currentItem.getQuality() >= 0 &&
                (currentItem.getName() == "Sulfuras" ||
                        currentItem.getQuality() <= 50));

        int changeInQuality = 0;

        if (currentItem.getName() == "Aged Brie") {
            changeInQuality++;
        } else if (currentItem.getName() == "Backstage passes") {
            if (currentItem.getSellIn() < 0) {
                currentItem.setQuality(0);
            } else {
                changeInQuality++;
                if (currentItem.getSellIn() < 11) {
                    changeInQuality++;
                }
                if (currentItem.getSellIn() < 6) {
                    changeInQuality++;
                }
            }
        } else if (currentItem.getName() != "Sulfuras") {
            changeInQuality--;
            if (currentItem.getSellIn() < 0) {
                changeInQuality *= 2;
            }
            if (currentItem.getName().startsWith("Conjured")) {
                changeInQuality *= 2;
            }
        }
        if (changeInQuality > 0) {
            for (int i = 0; i < changeInQuality; i++) {
                incrementQuality(currentItem);
            }
        } else if (changeInQuality < 0) {
            for (int i = changeInQuality; i < 0; i++) {
                decrementQuality(currentItem);
            }
        }
        assert (currentItem.getQuality() >= 0 &&
                (currentItem.getName() == "Sulfuras" ||
                        currentItem.getQuality() <= 50));
    }

    private static void updateSellIn(Item currentItem) {
        if (currentItem.getName() != "Sulfuras") {
            currentItem.setSellIn(currentItem.getSellIn() - 1);
        }
    }

    private static void incrementQuality(Item currentItem) {
        if (currentItem.getQuality() < 50) {
            currentItem.setQuality(currentItem.getQuality() + 1);
        }
    }

    private static void decrementQuality(Item currentItem) {
        if (currentItem.getQuality() > 0) {
            currentItem.setQuality(currentItem.getQuality() - 1);
        }
    }

}