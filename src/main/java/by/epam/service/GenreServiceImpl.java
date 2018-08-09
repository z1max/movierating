package by.epam.service;

import by.epam.dao.GenreDao;
import by.epam.exception.DaoException;
import by.epam.exception.ServiceException;
import by.epam.model.Genre;

import java.util.List;

public class GenreServiceImpl implements GenreService {

    private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public List<Genre> getAll() throws ServiceException {
        try {
            return genreDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("exception.genre.getAll");
        }
    }

    @Override
    public Genre getByName(String name) throws ServiceException {
        try {
            return genreDao.findByName(name)
                    .orElseThrow(() -> new ServiceException("exception.genre.notFound"));
        } catch (DaoException e) {
            throw new ServiceException("exception.genre.getByName");
        }
    }

    @Override
    public Genre save(Genre genre) throws ServiceException {
        try {
            return genreDao.save(genre);
        } catch (DaoException e) {
            throw new ServiceException("exception.genre.save");
        }
    }

    @Override
    public void delete(int id) throws ServiceException {
        try {
            genreDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("exception.genre.delete");
        }
    }
}
