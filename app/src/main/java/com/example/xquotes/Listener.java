package com.example.xquotes;

import java.util.List;

public interface Listener {
    void fetch(List<Model> response, String message);
    void Error(String message);
}
