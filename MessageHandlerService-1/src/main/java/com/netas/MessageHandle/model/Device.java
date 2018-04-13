package com.netas.MessageHandle.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "device")
public class Device  {
	public Device() {
		super();
	}
	public Device(Long id, String name, String authToken, String description, String templateId, boolean online,
			String createdBy, Long createdDate, String lastUpdatedBy, Long lastUpdatedDate) {
		super();
		this.id = id;
		this.name = name;
		this.authToken = authToken;
		this.description = description;
		this.templateId = templateId;
		this.online = online;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastUpdatedBy = lastUpdatedBy;
		this.lastUpdatedDate = lastUpdatedDate;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long                 id;
    private String               name;
    private String               authToken;
    private String               description;
    private String               templateId;
   // private DeviceInfo           deviceInfo;
  //  private Address              address;
   // private Capability           capability;
    private boolean              online;
   // private AdministrationStatus administrationStatus;
    private String               createdBy;
    private Long                 createdDate;
    private String               lastUpdatedBy;
    private Long                 lastUpdatedDate;
//	private List<Messages> messages;
//    private List<Messages>       messages;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTemplateId() {
		return templateId;
	}
	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Long getLastUpdatedDate() {
		return lastUpdatedDate;
	}
	public void setLastUpdatedDate(Long lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
//	public List<Messages> getMessages() {
//		return messages;
//	}
//	public void setMessages(List<Messages> messages) {
//		this.messages = messages;
//	}

    
    
}
