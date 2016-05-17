package fr.sleeptight.presentation.calendar;

import android.graphics.Bitmap;

import java.util.Date;

import fr.sleeptight.data.traitement.SingleEvent;

/**
 * Created by User on 5/17/2016.
 */
public interface iEventView {

    void showDescription(String description);
    void showTime(Date time);
    void showImage(Bitmap image);
    void showImportance(int importance);

}
