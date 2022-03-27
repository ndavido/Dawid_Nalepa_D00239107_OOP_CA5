package project.deliverable1;

public enum SortType
{
    Ascending(1, "Sort Ascending"),
    Descending(-1, "Sort Descending");

    private int value;
    private String description;

    private SortType(int value, String description)
    {
        this.value =  value;
        this.description = description;
    }

    public int getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return this.description;
    }
}
