package gr.manousos.service;

import javax.jws.WebService;

import gr.manousos.model.E2;

@WebService(name = "DocumentSrv", targetNamespace = "http://service.manousos.gr/")
public interface DocumentSrv {
	public E2 finalSubmitE2(E2 entity);

	public String test(String msg);

	public E2 getE2DocById(int taxPayerID, int year);
}
