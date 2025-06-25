package beans;

	import java.sql.SQLException;
	import java.util.Collection;

	public interface IBeanDao<T> {
		public void doSave(T product) throws SQLException;

		public boolean doDelete(Object code) throws SQLException;

		public T doRetrieveByKey(Object code) throws SQLException;
		
		public Collection<T> doRetrieveAll(String order) throws SQLException;
	}

