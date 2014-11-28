package org.dms.sys.semantic.db;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openrdf.model.Resource;
import org.openrdf.model.Statement;
import org.openrdf.model.URI;
import org.openrdf.model.Value;
import org.openrdf.query.GraphQuery;
import org.openrdf.query.GraphQueryResult;
import org.openrdf.query.MalformedQueryException;
import org.openrdf.query.QueryEvaluationException;
import org.openrdf.query.QueryLanguage;
import org.openrdf.query.QueryResults;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.RepositoryResult;

/**
 * The Class SemanticDBServiceImpl.
 */
public class SemanticDBDaoImpl implements SemanticDBDao {

	/** The semantic data source. */
	private SemanticDataSource semanticDataSource;

	/** The repository connection. */
	private RepositoryConnection repositoryConnection;

	/**
	 * Inits the.
	 */
	void init() {
		repositoryConnection = getSemanticDataSource()
				.getRepositoryConnection();
	}

	/**
	 * Insert.
	 *
	 * @param statement the statement
	 * @see org.dms.sys.semantic.db.SemanticDBService#insert(java.net.URL, org.openrdf.rio.RDFFormat)
	 */
	public void insert(Statement statement) {
		try {
			if (repositoryConnection.isOpen()) {
				repositoryConnection.add(statement);
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the.
	 *
	 * @param subject the subject
	 * @param predicate the predicate
	 * @param object the object
	 * @param includeInferred the include inferred
	 * @param contexts the contexts
	 * @return the list
	 * @see org.dms.sys.semantic.db.SemanticDBService#get(org.openrdf.model.Resource, org.openrdf.model.URI, org.openrdf.model.Value, boolean, org.openrdf.model.Resource[])
	 */
	public List<Statement> get(Resource subject, URI predicate, Value object,boolean includeInferred,Resource[] contexts) {
		List<Statement> result = new ArrayList<Statement>();
		try {
			if (repositoryConnection.isOpen()) {
				RepositoryResult<Statement> statements;
				if (contexts != null && contexts.length > 0)
				{
					statements = repositoryConnection.getStatements(subject, predicate, object, includeInferred, contexts);
				}
				else
				{
					statements = repositoryConnection.getStatements(subject, predicate, object, includeInferred);
				}
				while (statements.hasNext()) {
					Statement statement = statements.next();
					result.add(statement);
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Insert.
	 *
	 * @param statements the statements
	 * @see org.dms.sys.semantic.db.SemanticDBService#insert(java.net.URL, org.openrdf.rio.RDFFormat)
	 */
	public void insert(List<Statement> statements) {
		try {
			if (repositoryConnection.isOpen()) {
				repositoryConnection.add(statements);
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Removes the.
	 *
	 * @param subject the subject
	 * @param predicate the predicate
	 * @param object the object
	 * @see org.dms.sys.semantic.db.SemanticDBService#remove(org.openrdf.model.Resource, org.openrdf.model.URI, org.openrdf.model.Value)
	 */
	public void remove(Resource subject, URI predicate, Value object) {
		try {
			if (repositoryConnection.isOpen()) {
				repositoryConnection.remove(subject, predicate, object);
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Update.
	 *
	 * @param subject the subject
	 * @param predicate the predicate
	 * @param object the object
	 * @see org.dms.sys.semantic.db.SemanticDBService#update(org.openrdf.model.Resource, org.openrdf.model.URI, org.openrdf.model.Value)
	 */
	public void update(Resource subject, URI predicate, Value object) {
		try {
			if (repositoryConnection.isOpen()) {
				repositoryConnection.remove(subject, predicate, object);
				repositoryConnection.add(subject, predicate, object);
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Query.
	 *
	 * @param queryString the query string
	 * @return the list
	 * @see org.dms.sys.semantic.db.SemanticDBService#query(java.lang.String)
	 */
	public List<Statement> query(String queryString) {
		List<Statement> result = Collections.emptyList();
		// Example String queryString = "SELECT ?x ?y WHERE { ?x ?p ?y } ";
		try {
			GraphQuery graphQuery = repositoryConnection.prepareGraphQuery(QueryLanguage.SPARQL, queryString);
			GraphQueryResult graphQueryResult = graphQuery.evaluate();
//			Model resultModel = QueryResults.asModel(graphQueryResult);
			result = QueryResults.asList(graphQueryResult);
		} catch (QueryEvaluationException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (MalformedQueryException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Gets the semantic data source.
	 *
	 * @return the semantic data source
	 */
	public SemanticDataSource getSemanticDataSource() {
		return semanticDataSource;
	}

	/**
	 * Sets the semantic data source.
	 *
	 * @param semanticDataSource the new semantic data source
	 */
	public void setSemanticDataSource(SemanticDataSource semanticDataSource) {
		this.semanticDataSource = semanticDataSource;
	}

}
