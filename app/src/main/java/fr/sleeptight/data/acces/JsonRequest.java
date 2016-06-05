package fr.sleeptight.data.acces;


import android.text.TextUtils;

import com.yolanda.nohttp.JsonObjectRequest;
import com.yolanda.nohttp.RequestMethod;

/**
 * Created by Zhengrui on 6/5/2016.
 */
public class JsonRequest extends JsonObjectRequest {

    private String contentType;

    public JsonRequest(String url) {
        super(url);
    }

    public JsonRequest(String url, RequestMethod requestMethod) {
        super(url, requestMethod);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public String getContentType() {
        return TextUtils.isEmpty(contentType) ? super.getContentType() : contentType;
    }
}