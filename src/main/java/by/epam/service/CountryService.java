package by.epam.service;

import by.epam.exception.ServiceException;
import by.epam.model.Country;

import java.util.List;

public interface CountryService {
    List<Country> getAll() throws ServiceException;
    Country getByName(String name) throws ServiceException;
    Country save(Country country) throws ServiceException;
    void delete(int id) throws ServiceException;
}
