package org.jamespanTW0411.bean.normal;

import org.jamespanTW0411.bean.normal.Data;

public class Product extends Data{
    private int type;
    private Data[] ports;
    private int[] tags;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Data[] getPorts() {
        return ports;
    }

    public void setPorts(Data[] ports) {
        this.ports = ports;
    }

    public int[] getTags() {
        return tags;
    }

    public void setTags(int[] tags) {
        this.tags = tags;
    }
}
