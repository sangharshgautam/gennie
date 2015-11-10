package org.telegram.client.adpater;

import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class UnixDateTimeAdapter extends XmlAdapter<Long, Date> {

	@Override
	public Date unmarshal(Long timeStamp) throws Exception {
		return new Date(timeStamp*1000);
	}

	@Override
	public Long marshal(Date date) throws Exception {
		return date.getTime();
	}
	public static void main(String[] args) throws Exception {
		System.out.println(new UnixDateTimeAdapter().marshal(new Date()));
	}
}
