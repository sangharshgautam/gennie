package org.telegram.client.adpater;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;

public class JodaDateTimeAdapter extends XmlAdapter<Long, DateTime> {

	@Override
	public DateTime unmarshal(Long timeStamp) throws Exception {
		return new DateTime(timeStamp * 1000L);
	}

	@Override
	public Long marshal(DateTime dateTime) throws Exception {
		return dateTime.getMillis()/1000;
	}
	public static void main(String[] args) throws Exception {
		DateTime dateTime = new DateTime();
		System.out.println(dateTime);
		Long marshal = new JodaDateTimeAdapter().marshal(dateTime);
		System.out.println(marshal);
		DateTime unmarshal = new JodaDateTimeAdapter().unmarshal(marshal);
		System.out.println(unmarshal);
	}
}
