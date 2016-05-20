package fr.sleeptight.data.acces;

import java.util.List;

/**
 * Created by Zhengrui on 2016/5/11.
 * cette interface a defini les methodes usuelle API
 */
public interface APIHelper {
    String encapJson(List<Object> objects);
    List<Object> desencapJson(String JsonString);
    String requestJson(String url);
    boolean updateJson(String url, String JsonString);
    boolean deleteJson(String url);
}
