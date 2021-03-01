package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;
import model.Client;
import model.OrderItem;
import model.Product;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AbstractDAO<T> {
	
	protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
	private final Class<T> type;

	public AbstractDAO() {
		this.type=(Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	private String select(String search) {
		StringBuilder st=new StringBuilder();
		st.append("SELECT ");
		st.append(" * ");
		st.append(" FROM ordermanagement.");
		st.append(type.getSimpleName());
		st.append("WHERE"+search+"=?");
		return st.toString();	
	}
	
	private String delete(String search) {
		StringBuilder st=new StringBuilder();
		st.append("DELETE FROM ordermanagement.");
		st.append(type.getSimpleName());
		st.append("WHERE"+search+"=?");
		return st.toString();	
	}
	
	public void deleteObj(int id, String field) {
		Connection dbConnection = null;
		PreparedStatement deleteStatement = null;
		String query = delete(field);
		try {
			dbConnection = ConnectionFactory.getConnection();
			deleteStatement = dbConnection.prepareStatement(query);
			deleteStatement.setInt(1, id);
			deleteStatement.executeUpdate();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Delete " + e.getMessage());
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Delete " + e.getMessage());

		} finally {
			ConnectionFactory.close(deleteStatement);
			ConnectionFactory.close(dbConnection);
		}

	}
	
	private String update() {
		int i;
		StringBuilder st=new StringBuilder();
		st.append("UPDATE ordermanagement. ");
		st.append(type.getSimpleName());
		st.append(" SET ");
		for (Field field: type.getDeclaredFields()) {
			//st.append("?,");
			st.append(field.getName()+"=?,");
		}
		st.deleteCharAt(st.length()-1)	;
		st.append(" WHERE id=?");
		return st.toString();	
		
	}
	
	public T updateObj(T t) throws IllegalArgumentException, IllegalAccessException {
		Connection dbConnection = null;
		PreparedStatement updateStatement = null;
		String query = update();
		ResultSet rs = null;
		try {
			dbConnection = ConnectionFactory.getConnection();
			updateStatement = dbConnection.prepareStatement(query);
			int variable=1;
			for(Field field: t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object obj;
				obj = field.get(t);
				updateStatement.setObject(variable, obj);
				if (variable == 1)
					updateStatement.setObject(t.getClass().getDeclaredFields().length+1, obj);
				variable++;
			}

			updateStatement.executeUpdate();

		} catch (SQLException e) {
			//LOGGER.log(Level.WARNING, type.getName() + "DAO:Update " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(updateStatement);
			ConnectionFactory.close(dbConnection);
		}
		return t;
	}
	
	private String insert() {
		int i;
		StringBuilder st=new StringBuilder();
		st.append("INSERT INTO ordermanagement. ");
		st.append(type.getSimpleName());
		st.append(" VALUES( ");
		for (i=0; i<type.getDeclaredFields().length-1; i++) {
			st.append("?,");
		}
		st.append("?)");	
		return st.toString();	
	}
	
	/*public OrderItem insert(OrderItem t) throws IllegalArgumentException, IllegalAccessException {
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		String query = insert();
		try {
			connection = ConnectionFactory.getConnection();
			statement = connection.prepareStatement(query);

			int variable = 1;
			for (Field field : t.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				Object obj;
				obj = field.get(t);
				statement.setObject(variable, obj);
				variable++;
			}
			statement.executeUpdate();

		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, type.getName() + "DAO:Insert " + e.getMessage());
		
		} finally {
			ConnectionFactory.close(resultSet);
			ConnectionFactory.close(statement);
			ConnectionFactory.close(connection);
		}

		return t;
	}*/
	
	public String findAll() {
		StringBuilder st=new StringBuilder();
		st.append("SELECT ");
		st.append(" * ");
		st.append(" FROM ordermanagement.");
		st.append(type.getSimpleName());
		return st.toString();
	}
	
	public ArrayList<Client> findAllArrays() {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement findAllStatement=null;
		ResultSet rs=null;
		int insertedId = -1;
		String query=findAll();
		try {
			dbConnection = ConnectionFactory.getConnection();
			findAllStatement = dbConnection.prepareStatement(query);
			rs = findAllStatement.executeQuery();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "AbstractDAO: findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
		return null;
	}
	
	public ArrayList<Product> findAllArrays2() {
		Connection dbConnection = ConnectionFactory.getConnection();

		PreparedStatement findAllStatement=null;
		ResultSet rs=null;
		int insertedId = -1;
		String query=findAll();
		try {
			dbConnection = ConnectionFactory.getConnection();
			findAllStatement = dbConnection.prepareStatement(query);
			rs = findAllStatement.executeQuery();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "AbstractDAO: findAll " + e.getMessage());
		} finally {
			ConnectionFactory.close(rs);
			ConnectionFactory.close(findAllStatement);
			ConnectionFactory.close(dbConnection);
		}
		return null;
	}
		
}
