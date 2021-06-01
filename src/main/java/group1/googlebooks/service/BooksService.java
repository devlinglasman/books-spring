package group1.googlebooks.service;

import group1.googlebooks.model.VolumesCollection;

public interface BooksService {
    VolumesCollection get(String searchTerm, int maxResults);
}
