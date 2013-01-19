package gr.manousos.service;

import javax.jws.WebService;

import gr.manousos.model.Taxpayer;

@WebService(name = "UserSrv", targetNamespace = "http://service.manousos.gr/")
public interface UserSrv {
	public String test(String msg);

	public void RegisterTaxPayer(Taxpayer user);

	public Taxpayer Register(Taxpayer user);

	public Taxpayer getTaxPayerById(int id);
}
