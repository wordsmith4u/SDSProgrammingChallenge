import org.junit.After;
import org.junit.Test;

public class test {

    Item[] items = new Item[1];

    @After
    public void cleanUp() {
        items = new Item[1];
    }

    @Test
    public void shouldUpdateNormalItem() {
        items[0] = new Item("+5 Dexterity Vest", 10, 20);

        challenge.updateItems(items);
        if ((items[0].getSellIn() != 9
                || items[0].getQuality() != 19)) {
            throw new AssertionError();
        }
    }

    @Test
    public void shouldUpdateMultipleItems() {
        items = new Item[2];

        items[0] = new Item("+5 Dexterity Vest", 10, 20);
        items[1] = new Item("Elixir of the Mongoose", 5, 7);

        challenge.updateItems(items);
        if ((items[0].getSellIn() != 9
                || items[0].getQuality() != 19
                || items[1].getSellIn() != 4
                || items[1].getQuality() != 6)) {
            throw new AssertionError();
        }
    }

    @Test
    public void shouldDegradeExpiredItemsQuicker() {
        items[0] = new Item("+5 Dexterity Vest", -5, 20);

        challenge.updateItems(items);
        if ((items[0].getSellIn() != -6
                || items[0].getQuality() != 18)) {
            throw new AssertionError();
        }
    }

    @Test
    public void shouldNotDegradeLegendary() {
        items[0] = new Item("Sulfuras", 0, 80);

        challenge.updateItems(items);
        if ((items[0].getSellIn() != 0
                || items[0].getQuality() != 80)) {
            throw new AssertionError();
        }
    }

    @Test
    public void shouldImproveAgedBrieQuality() {
        items[0] = new Item("Aged Brie", 2, 4);

        challenge.updateItems(items);
        if ((items[0].getSellIn() != 1
                || items[0].getQuality() != 5)) {
            throw new AssertionError();
        }
    }

    @Test
    public void shouldNotIncreaseQualityPast50() {
        items[0] = new Item("Aged Brie", 10, 50);

        challenge.updateItems(items);
        if ((items[0].getSellIn() != 9
                || items[0].getQuality() != 50)) {
            throw new AssertionError();
        }
    }

    @Test
    public void shouldImproveBackstagePassQuality() {
        items = new Item[3];

        items[0] = new Item("Backstage passes", 15, 20);
        items[1] = new Item("Backstage passes", 10, 20);
        items[2] = new Item("Backstage passes", 5, 20);

        challenge.updateItems(items);
        if ((items[0].getSellIn() != 14
                || items[0].getQuality() != 21
                || items[1].getSellIn() != 9
                || items[1].getQuality() != 22
                || items[2].getSellIn() != 4
                || items[2].getQuality() != 23)) {
            throw new AssertionError();
        }

    }

    @Test
    public void shouldDegradeBackstagePageQuality() {
        items[0] = new Item("Backstage passes", 0, 20);

        challenge.updateItems(items);
        assert (items[0].getSellIn() == -1
                && items[0].getQuality() == 0
        );
    }

    @Test
    public void shouldDegradeConjuredItems() {
        items[0] = new Item("Conjured", 3, 6);
        challenge.updateItems(items);
        assert (items[0].getSellIn() == 2
                && items[0].getQuality() == 4
        );
    }
}