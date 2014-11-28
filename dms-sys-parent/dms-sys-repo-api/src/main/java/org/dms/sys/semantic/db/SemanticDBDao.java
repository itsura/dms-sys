package org.dms.sys.semantic.db;

import java.util.List;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;

/**
 * The Class SemanticDataSourceService.
 * 
 * @author hlungov
 * 
 */
public interface SemanticDBDao {

	/**
	 * Insert.
	 *
	 * @param statement the statement
	 */
	void insert(Statement statement);
	
	/**
	 * Insert.
	 *
	 * @param statements the statements
	 */
	void insert(List<Statement> statements);
	
	/**
	 * Removes the.
	 *
	 * @param subject the subject
	 * @param predicate the predicate
	 * @param object the object
	 */
	void remove(Resource subject, URI predicate, Value object);
	
	/**
	 * Update.
	 *
	 * @param subject the subject
	 * @param predicate the predicate
	 * @param object the object
	 */
	void update(Resource subject, URI predicate, Value object);
	
	
	/**
	 * Gets the.
	 *
	 * @param subject the subject
	 * @param predicate the predicate
	 * @param object the object
	 * @param includeInferred the include inferred
	 * @param contexts the contexts
	 * @return the list
	 */
	List<Statement> get(Resource subject, URI predicate, Value object,boolean includeInferred,Resource[] contexts);
	
	/**
	 * Query.
	 *
	 * @param queryString the query string
	 * @return the list
	 */
	List<Statement> query(String queryString);
	
}
