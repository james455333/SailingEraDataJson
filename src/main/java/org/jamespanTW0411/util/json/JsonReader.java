package org.jamespanTW0411.util.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class JsonReader {
    private final Gson gson = new Gson();
    public <T> T read(URL resource, Class<T> tarCla){
        try (Reader reader = Files.newBufferedReader(Paths.get(resource.toURI()));){
            return gson.fromJson(reader, tarCla);
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    };

    public <T> List<T> readAsList(URL resource, Class<T> tarCla){
        try (Reader reader = Files.newBufferedReader(Paths.get(resource.toURI()));){
            return gson.fromJson(reader, TypeToken.getParameterized(List.class, tarCla).getType());
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
