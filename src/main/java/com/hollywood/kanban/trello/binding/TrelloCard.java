package com.hollywood.kanban.trello.binding;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TrelloCard {

	private String id;
	private String idBoard;
	private String idList;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIdBoard() {
		return idBoard;
	}
	public void setIdBoard(String idBoard) {
		this.idBoard = idBoard;
	}
	public String getIdList() {
		return idList;
	}
	public void setIdList(String idList) {
		this.idList = idList;
	}
	@Override
	public String toString() {
		return "TrelloCard [id=" + id + ", idBoard=" + idBoard + ", idList=" + idList + "]";
	}
	
}
