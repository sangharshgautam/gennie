package uk.co.sangharsh.dao;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.telegram.client.pojo.Update;

@Repository
public class UpdateDaoImpl extends  AbstractBaseDaoImpl<Update> implements UpdateDao{

	protected UpdateDaoImpl() {
		super(Update.class);
	}

	@Override
	public List<Update> findUnprocessed() {
		return (List<Update>)getSessionFactory().createCriteria(Update.class).add(Restrictions.eq("processed", false)).list();
	}
}
