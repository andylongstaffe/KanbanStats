package com.hollywood.kanban.stats.binding;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

public class KanbanStats {

	Map<String, Integer> columnCounts;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm", timezone="GMT")
	Date collectionDate;
	
	String boardName;
	
	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public KanbanStats(Map<String, Integer> columnCounts, Date collectionDate, String boardName) {
		this.columnCounts = columnCounts;
		this.collectionDate = collectionDate;
		this.boardName = boardName;
	}

	public Map<String, Integer> getColumnCounts() {
		return columnCounts;
	}

	public void setColumnCounts(Map<String, Integer> columnCounts) {
		this.columnCounts = columnCounts;
	}
	
	public Date getCollectionDate() {
		return collectionDate;
	}

	public void setCollectionDate(Date collectionDate) {
		this.collectionDate = collectionDate;
	}
	
}
