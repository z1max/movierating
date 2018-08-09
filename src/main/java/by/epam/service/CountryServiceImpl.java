package by.epam.service;

import by.epam.dao.CountryDao;
import by.epam.exception.DaoException;
import by.epam.exception.ServiceException;
import by.epam.model.Country;

import java.util.List;

public class CountryServiceImpl implements CountryService {

    private CountryDao countryDao;

    public CountryServiceImpl(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public List<Country> getAll() throws ServiceException {
        try {
            return countryDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("exception.country.getAll");
        }
    }

    @Override
    public Country getByName(String name) throws ServiceException {
        try {
            return countryDao.findByName(name)
                    .orElseThrow(() -> new ServiceException("exception.country.notFound"));
        } catch (DaoException e) {
            throw new ServiceException("exception.country.getByName");
        }
    }

    @Override
    public Country save(Country country) throws ServiceException {
        try {
            return countryDao.save(country);
        } catch (DaoException e) {
            throw new ServiceException("exception.country.save");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            countryDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("exception.country.delete");
        }
    }
}
