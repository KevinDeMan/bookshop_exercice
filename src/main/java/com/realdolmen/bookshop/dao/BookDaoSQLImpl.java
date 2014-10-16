package com.realdolmen.bookshop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;


import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.realdolmen.bookshop.domain.Book;

@Repository(value="BookDaoSqlImpl")
@Transactional
public class BookDaoSQLImpl implements BookDao{
	
	@PersistenceContext
	EntityManager em;
	
	private final static String QUERY_BOOK_ALL = "Select b from Book b";
	private final static String QUERY_BOOK_ISBN = "Select b from Book b where b.isbn = :isbn";
	private final static String QUERY_BOOK_CATEGORY = "Select b from Book b where b.genre = :genre";

	@Override
	public Long storeBook(Book book) {
		System.out.println("In StoreBook");
		if( book.getId() == 0L ) {
			System.out.println("Persisting new book");
			em.persist(book);
		} else {
			System.out.println("Updating existing book");
			em.merge(book);
		}
		return book.getId();
	}

	@Override
	public Book getBookById(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	public Book getBookTitleByIsbn(String isbn) {
		Query query = em.createQuery(QUERY_BOOK_ISBN);
		query.setParameter("isbn", isbn);
		try{
			return (Book) query.getSingleResult();
		} catch(NoResultException nre) {
			return null;
		}
		
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		Query query = em.createQuery(QUERY_BOOK_CATEGORY);
		query.setParameter("genre", category);
		return query.getResultList();
	}

	@Override
	public List<Book> getAllBooks() {
		Query query = em.createQuery(QUERY_BOOK_ALL);
		return query.getResultList();
	}

}
