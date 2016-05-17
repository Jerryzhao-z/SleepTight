package fr.sleeptight.data.traitement;

import java.util.Date;

/**
 * Created by zhengrui on 2016/5/13.
 */
public class SingleEvent {

    private String name;
    private int imageId;
    private String description;
    private Date time;

    public SingleEvent(String name, Date time)
    {
        this.name = name;
        this.time = time;
    }

    public SingleEvent setName(String name)
    {
        this.name = name;
        return this;
    }

    public SingleEvent setTime(Date time)
    {
        this.time = time;
        return this;
    }

    public SingleEvent setImage(int imageId)
    {
        this.imageId = imageId;
        return this;
    }

    public SingleEvent setDescription(String description)
    {
        this.description = description;
        return this;
    }

    public String getName()
    {
        return this.name;
    }

    public int getImageId()
    {
        return this.imageId;
    }

    public String getDescription()
    {
        return this.description;
    }

    public Date getTime()
    {
        return this.time;
    }

}
