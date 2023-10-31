package ru.kpfu.itis.charntsev.net.util;

import com.cloudinary.Cloudinary;

import java.util.HashMap;
import java.util.Map;

public class CloudinaryUtil {
    private static Cloudinary cloudinary;

    public static Cloudinary getInstance() {
        if (cloudinary == null) {
            Map<String, String> configMap = new HashMap<>();
            configMap.put("cloud_name", "charntsev");
            configMap.put("api_key", "696675875469677");
            configMap.put("api_secret", "PW8fNPvvPjFNcIooDs5xy337mVQ");
            cloudinary = new Cloudinary(configMap);
        }
        return cloudinary;
    }
}
