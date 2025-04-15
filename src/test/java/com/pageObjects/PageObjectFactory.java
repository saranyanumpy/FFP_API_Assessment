package com.lms.pageObjects;

public class PageObjectFactory {
	
	private static LoginPage loginPage = new LoginPage();
	private static ClassAddPage classAddPage = new ClassAddPage();
	private static ClassPage classPage = new ClassPage();
	private static DashboardPage dashboardPage = new DashboardPage();
	private static ProgramPage programPage = new ProgramPage();
	private static BatchPage batchPage = new BatchPage();
	
	public static LoginPage getLoginPage() {
		loginPage.initElements();
		return loginPage;
	}
	public static ClassAddPage getClassAddPage() {
		classAddPage.initElements();
		return classAddPage;
	}
	
	public static ClassPage getClassPage() {
		
		return classPage;
	}
	public static DashboardPage getDashboardPage() {
		return dashboardPage;
	}
	public static ProgramPage getProgramPage() {
		programPage.initElements();
		return programPage;
	}
	public static BatchPage getBatchPage() {
		batchPage.initElements();
		return batchPage;
	}
	
}
