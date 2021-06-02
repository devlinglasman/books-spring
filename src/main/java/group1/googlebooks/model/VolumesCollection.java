package group1.googlebooks.model;

import java.io.Serializable;

public class VolumesCollection implements Serializable {
    public int totalItems;
    public String kind;
    public Volume[] items;
}
