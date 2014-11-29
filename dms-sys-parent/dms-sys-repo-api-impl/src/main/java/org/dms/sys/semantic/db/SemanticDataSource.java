package org.dms.sys.semantic.db;

import org.openrdf.repository.Repository;
import org.openrdf.repository.RepositoryConnection;
import org.openrdf.repository.RepositoryException;
import org.openrdf.repository.config.RepositoryConfigException;
import org.openrdf.repository.manager.RemoteRepositoryManager;

/**
 * The Class SemanticDataSource.
 * 
 * @author hlungov
 * 
 */
public class SemanticDataSource {

	/** The base uri. */
	private final String BASE_URI = "openrdf-sesame";

	/** The repo uri. */
	private final String REPO_URI = "repositories";

	/** The server. */
	private String server;

	/** The name. */
	private String name;

	/** The port. */
	private int port;

	/** The url. */
	private String protocol;

	/** The password. */
	private String password;

	/** The username. */
	private String username;

	/** The manager. */
	private RemoteRepositoryManager manager;

	/** The repository. */
	private Repository repository;

	/** The repository connection. */
	public RepositoryConnection repositoryConnection;

	/**
	 * Inits the.
	 */
	void init() {
		String serverUrl = protocol + "://" + server + ":" + port + "/"
				+ BASE_URI;
		manager = new RemoteRepositoryManager(serverUrl);
		try {
			if (manager.isInitialized()) {
				manager.initialize();
				repository = manager.getRepository(name);
				repositoryConnection = repository.getConnection();
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (RepositoryConfigException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the repository connection.
	 * 
	 * @return the repository connection
	 */
	public RepositoryConnection getRepositoryConnection() {
		return repositoryConnection;
	}

	/**
	 * Close.
	 */
	void close() {
		if (manager != null && manager.isInitialized()) {
			manager.shutDown();
		}

	}

	/**
	 * Gets the name.
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * 
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the port.
	 * 
	 * @param port
	 *            the new port
	 */
	public void setPort(String port) {
		this.port = Integer.valueOf(port);
	}

	/**
	 * Gets the server.
	 * 
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * Sets the server.
	 * 
	 * @param server
	 *            the new server
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * Gets the protocol.
	 * 
	 * @return the protocol
	 */
	public String getProtocol() {
		return protocol;
	}

	/**
	 * Sets the protocol.
	 * 
	 * @param protocol
	 *            the new protocol
	 */
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 * 
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 * 
	 * @param username
	 *            the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
}
