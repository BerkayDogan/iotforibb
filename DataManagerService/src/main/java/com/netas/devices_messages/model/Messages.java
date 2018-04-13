
package com.netas.devices_messages.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;




import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


@Entity
@Table(name = "messages")
@JsonDeserialize(as = Messages.class)
public class Messages  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String               id;
	private String               deviceid;
    private String               data;
    public  static int 			     viewCount;
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Messages [id=" + id + ", deviceid=" + deviceid + ", data=" + data + ", viewCount=" + viewCount + "]";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Messages() {
		super();
		
	}
	public Messages(String id, String deviceid, String data,int viewCount) {	
		this.id = id;
		this.deviceid = deviceid;
		this.data = data;
		Messages.viewCount = viewCount;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		Messages.viewCount = viewCount;
	}
	public void increment() {
		viewCount ++;
	}

	

}