package com.alagezia37.archivedocuments.model;

public class Document {
	private int id;
	private String fund;
	private String anagraph;
	private String file;
	private boolean permission;

	public Document(String fund, String anagraph, String file,
			boolean permission) {
		this.fund = fund;
		this.anagraph = anagraph;
		this.file = file;
		this.permission = permission;
	}

	public Document() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFund() {
		return fund;
	}

	public void setFund(String fund) {
		this.fund = fund;
	}

	public String getAnagraph() {
		return anagraph;
	}

	public void setAnagraph(String anagraph) {
		this.anagraph = anagraph;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public boolean isPermission() {
		return permission;
	}

	public void setPermission(boolean permission) {
		this.permission = permission;
	}
}
